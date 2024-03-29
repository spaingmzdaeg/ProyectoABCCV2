package edu.tec.jerez.topicos.controlador;
import edu.tec.jerez.topicos.conexionBD.*;
import edu.tec.jerez.topicos.modelo.Alumno;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AlumnoDAO {
    conexionBD conexion = new conexionBD();
    final String conexionTabla = "alumnos";

    /*
    METODOS DE ABCC (ALTAS, BAJAS, CAMBIOS y CONSULTAS
    CRUD (CREATE, READ, UPDATE and DELETE
    */

    public boolean insertarRegistros(Alumno alumno) {
        boolean resultado;

        String instruccionSQL = "INSERT INTO alumnos VALUES('"+ alumno.getNumControl()
                +"', '"+alumno.getNombre()
                +"', '"+alumno.getPrimerAp()
                +"', '"+alumno.getSegundoAp()
                +"', '"+alumno.getEdad()
                +"', '"+alumno.getSemestre()
                +"', '"+alumno.getCarrera()
                +"')";

        resultado = conexion.ejecutarInstruccionSQL(instruccionSQL);
        conexion.cerrarConexion();

        return resultado;
    }

    public boolean eliminarRegistro(String numControl){
        boolean resultado;

        String sql = "DELETE FROM "+conexionTabla+" WHERE NumControl= '"+numControl+"'";

        resultado = conexion.ejecutarInstruccionSQL(sql);
        conexion.cerrarConexion();
        return resultado;
    }

    public boolean actualizarRegistro(Alumno alumno){

        boolean resultado;

        //UPDATE alumnos_SET Nombre = ''....
        String sql = "UPDATE "+conexionTabla+" SET Nombre = '" +alumno.getNombre()
                +"', primerAp = '"+alumno.getPrimerAp()
                +"', segundoAp = '" +alumno.getSegundoAp()
                +"', Edad = "+alumno.getEdad()
                +", Semestre = "+alumno.getSemestre()
                +", Carrera = '"+alumno.getCarrera()
                +"' WHERE NumControl = '"+alumno.getNumControl()+"'";

        resultado = conexion.ejecutarInstruccionSQL(sql);
        conexion.cerrarConexion();

        return resultado;
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

    public boolean consultasPorNombre(String nombre,JTable tabla){
        boolean completo = false;

        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos WHERE Nombre= '"+nombre+"'");
        // ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos ");
        modelo.setColumnIdentifiers( new Object[] {
                "num. Control", "nombre", "primerAp", "segundoAp","edad", "Semestre", "Carrera"});

        try{
            while(rs.next()){
                modelo.addRow(new Object[]{rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getByte(5),
                        rs.getByte(6),
                        rs.getString(7)});
                completo = true;
            }
            tabla.setModel(modelo);
            conexion.cerrarConexion();
        }catch (Exception e){
            System.out.println(e.getMessage());
            completo = false;
        }
        return  completo;
    }

    public boolean consultasPorApaterno(String ap,JTable tabla){
        boolean completo = false;

        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos WHERE primerAp= '"+ap+"'");
        // ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos ");
        modelo.setColumnIdentifiers( new Object[] {
                "num. Control", "nombre", "primerAp", "segundoAp","edad", "Semestre", "Carrera"});


        try{
            while(rs.next()){
                modelo.addRow(new Object[]{rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getByte(5),
                        rs.getByte(6),
                        rs.getString(7)});
                completo = true;
            }
            tabla.setModel(modelo);
            conexion.cerrarConexion();
        }catch (Exception e){
            System.out.println(e.getMessage());
            completo = false;
        }
        return  completo;
    }

    public boolean consultasPorAmaterno(String am,JTable tabla){
        boolean completo = false;

        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos WHERE segundoAp= '"+am+"'");
        // ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos ");
        modelo.setColumnIdentifiers( new Object[] {
                "num. Control", "nombre", "primerAp", "segundoAp","edad", "Semestre", "Carrera"});


        try{
            while(rs.next()){
                modelo.addRow(new Object[]{rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getByte(5),
                        rs.getByte(6),
                        rs.getString(7)});
                completo = true;
            }
            tabla.setModel(modelo);
            conexion.cerrarConexion();
        }catch (Exception e){
            System.out.println(e.getMessage());
            completo = true;
        }
        return  completo;
    }

    public boolean consultasPorSemestre(String semestre,JTable tabla){
        boolean completo = false;

        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos WHERE semestre= '"+semestre+"'");
        // ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos ");
        modelo.setColumnIdentifiers( new Object[] {
                "num. Control", "nombre", "primerAp", "segundoAp","edad", "Semestre", "Carrera"});


        try{
            while(rs.next()){
                modelo.addRow(new Object[]{rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getByte(5),
                        rs.getByte(6),
                        rs.getString(7)});
            }
            tabla.setModel(modelo);
            conexion.cerrarConexion();
        }catch (Exception e){
            System.out.println(e.getMessage());
            completo = true;
        }
        return  completo;
    }

    public boolean consultasPorCarrera(String carrera,JTable tabla){
        boolean completo = false;

        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos WHERE carrera= '"+carrera+"'");
        // ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos ");
        modelo.setColumnIdentifiers( new Object[] {
                "num. Control", "nombre", "primerAp", "segundoAp","edad", "Semestre", "Carrera"});


        try{
            while(rs.next()){
                modelo.addRow(new Object[]{rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getByte(5),
                        rs.getByte(6),
                        rs.getString(7)});
                completo = true;
            }
            tabla.setModel(modelo);
            conexion.cerrarConexion();
        }catch (Exception e){
            System.out.println(e.getMessage());
            completo = false;
        }
        return  completo;
    }

    public boolean consultasPorEdad(String edad,JTable tabla){
        boolean completo = false;

        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos WHERE edad= '"+edad+"'");
        // ResultSet rs = conexion.consultarRegistros("SELECT * FROM alumnos ");
        modelo.setColumnIdentifiers( new Object[] {
                "num. Control", "nombre", "primerAp", "segundoAp","edad", "Semestre", "Carrera"});


        try{
            while(rs.next()){
                modelo.addRow(new Object[]{rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getByte(5),
                        rs.getByte(6),
                        rs.getString(7)});
                completo = true;
            }
            tabla.setModel(modelo);
            conexion.cerrarConexion();
        }catch (Exception e){
            System.out.println(e.getMessage());
            completo = false;
        }
        return  completo;
    }

    //metodos consultas

    public Alumno mostrarAlumno(String numControl){//no puesto en interfaz
        Alumno alumno = null;
        String sql = "SELECT * FROM alumnos WHERE carrera= '"+numControl+"'";
        ResultSet rs = conexion.consultarRegistros(sql);

        try {
            rs.last();
            alumno.setNumControl(rs.getString(1));
            alumno.setNombre(rs.getString(2));
            alumno.setPrimerAp(rs.getString(3));
            alumno.setSegundoAp(rs.getString(3));
            alumno.setEdad(rs.getByte(5));
            alumno.setSemestre(rs.getByte(6));
            alumno.setCarrera(rs.getString(7));
        }catch (SQLException e){
            e.printStackTrace();
        }


        return alumno;
    }

    public ArrayList<Alumno> mostrarAlumnos(String numControl){//no puesto en interfaz Luego implementacip
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        Alumno alumno = null;
        String sql = "SELECT * FROM alumnos WHERE numControl= '"+numControl+"'";
        ResultSet rs = conexion.consultarRegistros(sql);

        try{
            while(rs.next()){
                alumno.setNumControl(rs.getString(1));
                alumno.setNombre(rs.getString(2));
                alumno.setPrimerAp(rs.getString(3));
                alumno.setSegundoAp(rs.getString(3));
                alumno.setEdad(rs.getByte(5));
                alumno.setSemestre(rs.getByte(6));
                alumno.setCarrera(rs.getString(7));

                listaAlumnos.add(alumno);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());

        }




        return listaAlumnos;
    }

}



