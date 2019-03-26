package edu.tec.jerez.topicos.modelo;

public class Alumno {
    //Atributos
    private String numControl;
    private String nombre;
    private String primerAp;
    private String segundoAp;
    private byte edad;
    private byte semestre;
    private String carrera;

    //Constructores
    public Alumno() {}
    public Alumno(String numControl, String nombre, String primerAp, String segundoAp, byte edad,
                  byte semestre, String carrera) {
        this.numControl = numControl;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.edad = edad;
        this.semestre = semestre;
        this.carrera = carrera;
    }

    //GETTERS
    public String getNumControl(){return numControl;}
    public String getNombre(){return nombre;}
    public String getPrimerAp(){return primerAp;}
    public String getSegundoAp(){return segundoAp;}
    public byte getEdad(){return edad;}
    public byte getSemestre(){return semestre;}
    public String getCarrera(){return carrera;}

    //SETTERS
    public void setNumControl(String numControl){this.numControl = numControl;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setPrimerAp(String primerAp){this.primerAp = primerAp;}
    public void setSegundoAp(String segundoAp){this.segundoAp = segundoAp;}
    public void setEdad(byte edad){this.edad = edad;}
    public void setSemestre(byte semestre){	this.semestre = semestre;}
    public void setCarrera(String carrera){this.carrera = carrera;}

    //toString
    @Override
    public String toString() {
        return "Alumno [numControl=" + numControl + ", nombre=" + nombre + ", primerAp=" + primerAp + ","
                + " segundoAp=" + segundoAp + ", edad=" + edad + ", semestre=" + semestre + ", carrera="
                + carrera + "]";
    }

    //OTROS METODOS
    public boolean registrarInscripcion() {
        return false;
    }
}
