/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Producto;
import Biblioteca.Productor;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author tamar
 */
public class MantenedorProducto {
    
    // crear 
    public boolean crear(Producto pr, Productor p) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_producto(?,?,?,?,?,?)");

            statement.setString(1, pr.getNombre());
            statement.setInt(2, pr.getPrecio());
            statement.setInt(3, pr.getCantidad());
            statement.setString(4, pr.getDescripcion());
            statement.setInt(5, p.getIdProductor());
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();
            
        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL PRODUCTO YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;

    }
    
    // listar
    public ArrayList<Producto> listarTodo()throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_pdcto(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Producto> pr = new ArrayList();

            while (rs.next()) {
                Producto pro = new Producto();
                
                pro.setIdProducto(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setPrecio(rs.getInt(3));
                pro.setCantidad(rs.getInt(4));
                pro.setDescripcion(rs.getString(5));
                pro.setIdProductor(rs.getInt(6));
                
                pr.add(pro);

            }

            return pr;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }
    
    //update
    public boolean modificar(Producto pr, Productor p) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
 
            CallableStatement statement = conexion.getConexion().prepareCall("call update_producto(?)");
            statement.setInt(1, pr.getIdProducto());

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el producto: " + ex.getMessage());
        }

        return false;
    }
    
    // delete
    public boolean eliminar(Producto pr) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            CallableStatement statement = conexion.getConexion().prepareCall("call delete_producto(?)");

            statement.setInt(1, pr.getIdProducto());

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("no se puede eliminar el producto: " + ex.getMessage());

        }
        return false;
    }
}
