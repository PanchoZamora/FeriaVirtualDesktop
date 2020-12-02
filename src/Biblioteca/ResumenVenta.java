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
public class ResumenVenta {
    private int idResumen;
    private int costoTransporte;
    private int impuestos;
    private int pagoServicio;
    private int comisionEmpresa;
    private int idVenta;
    
    public ResumenVenta(){
    }

    public ResumenVenta(int idResumen, int costoTransporte, int impuestos, int pagoServicio, int comisionEmpresa, int idVenta) {
        this.idResumen = idResumen;
        this.costoTransporte = costoTransporte;
        this.impuestos = impuestos;
        this.pagoServicio = pagoServicio;
        this.comisionEmpresa = comisionEmpresa;
        this.idVenta = idVenta;
    }

    public int getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(int idResumen) {
        this.idResumen = idResumen;
    }

    public int getCostoTransporte() {
        return costoTransporte;
    }

    public void setCostoTransporte(int costoTransporte) {
        this.costoTransporte = costoTransporte;
    }

    public int getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(int impuestos) {
        this.impuestos = impuestos;
    }

    public int getPagoServicio() {
        return pagoServicio;
    }

    public void setPagoServicio(int pagoServicio) {
        this.pagoServicio = pagoServicio;
    }

    public int getComisionEmpresa() {
        return comisionEmpresa;
    }

    public void setComisionEmpresa(int comisionEmpresa) {
        this.comisionEmpresa = comisionEmpresa;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public String toString() {
        return "ResumenVenta{" + "idResumen=" + idResumen + ", costoTransporte=" + costoTransporte + ", impuestos=" + impuestos + ", pagoServicio=" + pagoServicio + ", comisionEmpresa=" + comisionEmpresa + ", idVenta=" + idVenta + '}';
    }
    
    
}
