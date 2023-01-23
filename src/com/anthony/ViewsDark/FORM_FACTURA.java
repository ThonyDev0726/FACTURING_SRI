package com.anthony.ViewsDark;

import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.componentsDark.MessageDialogDark;
import com.anthony.email.ENVIO_MAIL;
import com.anthony.toast.Toast;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.BarcodeQRCode;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FORM_FACTURA extends javax.swing.JPanel {

    /* ================================== 
     INSTANCIAS NECESARIAS 
    ================================== */
    String[] titulosProductos = {"#", "Producto", "Cod. Principal.", "Cod. Auxiliar", "Det. Extra", "Stock", "P.V.P.", "Tipo IVA", "Descuento"};
    DefaultTableModel dtmProductos = new DefaultTableModel(null, titulosProductos);
    String[] titulosClientes = {"#", "Nombres", "Apellidos", "Cedula", "RUC", "Telefono", "Direccion", "Email", "Estado"};
    DefaultTableModel dtmClientes = new DefaultTableModel(null, titulosClientes);
    DefaultTableModel dtmDetalleFactura = new DefaultTableModel();
    RoundBorder border = new RoundBorder(0);
    AJUSTES ajustes = new AJUSTES_DAO().listAjustes();
    CLIENTE_DAO daoCli = new CLIENTE_DAO();
    PRODUCTO_DAO daoPro = new PRODUCTO_DAO();
    PRODUCTO pro = new PRODUCTO();
    CLIENTE cli = new CLIENTE();
    FACTURA fac = new FACTURA();
    FACTURA_DAO daoFac = new FACTURA_DAO();
    EMPRESA emp = new EMPRESA();
    EMPRESA_DAO empDao = new EMPRESA_DAO();
    FACTURA_DESCRIPCION desFac = new FACTURA_DESCRIPCION();
    FACTURA_DESCRIPCION_DAO desFacDao = new FACTURA_DESCRIPCION_DAO();
    FACTURA_TOTALIDAD facTotalidad = new FACTURA_TOTALIDAD();
    FACTURA_TOTALIDAD_DAO facTotDao = new FACTURA_TOTALIDAD_DAO();
    ENVIO_MAIL mail = new ENVIO_MAIL();
    Calendar fecha = Calendar.getInstance();
    int year = fecha.get(Calendar.YEAR);
    int mes = fecha.get(Calendar.MONTH) + 1;

    MainAdministradorDark admin;
    static ResultSet rs = null;
    String criterio, busqueda;
    USUARIO usu;
    Toast panel;

    /*
    variables detalle factura
     */
//    DecimalFormat df = new DecimalFormat("#.00");
    int stock = 0;
    int cantidad = 0;
    double precio;
    Double PrecioTotal;
    Double PVP;
    double iva;
    double subtotal;
    double total;

    public FORM_FACTURA() {
        initComponents();
        init();
        this.admin = admin;
        this.usu = usu;
        tablaClientes();
        tablaProductos();
        datosFecha();
        scroll();
    }

    public FORM_FACTURA(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        init();
        this.admin = admin;
        this.usu = usu;
        tablaClientes();
        tablaProductos();
        datosFecha();
        scroll();
    }

    private void datosEncabezado() {
        emp = empDao.list();
        lblClaveAcceso.setText(daoFac.generadorNumeroFactura(emp.getEMP_RUC(), txtCedula.getText()));
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("¿ Deseas generar la venta al cliente ?", "");
            fac.setFK_EMPLEADO(usu.getFK_EMPLEADO());
            fac.setFK_CLIENTE(Integer.parseInt(lblIdCliente.getText()));
            fac.setFK_SUCURSAL(usu.getFK_SUCURSAL());
            fac.setFAC_FECHA(daoFac.fechaNormal());
            fac.setFAC_HORA(daoFac.horaNormal());
            fac.setFAC_CODIGO(daoFac.generadorNumeroFactura(emp.getEMP_RUC(), txtCedula.getText()));
            fac.setFAC_COD_AUT(daoFac.generadorNumeroFactura(emp.getEMP_RUC(), txtCedula.getText()));
            fac.setFAC_RUTA("C:\\FACTURING_V1\\2022\\NOVIEMBRE\\FACTURAS\\APROBADAS/" + txtCedula.getText() + "(" + daoFac.fecha() + "-" + daoFac.hora() + ").pdf");
            lblPdf.setText(txtCedula.getText() + "(" + daoFac.fecha() + "-" + daoFac.hora() + ").pdf");
            fac.setFAC_ESTADO("PENDIENTE");
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoFac.add(fac) == "La factura fue creada con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "La factura fue creada con exito!!");
                    panel.showNotification();
                    tablaClientes();
                    tabbedPane.setSelectedComponent(panelProductos);
                } else if (daoFac.add(fac) == "La factura no fue creada!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo crear la factura!!");
                    panel.showNotification();
                    tablaClientes();
                    tabbedPane.setSelectedComponent(panelClientes);
                    spPanel.getVerticalScrollBar().setValue(panelFormClientes.getY());
                    txtApellidosCliente.requestFocus();
                }
            } else if (obj.getMessageType() == MessageDialogDark.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
                txtApellidosCliente.setText("");
                txtCedula.setText("");
                txtDireccion.setText("");
                txtRuc.setText("");
                spPanel.getVerticalScrollBar().setValue(0);
                tabbedPane.setSelectedComponent(panelClientes);
                txtApellidosCliente.requestFocus();
            }
        } catch (Exception ex) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion!!");
            panel.showNotification();
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }

    private void datosEncabezadoConsumidor() {
        emp = empDao.list();
        lblClaveAcceso.setText(daoFac.generadorNumeroFacturaCosumidor(emp.getEMP_RUC(), "0000000000"));
        try {
            MessageDialogDark obj = new MessageDialogDark(admin);
            obj.showMessage("GENERAR VENTA", "¿ Deseas generar la venta al cliente ?");
            fac.setFK_EMPLEADO(usu.getFK_EMPLEADO());
            if (txtCedula.getText().equals("N / A")) {
                lblIdCliente.setText("0000000000");
            }
            fac.setFK_CLIENTE(Integer.parseInt(lblIdCliente.getText()));
            fac.setFK_SUCURSAL(usu.getFK_SUCURSAL());
            fac.setFAC_FECHA(daoFac.fechaNormal());
            fac.setFAC_HORA(daoFac.horaNormal());
            fac.setFAC_CODIGO(daoFac.generadorNumeroFacturaCosumidor(emp.getEMP_RUC(), "0000000001"));
            fac.setFAC_COD_AUT(daoFac.generadorNumeroFacturaCosumidor(emp.getEMP_RUC(), "0000000001"));
            fac.setFAC_RUTA("C:\\FACTURING_V1\\2022\\NOVIEMBRE\\FACTURAS\\APROBADAS/" + "0000000001" + "(" + daoFac.fecha() + "-" + daoFac.hora() + ").pdf");
            lblPdf.setText("0000000001" + "(" + daoFac.fecha() + "-" + daoFac.hora() + ").pdf");
            fac.setFAC_ESTADO("APROBADO");
            if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
                if (daoFac.add(fac) == "La factura fue creada con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "La factura fue creada con exito!!");
                    panel.showNotification();
                    tablaClientes();
                } else if (daoFac.add(fac) == "La factura no fue creada!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo crear la factura!!");
                    panel.showNotification();
                    tablaClientes();
                }
            } else if (obj.getMessageType() == MessageDialogDark.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
            }
        } catch (Exception ex) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion!!");
            panel.showNotification();
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }

    private void datosFecha() {
        lblFecha.setText(daoFac.fechaNormal());
    }

    private void init() {
        lblTotal.setText("Total");
        lblIva.setText("IVA 12%");
        lblSub0.setText("Subt 0%");
        lblSub12.setText("Subt 12%");
        lblDescuento.setText("Descuento");
        btnGuardar.setVisible(true);
        btnImprimir.setVisible(false);
        panelDatos.setVisible(false);
        btnCancelar.setVisible(false);
    }

    private void scroll() {
        spClientes.setVerticalScrollBar(new ScrollBarCustom());
        spClientes.setHorizontalScrollBar(new ScrollBarCustom());
        spProductos.setVerticalScrollBar(new ScrollBarCustom());
        spProductos.setHorizontalScrollBar(new ScrollBarCustom());
        spFactura.setVerticalScrollBar(new ScrollBarCustom());
        spFactura.setHorizontalScrollBar(new ScrollBarCustom());
        spPanel.setVerticalScrollBar(new ScrollBarCustom());
        spPanel.setHorizontalScrollBar(new ScrollBarCustom());
        spClientes.getViewport().setOpaque(false);
        spProductos.getViewport().setOpaque(false);
        spFactura.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        ScrollBarCustom sbh = new ScrollBarCustom();
        spClientes.setVerticalScrollBar(new ScrollBarCustom());
        spClientes.setBorder(border);
        spProductos.setVerticalScrollBar(new ScrollBarCustom());
        spProductos.setBorder(border);
        spFactura.setVerticalScrollBar(new ScrollBarCustom());
        spFactura.setBorder(border);
        spClientes.setVerticalScrollBar(new ScrollBarCustom());
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
        String fila[] = new String[9];
        List<PRODUCTO> listCh = daoPro.listarFacturacion();
        Iterator<PRODUCTO> iterCh = listCh.iterator();
        pro = null;
        while (iterCh.hasNext()) {
            pro = iterCh.next();
            fila[0] = String.valueOf(pro.getID_PRODUCTO());
            fila[1] = pro.getPRO_NOMBRE();
            fila[2] = pro.getPRO_COD_PRINC();
            fila[3] = pro.getPRO_COD_AUX();
            fila[4] = pro.getPRO_DETALLE_EXTRA();
            fila[5] = String.valueOf(pro.getPRO_STOCK());
            fila[6] = String.valueOf(pro.getPRO_PVP());
            fila[7] = pro.getPRO_TIPO_IVA();
            fila[8] = String.valueOf(pro.getPRO_DESCUENTO());
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
            txtTelefono.setText((String) dtmClientes.getValueAt(fila, 5));
            txtDireccion.setText((String) dtmClientes.getValueAt(fila, 6));
            txtEmail.setText((String) dtmClientes.getValueAt(fila, 7));
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
            rs = daoPro.BUSCAR_PRODUCTO_FACTURACION(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[9];
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
                    Datos[1] = (String) rs.getString(2);
                    Datos[2] = (String) rs.getString(3);
                    Datos[3] = (String) rs.getString(4);
                    Datos[4] = (String) rs.getString(5);
                    Datos[5] = (String) rs.getString(6);
                    Datos[6] = (String) rs.getString(7);
                    Datos[7] = (String) rs.getString(8);
                    Datos[8] = (String) rs.getString(9);
                    dtmProductos.addRow(Datos);
                    encuentra = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            if (encuentra == false) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Producto no registrado!!");
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
        txtCantidad.setText("");
        txtNombreProd.setText("");
        txtDetalleExtra.setText("");
        txtDescuentoProd.setText("");
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
            txtCodPrinc.setText((String) dtmProductos.getValueAt(fila, 2));
            txtCodAux.setText((String) dtmProductos.getValueAt(fila, 3));
            txtDetalleExtra.setText((String) dtmProductos.getValueAt(fila, 4));
            txtStock.setText((String) dtmProductos.getValueAt(fila, 5));
            txtPVP.setText((String) dtmProductos.getValueAt(fila, 6));
            txtTipoIva.setText((String) dtmProductos.getValueAt(fila, 7));
            txtDescuentoProd.setText((String) dtmProductos.getValueAt(fila, 8));
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
        String descuento = txtDescuentoProd.getText();
        Double descuentoNum;
        precio = Double.parseDouble(txtPVP.getText());
        cantidad = Integer.parseInt(txtCantidad.getText());
        int stock = Integer.parseInt(txtStock.getText());
        total = cantidad * precio;
        descuentoNum = cantidad * Double.parseDouble(descuento);
//        total = Double.parseDouble(df.format(total));
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
            listaFactura.add(descuentoNum);
            listaFactura.add(total);
            Object[] ob = new Object[8];
            ob[0] = listaFactura.get(0);
            ob[1] = listaFactura.get(1);
            ob[2] = listaFactura.get(2);
            ob[3] = listaFactura.get(3);
            ob[4] = listaFactura.get(4);
            ob[5] = listaFactura.get(5);
            ob[6] = listaFactura.get(6);
            ob[7] = listaFactura.get(7);
            dtmDetalleFactura.addRow(ob);
            tDatosFactura.setModel(dtmDetalleFactura);
            txtSub0.setText("0.0");
            SubTotal();
            Descuento();
            Iva();
            Total();
            autoajustarColumnas(tDatosFactura);
            agregarProductoBase(cantidad, precio, total);
        }
    }

    private void agregarProductoBase(int cantidad, double precio, double total) {
        desFac.setFK_FACTURA(Integer.parseInt(daoFac.ultimaFactura_id()));
        desFac.setFK_PRODUCTO(Integer.parseInt(lblIdProducto.getText()));
        desFac.setDET_CANTIDAD(cantidad);
        desFac.setDET_PRECIO(precio);
        desFac.setDET_TOTAL(total);
        if (desFacDao.add(desFac) == "La factura fue creada con exito!") {
            panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.TOP_CENTER, "Producto agregado con exito!!");
            panel.showNotification();
            tablaProductos();
        } else if (desFacDao.add(desFac) == "La factura no fue creada!") {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.TOP_CENTER, "No se pudo agregar el producto!!");
            panel.showNotification();
            tablaProductos();
        }
    }

    private void SubTotal() {
        iva = 0;
        subtotal = 0;
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            cantidad = Integer.parseInt(tDatosFactura.getValueAt(i, 2).toString());
            PrecioTotal = Double.parseDouble(tDatosFactura.getValueAt(i, 7).toString());
            subtotal = subtotal + PrecioTotal;
        }
//        txtSub12.setText("" + df.format(subtotal));
//        txtSubtotal12.setText("" + df.format(subtotal));
        txtSub12.setText("" + subtotal);
//        txtSubtotal12.setText("" + subtotal);
    }

    private void Descuento() {
        iva = 0;
        subtotal = 0;
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            cantidad = Integer.parseInt(tDatosFactura.getValueAt(i, 2).toString());
            PrecioTotal = Double.parseDouble(tDatosFactura.getValueAt(i, 6).toString());
            subtotal = subtotal + PrecioTotal;
        }
//        txtDescuento.setText("" + df.format(subtotal));
//        txtSubtotal12.setText("" + df.format(subtotal));
        txtDescuento.setText("" + subtotal);
//        txtSubtotal12.setText("" + subtotal);
    }

    private void Iva() {
        double iva = 0;
        subtotal = 0;
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            subtotal = Double.parseDouble(txtSub12.getText());
            iva = 0.12 * subtotal;
//            iva = Double.parseDouble(df.format(iva));
        }
//        lblIva.setText(df.format(iva));
        txtIva12.setText("" + iva);
    }

    private void Total() {
        double iva = 0;
        double total = 0;
        subtotal = 0;
        Double descuento = Double.parseDouble(txtDescuento.getText());
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            subtotal = Double.parseDouble(txtSub12.getText());
            iva = 0.12 * subtotal;
            total = (iva + subtotal) - descuento;
//            total = Double.parseDouble(df.format(total));
        }
        txtTotal.setText(total + "");
    }

    private void verficarTotal() {
        Double total = Double.parseDouble(txtTotal.getText());
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

    public void actualizarStock() {
        int nuevoStock;
        int cantidadSolicitada = Integer.parseInt(txtCantidad.getText());
        int stockBaseDatos = Integer.parseInt(txtStock.getText());
        nuevoStock = stockBaseDatos - cantidadSolicitada;
        if (daoPro.actualizar_stock(Integer.parseInt(lblIdProducto.getText()), nuevoStock).equals("El stock fue actualizado con exito!")) {
            System.out.println("Stock actualizado");
        } else if (daoPro.actualizar_stock(Integer.parseInt(lblIdProducto.getText()), nuevoStock).equals("Error al actualizar el stock!")) {
            System.out.println("error al actualizar el stock");
        }
        txtStock.setText(String.valueOf(nuevoStock));
        tablaProductos();
    }

    public void eliminarProducto() {
        int nuevoStock;
        int cantidadSolicitada = Integer.parseInt(txtCantidad.getText());
        int stockBaseDatos = Integer.parseInt(txtStock.getText());
        nuevoStock = stockBaseDatos - cantidadSolicitada;
        if (daoPro.actualizar_stock(Integer.parseInt(lblIdProducto.getText()), nuevoStock).equals("El stock fue actualizado con exito!")) {
            System.out.println("Stock actualizado");
        } else if (daoPro.actualizar_stock(Integer.parseInt(lblIdProducto.getText()), nuevoStock).equals("Error al actualizar el stock!")) {
            System.out.println("error al actualizar el stock");
        }
        System.out.println("Se actualizao el stock");
        txtStock.setText(String.valueOf(nuevoStock));
        tablaProductos();
    }

    private void limpiarFormCliente() {
        txtApellidosCliente.setText("");
        txtCedula.setText("");
        txtRuc.setText("");
        txtDireccion.setText("");
        switchCliente.setEnabled(false);
    }

    private void limpiarInterfaz() {
        limpiarFormCliente();
        limpiarFormProducto();
        limparTablaFactura();
    }

    private void limparTablaFactura() {
        int f, i;
        f = dtmDetalleFactura.getRowCount();
        if (f > 0) {
            for (i = 0; i < f; i++) {
                dtmDetalleFactura.removeRow(0);
            }
        }
        txtDescuento.setText("-- --");
        txtSub0.setText("-- --");
        txtIva12.setText("-- --");
        txtTotal.setText("-- --");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        spPanel = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        panelDatos = new com.anthony.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblClaveAcceso = new javax.swing.JLabel();
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
        txtCantidad = new textfield.TextField();
        txtStock = new textfield.TextField();
        txtNombreProd = new textfield.TextField();
        txtDetalleExtra = new textfield.TextField();
        txtTipoIva = new javax.swing.JLabel();
        txtDescuentoProd = new textfield.TextField();
        btnCancelar = new com.anthony.swing.Button();
        btnGuardar = new com.anthony.swing.Button();
        btnImprimir = new com.anthony.swing.Button();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        progress3 = new com.anthony.swing.progress.Progress();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        roundPanel3 = new com.anthony.swing.RoundPanel();
        progress6 = new com.anthony.swing.progress.Progress();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        roundPanel18 = new com.anthony.swing.RoundPanel();
        txtDescuento = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        roundPanel19 = new com.anthony.swing.RoundPanel();
        txtSub0 = new javax.swing.JLabel();
        lblSub0 = new javax.swing.JLabel();
        roundPanel4 = new com.anthony.swing.RoundPanel();
        progress7 = new com.anthony.swing.progress.Progress();
        jLabel21 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        roundPanel21 = new com.anthony.swing.RoundPanel();
        txtIva12 = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        roundPanel22 = new com.anthony.swing.RoundPanel();
        txtTotal = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        roundPanel23 = new com.anthony.swing.RoundPanel();
        txtSub12 = new javax.swing.JLabel();
        lblSub12 = new javax.swing.JLabel();
        lblIdCliente = new javax.swing.JLabel();
        lblIdProducto = new javax.swing.JLabel();
        lblPdf = new javax.swing.JLabel();
        lblCantidadTabla = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("FACTURACION");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        spPanel.setBackground(new java.awt.Color(22, 23, 23));
        spPanel.setBorder(null);

        jPanel4.setBackground(new java.awt.Color(22, 23, 23));

        panelDatos.setBackground(new java.awt.Color(32, 32, 32));

        jLabel2.setForeground(new java.awt.Color(144, 124, 85));
        jLabel2.setText("NUMERO DE COMPROBANTE:");

        jLabel3.setForeground(new java.awt.Color(0, 110, 162));
        jLabel3.setText("123-123-000000001");

        lblFecha.setForeground(new java.awt.Color(0, 110, 162));
        lblFecha.setText("Martes, 18 de octubre del 2022");

        jLabel5.setForeground(new java.awt.Color(144, 124, 85));
        jLabel5.setText("FECHA DE EMISION:");

        jLabel6.setForeground(new java.awt.Color(0, 110, 162));
        jLabel6.setText("123-123-000000001");

        jLabel7.setForeground(new java.awt.Color(144, 124, 85));
        jLabel7.setText("GUIA DE REMISION:");

        lblClaveAcceso.setForeground(new java.awt.Color(0, 110, 162));
        lblClaveAcceso.setText("12345678901234567890123456789012345678901234567890");

        jLabel9.setForeground(new java.awt.Color(144, 124, 85));
        jLabel9.setText("CLAVE DE ACCESO:");

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClaveAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFecha)
                            .addComponent(jLabel6)))
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, 0)
                        .addComponent(lblClaveAcceso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(3, 3, 3))
        );

        panelFormClientes.setBackground(new java.awt.Color(32, 32, 32));

        txtApellidosCliente.setBackground(new java.awt.Color(32, 32, 32));
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

        txtCedula.setBackground(new java.awt.Color(32, 32, 32));
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
        jLabel10.setForeground(new java.awt.Color(144, 124, 85));
        jLabel10.setText("DATOS DEL COMPRADOR");

        jLabel11.setForeground(new java.awt.Color(0, 110, 162));
        jLabel11.setText("Consumidor final:");

        switchCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                switchClienteMouseClicked(evt);
            }
        });

        txtRuc.setBackground(new java.awt.Color(32, 32, 32));
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

        txtDireccion.setBackground(new java.awt.Color(32, 32, 32));
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
                .addGap(0, 0, 0)
                .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));
        roundPanel1.setLayout(new java.awt.BorderLayout());

        tabbedPane.setBackground(new java.awt.Color(32, 32, 32));
        tabbedPane.setForeground(new java.awt.Color(63, 81, 102));

        panelClientes.setBackground(new java.awt.Color(32, 32, 32));
        panelClientes.setLayout(new java.awt.BorderLayout());

        spClientes.setBackground(new java.awt.Color(32, 32, 32));
        spClientes.setBorder(null);
        spClientes.setForeground(new java.awt.Color(32, 32, 32));
        spClientes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
        tDatosClientes.setFuenteHead(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tDatosClientes.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosClientes.setGrosorBordeFilas(0);
        tDatosClientes.setGrosorBordeHead(0);
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
        tDatosProductos.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosProductos.setColorBordeHead(new java.awt.Color(32, 32, 32));
        tDatosProductos.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosProductos.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosProductos.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosProductos.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosProductos.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosProductos.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosProductos.setFocusable(false);
        tDatosProductos.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProductos.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosProductos.setFuenteHead(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
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

        panelFactura.setBackground(new java.awt.Color(32, 32, 32));
        panelFactura.setLayout(new java.awt.BorderLayout());

        spFactura.setBackground(new java.awt.Color(32, 32, 32));
        spFactura.setBorder(null);
        spFactura.setForeground(new java.awt.Color(32, 32, 32));
        spFactura.setFocusable(false);
        spFactura.setOpaque(false);

        tDatosFactura = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosFactura.setBackground(new java.awt.Color(32, 32, 32));
        tDatosFactura.setForeground(new java.awt.Color(32, 32, 32));
        tDatosFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Principal", "Codigo auxiliar", "Cantidad", "Descripcion", "Detalle extra", "P.V.P", "Descuento", "Precio Total"
            }
        ));
        tDatosFactura.setAltoHead(30);
        tDatosFactura.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosFactura.setColorBordeHead(new java.awt.Color(32, 32, 32));
        tDatosFactura.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosFactura.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosFactura.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosFactura.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosFactura.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosFactura.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosFactura.setFocusable(false);
        tDatosFactura.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosFactura.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosFactura.setFuenteHead(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tDatosFactura.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosFactura.setGrosorBordeFilas(0);
        tDatosFactura.setGrosorBordeHead(0);
        tDatosFactura.setRowHeight(30);
        tDatosFactura.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosFacturaMouseClicked(evt);
            }
        });
        spFactura.setViewportView(tDatosFactura);

        panelFactura.add(spFactura, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("DETALLE DE LA FACTURA", panelFactura);

        roundPanel1.add(tabbedPane, java.awt.BorderLayout.CENTER);

        panelFormProductos.setBackground(new java.awt.Color(32, 32, 32));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(144, 124, 85));
        jLabel12.setText("DATOS DEL PRODUCTO");

        txtCodPrinc.setBackground(new java.awt.Color(32, 32, 32));
        txtCodPrinc.setForeground(new java.awt.Color(0, 110, 162));
        txtCodPrinc.setLabelText("Codigo principal");
        txtCodPrinc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodPrincMouseClicked(evt);
            }
        });
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

        txtCodAux.setBackground(new java.awt.Color(32, 32, 32));
        txtCodAux.setForeground(new java.awt.Color(0, 110, 162));
        txtCodAux.setLabelText("Codigo auxiliar");
        txtCodAux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodAuxMouseClicked(evt);
            }
        });
        txtCodAux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodAuxKeyReleased(evt);
            }
        });

        txtPVP.setBackground(new java.awt.Color(32, 32, 32));
        txtPVP.setForeground(new java.awt.Color(0, 110, 162));
        txtPVP.setFocusable(false);
        txtPVP.setLabelText("Precio");

        btnNuevoDetalle.setBackground(new java.awt.Color(37, 47, 33));
        btnNuevoDetalle.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoDetalle.setText("AGREGAR PRODUCTO");
        btnNuevoDetalle.setBorderPainted(false);
        btnNuevoDetalle.setFocusPainted(false);
        btnNuevoDetalle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDetalleActionPerformed(evt);
            }
        });

        txtCantidad.setBackground(new java.awt.Color(32, 32, 32));
        txtCantidad.setForeground(new java.awt.Color(0, 110, 162));
        txtCantidad.setLabelText("Cantidad");

        txtStock.setBackground(new java.awt.Color(32, 32, 32));
        txtStock.setForeground(new java.awt.Color(0, 110, 162));
        txtStock.setFocusable(false);
        txtStock.setLabelText("Stock");

        txtNombreProd.setBackground(new java.awt.Color(32, 32, 32));
        txtNombreProd.setForeground(new java.awt.Color(0, 110, 162));
        txtNombreProd.setLabelText("Producto");
        txtNombreProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreProdMouseClicked(evt);
            }
        });
        txtNombreProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProdKeyReleased(evt);
            }
        });

        txtDetalleExtra.setBackground(new java.awt.Color(32, 32, 32));
        txtDetalleExtra.setForeground(new java.awt.Color(0, 110, 162));
        txtDetalleExtra.setFocusable(false);
        txtDetalleExtra.setLabelText("Detalle extra");

        txtTipoIva.setForeground(new java.awt.Color(32, 32, 32));

        txtDescuentoProd.setEditable(false);
        txtDescuentoProd.setBackground(new java.awt.Color(32, 32, 32));
        txtDescuentoProd.setForeground(new java.awt.Color(0, 110, 162));
        txtDescuentoProd.setFocusable(false);
        txtDescuentoProd.setLabelText("Descuento");

        javax.swing.GroupLayout panelFormProductosLayout = new javax.swing.GroupLayout(panelFormProductos);
        panelFormProductos.setLayout(panelFormProductosLayout);
        panelFormProductosLayout.setHorizontalGroup(
            panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormProductosLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelFormProductosLayout.createSequentialGroup()
                        .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormProductosLayout.createSequentialGroup()
                                .addComponent(txtCodPrinc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodAux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtNombreProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormProductosLayout.createSequentialGroup()
                                .addComponent(txtPVP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescuentoProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelFormProductosLayout.createSequentialGroup()
                                .addComponent(txtDetalleExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(171, 171, 171)
                                .addComponent(btnNuevoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        panelFormProductosLayout.setVerticalGroup(
            panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormProductosLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFormProductosLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)))
                .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodPrinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPVP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescuentoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDetalleExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNuevoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnCancelar.setBackground(new java.awt.Color(55, 29, 29));
        btnCancelar.setForeground(new java.awt.Color(204, 204, 204));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(37, 47, 33));
        btnGuardar.setForeground(new java.awt.Color(204, 204, 204));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(55, 50, 40));
        btnImprimir.setForeground(new java.awt.Color(204, 204, 204));
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setBorderPainted(false);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        roundPanel2.setBackground(new java.awt.Color(32, 32, 32));

        progress3.setBorder(null);
        progress3.setForeground(new java.awt.Color(153, 102, 0));
        progress3.setMaximum(150);
        progress3.setValue(95);
        progress3.setBorderPainted(false);

        jLabel13.setForeground(new java.awt.Color(144, 124, 85));
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
                .addGap(6, 6, 6)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(progress3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(32, 32, 32));

        progress6.setBorder(null);
        progress6.setForeground(new java.awt.Color(153, 102, 0));
        progress6.setMaximum(150);
        progress6.setValue(95);
        progress6.setBorderPainted(false);

        jLabel19.setForeground(new java.awt.Color(144, 124, 85));
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
                .addGap(6, 6, 6)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(progress6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        roundPanel18.setBackground(new java.awt.Color(32, 32, 32));

        txtDescuento.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtDescuento.setForeground(new java.awt.Color(63, 81, 102));
        txtDescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDescuento.setText("-- --");

        lblDescuento.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblDescuento.setForeground(new java.awt.Color(144, 124, 85));
        lblDescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescuento.setText("des");

        javax.swing.GroupLayout roundPanel18Layout = new javax.swing.GroupLayout(roundPanel18);
        roundPanel18.setLayout(roundPanel18Layout);
        roundPanel18Layout.setHorizontalGroup(
            roundPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel18Layout.setVerticalGroup(
            roundPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel19.setBackground(new java.awt.Color(32, 32, 32));

        txtSub0.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtSub0.setForeground(new java.awt.Color(63, 81, 102));
        txtSub0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSub0.setText("-- --");

        lblSub0.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblSub0.setForeground(new java.awt.Color(144, 124, 85));
        lblSub0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub0.setText("sub0");

        javax.swing.GroupLayout roundPanel19Layout = new javax.swing.GroupLayout(roundPanel19);
        roundPanel19.setLayout(roundPanel19Layout);
        roundPanel19Layout.setHorizontalGroup(
            roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSub0, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel19Layout.setVerticalGroup(
            roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSub0, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSub0, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel4.setBackground(new java.awt.Color(32, 32, 32));

        progress7.setBorder(null);
        progress7.setForeground(new java.awt.Color(153, 102, 0));
        progress7.setMaximum(150);
        progress7.setValue(95);
        progress7.setBorderPainted(false);

        jLabel21.setForeground(new java.awt.Color(144, 124, 85));
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
                .addGap(6, 6, 6)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(progress7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        roundPanel21.setBackground(new java.awt.Color(32, 32, 32));

        txtIva12.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtIva12.setForeground(new java.awt.Color(63, 81, 102));
        txtIva12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtIva12.setText("-- --");

        lblIva.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblIva.setForeground(new java.awt.Color(144, 124, 85));
        lblIva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIva.setText("iv12");

        javax.swing.GroupLayout roundPanel21Layout = new javax.swing.GroupLayout(roundPanel21);
        roundPanel21.setLayout(roundPanel21Layout);
        roundPanel21Layout.setHorizontalGroup(
            roundPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIva12, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel21Layout.setVerticalGroup(
            roundPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIva, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIva12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel22.setBackground(new java.awt.Color(32, 32, 32));

        txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(63, 81, 102));
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotal.setText("-- --");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(144, 124, 85));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("t");

        javax.swing.GroupLayout roundPanel22Layout = new javax.swing.GroupLayout(roundPanel22);
        roundPanel22.setLayout(roundPanel22Layout);
        roundPanel22Layout.setHorizontalGroup(
            roundPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel22Layout.setVerticalGroup(
            roundPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel23.setBackground(new java.awt.Color(32, 32, 32));

        txtSub12.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtSub12.setForeground(new java.awt.Color(63, 81, 102));
        txtSub12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSub12.setText("-- --");

        lblSub12.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblSub12.setForeground(new java.awt.Color(144, 124, 85));
        lblSub12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub12.setText("sub12");

        javax.swing.GroupLayout roundPanel23Layout = new javax.swing.GroupLayout(roundPanel23);
        roundPanel23.setLayout(roundPanel23Layout);
        roundPanel23Layout.setHorizontalGroup(
            roundPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSub12, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel23Layout.setVerticalGroup(
            roundPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSub12, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSub12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(panelDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFormProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {roundPanel2, roundPanel3, roundPanel4});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(roundPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roundPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {roundPanel18, roundPanel19, roundPanel21, roundPanel22, roundPanel23, roundPanel4});

        spPanel.setViewportView(jPanel4);

        lblIdCliente.setForeground(new java.awt.Color(22, 23, 23));

        lblIdProducto.setForeground(new java.awt.Color(22, 23, 23));

        lblPdf.setForeground(new java.awt.Color(22, 23, 23));

        lblCantidadTabla.setForeground(new java.awt.Color(22, 23, 23));

        txtTelefono.setForeground(new java.awt.Color(22, 23, 23));

        txtEmail.setForeground(new java.awt.Color(22, 23, 23));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidadTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(spPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIdProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(lblPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCantidadTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        spPanel.getVerticalScrollBar().setValue(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tDatosClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosClientesMouseClicked
        datosCliente();
        datosEncabezado();
        txtApellidosCliente.setEditable(false);
        txtCedula.setEditable(false);
        txtRuc.setEditable(false);
        txtDireccion.setEditable(false);
        txtCodPrinc.requestFocus();
        spPanel.getVerticalScrollBar().setValue(panelFormClientes.getY());
    }//GEN-LAST:event_tDatosClientesMouseClicked

    private void btnNuevoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDetalleActionPerformed
        try {
            if (txtCantidad.getText().length() == 0 || txtCantidad.getText() == null) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Debe agregar una cantidad!!");
                panel.showNotification();
                txtCantidad.requestFocus();
            }
            cantidad = Integer.parseInt(txtCantidad.getText());
            stock = Integer.parseInt(txtStock.getText());
            if (cantidad > stock) {
                panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tenemos sufiente en Stock");
                panel.showNotification();
            } else {
                agregarProducto();
                actualizarStock();
                limpiarFormProducto();
                tablaProductos();
                tabbedPane.setSelectedComponent(panelFactura);
            }
            verficarTotal();
            btnGuardar.setVisible(true);
            btnCancelar.setVisible(true);
            btnImprimir.setVisible(false);
        } catch (Exception e) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Error al procesar tu peticion!!");
            panel.showNotification();
            System.out.println("Error " + e);
        }
    }//GEN-LAST:event_btnNuevoDetalleActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarInterfaz();
        int fk_factura = (Integer.parseInt(daoFac.ultimaFactura_id()));
        System.out.println("fk_factura " + fk_factura);
        desFacDao.delete(fk_factura);
        facTotDao.delete(fk_factura);
        daoFac.delete(fk_factura);
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            txtCedula.setText("0000000001");
            txtRuc.setText("N / A");
            txtDireccion.setText("N / A");
            txtTelefono.setText("N / A");
            txtApellidosCliente.setEditable(false);
            txtCedula.setEditable(false);
            txtRuc.setEditable(false);
            txtDireccion.setEditable(false);
            datosEncabezadoConsumidor();
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
            rs = daoPro.BUSCAR_PRODUCTO_FACTURACION(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[9];
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
                Datos[1] = (String) rs.getString(2);
                Datos[2] = (String) rs.getString(3);
                Datos[3] = (String) rs.getString(4);
                Datos[4] = (String) rs.getString(5);
                Datos[5] = (String) rs.getString(6);
                Datos[6] = (String) rs.getString(7);
                Datos[7] = (String) rs.getString(8);
                Datos[8] = (String) rs.getString(9);
                dtmProductos.addRow(Datos);
                encuentra = true;
                if (encuentra == true) {
                    if (tDatosProductos.getRowCount() == 1) {
                        dtmProductos = (DefaultTableModel) tDatosProductos.getModel();
                        lblIdProducto.setText((String) dtmProductos.getValueAt(0, 0));
                        txtNombreProd.setText((String) dtmProductos.getValueAt(0, 1));
                        txtCodAux.setText((String) dtmProductos.getValueAt(0, 3));
                        txtDetalleExtra.setText((String) dtmProductos.getValueAt(0, 4));
                        txtStock.setText((String) dtmProductos.getValueAt(0, 5));
                        txtPVP.setText((String) dtmProductos.getValueAt(0, 6));
                        txtTipoIva.setText((String) dtmProductos.getValueAt(0, 7));
                        txtDescuentoProd.setText((String) dtmProductos.getValueAt(0, 8));
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
                txtDescuentoProd.setText("");
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
            rs = daoPro.BUSCAR_PRODUCTO_FACTURACION(criterio, busqueda);
            boolean encuentra = false;
            String Datos[] = new String[9];
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
                Datos[1] = (String) rs.getString(2);
                Datos[2] = (String) rs.getString(3);
                Datos[3] = (String) rs.getString(4);
                Datos[4] = (String) rs.getString(5);
                Datos[5] = (String) rs.getString(6);
                Datos[6] = (String) rs.getString(7);
                Datos[7] = (String) rs.getString(8);
                Datos[8] = (String) rs.getString(9);
                dtmProductos.addRow(Datos);
                encuentra = true;
                if (encuentra == true) {
                    if (tDatosProductos.getRowCount() == 1) {
                        dtmProductos = (DefaultTableModel) tDatosProductos.getModel();
                        lblIdProducto.setText((String) dtmProductos.getValueAt(0, 0));
                        txtNombreProd.setText((String) dtmProductos.getValueAt(0, 1));
                        txtCodAux.setText((String) dtmProductos.getValueAt(0, 3));
                        txtDetalleExtra.setText((String) dtmProductos.getValueAt(0, 4));
                        txtStock.setText((String) dtmProductos.getValueAt(0, 5));
                        txtPVP.setText((String) dtmProductos.getValueAt(0, 6));
                        txtTipoIva.setText((String) dtmProductos.getValueAt(0, 7));
                        txtDescuentoProd.setText((String) dtmProductos.getValueAt(0, 8));
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
                txtDescuentoProd.setText("");
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
            txtDescuentoProd.setText("");
        }
    }//GEN-LAST:event_txtNombreProdKeyReleased

    private void tDatosProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosProductosMouseClicked
        cargarDatosProductosTabla();
    }//GEN-LAST:event_tDatosProductosMouseClicked

    private void tDatosFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosFacturaMouseClicked

    }//GEN-LAST:event_tDatosFacturaMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int fk_factura = Integer.parseInt(daoFac.ultimaFactura_id());
        String subtotal_0 = txtDescuento.getText();
        String subtotal_12 = txtDescuento.getText();
//        String objIva = txtSubtotal.getText();
//        String excIva = txtSubtotal.getText();
        String descuentoString = txtSub0.getText();
        Double descuento = Double.parseDouble(descuentoString);
        String iva12 = txtIva12.getText();
        String totalPagar = txtTotal.getText();
        facTotalidad.setFK_FACTURA(fk_factura);
        facTotalidad.setFAC_SUB_0(Double.parseDouble(subtotal_0));
        facTotalidad.setFAC_SUB_12(Double.parseDouble(subtotal_12));
        facTotalidad.setFAC_OBJ_IVA(0.0);
        facTotalidad.setFAC_EXC_IVA(0.0);
        facTotalidad.setFAC_DESCUENTO(descuento);
        facTotalidad.setFAC_IVA_12(Double.parseDouble(iva12.toString()));
        facTotalidad.setFAC_TOTAL_PAGAR(Double.parseDouble(totalPagar.toString()));
        if (facTotDao.add(facTotalidad).equals("La factura fue creada con exito!")) {
            panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.TOP_CENTER, "Factura guardada con exito!!");
            panel.showNotification();
        } else if (facTotDao.add(facTotalidad).equals("La factura no fue creada!")) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.TOP_CENTER, "No se pudo guardar la factura!!");
            panel.showNotification();
        }
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        btnImprimir.setVisible(true);
        try {
            facturaPdf();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_FACTURA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_FACTURA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            abrirPdf();
        } catch (IOException ex) {
            Logger.getLogger(FORM_FACTURA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtCodPrincMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodPrincMouseClicked
        tabbedPane.setSelectedComponent(panelProductos);
    }//GEN-LAST:event_txtCodPrincMouseClicked

    private void txtCodAuxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodAuxMouseClicked
        tabbedPane.setSelectedComponent(panelProductos);
    }//GEN-LAST:event_txtCodAuxMouseClicked

    private void txtNombreProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreProdMouseClicked
        tabbedPane.setSelectedComponent(panelProductos);
    }//GEN-LAST:event_txtNombreProdMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnCancelar;
    private com.anthony.swing.Button btnGuardar;
    private com.anthony.swing.Button btnImprimir;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCantidadTabla;
    private javax.swing.JLabel lblClaveAcceso;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblPdf;
    private javax.swing.JLabel lblSub0;
    private javax.swing.JLabel lblSub12;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelClientes;
    private com.anthony.swing.RoundPanel panelDatos;
    private javax.swing.JPanel panelFactura;
    private com.anthony.swing.RoundPanel panelFormClientes;
    private com.anthony.swing.RoundPanel panelFormProductos;
    private javax.swing.JPanel panelProductos;
    private com.anthony.swing.progress.Progress progress3;
    private com.anthony.swing.progress.Progress progress6;
    private com.anthony.swing.progress.Progress progress7;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel18;
    private com.anthony.swing.RoundPanel roundPanel19;
    private com.anthony.swing.RoundPanel roundPanel2;
    private com.anthony.swing.RoundPanel roundPanel21;
    private com.anthony.swing.RoundPanel roundPanel22;
    private com.anthony.swing.RoundPanel roundPanel23;
    private com.anthony.swing.RoundPanel roundPanel3;
    private com.anthony.swing.RoundPanel roundPanel4;
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
    private textfield.TextField txtCantidad;
    private textfield.TextField txtCedula;
    private textfield.TextField txtCodAux;
    private textfield.TextField txtCodPrinc;
    private javax.swing.JLabel txtDescuento;
    private textfield.TextField txtDescuentoProd;
    private textfield.TextField txtDetalleExtra;
    private textfield.TextField txtDireccion;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtIva12;
    private textfield.TextField txtNombreProd;
    private textfield.TextField txtPVP;
    private textfield.TextField txtRuc;
    private textfield.TextField txtStock;
    private javax.swing.JLabel txtSub0;
    private javax.swing.JLabel txtSub12;
    private javax.swing.JLabel txtTelefono;
    private javax.swing.JLabel txtTipoIva;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables

    private void reportepdf() {
        Calendar fecha = Calendar.getInstance();
        int year = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        try {

            String numeroFactura = "";
            emp = (EMPRESA) empDao.list();
            FileOutputStream archivo;
            String cedula = txtCedula.getText();
            String ruta = "C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/" + cedula + "-" + usu.getUSU_USUARIO() + " " + daoFac.fechaNormal() + "S" + usu.getFK_SUCURSAL() + ".pdf";
            File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter p = PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
            Font negritaTitulo = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, BaseColor.BLACK);
            Font negrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
            Font negrita1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
            Paragraph fechaParagraph = new Paragraph("Fecha de emisión: "
                    + daoFac.fechaNormal() + "\n"
                    + "Factura número: ");
            PdfPTable codigoBarras = new PdfPTable(1);
            codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Barcode128 b = new Barcode128();
            b.setCode(lblClaveAcceso.getText());
            PdfPCell barCodeCell = new PdfPCell();
            barCodeCell.setBorder(0);
            barCodeCell.addElement(b.createImageWithBarcode(p.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
            codigoBarras.setWidthPercentage(40f);
            codigoBarras.addCell(barCodeCell);
            fechaParagraph.add(Chunk.NEWLINE);

            //------------------------------------------------------------------Titulo del documento
            PdfPTable Titulo = new PdfPTable(1);
            Titulo.setWidthPercentage(100);
            Titulo.getDefaultCell().setBorder(0);
            float[] ColumnaTitulo = new float[]{100f};
            Titulo.setWidths(ColumnaTitulo);
            Titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph titulo = new Paragraph("FACTURA", negritaTitulo);
            Titulo.addCell(titulo);
            PdfPTable Detalle = new PdfPTable(1);
            Paragraph detalle = new Paragraph("DETALLE DE LA COMPRA:", negrita1);
            Detalle.addCell(detalle);

            //------------------------------------------------------------------ Encabezado del documento
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{15f, 70f, 40f, 60f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            //Logo de la empresa para la factura
            String ruc = "1723382592";
            String correo = "correo@empresa.com";
            String tel = "0980868422";
            String dir = "Quito - Ecuador";
            String slogan = "";

            Encabezado.addCell(img);
            Encabezado.addCell(" R.U.C.: " + ruc + "\n"
                    + " Telefono: " + tel + "\n"
                    + "Dirección: " + dir + "\n"
                    + " Email: " + correo);
            Encabezado.addCell(slogan);
            Encabezado.addCell(fechaParagraph);

            //Espacio 
            Paragraph espacio = new Paragraph();
            espacio.add("\n");

            //------------------------------------------------------------------Tabla de los datos del Cliente
            //Datos de los Clientes 
            Paragraph cliente = new Paragraph("\n" + "Datos del cliente: " + "\n", negrita1);

            //Nombre
            PdfPTable tablaClienteNombre = new PdfPTable(2);
            tablaClienteNombre.setWidthPercentage(100);
            tablaClienteNombre.getDefaultCell().setBorder(0);
            float[] ColumnaCliente = new float[]{23f, 80f};
            tablaClienteNombre.setWidths(ColumnaCliente);
            tablaClienteNombre.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Nombre del cliente:", negrita1));

            //Quitamos los bordes de las celdas
            cl1.setBorder(0);

            //Añadimos las celdas de la tablaCliente
            tablaClienteNombre.addCell(cl1);
            tablaClienteNombre.addCell(txtApellidosCliente.getText());

            //Cedula
            PdfPTable tablaClienteCedula = new PdfPTable(2);
            tablaClienteCedula.setWidthPercentage(100);
            tablaClienteCedula.getDefaultCell().setBorder(0);
            float[] ColumnaCedula = new float[]{23f, 80f};
            tablaClienteCedula.setWidths(ColumnaCedula);
            tablaClienteCedula.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliCedula = new PdfPCell(new Phrase("RUC / Cedula:", negrita1));

            //Quitamos los bordes de las celdas
            cliCedula.setBorder(0);

            //Añadimos las celdas de la tablaCliente
            tablaClienteCedula.addCell(cliCedula);
            tablaClienteCedula.addCell(txtCedula.getText());

            //Telefono
            PdfPTable tablaClienteTelefono = new PdfPTable(2);
            tablaClienteTelefono.setWidthPercentage(100);
            tablaClienteTelefono.getDefaultCell().setBorder(0);
            float[] ColumnaTelefono = new float[]{23f, 80f};
            tablaClienteTelefono.setWidths(ColumnaTelefono);
            tablaClienteTelefono.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliTelefono = new PdfPCell(new Phrase("Telefono / Cel:", negrita1));

            //Quitamos los bordes de las celdas
            cliTelefono.setBorder(0);

            //Añadimos las celdas de la tablaCliente
            tablaClienteCedula.addCell(cliTelefono);
            tablaClienteCedula.addCell("0980868422");

            //Direccion
            PdfPTable tablaClienteDireccion = new PdfPTable(2);
            tablaClienteDireccion.setWidthPercentage(100);
            tablaClienteDireccion.getDefaultCell().setBorder(0);
            float[] ColumnaDireccion = new float[]{23f, 80f};
            tablaClienteDireccion.setWidths(ColumnaDireccion);
            tablaClienteDireccion.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliDireccion = new PdfPCell(new Phrase("Direccion:", negrita1));

            //Quitamos los bordes de las celdas
            cliDireccion.setBorder(0);

            //Añadimos las celdas de la tablaCliente
            tablaClienteDireccion.addCell(cliDireccion);
            tablaClienteDireccion.addCell("Quito");

            //------------------------------------------------------------------Tabla de los datos de los productos que facturamos
            PdfPTable tablaProductos = new PdfPTable(6);
            tablaProductos.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaProductos.setWidthPercentage(100);
            tablaProductos.getDefaultCell();
            float[] columnaProductos = new float[]{15f, 15f, 65f, 10f, 11f, 12f};
            tablaProductos.setWidths(columnaProductos);
            tablaProductos.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell pro1 = new PdfPCell(new Phrase("Codigo", negrita1));
            PdfPCell pro2 = new PdfPCell(new Phrase("Cod. Aux", negrita1));
            PdfPCell pro3 = new PdfPCell(new Phrase("Descripsión", negrita1));
            PdfPCell pro4 = new PdfPCell(new Phrase("Cant.", negrita1));
            PdfPCell pro5 = new PdfPCell(new Phrase("P.V.P", negrita1));
            PdfPCell pro6 = new PdfPCell(new Phrase("P. Total", negrita1));
            pro1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro3.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro4.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro5.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro6.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaProductos.addCell(pro1);
            tablaProductos.addCell(pro2);
            tablaProductos.addCell(pro3);
            tablaProductos.addCell(pro4);
            tablaProductos.addCell(pro5);
            tablaProductos.addCell(pro6);
            for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
                String codigoPrin = tDatosFactura.getValueAt(i, 0).toString();
                String codigoAux = tDatosFactura.getValueAt(i, 1).toString();
                String producto = tDatosFactura.getValueAt(i, 3).toString();
                String prodDetExtra = tDatosFactura.getValueAt(i, 4).toString();
                String cantidad = tDatosFactura.getValueAt(i, 2).toString();
                String precio = tDatosFactura.getValueAt(i, 5).toString();
                String total = tDatosFactura.getValueAt(i, 6).toString();
                tablaProductos.addCell(codigoPrin);
                tablaProductos.addCell(codigoAux);
                tablaProductos.addCell(producto + "-" + prodDetExtra);
                tablaProductos.addCell(cantidad);
                tablaProductos.addCell(precio);
                tablaProductos.addCell(total);
            }
            //------------------------------------------------------------------Tabla Subtotal
            PdfPTable tablaSubtotal = new PdfPTable(5);
            tablaSubtotal.setWidthPercentage(100);
            tablaSubtotal.getDefaultCell();
            float[] columnaSubtotal = new float[]{20f, 60f, 15f, 25f, 20f};
            tablaSubtotal.setWidths(columnaSubtotal);
            tablaSubtotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell sub1 = new PdfPCell(new Phrase("", negrita));
            PdfPCell sub2 = new PdfPCell(new Phrase("", negrita));
            PdfPCell sub3 = new PdfPCell(new Phrase("", negrita));
            PdfPCell sub4 = new PdfPCell(new Phrase("Subtotal:", negrita1));
            PdfPCell sub5 = new PdfPCell(new Phrase("USD " + txtDescuento.getText()));

            sub1.setBorder(0);
            sub2.setBorder(0);
            sub3.setBorder(0);
            sub4.setBorder(0);
            sub5.setBorder(0);

            tablaSubtotal.addCell(sub1);
            tablaSubtotal.addCell(sub2);
            tablaSubtotal.addCell(sub3);
            tablaSubtotal.addCell(sub4);
            tablaSubtotal.addCell(sub5);
            //------------------------------------------------------------------Tabla iva 12%
            PdfPTable tablaIva = new PdfPTable(5);
            tablaIva.setWidthPercentage(100);
            tablaIva.getDefaultCell();
            float[] columnaIva = new float[]{20f, 60f, 15f, 25f, 20f};
            tablaIva.setWidths(columnaIva);
            tablaIva.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell iva1 = new PdfPCell(new Phrase("", negrita));
            PdfPCell iva2 = new PdfPCell(new Phrase("", negrita));
            PdfPCell iva3 = new PdfPCell(new Phrase("", negrita));
            PdfPCell iva4 = new PdfPCell(new Phrase("Iva 12%:", negrita1));
            PdfPCell iva5 = new PdfPCell(new Phrase("USD " + txtIva12.getText()));

            iva1.setBorder(0);
            iva2.setBorder(0);
            iva3.setBorder(0);
            iva4.setBorder(0);
            iva5.setBorder(0);

            tablaIva.addCell(iva1);
            tablaIva.addCell(iva2);
            tablaIva.addCell(iva3);
            tablaIva.addCell(iva4);
            tablaIva.addCell(iva5);

            //------------------------------------------------------------------Tabla iva Cero
            PdfPTable tablaIvaCero = new PdfPTable(5);
            tablaIvaCero.setWidthPercentage(100);
            tablaIvaCero.getDefaultCell();
            float[] columnaIvaCero = new float[]{20f, 60f, 15f, 25f, 20f};
            tablaIvaCero.setWidths(columnaIvaCero);
            tablaIvaCero.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell ivaC1 = new PdfPCell(new Phrase("", negrita));
            PdfPCell ivaC2 = new PdfPCell(new Phrase("", negrita));
            PdfPCell ivaC3 = new PdfPCell(new Phrase("", negrita));
            PdfPCell ivaC4 = new PdfPCell(new Phrase("Descuento %:", negrita1));
            PdfPCell ivaC5 = new PdfPCell(new Phrase("USD " + txtSub0.getText()));

            ivaC1.setBorder(0);
            ivaC2.setBorder(0);
            ivaC3.setBorder(0);
            ivaC4.setBorder(0);
            ivaC5.setBorder(0);

            tablaIvaCero.addCell(ivaC1);
            tablaIvaCero.addCell(ivaC2);
            tablaIvaCero.addCell(ivaC3);
            tablaIvaCero.addCell(ivaC4);
            tablaIvaCero.addCell(ivaC5);

            //------------------------------------------------------------------Tabla Total a Pagar
            PdfPTable tablaTotalPagar = new PdfPTable(5);
            tablaTotalPagar.setWidthPercentage(100);
            tablaTotalPagar.getDefaultCell();
            float[] columnaTotalPagar = new float[]{20f, 60f, 15f, 25f, 20f};
            tablaTotalPagar.setWidths(columnaTotalPagar);
            tablaTotalPagar.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell TotalPagar1 = new PdfPCell(new Phrase("", negrita));
            PdfPCell TotalPagar2 = new PdfPCell(new Phrase("", negrita));
            PdfPCell TotalPagar3 = new PdfPCell(new Phrase("", negrita));
            PdfPCell TotalPagar4 = new PdfPCell(new Phrase("Total a Pagar:", negrita1));
            PdfPCell TotalPagar5 = new PdfPCell(new Phrase("USD " + txtTotal.getText()));

            TotalPagar1.setBorder(0);
            TotalPagar2.setBorder(0);
            TotalPagar3.setBorder(0);
            TotalPagar4.setBorder(0);
            TotalPagar5.setBorder(0);

            tablaTotalPagar.addCell(TotalPagar1);
            tablaTotalPagar.addCell(TotalPagar2);
            tablaTotalPagar.addCell(TotalPagar3);
            tablaTotalPagar.addCell(TotalPagar4);
            tablaTotalPagar.addCell(TotalPagar5);

            //------------------------------------------------------------------Tabla PiePagina
            PdfPTable tablaFirmas = new PdfPTable(2);
            tablaFirmas.setWidthPercentage(100);
            tablaFirmas.getDefaultCell().setBorder(0);
            float[] ColumnaFirmas = new float[]{100f, 100f};
            tablaFirmas.setWidths(ColumnaFirmas);
            tablaFirmas.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell firmaAdmin = new PdfPCell(new Phrase("----------------------------------" + "\n" + usu.getUSU_USUARIO() + "\n" + usu.getUSU_PARAMETRO(), negrita1));
            PdfPCell firmaCli = new PdfPCell(new Phrase("----------------------------------" + "\n" + txtApellidosCliente.getText() + "\n" + txtCedula.getText(), negrita1));

            //Quitamos los bordes de las celdas
            firmaAdmin.setBorder(0);
            firmaAdmin.setHorizontalAlignment(Element.ALIGN_CENTER);
            firmaCli.setBorder(0);
            firmaCli.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Añadimos las celdas de la firmas
            tablaFirmas.addCell(firmaAdmin);
            tablaFirmas.addCell(firmaCli);

            PdfPTable tablaGracias = new PdfPTable(1);
            tablaGracias.setWidthPercentage(100);
            tablaGracias.getDefaultCell().setBorder(0);
            float[] ColumnaGracias = new float[]{100f};
            tablaGracias.setWidths(ColumnaGracias);
            tablaGracias.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell Gracias1 = new PdfPCell(new Phrase("*********¡Gracias por su compra!*********"));

            //Quitamos los bordes de las celdas
            Gracias1.setBorder(0);
            Gracias1.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Añadimos las celdas de la firmas
            tablaGracias.addCell(Gracias1);

            //------------------------------------------------------------------Partes que se unen al documento pdf
            doc.add(Titulo);
            doc.add(espacio);
            doc.add(Encabezado);
            doc.add(codigoBarras);
            doc.add(cliente);//Texto datos del CLiente
            doc.add(tablaClienteNombre);
            doc.add(tablaClienteCedula);
            doc.add(tablaClienteTelefono);
            doc.add(tablaClienteDireccion);
            doc.add(espacio);
            doc.add(detalle);
            doc.add(espacio);
            doc.add(tablaProductos);
            doc.add(espacio);
            doc.add(tablaSubtotal);
            doc.add(tablaIva);
            doc.add(tablaIvaCero);
            doc.add(tablaTotalPagar);
            doc.add(espacio);
            doc.add(espacio);
            doc.add(espacio);
            doc.add(espacio);
            doc.add(tablaFirmas);
            doc.add(espacio);
            doc.add(espacio);
            doc.add(espacio);
            doc.add(tablaGracias);

            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
            System.out.println("Documento Abierto");
            //---------------------------------Fin del documento

        } catch (Exception e) {
            System.out.println("Error en la parte de generar el reporte pdf" + "\n" + e.toString());
        }
    }

    private void facturaPdf() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        /* ============================
        DATOS PRINCIPALES FECHAS
        ============================ */

        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        /* ============================
        DATOS PRINCIPALES DEL DOCUMENTO
        ============================ */
        FileOutputStream archivo;
        String cedula = txtCedula.getText();
        String ruta = "C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/" + cedula + "-" + usu.getUSU_USUARIO() + " " + daoFac.fechaNormal() + " (" + daoFac.hora() + ") S" + usu.getFK_SUCURSAL() + ".pdf";
        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter p = PdfWriter.getInstance(doc, archivo);
        doc.open();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        /* ============================
        FUENTES Y TIPOS
        ============================ */
        Font negrita = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        Font negritaSmall = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
        Font negritaSmallBold = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.BLACK);
        Font negritaMediuamBold = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD, BaseColor.BLACK);
        Font normalBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
        Font normalBold8 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
        Font normal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
        //Espacio 
        Paragraph espacio = new Paragraph();
        espacio.add("\n");
        LineSeparator separator = new LineSeparator();
        separator.setPercentage(59500f / 523f);
        Chunk linebreak = new Chunk(separator);
        /* ============================
        PARTE ENCABEZADO
        ============================ */
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 60f};
        encabezado.setWidths(ColumnaEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //VARIABLES EMPRESA
        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
        emp = empDao.list();
        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
        /* ============================
        AGREGAR TABLA DATOS FACTURA
        ============================ */
        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
        PdfPTable datosFactura = new PdfPTable(1);
        datosFactura.setWidthPercentage(100);
        datosFactura.getDefaultCell().setBorder(0);
        String datosFacturaCelda = "R.U.C.  " + emp.getEMP_RUC() + "\n"
                + "FACTURA N.-  " + "001-001-002905538" + "\n"
                + "NUMERO DE AUTORIZACION  " + lblClaveAcceso.getText() + "\n"
                + "FECHA DE AUTORIZACION  " + "\n" + daoFac.fechaNormal() + "  " + daoFac.horaNormal() + "\n"
                + "AMBIENTE:  " + "PRUEBAS" + "\n"
                + "EMISION:  " + "NORMAL";
        datosFactura.addCell(datosFacturaCelda);
        Paragraph parrafoFactura = new Paragraph(datosFacturaCelda, negritaSmall);
        //ENVIO DATOS A LA TABLA
        encabezado.addCell(img);//img empresa
        encabezado.addCell(parrafoEmpresa);//datos empresa
        encabezado.addCell("");//espacio
        encabezado.addCell(parrafoFactura);//datos factura
        /* ============================
        AGREGAR CLAVE DE ACCESO
        ============================ */
        PdfPTable claveAccesoTable = new PdfPTable(1);
        Paragraph claveAcceso = new Paragraph("CLAVE DE ACCESO", negritaSmallBold);
        claveAccesoTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell claveAccesoCell = new PdfPCell();
        claveAccesoCell.setBorder(0);
        claveAccesoTable.setWidthPercentage(40f);
        claveAccesoCell.addElement(claveAcceso);
        claveAccesoTable.addCell(claveAccesoCell);
        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable codigoBarras = new PdfPTable(1);
        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Barcode128 b = new Barcode128();
        b.setCode(lblClaveAcceso.getText());
        PdfPCell barCodeCell = new PdfPCell();
        barCodeCell.setBorder(0);
        barCodeCell.addElement(b.createImageWithBarcode(p.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
        codigoBarras.setWidthPercentage(40f);
        codigoBarras.addCell(barCodeCell);
        codigoBarras.getDefaultCell().setBorder(0);

        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable datosClientesMain = new PdfPTable(1);
        datosClientesMain.setWidthPercentage(100);
        PdfPTable datosClientes = new PdfPTable(4);
        float[] columnaCliente = new float[]{30f, 70f, 30f, 40f};
        datosClientes.setWidths(columnaCliente);
        datosClientes.getDefaultCell().setBorder(0);
        Paragraph razonSocial = new Paragraph("Razon Social", normalBold);
        Paragraph cliNombres = new Paragraph(txtApellidosCliente.getText(), normal);
        Paragraph identificacion = new Paragraph("Identificacion", normalBold);
        Paragraph cliCedula = new Paragraph(txtCedula.getText(), normal);
        Paragraph fechaEmsision = new Paragraph("Fecha de emision", normalBold);
        Paragraph fechaEmsis = new Paragraph(dtf.format(now), normal);
        Paragraph guiaRemision = new Paragraph("Guia de remision", normalBold);
        Paragraph guia = new Paragraph("001-001-001541862", normal);
        datosClientes.addCell(razonSocial);
        datosClientes.addCell(cliNombres);
        datosClientes.addCell(identificacion);
        datosClientes.addCell(cliCedula);
        datosClientes.addCell(fechaEmsision);
        datosClientes.addCell(fechaEmsis);
        datosClientes.addCell(guiaRemision);
        datosClientes.addCell(guia);

        //agregarmos a la tabladatosClientesMain
        datosClientesMain.addCell(datosClientes);

        /* ============================
        DATOS PRODUCTO
        ============================ */
        PdfPTable tablaProductos = new PdfPTable(7);
        //tablaProductos.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaProductos.setWidthPercentage(100);
        tablaProductos.getDefaultCell();
        float[] columnaProductos = new float[]{20f, 20f, 60f, 10f, 15f, 15f, 12f};
        tablaProductos.setWidths(columnaProductos);
        tablaProductos.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell pro1 = new PdfPCell(new Phrase("Codigo", normalBold));
        PdfPCell pro2 = new PdfPCell(new Phrase("Cod. Aux", normalBold));
        PdfPCell pro3 = new PdfPCell(new Phrase("Descripsión", normalBold));
        PdfPCell pro4 = new PdfPCell(new Phrase("Cant.", normalBold));
        PdfPCell pro5 = new PdfPCell(new Phrase("P.V.P", normalBold));
        PdfPCell pro6 = new PdfPCell(new Phrase("Descuento", normalBold));
        PdfPCell pro7 = new PdfPCell(new Phrase("P. Total", normalBold));
        pro1.setHorizontalAlignment(Element.ALIGN_CENTER);
        pro2.setHorizontalAlignment(Element.ALIGN_CENTER);
        pro3.setHorizontalAlignment(Element.ALIGN_CENTER);
        pro4.setHorizontalAlignment(Element.ALIGN_CENTER);
        pro5.setHorizontalAlignment(Element.ALIGN_CENTER);
        pro6.setHorizontalAlignment(Element.ALIGN_CENTER);
        pro7.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaProductos.addCell(pro1);
        tablaProductos.addCell(pro2);
        tablaProductos.addCell(pro3);
        tablaProductos.addCell(pro4);
        tablaProductos.addCell(pro5);
        tablaProductos.addCell(pro6);
        tablaProductos.addCell(pro7);
        for (int i = 0; i < tDatosFactura.getRowCount(); i++) {
            String codigoPrin = tDatosFactura.getValueAt(i, 0).toString();
            String codigoAux = tDatosFactura.getValueAt(i, 1).toString();
            String producto = tDatosFactura.getValueAt(i, 3).toString();
            String prodDetExtra = tDatosFactura.getValueAt(i, 4).toString();
            String cantidadFact = tDatosFactura.getValueAt(i, 2).toString();
            String precioFact = tDatosFactura.getValueAt(i, 5).toString();
            String descuFact = tDatosFactura.getValueAt(i, 6).toString();
            String totalFact = tDatosFactura.getValueAt(i, 7).toString();
            PdfPCell codigoPrinCell = new PdfPCell(new Phrase(codigoPrin, negrita));
            PdfPCell codigoAuxCell = new PdfPCell(new Phrase(codigoAux, negrita));
            PdfPCell productoCell = new PdfPCell(new Phrase(producto + " - " + prodDetExtra, negrita));
            PdfPCell cantidadCell = new PdfPCell(new Phrase(cantidadFact, negrita));
            PdfPCell precioCell = new PdfPCell(new Phrase(precioFact, negrita));
            PdfPCell descuentoCell = new PdfPCell(new Phrase(descuFact, negrita));
            PdfPCell totalCell = new PdfPCell(new Phrase(totalFact, negrita));
            codigoPrinCell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            codigoAuxCell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            productoCell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cantidadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            precioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaProductos.addCell(codigoPrinCell);
            tablaProductos.addCell(codigoAuxCell);
            tablaProductos.addCell(productoCell);
            tablaProductos.addCell(cantidadCell);
            tablaProductos.addCell(precioCell);
            tablaProductos.addCell(descuentoCell);
            tablaProductos.addCell(totalCell);
        }

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        PdfPTable datosExtraMain = new PdfPTable(3);
        datosExtraMain.setWidthPercentage(100);
        float[] columnaTablaDatosExtra = new float[]{50f, 2f, 30f};
        datosExtraMain.setWidths(columnaTablaDatosExtra);
        datosExtraMain.getDefaultCell().setBorder(0);
        // Tabla datosAdicionales
        PdfPTable datosAdicionales = new PdfPTable(2);
        float[] columnaTablaDatosExtraAdi = new float[]{20f, 30f};
        datosAdicionales.setWidths(columnaTablaDatosExtraAdi);

        //Datos adicionales
        Paragraph infoAdi = new Paragraph("Informacion adicional:", normal);
        Paragraph infoAdiEspacio = new Paragraph("", normal);
        Paragraph nombreCliente = new Paragraph("Nombre comercial cliente", normalBold);
        Paragraph cliNombresAdi = new Paragraph(txtApellidosCliente.getText(), normal);
        Paragraph cliDir = new Paragraph("Direccion", normalBold);
        Paragraph cliDirAdi = new Paragraph(txtDireccion.getText(), normal);
        Paragraph codCliente = new Paragraph("Codigo cliente", normalBold);
        Paragraph codClienteAdi = new Paragraph("1234567890123", normal);
        Paragraph cliTelefono = new Paragraph("Telefono", normalBold);
        Paragraph cliTelefonoAdi = new Paragraph(txtTelefono.getText(), normal);
        Paragraph terminoPago = new Paragraph("Tipo de pago", normalBold);
        Paragraph terminoPagoAdi = new Paragraph("Efectivo", normal);
        Paragraph fechaVencimiento = new Paragraph("Fecha de vencimiento", normalBold);
        Paragraph fechaVencimientoAdi = new Paragraph(dtf.format(now), normal);
        //
        PdfPCell infoAdiCell = new PdfPCell(infoAdi);
        PdfPCell infoAdiEspacioCell = new PdfPCell(infoAdiEspacio);
        PdfPCell nombreClienteCell = new PdfPCell(nombreCliente);
        PdfPCell cliNombresAdiCell = new PdfPCell(cliNombresAdi);
        PdfPCell cliDirCell = new PdfPCell(cliDir);
        PdfPCell cliDirAdiCell = new PdfPCell(cliDirAdi);
        PdfPCell codClienteCell = new PdfPCell(codCliente);
        PdfPCell codClienteAdiCell = new PdfPCell(codClienteAdi);
        PdfPCell cliTelefonoCell = new PdfPCell(cliTelefono);
        PdfPCell cliTelefonoAdiCell = new PdfPCell(cliTelefonoAdi);
        PdfPCell terminoPagoCell = new PdfPCell(terminoPago);
        PdfPCell terminoPagoAdiCell = new PdfPCell(terminoPagoAdi);
        PdfPCell fechaVencimientoCell = new PdfPCell(fechaVencimiento);
        PdfPCell fechaVencimientoAdiCell = new PdfPCell(fechaVencimientoAdi);

        infoAdiCell.setBorder(0);
        infoAdiEspacioCell.setBorder(0);
        //Envio a la tabla datosAdicionales
        datosAdicionales.addCell(infoAdiCell);
        datosAdicionales.addCell(infoAdiEspacioCell);
        datosAdicionales.addCell(nombreClienteCell);
        datosAdicionales.addCell(cliNombresAdiCell);
        datosAdicionales.addCell(cliDirCell);
        datosAdicionales.addCell(cliDirAdiCell);
        datosAdicionales.addCell(codClienteCell);
        datosAdicionales.addCell(codClienteAdiCell);
        datosAdicionales.addCell(cliTelefonoCell);
        datosAdicionales.addCell(cliTelefonoAdiCell);
        datosAdicionales.addCell(terminoPagoCell);
        datosAdicionales.addCell(terminoPagoAdiCell);
        datosAdicionales.addCell(fechaVencimientoCell);
        datosAdicionales.addCell(fechaVencimientoAdiCell);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        PdfPTable calculosMain = new PdfPTable(2);
        calculosMain.setWidthPercentage(100);
        float[] columnaaCalculosMain = new float[]{50f, 50f};
        calculosMain.setWidths(columnaaCalculosMain);
        //
        Paragraph subTotal12Cell = new Paragraph("Subtotal 12%", negritaMediuamBold);
        Paragraph subTotal12InfoCell = new Paragraph(txtSub12.getText(), normal);
        Paragraph sub0Cell = new Paragraph("Subtotal 0%", negritaMediuamBold);
        Paragraph sub0InfoCell = new Paragraph(txtSub0.getText(), normal);
        Paragraph subObjCell = new Paragraph("Subtotal no sujeto de IVA", negritaMediuamBold);
        Paragraph subObjInfoCell = new Paragraph("0.00", normal);
        subObjInfoCell.setAlignment(Element.ALIGN_CENTER);
        Paragraph subImpCell = new Paragraph("Subtotal sin impuestos", negritaMediuamBold);
        Paragraph subImpInfoCell = new Paragraph(txtDescuento.getText(), normal);
        Paragraph desCell = new Paragraph("Descuento", negritaMediuamBold);
        Paragraph desInfoCell = new Paragraph(txtDescuento.getText(), normal);
        Paragraph ivaCell = new Paragraph("IVA 12%", negritaMediuamBold);
        Paragraph ivaInfoCell = new Paragraph(txtIva12.getText(), normal);
        Paragraph totalCell = new Paragraph("VALOR A PAGAR", normalBold);
        Paragraph totalInfoCell = new Paragraph(txtTotal.getText(), normalBold);

        //
        calculosMain.addCell(subTotal12Cell);
        calculosMain.addCell(subTotal12InfoCell);
        calculosMain.addCell(sub0Cell);
        calculosMain.addCell(sub0InfoCell);
        calculosMain.addCell(subObjCell);
        calculosMain.addCell(subObjInfoCell);
        calculosMain.addCell(subImpCell);
        calculosMain.addCell(subImpInfoCell);
        calculosMain.addCell(desCell);
        calculosMain.addCell(desInfoCell);
        calculosMain.addCell(ivaCell);
        calculosMain.addCell(ivaInfoCell);
        calculosMain.addCell(totalCell);
        calculosMain.addCell(totalInfoCell);

        //Envio a la otra tabla
        datosExtraMain.addCell(datosAdicionales);
        datosExtraMain.addCell(espacio);
        datosExtraMain.addCell(calculosMain);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        PdfPTable formaFago = new PdfPTable(3);
        formaFago.setWidthPercentage(100);
        float[] columnaformaFago = new float[]{42f, 20f, 7f};
        formaFago.setWidths(columnaformaFago);
        formaFago.getDefaultCell().setBorder(0);
        //
        PdfPTable formaFagoMain = new PdfPTable(4);
        formaFagoMain.setWidthPercentage(100);
        float[] formaPagoMain = new float[]{50f, 20f, 20f, 20f};
        formaFagoMain.setWidths(formaPagoMain);
        //
        Paragraph formaPagoText = new Paragraph("Informacion adicional:", normalBold);
        Paragraph formaValorText = new Paragraph("Valor", normalBold);
        Paragraph formaPlazoText = new Paragraph("Plazo", normalBold);
        Paragraph formaTiempoText = new Paragraph("Tiempo", normalBold);
        Paragraph formaPagoInfo = new Paragraph("Sin utilizar sistema financiero", normal);
        Paragraph formaValorInfo = new Paragraph(txtTotal.getText(), normal);
        Paragraph formaPlazoInfo = new Paragraph("0", normal);
        Paragraph formaTiempoInfo = new Paragraph("dias", normal);
        //
        PdfPCell formaPagoCell = new PdfPCell(formaPagoText);
        PdfPCell formaValorCell = new PdfPCell(formaValorText);
        PdfPCell formaPlazoCell = new PdfPCell(formaPlazoText);
        PdfPCell formaTiempoCell = new PdfPCell(formaTiempoText);
        PdfPCell formaPagoInfoCell = new PdfPCell(formaPagoInfo);
        PdfPCell formaValorInfoCell = new PdfPCell(formaValorInfo);
        PdfPCell formaPlazoInfoCell = new PdfPCell(formaPlazoInfo);
        PdfPCell formaTiempoInfoCell = new PdfPCell(formaTiempoInfo);
        //
        formaPagoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        formaValorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        formaPlazoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        formaTiempoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        formaValorInfoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        formaPlazoInfoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        formaTiempoInfoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //
        formaFagoMain.addCell(formaPagoCell);
        formaFagoMain.addCell(formaValorCell);
        formaFagoMain.addCell(formaPlazoCell);
        formaFagoMain.addCell(formaTiempoCell);
        formaFagoMain.addCell(formaPagoInfoCell);
        formaFagoMain.addCell(formaValorInfoCell);
        formaFagoMain.addCell(formaPlazoInfoCell);
        formaFagoMain.addCell(formaTiempoInfoCell);
        formaFagoMain.addCell(formaPagoInfoCell);
        formaFagoMain.addCell(formaValorInfoCell);
        formaFagoMain.addCell(formaPlazoInfoCell);
        formaFagoMain.addCell(formaTiempoInfoCell);

        //
        PdfPTable codigoQr = new PdfPTable(1);
        codigoQr.setHorizontalAlignment(Element.ALIGN_RIGHT);
        BarcodeQRCode my_code = new BarcodeQRCode("Example QR Code Creation in Itext", 1, 1, null);
        //Step-6: Get Image corresponding to the input string
        Image qr_image = my_code.getImage();
        qr_image.setBorderWidth(0);
        qr_image.scaleAbsoluteHeight(20f);
        qr_image.setWidthPercentage(100);
        PdfPCell qrCodeCell = new PdfPCell();
        qrCodeCell.setBorder(0);
        codigoQr.getDefaultCell().setBorder(0);
        qrCodeCell.addElement(b.createImageWithBarcode(p.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
//        codigoQr.setWidthPercentage(40f);
        codigoQr.addCell(qr_image);
        codigoQr.getDefaultCell().setBorder(0);

        formaFago.addCell(formaFagoMain);
        formaFago.addCell(espacio);
        formaFago.addCell(codigoQr);
        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        doc.add(encabezado);
        doc.add(claveAccesoTable);
        doc.add(codigoBarras);
        doc.add(espacio);
        doc.add(datosClientesMain);
        doc.add(espacio);
        doc.add(tablaProductos);
        doc.add(espacio);
        doc.add(datosExtraMain);
        doc.add(espacio);
        doc.add(formaFago);
        doc.close();

        if (mail.sendMail(txtEmail.getText(),
                txtApellidosCliente.getText(),
                emp.getEMP_NOMBRE_COMERCIAL(),
                ajustes.getAJ_EMAIL(),
                ajustes.getAJ_CLAVE(),
                "COMPROBANTE GENERADO",
                "FACTURA",
                cedula + "-" + daoFac.fechaNormal() + daoFac.hora(),
                txtTotal.getText(),
                "C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/" + cedula + "-" + usu.getUSU_USUARIO() + " " + daoFac.fechaNormal() + " (" + daoFac.hora() + ") S" + usu.getFK_SUCURSAL() + ".pdf",
                cedula + " " + daoFac.fechaNormal() + "(" + daoFac.hora() + ")" + ".pdf")
                .equals("Email enviado correctamente")) {
            panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.TOP_CENTER, "Factura enviada!!");
            panel.showNotification();
        } else if (mail.sendMail(txtEmail.getText(),
                txtApellidosCliente.getText(),
                emp.getEMP_NOMBRE_COMERCIAL(),
                ajustes.getAJ_EMAIL(),
                ajustes.getAJ_CLAVE(),
                emp.getEMP_NOMBRE_COMERCIAL() + " COMPROBANTE GENERADO",
                "FACTURA",
                cedula + "-" + usu.getUSU_USUARIO() + " " + daoFac.fechaNormal() + " (" + daoFac.hora() + ") S" + usu.getFK_SUCURSAL(),
                txtTotal.getText(),
                "C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/" + cedula + "-" + usu.getUSU_USUARIO() + " " + daoFac.fechaNormal() + " (" + daoFac.hora() + ") S" + usu.getFK_SUCURSAL() + ".pdf",
                cedula + "-" + usu.getUSU_USUARIO() + " " + daoFac.fechaNormal() + " (" + daoFac.hora() + ") S" + usu.getFK_SUCURSAL() + ".pdf")
                .equals("No se pudo enviar el email")) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.TOP_CENTER, "No se pudo enviar la factura!!");
            panel.showNotification();
        }
    }

    private void abrirPdf() throws IOException {
        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        String cedula = txtCedula.getText();
        File myFile = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/" + cedula + "-" + usu.getUSU_USUARIO() + " " + daoFac.fechaNormal() + " (" + daoFac.hora() + ") S" + usu.getFK_SUCURSAL() + ".pdf");
        Desktop.getDesktop().open(myFile);
    }

}
