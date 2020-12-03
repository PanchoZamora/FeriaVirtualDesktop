/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Administrador;
import Biblioteca.Usuario;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorAdministrador {
    
    //insertar
    public boolean crear(Administrador a, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario user = new MantenedorUsuario();
            user.crear(u);
            
            CallableStatement statement = conexion.getConexion().prepareCall("call insert_admin(?,?)");

            statement.setString(1, a.getRut());
            statement.registerOutParameter(2, OracleTypes.INTEGER);

            statement.execute();
            statement.close();

        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL Administrador YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;
    }
    
    //listar
    public ArrayList<Administrador> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_admin(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Administrador> LCli = new ArrayList();

            while (rs.next()) {
                Administrador adm = new Administrador();
                MantenedorUsuario user = new MantenedorUsuario();

                adm.setIdAdmin(rs.getInt(1));
                adm.setRut(rs.getString(2));
                user.listarTodo();

                LCli.add(adm);

            }

            return LCli;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    // update
    public boolean modificar(Administrador a, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario mnUsr = new MantenedorUsuario();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_admin(?, ?)");
            statement.setInt(1, a.getIdAdmin());
            statement.setString(2, a.getRut());

            mnUsr.modificar(u);

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el administrador: " + ex.getMessage());
        }

        return false;
    }
    
    // delete
    public boolean eliminar(Administrador a, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call delete_admin(?)");

            statement.setInt(1, a.getIdAdmin());

            statement.execute();
            statement.close();

            MantenedorUsuario user = new MantenedorUsuario();

            user.eliminar(u.getRut());

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el administrador: " + ex.getMessage());

        }
        return false;
    }
    
}
