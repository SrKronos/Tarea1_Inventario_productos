/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Productos;

import Conexion.Conexion;
import Util.Respuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Leonardo Loor <leonardoloor1988@gmail.com>
 */
public class ControladorMovimientoProducto {
    int codigo;
    int cantidad;
    String tipo;
    Respuesta respuesta = new Respuesta();
    
    public ControladorMovimientoProducto() {
    }

    public ControladorMovimientoProducto(int codigo, int cantidad, String tipo) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
        
public Respuesta guardarMovimiento(Producto p1, int cantidad, String tipo) {
    
    Conexion con = new Conexion();
    PreparedStatement pstHistorial;
    PreparedStatement pstProducto;
    
    int nuevoStock;

    try {
        // Comprobar si el tipo es ingreso
        switch (tipo) {
            case "INGRESO" -> {
                // Inserción en la tabla historial
                pstHistorial = con.establecerConexion().prepareStatement("INSERT INTO public.historial (fkproducto, cantidad, tipo, fecha) VALUES (?, ?, ?, now())");
                pstHistorial.setInt(1, p1.getCodigo());
                pstHistorial.setInt(2, cantidad);
                pstHistorial.setString(3, "I");
                pstHistorial.executeUpdate();
                pstHistorial.close();
                // Actualizar el stock en la tabla producto
                nuevoStock = p1.getStock() + cantidad;
                pstProducto = con.establecerConexion().prepareStatement("UPDATE public.producto SET stock = ? WHERE id = ?");
                pstProducto.setInt(1, nuevoStock);
                pstProducto.setInt(2, p1.getCodigo());
                pstProducto.executeUpdate();
                pstProducto.close();
                setMensaje(p1,"Ingreso registrado exitosamente.");
                //JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente.");
            }
            case "EGRESO" -> {
                // Comprobar el stock antes de realizar el egreso
                if (p1.getStock() >= cantidad) {
                    // Inserción en la tabla historial
                    pstHistorial = con.establecerConexion().prepareStatement("INSERT INTO public.historial (fkproducto, cantidad, tipo, fecha) VALUES (?, ?, ?, now())");
                    pstHistorial.setInt(1, p1.getCodigo());
                    pstHistorial.setInt(2, cantidad);
                    pstHistorial.setString(3, "E");
                    pstHistorial.executeUpdate();
                    pstHistorial.close();
                    
                    // Actualizar el stock en la tabla producto
                    nuevoStock = p1.getStock() - cantidad;
                    pstProducto = con.establecerConexion().prepareStatement("UPDATE public.producto SET stock = ? WHERE id = ?");
                    pstProducto.setInt(1, nuevoStock);
                    pstProducto.setInt(2, p1.getCodigo());
                    pstProducto.executeUpdate();
                    pstProducto.close();
                      setMensaje(p1,"Egreso registrado exitosamente.");
                   // JOptionPane.showMessageDialog(null, "Egreso registrado exitosamente.");
                } else {
                     setMensaje(p1,"No hay suficiente stock para realizar el egreso");
                    //JOptionPane.showMessageDialog(null, "No hay suficiente stock para realizar el egreso.");
                }
            }
            default -> setMensaje(p1,"Tipo de movimiento no válido");//JOptionPane.showMessageDialog(null, "Tipo de movimiento no válido.");
        }

        // Cerrar la conexión
        con.establecerConexion().close();
    } catch (SQLException e) {
        setMensaje(p1,"Error en sentencia SQL"+e.getMessage());
        //e.printStackTrace();
    }
    return respuesta;
}

public Respuesta EliminarMovimiento(int idHistorial) {
    Conexion con = new Conexion();
    PreparedStatement pstHistorial;
    PreparedStatement pstProducto;
    ResultSet rs;
    int fkproducto, cantidad;
    String tipo;
    Respuesta respuesta = new Respuesta();
    Producto p1 = new Producto();

    try {
        // Buscar el movimiento en el historial
        pstHistorial = con.establecerConexion().prepareStatement("SELECT fkproducto, cantidad, tipo FROM public.historial WHERE id = ?");
        pstHistorial.setInt(1, idHistorial);
        rs = pstHistorial.executeQuery();

        if (rs.next()) {
            fkproducto = rs.getInt("fkproducto");
            cantidad = rs.getInt("cantidad");
            tipo = rs.getString("tipo");

            // Obtener el stock actual del producto
            pstProducto = con.establecerConexion().prepareStatement("SELECT stock FROM public.producto WHERE id = ?");
            pstProducto.setInt(1, fkproducto);
            ResultSet rsProducto = pstProducto.executeQuery();

            if (rsProducto.next()) {
                int stockActual = rsProducto.getInt("stock");
                int nuevoStock;

                // Actualizar el stock según el tipo de movimiento
                switch (tipo) {
                    case "I" -> {
                        nuevoStock = stockActual - cantidad;
                        if (nuevoStock < 0) {
                            setMensaje(p1, "No se puede eliminar el ingreso. El stock resultante sería negativo.");
                            return respuesta;
                        }
                    }
                    case "E" -> nuevoStock = stockActual + cantidad;
                    default -> {
                        setMensaje(p1, "Tipo de movimiento no válido.");
                        return respuesta;
                    }
                }

                // Actualizar el stock en la tabla producto
                pstProducto = con.establecerConexion().prepareStatement("UPDATE public.producto SET stock = ? WHERE id = ?");
                pstProducto.setInt(1, nuevoStock);
                pstProducto.setInt(2, fkproducto);
                pstProducto.executeUpdate();

                // Eliminar el registro del historial
                pstHistorial = con.establecerConexion().prepareStatement("DELETE FROM public.historial WHERE id = ?");
                pstHistorial.setInt(1, idHistorial);
                pstHistorial.executeUpdate();

                setMensaje(p1, "Movimiento eliminado exitosamente.");
            } else {
                setMensaje(p1, "No se encontró el producto asociado al movimiento.");
            }
        } else {
            setMensaje(p1, "No se encontró el movimiento especificado.");
        }

        // Cerrar la conexión
        con.establecerConexion().close();
    } catch (SQLException e) {
        setMensaje(p1, "Error en sentencia SQL: " + e.getMessage());
    }

    return respuesta;
}

public ArrayList<Movimiento> getListaMovimiento() {
    ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
    Conexion con = new Conexion();
    PreparedStatement pst;
    ResultSet rs;

    try {
        // Consulta SQL para obtener los productos, limitando a 10
        pst = con.establecerConexion().prepareStatement("SELECT * FROM public.vhistorial ORDER BY id DESC LIMIT 10 ");
        rs = pst.executeQuery();

        // Recorrer el ResultSet y llenar la lista de productos
        while (rs.next()) {
            Movimiento movimiento = new Movimiento();
            movimiento.setId(rs.getInt("id"));
            movimiento.setFkproducto(rs.getInt("fkproducto"));
            movimiento.setNombre(rs.getString("nombre"));
            movimiento.setStock(rs.getInt("stock"));
            movimiento.setTipo(rs.getString("tipo"));
            movimiento.setCantidad(rs.getInt("cantidad"));
            movimiento.setFecha(rs.getString("fecha"));
            listaMovimientos.add(movimiento); // Agregar el producto a la lista            
        }

        // Cerrar recursos
        rs.close();
        pst.close();
        con.establecerConexion().close(); // Cerrar la conexión
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaMovimientos; // Devolver la lista de productos
}

    void setMensaje(Producto p1, String mensaje){
        respuesta.setP1(p1);
        respuesta.setMensaje(mensaje);
    }
    
}
