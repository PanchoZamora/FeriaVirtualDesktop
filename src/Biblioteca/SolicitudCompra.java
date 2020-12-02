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
public class SolicitudCompra {
    private int idSolicitud;
    private int idCliente;

    public SolicitudCompra() {
    }

    public SolicitudCompra(int idSolicitud, int idCliente) {
        this.idSolicitud = idSolicitud;
        this.idCliente = idCliente;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "SolicitudCompra{" + "idSolicitud=" + idSolicitud + ", idCliente=" + idCliente + '}';
    }
    
    
}
