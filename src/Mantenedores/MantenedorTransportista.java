/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenedores;

import Biblioteca.Transportista;
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
public class MantenedorTransportista {

    // crear
    public boolean crear(Transportista t, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {

            CallableStatement statement = conexion.getConexion().prepareCall("call insert_trans(?,?,?)");

            statement.setInt(1, t.getIdTransp());
            statement.setString(2, u.getRut());
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute();
            statement.close();

            MantenedorUsuario user = new MantenedorUsuario();
            user.crear(u);

        } catch (Exception e) {

            if (e.getMessage().substring(0, 9).equalsIgnoreCase("ORA-00001")) {
                throw new Exception("EL TRANSPORTISTA YA EXISTE");
            } else {
                throw new Exception("ERROR: " + e.getMessage());
            }
        }
        return false;
    }

    //listaer
    public ArrayList<Transportista> listarTodo() throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            String query = "call listar_trans(?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            ArrayList<Transportista> LTr = new ArrayList();

            while (rs.next()) {
                Transportista trans = new Transportista();
                MantenedorUsuario user = new MantenedorUsuario();

                trans.setIdTransp(rs.getInt(1));
                trans.setRut(rs.getString(3));
                user.listarTodo();

                LTr.add(trans);

            }

            return LTr;

        } catch (Exception e) {
            throw new Exception("No se puede listar : " + e.getMessage());

        }
    }

    //editar
    public boolean modificar(Transportista t, Usuario u) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();
        try {
            MantenedorUsuario DAOUser = new MantenedorUsuario();
            CallableStatement statement = conexion.getConexion().prepareCall("call update_cliente(?, ?)");
            statement.setInt(1, t.getIdTransp());
            statement.setString(2, u.getRut());

            DAOUser.modificar(u);

            statement.execute();
            statement.close();

        } catch (Exception ex) {
            throw new Exception("No se puede editar el transportista: " + ex.getMessage());
        }

        return false;
    }

    //eliminar
    public boolean eliminar(Transportista t, Usuario u) throws Exception {

        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {

            CallableStatement stmt = conexion.getConexion().prepareCall("call delete_trans(?)");
            stmt.setInt(1, t.getIdTransp());

            stmt.execute();
            stmt.close();

            MantenedorUsuario user = new MantenedorUsuario();

            user.eliminar(u);

        } catch (Exception e) {
            throw new Exception("no se puede eliminar el transportista: " + e.getMessage());
        }

        return false;
    }

    //buscar por id 
    public Transportista buscarTransp(int id) throws Exception {
        ConexionBD conexion = new ConexionBD();
        conexion.Conectar();

        try {
            String query = "call buscar_transp(?,?,?,?,?,?,?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(query);

            stmt.setInt(1, id);
            stmt.registerOutParameter(2, OracleTypes.VARCHAR);
            stmt.registerOutParameter(3, OracleTypes.VARCHAR);
            stmt.registerOutParameter(4, OracleTypes.VARCHAR);
            stmt.registerOutParameter(5, OracleTypes.DATE);
            stmt.registerOutParameter(6, OracleTypes.VARCHAR);
            stmt.registerOutParameter(7, OracleTypes.VARCHAR);

            stmt.execute();
            if (stmt.getString(2) == null) {
                throw new Exception("el id buscada no existe");
            }

            Transportista recuperado = new Transportista();
            Usuario recuperado2 = new Usuario();

            recuperado.setIdTransp(id);
            recuperado2.setRut(stmt.getString(2));
            recuperado2.setNombre(stmt.getString(3));
            recuperado2.setApellidoPaterno(stmt.getString(4));
            recuperado2.setFechaNac(stmt.getDate(5));
            recuperado2.setCorreo(stmt.getString(6));
            recuperado2.setTipoUsuario(stmt.getString(7));

            conexion.getConexion().close();
            return recuperado;
        } catch (Exception e) {
            throw new Exception("No se puede obtener datos: " + e.getMessage());
        }

    }
}
