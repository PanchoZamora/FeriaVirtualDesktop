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
public class Venta {
    private int idVenta;
    private String tipoVenta;

    public Venta(int idVenta, String tipoVenta) {
        this.idVenta = idVenta;
        this.tipoVenta = tipoVenta;
    }

    public Venta() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        if (tipoVenta == null || tipoVenta.isEmpty() || "".equals(tipoVenta)) {
            throw new IllegalArgumentException("El tipo de venta esta vacio");
        } else {
            this.tipoVenta = tipoVenta;
        }
        
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", tipoVenta=" + tipoVenta + '}';
    }
    
    
}
