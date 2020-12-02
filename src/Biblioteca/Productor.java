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
public class Productor {
    private int idProductor;
    private String rut;

    public Productor() {
    }

    public Productor(int idProductor, String rut) {
        this.idProductor = idProductor;
        this.rut = rut;
    }

    public int getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(int idProductor) {
        this.idProductor = idProductor;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if (rut == null || rut.isEmpty() || "".equals(rut)) {
            throw new IllegalArgumentException("El rut esta vacio");
        } else {
            this.rut = rut;
        }
    }

    @Override
    public String toString() {
        return "Productor{" + "idProductor=" + idProductor + ", rut=" + rut + '}';
    }
    
    
}
