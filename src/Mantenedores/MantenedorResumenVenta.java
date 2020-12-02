/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.ResumenVenta;
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
public class MantenedorResumenVenta {
    public boolean crear(ResumenVenta rv, Venta v) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call insert_resumen(?, ?,?,?,?,?,?)");

            statement.setInt(1, rv.getIdResumen());
            statement.setInt(2, rv.getCostoTransporte());
            statement.setInt(3, rv.getImpuestos());
            statement.setInt(4, rv.getComisionEmpresa());
            statement.setInt(5, rv.getPagoServicio());
            statement.setInt(6, v.getIdVenta());
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();

            MantenedorVenta daov = new MantenedorVenta();
            daov.crear(v);

        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL RESUMEN DE LA VENTA YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;

    }
    
    // read
    
    public ArrayList<ResumenVenta> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_cliente(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<ResumenVenta> LRv = new ArrayList();

            while (rs.next()) {
                ResumenVenta resven = new ResumenVenta();
                MantenedorVenta ven = new MantenedorVenta();

                resven.setIdResumen(rs.getInt(1));
                resven.setCostoTransporte(rs.getInt(2));
                resven.setImpuestos(rs.getInt(3));
                resven.setPagoServicio(rs.getInt(4));
                resven.setComisionEmpresa(rs.getInt(5));
                ven.listarTodo();

                LRv.add(resven);

            }

            return LRv;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    //update
    public boolean modificar(ResumenVenta rv, Venta v) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_resumen(?, ?,?,?,?)");

            statement.setInt(1, rv.getIdResumen());
            statement.setInt(2, rv.getCostoTransporte());
            statement.setInt(3, rv.getImpuestos());
            statement.setInt(4, rv.getComisionEmpresa());
            statement.setInt(5, rv.getPagoServicio());

            statement.execute();
            statement.close();

            MantenedorVenta daov = new MantenedorVenta();
            daov.modificar(v);

        } catch (Exception ex) {
            throw new Exception("No se puede editar el resumen de la venta " + ex.getMessage());
        }

        return false;
    }
    
    //eliminar
    public boolean eliminar(ResumenVenta rv, Venta v) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call delete_resumen(?)");

            statement.setInt(1, rv.getIdResumen());

            statement.execute();
            statement.close();

            MantenedorVenta daov = new MantenedorVenta();
            daov.eliminar(v);

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el resumen de la venta: " + ex.getMessage());

        }
        return false;
    }
}
