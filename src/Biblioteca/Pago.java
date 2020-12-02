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
public class Pago {
    private int idPago;
    private String tipoPago;
    private int idBoleta;

    public Pago() {
    }

    public Pago(int idPago, String tipoPago, int idBoleta) {
        this.idPago = idPago;
        this.tipoPago = tipoPago;
        this.idBoleta = idBoleta;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        if (tipoPago == null || tipoPago.isEmpty() || "".equals(tipoPago)) {
            throw new IllegalArgumentException("El tipo pago esta vacio");
        } else {
            this.tipoPago = tipoPago;
        }
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", tipoPago=" + tipoPago + ", idBoleta=" + idBoleta + '}';
    }
    
    
}
