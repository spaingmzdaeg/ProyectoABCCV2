package edu.tec.jerez.topicos.controlador;

import edu.tec.jerez.topicos.conexionBD.conexionBD;
import edu.tec.jerez.topicos.modelo.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
//instancias
conexionBD conexion = new conexionBD();
final String conexionTabla = "alumnos";
//Altas
    public boolean insertarRegistros(Alumno alumno) {
        boolean resultado = false;

        String instruccionSQL = "INSERT INTO alumnos VALUES('"+ alumno.getNumControl()
                +"', '"+alumno.getNombre()
                +"', '"+alumno.getPrimerAp()
                +"', '"+alumno.getSegundoAp()
                +"', '"+alumno.getEdad()
                +"', '"+alumno.getSemestre()
                +"', '"+alumno.getCarrera()
                +"')";

        resultado = conexion.ejecutarInstruccionSQL(instruccionSQL);

        return resultado;
    }

//Bajas
public boolean eliminarRegistro(String numControl){

    String sql = "DELETE FROM "+conexionTabla+" WHERE NumControl= '"+numControl+"'";

    return conexion.ejecutarInstruccionSQL(sql);
}

    public Alumno buscarAlumno(String numControl){
        Alumno alumno = null;
        /*
        Sintaxis de BUSQUEDA de registros en MySQL

        SELECT * FROM alumnos WHERE NumControl = '01';
        */

        String instruccionSQL = "SELECT * FROM alumnos WHERE NumControl= '"+numControl+"'";
        ResultSet rs = conexion.consultarRegistros(instruccionSQL);

        try{
            if(rs.first()){
                alumno = new Alumno(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getByte(5),
                        rs.getByte(6),
                        rs.getString(7));
            }
            conexion.cerrarConexion();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return  alumno;
    }


}
