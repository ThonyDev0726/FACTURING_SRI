package com.anthony.ViewsDark;

import com.anthony.Controller.Convertidor;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.dialog.MessageDialogDark;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.toast.Toast;
import java.awt.Component;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class FORM_CLIENTES extends javax.swing.JPanel {

    /* ================================== 
    BASE DE DATOS
    ================================== */
    String[] titulosClientes = {"#", "Nombres", "Apellidos", "Cedula", "RUC", "Telefono", "Direccion", "Email", "Estado"};
    DefaultTableModel dtmClientes = new DefaultTableModel(null, titulosClientes);
    Convertidor convertidor = new Convertidor();
    RoundBorder border = new RoundBorder(0);
    CLIENTE_DAO daoCli = new CLIENTE_DAO();
    CLIENTE cli = new CLIENTE();
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

    /* ================================== 
    BASE DE DATOS
    ================================== */
    private Integer ID_CLIENTE;
    private String CLI_NOMBRES;
    private String CLI_APELLIDOS;
    private String CLI_CEDULA;
    private String CLI_RUC;
    private String CLI_TELEFONO;
    private String CLI_DIRECCION;
    private String CLI_EMAIL;
    private String CLI_ESTADO;
    private String CLI_CREACION;

    /* ================================== 
    BASE DE DATOS
    ================================== */
    public FORM_CLIENTES() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        datosSpinner();
        tablaClientes();
    }

    public FORM_CLIENTES(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        datosSpinner();
        tablaClientes();
    }

    /* ================================== 
    INTERFACES
    ================================== */
    private void datosSpinner() {
        cbxEstadoCli.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"EN LINEA", "DE BAJA"}));
        cbxEstadoCli.setSelectedIndex(-1);
    }

    private void scroll() {
        spClientes.setVerticalScrollBar(new ScrollBarCustom());
        spClientes.setHorizontalScrollBar(new ScrollBarCustom());
        spClientes.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        spClientes.setVerticalScrollBar(new ScrollBarCustom());
        spClientes.setBorder(border);
    }

    private void ocultar() {
        btnGuardarCli.setVisible(true);
        cbxEstadoCli.setVisible(false);
        panelBuscar.setVisible(false);
        panelForm.setVisible(false);
        btnEliminar.setVisible(false);
        btnActualizar.setVisible(false);
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

    private void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        txtRuc.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        cbxEstadoCli.setSelectedIndex(-1);
    }

    private void validarCedula() {
        if (txtCedula.getText().length() > 1) {
            txtRuc.setText("N / A");
            txtRuc.setEditable(false);
        } else {
            limpiarCedulaRuc();
        }
    }

    private void validarRuc() {
        if (txtRuc.getText().length() > 1) {
            txtCedula.setText("N / A");
            txtCedula.setEditable(false);
        } else {
            limpiarCedulaRuc();
        }
    }

    private void limpiarCedulaRuc() {
        if (txtCedula.getText().equals("N / A") && txtRuc.getText().equals("N / A")) {
            txtCedula.setText("");
            txtCedula.setEditable(true);
            txtRuc.setText("");
            txtRuc.setEditable(true);
        } else if (txtRuc.getText().length() == 0 && txtCedula.getText().equals("N / A")) {
            txtCedula.setText("");
            txtCedula.setEditable(true);
            txtRuc.setText("");
            txtRuc.setEditable(true);
        } else if (txtCedula.getText().length() == 0 && txtRuc.getText().equals("N / A")) {
            txtCedula.setText("");
            txtCedula.setEditable(true);
            txtRuc.setText("");
            txtRuc.setEditable(true);
        } else if (txtCedula.getText().length() == 0 && txtRuc.getText().length() == 0) {
            txtCedula.setText("");
            txtCedula.setEditable(true);
            txtRuc.setText("");
            txtRuc.setEditable(true);
        }
    }

    /* ================================== 
    BASE DE DATOS
    ================================== */
    public void tablaClientes() {
        dtmClientes = new DefaultTableModel(null, titulosClientes);
        String fila[] = new String[9];
        List<CLIENTE> listCh = daoCli.listar();
        Iterator<CLIENTE> iterCli = listCh.iterator();
        cli = null;
        while (iterCli.hasNext()) {
            cli = iterCli.next();
            fila[0] = String.valueOf(cli.getID_CLIENTE());
            fila[1] = cli.getCLI_NOMBRES();
            fila[2] = cli.getCLI_APELLIDOS();
            fila[3] = cli.getCLI_CEDULA();
            fila[4] = cli.getCLI_RUC();
            fila[5] = cli.getCLI_TELEFONO();
            fila[6] = cli.getCLI_DIRECCION();
            fila[7] = cli.getCLI_EMAIL();
            fila[8] = cli.getCLI_ESTADO();
            dtmClientes.addRow(fila);
        }
        tDatosClientes.setModel(dtmClientes);
        autoajustarColumnas(tDatosClientes);
    }

    public void buscarCliente() {
        busqueda = txtBusqueda.getText();
        if (rdtbNombres.isSelected()) {
            criterio = "CLI_NOMBRES";
        } else if (rdtbApellidos.isSelected()) {
            criterio = "CLI_APELLIDOS";
        } else if (rdtbCedula.isSelected()) {
            criterio = "CLI_CEDULA";
        } else if (rdtbDireccion.isSelected()) {
            criterio = "CLI_DIRECCION";
        } else if (rdtbEstado.isSelected()) {
            criterio = "CLI_ESTADO";
        } else if (rdtbRuc.isSelected()) {
            criterio = "CLI_RUC";
        }
        try {
            rs = daoCli.BUSCAR_CLIENTE(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[9];
            //Eliminar los datos que se encuentran en la tabla
            int f, i;
            f = dtmClientes.getRowCount();
            if (f > 0) {
                for (i = 0; i < f; i++) {
                    dtmClientes.removeRow(0);
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
                dtmClientes.addRow(Datos);
                encuentra = true;
            }
            if (encuentra == false) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Cliente no encontrado!!");
                panel.showNotification();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en el try");
        }
        tDatosClientes.setModel(dtmClientes);
        autoajustarColumnas(tDatosClientes);
//        contarRegistros();
    }

    public void cargarDatosClientes() {
        int fila;
        fila = tDatosClientes.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmClientes = (DefaultTableModel) tDatosClientes.getModel();
            lblIdCliente.setText((String) dtmClientes.getValueAt(fila, 0));
            txtNombres.setText((String) dtmClientes.getValueAt(fila, 1));
            txtApellidos.setText((String) dtmClientes.getValueAt(fila, 2));
            txtCedula.setText((String) dtmClientes.getValueAt(fila, 3));
            txtRuc.setText((String) dtmClientes.getValueAt(fila, 4));
            txtTelefono.setText((String) dtmClientes.getValueAt(fila, 5));
            txtDireccion.setText((String) dtmClientes.getValueAt(fila, 6));
            txtEmail.setText((String) dtmClientes.getValueAt(fila, 7));
            cbxEstadoCli.setSelectedItem((String) dtmClientes.getValueAt(fila, 8));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        spClientes = new javax.swing.JScrollPane();
        tDatosClientes = new rojeru_san.complementos.TableMetro();
        btnEliminar = new com.anthony.swing.Button();
        btnNuevoRegistro = new com.anthony.swing.Button();
        btnBuscar1 = new com.anthony.swing.Button();
        panelBuscar = new com.anthony.swing.RoundPanel();
        txtBusqueda = new textfield.TextField();
        jLabel17 = new javax.swing.JLabel();
        rdtbApellidos = new com.anthony.swing.RadioButton();
        rdtbNombres = new com.anthony.swing.RadioButton();
        rdtbCedula = new com.anthony.swing.RadioButton();
        rdtbRuc = new com.anthony.swing.RadioButton();
        rdtbDireccion = new com.anthony.swing.RadioButton();
        lblTotalRegistros = new javax.swing.JLabel();
        rdtbEstado = new com.anthony.swing.RadioButton();
        jLabel12 = new javax.swing.JLabel();
        panelForm = new com.anthony.swing.RoundPanel();
        txtNombres = new textfield.TextField();
        txtApellidos = new textfield.TextField();
        txtRuc = new textfield.TextField();
        txtCedula = new textfield.TextField();
        txtTelefono = new textfield.TextField();
        btnGuardarCli = new com.anthony.swing.Button();
        btnActualizar = new com.anthony.swing.Button();
        btnCancelarSuc1 = new com.anthony.swing.Button();
        cbxEstadoCli = new com.anthony.swing.Combobox();
        txtDireccion = new textfield.TextField();
        txtEmail = new textfield.TextField();
        lblIdCliente = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("CLIENTES");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));

        spClientes.setBackground(new java.awt.Color(32, 32, 32));
        spClientes.setBorder(null);
        spClientes.setForeground(new java.awt.Color(32, 32, 32));
        spClientes.setFocusable(false);
        spClientes.setOpaque(false);

        tDatosClientes = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosClientes.setBackground(new java.awt.Color(32, 32, 32));
        tDatosClientes.setForeground(new java.awt.Color(32, 32, 32));
        tDatosClientes.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {"fila 31", null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosClientes.setAltoHead(30);
        tDatosClientes.setAutoscrolls(false);
        tDatosClientes.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosClientes.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosClientes.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosClientes.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosClientes.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosClientes.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosClientes.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosClientes.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosClientes.setFocusable(false);
        tDatosClientes.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosClientes.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosClientes.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosClientes.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosClientes.setGrosorBordeFilas(0);
        tDatosClientes.setGrosorBordeHead(0);
        tDatosClientes.setOpaque(false);
        tDatosClientes.setRowHeight(30);
        tDatosClientes.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosClientesMouseClicked(evt);
            }
        });
        spClientes.setViewportView(tDatosClientes);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spClientes)
                .addGap(3, 3, 3))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addGap(4, 4, 4))
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
        btnNuevoRegistro.setFocusPainted(false);
        btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRegistroActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(0, 102, 153));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        btnBuscar1.setFocusPainted(false);
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        panelBuscar.setBackground(new java.awt.Color(32, 32, 32));

        txtBusqueda.setBackground(new java.awt.Color(32, 32, 32));
        txtBusqueda.setForeground(new java.awt.Color(0, 153, 204));
        txtBusqueda.setLabelText("Escribe al cliente que deseas buscar....");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(96, 96, 96));
        jLabel17.setText("Buscar por:");

        rdtbApellidos.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbApellidos);
        rdtbApellidos.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos.setText("Apellidos");
        rdtbApellidos.setFocusPainted(false);
        rdtbApellidos.setOpaque(true);
        rdtbApellidos.setRequestFocusEnabled(false);

        rdtbNombres.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbNombres);
        rdtbNombres.setForeground(new java.awt.Color(63, 81, 102));
        rdtbNombres.setText("Nombres");
        rdtbNombres.setFocusPainted(false);
        rdtbNombres.setOpaque(true);
        rdtbNombres.setRequestFocusEnabled(false);

        rdtbCedula.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbCedula);
        rdtbCedula.setForeground(new java.awt.Color(63, 81, 102));
        rdtbCedula.setText("Cedula");
        rdtbCedula.setFocusPainted(false);
        rdtbCedula.setOpaque(true);
        rdtbCedula.setRequestFocusEnabled(false);

        rdtbRuc.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbRuc);
        rdtbRuc.setForeground(new java.awt.Color(63, 81, 102));
        rdtbRuc.setText("RUC");
        rdtbRuc.setFocusPainted(false);
        rdtbRuc.setOpaque(true);
        rdtbRuc.setRequestFocusEnabled(false);

        rdtbDireccion.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbDireccion);
        rdtbDireccion.setForeground(new java.awt.Color(63, 81, 102));
        rdtbDireccion.setText("Direccion");
        rdtbDireccion.setFocusPainted(false);
        rdtbDireccion.setOpaque(true);
        rdtbDireccion.setRequestFocusEnabled(false);

        lblTotalRegistros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        rdtbEstado.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbEstado);
        rdtbEstado.setForeground(new java.awt.Color(63, 81, 102));
        rdtbEstado.setText("Estado");
        rdtbEstado.setFocusPainted(false);
        rdtbEstado.setOpaque(true);
        rdtbEstado.setRequestFocusEnabled(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(96, 96, 96));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL DE REGISTROS:");

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(rdtbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(rdtbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelForm.setBackground(new java.awt.Color(32, 32, 32));

        txtNombres.setBackground(new java.awt.Color(32, 32, 32));
        txtNombres.setForeground(new java.awt.Color(0, 153, 204));
        txtNombres.setLabelText("Nombres");

        txtApellidos.setBackground(new java.awt.Color(32, 32, 32));
        txtApellidos.setForeground(new java.awt.Color(0, 153, 204));
        txtApellidos.setLabelText("Apellidos");

        txtRuc.setBackground(new java.awt.Color(32, 32, 32));
        txtRuc.setForeground(new java.awt.Color(0, 153, 204));
        txtRuc.setLabelText("R.U.C");
        txtRuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRucMouseClicked(evt);
            }
        });
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        txtCedula.setBackground(new java.awt.Color(32, 32, 32));
        txtCedula.setForeground(new java.awt.Color(0, 153, 204));
        txtCedula.setLabelText("Cedula");
        txtCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCedulaMouseClicked(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtTelefono.setBackground(new java.awt.Color(32, 32, 32));
        txtTelefono.setForeground(new java.awt.Color(0, 153, 204));
        txtTelefono.setLabelText("Telefono");
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        btnGuardarCli.setBackground(new java.awt.Color(37, 47, 33));
        btnGuardarCli.setForeground(new java.awt.Color(204, 204, 204));
        btnGuardarCli.setText("GUARDAR");
        btnGuardarCli.setFocusPainted(false);
        btnGuardarCli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCliActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(55, 50, 40));
        btnActualizar.setForeground(new java.awt.Color(204, 204, 204));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFocusPainted(false);
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelarSuc1.setBackground(new java.awt.Color(55, 29, 29));
        btnCancelarSuc1.setForeground(new java.awt.Color(204, 204, 204));
        btnCancelarSuc1.setText("CANCELAR");
        btnCancelarSuc1.setFocusPainted(false);
        btnCancelarSuc1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarSuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSuc1ActionPerformed(evt);
            }
        });

        cbxEstadoCli.setBackground(new java.awt.Color(32, 32, 32));
        cbxEstadoCli.setForeground(new java.awt.Color(0, 153, 204));
        cbxEstadoCli.setLabeText("Estado");

        txtDireccion.setBackground(new java.awt.Color(32, 32, 32));
        txtDireccion.setForeground(new java.awt.Color(0, 153, 204));
        txtDireccion.setLabelText("Direccion");

        txtEmail.setBackground(new java.awt.Color(32, 32, 32));
        txtEmail.setForeground(new java.awt.Color(0, 153, 204));
        txtEmail.setLabelText("Email");

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxEstadoCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxEstadoCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxEstadoCli, txtApellidos, txtCedula, txtDireccion, txtEmail, txtNombres, txtRuc, txtTelefono});

        lblIdCliente.setForeground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIdCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(lblIdCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        tablaClientes();
        ocultar();
        limpiarFormulario();
        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Se actualizo la base de datos!!");
        panel.showNotification();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tDatosClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosClientesMouseClicked
        cargarDatosClientes();
        btnGuardarCli.setVisible(false);
        btnEliminar.setVisible(true);
        if (evt.getClickCount() == 2) {
            panelForm.setVisible(true);
            panelBuscar.setVisible(false);
            btnActualizar.setVisible(true);
            cbxEstadoCli.setVisible(true);
        }
    }//GEN-LAST:event_tDatosClientesMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ID_CLIENTE = Integer.parseInt(lblIdCliente.getText());
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¡¡ELIMINAR REGISTRO!!", "¿ Desea eliminar al cliente ?");
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoCli.delete(ID_CLIENTE) == "El cliente fue eliminado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El cliente fue eliminado con exito!!");
                    panel.showNotification();
                    ocultar();
                    tablaClientes();
                    limpiarFormulario();
                } else if (daoCli.delete(ID_CLIENTE) == "Error al eliminar el cliente!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo eliminar al cliente!!");
                    panel.showNotification();
                    tablaClientes();
                    limpiarFormulario();
                    ocultar();
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
        panelForm.setVisible(true);
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
    }//GEN-LAST:event_btnCancelarSuc1ActionPerformed

    private void btnGuardarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCliActionPerformed
        CLI_NOMBRES = txtNombres.getText();
        CLI_APELLIDOS = txtApellidos.getText();
        CLI_CEDULA = txtCedula.getText();
        CLI_RUC = txtRuc.getText();
        CLI_TELEFONO = txtTelefono.getText();
        CLI_DIRECCION = txtDireccion.getText();
        CLI_EMAIL = txtEmail.getText();
        CLI_ESTADO = "EN LINEA";
        CLI_CREACION = fecha;
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas crear al cliente ?", "");
            cli.setCLI_NOMBRES(CLI_NOMBRES);
            cli.setCLI_APELLIDOS(CLI_APELLIDOS);
            cli.setCLI_CEDULA(CLI_CEDULA);
            cli.setCLI_RUC(CLI_RUC);
            cli.setCLI_TELEFONO(CLI_TELEFONO);
            cli.setCLI_DIRECCION(CLI_DIRECCION);
            cli.setCLI_EMAIL(CLI_EMAIL);
            cli.setCLI_ESTADO(CLI_ESTADO);
            cli.setCLI_CREACION(CLI_CREACION);
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoCli.add(cli) == "El cliente fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El cliente fue creado con exito!!");
                    panel.showNotification();
                    tablaClientes();
                    limpiarFormulario();
                    ocultar();
                } else if (daoCli.add(cli) == "El cliente no fue creado!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo crear al cliente!!");
                    panel.showNotification();
                    tablaClientes();
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
    }//GEN-LAST:event_btnGuardarCliActionPerformed

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
        validarCedula();
    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased
        validarRuc();
    }//GEN-LAST:event_txtRucKeyReleased

    private void txtRucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRucMouseClicked
        limpiarCedulaRuc();
    }//GEN-LAST:event_txtRucMouseClicked

    private void txtCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCedulaMouseClicked
        limpiarCedulaRuc();
    }//GEN-LAST:event_txtCedulaMouseClicked

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            evt.consume();
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten numeros!!");
            panel.showNotification();
        }
        if (txtRuc.getText().trim().length() == 13) {
            evt.consume();
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten 13 caracteres!!");
            panel.showNotification();
        }
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            evt.consume();
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten numeros!!");
            panel.showNotification();
        }
        if (txtCedula.getText().trim().length() == 10) {
            evt.consume();
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten 10 caracteres!!");
            panel.showNotification();
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            evt.consume();
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten numeros!!");
            panel.showNotification();
        }
        if (txtTelefono.getText().trim().length() == 10) {
            evt.consume();
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten 10 caracteres!!");
            panel.showNotification();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        buscarCliente();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ID_CLIENTE = Integer.parseInt(lblIdCliente.getText());
        CLI_NOMBRES = txtNombres.getText();
        CLI_APELLIDOS = txtApellidos.getText();
        CLI_CEDULA = txtCedula.getText();
        CLI_RUC = txtRuc.getText();
        CLI_TELEFONO = txtTelefono.getText();
        CLI_DIRECCION = txtDireccion.getText();
        CLI_EMAIL = txtEmail.getText();
        CLI_ESTADO = cbxEstadoCli.getSelectedItem().toString();
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas actualizar al cliente ?", "");
            cli.setID_CLIENTE(ID_CLIENTE);
            cli.setCLI_NOMBRES(CLI_NOMBRES);
            cli.setCLI_APELLIDOS(CLI_APELLIDOS);
            cli.setCLI_CEDULA(CLI_CEDULA);
            cli.setCLI_RUC(CLI_RUC);
            cli.setCLI_TELEFONO(CLI_TELEFONO);
            cli.setCLI_DIRECCION(CLI_DIRECCION);
            cli.setCLI_EMAIL(CLI_EMAIL);
            cli.setCLI_ESTADO(CLI_ESTADO);
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoCli.update(cli) == "El cliente fue actualizado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El cliente fue actualizado con exito!!");
                    panel.showNotification();
                    tablaClientes();
                    limpiarFormulario();
                    ocultar();
                } else if (daoCli.update(cli) == "El cliente no fue actualizado!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar al cliente!!");
                    panel.showNotification();
                    tablaClientes();
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
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnActualizar;
    private com.anthony.swing.Button btnBuscar1;
    private com.anthony.swing.Button btnCancelarSuc1;
    private com.anthony.swing.Button btnEliminar;
    private com.anthony.swing.Button btnGuardarCli;
    private com.anthony.swing.Button btnNuevoRegistro;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.anthony.swing.Combobox cbxEstadoCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblTotalRegistros;
    private com.anthony.swing.RoundPanel panelBuscar;
    private com.anthony.swing.RoundPanel panelForm;
    private com.anthony.swing.RadioButton rdtbApellidos;
    private com.anthony.swing.RadioButton rdtbCedula;
    private com.anthony.swing.RadioButton rdtbDireccion;
    private com.anthony.swing.RadioButton rdtbEstado;
    private com.anthony.swing.RadioButton rdtbNombres;
    private com.anthony.swing.RadioButton rdtbRuc;
    private com.anthony.swing.RoundPanel roundPanel1;
    private javax.swing.JScrollPane spClientes;
    private rojeru_san.complementos.TableMetro tDatosClientes;
    private textfield.TextField txtApellidos;
    private textfield.TextField txtBusqueda;
    private textfield.TextField txtCedula;
    private textfield.TextField txtDireccion;
    private textfield.TextField txtEmail;
    private textfield.TextField txtNombres;
    private textfield.TextField txtRuc;
    private textfield.TextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
