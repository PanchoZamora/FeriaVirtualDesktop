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
public class Cliente {
    private String rut;
    private int idCliente;
    private String tipoCliente;

    public Cliente(String rut, int idCliente, String tipoCliente) {
        this.rut = rut;
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
    }

    public Cliente() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "rut=" + rut + ", idCliente=" + idCliente + ", tipoCliente=" + tipoCliente + '}';
    }
    
    
}
