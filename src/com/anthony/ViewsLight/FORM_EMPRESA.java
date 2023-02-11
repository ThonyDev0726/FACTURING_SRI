package com.anthony.ViewsLight;

import com.anthony.MainLight.MainAdministrador;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.componentsLigth.MessageDialogLight;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.swing.scrollbar.ScrollBarCustomClaro;
import com.anthony.toast.Toast;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Iterator;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class FORM_EMPRESA extends javax.swing.JPanel {

    /* ================================== 
    INSTANCIAS NECESARIAS 
    ================================== */
    MainAdministrador admin;
    RoundBorder border = new RoundBorder(0);
    EMPRESA_DAO daoEmpresa = new EMPRESA_DAO();
    EMPRESA emp = new EMPRESA();
    SUCURSAL suc = new SUCURSAL();
    SUCURSAL_DAO daoSuc = new SUCURSAL_DAO();
    String[] titulosSucursal = {"#", "Empresa", "Nombres", "Telefono", "Email", "Direccion", "Estado"};
    DefaultTableModel dtmSucursal = new DefaultTableModel(null, titulosSucursal);
    USUARIO usu;
    Toast panel;

    /*==============================
    VARIABLES GENERALES DE EMPRESA
    ==============================*/
    private Integer ID_EMPRESA;
    private String EMP_NOMBRE_COMERCIAL;
    private String EMP_RAZON_SOCIAL;
    private String EMP_RUC;
    private String EMP_MATRIZ;
    private String EMP_ESTABLECIMIENTO;
    private String EMP_COD_ESTABLECIMIENTO;
    private String EMP_PUNTO_EMISION;
    private String EMP_RESOLUCION_CONTRIB_ESPECIAL;
    private String EMP_NUM_AGENTE_RETENCION;
    private String EMP_LLEVAR_CONTABILIDAD;
    private String EMP_CONTRIBUYENTE_RIMPE;
    private String EMP_TOKEN;

    /*==============================
    VARIABLES GENERALES DE EMPRESA
    ==============================*/
    private Integer ID_SUCURSAL;
    private Integer FK_EMPRESA;
    private String SUC_NOMBRE;
    private String SUC_TELEFONO;
    private String SUC_EMAIL;
    private String SUC_DIRECCION;
    private String SUC_ESTADO;

    /*==============================
      CONSTRUCTORES GENERALES 
    ==============================*/
    public FORM_EMPRESA() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        datosSpinner();
        datosEmpresa();
        actualizarTablaSucursal();
    }

    public FORM_EMPRESA(USUARIO usu, MainAdministrador admin) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        datosSpinner();
        datosEmpresa();
        actualizarTablaSucursal();
    }

    /*==============================
      INTERFACES 
    ==============================*/
    private void datosSpinner() {
        cbxLlevarContabilidad.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"SI", "NO"}));
        cbxLlevarContabilidad.setSelectedIndex(-1);
        cbxRimpe.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"SI", "NO"}));
        cbxRimpe.setSelectedIndex(-1);
        cbxSucursalEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"EN LINEA", "DE BAJA"}));
        cbxSucursalEstado.setSelectedIndex(-1);
        cbxToken.setModel(new javax.swing.DefaultComboBoxModel(new String[]{
            "BCE - iKey2032",
            "SD - Aladdin eToquen Pro",
            "SD - ePass3003 auto",
            "SD - BioPass3000",
            "KEY4 - Concejo Judicatura",
            "UANATACA",
            "EclipSoft",
            "DATILMEDIA"
        }));
        cbxToken.setSelectedIndex(-1);
    }

    private void scroll() {
        spPrincipal.setVerticalScrollBar(new ScrollBarCustomClaro());
        spPrincipal.setHorizontalScrollBar(new ScrollBarCustomClaro());
        spPago.setVerticalScrollBar(new ScrollBarCustomClaro());
        spSucursal.setVerticalScrollBar(new ScrollBarCustomClaro());
        spSucursal.getViewport().setOpaque(false);
        spPago.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        ScrollBarCustomClaro sbh = new ScrollBarCustomClaro();
        spPago.setHorizontalScrollBar(sbh);
        spPago.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spPago.setBorder(border);
        spSucursal.setVerticalScrollBar(new ScrollBarCustomClaro());
        spSucursal.setBorder(border);
        spSucursal.setVerticalScrollBar(new ScrollBarCustomClaro());
    }

    /* ==== INTERFACE ==== */
    private void limpiarFormulario() {
        txtSucNombre.setText("");
        txtSucTelefono.setText("");
        txtSucEmail.setText("");
        txtSucDireccion.setText("");
    }

    private void ocultar() {
        btnGuardarSuc.setVisible(true);
        panelSucursalForm.setVisible(false);
        btnEliminarSuc.setVisible(false);
        panelBuscarSuc.setVisible(false);
        pnlTipoPago.setVisible(false);
        btnActualizarSuc.setVisible(false);
        cbxSucursalEstado.setVisible(false);
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

    /*==============================
    BASE DE DATOS 
    ==============================*/
    private void datosEmpresa() {
        emp = (EMPRESA) daoEmpresa.list();
        ID_EMPRESA = emp.getID_EMPRESA();
        txtNombreComercial.setText(emp.getEMP_NOMBRE_COMERCIAL());
        txtRazonSocial.setText(emp.getEMP_RAZON_SOCIAL());
        txtRuc.setText(emp.getEMP_RUC());
        txtMatriz.setText(emp.getEMP_MATRIZ());
        txtEstablecimiento.setText(emp.getEMP_ESTABLECIMIENTO());
        txtCodEstablecimiento.setText(emp.getEMP_COD_ESTABLECIMIENTO());
        txtCodPuntoEmis.setText(emp.getEMP_PUNTO_EMISION());
        txtNumResolContribuyenteEsp.setText(emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL());
        txtNumAgenteRetencion.setText(emp.getEMP_NUM_AGENTE_RETENCION());
        cbxLlevarContabilidad.setSelectedItem(emp.getEMP_LLEVAR_CONTABILIDAD());
        cbxRimpe.setSelectedItem(emp.getEMP_CONTRIBUYENTE_RIMPE());
        cbxToken.setSelectedItem(emp.getEMP_TOKEN());
    }

    public void actualizarTablaSucursal() {
        dtmSucursal = new DefaultTableModel(null, titulosSucursal);
        String fila[] = new String[8];
        List<SUCURSAL> listCh = daoSuc.listar();
        Iterator<SUCURSAL> iterCh = listCh.iterator();
        suc = null;
        while (iterCh.hasNext()) {
            suc = iterCh.next();
            fila[0] = String.valueOf(suc.getID_SUCURSAL());
            fila[1] = suc.getEMP_NOMBRE_COMERCIAL();
            fila[2] = suc.getSUC_NOMBRE();
            fila[3] = suc.getSUC_TELEFONO();
            fila[4] = suc.getSUC_EMAIL();
            fila[5] = suc.getSUC_DIRECCION();
            fila[6] = suc.getSUC_ESTADO();
            dtmSucursal.addRow(fila);
        }
        tDatosSucursal.setModel(dtmSucursal);
        autoajustarColumnas(tDatosSucursal);
//        contarRegistros();
    }

    public void cargarDatosSucursal() {
        int fila;
        fila = tDatosSucursal.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmSucursal = (DefaultTableModel) tDatosSucursal.getModel();
            lblId_Sucursal.setText((String) dtmSucursal.getValueAt(fila, 0));
            txtSucNombre.setText((String) dtmSucursal.getValueAt(fila, 2));
            txtSucTelefono.setText((String) dtmSucursal.getValueAt(fila, 3));
            txtSucEmail.setText((String) dtmSucursal.getValueAt(fila, 4));
            txtSucDireccion.setText((String) dtmSucursal.getValueAt(fila, 5));
            cbxSucursalEstado.setSelectedItem((String) dtmSucursal.getValueAt(fila, 6));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        spPrincipal = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        txtNombreComercial = new textfield.TextField();
        txtRazonSocial = new textfield.TextField();
        txtRuc = new textfield.TextField();
        txtMatriz = new textfield.TextField();
        txtEstablecimiento = new textfield.TextField();
        txtCodEstablecimiento = new textfield.TextField();
        txtNumResolContribuyenteEsp = new textfield.TextField();
        txtCodPuntoEmis = new textfield.TextField();
        txtNumAgenteRetencion = new textfield.TextField();
        cbxLlevarContabilidad = new com.anthony.swing.Combobox();
        cbxToken = new com.anthony.swing.Combobox();
        cbxRimpe = new com.anthony.swing.Combobox();
        btnActualizarEmpresa = new com.anthony.swing.Button();
        jLabel3 = new javax.swing.JLabel();
        panelSucursalForm = new com.anthony.swing.RoundPanel();
        txtSucNombre = new textfield.TextField();
        txtSucTelefono = new textfield.TextField();
        txtSucDireccion = new textfield.TextField();
        txtSucEmail = new textfield.TextField();
        cbxSucursalEstado = new com.anthony.swing.Combobox();
        btnGuardarSuc = new com.anthony.swing.Button();
        btnActualizarSuc = new com.anthony.swing.Button();
        btnCancelarSuc1 = new com.anthony.swing.Button();
        roundPanel3 = new com.anthony.swing.RoundPanel();
        textField1 = new textfield.TextField();
        button13 = new com.anthony.swing.Button();
        roundPanel4 = new com.anthony.swing.RoundPanel();
        textField2 = new textfield.TextField();
        button14 = new com.anthony.swing.Button();
        pnlTipoPago = new com.anthony.swing.RoundPanel();
        textField3 = new textfield.TextField();
        button17 = new com.anthony.swing.Button();
        btnEliminarTipoPago = new com.anthony.swing.Button();
        button15 = new com.anthony.swing.Button();
        roundPanel6 = new com.anthony.swing.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        button11 = new com.anthony.swing.Button();
        spPago = new javax.swing.JScrollPane();
        tDatosUsuarios6 = new rojeru_san.complementos.TableMetro();
        panelBuscarSuc = new com.anthony.swing.RoundPanel();
        radioButton6 = new com.anthony.swing.RadioButton();
        radioButton4 = new com.anthony.swing.RadioButton();
        jLabel17 = new javax.swing.JLabel();
        lblTotalRegistros = new javax.swing.JLabel();
        textField9 = new textfield.TextField();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        jLabel15 = new javax.swing.JLabel();
        button20 = new com.anthony.swing.Button();
        btnEliminarSuc = new com.anthony.swing.Button();
        button21 = new com.anthony.swing.Button();
        spSucursal = new javax.swing.JScrollPane();
        tDatosSucursal = new rojeru_san.complementos.TableMetro();
        lblId_Sucursal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(234, 241, 251));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 122, 108));
        jLabel1.setText("EMPRESA");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        spPrincipal.setBorder(null);
        spPrincipal.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(234, 241, 251));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtNombreComercial.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreComercial.setForeground(new java.awt.Color(0, 153, 204));
        txtNombreComercial.setLabelText("Nombre comercial");

        txtRazonSocial.setBackground(new java.awt.Color(255, 255, 255));
        txtRazonSocial.setForeground(new java.awt.Color(0, 153, 204));
        txtRazonSocial.setLabelText("Razon social");

        txtRuc.setBackground(new java.awt.Color(255, 255, 255));
        txtRuc.setForeground(new java.awt.Color(0, 153, 204));
        txtRuc.setLabelText("R.U.C.");

        txtMatriz.setBackground(new java.awt.Color(255, 255, 255));
        txtMatriz.setForeground(new java.awt.Color(0, 153, 204));
        txtMatriz.setLabelText("Direccion matriz");

        txtEstablecimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtEstablecimiento.setForeground(new java.awt.Color(0, 153, 204));
        txtEstablecimiento.setLabelText("Direccion del establecimiento");

        txtCodEstablecimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtCodEstablecimiento.setForeground(new java.awt.Color(0, 153, 204));
        txtCodEstablecimiento.setLabelText("Codigo del establecimiento");

        txtNumResolContribuyenteEsp.setBackground(new java.awt.Color(255, 255, 255));
        txtNumResolContribuyenteEsp.setForeground(new java.awt.Color(0, 153, 204));
        txtNumResolContribuyenteEsp.setLabelText("Numero de resolucion de contribuyente especial");

        txtCodPuntoEmis.setBackground(new java.awt.Color(255, 255, 255));
        txtCodPuntoEmis.setForeground(new java.awt.Color(0, 153, 204));
        txtCodPuntoEmis.setLabelText("Codigo punto emision");

        txtNumAgenteRetencion.setBackground(new java.awt.Color(255, 255, 255));
        txtNumAgenteRetencion.setForeground(new java.awt.Color(0, 153, 204));
        txtNumAgenteRetencion.setLabelText("Numero de Agente de retencion");

        cbxLlevarContabilidad.setForeground(new java.awt.Color(0, 153, 204));
        cbxLlevarContabilidad.setLabeText("Obligado a llevar contabilidad:");

        cbxToken.setForeground(new java.awt.Color(0, 153, 204));
        cbxToken.setLabeText("Tipo de token:");

        cbxRimpe.setForeground(new java.awt.Color(0, 153, 204));
        cbxRimpe.setLabeText("Contribuyente RIMPE:");

        btnActualizarEmpresa.setBackground(new java.awt.Color(235, 190, 25));
        btnActualizarEmpresa.setBorder(null);
        btnActualizarEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarEmpresa.setText("ACTUALIZAR");
        btnActualizarEmpresa.setFocusPainted(false);
        btnActualizarEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombreComercial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstablecimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodPuntoEmis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumAgenteRetencion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxRimpe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodEstablecimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumResolContribuyenteEsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxLlevarContabilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxToken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnActualizarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodPuntoEmis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumResolContribuyenteEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumAgenteRetencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxLlevarContabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxRimpe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxLlevarContabilidad, txtCodEstablecimiento, txtCodPuntoEmis, txtEstablecimiento, txtMatriz, txtNombreComercial, txtNumAgenteRetencion, txtNumResolContribuyenteEsp, txtRazonSocial, txtRuc});

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 122, 108));
        jLabel3.setText("DATOS DE FACTURACION Y GESTION DE SUCURSALES");

        panelSucursalForm.setBackground(new java.awt.Color(255, 255, 255));

        txtSucNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtSucNombre.setForeground(new java.awt.Color(0, 153, 204));
        txtSucNombre.setLabelText("Nombre de la sucursal");

        txtSucTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtSucTelefono.setForeground(new java.awt.Color(0, 153, 204));
        txtSucTelefono.setLabelText("Telefono");

        txtSucDireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtSucDireccion.setForeground(new java.awt.Color(0, 153, 204));
        txtSucDireccion.setLabelText("Direccion");

        txtSucEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtSucEmail.setForeground(new java.awt.Color(0, 153, 204));
        txtSucEmail.setLabelText("Email");

        cbxSucursalEstado.setForeground(new java.awt.Color(0, 153, 204));
        cbxSucursalEstado.setLabeText("Estado");

        btnGuardarSuc.setBackground(new java.awt.Color(46, 189, 141));
        btnGuardarSuc.setBorder(null);
        btnGuardarSuc.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarSuc.setText("GUARDAR");
        btnGuardarSuc.setFocusPainted(false);
        btnGuardarSuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSucActionPerformed(evt);
            }
        });

        btnActualizarSuc.setBackground(new java.awt.Color(235, 190, 25));
        btnActualizarSuc.setBorder(null);
        btnActualizarSuc.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarSuc.setText("ACTUALIZAR");
        btnActualizarSuc.setFocusPainted(false);
        btnActualizarSuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarSucActionPerformed(evt);
            }
        });

        btnCancelarSuc1.setBackground(new java.awt.Color(250, 104, 8));
        btnCancelarSuc1.setBorder(null);
        btnCancelarSuc1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarSuc1.setText("CANCELAR");
        btnCancelarSuc1.setFocusPainted(false);
        btnCancelarSuc1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarSuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSuc1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSucursalFormLayout = new javax.swing.GroupLayout(panelSucursalForm);
        panelSucursalForm.setLayout(panelSucursalFormLayout);
        panelSucursalFormLayout.setHorizontalGroup(
            panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSucursalFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSucursalFormLayout.createSequentialGroup()
                        .addComponent(btnGuardarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSucursalFormLayout.createSequentialGroup()
                        .addGroup(panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSucNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(cbxSucursalEstado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(txtSucEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSucDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(txtSucTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSucursalFormLayout.setVerticalGroup(
            panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSucursalFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSucNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSucEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSucursalEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSucursalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        textField1.setBackground(new java.awt.Color(255, 255, 255));
        textField1.setForeground(new java.awt.Color(0, 153, 204));
        textField1.setLabelText("Iva");

        button13.setBackground(new java.awt.Color(235, 190, 25));
        button13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoActualizar.png"))); // NOI18N
        button13.setFocusPainted(false);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        textField2.setBackground(new java.awt.Color(255, 255, 255));
        textField2.setForeground(new java.awt.Color(0, 153, 204));
        textField2.setLabelText("Descuento");

        button14.setBackground(new java.awt.Color(235, 190, 25));
        button14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoActualizar.png"))); // NOI18N
        button14.setFocusPainted(false);

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTipoPago.setBackground(new java.awt.Color(255, 255, 255));

        textField3.setBackground(new java.awt.Color(255, 255, 255));
        textField3.setForeground(new java.awt.Color(0, 153, 204));
        textField3.setLabelText("Tipo de pago");

        button17.setBackground(new java.awt.Color(250, 104, 8));
        button17.setBorder(null);
        button17.setForeground(new java.awt.Color(255, 255, 255));
        button17.setText("CANCELAR");
        button17.setFocusPainted(false);
        button17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button17ActionPerformed(evt);
            }
        });

        btnEliminarTipoPago.setBackground(new java.awt.Color(235, 190, 25));
        btnEliminarTipoPago.setBorder(null);
        btnEliminarTipoPago.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarTipoPago.setText("ACTUALIZAR");
        btnEliminarTipoPago.setFocusPainted(false);
        btnEliminarTipoPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        button15.setBackground(new java.awt.Color(46, 189, 141));
        button15.setBorder(null);
        button15.setForeground(new java.awt.Color(255, 255, 255));
        button15.setText("GUARDAR");
        button15.setFocusPainted(false);
        button15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout pnlTipoPagoLayout = new javax.swing.GroupLayout(pnlTipoPago);
        pnlTipoPago.setLayout(pnlTipoPagoLayout);
        pnlTipoPagoLayout.setHorizontalGroup(
            pnlTipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTipoPagoLayout.createSequentialGroup()
                        .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTipoPagoLayout.setVerticalGroup(
            pnlTipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(7, 6, 17));
        jLabel6.setText("TIPOS DE PAGO");

        button11.setBackground(new java.awt.Color(8, 170, 250));
        button11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        button11.setFocusPainted(false);
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });

        spPago.setBackground(new java.awt.Color(255, 255, 255));
        spPago.setBorder(null);
        spPago.setForeground(new java.awt.Color(255, 255, 255));
        spPago.setFocusable(false);
        spPago.setOpaque(false);

        tDatosSucursal = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosUsuarios6.setBackground(new java.awt.Color(234, 241, 251));
        tDatosUsuarios6.setForeground(new java.awt.Color(255, 255, 255));
        tDatosUsuarios6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCION"
            }
        ));
        tDatosUsuarios6.setAltoHead(30);
        tDatosUsuarios6.setAutoscrolls(false);
        tDatosUsuarios6.setColorBackgoundHead(new java.awt.Color(243, 248, 255));
        tDatosUsuarios6.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosUsuarios6.setColorFilasBackgound2(new java.awt.Color(248, 248, 248));
        tDatosUsuarios6.setColorFilasForeground1(new java.awt.Color(150, 160, 175));
        tDatosUsuarios6.setColorFilasForeground2(new java.awt.Color(150, 160, 175));
        tDatosUsuarios6.setColorForegroundHead(new java.awt.Color(7, 6, 17));
        tDatosUsuarios6.setColorSelBackgound(new java.awt.Color(224, 237, 255));
        tDatosUsuarios6.setColorSelForeground(new java.awt.Color(102, 102, 102));
        tDatosUsuarios6.setFocusable(false);
        tDatosUsuarios6.setFuenteHead(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tDatosUsuarios6.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosUsuarios6.setGrosorBordeFilas(0);
        tDatosUsuarios6.setGrosorBordeHead(0);
        tDatosUsuarios6.setOpaque(false);
        tDatosUsuarios6.setRowHeight(30);
        tDatosUsuarios6.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosUsuarios6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosUsuarios6MouseClicked(evt);
            }
        });
        spPago.setViewportView(tDatosUsuarios6);

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spPago, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button11, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPago, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        panelBuscarSuc.setBackground(new java.awt.Color(255, 255, 255));

        radioButton6.setBackground(new java.awt.Color(255, 255, 255));
        radioButton6.setForeground(new java.awt.Color(7, 6, 17));
        radioButton6.setText("ESTADO");
        radioButton6.setFocusPainted(false);
        radioButton6.setRequestFocusEnabled(false);

        radioButton4.setBackground(new java.awt.Color(255, 255, 255));
        radioButton4.setForeground(new java.awt.Color(7, 6, 17));
        radioButton4.setText("NOMBRE");
        radioButton4.setFocusPainted(false);
        radioButton4.setRequestFocusEnabled(false);

        jLabel17.setForeground(new java.awt.Color(7, 6, 17));
        jLabel17.setText("Buscar por:");

        lblTotalRegistros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        textField9.setBackground(new java.awt.Color(255, 255, 255));
        textField9.setForeground(new java.awt.Color(0, 153, 204));
        textField9.setLabelText("Escribe la sucursal que deseas buscar....");

        javax.swing.GroupLayout panelBuscarSucLayout = new javax.swing.GroupLayout(panelBuscarSuc);
        panelBuscarSuc.setLayout(panelBuscarSucLayout);
        panelBuscarSucLayout.setHorizontalGroup(
            panelBuscarSucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarSucLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscarSucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBuscarSucLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBuscarSucLayout.createSequentialGroup()
                        .addComponent(radioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 348, Short.MAX_VALUE)
                        .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBuscarSucLayout.setVerticalGroup(
            panelBuscarSucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarSucLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBuscarSucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarSucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(7, 6, 17));
        jLabel15.setText("SUCURSALES");

        button20.setBackground(new java.awt.Color(8, 170, 250));
        button20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        button20.setFocusPainted(false);
        button20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button20ActionPerformed(evt);
            }
        });

        btnEliminarSuc.setBackground(new java.awt.Color(250, 104, 8));
        btnEliminarSuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoEliminar.png"))); // NOI18N
        btnEliminarSuc.setFocusPainted(false);
        btnEliminarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSucActionPerformed(evt);
            }
        });

        button21.setBackground(new java.awt.Color(46, 189, 141));
        button21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        button21.setFocusPainted(false);
        button21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button21ActionPerformed(evt);
            }
        });

        spSucursal.setBackground(new java.awt.Color(255, 255, 255));
        spSucursal.setBorder(null);
        spSucursal.setForeground(new java.awt.Color(255, 255, 255));
        spSucursal.setFocusable(false);
        spSucursal.setOpaque(false);

        tDatosSucursal = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosSucursal.setBackground(new java.awt.Color(255, 255, 255));
        tDatosSucursal.setForeground(new java.awt.Color(234, 241, 251));
        tDatosSucursal.setModel(new javax.swing.table.DefaultTableModel(
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
        tDatosSucursal.setAltoHead(30);
        tDatosSucursal.setColorBackgoundHead(new java.awt.Color(243, 248, 255));
        tDatosSucursal.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tDatosSucursal.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosSucursal.setColorFilasBackgound2(new java.awt.Color(248, 248, 248));
        tDatosSucursal.setColorFilasForeground1(new java.awt.Color(123, 123, 123));
        tDatosSucursal.setColorFilasForeground2(new java.awt.Color(123, 123, 123));
        tDatosSucursal.setColorForegroundHead(new java.awt.Color(7, 6, 17));
        tDatosSucursal.setColorSelBackgound(new java.awt.Color(224, 237, 255));
        tDatosSucursal.setColorSelForeground(new java.awt.Color(102, 102, 102));
        tDatosSucursal.setFocusable(false);
        tDatosSucursal.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosSucursal.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosSucursal.setFuenteHead(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosSucursal.setGridColor(new java.awt.Color(255, 255, 255));
        tDatosSucursal.setGrosorBordeFilas(0);
        tDatosSucursal.setGrosorBordeHead(0);
        tDatosSucursal.setMultipleSeleccion(false);
        tDatosSucursal.setOpaque(false);
        tDatosSucursal.setRowHeight(30);
        tDatosSucursal.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosSucursalMouseClicked(evt);
            }
        });
        spSucursal.setViewportView(tDatosSucursal);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarSuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(roundPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlTipoPago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelSucursalForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBuscarSuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pnlTipoPago, roundPanel3, roundPanel4, roundPanel6});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelSucursalForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBuscarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        spPrincipal.setViewportView(jPanel1);

        lblId_Sucursal.setForeground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblId_Sucursal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(spPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblId_Sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPrincipal))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ocultar();
        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Se actualizao la base de datos!!");
        panel.showNotification();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnActualizarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpresaActionPerformed
        ID_EMPRESA = 1;
        EMP_NOMBRE_COMERCIAL = txtNombreComercial.getText();
        EMP_RAZON_SOCIAL = txtRazonSocial.getText();
        EMP_RUC = txtRuc.getText();
        EMP_MATRIZ = txtMatriz.getText();
        EMP_ESTABLECIMIENTO = txtEstablecimiento.getText();
        EMP_COD_ESTABLECIMIENTO = txtCodEstablecimiento.getText();
        EMP_PUNTO_EMISION = txtCodPuntoEmis.getText();
        EMP_RESOLUCION_CONTRIB_ESPECIAL = txtNumResolContribuyenteEsp.getText();
        EMP_NUM_AGENTE_RETENCION = txtNumAgenteRetencion.getText();
        EMP_LLEVAR_CONTABILIDAD = cbxLlevarContabilidad.getSelectedItem().toString();
        EMP_CONTRIBUYENTE_RIMPE = cbxRimpe.getSelectedItem().toString();
        EMP_TOKEN = cbxToken.getSelectedItem().toString();
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¡¡ACTUALIZAR REGISTRO!!", "¿ Desea actualizar los datos de la empresa ?");
            emp.setID_EMPRESA(ID_EMPRESA);
            emp.setEMP_NOMBRE_COMERCIAL(EMP_NOMBRE_COMERCIAL);
            emp.setEMP_RAZON_SOCIAL(EMP_RAZON_SOCIAL);
            emp.setEMP_RUC(EMP_RUC);
            emp.setEMP_MATRIZ(EMP_MATRIZ);
            emp.setEMP_ESTABLECIMIENTO(EMP_ESTABLECIMIENTO);
            emp.setEMP_COD_ESTABLECIMIENTO(EMP_COD_ESTABLECIMIENTO);
            emp.setEMP_PUNTO_EMISION(EMP_PUNTO_EMISION);
            emp.setEMP_RESOLUCION_CONTRIB_ESPECIAL(EMP_RESOLUCION_CONTRIB_ESPECIAL);
            emp.setEMP_NUM_AGENTE_RETENCION(EMP_NUM_AGENTE_RETENCION);
            emp.setEMP_LLEVAR_CONTABILIDAD(EMP_LLEVAR_CONTABILIDAD);
            emp.setEMP_CONTRIBUYENTE_RIMPE(EMP_CONTRIBUYENTE_RIMPE);
            emp.setEMP_TOKEN(EMP_TOKEN);
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                if (daoEmpresa.update(emp) == "La empresa fue actualizada con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Los datos fueron actualizados!!");
                    panel.showNotification();
                    datosEmpresa();
                } else if (daoEmpresa.update(emp) == "La empresa no fue actualizada!") {
                    panel = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar los datos!!");
                    panel.showNotification();
                    datosEmpresa();
                }
            } else if (obj.getMessageType() == MessageDialogLight.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
            }
        } catch (Exception ex) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion, favor corregirlos!!");
            panel.showNotification();
        }
    }//GEN-LAST:event_btnActualizarEmpresaActionPerformed

    private void button17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button17ActionPerformed
        pnlTipoPago.setVisible(false);
    }//GEN-LAST:event_button17ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        pnlTipoPago.setVisible(true);
        btnEliminarTipoPago.setVisible(false);
    }//GEN-LAST:event_button11ActionPerformed

    private void btnCancelarSuc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSuc1ActionPerformed
        limpiarFormulario();
        ocultar();
    }//GEN-LAST:event_btnCancelarSuc1ActionPerformed

    private void tDatosUsuarios6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosUsuarios6MouseClicked

    }//GEN-LAST:event_tDatosUsuarios6MouseClicked

    private void button20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button20ActionPerformed
        panelSucursalForm.setVisible(false);
        panelBuscarSuc.setVisible(true);
    }//GEN-LAST:event_button20ActionPerformed

    private void btnEliminarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSucActionPerformed
        ID_SUCURSAL = Integer.parseInt(lblId_Sucursal.getText());
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¡¡ELIMINAR REGISTRO!!", "¿ Desea eliminar la sucursal ?");
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                if (daoSuc.delete(ID_SUCURSAL) == "La sucursal fue eliminada con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "La sucursal fue eliminada con exito!!");
                    panel.showNotification();
                    actualizarTablaSucursal();
                    datosEmpresa();
                    limpiarFormulario();
                } else if (daoSuc.delete(ID_SUCURSAL) == "Error al eliminar la sucursal!") {
                    panel = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "No se pudo eliminar la sucursal!!");
                    panel.showNotification();
                    actualizarTablaSucursal();
                    datosEmpresa();
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
    }//GEN-LAST:event_btnEliminarSucActionPerformed

    private void button21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button21ActionPerformed
        limpiarFormulario();
        btnGuardarSuc.setVisible(true);
        panelSucursalForm.setVisible(true);
        cbxSucursalEstado.setVisible(false);
        panelBuscarSuc.setVisible(false);
        btnActualizarSuc.setVisible(false);
        btnEliminarSuc.setVisible(false);
    }//GEN-LAST:event_button21ActionPerformed

    private void tDatosSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosSucursalMouseClicked
        cargarDatosSucursal();
        btnGuardarSuc.setVisible(false);
        btnEliminarSuc.setVisible(true);
        if (evt.getClickCount() == 2) {
            panelSucursalForm.setVisible(true);
            btnActualizarSuc.setVisible(true);
            cbxSucursalEstado.setVisible(true);
        }
    }//GEN-LAST:event_tDatosSucursalMouseClicked

    private void btnGuardarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSucActionPerformed
        FK_EMPRESA = 1;
        SUC_NOMBRE = txtSucNombre.getText();
        SUC_TELEFONO = txtSucTelefono.getText();
        SUC_EMAIL = txtSucEmail.getText();
        SUC_DIRECCION = txtSucDireccion.getText();
        SUC_ESTADO = "EN LINEA";
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¡¡CREAR REGISTRO!!", "¿ Desea crear la sucursal ?");
            suc.setFK_EMPRESA(FK_EMPRESA);
            suc.setSUC_NOMBRE(SUC_NOMBRE);
            suc.setSUC_TELEFONO(SUC_TELEFONO);
            suc.setSUC_EMAIL(SUC_EMAIL);
            suc.setSUC_DIRECCION(SUC_DIRECCION);
            suc.setSUC_ESTADO(SUC_ESTADO);
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                if (daoSuc.add(suc) == "La sucursal fue creada con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "La sucursal fue creada con exito!!");
                    panel.showNotification();
                    actualizarTablaSucursal();
                    datosEmpresa();
                    limpiarFormulario();
                } else if (daoSuc.add(suc) == "La sucursal no fue creada con exito!") {
                    panel = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "No se pudo crear la sucursal!!");
                    panel.showNotification();
                    actualizarTablaSucursal();
                    datosEmpresa();
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
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }//GEN-LAST:event_btnGuardarSucActionPerformed

    private void btnActualizarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarSucActionPerformed
        ID_SUCURSAL = Integer.parseInt(lblId_Sucursal.getText());
        SUC_NOMBRE = txtSucNombre.getText();
        SUC_TELEFONO = txtSucTelefono.getText();
        SUC_EMAIL = txtSucEmail.getText();
        SUC_DIRECCION = txtSucDireccion.getText();
        SUC_ESTADO = cbxSucursalEstado.getSelectedItem().toString();
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¡¡ACTUALIZAR REGISTRO!!", "¿ Desea actualizar la sucursal ?");
            suc.setID_SUCURSAL(ID_SUCURSAL);
            suc.setSUC_NOMBRE(SUC_NOMBRE);
            suc.setSUC_TELEFONO(SUC_TELEFONO);
            suc.setSUC_EMAIL(SUC_EMAIL);
            suc.setSUC_DIRECCION(SUC_DIRECCION);
            suc.setSUC_ESTADO(SUC_ESTADO);
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                if (daoSuc.update(suc) == "La sucursal fue actualizada con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "La sucursal fue actualizada con exito!!");
                    panel.showNotification();
                    ocultar();
                    actualizarTablaSucursal();
                    datosEmpresa();
                    limpiarFormulario();
                } else if (daoSuc.update(suc) == "La sucursal no fue actualizada con exito!") {
                    panel = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar la sucursal!!");
                    panel.showNotification();
                    actualizarTablaSucursal();
                    datosEmpresa();
                    limpiarFormulario();
                }
            } else if (obj.getMessageType() == MessageDialogLight.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
                limpiarFormulario();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion, favor corregirlos!!");
            panel.showNotification();
            limpiarFormulario();
        }

    }//GEN-LAST:event_btnActualizarSucActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnActualizarEmpresa;
    private com.anthony.swing.Button btnActualizarSuc;
    private com.anthony.swing.Button btnCancelarSuc1;
    private com.anthony.swing.Button btnEliminarSuc;
    private com.anthony.swing.Button btnEliminarTipoPago;
    private com.anthony.swing.Button btnGuardarSuc;
    private com.anthony.swing.Button button11;
    private com.anthony.swing.Button button13;
    private com.anthony.swing.Button button14;
    private com.anthony.swing.Button button15;
    private com.anthony.swing.Button button17;
    private com.anthony.swing.Button button20;
    private com.anthony.swing.Button button21;
    private com.anthony.swing.Combobox cbxLlevarContabilidad;
    private com.anthony.swing.Combobox cbxRimpe;
    private com.anthony.swing.Combobox cbxSucursalEstado;
    private com.anthony.swing.Combobox cbxToken;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblId_Sucursal;
    private javax.swing.JLabel lblTotalRegistros;
    private com.anthony.swing.RoundPanel panelBuscarSuc;
    private com.anthony.swing.RoundPanel panelSucursalForm;
    private com.anthony.swing.RoundPanel pnlTipoPago;
    private com.anthony.swing.RadioButton radioButton4;
    private com.anthony.swing.RadioButton radioButton6;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel2;
    private com.anthony.swing.RoundPanel roundPanel3;
    private com.anthony.swing.RoundPanel roundPanel4;
    private com.anthony.swing.RoundPanel roundPanel6;
    private javax.swing.JScrollPane spPago;
    private javax.swing.JScrollPane spPrincipal;
    private javax.swing.JScrollPane spSucursal;
    private rojeru_san.complementos.TableMetro tDatosSucursal;
    private rojeru_san.complementos.TableMetro tDatosUsuarios6;
    private textfield.TextField textField1;
    private textfield.TextField textField2;
    private textfield.TextField textField3;
    private textfield.TextField textField9;
    private textfield.TextField txtCodEstablecimiento;
    private textfield.TextField txtCodPuntoEmis;
    private textfield.TextField txtEstablecimiento;
    private textfield.TextField txtMatriz;
    private textfield.TextField txtNombreComercial;
    private textfield.TextField txtNumAgenteRetencion;
    private textfield.TextField txtNumResolContribuyenteEsp;
    private textfield.TextField txtRazonSocial;
    private textfield.TextField txtRuc;
    private textfield.TextField txtSucDireccion;
    private textfield.TextField txtSucEmail;
    private textfield.TextField txtSucNombre;
    private textfield.TextField txtSucTelefono;
    // End of variables declaration//GEN-END:variables
}

//class RoundBorder1 implements Border {
//
//    /*        
//        PARA QUITAR EL BORDE POR DEFECTO DE LA TABLA        
//     */
//    private int r;
//
//    RoundBorder1(int r) {
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
