package edu.tec.jerez.topicos.vista;

import edu.tec.jerez.topicos.controlador.AlumnoDAO;
import edu.tec.jerez.topicos.modelo.Alumno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Ventana extends JFrame {//Ventana principal
    //--------instancias
    JTable tablaAlumnosAltas;
    JInternalFrame internalFrameAltas;
    //--------instancias

    private static final long serialVersionUID = 1L;

    public Ventana() {//Constructor

        getContentPane().setLayout(new BorderLayout());
        setSize(1000, 1000);
        setTitle("Ventana Principal");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        //----------codigo para JMenu
        //---------Menu Altas
        JMenuBar menu = new JMenuBar();
        JMenu mAltas = new JMenu("Altas");
        JMenuItem itemAltas = new JMenuItem("Dar de Alta un Alumno..");
        itemAltas.setMnemonic(KeyEvent.VK_N);
        itemAltas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        itemAltas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrameAltas.setVisible(true);
            }
        });
        mAltas.add(itemAltas);
        menu.add(mAltas);
        //---------Menu Altas

        setJMenuBar(menu);//Agregar menu al panel principal.


        JDesktopPane panelEscritorio = new JDesktopPane();//Creacion del Desktop pane

        //----------Codigo Internal Frame Altas
        internalFrameAltas = new JInternalFrame();
        internalFrameAltas.setLayout(null);
        internalFrameAltas.setDefaultCloseOperation(HIDE_ON_CLOSE);
        internalFrameAltas.setSize(800, 500);
        internalFrameAltas.setResizable(true);
        internalFrameAltas.setVisible(false);
        internalFrameAltas.setClosable(true);
        internalFrameAltas.setIconifiable(true);
        internalFrameAltas.setMaximizable(true);
        internalFrameAltas.setBackground(Color.black);

        internalFrameAltas.add(metodoAltas());//recibe el panel
        panelEscritorio.add(internalFrameAltas);
        add(panelEscritorio, BorderLayout.CENTER);
        //----------Codigo Internal Frame Altas


    }
    //----------Jpanel metodo Altas
        public JPanel metodoAltas(){
            JPanel altas = new JPanel();
            altas.setLayout(null);
            altas.setSize(800, 500);
            altas.setBounds(0, 0, 800, 800);

            //------Codigo Barra De titulo Altas

            JPanel barra = new JPanel();
            barra.setVisible(true);
            barra.setLayout(null);
            barra.setBackground(new Color(128, 146, 110));
            barra.setSize(800, 800);
            barra.setBounds(0, 0, 800, 70);
            JLabel titulo = new JLabel("Altas Alumnos");
            titulo.setFont(new Font("ARIAL", Font.BOLD, 40));
            titulo.setBounds(new Rectangle(25, 0, 600, 70));
            barra.add(titulo);
            altas.add(barra);

            //------Codigo Barra De titulo Altas

            //------Contenido Frame Altas

            JLabel etiquetaNumControl = new JLabel("Numero de Control: ");
            JLabel etiquetaNombre = new JLabel("Nombre: ");
            JLabel etiquetaApPaterno = new JLabel("Apellido Paterno: ");
            JLabel etiquetaApMaterno = new JLabel("Apellido Materno: ");
            JLabel etiquetaSemestre = new JLabel("Semestre: ");
            JLabel etiquetaCarrera = new JLabel("Carrera: ");
            JTextField entradaNumControl = new JTextField();
            JTextField entradaNombre = new JTextField();
            JTextField entradaApPaterno = new JTextField();
            JTextField entradaApMaterno = new JTextField();
            JComboBox<String> entradaSemestre = new JComboBox<String>();
            JComboBox<String> entradaCarrera  = new JComboBox<String>();
            JButton botonAgregar = new JButton("AGREGAR");
            JButton botonBorrar = new JButton("BORRAR");
            JButton botonCancelar = new JButton("CANCELAR");

            //------Contendido Frame Altas

            //------Iconos Frame Altas
            ImageIcon iconoAgregar = new ImageIcon("imagenes/add.png");
            ImageIcon iconoBorrar = new ImageIcon("imagenes/eraser.png");
            ImageIcon iconoCancelar = new ImageIcon("imagenes/error.png");
            //------Iconos Frame Altas

            //------Configuracion de Componentes Frame Altas

            etiquetaNumControl.setFont(new Font("ARIAL", Font.BOLD, 20));
            etiquetaNombre.setFont(new Font("ARIAL", Font.BOLD, 20));
            etiquetaApPaterno.setFont(new Font("ARIAL", Font.BOLD, 20));
            etiquetaApMaterno.setFont(new Font("ARIAL", Font.BOLD, 20));
            etiquetaSemestre.setFont(new Font("ARIAL", Font.BOLD, 20));
            etiquetaCarrera.setFont(new Font("ARIAL", Font.BOLD, 20));
            entradaNumControl.setFont(new Font("ARIAL", Font.BOLD, 20));
            entradaNombre.setFont(new Font("ARIAL", Font.BOLD, 20));
            entradaApPaterno.setFont(new Font("ARIAL", Font.BOLD, 20));
            entradaApMaterno.setFont(new Font("ARIAL", Font.BOLD, 20));
            entradaSemestre.setFont(new Font("ARIAL", Font.ITALIC, 20));
            entradaSemestre.addItem("Elige Semestre");
            entradaSemestre.addItem("1");
            entradaSemestre.addItem("2");
            entradaSemestre.addItem("3");
            entradaSemestre.addItem("4");
            entradaSemestre.addItem("5");
            entradaSemestre.addItem("6");
            entradaSemestre.addItem("7");
            entradaSemestre.addItem("8");
            entradaSemestre.addItem("9");
            entradaSemestre.addItem("10");
            entradaSemestre.addItem("11");
            entradaSemestre.addItem("12");
            entradaCarrera.setFont(new Font("ARIAL", Font.ITALIC, 20));
            entradaCarrera.addItem("Elige Carrera");
            entradaCarrera.addItem("I.S.C.");
            entradaCarrera.addItem("I.M.");
            entradaCarrera.addItem("I.A.");
            entradaCarrera.addItem("L.C.P.");
            entradaCarrera.addItem("L.A.");
            botonAgregar.setFont(new Font("ARIAL", Font.BOLD, 13));
            botonAgregar.setIcon(iconoAgregar);
            botonBorrar.setFont(new Font("ARIAL", Font.BOLD, 13));
            botonBorrar.setIcon(iconoBorrar);
            botonCancelar.setFont(new Font("ARIAL", Font.BOLD, 13));
            botonCancelar.setIcon(iconoCancelar);

            //------Configuracion de Componentes Frame Altas

            //------Ubicacion de componentes Frame Altas Null layaout
            etiquetaNumControl.setBounds(new Rectangle(40, 70, 200, 70));
            etiquetaNombre.setBounds(new Rectangle(40, 100, 100, 70));
            etiquetaApPaterno.setBounds(new Rectangle(40, 130, 190, 70));
            etiquetaApMaterno.setBounds(new Rectangle(40, 160, 190, 70));
            etiquetaSemestre.setBounds(new Rectangle(40, 220, 120, 70));
            etiquetaCarrera.setBounds(new Rectangle(40, 250, 100, 70));
            entradaNumControl.setBounds(new Rectangle(230, 90, 200, 30));
            entradaNombre.setBounds(new Rectangle(125, 120, 305, 30));
            entradaApPaterno.setBounds(new Rectangle(210, 150, 220, 30));
            entradaApMaterno.setBounds(new Rectangle(210, 183, 220, 30));
            entradaSemestre.setBounds(new Rectangle(140, 238, 170, 30));
            entradaCarrera.setBounds(new Rectangle(125, 270, 185, 30));
            botonAgregar.setBounds(new Rectangle(500, 90, 140, 30));
            botonBorrar.setBounds(new Rectangle(500, 150, 120, 30));
            botonCancelar.setBounds(new Rectangle(500, 210, 150, 30));

            //------Ubicacion de componentes Frame Altas Null layout

            //------Tabla Frame Altas

            JPanel tabla = new JPanel();
            tabla.setVisible(true);
            tabla.setLayout(null);
            tabla.setSize(500, 150);
            tabla.setBounds(80, 310, 500, 150);
            tablaAlumnosAltas = new JTable();
            tablaAlumnosAltas.setBounds(0, 0, 500, 150);
            tablaAlumnosAltas.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][] {
                    }, new String []{"Num. Control", "Nombre", "Primer Ap", "Segundo Ap", "Semestre", "Carrera"}
            ));
            JScrollPane scrolltable = new JScrollPane();
            scrolltable.setViewportView(tablaAlumnosAltas);
            scrolltable.setSize(500, 150);
            tabla.add(scrolltable);

            //------Tabla Frame Altas

            //------Insercion Componentes Frame Altas
            altas.add(etiquetaNumControl);
            altas.add(etiquetaNombre);
            altas.add(etiquetaApPaterno);
            altas.add(etiquetaApMaterno);
            altas.add(etiquetaSemestre);
            altas.add(etiquetaCarrera);
            altas.add(entradaNumControl);
            altas.add(entradaNombre);
            altas.add(entradaApPaterno);
            altas.add(entradaApMaterno);
            altas.add(entradaSemestre);
            altas.add(entradaCarrera);
            altas.add(botonAgregar);
            altas.add(botonBorrar);
            altas.add(botonCancelar);
            altas.add(tabla);

            //------Insercion Componentes Frame Altas

            //------Eventos frame Altas
            botonAgregar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (entradaNumControl.getText().equals("") || entradaNombre.getText().equals("") || entradaApPaterno.getText().equals("")
                            || entradaApMaterno.getText().equals("") || (entradaSemestre.getSelectedItem() + "").equals("Elige Semestre")
                            || (entradaCarrera.getSelectedItem() + "").equals("Elige Carrera")) {//validacion
                        JOptionPane.showMessageDialog(getContentPane(), "Ningun Campo puede estar en blanco",
                                "REGISTRO NO COMPLETADO",JOptionPane.WARNING_MESSAGE);
                    } else {
                        Alumno a = new Alumno(entradaNumControl.getText(),
                                entradaNombre.getText(),
                                entradaApPaterno.getText(),
                                entradaApMaterno.getText(),
                                Byte.parseByte("18"),
                                Byte.parseByte((String) entradaSemestre.getSelectedItem()),
                                entradaCarrera.getSelectedItem() + "");

                        AlumnoDAO mAlumnoDAO = new AlumnoDAO();

                        if (mAlumnoDAO.insertarRegistros(a)) {
                            JOptionPane.showMessageDialog(getContentPane(), "REGISTRO AGREGADO CORRECTAMENTE");

                            final String TABLA_ALUMNOS = "alumnos";

                            String driver = "com.mysql.jdbc.Driver";
                            String url = "jdbc:mysql://localhost/bd_escuela";
                            String user = "root";
                            String password = "chesterf51997";
                            String query = "SELECT * FROM " + TABLA_ALUMNOS;


                           /* ResultSetTableModel modelo = null;
                            try {
                                modelo = new ResultSetTableModel(driver, url, user, password, query);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            actualizarTabla();*/
                            entradaNumControl.setText("");
                            entradaNombre.setText("");
                            entradaApPaterno.setText("");
                            entradaApMaterno.setText("");
                            entradaSemestre.setSelectedItem("Elige Semestre");
                            entradaCarrera.setSelectedItem("Elige Carrera");
                        } else {
                            JOptionPane.showMessageDialog(getContentPane(), "ERROR!!!!");
                        }
                    }
                }
            });
            botonBorrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    entradaNumControl.setText("");
                    entradaNombre.setText("");
                    entradaApPaterno.setText("");
                    entradaApMaterno.setText("");
                    entradaSemestre.setSelectedItem("Elige Semestre");
                    entradaCarrera.setSelectedItem("Elige Carrera");
                }
            });
            botonCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    internalFrameAltas.setVisible(false);
                }
            });


            //------Eventos frame Altas





            return altas;
        }


    public static void main(String[] args) {//metodo principal
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana();
            }
        });
    }//metodo principal


    }//ventana clase


