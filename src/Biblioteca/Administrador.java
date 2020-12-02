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
public class Administrador {
    private int idAdmin;
    private String rut;

    /**
     * @return the idAdmin
     */
    public int getIdAdmin() {
        return idAdmin;
    }

    /**
     * @param idAdmin the idAdmin to set
     */
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    public Administrador(int idAdmin, String rut) {
        this.idAdmin = idAdmin;
        this.rut = rut;
    }

    public Administrador() {
    }

    @Override
    public String toString() {
        return "Administrador{" + "idAdmin=" + idAdmin + ", rut=" + rut + '}';
    }
    
   
}
