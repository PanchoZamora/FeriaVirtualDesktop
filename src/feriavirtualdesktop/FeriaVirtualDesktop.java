/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feriavirtualdesktop;

import Biblioteca.Usuario;
import Mantenedores.MantenedorUsuario;
import Vistas.Login;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tamar
 */
public class FeriaVirtualDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // RUN PRINCIPAL
        
        /************************************************
         ****** LOGIN PRINCIPAL. INICIO OPERATIVO *******
         ***********************************************/

        
        Login vma = new Login();
        vma.setVisible(true);
        
        
        
        
        /* Mantenedor Usuario*/
        LocalDate fecha_local;
        ZoneId defaultZoneId = ZoneId.systemDefault();
        fecha_local = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.of(Calendar.getInstance().get(Calendar.MONTH)+1), Calendar.getInstance().get(Calendar.DAY_OF_MONTH ));
        Date fecha = Date.from(fecha_local.atStartOfDay(defaultZoneId).toInstant());
        //insert
        MantenedorUsuario mantenedorUsuario = new MantenedorUsuario();
        Usuario usuario = new Usuario(
                "19341129-0", /*rut*/
                "Elmi", /*nombre*/
                "Smisimocon", /*apellido paterno*/
                "chetumare", /*apellido materno*/
                fecha, /*fecha nac*/
                "elmi@smisimo.cl", /*correo*/
                "123456", /*contrasena*/
                "administrador" /*tipo usuario*/
            );
        
        try {
            
            //insert
            
            System.out.println(mantenedorUsuario.crear(usuario));
            //list      
            
            ArrayList<Usuario> lista_usuarios = mantenedorUsuario.listarTodo();
            for (Object usr : lista_usuarios) {
                System.out.println(usr.toString());
            }
            
            //modificar 
            
            mantenedorUsuario.modificar(usuario);
            System.out.println("Usuario modificado");
            
            //eliminar
            
            String rut = "19341129-0";
            mantenedorUsuario.eliminar(rut);
            System.out.println("Usuario eliminado");
            
            //buscar
            
            Usuario usr = mantenedorUsuario.buscarUsuario("154516897");
            System.out.println(usr.toString());
            
            //validar sesion
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
        
        
    }
    
}
