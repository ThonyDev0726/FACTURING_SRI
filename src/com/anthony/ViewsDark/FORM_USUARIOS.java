package com.anthony.ViewsDark;

import com.anthony.Controller.*;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.componentsDark.MessageDialogDark;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.toast.Toast;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.*;

public class FORM_USUARIOS extends javax.swing.JPanel {

    /* ==============================
    OBJETOS PRINCIPALES
    ============================== */
    RoundBorder border = new RoundBorder(0);
    MainAdministradorDark admin;
    Encriptador encriptador = new Encriptador();
    PERMISOS per = new PERMISOS();
    PERMISOS_DAO daoPer = new PERMISOS_DAO();
    USUARIO usu = new USUARIO();
    USUARIO_DAO daoUsu = new USUARIO_DAO();
    EMPLEADO emp = new EMPLEADO();
    EMPLEADO_DAO daoEmp = new EMPLEADO_DAO();
    SUCURSAL suc = new SUCURSAL();
    SUCURSAL_DAO daoSuc = new SUCURSAL_DAO();
    String[] titulosEmpleados = {"#", "Sucursal", "Nombres", "Apellidos", "Cedula", "Email", "Telefono", "Direccion", "Estado"};
    DefaultTableModel dtmEmpleados = new DefaultTableModel(null, titulosEmpleados);
    String[] titulosUsuarios = {"#", "Sucursal", "Nombres", "Apellidos", "Usuario", "Clave", "Parametro", "Estado"};
    DefaultTableModel dtmUsuarios = new DefaultTableModel(null, titulosUsuarios);
    Toast panel;
    Convertidor convertidor = new Convertidor();
    String criterio, busqueda;
    static ResultSet rs = null;

    /* ==============================
    VARIABLES FECHA
    ============================== */
    LocalDate todaysDate = LocalDate.now();
    String fecha = todaysDate.toString();

    /* ==============================
    VARIABLES PRINCIPALES
    ============================== */
    private Integer ID_USUARIO;
    private Integer FK_SUCURSAL;
    private Integer FK_EMPLEADO;
    private String FK_SUCURSAL_TEXT;
    private String USU_USUARIO;
    private String USU_CLAVE;
    private String USU_PARAMETRO;
    private String USU_CREACION;
    private String USU_ESTADO;

    /* ==============================
    CONSTRUCTORES
    ============================== */
    public FORM_USUARIOS() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        tablaEmpleados();
        datosSucursal();
        datosSpinner();
        tablaUsuarios();
    }

    public FORM_USUARIOS(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        tablaEmpleados();
        datosSucursal();
        tablaUsuarios();
        datosSpinner();
    }

    private void datosSpinner() {
        cbxEstadoUsu.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"EN LINEA", "DE BAJA"}));
        cbxEstadoUsu.setSelectedIndex(-1);
        cbxParametro.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Administrador", "Cajero"}));
        cbxParametro.setSelectedIndex(-1);
    }

    private void scroll() {
        spPanel.setVerticalScrollBar(new ScrollBarCustom());
        spPanel.setHorizontalScrollBar(new ScrollBarCustom());
        spUsuarios.setVerticalScrollBar(new ScrollBarCustom());
        spUsuarios.setHorizontalScrollBar(new ScrollBarCustom());
        spEmpleados.setVerticalScrollBar(new ScrollBarCustom());
        spEmpleados.setHorizontalScrollBar(new ScrollBarCustom());
        spUsuarios.getViewport().setOpaque(false);
        spEmpleados.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        spUsuarios.setVerticalScrollBar(new ScrollBarCustom());
        spUsuarios.setBorder(border);
        spEmpleados.setVerticalScrollBar(new ScrollBarCustom());
        spEmpleados.setBorder(border);
    }

    private void ocultar() {
        tabbedPanel.setSelectedComponent(panelUsuarios);
        panelBuscar.setVisible(false);
        panelForm.setVisible(false);
        btnEliminar.setVisible(false);
        btnActualizar.setVisible(false);
        cbxEstadoUsu.setVisible(false);
    }

    public void autoajustarColumnas(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    /* ================================== 
    BASE DE DATOS
    ================================== */
    public void datosSucursal() {
        List<SUCURSAL> listCh = daoSuc.listar();
        Iterator<SUCURSAL> iterCh = listCh.iterator();
        suc = null;
        while (iterCh.hasNext()) {
            suc = iterCh.next();
            cbxSucursal.addItem(suc.getID_SUCURSAL() + ".- " + suc.getSUC_NOMBRE());
        }
        cbxSucursal.setSelectedIndex(-1);
    }

    public void tablaEmpleados() {
        dtmEmpleados = new DefaultTableModel(null, titulosEmpleados);
        String fila[] = new String[9];
        List<EMPLEADO> listCh = daoEmp.listar();
        Iterator<EMPLEADO> iterCh = listCh.iterator();
        emp = null;
        while (iterCh.hasNext()) {
            emp = iterCh.next();
            fila[0] = String.valueOf(emp.getID_EMPLEADO());
            fila[1] = emp.getSUC_NOMBRE();
            fila[2] = emp.getEMP_NOMBRES();
            fila[3] = emp.getEMP_APELLIDOS();
            fila[4] = emp.getEMP_CEDULA();
            fila[5] = emp.getEMP_EMAIL();
            fila[6] = emp.getEMP_TELEFONO();
            fila[7] = emp.getEMP_DIRECCION();
            fila[8] = emp.getEMP_ESTADO();
            dtmEmpleados.addRow(fila);
        }
        tDatosProveedores.setModel(dtmEmpleados);
        autoajustarColumnas(tDatosProveedores);
    }

    public void tablaUsuarios() {
        dtmUsuarios = new DefaultTableModel(null, titulosUsuarios);
        String fila[] = new String[8];
        List<USUARIO> listUsu = daoUsu.listar();
        Iterator<USUARIO> iterUsu = listUsu.iterator();
        usu = null;
        while (iterUsu.hasNext()) {
            usu = iterUsu.next();
            fila[0] = String.valueOf(usu.getID_USUARIO());
            fila[1] = usu.getSUC_NOMBRE();
            fila[2] = usu.getEMP_NOMBRES();
            fila[3] = usu.getEMP_APELLIDOS();
            fila[4] = usu.getUSU_USUARIO();
            fila[5] = usu.getUSU_CLAVE();
            fila[6] = usu.getUSU_PARAMETRO();
            fila[7] = usu.getUSU_ESTADO();
            dtmUsuarios.addRow(fila);
        }
        tDatosUsuarios.setModel(dtmUsuarios);
        autoajustarColumnas(tDatosUsuarios);
    }

    public void buscarEmpleado() {
        busqueda = txtBusquedaEmpleado.getText();
        if (rdtbNombres.isSelected()) {
            criterio = "EMP_NOMBRES";
        } else if (rdtbApellidos.isSelected()) {
            criterio = "EMP_APELLIDOS";
        } else if (rdtbCedula.isSelected()) {
            criterio = "EMP_CEDULA";
        } else if (rdtbDireccion.isSelected()) {
            criterio = "EMP_DIRECCION";
        } else if (rdtbEstado.isSelected()) {
            criterio = "EMP_ESTADO";
        }
        try {
            rs = daoEmp.BUSCAR_EMPLEADO(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[9];
            //Eliminar los datos que se encuentran en la tabla
            int f, i;
            f = dtmEmpleados.getRowCount();
            if (f > 0) {
                for (i = 0; i < f; i++) {
                    dtmEmpleados.removeRow(0);
                }
            }
            //Llenar la tabla con la informacion de acuerdo al criterio y el texto de la busqueda
            while (rs.next()) {
                Datos[0] = (String) rs.getString(1);
                Datos[1] = (String) rs.getString(2);
                Datos[2] = (String) rs.getString(3);
                Datos[3] = (String) rs.getString(4);
                Datos[4] = (String) rs.getString(5);
                Datos[5] = (String) rs.getString(6);
                Datos[6] = (String) rs.getString(7);
                Datos[7] = (String) rs.getString(8);
                Datos[8] = (String) rs.getString(9);
                dtmEmpleados.addRow(Datos);
                encuentra = true;
            }
            if (encuentra == false) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Empleado no encontrado!!");
                panel.showNotification();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en el try");
        }
        tDatosProveedores.setModel(dtmEmpleados);
        autoajustarColumnas(tDatosProveedores);
//        contarRegistros();
    }

    public void cargarDatosEmpleados() {
        int fila;
        fila = tDatosProveedores.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmEmpleados = (DefaultTableModel) tDatosProveedores.getModel();
            lblIdEmpleado.setText((String) dtmEmpleados.getValueAt(fila, 0));
            FK_SUCURSAL_TEXT = (String) dtmEmpleados.getValueAt(fila, 1);
            FK_SUCURSAL = Integer.parseInt(daoSuc.id_sucursal(FK_SUCURSAL_TEXT));
            FK_SUCURSAL_TEXT = FK_SUCURSAL + ".- " + (String) dtmEmpleados.getValueAt(fila, 1);
            cbxSucursal.setSelectedItem(FK_SUCURSAL_TEXT);
            txtEmpNombres.setText((String) dtmEmpleados.getValueAt(fila, 2));
            txtEmpApellidos.setText((String) dtmEmpleados.getValueAt(fila, 3));
            txtUsuario.setText((String) dtmEmpleados.getValueAt(fila, 4));
        }
    }

    private void limpiarFormulario() {
        txtEmpNombres.setText("");
        txtEmpApellidos.setText("");
        txtUsuario.setText("");
        txtClave.setText("");
        cbxSucursal.setSelectedIndex(-1);
        cbxEstadoUsu.setSelectedIndex(-1);
        cbxParametro.setSelectedIndex(-1);
    }

    public void cargarDatosUsuarios() {
        int fila;

        fila = tDatosUsuarios.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmUsuarios = (DefaultTableModel) tDatosUsuarios.getModel();
            lblIdUsuario.setText((String) dtmUsuarios.getValueAt(fila, 0));
            FK_SUCURSAL_TEXT = (String) dtmUsuarios.getValueAt(fila, 1);
            FK_SUCURSAL = Integer.parseInt(daoSuc.id_sucursal(FK_SUCURSAL_TEXT));
            FK_SUCURSAL_TEXT = FK_SUCURSAL + ".- " + (String) dtmUsuarios.getValueAt(fila, 1);
            cbxSucursal.setSelectedItem(FK_SUCURSAL_TEXT);
            txtEmpNombres.setText((String) dtmUsuarios.getValueAt(fila, 2));
            txtEmpApellidos.setText((String) dtmUsuarios.getValueAt(fila, 3));
            txtUsuario.setText((String) dtmUsuarios.getValueAt(fila, 4));
            txtClave.setText(encriptador.desencriptar((String) dtmUsuarios.getValueAt(fila, 5)));
            cbxParametro.setSelectedItem((String) dtmUsuarios.getValueAt(fila, 6));
            cbxEstadoUsu.setSelectedItem((String) dtmUsuarios.getValueAt(fila, 7));
        }
    }

    public void buscarUsuario() {
        busqueda = txtBusqueda.getText();
        if (rdbtnUsuario.isSelected()) {
            criterio = "USU_USUARIO";
        } else if (rdbtnCargoUsuario.isSelected()) {
            criterio = "USU_PARAMETRO";
        } else if (rdbtnEstadoUsuario.isSelected()) {
            criterio = "USU_ESTADO";
        }
        try {
            rs = daoUsu.BUSCAR_USUARIO(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[8];
            //Eliminar los datos que se encuentran en la tabla
            int f, i;
            f = dtmUsuarios.getRowCount();
            if (f > 0) {
                for (i = 0; i < f; i++) {
                    dtmUsuarios.removeRow(0);
                }
            }
            //Llenar la tabla con la informacion de acuerdo al criterio y el texto de la busqueda
            while (rs.next()) {
                Datos[0] = (String) rs.getString(1);
                Datos[1] = (String) rs.getString(2);
                Datos[2] = (String) rs.getString(3);
                Datos[3] = (String) rs.getString(4);
                Datos[4] = (String) rs.getString(5);
                Datos[5] = (String) rs.getString(6);
                Datos[6] = (String) rs.getString(7);
                Datos[7] = (String) rs.getString(8);
                dtmUsuarios.addRow(Datos);
                encuentra = true;
            }
            if (encuentra == false) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Empleado no encontrado!!");
                panel.showNotification();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en el try");
        }
        tDatosUsuarios.setModel(dtmUsuarios);
        autoajustarColumnas(tDatosUsuarios);
//        contarRegistros();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new com.anthony.swing.Button();
        btnNuevoRegistro = new com.anthony.swing.Button();
        btnBuscar1 = new com.anthony.swing.Button();
        spPanel = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        tabbedPanel = new com.anthony.swing.TabbedPane();
        panelUsuarios = new javax.swing.JPanel();
        spUsuarios = new javax.swing.JScrollPane();
        tDatosUsuarios = new rojeru_san.complementos.TableMetro();
        panelEmpleados = new javax.swing.JPanel();
        spEmpleados = new javax.swing.JScrollPane();
        tDatosProveedores = new rojeru_san.complementos.TableMetro();
        txtBusquedaEmpleado = new textfield.TextField();
        rdtbNombres = new com.anthony.swing.RadioButton();
        jLabel18 = new javax.swing.JLabel();
        rdtbApellidos = new com.anthony.swing.RadioButton();
        rdtbCedula = new com.anthony.swing.RadioButton();
        rdtbDireccion = new com.anthony.swing.RadioButton();
        rdtbEstado = new com.anthony.swing.RadioButton();
        jLabel12 = new javax.swing.JLabel();
        lblTotalRegistros = new javax.swing.JLabel();
        panelBuscar = new com.anthony.swing.RoundPanel();
        txtBusqueda = new textfield.TextField();
        jLabel17 = new javax.swing.JLabel();
        rdbtnUsuario = new com.anthony.swing.RadioButton();
        rdbtnCargoUsuario = new com.anthony.swing.RadioButton();
        rdbtnEstadoUsuario = new com.anthony.swing.RadioButton();
        jLabel13 = new javax.swing.JLabel();
        lblTotalRegistros1 = new javax.swing.JLabel();
        panelForm = new com.anthony.swing.RoundPanel();
        txtEmpNombres = new textfield.TextField();
        txtEmpApellidos = new textfield.TextField();
        txtUsuario = new textfield.TextField();
        btnGuardarUsu = new com.anthony.swing.Button();
        btnActualizar = new com.anthony.swing.Button();
        btnCancelarSuc1 = new com.anthony.swing.Button();
        cbxSucursal = new com.anthony.swing.Combobox();
        cbxEstadoUsu = new com.anthony.swing.Combobox();
        txtClave = new textfield.PasswordField();
        cbxParametro = new com.anthony.swing.Combobox();
        lblIdEmpleado = new javax.swing.JLabel();
        lblIdUsuario = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("USUARIOS");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(55, 29, 29));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoEliminar.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevoRegistro.setBackground(new java.awt.Color(37, 47, 33));
        btnNuevoRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRegistroActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(0, 102, 153));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        spPanel.setBorder(null);
        spPanel.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(22, 23, 23));

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));
        roundPanel1.setLayout(new java.awt.BorderLayout());

        tabbedPanel.setBackground(new java.awt.Color(32, 32, 32));
        tabbedPanel.setForeground(new java.awt.Color(63, 81, 102));

        panelUsuarios.setBackground(new java.awt.Color(32, 32, 32));
        panelUsuarios.setLayout(new java.awt.BorderLayout());

        spUsuarios.setBackground(new java.awt.Color(32, 32, 32));
        spUsuarios.setForeground(new java.awt.Color(32, 32, 32));
        spUsuarios.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        spUsuarios.setFocusable(false);
        spUsuarios.setOpaque(false);

        tDatosUsuarios = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosUsuarios.setBackground(new java.awt.Color(32, 32, 32));
        tDatosUsuarios.setForeground(new java.awt.Color(32, 32, 32));
        tDatosUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosUsuarios.setAltoHead(30);
        tDatosUsuarios.setAutoscrolls(false);
        tDatosUsuarios.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosUsuarios.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosUsuarios.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosUsuarios.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosUsuarios.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosUsuarios.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosUsuarios.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosUsuarios.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosUsuarios.setFocusable(false);
        tDatosUsuarios.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosUsuarios.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosUsuarios.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosUsuarios.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosUsuarios.setGrosorBordeFilas(0);
        tDatosUsuarios.setGrosorBordeHead(0);
        tDatosUsuarios.setRowHeight(30);
        tDatosUsuarios.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosUsuariosMouseClicked(evt);
            }
        });
        spUsuarios.setViewportView(tDatosUsuarios);

        panelUsuarios.add(spUsuarios, java.awt.BorderLayout.CENTER);

        tabbedPanel.addTab("USUARIOS", panelUsuarios);

        panelEmpleados.setBackground(new java.awt.Color(32, 32, 32));

        spEmpleados.setBackground(new java.awt.Color(32, 32, 32));
        spEmpleados.setBorder(null);
        spEmpleados.setForeground(new java.awt.Color(32, 32, 32));
        spEmpleados.setFocusable(false);
        spEmpleados.setOpaque(false);

        tDatosProveedores = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosProveedores.setBackground(new java.awt.Color(32, 32, 32));
        tDatosProveedores.setForeground(new java.awt.Color(32, 32, 32));
        tDatosProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ejemplo 1", "Ejemplo 2", "Ejemplo 3", "Ejemplo 4"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosProveedores.setAltoHead(30);
        tDatosProveedores.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosProveedores.setColorBordeHead(new java.awt.Color(32, 32, 32));
        tDatosProveedores.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosProveedores.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosProveedores.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosProveedores.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosProveedores.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosProveedores.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosProveedores.setFocusable(false);
        tDatosProveedores.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProveedores.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProveedores.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosProveedores.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosProveedores.setGrosorBordeFilas(0);
        tDatosProveedores.setGrosorBordeHead(0);
        tDatosProveedores.setRowHeight(30);
        tDatosProveedores.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosProveedoresMouseClicked(evt);
            }
        });
        spEmpleados.setViewportView(tDatosProveedores);

        txtBusquedaEmpleado.setBackground(new java.awt.Color(32, 32, 32));
        txtBusquedaEmpleado.setForeground(new java.awt.Color(0, 153, 204));
        txtBusquedaEmpleado.setLabelText("Escribe la sucursal que deseas buscar....");
        txtBusquedaEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaEmpleadoKeyReleased(evt);
            }
        });

        rdtbNombres.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtbNombres);
        rdtbNombres.setForeground(new java.awt.Color(63, 81, 102));
        rdtbNombres.setText("Nombres");
        rdtbNombres.setFocusPainted(false);
        rdtbNombres.setRequestFocusEnabled(false);

        jLabel18.setForeground(new java.awt.Color(96, 96, 96));
        jLabel18.setText("Buscar por:");

        rdtbApellidos.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtbApellidos);
        rdtbApellidos.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos.setText("Apellidos");
        rdtbApellidos.setFocusPainted(false);
        rdtbApellidos.setRequestFocusEnabled(false);

        rdtbCedula.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtbCedula);
        rdtbCedula.setForeground(new java.awt.Color(63, 81, 102));
        rdtbCedula.setText("Cedula");
        rdtbCedula.setFocusPainted(false);
        rdtbCedula.setRequestFocusEnabled(false);

        rdtbDireccion.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtbDireccion);
        rdtbDireccion.setForeground(new java.awt.Color(63, 81, 102));
        rdtbDireccion.setText("Direccion");
        rdtbDireccion.setFocusPainted(false);
        rdtbDireccion.setRequestFocusEnabled(false);

        rdtbEstado.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtbEstado);
        rdtbEstado.setForeground(new java.awt.Color(63, 81, 102));
        rdtbEstado.setText("Estado");
        rdtbEstado.setFocusPainted(false);
        rdtbEstado.setRequestFocusEnabled(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(96, 96, 96));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL DE REGISTROS:");

        lblTotalRegistros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelEmpleadosLayout = new javax.swing.GroupLayout(panelEmpleados);
        panelEmpleados.setLayout(panelEmpleadosLayout);
        panelEmpleadosLayout.setHorizontalGroup(
            panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
            .addGroup(panelEmpleadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusquedaEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGroup(panelEmpleadosLayout.createSequentialGroup()
                        .addComponent(rdtbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEmpleadosLayout.setVerticalGroup(
            panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtBusquedaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEmpleadosLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdtbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        tabbedPanel.addTab("EMPLEADOS", panelEmpleados);

        roundPanel1.add(tabbedPanel, java.awt.BorderLayout.CENTER);

        panelBuscar.setBackground(new java.awt.Color(32, 32, 32));

        txtBusqueda.setBackground(new java.awt.Color(32, 32, 32));
        txtBusqueda.setForeground(new java.awt.Color(0, 153, 204));
        txtBusqueda.setLabelText("Escribe la sucursal que deseas buscar....");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(96, 96, 96));
        jLabel17.setText("Buscar por:");

        rdbtnUsuario.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdbtnUsuario);
        rdbtnUsuario.setForeground(new java.awt.Color(63, 81, 102));
        rdbtnUsuario.setText("USUARIO");
        rdbtnUsuario.setFocusPainted(false);
        rdbtnUsuario.setRequestFocusEnabled(false);

        rdbtnCargoUsuario.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdbtnCargoUsuario);
        rdbtnCargoUsuario.setForeground(new java.awt.Color(63, 81, 102));
        rdbtnCargoUsuario.setText("CARGO");
        rdbtnCargoUsuario.setFocusPainted(false);
        rdbtnCargoUsuario.setRequestFocusEnabled(false);

        rdbtnEstadoUsuario.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdbtnEstadoUsuario);
        rdbtnEstadoUsuario.setForeground(new java.awt.Color(63, 81, 102));
        rdbtnEstadoUsuario.setText("ESTADO");
        rdbtnEstadoUsuario.setFocusPainted(false);
        rdbtnEstadoUsuario.setRequestFocusEnabled(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(96, 96, 96));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("TOTAL DE REGISTROS:");

        lblTotalRegistros1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros1.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(rdbtnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnCargoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbtnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnCargoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        panelForm.setBackground(new java.awt.Color(32, 32, 32));

        txtEmpNombres.setEditable(false);
        txtEmpNombres.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpNombres.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpNombres.setLabelText("Nombre del empleado");

        txtEmpApellidos.setEditable(false);
        txtEmpApellidos.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpApellidos.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpApellidos.setLabelText("Apellidos del empleado");

        txtUsuario.setEditable(false);
        txtUsuario.setBackground(new java.awt.Color(32, 32, 32));
        txtUsuario.setForeground(new java.awt.Color(0, 153, 204));
        txtUsuario.setLabelText("Usuario");

        btnGuardarUsu.setBackground(new java.awt.Color(37, 47, 33));
        btnGuardarUsu.setForeground(new java.awt.Color(204, 204, 204));
        btnGuardarUsu.setText("GUARDAR");
        btnGuardarUsu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(55, 50, 40));
        btnActualizar.setForeground(new java.awt.Color(204, 204, 204));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelarSuc1.setBackground(new java.awt.Color(55, 29, 29));
        btnCancelarSuc1.setForeground(new java.awt.Color(204, 204, 204));
        btnCancelarSuc1.setText("CANCELAR");
        btnCancelarSuc1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarSuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSuc1ActionPerformed(evt);
            }
        });

        cbxSucursal.setBackground(new java.awt.Color(32, 32, 32));
        cbxSucursal.setForeground(new java.awt.Color(0, 153, 204));
        cbxSucursal.setLabeText("Sucursal");

        cbxEstadoUsu.setBackground(new java.awt.Color(32, 32, 32));
        cbxEstadoUsu.setForeground(new java.awt.Color(0, 153, 204));
        cbxEstadoUsu.setLabeText("Estado");

        txtClave.setBackground(new java.awt.Color(32, 32, 32));
        txtClave.setForeground(new java.awt.Color(0, 153, 204));
        txtClave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtClave.setLabelText("Contraseña");
        txtClave.setShowAndHide(true);
        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });

        cbxParametro.setBackground(new java.awt.Color(32, 32, 32));
        cbxParametro.setForeground(new java.awt.Color(0, 153, 204));
        cbxParametro.setLabeText("Cargo");

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(btnGuardarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmpNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxEstadoUsu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxParametro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmpApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEstadoUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxEstadoUsu, cbxSucursal, txtEmpApellidos, txtEmpNombres, txtUsuario});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        spPanel.setViewportView(jPanel3);

        lblIdEmpleado.setForeground(new java.awt.Color(22, 23, 23));

        lblIdUsuario.setForeground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addComponent(spPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPanel)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ocultar();
        tablaEmpleados();
        tablaUsuarios();
        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Se actualizo la base de datos!!");
        panel.showNotification();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ID_USUARIO = Integer.parseInt(lblIdEmpleado.getText());
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¡¡ELIMINAR REGISTRO!!", "¿ Desea eliminar al usuario ?");
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoUsu.delete(ID_USUARIO) == "El usuario fue eliminado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El usuario fue eliminada con exito!!");
                    panel.showNotification();
                    tablaEmpleados();
                    limpiarFormulario();
                } else if (daoUsu.delete(ID_USUARIO) == "Error al eliminar el usuario!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo eliminar al usuario!!");
                    panel.showNotification();
                    tablaEmpleados();
                    limpiarFormulario();
                }
            } else if (obj.getMessageType() == MessageDialogDark.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
                limpiarFormulario();
            }
        } catch (Exception ex) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion, favor corregirlos!!");
            panel.showNotification();
            limpiarFormulario();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRegistroActionPerformed
        tabbedPanel.setSelectedComponent(panelEmpleados);
        panelForm.setVisible(true);
        btnGuardarUsu.setVisible(true);
        panelBuscar.setVisible(false);
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoRegistroActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        panelBuscar.setVisible(true);
        panelForm.setVisible(false);
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnCancelarSuc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSuc1ActionPerformed
        // TODO add your handling code here:
        ocultar();
        limpiarFormulario();
    }//GEN-LAST:event_btnCancelarSuc1ActionPerformed

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void tDatosUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosUsuariosMouseClicked
        cargarDatosUsuarios();
        btnGuardarUsu.setVisible(false);
        btnEliminar.setVisible(true);
        if (evt.getClickCount() == 2) {
            panelForm.setVisible(true);
            panelBuscar.setVisible(false);
            btnActualizar.setVisible(true);
            cbxEstadoUsu.setVisible(true);
        }
    }//GEN-LAST:event_tDatosUsuariosMouseClicked

    private void txtBusquedaEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaEmpleadoKeyReleased
        buscarEmpleado();
    }//GEN-LAST:event_txtBusquedaEmpleadoKeyReleased

    private void tDatosProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosProveedoresMouseClicked
        limpiarFormulario();
        cargarDatosEmpleados();
    }//GEN-LAST:event_tDatosProveedoresMouseClicked

    private void btnGuardarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuActionPerformed
        FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
        FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
        FK_EMPLEADO = Integer.parseInt(lblIdEmpleado.getText());
        USU_USUARIO = txtUsuario.getText();
        USU_CLAVE = txtClave.getText();
        USU_PARAMETRO = cbxParametro.getSelectedItem().toString();;
        USU_CREACION = fecha;
        USU_ESTADO = "EN LINEA";
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas crear al usuario ?", "");
            usu.setFK_SUCURSAL(FK_SUCURSAL);
            usu.setFK_EMPLEADO(FK_EMPLEADO);
            usu.setUSU_USUARIO(USU_USUARIO);
            usu.setUSU_CLAVE(USU_CLAVE);
            usu.setUSU_PARAMETRO(USU_PARAMETRO);
            usu.setUSU_ESTADO(USU_ESTADO);
            usu.setUSU_CREACION(USU_CREACION);
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoUsu.add(usu) == "El usuario fue creado con exito!") {
                    Integer ultimo_id_usuario = Integer.parseInt(daoUsu.ULTIMO_ID_USUARIO());
                    if (daoPer.add(ultimo_id_usuario) == "El permiso fue creado con exito!") {
                        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El usuario y sus permisos fueron creado con exito!");
                        panel.showNotification();
                        tablaEmpleados();
                        tablaUsuarios();
                        limpiarFormulario();
                        ocultar();
                    } else if (daoPer.add(ultimo_id_usuario) == "El permiso no fue creado!") {

                    }

                } else if (daoUsu.add(usu) == "El usuario no fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo crear al usuario!!");
                    panel.showNotification();
                    tablaUsuarios();
                    tablaEmpleados();
                    limpiarFormulario();
                    ocultar();
                }
            } else if (obj.getMessageType() == MessageDialogDark.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
                limpiarFormulario();
                ocultar();
            }
        } catch (Exception ex) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion!!");
            panel.showNotification();
            limpiarFormulario();
            ocultar();
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }//GEN-LAST:event_btnGuardarUsuActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        buscarUsuario();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ID_USUARIO = Integer.parseInt(lblIdUsuario.getText());
        FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
        FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
        USU_CLAVE = txtClave.getText();
        USU_PARAMETRO = cbxParametro.getSelectedItem().toString();
        USU_ESTADO = cbxEstadoUsu.getSelectedItem().toString();
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas actualizar al usuario ?", "");
            usu.setID_USUARIO(ID_USUARIO);
            usu.setFK_SUCURSAL(FK_SUCURSAL);
            usu.setUSU_CLAVE(USU_CLAVE);
            usu.setUSU_PARAMETRO(USU_PARAMETRO);
            usu.setUSU_ESTADO(USU_ESTADO);
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoUsu.update(usu) == "El usuario fue actualizado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El usuario fue actualizado con exito!!");
                    panel.showNotification();
                    tablaEmpleados();
                    tablaUsuarios();
                    limpiarFormulario();
                    ocultar();
                } else if (daoUsu.update(usu) == "El usuario no fue actualizado con exito!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar al usuario!!");
                    panel.showNotification();
                    tablaUsuarios();
                    tablaEmpleados();
                    limpiarFormulario();
                    ocultar();
                }
            } else if (obj.getMessageType() == MessageDialogDark.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
                limpiarFormulario();
                ocultar();
            }
        } catch (Exception ex) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion!!");
            panel.showNotification();
            limpiarFormulario();
            ocultar();
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnActualizar;
    private com.anthony.swing.Button btnBuscar1;
    private com.anthony.swing.Button btnCancelarSuc1;
    private com.anthony.swing.Button btnEliminar;
    private com.anthony.swing.Button btnGuardarUsu;
    private com.anthony.swing.Button btnNuevoRegistro;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.anthony.swing.Combobox cbxEstadoUsu;
    private com.anthony.swing.Combobox cbxParametro;
    private com.anthony.swing.Combobox cbxSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblIdEmpleado;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JLabel lblTotalRegistros1;
    private com.anthony.swing.RoundPanel panelBuscar;
    private javax.swing.JPanel panelEmpleados;
    private com.anthony.swing.RoundPanel panelForm;
    private javax.swing.JPanel panelUsuarios;
    private com.anthony.swing.RadioButton rdbtnCargoUsuario;
    private com.anthony.swing.RadioButton rdbtnEstadoUsuario;
    private com.anthony.swing.RadioButton rdbtnUsuario;
    private com.anthony.swing.RadioButton rdtbApellidos;
    private com.anthony.swing.RadioButton rdtbCedula;
    private com.anthony.swing.RadioButton rdtbDireccion;
    private com.anthony.swing.RadioButton rdtbEstado;
    private com.anthony.swing.RadioButton rdtbNombres;
    private com.anthony.swing.RoundPanel roundPanel1;
    private javax.swing.JScrollPane spEmpleados;
    private javax.swing.JScrollPane spPanel;
    private javax.swing.JScrollPane spUsuarios;
    private rojeru_san.complementos.TableMetro tDatosProveedores;
    private rojeru_san.complementos.TableMetro tDatosUsuarios;
    private com.anthony.swing.TabbedPane tabbedPanel;
    private textfield.TextField txtBusqueda;
    private textfield.TextField txtBusquedaEmpleado;
    private textfield.PasswordField txtClave;
    private textfield.TextField txtEmpApellidos;
    private textfield.TextField txtEmpNombres;
    private textfield.TextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
