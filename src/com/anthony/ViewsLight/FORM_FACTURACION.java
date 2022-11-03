package com.anthony.ViewsLight;

import com.anthony.MainLight.MainAdministrador;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.dialog.MessageDialogDark;
import com.anthony.swing.scrollbar.ScrollBarCustomClaro;
import com.anthony.toast.Toast;
import java.awt.*;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.*;

public class FORM_FACTURACION extends javax.swing.JPanel {

    /* ================================== 
     INSTANCIAS NECESARIAS 
    ================================== */
    String[] titulosProductos = {"#", "Producto", "Empresa", "Sucursal", "Detalle", "Categoria", "Cod. Princ.", "Cod. Aux", "Det. Extra", "Stock", "Precio Fabrica", "% Ganancia", "P.V.P.", "Estado"};
    DefaultTableModel dtmProductos = new DefaultTableModel(null, titulosProductos);
    String[] titulosClientes = {"#", "Nombres", "Apellidos", "Cedula", "RUC", "Telefono", "Direccion", "Email", "Estado"};
    DefaultTableModel dtmClientes = new DefaultTableModel(null, titulosClientes);
    String[] titulosFactura = {"Codigo Principal", "Codigo auxiliar", "Cantidad", "Descripcion", "Detalle adicional", "Precio Total"};
    DefaultTableModel dtmFactura = new DefaultTableModel(null, titulosFactura);
    DefaultTableModel dtmDetalleFactura = new DefaultTableModel();
    RoundBorder border = new RoundBorder(0);
    CLIENTE_DAO daoCli = new CLIENTE_DAO();
    PRODUCTO_DAO daoPro = new PRODUCTO_DAO();
    PRODUCTO pro = new PRODUCTO();
    CLIENTE cli = new CLIENTE();
    MainAdministrador admin;
    static ResultSet rs = null;
    String criterio, busqueda;
    USUARIO usu;
    Toast panel;

    /*
    variables detalle factura
     */
    DecimalFormat df = new DecimalFormat("#.00");
    int stock = 0;
    int cantidad = 0;
    double precio;
    Double PrecioTotal;
    Double PVP;
    double iva;
    double subtotal;
    double total;

    public FORM_FACTURACION() {
        initComponents();
        init();
        this.admin = admin;
        this.usu = usu;
        tablaClientes();
        tablaProductos();
        scroll();
    }

    public FORM_FACTURACION(USUARIO usu, MainAdministrador admin) {
        initComponents();
        init();
        this.admin = admin;
        this.usu = usu;
        tablaClientes();
        tablaProductos();
        scroll();
    }

    private void init() {
        lblTotalPagar.setText("Total a Pagar");
        lblIva.setText("IVA 12%");
        lblDescuento.setText("Descuento");
        lblSubtotal.setText("Subtotal");
        lblSubtotal0.setText("Sub. 0%");
        lblSubtotal12.setText("Sub. 12%");
        lblSubNoObjIva.setText("Sub. no obj. IVA");
        lblSubTotalExento.setText("Sub. excento IVA");
    }

    private void scroll() {
        spClientes.setVerticalScrollBar(new ScrollBarCustomClaro());
        spClientes.setHorizontalScrollBar(new ScrollBarCustomClaro());
        spProductos.setVerticalScrollBar(new ScrollBarCustomClaro());
        spProductos.setHorizontalScrollBar(new ScrollBarCustomClaro());
        spFactura.setVerticalScrollBar(new ScrollBarCustomClaro());
        spFactura.setHorizontalScrollBar(new ScrollBarCustomClaro());
        spPanel.setVerticalScrollBar(new ScrollBarCustomClaro());
        spPanel.setHorizontalScrollBar(new ScrollBarCustomClaro());
        spClientes.getViewport().setOpaque(false);
        spProductos.getViewport().setOpaque(false);
        spFactura.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        ScrollBarCustom sbh = new ScrollBarCustom();
        spClientes.setVerticalScrollBar(new ScrollBarCustomClaro());
        spClientes.setBorder(border);
        spProductos.setVerticalScrollBar(new ScrollBarCustomClaro());
        spProductos.setBorder(border);
        spFactura.setVerticalScrollBar(new ScrollBarCustomClaro());
        spFactura.setBorder(border);
        spClientes.setVerticalScrollBar(new ScrollBarCustomClaro());
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

    public void tablaProductos() {
        dtmProductos = new DefaultTableModel(null, titulosProductos);
        String fila[] = new String[14];
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
            dtmProductos.addRow(fila);
        }
        tDatosProductos.setModel(dtmProductos);
        autoajustarColumnas(tDatosProductos);
    }

    private void datosCliente() {
        int fila;
        fila = tDatosClientes.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmClientes = (DefaultTableModel) tDatosClientes.getModel();
            lblIdCliente.setText((String) dtmClientes.getValueAt(fila, 0));
            txtApellidosCliente.setText((String) dtmClientes.getValueAt(fila, 1) + " " + (String) dtmClientes.getValueAt(fila, 2));
            txtCedula.setText((String) dtmClientes.getValueAt(fila, 3));
            txtRuc.setText((String) dtmClientes.getValueAt(fila, 4));
            txtDireccion.setText((String) dtmClientes.getValueAt(fila, 6));
        }
    }

    private void buscarProductoCodPrin() {
        busqueda = txtCodPrinc.getText();
        criterio = "PRO_COD_PRINC";
        try {
            rs = daoPro.BUSCAR_PRODUCTO(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[14];
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
    }

    public void cargarDatosProductos() {
        int fila;
        fila = tDatosProductos.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmProductos = (DefaultTableModel) tDatosProductos.getModel();
            lblIdProducto.setText((String) dtmProductos.getValueAt(0, 0));

            txtNombreProd.setText((String) dtmProductos.getValueAt(0, 1));
            txtCodPrinc.setText((String) dtmProductos.getValueAt(0, 6));
            txtCodAux.setText((String) dtmProductos.getValueAt(0, 7));
            txtDetalleExtra.setText((String) dtmProductos.getValueAt(0, 8));
            txtStock.setText((String) dtmProductos.getValueAt(0, 9));
            txtPVP.setText((String) dtmProductos.getValueAt(0, 12));

        }
    }

    public void buscarProducto() {
        busqueda = txtNombreProd.getText();
        criterio = "PRO_NOMBRE";
        try {
            rs = daoPro.BUSCAR_PRODUCTO(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[14];
            //Eliminar los datos que se encuentran en la tabla
            int f, i;
            f = dtmProductos.getRowCount();
            if (f > 0) {
                for (i = 0; i < f; i++) {
                    dtmProductos.removeRow(0);
                }
            }
            //Llenar la tabla con la informacion de acuerdo al criterio y el texto de la busqueda
            try {
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
                    dtmProductos.addRow(Datos);
                    encuentra = true;
                }
            } catch (Exception e) {
                System.out.println(e);
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
    }

    private void limpiarFormProducto() {
        txtCodPrinc.setText("");
        txtCodAux.setText("");
        txtPVP.setText("");
        txtStock.setText("");
        txtCantidadProducto.setText("");
        txtNombreProd.setText("");
        txtDetalleExtra.setText("");
        txtCodPrinc.requestFocus();
    }

    public void cargarDatosProductosTabla() {
        int fila;
        fila = tDatosProductos.getSelectedRow();
        if (fila == -1) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Se debe seleccionar un registro !!");
            panel.showNotification();
        } else {
            dtmProductos = (DefaultTableModel) tDatosProductos.getModel();
            lblIdProducto.setText((String) dtmProductos.getValueAt(fila, 0));
            txtNombreProd.setText((String) dtmProductos.getValueAt(fila, 1));
            txtCodPrinc.setText((String) dtmProductos.getValueAt(fila, 6));
            txtCodAux.setText((String) dtmProductos.getValueAt(fila, 7));
            txtDetalleExtra.setText((String) dtmProductos.getValueAt(fila, 8));
            txtStock.setText((String) dtmProductos.getValueAt(fila, 9));
            txtPVP.setText((String) dtmProductos.getValueAt(fila, 12));
        }
    }

    private void agregarProducto() {
        dtmDetalleFactura = (DefaultTableModel) tDatosFactura.getModel();
        int item;
        int contador = 0;
        double total;
        item = contador + 1;
        String nombreProducto = txtNombreProd.getText();
        String detalleExtra = txtDetalleExtra.getText();
        String codPrincipal = txtCodPrinc.getText();
        String codAuxiliar = txtCodAux.getText();
        precio = Double.parseDouble(txtPVP.getText());
//        precio = Double.parseDouble(df.format(precio));
        cantidad = Integer.parseInt(txtCantidadProducto.getText());
        int stock = Integer.parseInt(txtStock.getText());
        total = cantidad * precio;
        total = Double.parseDouble(df.format(total));
        ArrayList listaFactura = new ArrayList();
        if (stock <= 0) {
            System.out.println("Nada");
        } else if (stock > 0) {
            listaFactura.add(codPrincipal);
            listaFactura.add(codAuxiliar);
            listaFactura.add(cantidad);
            listaFactura.add(nombreProducto);
            listaFactura.add(detalleExtra);
            listaFactura.add(precio);
            listaFactura.add(total);
            Object[] ob = new Object[7];
            ob[0] = listaFactura.get(0);
            ob[1] = listaFactura.get(1);
            ob[2] = listaFactura.get(2);
            ob[3] = listaFactura.get(3);
            ob[4] = listaFactura.get(4);
            ob[5] = listaFactura.get(5);
            ob[6] = listaFactura.get(6);
            dtmDetalleFactura.addRow(ob);
            tDatosFactura.setModel(dtmDetalleFactura);
            txtDescuento.setText("00.00");
            SubTotal();
            Iva();
            Total();
            autoajustarColumnas(tDatosFactura);
        }
    }

    private void SubTotal() {
        iva = 0;
        subtotal = 0;
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            cantidad = Integer.parseInt(tDatosFactura.getValueAt(i, 2).toString());
            PrecioTotal = Double.parseDouble(tDatosFactura.getValueAt(i, 6).toString());
            subtotal = subtotal + PrecioTotal;
        }
//        txtSubtotal.setText("" + df.format(subtotal));
//        txtSubtotal12.setText("" + df.format(subtotal));
        txtSubtotal.setText("" + subtotal);
        txtSubtotal12.setText("" + subtotal);
    }

    private void Iva() {
        double iva = 0;
        subtotal = 0;
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            subtotal = Double.parseDouble(txtSubtotal.getText());
            iva = 0.12 * subtotal;
//            iva = Double.parseDouble(df.format(iva));
        }
        //lblIva.setText(df.format(iva));
        txtIva.setText("" + iva);
    }

    private void Total() {
        double iva = 0;
        double total = 0;
        subtotal = 0;
        Double descuento = Double.parseDouble(txtDescuento.getText());
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            subtotal = Double.parseDouble(txtSubtotal.getText());
            iva = 0.12 * subtotal;
            total = (iva + subtotal) - descuento;
//            total = Double.parseDouble(df.format(total));
        }
        lblTotal.setText("" + total);
    }

    private void verficarTotal() {
        Double total = Double.parseDouble(lblTotal.getText());
        String cliente = txtApellidosCliente.getText();
        if (total > 200 && cliente.equalsIgnoreCase("CONSUMIDOR FINAL")) {
            panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "No debe ser consumidor final!!");
            panel.showNotification();
            switchCliente.setEnabled(false);
            txtApellidosCliente.setText("");
            txtCedula.setText("");
            txtRuc.setText("");
            txtDireccion.setText("");
            txtApellidosCliente.setEditable(true);
            txtCedula.setEditable(true);
            txtRuc.setEditable(true);
            txtDireccion.setEditable(true);
            txtApellidosCliente.requestFocus();
            spPanel.getVerticalScrollBar().setValue(panelFormClientes.getY());
            tabbedPane.setSelectedComponent(panelClientes);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        spPanel = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        panelBuscar = new com.anthony.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelFormClientes = new com.anthony.swing.RoundPanel();
        txtApellidosCliente = new textfield.TextField();
        txtCedula = new textfield.TextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        switchCliente = new com.anthony.swing.SwitchButton();
        txtRuc = new textfield.TextField();
        txtDireccion = new textfield.TextField();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        tabbedPane = new com.anthony.swing.TabbedPane();
        panelClientes = new javax.swing.JPanel();
        spClientes = new javax.swing.JScrollPane();
        tDatosClientes = new rojeru_san.complementos.TableMetro();
        panelProductos = new javax.swing.JPanel();
        spProductos = new javax.swing.JScrollPane();
        tDatosProductos = new rojeru_san.complementos.TableMetro();
        panelFactura = new javax.swing.JPanel();
        spFactura = new javax.swing.JScrollPane();
        tDatosFactura = new rojeru_san.complementos.TableMetro();
        panelFormProductos = new com.anthony.swing.RoundPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCodPrinc = new textfield.TextField();
        txtCodAux = new textfield.TextField();
        txtPVP = new textfield.TextField();
        btnNuevoDetalle = new com.anthony.swing.Button();
        btnEliminar1 = new com.anthony.swing.Button();
        txtCantidadProducto = new textfield.TextField();
        txtStock = new textfield.TextField();
        txtNombreProd = new textfield.TextField();
        txtDetalleExtra = new textfield.TextField();
        btnCancelarSuc1 = new com.anthony.swing.Button();
        btnGuardarSuc1 = new com.anthony.swing.Button();
        btnActualizar = new com.anthony.swing.Button();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        progress3 = new com.anthony.swing.progress.Progress();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        roundPanel3 = new com.anthony.swing.RoundPanel();
        progress6 = new com.anthony.swing.progress.Progress();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        roundPanel6 = new com.anthony.swing.RoundPanel();
        progress8 = new com.anthony.swing.progress.Progress();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        roundPanel7 = new com.anthony.swing.RoundPanel();
        lblSubtotal12 = new javax.swing.JLabel();
        txtSubtotal12 = new javax.swing.JLabel();
        roundPanel9 = new com.anthony.swing.RoundPanel();
        jLabel33 = new javax.swing.JLabel();
        lblSubtotal0 = new javax.swing.JLabel();
        roundPanel10 = new com.anthony.swing.RoundPanel();
        jLabel35 = new javax.swing.JLabel();
        lblSubNoObjIva = new javax.swing.JLabel();
        roundPanel17 = new com.anthony.swing.RoundPanel();
        txtSubtotal = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        roundPanel18 = new com.anthony.swing.RoundPanel();
        txtIva = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        roundPanel19 = new com.anthony.swing.RoundPanel();
        txtDescuento = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        roundPanel4 = new com.anthony.swing.RoundPanel();
        progress7 = new com.anthony.swing.progress.Progress();
        jLabel21 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        roundPanel20 = new com.anthony.swing.RoundPanel();
        lblTotal = new javax.swing.JLabel();
        lblTotalPagar = new javax.swing.JLabel();
        roundPanel11 = new com.anthony.swing.RoundPanel();
        jLabel43 = new javax.swing.JLabel();
        lblSubTotalExento = new javax.swing.JLabel();
        lblIdCliente = new javax.swing.JLabel();
        lblIdProducto = new javax.swing.JLabel();

        setBackground(new java.awt.Color(234, 241, 251));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("FACTURACION");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        spPanel.setBackground(new java.awt.Color(233, 233, 233));
        spPanel.setBorder(null);

        jPanel4.setBackground(new java.awt.Color(234, 241, 251));

        panelBuscar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("NUMERO DE COMPROBANTE:");

        jLabel3.setForeground(new java.awt.Color(7, 6, 17));
        jLabel3.setText("123-123-000000001");

        jLabel4.setForeground(new java.awt.Color(7, 6, 17));
        jLabel4.setText("Martes, 18 de octubre del 2022");

        jLabel5.setForeground(new java.awt.Color(102, 153, 255));
        jLabel5.setText("FECHA DE EMISION:");

        jLabel6.setForeground(new java.awt.Color(7, 6, 17));
        jLabel6.setText("123-123-000000001");

        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("GUIA DE REMISION:");

        jLabel8.setForeground(new java.awt.Color(7, 6, 17));
        jLabel8.setText("12345678901234567890123456789012345678901234567890");

        jLabel9.setForeground(new java.awt.Color(102, 153, 255));
        jLabel9.setText("CLAVE DE ACCESO:");

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)))
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(3, 3, 3))
        );

        panelFormClientes.setBackground(new java.awt.Color(255, 255, 255));

        txtApellidosCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidosCliente.setForeground(new java.awt.Color(0, 110, 162));
        txtApellidosCliente.setLabelText("Nombre del cliente / Razon social");
        txtApellidosCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtApellidosClienteMouseClicked(evt);
            }
        });
        txtApellidosCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidosClienteKeyReleased(evt);
            }
        });

        txtCedula.setBackground(new java.awt.Color(255, 255, 255));
        txtCedula.setForeground(new java.awt.Color(0, 110, 162));
        txtCedula.setLabelText("Identificacion");
        txtCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCedulaMouseClicked(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("DATOS DEL COMPRADOR");

        jLabel11.setForeground(new java.awt.Color(7, 6, 17));
        jLabel11.setText("Consumidor final:");

        switchCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                switchClienteMouseClicked(evt);
            }
        });

        txtRuc.setBackground(new java.awt.Color(255, 255, 255));
        txtRuc.setForeground(new java.awt.Color(0, 110, 162));
        txtRuc.setLabelText("RUC");
        txtRuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRucMouseClicked(evt);
            }
        });
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucKeyReleased(evt);
            }
        });

        txtDireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccion.setForeground(new java.awt.Color(0, 110, 162));
        txtDireccion.setLabelText("Direccion");

        javax.swing.GroupLayout panelFormClientesLayout = new javax.swing.GroupLayout(panelFormClientes);
        panelFormClientes.setLayout(panelFormClientesLayout);
        panelFormClientesLayout.setHorizontalGroup(
            panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormClientesLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(17, 17, 17)
                        .addComponent(switchCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormClientesLayout.createSequentialGroup()
                        .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellidosCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(6, 6, 6))
        );
        panelFormClientesLayout.setVerticalGroup(
            panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormClientesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(switchCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new java.awt.BorderLayout());

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setForeground(new java.awt.Color(7, 6, 17));

        panelClientes.setBackground(new java.awt.Color(255, 255, 255));
        panelClientes.setLayout(new java.awt.BorderLayout());

        spClientes.setBackground(new java.awt.Color(255, 255, 255));
        spClientes.setBorder(null);
        spClientes.setForeground(new java.awt.Color(255, 255, 255));
        spClientes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spClientes.setFocusable(false);

        tDatosClientes = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosClientes.setBackground(new java.awt.Color(255, 255, 255));
        tDatosClientes.setForeground(new java.awt.Color(255, 255, 255));
        tDatosClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosClientes.setAltoHead(30);
        tDatosClientes.setAutoscrolls(false);
        tDatosClientes.setColorBackgoundHead(new java.awt.Color(243, 248, 255));
        tDatosClientes.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tDatosClientes.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosClientes.setColorFilasBackgound2(new java.awt.Color(248, 248, 248));
        tDatosClientes.setColorFilasForeground1(new java.awt.Color(123, 123, 123));
        tDatosClientes.setColorFilasForeground2(new java.awt.Color(123, 123, 123));
        tDatosClientes.setColorForegroundHead(new java.awt.Color(7, 6, 17));
        tDatosClientes.setColorSelBackgound(new java.awt.Color(224, 237, 255));
        tDatosClientes.setColorSelForeground(new java.awt.Color(102, 102, 102));
        tDatosClientes.setFocusable(false);
        tDatosClientes.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosClientes.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosClientes.setFuenteHead(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tDatosClientes.setGridColor(new java.awt.Color(255, 255, 255));
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

        panelClientes.add(spClientes, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("CLIENTES", panelClientes);

        panelProductos.setBackground(new java.awt.Color(255, 255, 255));
        panelProductos.setLayout(new java.awt.BorderLayout());

        spProductos.setBackground(new java.awt.Color(255, 255, 255));
        spProductos.setBorder(null);
        spProductos.setForeground(new java.awt.Color(255, 255, 255));
        spProductos.setFocusable(false);

        tDatosProductos = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosProductos.setBackground(new java.awt.Color(255, 255, 255));
        tDatosProductos.setForeground(new java.awt.Color(255, 255, 255));
        tDatosProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosProductos.setAltoHead(30);
        tDatosProductos.setColorBackgoundHead(new java.awt.Color(243, 248, 255));
        tDatosProductos.setColorBordeFilas(new java.awt.Color(7, 6, 17));
        tDatosProductos.setColorBordeHead(new java.awt.Color(7, 6, 17));
        tDatosProductos.setColorFilasBackgound2(new java.awt.Color(248, 248, 248));
        tDatosProductos.setColorFilasForeground1(new java.awt.Color(123, 123, 123));
        tDatosProductos.setColorFilasForeground2(new java.awt.Color(123, 123, 123));
        tDatosProductos.setColorForegroundHead(new java.awt.Color(7, 6, 17));
        tDatosProductos.setColorSelBackgound(new java.awt.Color(224, 237, 255));
        tDatosProductos.setColorSelForeground(new java.awt.Color(102, 102, 102));
        tDatosProductos.setFocusable(false);
        tDatosProductos.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProductos.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProductos.setFuenteHead(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tDatosProductos.setGridColor(new java.awt.Color(255, 255, 255));
        tDatosProductos.setGrosorBordeFilas(0);
        tDatosProductos.setGrosorBordeHead(0);
        tDatosProductos.setOpaque(false);
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

        panelFactura.setBackground(new java.awt.Color(255, 255, 255));
        panelFactura.setLayout(new java.awt.BorderLayout());

        spFactura.setBackground(new java.awt.Color(255, 255, 255));
        spFactura.setBorder(null);
        spFactura.setForeground(new java.awt.Color(255, 255, 255));
        spFactura.setFocusable(false);

        tDatosFactura = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosFactura.setBackground(new java.awt.Color(255, 255, 255));
        tDatosFactura.setForeground(new java.awt.Color(234, 241, 251));
        tDatosFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Principal", "Codigo auxiliar", "Cantidad", "Descripcion", "Detalle extra", "P.V.P", "Precio Total"
            }
        ));
        tDatosFactura.setAltoHead(30);
        tDatosFactura.setColorBackgoundHead(new java.awt.Color(243, 248, 255));
        tDatosFactura.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tDatosFactura.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tDatosFactura.setColorFilasBackgound2(new java.awt.Color(248, 248, 248));
        tDatosFactura.setColorFilasForeground1(new java.awt.Color(123, 123, 123));
        tDatosFactura.setColorFilasForeground2(new java.awt.Color(123, 123, 123));
        tDatosFactura.setColorForegroundHead(new java.awt.Color(7, 6, 17));
        tDatosFactura.setColorSelBackgound(new java.awt.Color(224, 237, 255));
        tDatosFactura.setColorSelForeground(new java.awt.Color(102, 102, 102));
        tDatosFactura.setFocusable(false);
        tDatosFactura.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosFactura.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosFactura.setFuenteHead(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tDatosFactura.setGridColor(new java.awt.Color(255, 255, 255));
        tDatosFactura.setGrosorBordeFilas(0);
        tDatosFactura.setGrosorBordeHead(0);
        tDatosFactura.setOpaque(false);
        tDatosFactura.setRowHeight(30);
        tDatosFactura.setSelectionBackground(new java.awt.Color(32, 32, 32));
        spFactura.setViewportView(tDatosFactura);

        panelFactura.add(spFactura, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("DETALLE DE LA FACTURA", panelFactura);

        roundPanel1.add(tabbedPane, java.awt.BorderLayout.CENTER);

        panelFormProductos.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 153, 255));
        jLabel12.setText("DATOS DEL PRODUCTO");

        txtCodPrinc.setBackground(new java.awt.Color(255, 255, 255));
        txtCodPrinc.setForeground(new java.awt.Color(0, 110, 162));
        txtCodPrinc.setLabelText("Codigo principal");
        txtCodPrinc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodPrincKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodPrincKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodPrincKeyTyped(evt);
            }
        });

        txtCodAux.setBackground(new java.awt.Color(255, 255, 255));
        txtCodAux.setForeground(new java.awt.Color(0, 110, 162));
        txtCodAux.setLabelText("Codigo auxiliar");
        txtCodAux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodAuxKeyReleased(evt);
            }
        });

        txtPVP.setBackground(new java.awt.Color(255, 255, 255));
        txtPVP.setForeground(new java.awt.Color(0, 110, 162));
        txtPVP.setFocusable(false);
        txtPVP.setLabelText("Precio");

        btnNuevoDetalle.setBackground(new java.awt.Color(46, 189, 141));
        btnNuevoDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        btnNuevoDetalle.setBorderPainted(false);
        btnNuevoDetalle.setFocusPainted(false);
        btnNuevoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDetalleActionPerformed(evt);
            }
        });

        btnEliminar1.setBackground(new java.awt.Color(250, 104, 8));
        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoEliminar.png"))); // NOI18N
        btnEliminar1.setBorderPainted(false);
        btnEliminar1.setFocusPainted(false);
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        txtCantidadProducto.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidadProducto.setForeground(new java.awt.Color(0, 110, 162));
        txtCantidadProducto.setLabelText("Cantidad");

        txtStock.setBackground(new java.awt.Color(255, 255, 255));
        txtStock.setForeground(new java.awt.Color(0, 110, 162));
        txtStock.setFocusable(false);
        txtStock.setLabelText("Stock");

        txtNombreProd.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreProd.setForeground(new java.awt.Color(0, 110, 162));
        txtNombreProd.setLabelText("Producto");
        txtNombreProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProdKeyReleased(evt);
            }
        });

        txtDetalleExtra.setBackground(new java.awt.Color(255, 255, 255));
        txtDetalleExtra.setForeground(new java.awt.Color(0, 110, 162));
        txtDetalleExtra.setFocusable(false);
        txtDetalleExtra.setLabelText("Detalle extra");

        javax.swing.GroupLayout panelFormProductosLayout = new javax.swing.GroupLayout(panelFormProductos);
        panelFormProductos.setLayout(panelFormProductosLayout);
        panelFormProductosLayout.setHorizontalGroup(
            panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormProductosLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelFormProductosLayout.createSequentialGroup()
                        .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelFormProductosLayout.createSequentialGroup()
                                .addComponent(txtCodPrinc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodAux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtNombreProd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormProductosLayout.createSequentialGroup()
                                .addComponent(txtPVP, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCantidadProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtDetalleExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        panelFormProductosLayout.setVerticalGroup(
            panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelFormProductosLayout.createSequentialGroup()
                        .addComponent(btnNuevoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormProductosLayout.createSequentialGroup()
                        .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodPrinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPVP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDetalleExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        btnCancelarSuc1.setBackground(new java.awt.Color(250, 104, 8));
        btnCancelarSuc1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarSuc1.setText("CANCELAR");
        btnCancelarSuc1.setBorderPainted(false);
        btnCancelarSuc1.setFocusPainted(false);
        btnCancelarSuc1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCancelarSuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSuc1ActionPerformed(evt);
            }
        });

        btnGuardarSuc1.setBackground(new java.awt.Color(46, 189, 141));
        btnGuardarSuc1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarSuc1.setText("GUARDAR");
        btnGuardarSuc1.setBorderPainted(false);
        btnGuardarSuc1.setFocusPainted(false);
        btnGuardarSuc1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        btnActualizar.setBackground(new java.awt.Color(235, 190, 25));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("IMPRIMIR");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        progress3.setBorder(null);
        progress3.setForeground(new java.awt.Color(153, 102, 0));
        progress3.setMaximum(150);
        progress3.setValue(95);
        progress3.setBorderPainted(false);

        jLabel13.setForeground(new java.awt.Color(150, 160, 175));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Fatura firmada");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 162, 81));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("REALIZADA");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(progress3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(19, 19, 19))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        progress6.setBorder(null);
        progress6.setForeground(new java.awt.Color(153, 102, 0));
        progress6.setMaximum(150);
        progress6.setValue(95);
        progress6.setBorderPainted(false);

        jLabel19.setForeground(new java.awt.Color(150, 160, 175));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Fatura enviada");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 162, 81));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("REALIZADA");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(progress6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));

        progress8.setBorder(null);
        progress8.setForeground(new java.awt.Color(153, 102, 0));
        progress8.setMaximum(150);
        progress8.setValue(95);
        progress8.setBorderPainted(false);

        jLabel38.setForeground(new java.awt.Color(150, 160, 175));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("SRI procesando");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 162, 81));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("REALIZADO");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(progress8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblSubtotal12.setForeground(new java.awt.Color(247, 122, 108));
        lblSubtotal12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtotal12.setText("iva");

        txtSubtotal12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtSubtotal12.setForeground(new java.awt.Color(102, 153, 255));
        txtSubtotal12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSubtotal12.setText("-- --");

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtotal12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubtotal12, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubtotal12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubtotal12, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 153, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("-- --");

        lblSubtotal0.setForeground(new java.awt.Color(247, 122, 108));
        lblSubtotal0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtotal0.setText("iva");

        javax.swing.GroupLayout roundPanel9Layout = new javax.swing.GroupLayout(roundPanel9);
        roundPanel9.setLayout(roundPanel9Layout);
        roundPanel9Layout.setHorizontalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtotal0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel9Layout.setVerticalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubtotal0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(150, 160, 175));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("-- --");

        lblSubNoObjIva.setForeground(new java.awt.Color(247, 122, 108));
        lblSubNoObjIva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubNoObjIva.setText("iva");

        javax.swing.GroupLayout roundPanel10Layout = new javax.swing.GroupLayout(roundPanel10);
        roundPanel10.setLayout(roundPanel10Layout);
        roundPanel10Layout.setHorizontalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubNoObjIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel10Layout.setVerticalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubNoObjIva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel17.setBackground(new java.awt.Color(255, 255, 255));

        txtSubtotal.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        txtSubtotal.setForeground(new java.awt.Color(102, 153, 255));
        txtSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSubtotal.setText("-- --");

        lblSubtotal.setForeground(new java.awt.Color(247, 122, 108));
        lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtotal.setText("iva");

        javax.swing.GroupLayout roundPanel17Layout = new javax.swing.GroupLayout(roundPanel17);
        roundPanel17.setLayout(roundPanel17Layout);
        roundPanel17Layout.setHorizontalGroup(
            roundPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel17Layout.setVerticalGroup(
            roundPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubtotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel18.setBackground(new java.awt.Color(255, 255, 255));

        txtIva.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtIva.setForeground(new java.awt.Color(7, 6, 17));
        txtIva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtIva.setText("-- --");

        lblIva.setForeground(new java.awt.Color(247, 122, 108));
        lblIva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIva.setText("iva");

        javax.swing.GroupLayout roundPanel18Layout = new javax.swing.GroupLayout(roundPanel18);
        roundPanel18.setLayout(roundPanel18Layout);
        roundPanel18Layout.setHorizontalGroup(
            roundPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel18Layout.setVerticalGroup(
            roundPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel19.setBackground(new java.awt.Color(255, 255, 255));

        txtDescuento.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtDescuento.setForeground(new java.awt.Color(150, 160, 175));
        txtDescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDescuento.setText("-- --");

        lblDescuento.setForeground(new java.awt.Color(247, 122, 108));
        lblDescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescuento.setText("iva");

        javax.swing.GroupLayout roundPanel19Layout = new javax.swing.GroupLayout(roundPanel19);
        roundPanel19.setLayout(roundPanel19Layout);
        roundPanel19Layout.setHorizontalGroup(
            roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel19Layout.setVerticalGroup(
            roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescuento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        progress7.setBorder(null);
        progress7.setForeground(new java.awt.Color(153, 102, 0));
        progress7.setMaximum(150);
        progress7.setValue(95);
        progress7.setBorderPainted(false);

        jLabel21.setForeground(new java.awt.Color(150, 160, 175));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Respuesta");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 162, 81));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("APROBADA");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(progress7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel20.setBackground(new java.awt.Color(255, 255, 255));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(7, 6, 17));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("-- --");

        lblTotalPagar.setForeground(new java.awt.Color(247, 122, 108));
        lblTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalPagar.setText("iva");

        javax.swing.GroupLayout roundPanel20Layout = new javax.swing.GroupLayout(roundPanel20);
        roundPanel20.setLayout(roundPanel20Layout);
        roundPanel20Layout.setHorizontalGroup(
            roundPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel20Layout.setVerticalGroup(
            roundPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(150, 160, 175));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("-- --");

        lblSubTotalExento.setForeground(new java.awt.Color(247, 122, 108));
        lblSubTotalExento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubTotalExento.setText("iva");

        javax.swing.GroupLayout roundPanel11Layout = new javax.swing.GroupLayout(roundPanel11);
        roundPanel11.setLayout(roundPanel11Layout);
        roundPanel11Layout.setHorizontalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubTotalExento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel11Layout.setVerticalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubTotalExento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFormClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFormProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(roundPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(roundPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {roundPanel10, roundPanel17, roundPanel18, roundPanel19, roundPanel7, roundPanel9});

        spPanel.setViewportView(jPanel4);

        lblIdCliente.setForeground(new java.awt.Color(22, 23, 23));

        lblIdProducto.setForeground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdProducto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(spPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIdProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPanel))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        spPanel.getVerticalScrollBar().setValue(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tDatosClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosClientesMouseClicked
        datosCliente();
        txtApellidosCliente.setEditable(false);
        txtCedula.setEditable(false);
        txtRuc.setEditable(false);
        txtDireccion.setEditable(false);
        tabbedPane.setSelectedComponent(panelProductos);
        txtCodPrinc.requestFocus();
        spPanel.getVerticalScrollBar().setValue(panelFormProductos.getY());
    }//GEN-LAST:event_tDatosClientesMouseClicked

    private void btnNuevoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDetalleActionPerformed
        if (txtCantidadProducto.getText().length() == 0 || txtCantidadProducto.getText() == null) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Debe agregar una cantidad!!");
            panel.showNotification();
            txtCantidadProducto.requestFocus();
        }
        cantidad = Integer.parseInt(txtCantidadProducto.getText());
        stock = Integer.parseInt(txtStock.getText());
        if (cantidad > stock) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tenemos sufiente en Stock");
            panel.showNotification();
        } else {
            agregarProducto();
            limpiarFormProducto();
            tablaProductos();
            tabbedPane.setSelectedComponent(panelFactura);
        }
        verficarTotal();
    }//GEN-LAST:event_btnNuevoDetalleActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnCancelarSuc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSuc1ActionPerformed

    }//GEN-LAST:event_btnCancelarSuc1ActionPerformed

    private void txtApellidosClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtApellidosClienteMouseClicked
        spPanel.getVerticalScrollBar().setValue(panelFormClientes.getY());
        tabbedPane.setSelectedComponent(panelClientes);
    }//GEN-LAST:event_txtApellidosClienteMouseClicked

    private void txtApellidosClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosClienteKeyReleased
        criterio = "CLI_APELLIDOS";
        busqueda = txtApellidosCliente.getText();
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
    }//GEN-LAST:event_txtApellidosClienteKeyReleased

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
        criterio = "CLI_CEDULA";
        busqueda = txtCedula.getText();
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
    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased
        criterio = "CLI_RUC";
        busqueda = txtRuc.getText();
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
    }//GEN-LAST:event_txtRucKeyReleased

    private void switchClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_switchClienteMouseClicked
        if (switchCliente.isSelected()) {
            lblIdCliente.setText("1");
            txtApellidosCliente.setText("CONSUMIDOR FINAL");
            txtCedula.setText("N / A");
            txtRuc.setText("N / A");
            txtDireccion.setText("N / A");
            txtApellidosCliente.setEditable(false);
            txtCedula.setEditable(false);
            txtRuc.setEditable(false);
            txtDireccion.setEditable(false);
            spPanel.getVerticalScrollBar().setValue(panelFormProductos.getY());
            tabbedPane.setSelectedComponent(panelProductos);
            txtCodPrinc.requestFocus();
        } else {
            txtApellidosCliente.setText("");
            txtCedula.setText("");
            txtRuc.setText("");
            txtDireccion.setText("");
            txtApellidosCliente.setEditable(true);
            txtCedula.setEditable(true);
            txtRuc.setEditable(true);
            txtDireccion.setEditable(true);
            txtApellidosCliente.requestFocus();
            spPanel.getVerticalScrollBar().setValue(panelFormClientes.getY());
            tabbedPane.setSelectedComponent(panelClientes);
        }
    }//GEN-LAST:event_switchClienteMouseClicked

    private void txtCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCedulaMouseClicked
        spPanel.getVerticalScrollBar().setValue(panelFormClientes.getY());
        tabbedPane.setSelectedComponent(panelClientes);
    }//GEN-LAST:event_txtCedulaMouseClicked

    private void txtRucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRucMouseClicked
        spPanel.getVerticalScrollBar().setValue(panelFormClientes.getY());
        tabbedPane.setSelectedComponent(panelClientes);
    }//GEN-LAST:event_txtRucMouseClicked

    private void txtCodPrincKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPrincKeyReleased
        busqueda = txtCodPrinc.getText();
        criterio = "PRO_COD_PRINC";
        try {
            rs = daoPro.BUSCAR_PRODUCTO(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[14];
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
                dtmProductos.addRow(Datos);
                encuentra = true;
                if (encuentra == true) {
                    if (tDatosProductos.getRowCount() == 1) {
                        dtmProductos = (DefaultTableModel) tDatosProductos.getModel();
                        lblIdProducto.setText((String) dtmProductos.getValueAt(0, 0));
                        txtNombreProd.setText((String) dtmProductos.getValueAt(0, 1));
                        txtCodAux.setText((String) dtmProductos.getValueAt(0, 7));
                        txtDetalleExtra.setText((String) dtmProductos.getValueAt(0, 8));
                        txtStock.setText((String) dtmProductos.getValueAt(0, 9));
                        txtPVP.setText((String) dtmProductos.getValueAt(0, 12));
                    }
                }
            }
            if (encuentra == false) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Producto no registrado!!");
                panel.showNotification();
                lblIdProducto.setText("");
                txtNombreProd.setText("");
                txtCodAux.setText("");
                txtDetalleExtra.setText("");
                txtStock.setText("");
                txtPVP.setText("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en el try");
        }
        tDatosProductos.setModel(dtmProductos);
        autoajustarColumnas(tDatosProductos);
        if (txtCodPrinc.getText().length() == 0) {
            lblIdProducto.setText("");
            txtNombreProd.setText("");
            txtCodAux.setText("");
            txtDetalleExtra.setText("");
            txtStock.setText("");
            txtPVP.setText("");
        }
    }//GEN-LAST:event_txtCodPrincKeyReleased

    private void txtCodPrincKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPrincKeyTyped
        // TODO add your handling code here:


    }//GEN-LAST:event_txtCodPrincKeyTyped

    private void txtCodPrincKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPrincKeyPressed


    }//GEN-LAST:event_txtCodPrincKeyPressed

    private void txtCodAuxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodAuxKeyReleased
        busqueda = txtCodAux.getText();
        criterio = "PRO_COD_AUX";
        try {
            rs = daoPro.BUSCAR_PRODUCTO(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[14];
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
                dtmProductos.addRow(Datos);
                encuentra = true;
                if (encuentra == true) {
                    if (tDatosProductos.getRowCount() == 1) {
                        dtmProductos = (DefaultTableModel) tDatosProductos.getModel();
                        lblIdProducto.setText((String) dtmProductos.getValueAt(0, 0));
                        txtNombreProd.setText((String) dtmProductos.getValueAt(0, 1));
                        txtCodPrinc.setText((String) dtmProductos.getValueAt(0, 6));
                        txtDetalleExtra.setText((String) dtmProductos.getValueAt(0, 8));
                        txtStock.setText((String) dtmProductos.getValueAt(0, 9));
                        txtPVP.setText((String) dtmProductos.getValueAt(0, 12));
                    }
                }
            }
            if (encuentra == false) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Producto no registrado!!");
                panel.showNotification();
                lblIdProducto.setText("");
                txtNombreProd.setText("");
                txtCodPrinc.setText("");
                txtDetalleExtra.setText("");
                txtStock.setText("");
                txtPVP.setText("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en el try");
        }
        tDatosProductos.setModel(dtmProductos);
        autoajustarColumnas(tDatosProductos);
        if (txtCodAux.getText().length() == 0) {
            lblIdProducto.setText("");
            txtNombreProd.setText("");
            txtCodPrinc.setText("");
            txtCodAux.setText("");
            txtDetalleExtra.setText("");
            txtStock.setText("");
            txtPVP.setText("");
        }
    }//GEN-LAST:event_txtCodAuxKeyReleased

    private void txtNombreProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProdKeyReleased
        buscarProducto();
        if (txtNombreProd.getText().length() == 0) {
            lblIdProducto.setText("");
            txtNombreProd.setText("");
            txtCodPrinc.setText("");
            txtCodAux.setText("");
            txtDetalleExtra.setText("");
            txtStock.setText("");
            txtPVP.setText("");
        }
    }//GEN-LAST:event_txtNombreProdKeyReleased

    private void tDatosProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosProductosMouseClicked
        cargarDatosProductosTabla();
    }//GEN-LAST:event_tDatosProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnActualizar;
    private com.anthony.swing.Button btnCancelarSuc1;
    private com.anthony.swing.Button btnEliminar1;
    private com.anthony.swing.Button btnGuardarSuc1;
    private com.anthony.swing.Button btnNuevoDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblSubNoObjIva;
    private javax.swing.JLabel lblSubTotalExento;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotal0;
    private javax.swing.JLabel lblSubtotal12;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalPagar;
    private com.anthony.swing.RoundPanel panelBuscar;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelFactura;
    private com.anthony.swing.RoundPanel panelFormClientes;
    private com.anthony.swing.RoundPanel panelFormProductos;
    private javax.swing.JPanel panelProductos;
    private com.anthony.swing.progress.Progress progress3;
    private com.anthony.swing.progress.Progress progress6;
    private com.anthony.swing.progress.Progress progress7;
    private com.anthony.swing.progress.Progress progress8;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel10;
    private com.anthony.swing.RoundPanel roundPanel11;
    private com.anthony.swing.RoundPanel roundPanel17;
    private com.anthony.swing.RoundPanel roundPanel18;
    private com.anthony.swing.RoundPanel roundPanel19;
    private com.anthony.swing.RoundPanel roundPanel2;
    private com.anthony.swing.RoundPanel roundPanel20;
    private com.anthony.swing.RoundPanel roundPanel3;
    private com.anthony.swing.RoundPanel roundPanel4;
    private com.anthony.swing.RoundPanel roundPanel6;
    private com.anthony.swing.RoundPanel roundPanel7;
    private com.anthony.swing.RoundPanel roundPanel9;
    private javax.swing.JScrollPane spClientes;
    private javax.swing.JScrollPane spFactura;
    private javax.swing.JScrollPane spPanel;
    private javax.swing.JScrollPane spProductos;
    private com.anthony.swing.SwitchButton switchCliente;
    private rojeru_san.complementos.TableMetro tDatosClientes;
    private rojeru_san.complementos.TableMetro tDatosFactura;
    private rojeru_san.complementos.TableMetro tDatosProductos;
    private com.anthony.swing.TabbedPane tabbedPane;
    private textfield.TextField txtApellidosCliente;
    private textfield.TextField txtCantidadProducto;
    private textfield.TextField txtCedula;
    private textfield.TextField txtCodAux;
    private textfield.TextField txtCodPrinc;
    private javax.swing.JLabel txtDescuento;
    private textfield.TextField txtDetalleExtra;
    private textfield.TextField txtDireccion;
    private javax.swing.JLabel txtIva;
    private textfield.TextField txtNombreProd;
    private textfield.TextField txtPVP;
    private textfield.TextField txtRuc;
    private textfield.TextField txtStock;
    private javax.swing.JLabel txtSubtotal;
    private javax.swing.JLabel txtSubtotal12;
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
