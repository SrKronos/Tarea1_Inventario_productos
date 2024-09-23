/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Productos;

/**
 *
 * @author Leonardo Loor <leonardoloor1988@gmail.com>
 */
public class Movimiento {
    int id;
    int fkproducto;
    String nombre;
    int stock;
    String tipo;
    int cantidad;
    String fecha;

    public Movimiento() {
    }

    public Movimiento(int id, int fkproducto, String nombre, int stock, String tipo, int cantidad, String fecha) {
        this.id = id;
        this.fkproducto = fkproducto;
        this.nombre = nombre;
        this.stock = stock;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkproducto() {
        return fkproducto;
    }

    public void setFkproducto(int fkproducto) {
        this.fkproducto = fkproducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
