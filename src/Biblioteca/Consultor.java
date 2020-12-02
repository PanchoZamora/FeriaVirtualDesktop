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
public class Consultor {

    private int idConsultor;
    private String rut;

    public Consultor(int idConsultor, String rut) {
        this.idConsultor = idConsultor;
        this.rut = rut;
    }

    public Consultor() {
    }

    public int getIdConsultor() {
        return idConsultor;
    }

    public void setIdConsultor(int idConsultor) {
        this.idConsultor = idConsultor;
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
        return "Consultor{" + "idConsultor=" + idConsultor + ", rut=" + rut + '}';
    }

}
