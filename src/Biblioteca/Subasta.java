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
public class Subasta {

    private int idSubasta;
    private int precio;
    private String descTransporte;
    private int idEnvio;

    public Subasta() {
    }

    public Subasta(int idSubasta, int precio, String descTransporte, int idEnvio) {
        this.idSubasta = idSubasta;
        this.precio = precio;
        this.descTransporte = descTransporte;
        this.idEnvio = idEnvio;
    }

    public int getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescTransporte() {
        return descTransporte;
    }

    public void setDescTransporte(String descTransporte) {
        if (descTransporte == null || descTransporte.isEmpty() || "".equals(descTransporte)) {
            throw new IllegalArgumentException("La descripcion del transporte esta vacia");
        } else {
            this.descTransporte = descTransporte;
        }

    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    @Override
    public String toString() {
        return "Subasta{" + "idSubasta=" + idSubasta + ", precio=" + precio + ", descTransporte=" + descTransporte + ", idEnvio=" + idEnvio + '}';
    }

}
