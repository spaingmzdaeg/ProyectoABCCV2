package edu.tec.jerez.topicos.vista;
import edu.tec.jerez.topicos.modelo.Alumno;
import edu.tec.jerez.topicos.controlador.AlumnoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;


public class Ventana extends JFrame{//ventana clase
    JTable tablaAlumnosAltas, tablaAlumnosBajas, tablaAlumnosModificaciones, tablaAlumnosConsultas;
    JInternalFrame internalFrameAltas, internalFrameBajas, internalFrameModificaciones, internalFrameConsultas;

    private static final long serialVersionUID = 1L;



    public Ventana(){

        getContentPane().setLayout(new BorderLayout());
        setSize(1000,1000);
        setTitle("Ventana Principal");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);



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
        JMenu mBajas = new JMenu("Bajas");
        JMenuItem itemBajas = new JMenuItem("Dar de Baja  un Alumno...");
        itemBajas.setMnemonic(KeyEvent.VK_N);
        itemBajas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemBajas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrameBajas.setVisible(true);
            }
        });
        mBajas.add(itemBajas);
        menu.add(mBajas);
        JMenu mModificaciones = new JMenu("Modificaciones");
        JMenuItem itemModificaciones = new JMenuItem("Modificaciones Alumnos");
        itemModificaciones.setMnemonic(KeyEvent.VK_N);
        itemModificaciones.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
        itemModificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrameModificaciones.setVisible(true);
            }
        });
        mModificaciones.add(itemModificaciones);
        menu.add(mModificaciones);
        JMenu mConsultas = new JMenu("Consultas");
        JMenuItem itemColsultas = new JMenuItem("consultas Alumnos");
        itemColsultas.setMnemonic(KeyEvent.VK_N);
        itemColsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.SHIFT_MASK));
        itemColsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrameConsultas.setVisible(true);
            }
        });
        mConsultas.add(itemColsultas);
        menu.add(mConsultas);

        setJMenuBar(menu);//------Acomodo en el menu principal


        JDesktopPane panelEscritorio = new JDesktopPane();
       //--Codigo Frame Altas
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

        internalFrameAltas.add(metodoAltas());
        panelEscritorio.add(internalFrameAltas);
        add(panelEscritorio, BorderLayout.CENTER);

        //--Codigo Frame Bajas
        internalFrameBajas = new JInternalFrame();
        internalFrameBajas.setLayout(null);
        internalFrameBajas.setDefaultCloseOperation(HIDE_ON_CLOSE);
        internalFrameBajas.setSize(800, 500);
        internalFrameBajas.setResizable(true);
        internalFrameBajas.setVisible(false);
        internalFrameBajas.setClosable(true);
        internalFrameBajas.setIconifiable(true);
        internalFrameBajas.setMaximizable(true);
        internalFrameBajas.setBackground(Color.black);

        internalFrameBajas.add(metodoBajas());
        panelEscritorio.add(internalFrameBajas);
        add(panelEscritorio, BorderLayout.CENTER);

        //---Codigo Frame Modificaciones
        internalFrameModificaciones = new JInternalFrame();
        internalFrameModificaciones.setLayout(null);
        internalFrameModificaciones.setDefaultCloseOperation(HIDE_ON_CLOSE);
        internalFrameModificaciones.setSize(800, 500);
        internalFrameModificaciones.setResizable(true);
        internalFrameModificaciones.setVisible(false);
        internalFrameModificaciones.setClosable(true);
        internalFrameModificaciones.setIconifiable(true);
        internalFrameModificaciones.setMaximizable(true);
        internalFrameModificaciones.setBackground(Color.black);

        internalFrameModificaciones.add(metodoModificaciones());
        panelEscritorio.add(internalFrameModificaciones);
        add(panelEscritorio, BorderLayout.CENTER);

        //----Codigo Frame Consultas
        internalFrameConsultas = new JInternalFrame();
        internalFrameConsultas.setLayout(null);
        internalFrameConsultas.setDefaultCloseOperation(HIDE_ON_CLOSE);
        internalFrameConsultas.setSize(800, 600);
        internalFrameConsultas.setResizable(true);
        internalFrameConsultas.setVisible(false);
        internalFrameConsultas.setClosable(true);
        internalFrameConsultas.setIconifiable(true);
        internalFrameConsultas.setMaximizable(true);
        internalFrameConsultas.setBackground(Color.black);

        internalFrameConsultas.add(metodoConsultas());
        panelEscritorio.add(internalFrameConsultas);
        add(panelEscritorio, BorderLayout.CENTER);
        actualizarTabla();
    }

    //JPanel metodo altas

    public JPanel metodoAltas() {//metodo altas

        JPanel altas = new JPanel();
        altas.setLayout(null);
        altas.setSize(800, 500);
        altas.setBounds(0, 0, 800, 800);

       //Codigo Barra Titulo altas
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

        //----Componentes Frame Altas
        JLabel etiquetaNumControl = new JLabel("Numero de Control: ");
        JLabel etiquetaNombre = new JLabel("Nombre: ");
        JLabel etiquetaApPaterno = new JLabel("Apellido Paterno: ");
        JLabel etiquetaApMaterno = new JLabel("Apellido Materno: ");
        JLabel etiquetaSemestre = new JLabel("Semestre: ");
        JLabel etiquetaCarrera = new JLabel("Carrera: ");
        JLabel etiquetaEdad = new JLabel("Edad: ");
        JTextField entradaNumControl = new JTextField();
        JTextField entradaNombre = new JTextField();
        JTextField entradaApPaterno = new JTextField();
        JTextField entradaApMaterno = new JTextField();
        JTextField entradaEdad = new JTextField();
        JComboBox<String> entradaSemestre = new JComboBox<String>();
        JComboBox<String> entradaCarrera  = new JComboBox<String>();
        JButton botonAgregar = new JButton("AGREGAR");
        JButton botonBorrar = new JButton("BORRAR");
        JButton botonCancelar = new JButton("CANCELAR");

        //-----Iconos Frame Altas
        ImageIcon iconoAgregar = new ImageIcon("imagenes/add.png");
        ImageIcon iconoBorrar = new ImageIcon("imagenes/eraser.png");
        ImageIcon iconoCancelar = new ImageIcon("imagenes/error.png");

        //------Configuracion
        etiquetaNumControl.setFont(new Font("ARIAL", Font.BOLD, 20));
        etiquetaNombre.setFont(new Font("ARIAL", Font.BOLD, 20));
        etiquetaApPaterno.setFont(new Font("ARIAL", Font.BOLD, 20));
        etiquetaApMaterno.setFont(new Font("ARIAL", Font.BOLD, 20));
        etiquetaSemestre.setFont(new Font("ARIAL", Font.BOLD, 20));
        etiquetaCarrera.setFont(new Font("ARIAL", Font.BOLD, 20));
        etiquetaEdad.setFont(new Font("ARIAL", Font.BOLD, 20));
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
        entradaEdad.setFont(new Font("ARIAL", Font.ITALIC, 20));
        botonAgregar.setFont(new Font("ARIAL", Font.BOLD, 13));
        botonAgregar.setIcon(iconoAgregar);
        botonBorrar.setFont(new Font("ARIAL", Font.BOLD, 13));
        botonBorrar.setIcon(iconoBorrar);
        botonCancelar.setFont(new Font("ARIAL", Font.BOLD, 13));
        botonCancelar.setIcon(iconoCancelar);

        //------Ubicacion Frame Altas
        etiquetaNumControl.setBounds(new Rectangle(40, 70, 200, 70));
        etiquetaNombre.setBounds(new Rectangle(40, 100, 100, 70));
        etiquetaApPaterno.setBounds(new Rectangle(40, 130, 190, 70));
        etiquetaApMaterno.setBounds(new Rectangle(40, 160, 190, 70));
        etiquetaSemestre.setBounds(new Rectangle(40, 220, 120, 70));
        etiquetaCarrera.setBounds(new Rectangle(40, 250, 100, 70));
        etiquetaEdad.setBounds(new Rectangle(340, 250, 100, 70));
        entradaNumControl.setBounds(new Rectangle(230, 90, 200, 30));
        entradaNombre.setBounds(new Rectangle(125, 120, 305, 30));
        entradaApPaterno.setBounds(new Rectangle(210, 150, 220, 30));
        entradaApMaterno.setBounds(new Rectangle(210, 183, 220, 30));
        entradaSemestre.setBounds(new Rectangle(140, 238, 170, 30));
        entradaCarrera.setBounds(new Rectangle(125, 270, 185, 30));
        entradaEdad.setBounds(new Rectangle(410, 270, 30, 30));
        botonAgregar.setBounds(new Rectangle(500, 90, 140, 30));
        botonBorrar.setBounds(new Rectangle(500, 150, 120, 30));
        botonCancelar.setBounds(new Rectangle(500, 210, 150, 30));

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

        //---------Incersion Componentes frame altas
        altas.add(etiquetaNumControl);
        altas.add(etiquetaNombre);
        altas.add(etiquetaApPaterno);
        altas.add(etiquetaApMaterno);
        altas.add(etiquetaSemestre);
        altas.add(etiquetaCarrera);
        altas.add(etiquetaEdad);
        altas.add(entradaNumControl);
        altas.add(entradaNombre);
        altas.add(entradaApPaterno);
        altas.add(entradaApMaterno);
        altas.add(entradaSemestre);
        altas.add(entradaCarrera);
        altas.add(entradaEdad);
        altas.add(botonAgregar);
        altas.add(botonBorrar);
        altas.add(botonCancelar);
        altas.add(tabla);

        //-----eventos frame altas
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (entradaNumControl.getText().equals("") || entradaNombre.getText().equals("") || entradaApPaterno.getText().equals("")
                        || entradaApMaterno.getText().equals("") || (entradaSemestre.getSelectedItem() + "").equals("Elige Semestre")
                        || (entradaCarrera.getSelectedItem() + "").equals("Elige Carrera") || entradaEdad.getText().equals("")
                || entradaEdad.getText().equals("0")) {//validacion
                    JOptionPane.showMessageDialog(getContentPane(), "Ningun Campo puede estar en blanco",
                            "REGISTRO NO COMPLETADO",JOptionPane.WARNING_MESSAGE);
                } else {
                    Alumno a = new Alumno(entradaNumControl.getText(),
                            entradaNombre.getText(),
                            entradaApPaterno.getText(),
                            entradaApMaterno.getText(),
                            Byte.parseByte(entradaEdad.getText()),
                            Byte.parseByte((String) entradaSemestre.getSelectedItem()),
                            entradaCarrera.getSelectedItem() + "");

                    AlumnoDAO mAlumnoDAO = new AlumnoDAO();

                    if (mAlumnoDAO.insertarRegistros(a)) {
                        JOptionPane.showMessageDialog(getContentPane(), "REGISTRO AGREGADO CORRECTAMENTE");

                        final String TABLA_ALUMNOS = "alumnos";

                        String driver = "com.mysql.jdbc.Driver";
                        String url = "jdbc:mysql://localhost/bd_escuela_2";
                        String user = "root";
                        String password = "chesterf51997";
                        String query = "SELECT * FROM " + TABLA_ALUMNOS;

                        ResultSetTableModel modelo = null;
                        try {
                            modelo = new ResultSetTableModel(driver, url, user, password, query);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        actualizarTabla();
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

        return altas;
    }//metodo altas

    public JPanel metodoBajas() {//metodo bajas

        JPanel bajas = new JPanel();
        bajas.setLayout(null);
        bajas.setSize(800, 500);
        bajas.setBounds(0, 0, 800, 800);

        //--------------BARRA Titulo
        JPanel barra = new JPanel();
        barra.setVisible(true);
        barra.setLayout(null);
        barra.setBackground(new Color(178, 46, 0));
        barra.setSize(600, 600);
        barra.setBounds(0, 0, 800, 70);
        JLabel titulo = new JLabel("Bajas Alumnos");
        titulo.setFont(new Font("ARIAL", Font.BOLD, 40));
        titulo.setBounds(new Rectangle(25, 0, 600, 70));
        barra.add(titulo);
        bajas.add(barra);

        //------Componentes frame bajas
        JLabel etiquetaNumControl = new JLabel("Numero de Control: ");
        JLabel etiquetaNombre = new JLabel("Nombre(s):");
        JLabel etiquetaApPaterno = new JLabel("Apellido Paterno: ");
        JLabel etiquetaApMaterno = new JLabel("Apellido Materno: ");
        JLabel etiquetaSemetre = new JLabel("Semestre: ");
        JLabel etiquetaCarrera = new JLabel("Carrera: ");
        JTextField entradaNumControl = new JTextField();
        JTextField entradaNombre = new JTextField();
        JTextField entradaApPaterno = new JTextField();
        JTextField entradaApMaterno = new JTextField();
        JComboBox<String> entradaSemestre = new JComboBox<String>();
        JComboBox<String> entradaCarrera = new JComboBox<String>();
        JButton botonBuscar = new JButton("");
        JButton botonBorrar = new JButton("BORRAR");
        JButton botonEliminar = new JButton("Eliminar");
        JButton botonCancelar = new JButton("Cancelar");

        JLabel etiquetaEdad = new JLabel("Edad: ");
        JTextField entradaEdad = new JTextField();


        //------Iconos Frame Bajas
        ImageIcon iconoBuscar = new ImageIcon("imagenes/lupa.png");
        ImageIcon iconoBorrar = new ImageIcon("imagenes/eraser.png");
        ImageIcon iconoCancelar = new ImageIcon("imagenes/error.png");
        ImageIcon iconoEliminar = new ImageIcon("imagenes/delete.png");

        //------Configuracion Frame Bajas
        Font letras = new Font("ARIAL", Font.BOLD, 20);
        Font letras2 = new Font("ARIAL", Font.BOLD, 13);
        etiquetaNumControl.setFont(letras);
        etiquetaNombre.setFont(letras);
        etiquetaApPaterno.setFont(letras);
        etiquetaApMaterno.setFont(letras);
        etiquetaSemetre.setFont(letras);
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
        etiquetaCarrera.setFont(letras);
        entradaCarrera.addItem("Elige Carrera");
        entradaCarrera.addItem("I.S.C.");
        entradaCarrera.addItem("I.M.");
        entradaCarrera.addItem("I.A.");
        entradaCarrera.addItem("L.C.P.");
        entradaCarrera.addItem("L.A.");
        entradaNumControl.setFont(letras);
        entradaNumControl.setBackground(new Color( 142, 196, 181));
        entradaNombre.setFont(letras);
        entradaApPaterno.setFont(letras);
        entradaApMaterno.setFont(letras);
        entradaSemestre.setFont(letras2);

        entradaCarrera.setFont(letras2);
        botonBuscar.setFont(letras2);
        botonBuscar.setIcon(iconoBuscar);
        botonBorrar.setFont(letras2);
        botonBorrar.setIcon(iconoBorrar);
        botonEliminar.setFont(letras2);
        botonEliminar.setIcon(iconoEliminar);
        botonCancelar.setFont(letras2);
        botonCancelar.setIcon(iconoCancelar);

        etiquetaEdad.setFont(new Font("ARIAL", Font.BOLD, 20));
        entradaEdad.setFont(new Font("ARIAL", Font.ITALIC, 20));

        //----------------Ubicacion Frame Bajas

        etiquetaNumControl.setBounds(new Rectangle(40, 70, 200, 70));
        etiquetaNombre.setBounds(new Rectangle(20, 100, 180, 70));
        etiquetaApPaterno.setBounds(new Rectangle(40, 130, 190, 70));
        etiquetaApMaterno.setBounds(new Rectangle(40, 160, 190, 70));
        etiquetaSemetre.setBounds(new Rectangle(40, 220, 120, 70));
        etiquetaCarrera.setBounds(new Rectangle(40, 250, 100, 70));
        entradaNumControl.setBounds(new Rectangle(230, 90, 200, 30));
        entradaNombre.setBounds(new Rectangle(125, 120, 305, 30));
        entradaApPaterno.setBounds(new Rectangle(210, 150, 220, 30));
        entradaApMaterno.setBounds(new Rectangle(210, 183, 220, 30));
        entradaSemestre.setBounds(new Rectangle(140, 238, 170, 30));
        entradaCarrera.setBounds(new Rectangle(125, 270, 185, 30));
        //botonAgregar.setBounds(new Rectangle(500, 90, 140, 30));
        botonBorrar.setBounds(new Rectangle(500, 150, 120, 30));
        botonCancelar.setBounds(new Rectangle(500, 210, 150, 30));


        botonBuscar.setBounds(new Rectangle(450, 90, 30, 30));
        botonEliminar.setBounds(new Rectangle(510, 90, 135, 30));

        etiquetaEdad.setBounds(new Rectangle(340, 250, 100, 70));
        entradaEdad.setBounds(new Rectangle(410, 270, 30, 30));
        //-----Tabla Frame Bajas
        JPanel tabla = new JPanel();
        tabla.setVisible(true);
        tabla.setLayout(null);
        tabla.setSize(500, 150);
        tabla.setBounds(80, 310, 500, 150);
        tablaAlumnosBajas = new JTable();
        tablaAlumnosBajas.setBounds(0, 0, 500, 150);
        tablaAlumnosBajas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                }, new String []{"Num. Control", "Nombre", "Primer Ap", "Segundo Ap", "Semestre", "Carrera"}
        ));
        JScrollPane scrolltable = new JScrollPane();
        scrolltable.setViewportView(tablaAlumnosBajas);
        scrolltable.setSize(500, 150);
        tabla.add(scrolltable);

        //-------Insercion Frame Bajas
        bajas.add(etiquetaNumControl);
        bajas.add(etiquetaNombre);
        bajas.add(etiquetaApPaterno);
        bajas.add(etiquetaApMaterno);
        bajas.add(etiquetaSemetre);
        bajas.add(etiquetaCarrera);
        bajas.add(entradaNumControl);
        bajas.add(entradaNombre);
        bajas.add(entradaApPaterno);
        bajas.add(entradaApMaterno);
        bajas.add(entradaSemestre);
        bajas.add(entradaCarrera);
        bajas.add(botonBuscar);
        bajas.add(botonBorrar);
        bajas.add(botonEliminar);
        bajas.add(botonCancelar);
        bajas.add(tabla);
        bajas.add(etiquetaEdad);
        bajas.add(entradaEdad);

        //--------------Eventos Frame Bajas
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(entradaNumControl.getText().equals("")){
                    JOptionPane.showMessageDialog(getContentPane(),"Ingrese numero de control","BUSCAR ALUMNO"
                            ,JOptionPane.WARNING_MESSAGE);
                }else {
                    AlumnoDAO mAlumnoDAO = new AlumnoDAO();
                    Alumno a = mAlumnoDAO.buscarAlumno(entradaNumControl.getText());

                    entradaNumControl.setText(a.getNumControl());
                    entradaNombre.setText(a.getNombre());
                    entradaApPaterno.setText(a.getPrimerAp());
                    entradaApMaterno.setText(a.getSegundoAp());
                    entradaEdad.setText(String.valueOf(a.getEdad()));
                    //cajaEdad.setText(a.getEdad()+"");
                    entradaSemestre.setSelectedItem(a.getSemestre() + "");
                    entradaCarrera.setSelectedItem(a.getCarrera());
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
                entradaSemestre.setSelectedItem("Elegir Semestre");
                entradaCarrera.setSelectedItem("Elegir Carrera");

            }
        });
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(entradaNumControl.getText().equals("")){
                    JOptionPane.showMessageDialog(getContentPane(),"Ingrese numero de control","ELIMINAR ALUMNO"
                            ,JOptionPane.WARNING_MESSAGE);
                }else {

                    String tNumControl = entradaNumControl.getText();
                    AlumnoDAO mAlumnoDAO = new AlumnoDAO();
                    if (mAlumnoDAO.eliminarRegistro(tNumControl)) {
                        JOptionPane.showMessageDialog(getContentPane(), "Eliminacion Exito");
                    } else
                        JOptionPane.showMessageDialog(getContentPane(), "Eliminacion NO Exitosa");

                    entradaNumControl.setText("");
                    entradaNombre.setText("");
                    entradaApPaterno.setText("");
                    entradaApMaterno.setText("");
                    entradaSemestre.setSelectedItem("Elige Semestre");
                    entradaCarrera.setSelectedItem("Elige Carrera");
                    actualizarTabla();
                }
            }
        });
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrameBajas.setVisible(false);
            }
        });

        return bajas;
    }//Metodo Bajas

    public JPanel metodoModificaciones() {//metodo Modificaciones

        JPanel modificaciones = new JPanel();
        modificaciones.setLayout(null);
        modificaciones.setSize(600, 600);
        modificaciones.setBounds(0, 0, 800, 500);

        //----Barra titulo Jframe Modificaciones
        JPanel barra = new JPanel();
        barra.setVisible(true);
        barra.setLayout(null);
        barra.setBackground(new Color(178, 176, 39));
        barra.setSize(600, 600);
        barra.setBounds(0, 0, 800, 70);
        JLabel titulo = new JLabel("Modificaciones Alumnos");
        titulo.setFont(new Font("ARIAL", Font.BOLD, 40));
        titulo.setBounds(new Rectangle(25, 0, 600, 70));
        barra.add(titulo);
        modificaciones.add(barra);

        //------Componentes Jframe Modificaciones
        JLabel etiquetaNumControl = new JLabel("Numero de Control: ");
        JLabel etiquetaNombre = new JLabel("Nombre(s): ");
        JLabel etiquetaApPaterno = new JLabel("Apellido Paterno: ");
        JLabel etiquetaApMaterno = new JLabel("Apellido Materno: ");
        JLabel etiquetaSemetre = new JLabel("Semestre: ");
        JLabel etiquetaCarrera = new JLabel("Carrera: ");
        JTextField entradaNumControl = new JTextField();
        JTextField entradaNombre = new JTextField();
        JTextField entradaApPaterno = new JTextField();
        JTextField entradaApMaterno = new JTextField();
        JComboBox<String> entradaSemestre = new JComboBox<String>();
        JComboBox<String> entradaCarrera = new JComboBox<String>();
        JButton botonBuscar = new JButton("");
        JButton botonBorrar = new JButton("BORRAR");
        JButton botonGuardar = new JButton("Guardar Cambios");
        JButton botonCancelar = new JButton("Cancelar");

        JLabel etiquetaEdad = new JLabel("Edad: ");
        JTextField entradaEdad = new JTextField();


        //-------Iconos Frame Modificaciones
        ImageIcon iconoBuscar = new ImageIcon("imagenes/lupa.png");
        ImageIcon iconoBorrar = new ImageIcon("imagenes/eraser.png");
        ImageIcon iconoCancelar = new ImageIcon("imagenes/error.png");
        ImageIcon iconoGurdar = new ImageIcon("imagenes/guardar.png");

        //-------Configuracion Frame Modificaciones
        Font letras = new Font("ARIAL", Font.BOLD, 20);
        Font letras2 = new Font("ARIAL", Font.BOLD, 13);
        etiquetaNumControl.setFont(letras);
        etiquetaNombre.setFont(letras);
        etiquetaApPaterno.setFont(letras);
        etiquetaApMaterno.setFont(letras);
        etiquetaSemetre.setFont(letras);
        etiquetaCarrera.setFont(letras);
        entradaNumControl.setFont(letras);
        entradaNumControl.setBackground(new Color( 142, 196, 181));
        entradaNombre.setFont(letras);
        entradaApPaterno.setFont(letras);
        entradaApMaterno.setFont(letras);
        entradaSemestre.setFont(letras);
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
        entradaCarrera.setFont(letras);
        entradaCarrera.addItem("Elegir Carrera");
        entradaCarrera.addItem("I.S.C.");
        entradaCarrera.addItem("I.M.");
        entradaCarrera.addItem("I.A.");
        entradaCarrera.addItem("L.C.P.");
        entradaCarrera.addItem("L.A.");
        botonBuscar.setFont(letras2);
        botonBuscar.setIcon(iconoBuscar);
        botonBorrar.setFont(letras2);
        botonBorrar.setIcon(iconoBorrar);
        botonGuardar.setFont(letras2);
        botonGuardar.setIcon(iconoGurdar);
        botonCancelar.setFont(letras2);
        botonCancelar.setIcon(iconoCancelar);

        etiquetaEdad.setFont(new Font("ARIAL", Font.BOLD, 20));
        entradaEdad.setFont(new Font("ARIAL", Font.ITALIC, 20));
        //------Ubicacion Jframe Modificaciones

        etiquetaNumControl.setBounds(new Rectangle(40, 70, 200, 70));
        etiquetaNombre.setBounds(new Rectangle(20, 100, 180, 70));
        etiquetaApPaterno.setBounds(new Rectangle(40, 130, 190, 70));
        etiquetaApMaterno.setBounds(new Rectangle(40, 160, 190, 70));
        etiquetaSemetre.setBounds(new Rectangle(40, 220, 120, 70));
        etiquetaCarrera.setBounds(new Rectangle(40, 250, 100, 70));
        entradaNumControl.setBounds(new Rectangle(230, 90, 200, 30));
        entradaNombre.setBounds(new Rectangle(125, 120, 305, 30));
        entradaApPaterno.setBounds(new Rectangle(210, 150, 220, 30));
        entradaApMaterno.setBounds(new Rectangle(210, 183, 220, 30));
        entradaSemestre.setBounds(new Rectangle(140, 238, 170, 30));
        entradaCarrera.setBounds(new Rectangle(125, 270, 185, 30));
        // botonAgregar.setBounds(new Rectangle(500, 90, 140, 30));
        botonBorrar.setBounds(new Rectangle(500, 150, 120, 30));
        botonCancelar.setBounds(new Rectangle(500, 210, 150, 30));
        botonBuscar.setBounds(new Rectangle(450, 90, 30, 30));
        botonGuardar.setBounds(new Rectangle(500, 90, 140, 30));

        etiquetaEdad.setBounds(new Rectangle(340, 250, 100, 70));
        entradaEdad.setBounds(new Rectangle(410, 270, 30, 30));

        //-----Jtable Frame Modificaciones
        JPanel tabla = new JPanel();
        tabla.setVisible(true);
        tabla.setLayout(null);
        tabla.setSize(500, 150);
        tabla.setBounds(80, 310, 500, 150);
        tablaAlumnosModificaciones = new JTable();
        tablaAlumnosModificaciones.setBounds(0, 0, 500, 150);
        tablaAlumnosModificaciones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                }, new String []{"Num. Control", "Nombre", "Primer Ap", "Segundo Ap", "Semestre", "Carrera"}
        ));
        JScrollPane scrolltable = new JScrollPane();
        scrolltable.setViewportView(tablaAlumnosModificaciones);
        scrolltable.setSize(500, 150);
        tabla.add(scrolltable);

        //----Insercion Componentes Frame Modificaciones
        modificaciones.add(etiquetaNumControl);
        modificaciones.add(etiquetaNombre);
        modificaciones.add(etiquetaApPaterno);
        modificaciones.add(etiquetaApMaterno);
        modificaciones.add(etiquetaSemetre);
        modificaciones.add(etiquetaCarrera);
        modificaciones.add(entradaNumControl);
        modificaciones.add(entradaNombre);
        modificaciones.add(entradaApPaterno);
        modificaciones.add(entradaApMaterno);
        modificaciones.add(entradaSemestre);
        modificaciones.add(entradaCarrera);
        modificaciones.add(botonBuscar);
        modificaciones.add(botonBorrar);
        modificaciones.add(botonGuardar);
        modificaciones.add(botonCancelar);
        modificaciones.add(tabla);

        modificaciones.add(etiquetaEdad);
        modificaciones.add(entradaEdad);

        //----------Eventos Frame Modificaciones
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (entradaNumControl.getText().equals("")) {
                    JOptionPane.showMessageDialog(getContentPane(), "Ingrese numero de control", "MODIFICAR ALUMNO"
                            , JOptionPane.WARNING_MESSAGE);
                } else {
                    AlumnoDAO mAlumnoDAO = new AlumnoDAO();
                    Alumno a = mAlumnoDAO.buscarAlumno(entradaNumControl.getText());

                    entradaNumControl.setText(a.getNumControl());
                    entradaNombre.setText(a.getNombre());
                    entradaApPaterno.setText(a.getPrimerAp());
                    entradaApMaterno.setText(a.getSegundoAp());
                    entradaEdad.setText(String.valueOf(a.getEdad()));
                    //cajaEdad.setText(a.getEdad()+"");
                    entradaSemestre.setSelectedItem(a.getSemestre() + "");
                    entradaCarrera.setSelectedItem(a.getCarrera());
                    actualizarTabla();

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
                entradaSemestre.setSelectedItem("Elegir Semestre");
                entradaCarrera.setSelectedItem("Elegir Carrera");
            }
        });
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (entradaNumControl.getText().equals("") || entradaNombre.getText().equals("") || entradaApPaterno.getText().equals("")
                        || entradaApMaterno.getText().equals("") || (entradaSemestre.getSelectedItem() + "").equals("Elige Semestre")
                        || (entradaCarrera.getSelectedItem() + "").equals("Elige Carrera")|| entradaEdad.getText().equals("")
                        || entradaEdad.getText().equals("0")){
                    JOptionPane.showMessageDialog(getContentPane(), "Ningun Campo puede estar en blanco",
                            "REGISTRO NO COMPLETADO",JOptionPane.WARNING_MESSAGE);

                } else{
                    Alumno a = new Alumno(entradaNumControl.getText(),
                            entradaNombre.getText(),
                            entradaApPaterno.getText(),
                            entradaApMaterno.getText(),
                            Byte.parseByte(entradaEdad.getText() + ""),
                            Byte.parseByte(entradaSemestre.getSelectedItem() + ""),
                            entradaCarrera.getSelectedItem() + "");

                    if (new AlumnoDAO().actualizarRegistro(a)) {
                        JOptionPane.showMessageDialog(getContentPane(), "Actualizacion Exitosa");
                    } else {
                        JOptionPane.showMessageDialog(getContentPane(), "Actualizacion NO Exitosa");
                    }
                    actualizarTabla();

                    entradaNumControl.setText("");
                    entradaNombre.setText("");
                    entradaApPaterno.setText("");
                    entradaApMaterno.setText("");
                    entradaSemestre.setSelectedItem("Elegir Semestre");
                    entradaCarrera.setSelectedItem("Elegir Carrera");
                }
            }
        });
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrameModificaciones.setVisible(false);
            }
        });

        return modificaciones;
    }//metodo modificaciones

    public JPanel metodoConsultas() {//metodo Consultas

        JPanel consultas = new JPanel();
        consultas.setLayout(null);
        consultas.setSize(600, 600);
        consultas.setBounds(0, 0, 800, 600);

        //-------Barra Consultas
        JPanel barra = new JPanel();
        barra.setVisible(true);
        barra.setLayout(null);
        barra.setBackground(new Color(32, 31, 178));
        barra.setSize(800, 500);
        barra.setBounds(0, 0, 800, 70);
        JLabel titulo = new JLabel("Consultar Alumnos");
        titulo.setFont(new Font("ARIAL", Font.BOLD, 40));
        titulo.setBounds(new Rectangle(25, 0, 600, 70));
        barra.add(titulo);
        consultas.add(barra);

        //------Componentes Frame Consultas
        JRadioButton todos = new JRadioButton("Todos");

        JRadioButton nombre = new JRadioButton("Nombre:");
        JRadioButton apPaterno = new JRadioButton("Apellido Paterno:");
        JRadioButton apMaterno = new JRadioButton("Apellido Materno:");
        JRadioButton semestre = new JRadioButton("Semestre:");
        JRadioButton carrera = new JRadioButton("Carrera:");
        JRadioButton edad = new JRadioButton("Edad:");
        JTextField entradaNombre = new JTextField();
        JTextField entradaApPaterno = new JTextField();
        JTextField entradaApMaterno = new JTextField();
        JTextField entradaEdad = new JTextField();
        JComboBox<String> entradaSemestre = new JComboBox<String>();
        JComboBox<String> entradaCarrera = new JComboBox<String>();
        JButton botonBuscar = new JButton("");
        JButton botonBorrar = new JButton("");
        JButton botonCancelar = new JButton("");

        //-----Iconos Frame Consultas
        ImageIcon iconoBuscar = new ImageIcon("imagenes/lupa.png");
        ImageIcon iconoBorrar = new ImageIcon("imagenes/eraser.png");
        ImageIcon iconoCancelar = new ImageIcon("imagenes/error.png");

        //----Configuracion frame consultas
        Font letras = new Font("ARIAL", Font.BOLD, 20);
        todos.setFont(letras);
        nombre.setFont(letras);
        apPaterno.setFont(letras);
        apMaterno.setFont(letras);
        semestre.setFont(letras);
        carrera.setFont(letras);
        edad.setFont(letras);
        entradaNombre.setFont(letras);
        entradaApPaterno.setFont(letras);
        entradaApMaterno.setFont(letras);
        entradaEdad.setFont(letras);
        entradaSemestre.setFont(letras);
        entradaSemestre.addItem("Elegir Semestre");
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
        entradaCarrera.setFont(letras);
        entradaCarrera.addItem("Elegir Carrera");
        entradaCarrera.addItem("I.S.C.");
        entradaCarrera.addItem("I.M.");
        entradaCarrera.addItem("I.A.");
        entradaCarrera.addItem("L.C.P.");
        entradaCarrera.addItem("L.A.");
        botonBuscar.setFont(letras);
        botonBuscar.setIcon(iconoBuscar);
        botonBorrar.setFont(letras);
        botonBorrar.setIcon(iconoBorrar);
        botonCancelar.setFont(letras);
        botonCancelar.setIcon(iconoCancelar);

        //-------Ubicacion Frame Consultas
        todos.setBounds(new Rectangle( 460, 100, 100, 30));
        nombre.setBounds(new Rectangle( 100, 100, 120, 30));
        apPaterno.setBounds(new Rectangle( 60, 150, 200, 30));
        apMaterno.setBounds(new Rectangle( 60, 200, 200, 30));
        semestre.setBounds(new Rectangle( 100, 250, 150, 30));
        carrera.setBounds(new Rectangle( 100, 300, 150, 30));
        edad.setBounds(new Rectangle( 100, 350, 150, 30));
        entradaNombre.setBounds(new Rectangle(270, 100, 160, 30));
        entradaApPaterno.setBounds(new Rectangle(270, 150, 160, 30));
        entradaApMaterno.setBounds(new Rectangle(270, 200, 160, 30));
        entradaSemestre.setBounds(new Rectangle(270, 250, 160, 30));
        entradaCarrera.setBounds(new Rectangle(270, 300, 160, 30));
        entradaEdad.setBounds(new Rectangle(270, 350, 30, 30));
        botonBuscar.setBounds(new Rectangle(470, 150, 70, 30));
        botonBorrar.setBounds(new Rectangle(470, 200, 70, 30));
        botonCancelar.setBounds(new Rectangle(470, 250, 70, 30));

        //------=Jtable Frame Consultas
        JPanel tabla = new JPanel();
        tabla.setVisible(true);
        tabla.setLayout(null);
        tabla.setSize(500, 150);
        tabla.setBounds(80, 400, 500, 150);
        tablaAlumnosConsultas = new JTable();
        tablaAlumnosConsultas.setBounds(0, 0, 500, 150);
        tablaAlumnosConsultas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                }, new String []{"Num. Control", "Nombre", "Primer Ap", "Segundo Ap", "Semestre", "Carrera"}
        ));
        JScrollPane scrolltable = new JScrollPane();
        scrolltable.setViewportView(tablaAlumnosConsultas);
        scrolltable.setSize(500, 150);
        tabla.add(scrolltable);

        //-----Incercion Frame Consultas
        consultas.add(todos);
        consultas.add(nombre);
        consultas.add(apPaterno);
        consultas.add(apMaterno);
        consultas.add(semestre);
        consultas.add(carrera);
        consultas.add(entradaNombre);
        consultas.add(entradaApPaterno);
        consultas.add(entradaApMaterno);
        consultas.add(entradaSemestre);
        consultas.add(entradaCarrera);
        consultas.add(tabla);
        consultas.add(botonBuscar);
        consultas.add(botonBorrar);
        consultas.add(botonCancelar);

        consultas.add(edad);
        consultas.add(entradaEdad);

        //-------Eventos Frame Consultas
        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                entradaNombre.setText("");
                entradaApPaterno.setText("");
                entradaApMaterno.setText("");
                entradaSemestre.setSelectedItem("Elegir Semestre");
                entradaCarrera.setSelectedItem("Elegir Carrera");
            }
        });
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				/*
				AlumnoDAO mAlumnoDAO = new AlumnoDAO();
				Alumno a = mAlumnoDAO.buscarAlumno(entradaNumControl.getText());

				entradaNumControl.setText(a.getNumControl());
				entradaNombre.setText(a.getNombre());
				entradaApPaterno.setText(a.getPrimerAp());
				entradaApMaterno.setText(a.getSegundoAp());

				entradaSemestre.setSelectedItem(a.getSemestre()+"");
				entradaCarrera.setSelectedItem(a.getCarrera());
				*/

                AlumnoDAO alumnoDAO = new AlumnoDAO();
                String dato = "";
                if(nombre.isSelected()){
                    dato = entradaNombre.getText();
                    alumnoDAO.consultasPorNombre(dato,tablaAlumnosConsultas);
                }else if(apPaterno.isSelected()){
                    dato = entradaApPaterno.getText();
                    alumnoDAO.consultasPorApaterno(dato,tablaAlumnosConsultas);
                }else if(apMaterno.isSelected()){
                    dato = entradaApMaterno.getText();
                    alumnoDAO.consultasPorAmaterno(dato,tablaAlumnosConsultas);
                }else if(semestre.isSelected()){
                    dato = (String)entradaSemestre.getSelectedItem();
                    alumnoDAO.consultasPorSemestre(dato,tablaAlumnosConsultas);
                }
                else if(carrera.isSelected()){
                    dato = (String)entradaCarrera.getSelectedItem();
                    alumnoDAO.consultasPorCarrera(dato,tablaAlumnosConsultas);
                }else if(edad.isSelected()){
                    dato = entradaEdad.getText();
                    alumnoDAO.consultasPorEdad(dato,tablaAlumnosConsultas);
                }




            }
        });
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrameConsultas.setVisible(false);
            }
        });

        return consultas;
    }

    public void actualizarTabla(){

        final String TABLA_ALUMNOS = "alumnos";//esto pede ser parametro

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/bd_escuela_2";
        String user = "root";
        String password = "chesterf51997";
        String query = "SELECT * FROM " + TABLA_ALUMNOS;

        ResultSetTableModel modelo = null;
        try {
            modelo = new ResultSetTableModel(driver, url, user, password, query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        tablaAlumnosAltas.setModel(modelo);
        tablaAlumnosBajas.setModel(modelo);
        tablaAlumnosModificaciones.setModel(modelo);
        tablaAlumnosConsultas.setModel(modelo);
    }

    public void actualizarTabla2(JTable tabla){
        final String TABLA_ALUMNOS = "alumnos";//CONSTANTE TABLA

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/bd_escuela_2";
        String user = "root";
        String password = "chesterf51997";
        String query = "SELECT * FROM " + TABLA_ALUMNOS;

        ResultSetTableModel modelo = null;
        try {
            modelo = new ResultSetTableModel(driver, url, user, password, query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        tablaAlumnosAltas.setModel(modelo);
        tablaAlumnosBajas.setModel(modelo);
        tablaAlumnosModificaciones.setModel(modelo);
        //tablaAlumnosConsultas.setModel(modelo);




    }







    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana();
            }
        });

    }



}//ventana clase
