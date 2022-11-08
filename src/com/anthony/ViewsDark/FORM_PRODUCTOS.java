package com.anthony.ViewsDark;

import com.anthony.Controller.Convertidor;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.ModelsDAO.SUCURSAL_DAO;
import com.anthony.dialog.MessageDialogDark;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.toast.Toast;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class FORM_PRODUCTOS extends javax.swing.JPanel {

    /* ==============================
    OBJETOS PRINCIPALES
    ============================== */
    String[] titulosProductos = {"#", "Producto", "Empresa", "Sucursal", "Detalle", "Categoria", "Cod. Princ.", "Cod. Aux", "Det. Extra", "Stock", "Precio Fabrica", "% Ganancia", "P.V.P.", "Estado", "Tipo Iva"};
    DefaultTableModel dtmProductos = new DefaultTableModel(null, titulosProductos);
    String[] titulosProveedores = {"#", "Sucursal", "Empresa", "Contacto", "Ruc", "Telefono", "Email", "Direccion", "Web", "Estado"};
    DefaultTableModel dtmProveedores = new DefaultTableModel(null, titulosProveedores);
    DecimalFormat df = new DecimalFormat("#.00");
    Convertidor convertidor = new Convertidor();
    RoundBorder border = new RoundBorder(0);
    PRODUCTO_DAO daoPro = new PRODUCTO_DAO();
    SUCURSAL_DAO daoSuc = new SUCURSAL_DAO();
    PROVEEDOR_DAO daoProv = new PROVEEDOR_DAO();
    PROVEEDOR prov = new PROVEEDOR();
    PRODUCTO pro = new PRODUCTO();
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
    VARIABLES PRINCIPALES
    ============================== */
    private Integer ID_PRODUCTO;
    private Integer FK_SUCURSAL;
    private String FK_SUCURSAL_TEXT;
    private Integer FK_PROVEEDOR;
    private String PRO_NOMBRE;
    private String PRO_DETALLE;
    private String PRO_CATEGORIA;
    private String PRO_COD_PRINC;
    private String PRO_COD_AUX;
    private String PRO_DETALLE_EXTRA;
    private Integer PRO_STOCK;
    private Double PRO_PRECIO_FABRICA;
    private Integer PRO_GANANCIA;
    private String PRO_GANANCIA_TEXT;
    private Double PRO_PVP;
    private String PRO_CREACION;
    private String PRO_ESTADO;
    private String PRO_TIPO_IVA;
    private String PRO_TIPO_IVA_TEXT;


    /* ==============================
    CONSTRUCTORES
    ============================== */
    public FORM_PRODUCTOS() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        datosSpinner();
        datosSucursal();
        tablaProductos();
    }

    public FORM_PRODUCTOS(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        scroll();
        ocultar();
        datosSpinner();
        datosSucursal();
        tablaProductos();
        tablaProveedores();
    }

    private void scroll() {
        spPanel.setVerticalScrollBar(new ScrollBarCustom());
        spPanel.setHorizontalScrollBar(new ScrollBarCustom());
        spProductos.setVerticalScrollBar(new ScrollBarCustom());
        spProductos.setHorizontalScrollBar(new ScrollBarCustom());
        spProductos.setVerticalScrollBar(new ScrollBarCustom());
        spProveedores.setVerticalScrollBar(new ScrollBarCustom());
        spProveedores.setHorizontalScrollBar(new ScrollBarCustom());
        spProveedores.setVerticalScrollBar(new ScrollBarCustom());
        spProductos.getViewport().setOpaque(false);
        spProveedores.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        ScrollBarCustom sbh = new ScrollBarCustom();
        spProveedores.setVerticalScrollBar(new ScrollBarCustom());
        spProveedores.setBorder(border);
        spProveedores.setVerticalScrollBar(new ScrollBarCustom());
        spProductos.setVerticalScrollBar(new ScrollBarCustom());
        spProductos.setBorder(border);
        spProductos.setVerticalScrollBar(new ScrollBarCustom());
    }

    private void ocultar() {
        tabbedPane.setSelectedComponent(panelProductos);
        panelBuscar.setVisible(false);
        panelForm.setVisible(false);
        btnEliminar.setVisible(false);
        btnActualizar.setVisible(false);
        cbxEstado.setVisible(false);
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

    private void datosSpinner() {
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"EN LINEA", "DE BAJA"}));
        cbxEstado.setSelectedIndex(-1);
        cbxGanancia.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"0%", "5%", "10%", "15%", "20%", "25%", "30%", "35%", "40%", "45%", "50%", "55%", "60%"}));
        cbxGanancia.setSelectedIndex(1);
        cbxTipoIva.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Objeto de iva", "Excento de iva"}));
        cbxTipoIva.setSelectedIndex(1);
    }

    public void datosSucursal() {
        List<SUCURSAL> listCh = daoSuc.listar();
        Iterator<SUCURSAL> iterCh = listCh.iterator();
        suc = null;
        while (iterCh.hasNext()) {
            suc = iterCh.next();
            cbxSucursal.addItem(suc.getID_SUCURSAL() + ".- " + suc.getSUC_NOMBRE());
        }
    }

    private void limpiarFormulario() {
        cbxEstado.setSelectedIndex(-1);
        txtProveedor.setText("");
        txtNombre.setText("");
        txtDetalle.setText("");
        txtCategoria.setText("");
        txtCodPrinc.setText("");
        txtCodAuxiliar.setText("");
        txtDetalleExtra.setText("");
        txtStock.setText("");
        txtValorFabrica.setText("");
        txtPVP.setText("");
    }

    public void tablaProductos() {
        dtmProductos = new DefaultTableModel(null, titulosProductos);
        String fila[] = new String[15];
        List<PRODUCTO> listCh = daoPro.listar();
        Iterator<PRODUCTO> iterCh = listCh.iterator();
        pro = null;
        while (iterCh.hasNext()) {
            pro = iterCh.next();
            fila[0] = String.valueOf(pro.getID_PRODUCTO());
            fila[1] = pro.getPRO_NOMBRE();
            fila[2] = pro.getSUC_NOMBRE();
            fila[3] = pro.getPRO_EMPRESA();
            fila[4] = pro.getPRO_DETALLE();
            fila[5] = pro.getPRO_CATEGORIA();
            fila[6] = pro.getPRO_COD_PRINC();
            fila[7] = pro.getPRO_COD_AUX();
            fila[8] = pro.getPRO_DETALLE_EXTRA();
            fila[9] = String.valueOf(pro.getPRO_STOCK());
            fila[10] = String.valueOf(pro.getPRO_PRECIO_FABRICA());
            fila[11] = String.valueOf(pro.getPRO_GANANCIA());
            fila[12] = String.valueOf(pro.getPRO_PVP());
            fila[13] = pro.getPRO_ESTADO();
            fila[14] = String.valueOf(pro.getPRO_TIPO_IVA());
            dtmProductos.addRow(fila);
        }
        tabbedPane.setSelectedComponent(panelProductos);
        tDatosProductos.setModel(dtmProductos);
        autoajustarColumnas(tDatosProductos);
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
        busqueda = txtBusquedaProveedor.getText();
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
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Proveedor no encontrado!!");
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

    public void buscarProducto() {
        busqueda = txtBuscarProducto.getText();
        if (rdbtnProducto.isSelected()) {
            criterio = "PRO_NOMBRE";
        } else if (rdbtnCodPrinc.isSelected()) {
            criterio = "PRO_COD_PRINC";
        } else if (rdbtnCodAux.isSelected()) {
            criterio = "PRO_COD_AUX";
        } else if (rdbtnPVP.isSelected()) {
            criterio = "PRO_PVP";
        } else if (rdtbEstadoProd.isSelected()) {
            criterio = "PRO_ESTADO";
        } else if (rdtbCategoria.isSelected()) {
            criterio = "PRO_CATEGORIA";
        }
        try {
            rs = daoPro.BUSCAR_PRODUCTO(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[15];
            //Eliminar los datos que se encuentran en la tabla
            int f, i;
            f = dtmProductos.getRowCount();
            if (f > 0) {
                for (i = 0; i < f; i++) {
                    dtmProductos.removeRow(0);
                }
            }
            //Llenar la tabla con la informacion de acuerdo al criterio y el texto de la busqueda
            while (rs.next()) {
                Datos[0] = (String) rs.getString(1);
                Datos[1] = (String) rs.getString(4);
                Datos[2] = (String) rs.getString(2);
                Datos[3] = (String) rs.getString(3);
                Datos[4] = (String) rs.getString(5);
                Datos[5] = (String) rs.getString(6);
                Datos[6] = (String) rs.getString(7);
                Datos[7] = (String) rs.getString(8);
                Datos[8] = (String) rs.getString(9);
                Datos[9] = (String) rs.getString(10);
                Datos[10] = (String) rs.getString(11);
                Datos[11] = (String) rs.getString(12);
                Datos[12] = (String) rs.getString(13);
                Datos[13] = (String) rs.getString(14);
                Datos[14] = (String) rs.getString(15);
                dtmProductos.addRow(Datos);
                encuentra = true;
            }
            if (encuentra == false) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Producto no encontrado!!");
                panel.showNotification();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en el try");
        }
        tDatosProductos.setModel(dtmProductos);
        autoajustarColumnas(tDatosProductos);
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
            txtProveedor.setText((String) dtmProveedores.getValueAt(fila, 2));
        }
    }

    public void cargarDatosProductos() {
        int fila;
        fila = tDatosProductos.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmProductos = (DefaultTableModel) tDatosProductos.getModel();
            lblIdProducto.setText((String) dtmProductos.getValueAt(fila, 0));
            txtNombre.setText((String) dtmProductos.getValueAt(fila, 1));
            txtProveedor.setText((String) dtmProductos.getValueAt(fila, 2));
            lblIdProveedor.setText(daoProv.id_proveedor((String) dtmProductos.getValueAt(fila, 2)));

            FK_SUCURSAL_TEXT = (String) dtmProductos.getValueAt(fila, 3);
            FK_SUCURSAL = Integer.parseInt(daoSuc.id_sucursal(FK_SUCURSAL_TEXT));
            FK_SUCURSAL_TEXT = FK_SUCURSAL + ".- " + (String) dtmProductos.getValueAt(fila, 3);
            cbxSucursal.setSelectedItem(FK_SUCURSAL_TEXT);
            txtDetalle.setText((String) dtmProductos.getValueAt(fila, 4));
            txtCategoria.setText((String) dtmProductos.getValueAt(fila, 5));
            txtCodPrinc.setText((String) dtmProductos.getValueAt(fila, 6));
            txtCodAuxiliar.setText((String) dtmProductos.getValueAt(fila, 7));
            txtDetalleExtra.setText((String) dtmProductos.getValueAt(fila, 8));
            txtStock.setText((String) dtmProductos.getValueAt(fila, 9));
            txtValorFabrica.setText((String) dtmProductos.getValueAt(fila, 10));
            cbxGanancia.setSelectedItem((String) dtmProductos.getValueAt(fila, 11) + "%");
            txtPVP.setText((String) dtmProductos.getValueAt(fila, 12));
            cbxEstado.setSelectedItem((String) dtmProductos.getValueAt(fila, 13));
            PRO_TIPO_IVA_TEXT = (String) dtmProductos.getValueAt(fila, 14);
            if (PRO_TIPO_IVA_TEXT.equals("12")) {
                cbxTipoIva.setSelectedItem((String) dtmProductos.getValueAt(fila, 14) + "% (Objeto de iva)");
            }
            if (PRO_TIPO_IVA_TEXT.equals("0")) {
                cbxTipoIva.setSelectedItem((String) dtmProductos.getValueAt(fila, 14) + "% (Excento de iva)");
            }
        }
    }

    private void calculo() {
        PRO_GANANCIA_TEXT = cbxGanancia.getSelectedItem().toString();
        PRO_GANANCIA = convertidor.obtenerNumero(PRO_GANANCIA_TEXT.toString());
        PRO_TIPO_IVA_TEXT = cbxTipoIva.getSelectedItem().toString();
        PRO_PRECIO_FABRICA = Double.parseDouble(txtValorFabrica.getText());
        PRO_PVP = ((PRO_GANANCIA * PRO_PRECIO_FABRICA) / 100) + PRO_PRECIO_FABRICA;
        txtPVP.setText("" + PRO_PVP);
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
        tabbedPane = new com.anthony.swing.TabbedPane();
        panelProductos = new javax.swing.JPanel();
        spProductos = new javax.swing.JScrollPane();
        tDatosProductos = new rojeru_san.complementos.TableMetro();
        panelProveedores = new javax.swing.JPanel();
        spProveedores = new javax.swing.JScrollPane();
        tDatosProveedores = new rojeru_san.complementos.TableMetro();
        roundPanel4 = new com.anthony.swing.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBusquedaProveedor = new textfield.TextField();
        rdtbContacto = new com.anthony.swing.RadioButton();
        rdtbEmpresa = new com.anthony.swing.RadioButton();
        rdtnRuc = new com.anthony.swing.RadioButton();
        rdtbDireccion = new com.anthony.swing.RadioButton();
        rdtbEstado = new com.anthony.swing.RadioButton();
        jLabel12 = new javax.swing.JLabel();
        lblTotalRegistros2 = new javax.swing.JLabel();
        panelBuscar = new com.anthony.swing.RoundPanel();
        txtBuscarProducto = new textfield.TextField();
        jLabel17 = new javax.swing.JLabel();
        rdbtnProducto = new com.anthony.swing.RadioButton();
        rdbtnCodPrinc = new com.anthony.swing.RadioButton();
        rdbtnCodAux = new com.anthony.swing.RadioButton();
        rdbtnPVP = new com.anthony.swing.RadioButton();
        lblTotalRegistros = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdtbEstadoProd = new com.anthony.swing.RadioButton();
        rdtbCategoria = new com.anthony.swing.RadioButton();
        panelForm = new com.anthony.swing.RoundPanel();
        txtNombre = new textfield.TextField();
        txtDetalle = new textfield.TextField();
        txtValorFabrica = new textfield.TextField();
        txtCategoria = new textfield.TextField();
        txtCodPrinc = new textfield.TextField();
        btnGuardar = new com.anthony.swing.Button();
        btnActualizar = new com.anthony.swing.Button();
        btnCancelarSuc1 = new com.anthony.swing.Button();
        cbxSucursal = new com.anthony.swing.Combobox();
        txtCodAuxiliar = new textfield.TextField();
        txtDetalleExtra = new textfield.TextField();
        cbxTipoIva = new com.anthony.swing.Combobox();
        txtStock = new textfield.TextField();
        txtPVP = new textfield.TextField();
        txtProveedor = new textfield.TextField();
        cbxGanancia = new com.anthony.swing.Combobox();
        cbxEstado = new com.anthony.swing.Combobox();
        lblIdProveedor = new javax.swing.JLabel();
        lblIdProducto = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("PRODUCTOS");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(55, 29, 29));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoEliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("");
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevoRegistro.setBackground(new java.awt.Color(37, 47, 33));
        btnNuevoRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        btnNuevoRegistro.setFocusPainted(false);
        btnNuevoRegistro.setFocusable(false);
        btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRegistroActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(0, 102, 153));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        btnBuscar1.setFocusPainted(false);
        btnBuscar1.setFocusable(false);
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

        tabbedPane.setBackground(new java.awt.Color(32, 32, 32));
        tabbedPane.setForeground(new java.awt.Color(63, 81, 102));

        panelProductos.setBackground(new java.awt.Color(32, 32, 32));
        panelProductos.setLayout(new java.awt.BorderLayout());

        spProductos.setBackground(new java.awt.Color(32, 32, 32));
        spProductos.setBorder(null);
        spProductos.setForeground(new java.awt.Color(32, 32, 32));
        spProductos.setFocusable(false);
        spProductos.setOpaque(false);

        tDatosProductos = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosProductos.setBackground(new java.awt.Color(32, 32, 32));
        tDatosProductos.setForeground(new java.awt.Color(32, 32, 32));
        tDatosProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosProductos.setAltoHead(30);
        tDatosProductos.setAutoscrolls(false);
        tDatosProductos.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosProductos.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosProductos.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosProductos.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosProductos.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosProductos.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosProductos.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosProductos.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosProductos.setFocusable(false);
        tDatosProductos.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProductos.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProductos.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosProductos.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosProductos.setGrosorBordeFilas(0);
        tDatosProductos.setGrosorBordeHead(0);
        tDatosProductos.setRowHeight(30);
        tDatosProductos.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosProductosMouseClicked(evt);
            }
        });
        spProductos.setViewportView(tDatosProductos);

        panelProductos.add(spProductos, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("PRODUCTOS", panelProductos);

        panelProveedores.setBackground(new java.awt.Color(32, 32, 32));

        spProveedores.setBackground(new java.awt.Color(32, 32, 32));
        spProveedores.setBorder(null);
        spProveedores.setForeground(new java.awt.Color(32, 32, 32));
        spProveedores.setFocusable(false);
        spProveedores.setOpaque(false);

        tDatosProveedores = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosProveedores.setBackground(new java.awt.Color(32, 32, 32));
        tDatosProveedores.setForeground(new java.awt.Color(32, 32, 32));
        tDatosProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        spProveedores.setViewportView(tDatosProveedores);

        roundPanel4.setBackground(new java.awt.Color(32, 32, 32));

        jLabel5.setForeground(new java.awt.Color(96, 96, 96));
        jLabel5.setText("Buscar por:");

        txtBusquedaProveedor.setBackground(new java.awt.Color(32, 32, 32));
        txtBusquedaProveedor.setForeground(new java.awt.Color(0, 153, 204));
        txtBusquedaProveedor.setLabelText("Escribe la sucursal que deseas buscar....");
        txtBusquedaProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaProveedorActionPerformed(evt);
            }
        });
        txtBusquedaProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaProveedorKeyReleased(evt);
            }
        });

        rdtbContacto.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtbContacto);
        rdtbContacto.setForeground(new java.awt.Color(63, 81, 102));
        rdtbContacto.setText("Contacto");
        rdtbContacto.setFocusPainted(false);
        rdtbContacto.setRequestFocusEnabled(false);

        rdtbEmpresa.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtbEmpresa);
        rdtbEmpresa.setForeground(new java.awt.Color(63, 81, 102));
        rdtbEmpresa.setText("Empresa");
        rdtbEmpresa.setFocusPainted(false);
        rdtbEmpresa.setRequestFocusEnabled(false);

        rdtnRuc.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup2.add(rdtnRuc);
        rdtnRuc.setForeground(new java.awt.Color(63, 81, 102));
        rdtnRuc.setText("RUC");
        rdtnRuc.setFocusPainted(false);
        rdtnRuc.setRequestFocusEnabled(false);

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

        lblTotalRegistros2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros2.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusquedaProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
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
                        .addComponent(lblTotalRegistros2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addComponent(txtBusquedaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdtbContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdtbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdtnRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdtbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(lblTotalRegistros2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelProveedoresLayout = new javax.swing.GroupLayout(panelProveedores);
        panelProveedores.setLayout(panelProveedoresLayout);
        panelProveedoresLayout.setHorizontalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelProveedoresLayout.setVerticalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        tabbedPane.addTab("PROVEEDORES", panelProveedores);

        roundPanel1.add(tabbedPane, java.awt.BorderLayout.CENTER);

        panelBuscar.setBackground(new java.awt.Color(32, 32, 32));

        txtBuscarProducto.setBackground(new java.awt.Color(32, 32, 32));
        txtBuscarProducto.setForeground(new java.awt.Color(0, 153, 204));
        txtBuscarProducto.setLabelText("Escribe la sucursal que deseas buscar....");
        txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyTyped(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(96, 96, 96));
        jLabel17.setText("Buscar por:");

        rdbtnProducto.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdbtnProducto);
        rdbtnProducto.setForeground(new java.awt.Color(63, 81, 102));
        rdbtnProducto.setText("Producto");
        rdbtnProducto.setFocusPainted(false);
        rdbtnProducto.setRequestFocusEnabled(false);

        rdbtnCodPrinc.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdbtnCodPrinc);
        rdbtnCodPrinc.setForeground(new java.awt.Color(63, 81, 102));
        rdbtnCodPrinc.setText("Cod. princ");
        rdbtnCodPrinc.setFocusPainted(false);
        rdbtnCodPrinc.setRequestFocusEnabled(false);

        rdbtnCodAux.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdbtnCodAux);
        rdbtnCodAux.setForeground(new java.awt.Color(63, 81, 102));
        rdbtnCodAux.setText("Cod. Aux");
        rdbtnCodAux.setFocusPainted(false);
        rdbtnCodAux.setRequestFocusEnabled(false);

        rdbtnPVP.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdbtnPVP);
        rdbtnPVP.setForeground(new java.awt.Color(63, 81, 102));
        rdbtnPVP.setText("P.V.P");
        rdbtnPVP.setFocusPainted(false);
        rdbtnPVP.setRequestFocusEnabled(false);

        lblTotalRegistros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(96, 96, 96));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("TOTAL DE REGISTROS:");

        rdtbEstadoProd.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbEstadoProd);
        rdtbEstadoProd.setForeground(new java.awt.Color(63, 81, 102));
        rdtbEstadoProd.setText("Estado");
        rdtbEstadoProd.setFocusPainted(false);
        rdtbEstadoProd.setRequestFocusEnabled(false);

        rdtbCategoria.setBackground(new java.awt.Color(32, 32, 32));
        buttonGroup1.add(rdtbCategoria);
        rdtbCategoria.setForeground(new java.awt.Color(63, 81, 102));
        rdtbCategoria.setText("Categoria");
        rdtbCategoria.setFocusPainted(false);
        rdtbCategoria.setRequestFocusEnabled(false);

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(rdbtnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnCodPrinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnCodAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnPVP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbEstadoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbtnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnCodPrinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnCodAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnPVP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(rdtbEstadoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdtbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        panelForm.setBackground(new java.awt.Color(32, 32, 32));

        txtNombre.setBackground(new java.awt.Color(32, 32, 32));
        txtNombre.setForeground(new java.awt.Color(0, 153, 204));
        txtNombre.setLabelText("Nombre del producto");

        txtDetalle.setBackground(new java.awt.Color(32, 32, 32));
        txtDetalle.setForeground(new java.awt.Color(0, 153, 204));
        txtDetalle.setLabelText("Detalle del producto");

        txtValorFabrica.setBackground(new java.awt.Color(32, 32, 32));
        txtValorFabrica.setForeground(new java.awt.Color(0, 153, 204));
        txtValorFabrica.setLabelText("Valor de fabrica");

        txtCategoria.setBackground(new java.awt.Color(32, 32, 32));
        txtCategoria.setForeground(new java.awt.Color(0, 153, 204));
        txtCategoria.setLabelText("Categoria del producto");

        txtCodPrinc.setBackground(new java.awt.Color(32, 32, 32));
        txtCodPrinc.setForeground(new java.awt.Color(0, 153, 204));
        txtCodPrinc.setLabelText("Codigo principal");

        btnGuardar.setBackground(new java.awt.Color(37, 47, 33));
        btnGuardar.setForeground(new java.awt.Color(204, 204, 204));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        txtCodAuxiliar.setBackground(new java.awt.Color(32, 32, 32));
        txtCodAuxiliar.setForeground(new java.awt.Color(0, 153, 204));
        txtCodAuxiliar.setLabelText("Codigo auxiliar");

        txtDetalleExtra.setBackground(new java.awt.Color(32, 32, 32));
        txtDetalleExtra.setForeground(new java.awt.Color(0, 153, 204));
        txtDetalleExtra.setLabelText("Detalle extra");

        cbxTipoIva.setBackground(new java.awt.Color(32, 32, 32));
        cbxTipoIva.setForeground(new java.awt.Color(0, 153, 204));
        cbxTipoIva.setLabeText("Tipo de IVA");

        txtStock.setBackground(new java.awt.Color(32, 32, 32));
        txtStock.setForeground(new java.awt.Color(0, 153, 204));
        txtStock.setLabelText("Stock");

        txtPVP.setEditable(false);
        txtPVP.setBackground(new java.awt.Color(32, 32, 32));
        txtPVP.setForeground(new java.awt.Color(0, 153, 204));
        txtPVP.setLabelText("Precio de vental al publico (P.V.P)");
        txtPVP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPVPMouseClicked(evt);
            }
        });

        txtProveedor.setEditable(false);
        txtProveedor.setBackground(new java.awt.Color(32, 32, 32));
        txtProveedor.setForeground(new java.awt.Color(0, 153, 204));
        txtProveedor.setLabelText("Proveedor");

        cbxGanancia.setBackground(new java.awt.Color(32, 32, 32));
        cbxGanancia.setForeground(new java.awt.Color(0, 153, 204));
        cbxGanancia.setLabeText("Porcentaje de ganancia");
        cbxGanancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxGananciaMouseClicked(evt);
            }
        });

        cbxEstado.setBackground(new java.awt.Color(32, 32, 32));
        cbxEstado.setForeground(new java.awt.Color(0, 153, 204));
        cbxEstado.setLabeText("Estado");

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodPrinc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDetalleExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxGanancia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPVP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtValorFabrica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodAuxiliar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodPrinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDetalleExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxGanancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPVP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxGanancia, cbxSucursal, cbxTipoIva, txtCategoria, txtCodAuxiliar, txtCodPrinc, txtDetalle, txtDetalleExtra, txtNombre, txtPVP, txtProveedor, txtStock, txtValorFabrica});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelForm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
        );

        spPanel.setViewportView(jPanel3);

        lblIdProveedor.setForeground(new java.awt.Color(22, 23, 23));

        lblIdProducto.setForeground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addComponent(spPanel)
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
                        .addComponent(lblIdProveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblIdProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPanel)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ocultar();
        tablaProductos();
        tablaProveedores();
        limpiarFormulario();
        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Se actualizo la base de datos!!");
        panel.showNotification();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRegistroActionPerformed
        tabbedPane.setSelectedComponent(panelProveedores);
        panelForm.setVisible(true);
        btnGuardar.setVisible(true);
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
        tablaProductos();
        tablaProveedores();
        limpiarFormulario();
        ocultar();
    }//GEN-LAST:event_btnCancelarSuc1ActionPerformed

    private void tDatosProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosProductosMouseClicked
        cargarDatosProductos();
        btnGuardar.setVisible(false);
        btnEliminar.setVisible(true);
        if (evt.getClickCount() == 2) {
            panelForm.setVisible(true);
            panelBuscar.setVisible(false);
            btnActualizar.setVisible(true);
            cbxEstado.setVisible(true);
        }
    }//GEN-LAST:event_tDatosProductosMouseClicked

    private void txtBusquedaProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaProveedorActionPerformed

    private void tDatosProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosProveedoresMouseClicked
        cargarDatosProveedores();
    }//GEN-LAST:event_tDatosProveedoresMouseClicked

    private void txtBusquedaProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaProveedorKeyReleased
        buscarProveedor();
    }//GEN-LAST:event_txtBusquedaProveedorKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            calculo();
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas crear el Producto ?", "");
            FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
            FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
            FK_PROVEEDOR = Integer.parseInt(lblIdProveedor.getText());
            PRO_NOMBRE = txtNombre.getText();
            PRO_DETALLE = txtDetalle.getText();
            PRO_CATEGORIA = txtCategoria.getText();
            PRO_COD_PRINC = txtCodPrinc.getText();
            PRO_COD_AUX = txtCodAuxiliar.getText();
            PRO_DETALLE_EXTRA = txtDetalleExtra.getText();
            PRO_STOCK = Integer.parseInt(txtStock.getText());
            PRO_PRECIO_FABRICA = Double.parseDouble(txtValorFabrica.getText());
            PRO_GANANCIA_TEXT = cbxGanancia.getSelectedItem().toString();
            PRO_GANANCIA = convertidor.obtenerNumero(PRO_GANANCIA_TEXT.toString());
            PRO_PVP = Double.parseDouble(txtPVP.getText());
            PRO_CREACION = fecha;
            PRO_ESTADO = "EN LINEA";
            PRO_TIPO_IVA_TEXT = cbxTipoIva.getSelectedItem().toString();
            if (PRO_TIPO_IVA_TEXT.equals("Objeto de iva")) {
                PRO_TIPO_IVA_TEXT = "Objeto de iva";
            } else if (PRO_TIPO_IVA_TEXT.equals("Excento de iva")) {
                PRO_TIPO_IVA_TEXT = "Excento de iva";
            }
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                pro.setFK_SUCURSAL(FK_SUCURSAL);
                pro.setFK_PROVEEDOR(FK_PROVEEDOR);
                pro.setPRO_NOMBRE(PRO_NOMBRE);
                pro.setPRO_DETALLE(PRO_DETALLE);
                pro.setPRO_CATEGORIA(PRO_CATEGORIA);
                pro.setPRO_COD_PRINC(PRO_COD_PRINC);
                pro.setPRO_COD_AUX(PRO_COD_AUX);
                pro.setPRO_DETALLE_EXTRA(PRO_DETALLE_EXTRA);
                pro.setPRO_STOCK(PRO_STOCK);
                pro.setPRO_PRECIO_FABRICA(PRO_PRECIO_FABRICA);
                pro.setPRO_GANANCIA(PRO_GANANCIA);
                pro.setPRO_PVP(PRO_PVP);
                pro.setPRO_CREACION(PRO_CREACION);
                pro.setPRO_ESTADO(PRO_ESTADO);
                pro.setPRO_TIPO_IVA(PRO_TIPO_IVA_TEXT);
                if (daoPro.add(pro) == "El producto fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El producto fue creado con exito!!");
                    panel.showNotification();
                    tablaProductos();
                    tablaProveedores();
                    limpiarFormulario();
                    ocultar();
                } else if (daoPro.add(pro) == "El producto no fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo crear el producto!!");
                    panel.showNotification();
                    tablaProductos();
                    tablaProveedores();
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
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxGananciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxGananciaMouseClicked

    }//GEN-LAST:event_cbxGananciaMouseClicked

    private void txtPVPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPVPMouseClicked
        if (txtValorFabrica.getText().length() == 0) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Debe dar un precio de fabrica al producto");
            panel.showNotification();
        } else {
            calculo();
        }
    }//GEN-LAST:event_txtPVPMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            calculo();
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas actualizar el Producto ?", "");
            ID_PRODUCTO = Integer.parseInt(lblIdProducto.getText());
            FK_SUCURSAL_TEXT = cbxSucursal.getSelectedItem().toString();
            FK_SUCURSAL = convertidor.obtenerNumero(FK_SUCURSAL_TEXT.toString());
            FK_PROVEEDOR = Integer.parseInt(lblIdProveedor.getText());
            PRO_NOMBRE = txtNombre.getText();
            PRO_DETALLE = txtDetalle.getText();
            PRO_CATEGORIA = txtCategoria.getText();
            PRO_COD_PRINC = txtCodPrinc.getText();
            PRO_COD_AUX = txtCodAuxiliar.getText();
            PRO_DETALLE_EXTRA = txtDetalleExtra.getText();
            PRO_STOCK = Integer.parseInt(txtStock.getText());
            PRO_PRECIO_FABRICA = Double.parseDouble(txtValorFabrica.getText());
            PRO_GANANCIA_TEXT = cbxGanancia.getSelectedItem().toString();
            PRO_GANANCIA = convertidor.obtenerNumero(PRO_GANANCIA_TEXT.toString());
            PRO_PVP = Double.parseDouble(txtPVP.getText());
            PRO_ESTADO = cbxEstado.getSelectedItem().toString();
            PRO_TIPO_IVA_TEXT = cbxTipoIva.getSelectedItem().toString();
            if (PRO_TIPO_IVA_TEXT.equals("Objeto de iva")) {
                PRO_TIPO_IVA_TEXT = "Objeto de iva";
            } else if (PRO_TIPO_IVA_TEXT.equals("Excento de iva")) {
                PRO_TIPO_IVA_TEXT = "Excento de iva";
            }
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                pro.setID_PRODUCTO(ID_PRODUCTO);
                pro.setFK_SUCURSAL(FK_SUCURSAL);
                pro.setFK_PROVEEDOR(FK_PROVEEDOR);
                pro.setPRO_NOMBRE(PRO_NOMBRE);
                pro.setPRO_DETALLE(PRO_DETALLE);
                pro.setPRO_CATEGORIA(PRO_CATEGORIA);
                pro.setPRO_COD_PRINC(PRO_COD_PRINC);
                pro.setPRO_COD_AUX(PRO_COD_AUX);
                pro.setPRO_DETALLE_EXTRA(PRO_DETALLE_EXTRA);
                pro.setPRO_STOCK(PRO_STOCK);
                pro.setPRO_PRECIO_FABRICA(PRO_PRECIO_FABRICA);
                pro.setPRO_GANANCIA(PRO_GANANCIA);
                pro.setPRO_PVP(PRO_PVP);
                pro.setPRO_CREACION(PRO_CREACION);
                pro.setPRO_ESTADO(PRO_ESTADO);
                pro.setPRO_TIPO_IVA(PRO_TIPO_IVA_TEXT);
                if (daoPro.update(pro) == "El producto fue actualizado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "El producto fue actualizado con exito!!");
                    panel.showNotification();
                    tablaProductos();
                    tablaProveedores();
                    limpiarFormulario();
                    ocultar();
                } else if (daoPro.update(pro) == "El producto no fue creado con exito!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar el producto!!");
                    panel.showNotification();
                    tablaProductos();
                    tablaProveedores();
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
            System.out.println("Hubo un error al procesar tu peticion!!" + ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtBuscarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyTyped
    }//GEN-LAST:event_txtBuscarProductoKeyTyped

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased
        buscarProducto();
    }//GEN-LAST:event_txtBuscarProductoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnActualizar;
    private com.anthony.swing.Button btnBuscar1;
    private com.anthony.swing.Button btnCancelarSuc1;
    private com.anthony.swing.Button btnEliminar;
    private com.anthony.swing.Button btnGuardar;
    private com.anthony.swing.Button btnNuevoRegistro;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.anthony.swing.Combobox cbxEstado;
    private com.anthony.swing.Combobox cbxGanancia;
    private com.anthony.swing.Combobox cbxSucursal;
    private com.anthony.swing.Combobox cbxTipoIva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblIdProveedor;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JLabel lblTotalRegistros2;
    private com.anthony.swing.RoundPanel panelBuscar;
    private com.anthony.swing.RoundPanel panelForm;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelProveedores;
    private com.anthony.swing.RadioButton rdbtnCodAux;
    private com.anthony.swing.RadioButton rdbtnCodPrinc;
    private com.anthony.swing.RadioButton rdbtnPVP;
    private com.anthony.swing.RadioButton rdbtnProducto;
    private com.anthony.swing.RadioButton rdtbCategoria;
    private com.anthony.swing.RadioButton rdtbContacto;
    private com.anthony.swing.RadioButton rdtbDireccion;
    private com.anthony.swing.RadioButton rdtbEmpresa;
    private com.anthony.swing.RadioButton rdtbEstado;
    private com.anthony.swing.RadioButton rdtbEstadoProd;
    private com.anthony.swing.RadioButton rdtnRuc;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel4;
    private javax.swing.JScrollPane spPanel;
    private javax.swing.JScrollPane spProductos;
    private javax.swing.JScrollPane spProveedores;
    private rojeru_san.complementos.TableMetro tDatosProductos;
    private rojeru_san.complementos.TableMetro tDatosProveedores;
    private com.anthony.swing.TabbedPane tabbedPane;
    private textfield.TextField txtBuscarProducto;
    private textfield.TextField txtBusquedaProveedor;
    private textfield.TextField txtCategoria;
    private textfield.TextField txtCodAuxiliar;
    private textfield.TextField txtCodPrinc;
    private textfield.TextField txtDetalle;
    private textfield.TextField txtDetalleExtra;
    private textfield.TextField txtNombre;
    private textfield.TextField txtPVP;
    private textfield.TextField txtProveedor;
    private textfield.TextField txtStock;
    private textfield.TextField txtValorFabrica;
    // End of variables declaration//GEN-END:variables

    /*
    ===========================
    MOSTRAR LA FILA SELECCIONADA
    ===========================
    tDatosProductos.setToolTipText(
                    "<html>"
                    + "Producto: " + pro.getPRO_NOMBRE()
                    + "<br>"
                    + "Proveedor: " + pro.getPRO_NOMBRE()
                    + "<br>"
                    + "Sucursal: " + pro.getSUC_NOMBRE()
                    + "<br>"
                    + "Detalle: " + pro.getPRO_DETALLE()
                    + "<br>"
                    + "Categoria: " + pro.getPRO_CATEGORIA()
                    + "<br>"
                    + "Cod. Principal: " + pro.getPRO_COD_PRINC()
                    + "<br>"
                    + "Cod. Auxiliar: " + pro.getPRO_COD_AUX()
                    + "<br>"
                    + "Detalle extra:" + pro.getPRO_DETALLE_EXTRA()
                    + "<br>"
                    + "Stock:" + String.valueOf(pro.getPRO_STOCK())
                    + "<br>"
                    + "Precio fabrica:" + String.valueOf(pro.getPRO_PRECIO_FABRICA())
                    + "<br>"
                    + "Porcentaje ganancia:" + String.valueOf(pro.getPRO_GANANCIA()) + "%"
                    + "<br>"
                    + "Valor unitario:" + String.valueOf(pro.getPRO_PVP())
                    + "<br>"
                    + "Estado:" + String.valueOf(pro.getPRO_ESTADO())
                    + "</html>"
            );
     */
}

class RoundBorder1 implements Border {

    /*        
        PARA QUITAR EL BORDE POR DEFECTO DE LA TABLA        
     */
    private int r;

    RoundBorder1(int r) {
        this.r = r;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, r, r);
    }
}
