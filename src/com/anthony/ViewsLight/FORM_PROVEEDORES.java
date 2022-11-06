package com.anthony.ViewsLight;

import com.anthony.Controller.Convertidor;
import com.anthony.MainLight.MainAdministrador;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.ModelsDAO.SUCURSAL_DAO;
import com.anthony.dialog.MessageDialogLight;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.swing.scrollbar.ScrollBarCustomClaro;
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

/**
 *
 * @author perez
 */
public class FORM_PROVEEDORES extends javax.swing.JPanel {

    /* ==============================
    OBJETOS PRINCIPALES
    ============================== */
    String[] titulosProveedores = {"#", "Sucursal", "Empresa", "Contacto", "Ruc", "Telefono", "Email", "Direccion", "Web", "Estado"};
    DefaultTableModel dtmProveedores = new DefaultTableModel(null, titulosProveedores);
    Convertidor convertidor = new Convertidor();
    RoundBorder border = new RoundBorder(0);
    PROVEEDOR_DAO daoProv = new PROVEEDOR_DAO();
    SUCURSAL_DAO daoSuc = new SUCURSAL_DAO();
    SUCURSAL suc = new SUCURSAL();
    PROVEEDOR prov = new PROVEEDOR();
    MainAdministrador admin;
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
    VARIABLES CLASE
    ============================== */
    private Integer ID_PROVEEDOR;
    private Integer FK_SUCURSAL;
    private String FK_SUCURSAL_TEXT;
    private String PRO_EMPRESA;
    private String PRO_CONTACTO;
    private String PRO_RUC;
    private String PRO_TELEFONO;
    private String PRO_EMAIL;
    private String PRO_DIRECCION;
    private String PRO_WEB;
    private String PRO_CREACION;
    private String PRO_ESTADO;

    /* ==============================
    CONSTRUCTORES
    ============================== */
    public FORM_PROVEEDORES() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        tablaProveedores();
        datosSpinner();
        datosSucursal();
    }

    public FORM_PROVEEDORES(USUARIO usu, MainAdministrador admin) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        tablaProveedores();
        datosSpinner();
        datosSucursal();
    }

    private void scroll() {
        spProveedores.setVerticalScrollBar(new ScrollBarCustomClaro());
        spProveedores.setHorizontalScrollBar(new ScrollBarCustomClaro());
        spProveedores.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        ScrollBarCustom sbh = new ScrollBarCustom();
        spProveedores.setVerticalScrollBar(new ScrollBarCustomClaro());
        spProveedores.setBorder(border);
        spProveedores.setVerticalScrollBar(new ScrollBarCustomClaro());
    }

    private void ocultar() {
        btnGuardar.setVisible(true);
        panelBuscar.setVisible(false);
        panelForm.setVisible(false);
        btnEliminar.setVisible(false);
        btnActualizar.setVisible(false);
        cbxEstado.setVisible(false);
    }

    private void datosSpinner() {
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"EN LINEA", "DE BAJA"}));
        cbxEstado.setSelectedIndex(-1);
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
        txtEmpresa.setText("");
        txtContacto.setText("");
        txtRuc.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtWeb.setText("");
        cbxEstado.setSelectedIndex(-1);
        cbxSucursal.setSelectedIndex(-1);
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

    public void tablaProveedores() {
        dtmProveedores = new DefaultTableModel(null, titulosProveedores);
        String fila[] = new String[10];
        List<PROVEEDOR> listProv = daoProv.listar();
        Iterator<PROVEEDOR> iterProv = listProv.iterator();
        prov = null;
        while (iterProv.hasNext()) {
            prov = iterProv.next();
            fila[0] = String.valueOf(prov.getID_PROVEEDOR());
            fila[1] = prov.getSUC_NOMBRE();
            fila[2] = prov.getPRO_EMPRESA();
            fila[3] = prov.getPRO_CONTACTO();
            fila[4] = prov.getPRO_RUC();
            fila[5] = prov.getPRO_TELEFONO();
            fila[6] = prov.getPRO_EMAIL();
            fila[7] = prov.getPRO_DIRECCION();
            fila[8] = prov.getPRO_WEB();
            fila[9] = prov.getPRO_ESTADO();
            dtmProveedores.addRow(fila);
        }
        tDatosProveedores.setModel(dtmProveedores);
        autoajustarColumnas(tDatosProveedores);
    }

    public void buscarProveedor() {
        busqueda = txtBusqueda.getText();
        if (rdtbContacto.isSelected()) {
            criterio = "PRO_CONTACTO";
        } else if (rdtbEmpresa.isSelected()) {
            criterio = "PRO_EMPRESA";
        } else if (rdtnRuc.isSelected()) {
            criterio = "PRO_RUC";
        } else if (rdtbDireccion.isSelected()) {
            criterio = "PRO_DIRECCION";
        } else if (rdtbEstado.isSelected()) {
            criterio = "PRO_ESTADO";
        }
        try {
            rs = daoProv.BUSCAR_PROVEEDOR(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[10];
            //Eliminar los datos que se encuentran en la tabla
            int f, i;
            f = dtmProveedores.getRowCount();
            if (f > 0) {
                for (i = 0; i < f; i++) {
                    dtmProveedores.removeRow(0);
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
                Datos[9] = (String) rs.getString(10);
                dtmProveedores.addRow(Datos);
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
        tDatosProveedores.setModel(dtmProveedores);
        autoajustarColumnas(tDatosProveedores);
//        contarRegistros();
    }

    public void cargarDatosProveedores() {
        int fila;
        fila = tDatosProveedores.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmProveedores = (DefaultTableModel) tDatosProveedores.getModel();
            lblIdProveedor.setText((String) dtmProveedores.getValueAt(fila, 0));
            FK_SUCURSAL_TEXT = (String) dtmProveedores.getValueAt(fila, 1);
            FK_SUCURSAL = Integer.parseInt(daoSuc.id_sucursal(FK_SUCURSAL_TEXT));
            FK_SUCURSAL_TEXT = FK_SUCURSAL + ".- " + (String) dtmProveedores.getValueAt(fila, 1);
            cbxSucursal.setSelectedItem(FK_SUCURSAL_TEXT);
            txtEmpresa.setText((String) dtmProveedores.getValueAt(fila, 2));
            txtContacto.setText((String) dtmProveedores.getValueAt(fila, 3));
            txtRuc.setText((String) dtmProveedores.getValueAt(fila, 4));
            txtTelefono.setText((String) dtmProveedores.getValueAt(fila, 5));
            txtEmail.setText((String) dtmProveedores.getValueAt(fila, 6));
            txtDireccion.setText((String) dtmProveedores.getValueAt(fila, 7));
            txtWeb.setText((String) dtmProveedores.getValueAt(fila, 8));
            cbxEstado.setSelectedItem((String) dtmProveedores.getValueAt(fila, 9));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        spProveedores = new javax.swing.JScrollPane();
        tDatosProveedores = new rojeru_san.complementos.TableMetro();
        btnEliminar = new com.anthony.swing.Button();
        btnNuevoRegistro = new com.anthony.swing.Button();
        btnBuscar1 = new com.anthony.swing.Button();
        panelBuscar = new com.anthony.swing.RoundPanel();
        txtBusqueda = new textfield.TextField();
        jLabel17 = new javax.swing.JLabel();
        rdtbContacto = new com.anthony.swing.RadioButton();
        rdtbEmpresa = new com.anthony.swing.RadioButton();
        rdtnRuc = new com.anthony.swing.RadioButton();
        rdtbDireccion = new com.anthony.swing.RadioButton();
        rdtbEstado = new com.anthony.swing.RadioButton();
        lblTotalRegistros = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelForm = new com.anthony.swing.RoundPanel();
        txtContacto = new textfield.TextField();
        txtEmpresa = new textfield.TextField();
        txtRuc = new textfield.TextField();
        txtEmail = new textfield.TextField();
        txtTelefono = new textfield.TextField();
        btnGuardar = new com.anthony.swing.Button();
        btnActualizar = new com.anthony.swing.Button();
        btnCancelarSuc1 = new com.anthony.swing.Button();
        cbxEstado = new com.anthony.swing.Combobox();
        txtDireccion = new textfield.TextField();
        txtWeb = new textfield.TextField();
        cbxSucursal = new com.anthony.swing.Combobox();
        lblIdProveedor = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 241, 251));

        jLabel1.setBackground(new java.awt.Color(102, 153, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("PROVEEDORES");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        spProveedores.setBackground(new java.awt.Color(255, 255, 255));
        spProveedores.setBorder(null);
        spProveedores.setForeground(new java.awt.Color(255, 255, 255));
        spProveedores.setFocusable(false);
        spProveedores.setOpaque(false);

        tDatosProveedores = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosProveedores.setBackground(new java.awt.Color(255, 255, 255));
        tDatosProveedores.setForeground(new java.awt.Color(234, 241, 251));
        tDatosProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosProveedores.setAltoHead(30);
        tDatosProveedores.setAutoscrolls(false);
        tDatosProveedores.setColorBackgoundHead(new java.awt.Color(243, 248, 255));
        tDatosProveedores.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosProveedores.setColorFilasBackgound2(new java.awt.Color(248, 248, 248));
        tDatosProveedores.setColorFilasForeground1(new java.awt.Color(123, 123, 123));
        tDatosProveedores.setColorFilasForeground2(new java.awt.Color(123, 123, 123));
        tDatosProveedores.setColorForegroundHead(new java.awt.Color(7, 16, 17));
        tDatosProveedores.setColorSelBackgound(new java.awt.Color(224, 237, 255));
        tDatosProveedores.setColorSelForeground(new java.awt.Color(102, 102, 102));
        tDatosProveedores.setFocusable(false);
        tDatosProveedores.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProveedores.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProveedores.setFuenteHead(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tDatosProveedores.setGridColor(new java.awt.Color(255, 255, 255));
        tDatosProveedores.setGrosorBordeFilas(0);
        tDatosProveedores.setGrosorBordeHead(0);
        tDatosProveedores.setOpaque(false);
        tDatosProveedores.setRowHeight(30);
        tDatosProveedores.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosProveedoresMouseClicked(evt);
            }
        });
        spProveedores.setViewportView(tDatosProveedores);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spProveedores)
                .addGap(3, 3, 3))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        btnEliminar.setBackground(new java.awt.Color(250, 104, 8));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoEliminar.png"))); // NOI18N
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevoRegistro.setBackground(new java.awt.Color(46, 189, 141));
        btnNuevoRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        btnNuevoRegistro.setFocusPainted(false);
        btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRegistroActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(8, 170, 250));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        btnBuscar1.setFocusPainted(false);
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        panelBuscar.setBackground(new java.awt.Color(255, 255, 255));

        txtBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setBorder(null);
        txtBusqueda.setLabelText("Escribe la sucursal que deseas buscar....");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(96, 96, 96));
        jLabel17.setText("Buscar por:");

        rdtbContacto.setBackground(new java.awt.Color(8, 170, 250));
        rdtbContacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 6, 17)));
        buttonGroup1.add(rdtbContacto);
        rdtbContacto.setForeground(new java.awt.Color(90, 122, 144));
        rdtbContacto.setText("Contacto");
        rdtbContacto.setFocusPainted(false);
        rdtbContacto.setRequestFocusEnabled(false);

        rdtbEmpresa.setBackground(new java.awt.Color(8, 170, 250));
        buttonGroup1.add(rdtbEmpresa);
        rdtbEmpresa.setForeground(new java.awt.Color(90, 122, 144));
        rdtbEmpresa.setText("Empresa");
        rdtbEmpresa.setFocusPainted(false);
        rdtbEmpresa.setRequestFocusEnabled(false);

        rdtnRuc.setBackground(new java.awt.Color(8, 170, 250));
        buttonGroup1.add(rdtnRuc);
        rdtnRuc.setForeground(new java.awt.Color(90, 122, 144));
        rdtnRuc.setText("RUC");
        rdtnRuc.setFocusPainted(false);
        rdtnRuc.setRequestFocusEnabled(false);

        rdtbDireccion.setBackground(new java.awt.Color(8, 170, 250));
        buttonGroup1.add(rdtbDireccion);
        rdtbDireccion.setForeground(new java.awt.Color(90, 122, 144));
        rdtbDireccion.setText("Direccion");
        rdtbDireccion.setFocusPainted(false);
        rdtbDireccion.setRequestFocusEnabled(false);

        rdtbEstado.setBackground(new java.awt.Color(8, 170, 250));
        buttonGroup1.add(rdtbEstado);
        rdtbEstado.setForeground(new java.awt.Color(90, 122, 144));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(rdtbContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtnRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdtbContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtnRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdtbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelBuscarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel12, lblTotalRegistros, rdtbContacto, rdtbDireccion, rdtbEmpresa, rdtbEstado, rdtnRuc});

        panelForm.setBackground(new java.awt.Color(255, 255, 255));

        txtContacto.setBackground(new java.awt.Color(255, 255, 255));
        txtContacto.setForeground(new java.awt.Color(0, 153, 204));
        txtContacto.setLabelText("Contacto");

        txtEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtEmpresa.setForeground(new java.awt.Color(0, 153, 204));
        txtEmpresa.setLabelText("Empresa");

        txtRuc.setBackground(new java.awt.Color(255, 255, 255));
        txtRuc.setForeground(new java.awt.Color(0, 153, 204));
        txtRuc.setLabelText("R.U.C");

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setForeground(new java.awt.Color(0, 153, 204));
        txtEmail.setLabelText("Email");

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setForeground(new java.awt.Color(0, 153, 204));
        txtTelefono.setLabelText("Telefono");

        btnGuardar.setBackground(new java.awt.Color(46, 189, 141));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(235, 190, 25));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFocusPainted(false);
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelarSuc1.setBackground(new java.awt.Color(250, 104, 8));
        btnCancelarSuc1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarSuc1.setText("CANCELAR");
        btnCancelarSuc1.setFocusPainted(false);
        btnCancelarSuc1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarSuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSuc1ActionPerformed(evt);
            }
        });

        cbxEstado.setForeground(new java.awt.Color(0, 153, 204));
        cbxEstado.setLabeText("Estado");

        txtDireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccion.setForeground(new java.awt.Color(0, 153, 204));
        txtDireccion.setLabelText("Direccion");

        txtWeb.setBackground(new java.awt.Color(255, 255, 255));
        txtWeb.setForeground(new java.awt.Color(0, 153, 204));
        txtWeb.setLabelText("Pagina Web");

        cbxSucursal.setForeground(new java.awt.Color(0, 153, 204));
        cbxSucursal.setLabeText("Sucursal");

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtWeb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxEstado, cbxSucursal, txtContacto, txtDireccion, txtEmail, txtEmpresa, txtRuc, txtTelefono, txtWeb});

        lblIdProveedor.setForeground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
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
                    .addComponent(lblIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        ocultar();
        tablaProveedores();
        datosSucursal();
        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Se actualizo la base de datos!!");
        panel.showNotification();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tDatosProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosProveedoresMouseClicked
        cargarDatosProveedores();
        btnGuardar.setVisible(false);
        btnEliminar.setVisible(true);
        if (evt.getClickCount() == 2) {
            panelForm.setVisible(true);
            panelBuscar.setVisible(false);
            btnActualizar.setVisible(true);
            cbxEstado.setVisible(true);
        }
    }//GEN-LAST:event_tDatosProveedoresMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ID_PROVEEDOR = Integer.parseInt(lblIdProveedor.getText());
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¡¡ELIMINAR REGISTRO!!", "¿ Desea eliminar al proveedor ?");
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                if (daoProv.delete(ID_PROVEEDOR) == "El proveedor fue eliminado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El proveedor fue eliminado con exito!!");
                    panel.showNotification();
                    tablaProveedores();
                    limpiarFormulario();
                } else if (daoProv.delete(ID_PROVEEDOR) == "Error al proveedor el empleado!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo eliminar al proveedor!!");
                    panel.showNotification();
                    tablaProveedores();
                    limpiarFormulario();
                }
            } else if (obj.getMessageType() == MessageDialogLight.MessageType.CANCEL) {
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
        panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
        panel.showNotification();
        limpiarFormulario();
        ocultar();
    }//GEN-LAST:event_btnCancelarSuc1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¿ Deseas crear al proveedor ?", "");
            FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
            FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
            PRO_EMPRESA = txtEmpresa.getText();
            PRO_CONTACTO = txtContacto.getText();
            PRO_RUC = txtRuc.getText();
            PRO_TELEFONO = txtTelefono.getText();
            PRO_EMAIL = txtEmail.getText();
            PRO_DIRECCION = txtDireccion.getText();
            PRO_WEB = txtWeb.getText();
            PRO_CREACION = fecha;
            PRO_ESTADO = "EN LINEA";
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                prov.setFK_SUCURSAL(FK_SUCURSAL);
                prov.setPRO_EMPRESA(PRO_EMPRESA);
                prov.setPRO_CONTACTO(PRO_CONTACTO);
                prov.setPRO_RUC(PRO_RUC);
                prov.setPRO_TELEFONO(PRO_TELEFONO);
                prov.setPRO_EMAIL(PRO_EMAIL);
                prov.setPRO_DIRECCION(PRO_DIRECCION);
                prov.setPRO_WEB(PRO_WEB);
                prov.setPRO_CREACION(PRO_CREACION);
                prov.setPRO_ESTADO(PRO_ESTADO);
                if (daoProv.add(prov) == "El proveedor fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El proveedor fue creado con exito!!");
                    panel.showNotification();
                    tablaProveedores();
                    limpiarFormulario();
                    ocultar();
                } else if (daoProv.add(prov) == "El proveedor no fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo crear al proveedor!!");
                    panel.showNotification();
                    tablaProveedores();
                    limpiarFormulario();
                    ocultar();
                }
            } else if (obj.getMessageType() == MessageDialogLight.MessageType.CANCEL) {
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
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        buscarProveedor();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¿ Deseas actualizar al proveedor ?", "");
            ID_PROVEEDOR = Integer.parseInt(lblIdProveedor.getText());
            FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
            FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
            PRO_EMPRESA = txtEmpresa.getText();
            PRO_CONTACTO = txtContacto.getText();
            PRO_RUC = txtRuc.getText();
            PRO_TELEFONO = txtTelefono.getText();
            PRO_EMAIL = txtEmail.getText();
            PRO_DIRECCION = txtDireccion.getText();
            PRO_WEB = txtWeb.getText();
            PRO_CREACION = fecha;
            PRO_ESTADO = cbxEstado.getSelectedItem().toString();
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                prov.setID_PROVEEDOR(ID_PROVEEDOR);
                prov.setFK_SUCURSAL(FK_SUCURSAL);
                prov.setPRO_EMPRESA(PRO_EMPRESA);
                prov.setPRO_CONTACTO(PRO_CONTACTO);
                prov.setPRO_RUC(PRO_RUC);
                prov.setPRO_TELEFONO(PRO_TELEFONO);
                prov.setPRO_EMAIL(PRO_EMAIL);
                prov.setPRO_DIRECCION(PRO_DIRECCION);
                prov.setPRO_WEB(PRO_WEB);
                prov.setPRO_CREACION(PRO_CREACION);
                prov.setPRO_ESTADO(PRO_ESTADO);
                if (daoProv.update(prov) == "El proveedor fue actualizado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El empleado fue actualizado con exito!!");
                    panel.showNotification();
                    tablaProveedores();
                    limpiarFormulario();
                    ocultar();
                } else if (daoProv.update(prov) == "El proveedor no fue actualizado!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar empleado!!");
                    panel.showNotification();
                    tablaProveedores();
                    limpiarFormulario();
                    ocultar();
                }
            } else if (obj.getMessageType() == MessageDialogLight.MessageType.CANCEL) {
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
    private com.anthony.swing.Button btnGuardar;
    private com.anthony.swing.Button btnNuevoRegistro;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.anthony.swing.Combobox cbxEstado;
    private com.anthony.swing.Combobox cbxSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel lblIdProveedor;
    private javax.swing.JLabel lblTotalRegistros;
    private com.anthony.swing.RoundPanel panelBuscar;
    private com.anthony.swing.RoundPanel panelForm;
    private com.anthony.swing.RadioButton rdtbContacto;
    private com.anthony.swing.RadioButton rdtbDireccion;
    private com.anthony.swing.RadioButton rdtbEmpresa;
    private com.anthony.swing.RadioButton rdtbEstado;
    private com.anthony.swing.RadioButton rdtnRuc;
    private com.anthony.swing.RoundPanel roundPanel1;
    private javax.swing.JScrollPane spProveedores;
    private rojeru_san.complementos.TableMetro tDatosProveedores;
    private textfield.TextField txtBusqueda;
    private textfield.TextField txtContacto;
    private textfield.TextField txtDireccion;
    private textfield.TextField txtEmail;
    private textfield.TextField txtEmpresa;
    private textfield.TextField txtRuc;
    private textfield.TextField txtTelefono;
    private textfield.TextField txtWeb;
    // End of variables declaration//GEN-END:variables
}

//class RoundBorder implements Border {
//
//    /*        
//        PARA QUITAR EL BORDE POR DEFECTO DE LA TABLA        
//     */
//    private int r;
//
//    RoundBorder(int r) {
//        this.r = r;
//    }
//
//    public Insets getBorderInsets(Component c) {
//        return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
//    }
//
//    public boolean isBorderOpaque() {
//        return true;
//    }
//
//    public void paintBorder(Component c, Graphics g, int x, int y,
//            int width, int height) {
//        g.drawRoundRect(x, y, width - 1, height - 1, r, r);
//    }
//}
