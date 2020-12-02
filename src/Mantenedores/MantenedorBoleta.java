/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Boleta;
import Biblioteca.Producto;
import Biblioteca.Venta;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorBoleta {
    
    //insert
    public boolean crear(Boleta o) throws Exception {
        try {
            ConexionBD conexion = new ConexionBD();
            conexion.Conectar();

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_boleta(?, ?)");

            statement.setInt(1, o.getIdBoleta());
            statement.setString(2, o.getDetalleVenta());

            statement.execute();

            statement.close();
        } catch (SQLException ex) {

            if (ex.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("LA BOLETA YA EXISTE");
            } else {
                throw new Exception("ERROR: " + ex.getMessage());
            }
        }
        return false;

    }
    
    //listar
    public ArrayList<Boleta> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            String query = "call listar_boleta(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.NUMBER);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);
            ArrayList<Boleta> listaBoleta = new ArrayList();

            while (rs.next()) {
                Boleta recuperado = new Boleta();

                recuperado.setIdBoleta(rs.getInt(1));
                recuperado.setDetalleVenta(rs.getString(2));

                listaBoleta.add(recuperado);
            }
            conexion.getConexion().close();
            return listaBoleta;
        } catch (Exception ex) {
            throw new Exception("No se puede listar: " + ex.getMessage());
        }
    }
    
    // update
    public boolean modificar(Boleta o) throws Exception {
        try {
            ConexionBD conexion = new ConexionBD();
            conexion.Conectar();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_boleta(?, ?)");

            statement.setInt(1, o.getIdBoleta());
            statement.setString(2, o.getDetalleVenta());

            statement.execute();
            statement.close();

            if (statement.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception ex) {
            throw new Exception("No se puede editar la boleta: " + ex.getMessage());
        }

        return false;

    }
    
    // eliminar
    public boolean eliminar(Boleta o) throws Exception {
        try {
            ConexionBD conexion = new ConexionBD();
            conexion.Conectar();
            CallableStatement statement = conexion.getConexion().prepareCall("call delete_boleta(?)");

            statement.setInt(1, o.getIdBoleta());
            statement.setString(2, o.getDetalleVenta());

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar la boleta: " + ex.getMessage());

        }
        return false;

    }
    
    // obtener boleta segun ID
     public Boleta buscarBoleta(Integer idBoleta) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {
            String query = "call buscar_boleta(?,?,?,?,?,?,?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.setInt(1, idBoleta);
            stmt.registerOutParameter(2, OracleTypes.VARCHAR);
            stmt.registerOutParameter(3, OracleTypes.NUMBER);
            stmt.registerOutParameter(4, OracleTypes.NUMBER);
            stmt.registerOutParameter(5, OracleTypes.VARCHAR);
            stmt.registerOutParameter(6, OracleTypes.VARCHAR);

            stmt.execute();
            if (stmt.getString(2) == null) {
                throw new Exception("la boleta no existe");
            }

            Boleta recuperado = new Boleta();
            Venta recuperado2 = new Venta();
            Producto recuperado3 = new Producto();

            recuperado.setIdBoleta(idBoleta);
            recuperado3.setNombre(stmt.getString(2));
            recuperado.setDetalleVenta(stmt.getString(3));
            recuperado3.setPrecio(stmt.getInt(3));
            recuperado3.setCantidad(stmt.getInt(5));
            recuperado3.setDescripcion(stmt.getString(6));
            recuperado2.setTipoVenta(stmt.getString(7));

            conexion.getConexion().close();
            return recuperado;
        } catch (Exception e) {
            throw new Exception("No se puede obtener datos: " + e.getMessage());
        }

    }
}
