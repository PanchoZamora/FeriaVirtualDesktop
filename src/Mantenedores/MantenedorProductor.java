/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Productor;
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
public class MantenedorProductor {
    
    // crear
    public boolean crear(Usuario u,Productor p ) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario user = new MantenedorUsuario();
            user.crear(u);
            
            CallableStatement statement = conexion.getConexion().prepareCall("call insert_pdctor(?,?,?)");

            statement.setInt(1, p.getIdProductor());
            statement.setString(2, u.getRut());
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();

            

        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL PRODUCTOR YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;

    }
    
    //listar
    public ArrayList<Productor> listarTodo()throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_pdctor(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Productor> pr = new ArrayList();

            while (rs.next()) {
                Productor pro = new Productor();
                MantenedorUsuario user = new MantenedorUsuario();

                pro.setIdProductor(rs.getInt(1));
                pro.setRut(rs.getString(2));
                user.listarTodo();

                pr.add(pro);

            }

            return pr;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    // editar
    public boolean modificar(Productor p, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario mantUsr = new MantenedorUsuario();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_pdctor(?, ?)");
            statement.setInt(1, p.getIdProductor());
            
            mantUsr.modificar(u);

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el productor: " + ex.getMessage());
        }

        return false;
    }
    
    // eliminar
    
    public boolean eliminar(Productor p, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call delete_pdctor(?)");

            statement.setInt(1, p.getIdProductor());

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
