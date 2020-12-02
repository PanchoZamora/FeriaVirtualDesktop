/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Consultor;
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
public class MantenedorConsultor {
    
    //insert
    public boolean crear(Consultor c, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_consultor(?,?)");

            statement.setInt(1, c.getIdConsultor());
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();

            MantenedorUsuario user = new MantenedorUsuario();
            user.crear(u);
            
            
        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL USUARIO YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;
    }
    
    // read
    public ArrayList<Consultor> listarTodo() throws Exception{
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_cliente(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Consultor> LCon = new ArrayList();

            while (rs.next()) {
                Consultor con = new Consultor();
                MantenedorUsuario user = new MantenedorUsuario();

                con.setIdConsultor(rs.getInt(1));
                con.setRut(rs.getString(3));
                user.listarTodo();

                LCon.add(con);

            }

            return LCon;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    //update
    
    public boolean modificar(Consultor c, Usuario u) throws Exception{
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario DAOUser = new MantenedorUsuario();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_cliente(?, ?)");
            statement.setInt(1, c.getIdConsultor());
            
            DAOUser.modificar(u);

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el cliente: " + ex.getMessage());
        }

        return false;
    }
    
    // delete 
    public boolean eliminar(Consultor c, Usuario u)throws Exception {
         ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call delete_cliente(?)");

            statement.setInt(1, c.getIdConsultor());

            statement.execute();
            statement.close();

            MantenedorUsuario user = new MantenedorUsuario();

            user.eliminar(u);

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el cliente: " + ex.getMessage());

        }
        return false;
    }
}
