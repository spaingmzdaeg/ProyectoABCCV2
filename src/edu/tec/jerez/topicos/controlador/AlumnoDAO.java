package edu.tec.jerez.topicos.controlador;

import edu.tec.jerez.topicos.conexionBD.conexionBD;
import edu.tec.jerez.topicos.modelo.Alumno;

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


}
