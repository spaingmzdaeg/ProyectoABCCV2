package edu.tec.jerez.topicos.conexionBD;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class conexionBD {
    private Connection conexion;
    private Statement stm;
    private ResultSet rs;

    public conexionBD(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/bd_escuela";
            conexion = DriverManager.getConnection(url, "root", "chesterf51997");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR EN EL DRIVER");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR EN LA RUTA o en CONEXION");
        }
    }



    public ResultSet consultarRegistros(String sql) {
        try {
            stm = conexion.createStatement();
            rs = stm.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        */
        return rs;
    }

    public boolean ejecutarInstruccionSQL(String sql) {

        boolean ejecucion = true;
        try {
            stm = (Statement) conexion.createStatement();
            stm.execute(sql);
        }
        catch(SQLException e) {
            e.printStackTrace();
            ejecucion = false;
        }finally{
            try {
                conexion.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return  ejecucion;
    }

    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
