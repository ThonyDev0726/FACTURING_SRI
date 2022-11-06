package com.anthony.ViewsDark;

import com.anthony.Controller.*;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.dialog.MessageDialogDark;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.toast.Toast;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.*;

public class FORM_EMPLEADOS extends javax.swing.JPanel {

    /* ==============================
    OBJETOS PRINCIPALES
    ============================== */
    String[] titulosEmpleados = {"#", "Sucursal", "Nombres", "Apellidos", "Cedula", "Email", "Telefono", "Direccion", "Estado"};
    DefaultTableModel dtmEmpleados = new DefaultTableModel(null, titulosEmpleados);
    Convertidor convertidor = new Convertidor();
    RoundBorder border = new RoundBorder(0);
    EMPLEADO_DAO daoEmp = new EMPLEADO_DAO();
    SUCURSAL_DAO daoSuc = new SUCURSAL_DAO();
    EMPLEADO emp = new EMPLEADO();
    SUCURSAL suc = new SUCURSAL();
    MainAdministradorDark admin;
    static ResultSet rs = null;
    String criterio, busqueda;
    USUARIO usu;
    Toast panel;

    /* ==============================
    VARIABLES FECHA
    ============================== */
    LocalDate todaysDate = LocalDate.now();
    String fecha = todaysDate.toString();
    
    /* ==============================
    VARIABLES MODELO EMPLEADO
    ============================== */
    private Integer ID_EMPLEADO;
    private String FK_SUCURSAL_TEXT;
    private Integer FK_SUCURSAL;
    private String EMP_NOMBRES;
    private String EMP_APELLIDOS;
    private String EMP_CEDULA;
    private String EMP_EMAIL;
    private String EMP_TELEFONO;
    private String EMP_DIRECCION;
    private String EMP_ESTADO;
    private String EMP_CREACION;

    /*==============================
    CONSTRUCOTES
    ==============================*/
    public FORM_EMPLEADOS() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        tablaEmpleados();
        datosSucursal();
        datosSpinner();        
    }

    public FORM_EMPLEADOS(MainAdministradorDark admin, USUARIO usu) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        tablaEmpleados();
        datosSucursal();
        datosSpinner();
    }

    /*==============================
    INTERFACES 
    ==============================*/
    private void limpiarFormulario() {
        txtEmpNombres.setText("");
        txtEmpApellidos.setText("");
        txtEmpCedula.setText("");
        txtEmpEmail.setText("");
        txtEmpTelefono.setText("");
        txtEmpDireccion.setText("");
    }

    private void datosSpinner() {
        cbxEstadoEmp.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"EN LINEA", "DE BAJA"}));
        cbxEstadoEmp.setSelectedIndex(-1);
    }

    private void scroll() {
        spPrincipal.setVerticalScrollBar(new ScrollBarCustom());
        spPrincipal.setHorizontalScrollBar(new ScrollBarCustom());
        spPrincipal.setVerticalScrollBar(new ScrollBarCustom());
        spPrincipal.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        ScrollBarCustom sbh = new ScrollBarCustom();
        spPrincipal.setVerticalScrollBar(new ScrollBarCustom());
        spPrincipal.setBorder(border);
        spPrincipal.setVerticalScrollBar(new ScrollBarCustom());
    }

    private void ocultar() {
        btnGuardarEmp.setVisible(true);
        panelBuscar.setVisible(false);
        panelForm.setVisible(false);
        btnEliminar.setVisible(false);
        btnActualizarEmp.setVisible(false);
        cbxEstadoEmp.setVisible(false);
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
        tDatosEmpleados.setModel(dtmEmpleados);
        autoajustarColumnas(tDatosEmpleados);
    }

    public void cargarDatosEmpleados() {
        int fila;
        fila = tDatosEmpleados.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmEmpleados = (DefaultTableModel) tDatosEmpleados.getModel();
            lblIdEmpleado.setText((String) dtmEmpleados.getValueAt(fila, 0));
            FK_SUCURSAL_TEXT = (String) dtmEmpleados.getValueAt(fila, 1);
            FK_SUCURSAL = Integer.parseInt(daoSuc.id_sucursal(FK_SUCURSAL_TEXT));
            FK_SUCURSAL_TEXT = FK_SUCURSAL + ".- " + (String) dtmEmpleados.getValueAt(fila, 1);
            cbxSucursal.setSelectedItem(FK_SUCURSAL_TEXT);
            txtEmpNombres.setText((String) dtmEmpleados.getValueAt(fila, 2));
            txtEmpApellidos.setText((String) dtmEmpleados.getValueAt(fila, 3));
            txtEmpCedula.setText((String) dtmEmpleados.getValueAt(fila, 4));
            txtEmpEmail.setText((String) dtmEmpleados.getValueAt(fila, 5));
            txtEmpTelefono.setText((String) dtmEmpleados.getValueAt(fila, 6));
            txtEmpDireccion.setText((String) dtmEmpleados.getValueAt(fila, 7));
            cbxEstadoEmp.setSelectedItem((String) dtmEmpleados.getValueAt(fila, 8));
        }
    }

    public void buscarEmpleado() {
        busqueda = txtBusqueda.getText();
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
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Proveedor no encontrado!!");
                panel.showNotification();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en el try");
        }
        tDatosEmpleados.setModel(dtmEmpleados);
        autoajustarColumnas(tDatosEmpleados);
//        contarRegistros();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblRecargar = new javax.swing.JLabel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        spPrincipal = new javax.swing.JScrollPane();
        tDatosEmpleados = new rojeru_san.complementos.TableMetro();
        btnEliminar = new com.anthony.swing.Button();
        btnNuevoRegistro = new com.anthony.swing.Button();
        btnBuscar1 = new com.anthony.swing.Button();
        panelBuscar = new com.anthony.swing.RoundPanel();
        txtBusqueda = new textfield.TextField();
        jLabel17 = new javax.swing.JLabel();
        rdtbNombres = new com.anthony.swing.RadioButton();
        rdtbApellidos = new com.anthony.swing.RadioButton();
        rdtbCedula = new com.anthony.swing.RadioButton();
        rdtbDireccion = new com.anthony.swing.RadioButton();
        rdtbEstado = new com.anthony.swing.RadioButton();
        lblTotalRegistros = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelForm = new com.anthony.swing.RoundPanel();
        btnGuardarEmp = new com.anthony.swing.Button();
        btnActualizarEmp = new com.anthony.swing.Button();
        btnCancelar = new com.anthony.swing.Button();
        txtEmpNombres = new textfield.TextField();
        txtEmpApellidos = new textfield.TextField();
        txtEmpTelefono = new textfield.TextField();
        txtEmpCedula = new textfield.TextField();
        txtEmpDireccion = new textfield.TextField();
        txtEmpEmail = new textfield.TextField();
        cbxEstadoEmp = new com.anthony.swing.Combobox();
        cbxSucursal = new com.anthony.swing.Combobox();
        lblIdEmpleado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        lblRecargar.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblRecargar.setForeground(new java.awt.Color(63, 81, 102));
        lblRecargar.setText("EMPLEADOS");
        lblRecargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRecargarMouseClicked(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));

        spPrincipal.setBackground(new java.awt.Color(32, 32, 32));
        spPrincipal.setBorder(null);
        spPrincipal.setForeground(new java.awt.Color(32, 32, 32));
        spPrincipal.setFocusable(false);
        spPrincipal.setOpaque(false);

        tDatosEmpleados = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosEmpleados.setBackground(new java.awt.Color(32, 32, 32));
        tDatosEmpleados.setForeground(new java.awt.Color(32, 32, 32));
        tDatosEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosEmpleados.setAltoHead(30);
        tDatosEmpleados.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosEmpleados.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosEmpleados.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosEmpleados.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosEmpleados.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosEmpleados.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosEmpleados.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosEmpleados.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosEmpleados.setFocusable(false);
        tDatosEmpleados.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosEmpleados.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosEmpleados.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosEmpleados.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosEmpleados.setGrosorBordeFilas(0);
        tDatosEmpleados.setGrosorBordeHead(0);
        tDatosEmpleados.setOpaque(false);
        tDatosEmpleados.setRowHeight(30);
        tDatosEmpleados.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosEmpleadosMouseClicked(evt);
            }
        });
        spPrincipal.setViewportView(tDatosEmpleados);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spPrincipal)
                .addGap(3, 3, 3))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        btnEliminar.setBackground(new java.awt.Color(55, 29, 29));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoEliminar.png"))); // NOI18N
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevoRegistro.setBackground(new java.awt.Color(37, 47, 33));
        btnNuevoRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        btnNuevoRegistro.setMnemonic('n');
        btnNuevoRegistro.setFocusPainted(false);
        btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRegistroActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(0, 102, 153));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        btnBuscar1.setMnemonic('b');
        btnBuscar1.setFocusPainted(false);
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        panelBuscar.setBackground(new java.awt.Color(32, 32, 32));

        txtBusqueda.setBackground(new java.awt.Color(32, 32, 32));
        txtBusqueda.setLabelText("Escribe la sucursal que deseas buscar....");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(96, 96, 96));
        jLabel17.setText("Buscar por:");

        rdtbNombres.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbNombres);
        rdtbNombres.setForeground(new java.awt.Color(63, 81, 102));
        rdtbNombres.setText("Nombres");
        rdtbNombres.setFocusPainted(false);
        rdtbNombres.setRequestFocusEnabled(false);

        rdtbApellidos.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbApellidos);
        rdtbApellidos.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos.setText("Apellidos");
        rdtbApellidos.setFocusPainted(false);
        rdtbApellidos.setRequestFocusEnabled(false);

        rdtbCedula.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbCedula);
        rdtbCedula.setForeground(new java.awt.Color(63, 81, 102));
        rdtbCedula.setText("Cedula");
        rdtbCedula.setFocusPainted(false);
        rdtbCedula.setRequestFocusEnabled(false);

        rdtbDireccion.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbDireccion);
        rdtbDireccion.setForeground(new java.awt.Color(63, 81, 102));
        rdtbDireccion.setText("Direccion");
        rdtbDireccion.setFocusPainted(false);
        rdtbDireccion.setRequestFocusEnabled(false);

        rdtbEstado.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbEstado);
        rdtbEstado.setForeground(new java.awt.Color(63, 81, 102));
        rdtbEstado.setText("Estado");
        rdtbEstado.setFocusPainted(false);
        rdtbEstado.setRequestFocusEnabled(false);

        lblTotalRegistros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(96, 96, 96));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL DE REGISTROS:");

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
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
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdtbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        panelForm.setBackground(new java.awt.Color(32, 32, 32));

        btnGuardarEmp.setBackground(new java.awt.Color(37, 47, 33));
        btnGuardarEmp.setForeground(new java.awt.Color(204, 204, 204));
        btnGuardarEmp.setText("GUARDAR");
        btnGuardarEmp.setFocusPainted(false);
        btnGuardarEmp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEmpActionPerformed(evt);
            }
        });

        btnActualizarEmp.setBackground(new java.awt.Color(55, 50, 40));
        btnActualizarEmp.setForeground(new java.awt.Color(204, 204, 204));
        btnActualizarEmp.setText("ACTUALIZAR");
        btnActualizarEmp.setFocusPainted(false);
        btnActualizarEmp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(55, 29, 29));
        btnCancelar.setForeground(new java.awt.Color(204, 204, 204));
        btnCancelar.setMnemonic('c');
        btnCancelar.setText("CANCELAR");
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtEmpNombres.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpNombres.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpNombres.setLabelText("Nombres del empleado");
        txtEmpNombres.setPreferredSize(new java.awt.Dimension(100, 40));

        txtEmpApellidos.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpApellidos.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpApellidos.setLabelText("Apellidos del empleado");
        txtEmpApellidos.setPreferredSize(new java.awt.Dimension(100, 40));

        txtEmpTelefono.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpTelefono.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpTelefono.setLabelText("Telefono");
        txtEmpTelefono.setPreferredSize(new java.awt.Dimension(100, 40));

        txtEmpCedula.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpCedula.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpCedula.setLabelText("Cedula");
        txtEmpCedula.setPreferredSize(new java.awt.Dimension(100, 40));

        txtEmpDireccion.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpDireccion.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpDireccion.setLabelText("Direccion ");
        txtEmpDireccion.setPreferredSize(new java.awt.Dimension(100, 40));

        txtEmpEmail.setBackground(new java.awt.Color(32, 32, 32));
        txtEmpEmail.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpEmail.setLabelText("Email");
        txtEmpEmail.setPreferredSize(new java.awt.Dimension(100, 40));

        cbxEstadoEmp.setBackground(new java.awt.Color(32, 32, 32));
        cbxEstadoEmp.setForeground(new java.awt.Color(0, 153, 204));
        cbxEstadoEmp.setLabeText("Estado");
        cbxEstadoEmp.setPreferredSize(new java.awt.Dimension(100, 40));

        cbxSucursal.setBackground(new java.awt.Color(32, 32, 32));
        cbxSucursal.setForeground(new java.awt.Color(0, 153, 204));
        cbxSucursal.setLabeText("Sucursal");
        cbxSucursal.setPreferredSize(new java.awt.Dimension(100, 40));

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnActualizarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmpApellidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmpDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmpNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmpTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmpEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxEstadoEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEstadoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblIdEmpleado.setForeground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblRecargar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblIdEmpleado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRecargar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblRecargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRecargarMouseClicked
        ocultar();
        tablaEmpleados();
        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Se actualizo la base de datos!!");
        panel.showNotification();
    }//GEN-LAST:event_lblRecargarMouseClicked

    private void tDatosEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosEmpleadosMouseClicked
        cargarDatosEmpleados();
        btnGuardarEmp.setVisible(false);
        btnEliminar.setVisible(true);
        if (evt.getClickCount() == 2) {
            panelForm.setVisible(true);
            panelBuscar.setVisible(false);
            btnActualizarEmp.setVisible(true);
            cbxEstadoEmp.setVisible(true);
        }
    }//GEN-LAST:event_tDatosEmpleadosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ID_EMPLEADO = Integer.parseInt(lblIdEmpleado.getText());
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¡¡ELIMINAR REGISTRO!!", "¿ Desea eliminar al empleado ?");
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoEmp.delete(ID_EMPLEADO) == "El empleado fue eliminado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El empleado fue eliminado con exito!!");
                    panel.showNotification();
                    tablaEmpleados();
                    limpiarFormulario();
                } else if (daoEmp.delete(ID_EMPLEADO) == "Error al eliminar el empleado!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo eliminar al empleado!!");
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
        limpiarFormulario();
        btnGuardarEmp.setVisible(true);
        panelForm.setVisible(true);
        cbxEstadoEmp.setVisible(false);
        panelBuscar.setVisible(false);
        btnEliminar.setVisible(false);
    }//GEN-LAST:event_btnNuevoRegistroActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        panelBuscar.setVisible(true);
        panelForm.setVisible(false);
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
        panel.showNotification();
        limpiarFormulario();
        ocultar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpActionPerformed
        FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
        FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
        EMP_NOMBRES = txtEmpNombres.getText();
        EMP_APELLIDOS = txtEmpApellidos.getText();
        EMP_CEDULA = txtEmpCedula.getText();
        EMP_EMAIL = txtEmpEmail.getText();
        EMP_TELEFONO = txtEmpTelefono.getText();
        EMP_DIRECCION = txtEmpDireccion.getText();
        EMP_ESTADO = "EN LINEA";
        EMP_CREACION = fecha;
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas crear al empleado ?", "");
            emp.setID_EMPLEADO(ID_EMPLEADO);
            emp.setFK_SUCURSAL(FK_SUCURSAL);
            emp.setEMP_NOMBRES(EMP_NOMBRES);
            emp.setEMP_APELLIDOS(EMP_APELLIDOS);
            emp.setEMP_CEDULA(EMP_CEDULA);
            emp.setEMP_EMAIL(EMP_EMAIL);
            emp.setEMP_TELEFONO(EMP_TELEFONO);
            emp.setEMP_DIRECCION(EMP_DIRECCION);
            emp.setEMP_ESTADO(EMP_ESTADO);
            emp.setEMP_CREACION(EMP_CREACION);
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoEmp.add(emp) == "El empleado fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El empleado fue creado con exito!!");
                    panel.showNotification();
                    tablaEmpleados();
                    limpiarFormulario();
                    ocultar();
                } else if (daoEmp.add(emp) == "El empleado no fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo crear empleado!!");
                    panel.showNotification();
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
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion, favor corregirlos!!");
            panel.showNotification();
            limpiarFormulario();
            ocultar();
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }//GEN-LAST:event_btnGuardarEmpActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        buscarEmpleado();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnActualizarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpActionPerformed
        ID_EMPLEADO = Integer.parseInt(lblIdEmpleado.getText());
        FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
        FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
        FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
        EMP_NOMBRES = txtEmpNombres.getText();
        EMP_APELLIDOS = txtEmpApellidos.getText();
        EMP_CEDULA = txtEmpCedula.getText();
        EMP_EMAIL = txtEmpEmail.getText();
        EMP_TELEFONO = txtEmpTelefono.getText();
        EMP_DIRECCION = txtEmpDireccion.getText();
        EMP_ESTADO = cbxEstadoEmp.getSelectedItem().toString();
        EMP_CREACION = fecha;
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas actualizar al empleado ?", "");
            emp.setID_EMPLEADO(ID_EMPLEADO);
            emp.setFK_SUCURSAL(FK_SUCURSAL);
            emp.setEMP_NOMBRES(EMP_NOMBRES);
            emp.setEMP_APELLIDOS(EMP_APELLIDOS);
            emp.setEMP_CEDULA(EMP_CEDULA);
            emp.setEMP_EMAIL(EMP_EMAIL);
            emp.setEMP_TELEFONO(EMP_TELEFONO);
            emp.setEMP_DIRECCION(EMP_DIRECCION);
            emp.setEMP_ESTADO(EMP_ESTADO);
            emp.setEMP_CREACION(EMP_CREACION);
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoEmp.update(emp) == "El empleado fue actualizado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El empleado fue actualizado con exito!!");
                    panel.showNotification();
                    tablaEmpleados();
                    limpiarFormulario();
                    ocultar();
                } else if (daoEmp.update(emp) == "El Empleado no fue actualizado!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar empleado!!");
                    panel.showNotification();
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
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion, favor corregirlos!!");
            panel.showNotification();
            limpiarFormulario();
            ocultar();
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }//GEN-LAST:event_btnActualizarEmpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnActualizarEmp;
    private com.anthony.swing.Button btnBuscar1;
    private com.anthony.swing.Button btnCancelar;
    private com.anthony.swing.Button btnEliminar;
    private com.anthony.swing.Button btnGuardarEmp;
    private com.anthony.swing.Button btnNuevoRegistro;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.anthony.swing.Combobox cbxEstadoEmp;
    private com.anthony.swing.Combobox cbxSucursal;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel lblIdEmpleado;
    private javax.swing.JLabel lblRecargar;
    private javax.swing.JLabel lblTotalRegistros;
    private com.anthony.swing.RoundPanel panelBuscar;
    private com.anthony.swing.RoundPanel panelForm;
    private com.anthony.swing.RadioButton rdtbApellidos;
    private com.anthony.swing.RadioButton rdtbCedula;
    private com.anthony.swing.RadioButton rdtbDireccion;
    private com.anthony.swing.RadioButton rdtbEstado;
    private com.anthony.swing.RadioButton rdtbNombres;
    private com.anthony.swing.RoundPanel roundPanel1;
    private javax.swing.JScrollPane spPrincipal;
    private rojeru_san.complementos.TableMetro tDatosEmpleados;
    private textfield.TextField txtBusqueda;
    private textfield.TextField txtEmpApellidos;
    private textfield.TextField txtEmpCedula;
    private textfield.TextField txtEmpDireccion;
    private textfield.TextField txtEmpEmail;
    private textfield.TextField txtEmpNombres;
    private textfield.TextField txtEmpTelefono;
    // End of variables declaration//GEN-END:variables

}