/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public ConexionBD Conectar() throws Exception {
        //credenciales
        //Maquina( PC CASA ): 201.239.34.154

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@201.239.34.154:1521:orcl";
            conexion = DriverManager.getConnection(BaseDeDatos, "FeriaVirtual2", "duoc");
            if (conexion != null) {

                StringBuilder input1 = new StringBuilder();
                StringBuilder output = new StringBuilder();

                // append a string into StringBuilder input1 
                input1.append(BaseDeDatos);

                // reverse StringBuilder input1 
                input1 = input1.reverse();

                output.append(input1.substring(0, 4));

                output.reverse();

                System.out.println("Conexion exitosa a BD: " + output);
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            throw new Exception("ERROR: Hubo un error al conectarse con la base de datos :\n" + e.getLocalizedMessage());
        }

        return this;
    }
    
    public static void main(String[] args) {
        ConexionBD conexion = new ConexionBD();
        try {
            conexion.Conectar();
        } catch (Exception ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
