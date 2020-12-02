/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Cliente;
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
public class MantenedorCliente {
    
    //insertar
    public boolean crear(Cliente c, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario user = new MantenedorUsuario();
            user.crear(u);
            
            CallableStatement statement = conexion.getConexion().prepareCall("call insert_cliente(?,?,?)");

            statement.setString(1, c.getTipoCliente());
            statement.setString(2, u.getRut());
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();

        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL Cliente YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;
    }
    
    //listar
    public ArrayList<Cliente> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_cliente(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Cliente> LCli = new ArrayList();

            while (rs.next()) {
                Cliente cli = new Cliente();
                MantenedorUsuario user = new MantenedorUsuario();

                cli.setIdCliente(rs.getInt(1));
                cli.setTipoCliente(rs.getString(2));
                cli.setRut(rs.getString(3));
                user.listarTodo();

                LCli.add(cli);

            }

            return LCli;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    // update
    public boolean modificar(Cliente c, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario DAOUser = new MantenedorUsuario();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_cliente(?, ?)");
            statement.setInt(1, c.getIdCliente());
            statement.setString(2, c.getTipoCliente());

            DAOUser.modificar(u);

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el cliente: " + ex.getMessage());
        }

        return false;
    }
    
    // delete
    public boolean eliminar(Cliente c, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call delete_cliente(?)");

            statement.setInt(1, c.getIdCliente());

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
