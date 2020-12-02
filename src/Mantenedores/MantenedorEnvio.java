/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Envio;
import Biblioteca.Transportista;
import Biblioteca.Usuario;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorEnvio {
    
    // insert
    public boolean crear(Envio e, Transportista t) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_envio(?,?,?,?)");

            statement.setInt(1, e.getIdEnvio());
            statement.setString(2, e.getTipoEnvio());
            statement.setInt(3, t.getIdTransp());
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();

            MantenedorTransportista transp = new MantenedorTransportista();
            MantenedorUsuario du = new MantenedorUsuario();
            
            Usuario u = du.buscarUsuario(t.getRut());
            transp.crear(t,u);

        } catch (Exception ex) {

            if (ex.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("LA VENTA YA EXISTE");
            } else {
                throw new Exception("ERROR: " + ex.getMessage());
            }
        }
        return false;
    }
    
    //READ
    public ArrayList<Envio> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {
            String query = "call listar_usuario(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Envio> LEnvio = new ArrayList();

            while (rs.next()) {
                Envio e = new Envio();
                MantenedorTransportista tr = new MantenedorTransportista();

                e.setIdEnvio(rs.getInt(1));
                e.setTipoEnvio(rs.getString(2));
                e.setIdTransp(rs.getInt(3));
                tr.listarTodo();

                LEnvio.add(e);
            }
            return LEnvio;
        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    // UPDATE
    public boolean modificar(Envio e, Transportista t) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorTransportista trans = new MantenedorTransportista();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_envio(?, ?,?)");

            statement.setInt(1, e.getIdEnvio());
            statement.setString(2, e.getTipoEnvio());

            statement.execute();
            statement.close();

            MantenedorUsuario mantUsr = new MantenedorUsuario();
            
            Usuario u = mantUsr.buscarUsuario(t.getRut());
            trans.modificar(t,u);
        } catch (Exception ex) {
            throw new Exception("No se puede editar el envio: " + ex.getMessage());
        }
        return false;
    }
    
    // delete
    public boolean eliminar(Envio e, Transportista t) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call delete_envio(?)");

            statement.setInt(1, e.getIdEnvio());

            statement.execute();
            statement.close();

            MantenedorTransportista trans = new MantenedorTransportista();
            MantenedorUsuario du = new MantenedorUsuario();
            
            Usuario u = du.buscarUsuario(t.getRut());
            trans.eliminar(t,u);

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el envio: " + ex.getMessage());
        }
        return false;

    }
}
