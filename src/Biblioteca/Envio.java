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
public class Envio {
    private int idEnvio;
    private String tipoEnvio;
    private int idTransp;

    public Envio() {
    }

    public Envio(int idEnvio, String tipoEnvio, int idTransp) {
        this.idEnvio = idEnvio;
        this.tipoEnvio = tipoEnvio;
        this.idTransp = idTransp;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        if (tipoEnvio == null || tipoEnvio.isEmpty() || "".equals(tipoEnvio)) {
            throw new IllegalArgumentException("El tipo de envio esta vacio");
        } else {
            this.tipoEnvio = tipoEnvio;
        }
    }

    public int getIdTransp() {
        return idTransp;
    }

    public void setIdTransp(int idTransp) {
        this.idTransp = idTransp;
    }

    @Override
    public String toString() {
        return "Envio{" + "idEnvio=" + idEnvio + ", tipoEnvio=" + tipoEnvio + ", idTransp=" + idTransp + '}';
    }
    
    
}
