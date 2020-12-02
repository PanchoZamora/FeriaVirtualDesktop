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
public class Contrato {

    private int idContrato;
    private String descripcion;
    private int idProductor;

    public Contrato(int idContrato, String descripcion, int idProductor) {
        this.idContrato = idContrato;
        this.descripcion = descripcion;
        this.idProductor = idProductor;
    }

    public Contrato() {
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty() || "".equals(descripcion)) {
            throw new IllegalArgumentException("La descripcion esta vacia");
        } else {
            this.descripcion = descripcion;
        }
    }

    public int getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(int idProductor) {
        this.idProductor = idProductor;
    }

    @Override
    public String toString() {
        return "Contrato{" + "idContrato=" + idContrato + ", descripcion=" + descripcion + ", idProductor=" + idProductor + '}';
    }

}
