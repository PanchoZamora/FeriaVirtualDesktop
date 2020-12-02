/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

/**
 *
 * @author tamar
 */
public class Boleta {
    private int idBoleta;
    private String detalleVenta;
    private int idProducto;
    private int idAdmin;
    private int idVenta;

    public Boleta(int idBoleta, String detalleVenta, int idProducto, int idAdmin, int idVenta) {
        this.idBoleta = idBoleta;
        this.detalleVenta = detalleVenta;
        this.idProducto = idProducto;
        this.idAdmin = idAdmin;
        this.idVenta = idVenta;
    }

    public Boleta() {
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public String getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(String detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public String toString() {
        return "Boleta{" + "idBoleta=" + idBoleta + ", detalleVenta=" + detalleVenta + ", idProducto=" + idProducto + ", idAdmin=" + idAdmin + ", idVenta=" + idVenta + '}';
    }
    
    
}
