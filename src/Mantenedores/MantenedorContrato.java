/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Contrato;
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
public class MantenedorContrato {
    
    // create
    public boolean crear(Contrato c,Productor p) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call insert_contrato(?,?)");

            statement.setInt(1, c.getIdContrato());
            statement.setString(2, c.getDescripcion());
            statement.setInt(3, p.getIdProductor());
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

           
            statement.execute();
            statement.close();
            
            MantenedorProductor pr = new MantenedorProductor();
            MantenedorUsuario du = new MantenedorUsuario();
            
            Usuario u = du.buscarUsuario(p.getRut());
            pr.crear(u, p);

        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL CONTRATO YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;
    }
    
    // listar
    public ArrayList<Contrato> listarTodo() throws Exception{
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_contrato(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Contrato> cont = new ArrayList();

            while (rs.next()) {
                MantenedorProductor pro = new MantenedorProductor();
                Contrato c = new Contrato();
                Productor pr = new Productor();
              
                
                c.setIdContrato(rs.getInt(1));
                c.setDescripcion(rs.getString(2));
                pr.setIdProductor(rs.getInt(3));
                pro.listarTodo();

                cont.add(c);

            }

            return cont;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    // update
    public boolean modificar(Contrato c, Productor p) throws Exception{
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call update_contrato(?, ?)}");

            statement.setInt(1, c.getIdContrato());
            statement.setString(2, c.getDescripcion());
            
            
            MantenedorProductor pr = new MantenedorProductor();
            MantenedorUsuario du = new MantenedorUsuario();
            
            Usuario u = du.buscarUsuario(p.getRut());
            pr.modificar(p,u);

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el contrato: " + ex.getMessage());
        }

        return false;
    }
    
    // delete
    public boolean eliminar(Contrato c, Productor p) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call delete_contrato(?)");

            statement.setInt(1, c.getIdContrato());

            statement.execute();
            statement.close();

            MantenedorProductor pr = new MantenedorProductor();
            MantenedorUsuario du = new MantenedorUsuario();
            
            Usuario u = du.buscarUsuario(p.getRut());
            pr.eliminar(p,u);

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el contrato: " + ex.getMessage());

        }
        return false;
    }
}
