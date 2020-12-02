/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Envio;
import Biblioteca.Subasta;
import Biblioteca.Transportista;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorSubasta {
    // insert
    public boolean crear(Subasta s, Envio e) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_subasta(?,?,?,?,?)");

            statement.setInt(1, s.getIdSubasta());
            statement.setInt(2, s.getPrecio());
            statement.setString(3, s.getDescTransporte());
            statement.setInt(4, e.getIdEnvio());
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();

            MantenedorEnvio en = new MantenedorEnvio();
            MantenedorTransportista daot = new MantenedorTransportista();
            
            Transportista t = new Transportista();
            daot.buscarTransp(t.getIdTransp());
            en.crear(e,t);

        } catch (Exception ex) {

            if (ex.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("LA SUBASTA YA EXISTE");
            } else {
                throw new Exception("ERROR: " + ex.getMessage());
            }
        }
        return false;
    }
    
    //listar
    public ArrayList<Subasta> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_cliente(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Subasta> LSub = new ArrayList();

            while (rs.next()) {
                Subasta su = new Subasta();
                MantenedorEnvio en = new MantenedorEnvio();

                su.setIdSubasta(rs.getInt(1));
                su.setPrecio(rs.getInt(2));
                su.setDescTransporte(rs.getString(3));
                en.listarTodo();

                LSub.add(su);

            }

            return LSub;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }

    }
    
    //editar
    public boolean modificar(Subasta s, Envio e) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorEnvio en = new MantenedorEnvio();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_cliente(?, ?)");
            statement.setInt(1, s.getIdEnvio());
            statement.setInt(2, s.getPrecio());
            statement.setString(3, s.getDescTransporte());
            statement.setInt(4, e.getIdEnvio());
            
            MantenedorTransportista daot = new MantenedorTransportista();
            
            Transportista t = new Transportista();
            daot.buscarTransp(t.getIdTransp());
            
            en.modificar(e,t);
            
            statement.execute();
            statement.close();
            

        } catch (Exception ex) {
            throw new Exception("No se puede editar el Subasta: " + ex.getMessage());
        }

        return false;
    }
    
    // eliminar
    public boolean eliminar(Subasta s, Envio e) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call delete_cliente(?)");

            statement.setInt(1, s.getIdEnvio());

            statement.execute();
            statement.close();

            MantenedorEnvio en = new MantenedorEnvio();
            MantenedorTransportista daot = new MantenedorTransportista();
            
            Transportista t = new Transportista();
            daot.buscarTransp(t.getIdTransp());

            en.eliminar(e,t);

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar la Subasta: " + ex.getMessage());

        }
        return false;
    }
    
}
