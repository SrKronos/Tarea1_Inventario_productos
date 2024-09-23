/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Productos;

import Conexion.Conexion;
import UI.MenuPrincipal;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Util.Respuesta;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
/**
 *
 * @author Asus.com
 */
public class ControladorProducto {
    Respuesta respuesta = new Respuesta();
    public ControladorProducto() {
        
    }
    public Respuesta GuardarProducto(Producto p1){              
        Conexion con = new Conexion();
        PreparedStatement pst;

        try {
            if (p1.getCodigo() == 0) {
                // Inserción de un nuevo producto
                pst = con.establecerConexion().prepareStatement("INSERT INTO public.producto (nombre, descripcion, precio, stock, imagen, estado) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                // Establecer los valores de los campos
                pst.setString(1, p1.getNombre());
                pst.setString(2, p1.getDescripcion());
                pst.setBigDecimal(3, BigDecimal.valueOf(p1.getPrecio()));
                pst.setInt(4, p1.getStock());
                pst.setString(5, p1.getImagen()); // Asumiendo que tienes un método getImagen()
                pst.setBoolean(6, p1.getEstado());

                // Ejecutar la inserción
                int filasInsertadas = pst.executeUpdate();

                if (filasInsertadas > 0) {
                    // Obtener el ID del nuevo producto insertado
                    ResultSet rs = pst.getGeneratedKeys();
                    if (rs.next()) {
                        int nuevoCodigo = rs.getInt(1);
                        p1.setCodigo(nuevoCodigo); // Actualizar el código del objeto p1
                        setMensaje(p1,"Producto guardado exitosamente ");
                        //JOptionPane.showMessageDialog(null, "Producto guardado exitosamente con código: " + nuevoCodigo);
                    }
                    rs.close();
                } else {
                    setMensaje(p1, "Producto No se Guardo");
                }
            } else {
                // Actualización de un producto existente
                pst = con.establecerConexion().prepareStatement("UPDATE public.producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?, imagen = ?, estado = ? WHERE id = ?");

                // Establecer los valores de los campos
                pst.setString(1, p1.getNombre());
                pst.setString(2, p1.getDescripcion());
                pst.setBigDecimal(3, BigDecimal.valueOf(p1.getPrecio()));
                pst.setInt(4, p1.getStock());
                pst.setString(5, p1.getImagen());
                pst.setBoolean(6, p1.getEstado());
                pst.setInt(7, p1.getCodigo()); // Usar el código existente para la actualización

                // Ejecutar la actualización
                int filasActualizadas = pst.executeUpdate();

                if (filasActualizadas > 0) {
                    setMensaje(p1, "Producto actualizado exitosamente");
                    //JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente");
                } else {
                  setMensaje(p1, "Error al actualizar el producto");
                    //JOptionPane.showMessageDialog(null, "Error al actualizar el producto");
                }
            }

            // Cerrar el PreparedStatement
            pst.close();
            con.establecerConexion().close(); // Cerrar la conexión
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return respuesta;
    }


    void setMensaje(Producto p1, String mensaje){
        respuesta.setP1(p1);
        respuesta.setMensaje(mensaje);
    }
    
public ArrayList<Producto> getListaProductos() {
    ArrayList<Producto> listaProductos = new ArrayList<>();
    Conexion con = new Conexion();
    PreparedStatement pst;
    ResultSet rs;

    try {
        // Consulta SQL para obtener los productos, limitando a 10
        pst = con.establecerConexion().prepareStatement("SELECT * FROM public.producto ORDER BY id DESC LIMIT 10 ");
        rs = pst.executeQuery();

        // Recorrer el ResultSet y llenar la lista de productos
        while (rs.next()) {
            Producto producto = new Producto();
            producto.setCodigo(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setStock(rs.getInt("stock"));
            producto.setImagen(rs.getString("imagen"));
            producto.setEstado(rs.getBoolean("estado"));
            listaProductos.add(producto); // Agregar el producto a la lista            
        }

        // Cerrar recursos
        rs.close();
        pst.close();
        con.establecerConexion().close(); // Cerrar la conexión
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaProductos; // Devolver la lista de productos
}
    public ArrayList<Producto> getListaProductosFiltrado(String buscar) {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Consulta SQL mejorada para obtener los productos, limitando a 10
            String sql = "SELECT * FROM public.producto WHERE nombre ILIKE ? and estado=true LIMIT 10";
            pst = con.establecerConexion().prepareStatement(sql);
            pst.setString(1, "%" + buscar + "%");        
            rs = pst.executeQuery();

            // Recorrer el ResultSet y llenar la lista de productos
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagen(rs.getString("imagen"));
                producto.setEstado(rs.getBoolean("estado"));
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos en el bloque finally para asegurar que se cierren incluso si hay una excepción
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.establecerConexion().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaProductos;
    }
    
    public JasperPrint reporteTodoProducto(){
         Conexion con = new Conexion();
         
        // File reporte = new File(getClass().getResource("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\SistemaMatricula\\src\\main\\java\\com\\mycompany\\sistemamatricula\\Reportes\\Estudiantes.jasper").getFile());        
        File reporte = new File("C:\\MAESTRIA\\MODULO 4 - Plataformas de desarrollo de software\\TAREA 1\\inventario_java\\sistemaInventario\\mavenproject1\\src\\main\\resources\\reportes\\TodoslosProductos.jasper");        
       
         //JOptionPane.showMessageDialog(null, reporte.getPath());
         
         
         if (!reporte.exists()){
             JOptionPane.showMessageDialog(null, "No existe archivo");
             return null;
         }
         
         try {
             InputStream is = new BufferedInputStream(new FileInputStream(reporte.getAbsoluteFile()));
             JasperReport jr = (JasperReport) JRLoader.loadObject(is);
             JasperPrint jp = JasperFillManager.fillReport(jr, null, con.establecerConexion()); 
             return jp;
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
         }
     return null;
         
     }
     
     public JasperPrint reporteTodosProductos(){
         return reporteTodoProducto();
     }
}
