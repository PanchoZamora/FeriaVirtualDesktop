/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.util.Date;

/**
 *
 * @author tamar
 */
public class Usuario {
    private String rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNac;
    private String correo;
    private String contrasena;
    private String tipoUsuario;

    public Usuario() {
    }

    public Usuario(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNac, String correo, String contrasena, String tipoUsuario) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if (rut == null || rut.isEmpty() || "".equals(rut)) {
            throw new IllegalArgumentException("El rut no puede estar vacio");
        } else {
            this.rut = rut;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty() || "".equals(nombre)) {
            throw new IllegalArgumentException("El nombre esta vacio");
        } else {
            this.nombre = nombre;
        }
        
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        if (apellidoPaterno == null || apellidoPaterno.isEmpty() || "".equals(apellidoPaterno)) {
            throw new IllegalArgumentException("El apellido paterno esta vacio");
        } else {
            this.apellidoPaterno = apellidoPaterno;
        }
        
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        if (apellidoMaterno == null || apellidoMaterno.isEmpty() || "".equals(apellidoMaterno)) {
            throw new IllegalArgumentException("El apellido materno esta vacio");
        } else {
            this.apellidoMaterno = apellidoMaterno;
        }  
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.isEmpty() || "".equals(correo)) {
            throw new IllegalArgumentException("El correo esta vacio");
        } else {
            this.correo = correo;
        }    
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (contrasena == null || contrasena.isEmpty() || "".equals(contrasena)) {
            throw new IllegalArgumentException("La contrasena esta vacia");
        } else {
            this.contrasena = contrasena;
        } 
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        if (tipoUsuario == null || tipoUsuario.isEmpty() || "".equals(tipoUsuario)) {
            throw new IllegalArgumentException("El tipo de usuario esta vacio");
        } else {
            this.tipoUsuario = tipoUsuario;
        } 
        
    }

    @Override
    public String toString() {
        return "Usuario{" + "rut=" + rut + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNac=" + fechaNac + ", correo=" + correo + ", contrasena=" + contrasena + ", tipoUsuario=" + tipoUsuario + '}';
    }
    
    
}
