/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Venta;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorVenta {
    
    // create
    public boolean crear(Venta o) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call insert_venta(?,?)");

            statement.setInt(1, o.getIdVenta());
            statement.setString(2, o.getTipoVenta());

            statement.execute();
            statement.close();

        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("LA VENTA YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;

    }
    
    // read 
    public ArrayList<Venta> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_cliente(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Venta> LVenta = new ArrayList();

            while (rs.next()) {
                Venta v = new Venta();

                v.setIdVenta(rs.getInt(1));
                v.setTipoVenta(rs.getString(2));

                LVenta.add(v);
            }

            return LVenta;
        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    //update
    public boolean modificar(Venta o) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call update_venta(?,?)");

            statement.setString(1, o.getTipoVenta());
            statement.setInt(2, o.getIdVenta());

            statement.execute();
            statement.close();

            if (statement.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception ex) {
            throw new Exception("No se puede editar la venta: " + ex.getMessage());
        }

        return false;

    }
    
    //delete
    public boolean eliminar(Venta o) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call delete_venta(?)");

            statement.setInt(1, o.getIdVenta());

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar la venta: " + ex.getMessage());

        }
        return false;

    }
}
