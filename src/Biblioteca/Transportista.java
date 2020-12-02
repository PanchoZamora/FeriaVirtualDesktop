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
public class Transportista {
    private int idTransp;
    private String rut;

    public Transportista(int idTransp, String rut) {
        this.idTransp = idTransp;
        this.rut = rut;
    }

    public Transportista() {
    }

    public int getIdTransp() {
        return idTransp;
    }

    public void setIdTransp(int idTransp) {
        this.idTransp = idTransp;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if (rut == null || rut.isEmpty() || "".equals(rut)) {
            throw new IllegalArgumentException("El rut no puede estar Vacio");
        } else {
            this.rut = rut;
        }
    }

    @Override
    public String toString() {
        return "Transportista{" + "idTransp=" + idTransp + ", rut=" + rut + '}';
    }
    
    
}
