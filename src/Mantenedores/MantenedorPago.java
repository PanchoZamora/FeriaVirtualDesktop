/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Boleta;
import Biblioteca.Pago;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorPago {
    
    // insert
    public boolean crear(Pago p, Boleta b) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_pago(?, ?,?,?)");

            statement.setInt(1, p.getIdPago());
            statement.setString(2, p.getTipoPago());
            statement.setInt(3, b.getIdBoleta());
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            
            statement.execute();
            statement.close();
            
            MantenedorBoleta bol = new MantenedorBoleta();
            bol.crear(b);
            
        } catch (Exception ex) {
            if (ex.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL Cliente YA EXISTE");
            } else {
                throw new Exception("ERROR: " + ex.getMessage());
            }
        }
        return false;
    }
    
    //read
    public ArrayList<Pago> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_pago(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Pago> LPago = new ArrayList();

            while (rs.next()) {
                Pago pago = new Pago();
                MantenedorBoleta bol = new MantenedorBoleta();

                pago.setIdPago(rs.getInt(1));
                pago.setTipoPago(rs.getString(2));
                pago.setIdBoleta(rs.getInt(3));
                bol.listarTodo();

                LPago.add(pago);

            }

            return LPago;
        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    // update
    public boolean modificar(Pago p, Boleta b) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call update_pago(?, ?,?)");

            statement.setInt(1, p.getIdPago());
            statement.setString(2, p.getTipoPago());
            statement.setInt(3, p.getIdBoleta());

            MantenedorBoleta bol = new MantenedorBoleta();
            bol.modificar(b);
            
            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el pago: " + ex.getMessage());
        }
        return false;

    }
    
    //delete
    public boolean eliminar(Pago p, Boleta b) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call delete_pago(?)");

            statement.setInt(1, p.getIdPago());

            statement.execute();
            statement.close();
            
            MantenedorBoleta bol = new MantenedorBoleta();
            bol.eliminar(b);

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el pago: " + ex.getMessage());
        }
        return false;

    }
}
