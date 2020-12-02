/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Usuario;
import Conexion.ConexionBD;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorUsuario {
    
    // INSERT USUARIO ( CREATE )
    public boolean crear(Usuario o) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_usuario(?,?,?,?,?,?,?,?,?)");

            statement.setString(1, o.getRut());
            statement.setString(2, o.getNombre());
            statement.setString(3, o.getApellidoPaterno());
            statement.setString(4, o.getApellidoMaterno());
            statement.setDate(5, java.sql.Date.valueOf(o.getFechaNac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            statement.setString(6, o.getCorreo());
            statement.setString(7, o.getContrasena());
            statement.setString(8, o.getTipoUsuario());
            statement.registerOutParameter(9, OracleTypes.VARCHAR);

            statement.execute();
            
            String rut_devuelto = statement.getString(9);
            
            statement.close();
            
            if(rut_devuelto != null){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL USUARIO YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
    }
    
    // LISTAR ( READ )
    public ArrayList<Usuario> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {
            String query = "call listar_usuario(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Usuario> listaUsuarios = new ArrayList();

            while (rs.next()) {
                Usuario recuperado = new Usuario();

                recuperado.setRut(rs.getString(1));
                recuperado.setNombre(rs.getString(2));
                recuperado.setApellidoPaterno(rs.getString(3));
                recuperado.setApellidoMaterno(rs.getString(4));
                recuperado.setFechaNac(rs.getDate(5));
                recuperado.setCorreo(rs.getString(6));
                recuperado.setContrasena(rs.getString(7));
                recuperado.setTipoUsuario(rs.getString(8));

                listaUsuarios.add(recuperado);

            }
            return listaUsuarios;
        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());
        }

    }
    // MODIFICAR ( UPDATE )
    public void modificar(Usuario user) throws Exception {

        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {

            CallableStatement stmt = conexion.getConexion().prepareCall("call update_usuario(?, ?,?,?,?,?,?,?)");

            //cambiar el id
            stmt.setString(1, user.getRut());
            stmt.setString(2, user.getNombre());
            stmt.setString(3, user.getApellidoPaterno());
            stmt.setString(4, user.getApellidoMaterno());
            stmt.setDate(5, java.sql.Date.valueOf(user.getFechaNac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            stmt.setString(6, user.getCorreo());
            stmt.setString(7, user.getContrasena());
            stmt.setString(8, user.getTipoUsuario());

            stmt.execute();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el usuario: " + ex.getMessage());
        }
    }
    
    // ELIMINAR (DELETE)
    public void eliminar(String rut) throws Exception {
        try {
            ConexionBD conexion = new ConexionBD();
            conexion.Conectar();
            CallableStatement statement = conexion.getConexion().prepareCall("call delete_usuario(?)");

            statement.setString(1, rut);

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el usuario: " + ex.getMessage());
        }
    }
    
    //BUSCAR POR RUT
    public Usuario buscarUsuario(String rut) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {
            String query = "call buscar_usuario(?,?,?,?,?,?,?,?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.setString(1, rut);
            stmt.registerOutParameter(2, OracleTypes.VARCHAR);
            stmt.registerOutParameter(3, OracleTypes.VARCHAR);
            stmt.registerOutParameter(4, OracleTypes.VARCHAR);
            stmt.registerOutParameter(5, OracleTypes.DATE);
            stmt.registerOutParameter(6, OracleTypes.VARCHAR);
            stmt.registerOutParameter(7, OracleTypes.VARCHAR);
            stmt.registerOutParameter(8, OracleTypes.VARCHAR);

            stmt.execute();
            if(stmt.getString(2)==null){
            throw new Exception("el rut buscado no existe");
            }
            
            Usuario recuperado = new Usuario();

            recuperado.setRut(rut);
            recuperado.setNombre(stmt.getString(2));
            recuperado.setApellidoPaterno(stmt.getString(3));
            recuperado.setApellidoMaterno(stmt.getString(4));
            recuperado.setFechaNac(stmt.getDate(5));
            recuperado.setCorreo(stmt.getString(6));
            recuperado.setContrasena(stmt.getString(7));
            recuperado.setTipoUsuario(stmt.getString(8));
            
            conexion.getConexion().close();
            return recuperado;
        } catch (Exception e) {
            throw new Exception("No se puede obtener datos: " + e.getMessage());
        }

    }
    
    //VALIDAR INICIO DE SESION
    
    /*
    *   SIN USAR
    *   CONTRASEÑA SIN ENCRIPTAR AUN
    *   
    *   SE DEBE BORRAR LA ENCRIPTACION PARA LA CORRECTA VALIDACION
    */
    public int[] validarSesion(String rut, String contrasena) throws Exception {

        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {
           
            //procedimiento
            String query = "CALL BUSCAR_USUARIO_PORRUT(?,?,?,?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.setString(1, rut);
            stmt.registerOutParameter(2, OracleTypes.LONGNVARCHAR); // rut
            stmt.registerOutParameter(3, OracleTypes.LONGNVARCHAR); // contraseña
            stmt.execute();

            if (stmt.getString(2) == null) {
                throw new Exception("El rut ingresado no existe");
            }
            // USUARIO RECUPERADO DESDE BD
            Usuario recuperado = new Usuario();
            recuperado.setRut(stmt.getString(1));

            recuperado.setContrasena(stmt.getString(4)); // CONTRASENA

            // PWD
            String passwordToHash = contrasena;
            String generatedPassword = null;
            try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(passwordToHash.getBytes());
                //Get the hash's bytes 
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            // GENERATED PASWORD = CONTRASEÑA INGRESADA EN MD5
            int[] aux = new int[2]; // variable de salida 
            if (generatedPassword.equalsIgnoreCase(recuperado.getContrasena())) {

                //return 1 = true y return id del usuario
                aux[0] = 1;
                //       aux[1] = recuperado.getRut();

                return aux;
            } else {

                //return 0 = false y return id del usuario
                aux[0] = 0;
                aux[1] = 0;

                return aux;
            }

        } catch (Exception ex) {
            throw new Exception("ERROR: " + ex.getMessage());
        }

    }
}
