package com.anthony.ViewsLight;

import com.anthony.MainLight.MainAdministrador;
import com.anthony.Models.PERMISOS;
import com.anthony.Models.USUARIO;
import com.anthony.ModelsDAO.PERMISOS_DAO;
import com.anthony.ModelsDAO.USUARIO_DAO;
import com.anthony.dialog.MessageDialogDark;
import com.anthony.dialog.MessageDialogLight;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.swing.scrollbar.ScrollBarCustomClaro;
import com.anthony.toast.Toast;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author The Thøny
 */
public class FORM_PERMISOS extends javax.swing.JPanel {

    /* ==============================
    OBJETOS PRINCIPALES
    ============================== */
    String[] titulosUsuarios = {"#", "Sucursal", "Nombres", "Apellidos", "Usuario", "Clave", "Parametro", "Estado"};
    DefaultTableModel dtmUsuarios = new DefaultTableModel(null, titulosUsuarios);
    RoundBorder border = new RoundBorder(0);
    Toast panel;
    MainAdministrador admin;
    /* ==============================
    OBJETOS PRINCIPALES
    ============================== */
    USUARIO usu = new USUARIO();
    USUARIO_DAO daoUsu = new USUARIO_DAO();
    PERMISOS per = new PERMISOS();
    PERMISOS_DAO daoPer = new PERMISOS_DAO();

    String criterio, busqueda;
    static ResultSet rs = null;

    /* ==============================
    OBJETOS PRINCIPALES
    ============================== */
    Integer ID_PERMISO;
    Integer FK_USUARIO;
    String PER_CLIENTE;
    String PER_EMPLEADO;
    String PER_PROVEEDOR;
    String PER_PRODUCTO;
    String PER_FACTURACION;
    String PER_NOTA_DEBITO;
    String PER_NOTA_CREDITO;
    String PER_REP_GENERALES;
    String PER_REP_ESTADISTICOS;
    String PER_REV_FACTURAS;
    String PER_REV_NOT_CREDITO;
    String PER_REV_NOT_DEBITO;
    String PER_USUARIOS;
    String PER_PERMISOS;
    String PER_AJUSTES;
    String PER_EMPRESA;

    public FORM_PERMISOS() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        tablaUsuarios();
        scroll();
        ocultar();
    }

    public FORM_PERMISOS(USUARIO usu, MainAdministrador admin) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        tablaUsuarios();
        scroll();
        ocultar();
    }

    private void scroll() {
        sp.setVerticalScrollBar(new ScrollBarCustomClaro());
        sp.setHorizontalScrollBar(new ScrollBarCustomClaro());
        sp.getViewport().setOpaque(false);
        spUsuarios.setVerticalScrollBar(new ScrollBarCustomClaro());
        spUsuarios.setHorizontalScrollBar(new ScrollBarCustomClaro());
        spUsuarios.getViewport().setOpaque(false);
        bordes();
    }

    public void bordes() {
        spUsuarios.setVerticalScrollBar(new ScrollBarCustomClaro());
        spUsuarios.setBorder(border);
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

    private void ocultar() {
        panelPermisos.setVisible(false);
        panelBuscar.setVisible(false);
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

    public void cargarDatosFormulario() {
        int fila;
        fila = tDatosUsuarios.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        } else {
            dtmUsuarios = (DefaultTableModel) tDatosUsuarios.getModel();
            FK_USUARIO = Integer.parseInt((String) dtmUsuarios.getValueAt(fila, 0));
            lblIdUsuario.setText("" + FK_USUARIO);
            usuNombres.setText((String) dtmUsuarios.getValueAt(fila, 2) + " " + (String) dtmUsuarios.getValueAt(fila, 3));
            usuCedula.setText((String) dtmUsuarios.getValueAt(fila, 4));
            per = daoPer.list(FK_USUARIO);
            PER_CLIENTE = per.getPER_CLIENTE();
            PER_EMPLEADO = per.getPER_EMPLEADO();
            PER_PROVEEDOR = per.getPER_PROVEEDOR();
            PER_PRODUCTO = per.getPER_PRODUCTO();
            PER_FACTURACION = per.getPER_FACTURACION();
            PER_NOTA_DEBITO = per.getPER_NOTA_DEBITO();
            PER_NOTA_CREDITO = per.getPER_NOTA_CREDITO();
            PER_REP_GENERALES = per.getPER_REP_GENERALES();
            PER_REP_ESTADISTICOS = per.getPER_REP_ESTADISTICOS();
            PER_REV_FACTURAS = per.getPER_REV_FACTURAS();
            PER_REV_NOT_CREDITO = per.getPER_REV_NOT_CREDITO();
            PER_REV_NOT_DEBITO = per.getPER_REV_NOT_DEBITO();
            PER_USUARIOS = per.getPER_USUARIOS();
            PER_PERMISOS = per.getPER_PERMISOS();
            PER_AJUSTES = per.getPER_AJUSTES();
            PER_EMPRESA = per.getPER_EMPRESA();
            if (PER_CLIENTE.equals("CON PERMISO")) {
                btnCliente.setSelected(true);
            } else if (PER_CLIENTE.equals("SIN PERMISO")) {
                btnCliente.setSelected(false);
            }
            if (PER_EMPLEADO.equals("CON PERMISO")) {
                btnEmpleados.setSelected(true);
            } else if (PER_EMPLEADO.equals("SIN PERMISO")) {
                btnEmpleados.setSelected(false);
            }
            if (PER_PROVEEDOR.equals("CON PERMISO")) {
                btnProveedores.setSelected(true);
            } else if (PER_PROVEEDOR.equals("SIN PERMISO")) {
                btnProveedores.setSelected(false);
            }
            if (PER_PRODUCTO.equals("CON PERMISO")) {
                btnProductos.setSelected(true);
            } else if (PER_PRODUCTO.equals("SIN PERMISO")) {
                btnProductos.setSelected(false);
            }
            if (PER_FACTURACION.equals("CON PERMISO")) {
                btnFacturacion.setSelected(true);
            } else if (PER_FACTURACION.equals("SIN PERMISO")) {
                btnFacturacion.setSelected(false);
            }
            if (PER_NOTA_DEBITO.equals("CON PERMISO")) {
                btnNotaDebito.setSelected(true);
            } else if (PER_NOTA_DEBITO.equals("SIN PERMISO")) {
                btnNotaDebito.setSelected(false);
            }
            if (PER_NOTA_CREDITO.equals("CON PERMISO")) {
                btnNotaCredito.setSelected(true);
            } else if (PER_NOTA_CREDITO.equals("SIN PERMISO")) {
                btnNotaCredito.setSelected(false);
            }
            if (PER_REP_GENERALES.equals("CON PERMISO")) {
                btnReporteGeneral.setSelected(true);
            } else if (PER_REP_GENERALES.equals("SIN PERMISO")) {
                btnReporteGeneral.setSelected(false);
            }
            if (PER_REP_ESTADISTICOS.equals("CON PERMISO")) {
                btnReporteEstad.setSelected(true);
            } else if (PER_REP_ESTADISTICOS.equals("SIN PERMISO")) {
                btnReporteEstad.setSelected(false);
            }
            if (PER_REV_FACTURAS.equals("CON PERMISO")) {
                btnRevFacturas.setSelected(true);
            } else if (PER_REV_FACTURAS.equals("SIN PERMISO")) {
                btnRevFacturas.setSelected(false);
            }
            if (PER_REV_NOT_CREDITO.equals("CON PERMISO")) {
                btnRepNotCredit.setSelected(true);
            } else if (PER_REV_NOT_CREDITO.equals("SIN PERMISO")) {
                btnRepNotCredit.setSelected(false);
            }
            if (PER_REV_NOT_DEBITO.equals("CON PERMISO")) {
                btnRepNotDebit.setSelected(true);
            } else if (PER_REV_NOT_DEBITO.equals("SIN PERMISO")) {
                btnRepNotDebit.setSelected(false);
            }
            if (PER_USUARIOS.equals("CON PERMISO")) {
                btnUsuarios.setSelected(true);
            } else if (PER_USUARIOS.equals("SIN PERMISO")) {
                btnUsuarios.setSelected(false);
            }
            if (PER_PERMISOS.equals("CON PERMISO")) {
                btnPermisos.setSelected(true);
            } else if (PER_PERMISOS.equals("SIN PERMISO")) {
                btnPermisos.setSelected(false);
            }
            if (PER_AJUSTES.equals("CON PERMISO")) {
                btnAjustes.setSelected(true);
            } else if (PER_AJUSTES.equals("SIN PERMISO")) {
                btnAjustes.setSelected(false);
            }
            if (PER_EMPRESA.equals("CON PERMISO")) {
                btnEmpresa.setSelected(true);
            } else if (PER_EMPRESA.equals("SIN PERMISO")) {
                btnEmpresa.setSelected(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelPermisos = new com.anthony.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        usuNombres = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuCedula = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnCliente = new com.anthony.swing.SwitchButton();
        btnEmpleados = new com.anthony.swing.SwitchButton();
        btnProveedores = new com.anthony.swing.SwitchButton();
        btnProductos = new com.anthony.swing.SwitchButton();
        btnFacturacion = new com.anthony.swing.SwitchButton();
        btnNotaCredito = new com.anthony.swing.SwitchButton();
        btnNotaDebito = new com.anthony.swing.SwitchButton();
        btnReporteGeneral = new com.anthony.swing.SwitchButton();
        btnReporteEstad = new com.anthony.swing.SwitchButton();
        btnRepNotCredit = new com.anthony.swing.SwitchButton();
        btnRepNotDebit = new com.anthony.swing.SwitchButton();
        btnRevFacturas = new com.anthony.swing.SwitchButton();
        btnUsuarios = new com.anthony.swing.SwitchButton();
        btnPermisos = new com.anthony.swing.SwitchButton();
        btnAjustes = new com.anthony.swing.SwitchButton();
        btnEmpresa = new com.anthony.swing.SwitchButton();
        btnActualizar = new com.anthony.swing.Button();
        btnCancelarSuc1 = new com.anthony.swing.Button();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblIdUsuario = new javax.swing.JLabel();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        spUsuarios = new javax.swing.JScrollPane();
        tDatosUsuarios = new rojeru_san.complementos.TableMetro();
        panelBuscar = new com.anthony.swing.RoundPanel();
        txtBusqueda = new textfield.TextField();
        jLabel23 = new javax.swing.JLabel();
        rdbtnUsuario = new com.anthony.swing.RadioButton();
        rdbtnCargoUsuario = new com.anthony.swing.RadioButton();
        rdbtnEstadoUsuario = new com.anthony.swing.RadioButton();
        jLabel24 = new javax.swing.JLabel();
        lblTotalRegistros1 = new javax.swing.JLabel();
        btnBuscar1 = new com.anthony.swing.Button();

        setBackground(new java.awt.Color(234, 241, 251));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 122, 108));
        jLabel1.setText("PERMISOS DE USUARIO EN EL SISTEMA");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        sp.setBackground(new java.awt.Color(233, 233, 233));
        sp.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(234, 241, 251));

        panelPermisos.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 122, 108));
        jLabel2.setText("USUARIO:");

        usuNombres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usuNombres.setForeground(new java.awt.Color(7, 16, 17));
        usuNombres.setText("RICHARD ANTHONY PEREZ PALACIOS");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("• Cuando el switch esta en color plomo no tiene autorizacion el usuario elegido");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("• Cuando el switch esta en color azul tiene autorizacion el usuario elegido.");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 122, 108));
        jLabel3.setText("OBSERVACION:");

        usuCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usuCedula.setForeground(new java.awt.Color(7, 16, 17));
        usuCedula.setText("1723382594");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(7, 16, 17));
        jLabel6.setText("Clientes");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(7, 16, 17));
        jLabel7.setText("Empleados");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(7, 16, 17));
        jLabel8.setText("Proveedores");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(7, 16, 17));
        jLabel9.setText("Productos");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(7, 16, 17));
        jLabel10.setText("Facturacion");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(7, 16, 17));
        jLabel11.setText("Nota de credito");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(7, 16, 17));
        jLabel12.setText("Usuarios");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(7, 16, 17));
        jLabel13.setText("Repts. Nota de debito");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(7, 16, 17));
        jLabel14.setText("Repts. Nota de credito");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(7, 16, 17));
        jLabel15.setText("Repts. estadisticos");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(7, 16, 17));
        jLabel16.setText("Reportes generales");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(7, 16, 17));
        jLabel17.setText("Nota de debito");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(7, 16, 17));
        jLabel18.setText("Permisos de usuario");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(7, 16, 17));
        jLabel19.setText("Ajustes");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(7, 16, 17));
        jLabel20.setText("Ajustes de la empresa");

        btnActualizar.setBackground(new java.awt.Color(235, 190, 25));
        btnActualizar.setForeground(new java.awt.Color(7, 6, 17));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFocusPainted(false);
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelarSuc1.setBackground(new java.awt.Color(250, 104, 8));
        btnCancelarSuc1.setForeground(new java.awt.Color(7, 6, 17));
        btnCancelarSuc1.setText("CANCELAR");
        btnCancelarSuc1.setFocusPainted(false);
        btnCancelarSuc1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarSuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSuc1ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(108, 95, 70));
        jLabel21.setText("-");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(7, 16, 17));
        jLabel22.setText("Rev. Facturas");

        lblIdUsuario.setForeground(new java.awt.Color(32, 32, 32));

        javax.swing.GroupLayout panelPermisosLayout = new javax.swing.GroupLayout(panelPermisos);
        panelPermisos.setLayout(panelPermisosLayout);
        panelPermisosLayout.setHorizontalGroup(
            panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPermisosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPermisosLayout.createSequentialGroup()
                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPermisosLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 108, Short.MAX_VALUE)
                                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(15, 15, 15)
                                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPermisosLayout.createSequentialGroup()
                                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnRepNotCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnRepNotDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelPermisosLayout.createSequentialGroup()
                                                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnNotaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnReporteGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnReporteEstad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                                                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(15, 15, 15)
                                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnRevFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelPermisosLayout.createSequentialGroup()
                                        .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelPermisosLayout.createSequentialGroup()
                                .addComponent(usuCedula)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIdUsuario))))
                    .addGroup(panelPermisosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(6, 6, 6))
        );
        panelPermisosLayout.setVerticalGroup(
            panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPermisosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(usuCedula)
                        .addComponent(usuNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIdUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPermisosLayout.createSequentialGroup()
                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelPermisosLayout.createSequentialGroup()
                                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelPermisosLayout.createSequentialGroup()
                                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelPermisosLayout.createSequentialGroup()
                                                .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelPermisosLayout.createSequentialGroup()
                                                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel6)
                                                            .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel7))
                                                    .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel8))
                                            .addComponent(btnProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9))
                                    .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))
                            .addComponent(btnFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(btnNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPermisosLayout.createSequentialGroup()
                        .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNotaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelPermisosLayout.createSequentialGroup()
                            .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPermisosLayout.createSequentialGroup()
                                    .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelPermisosLayout.createSequentialGroup()
                                            .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(panelPermisosLayout.createSequentialGroup()
                                                    .addComponent(jLabel18)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(jLabel22))
                                                .addGroup(panelPermisosLayout.createSequentialGroup()
                                                    .addComponent(btnPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnRevFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel19)))
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel20)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCancelarSuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelPermisosLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelPermisosLayout.createSequentialGroup()
                                    .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelPermisosLayout.createSequentialGroup()
                                            .addGroup(panelPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(panelPermisosLayout.createSequentialGroup()
                                                    .addComponent(btnReporteGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(37, 37, 37))
                                                .addComponent(btnReporteEstad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(37, 37, 37))
                                        .addComponent(btnRepNotCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(37, 37, 37))
                                .addComponent(btnRepNotDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        panelPermisosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel21, usuCedula, usuNombres});

        panelPermisosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCliente, btnEmpleados, btnFacturacion, btnNotaCredito, btnProductos, btnProveedores, jLabel10, jLabel11, jLabel6, jLabel7, jLabel8, jLabel9});

        panelPermisosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnNotaDebito, btnRepNotCredit, btnRepNotDebit, btnReporteEstad, btnReporteGeneral, btnUsuarios, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17});

        panelPermisosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAjustes, btnEmpresa, btnPermisos, btnRevFacturas, jLabel18, jLabel19, jLabel20, jLabel22});

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        spUsuarios.setBackground(new java.awt.Color(69, 66, 175));
        spUsuarios.setBorder(null);
        spUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        spUsuarios.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        spUsuarios.setFocusable(false);
        spUsuarios.setOpaque(false);

        tDatosUsuarios = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        tDatosUsuarios.setForeground(new java.awt.Color(234, 241, 251));
        tDatosUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosUsuarios.setAltoHead(30);
        tDatosUsuarios.setAutoscrolls(false);
        tDatosUsuarios.setColorBackgoundHead(new java.awt.Color(243, 248, 255));
        tDatosUsuarios.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tDatosUsuarios.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosUsuarios.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tDatosUsuarios.setColorFilasForeground1(new java.awt.Color(123, 123, 123));
        tDatosUsuarios.setColorFilasForeground2(new java.awt.Color(123, 123, 123));
        tDatosUsuarios.setColorForegroundHead(new java.awt.Color(7, 6, 17));
        tDatosUsuarios.setColorSelBackgound(new java.awt.Color(224, 237, 255));
        tDatosUsuarios.setColorSelForeground(new java.awt.Color(102, 102, 102));
        tDatosUsuarios.setFocusable(false);
        tDatosUsuarios.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosUsuarios.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosUsuarios.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosUsuarios.setGridColor(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spUsuarios)
                .addGap(3, 3, 3))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(spUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        panelBuscar.setBackground(new java.awt.Color(255, 255, 255));

        txtBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setBorder(null);
        txtBusqueda.setForeground(new java.awt.Color(0, 153, 204));
        txtBusqueda.setLabelText("Escribe la sucursal que deseas buscar....");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        jLabel23.setForeground(new java.awt.Color(7, 6, 17));
        jLabel23.setText("Buscar por:");

        rdbtnUsuario.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbtnUsuario);
        rdbtnUsuario.setForeground(new java.awt.Color(7, 6, 17));
        rdbtnUsuario.setText("USUARIO");
        rdbtnUsuario.setFocusPainted(false);
        rdbtnUsuario.setRequestFocusEnabled(false);

        rdbtnCargoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbtnCargoUsuario);
        rdbtnCargoUsuario.setForeground(new java.awt.Color(7, 6, 17));
        rdbtnCargoUsuario.setText("CARGO");
        rdbtnCargoUsuario.setFocusPainted(false);
        rdbtnCargoUsuario.setRequestFocusEnabled(false);

        rdbtnEstadoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbtnEstadoUsuario);
        rdbtnEstadoUsuario.setForeground(new java.awt.Color(7, 6, 17));
        rdbtnEstadoUsuario.setText("ESTADO");
        rdbtnEstadoUsuario.setFocusPainted(false);
        rdbtnEstadoUsuario.setRequestFocusEnabled(false);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(7, 6, 17));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("TOTAL DE REGISTROS:");

        lblTotalRegistros1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalRegistros1.setForeground(new java.awt.Color(63, 81, 102));
        lblTotalRegistros1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 471, Short.MAX_VALUE))
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(rdbtnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnCargoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbtnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnCargoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPermisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sp.setViewportView(jPanel1);

        btnBuscar1.setBackground(new java.awt.Color(8, 170, 250));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        btnBuscar1.setFocusPainted(false);
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ocultar();
        tablaUsuarios();
        panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Se actualizao la base de datos!!");
        panel.showNotification();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnCancelarSuc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSuc1ActionPerformed
        ocultar();
    }//GEN-LAST:event_btnCancelarSuc1ActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        buscarUsuario();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void tDatosUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosUsuariosMouseClicked
        panelBuscar.setVisible(false);
        panelPermisos.setVisible(true);
        cargarDatosFormulario();
    }//GEN-LAST:event_tDatosUsuariosMouseClicked

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        panelBuscar.setVisible(true);
        panelPermisos.setVisible(false);
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            MessageDialogLight obj = new MessageDialogLight(admin);
            obj.showMessage("¿Desea actualizar los permisos del usuario?", "");
            if (btnCliente.isSelected()) {
                PER_CLIENTE = "CON PERMISO";
            } else {
                PER_CLIENTE = "SIN PERMISO";
            }
            if (btnEmpleados.isSelected()) {
                PER_EMPLEADO = "CON PERMISO";
            } else {
                PER_EMPLEADO = "SIN PERMISO";
            }
            if (btnProveedores.isSelected()) {
                PER_PROVEEDOR = "CON PERMISO";
            } else {
                PER_PROVEEDOR = "SIN PERMISO";
            }
            if (btnProductos.isSelected()) {
                PER_PRODUCTO = "CON PERMISO";
            } else {
                PER_PRODUCTO = "SIN PERMISO";
            }
            if (btnFacturacion.isSelected()) {
                PER_FACTURACION = "CON PERMISO";
            } else {
                PER_FACTURACION = "SIN PERMISO";
            }
            if (btnNotaDebito.isSelected()) {
                PER_NOTA_DEBITO = "CON PERMISO";
            } else {
                PER_NOTA_DEBITO = "SIN PERMISO";
            }
            if (btnNotaCredito.isSelected()) {
                PER_NOTA_CREDITO = "CON PERMISO";
            } else {
                PER_NOTA_CREDITO = "SIN PERMISO";
            }
            if (btnReporteGeneral.isSelected()) {
                PER_REP_GENERALES = "CON PERMISO";
            } else {
                PER_REP_GENERALES = "SIN PERMISO";
            }
            if (btnReporteEstad.isSelected()) {
                PER_REP_ESTADISTICOS = "CON PERMISO";
            } else {
                PER_REP_ESTADISTICOS = "SIN PERMISO";
            }
            if (btnRevFacturas.isSelected()) {
                PER_REV_FACTURAS = "CON PERMISO";
            } else {
                PER_REV_FACTURAS = "SIN PERMISO";
            }
            if (btnRepNotCredit.isSelected()) {
                PER_REV_NOT_CREDITO = "CON PERMISO";
            } else {
                PER_REV_NOT_CREDITO = "SIN PERMISO";
            }
            if (btnRepNotDebit.isSelected()) {
                PER_REV_NOT_DEBITO = "CON PERMISO";
            } else {
                PER_REV_NOT_DEBITO = "SIN PERMISO";
            }
            if (btnUsuarios.isSelected()) {
                PER_USUARIOS = "CON PERMISO";
            } else {
                PER_USUARIOS = "SIN PERMISO";
            }
            if (btnPermisos.isSelected()) {
                PER_PERMISOS = "CON PERMISO";
            } else {
                PER_PERMISOS = "SIN PERMISO";
            }
            if (btnAjustes.isSelected()) {
                PER_AJUSTES = "CON PERMISO";
            } else {
                PER_AJUSTES = "SIN PERMISO";
            }
            if (btnEmpresa.isSelected()) {
                PER_EMPRESA = "CON PERMISO";
            } else {
                PER_EMPRESA = "SIN PERMISO";
            }
            FK_USUARIO = Integer.parseInt(lblIdUsuario.getText());
            per.setFK_USUARIO(FK_USUARIO);
            per.setPER_CLIENTE(PER_CLIENTE);
            per.setPER_EMPLEADO(PER_EMPLEADO);
            per.setPER_PROVEEDOR(PER_PROVEEDOR);
            per.setPER_PRODUCTO(PER_PRODUCTO);
            per.setPER_FACTURACION(PER_FACTURACION);
            per.setPER_NOTA_DEBITO(PER_NOTA_DEBITO);
            per.setPER_NOTA_CREDITO(PER_NOTA_CREDITO);
            per.setPER_REP_GENERALES(PER_REP_GENERALES);
            per.setPER_REP_ESTADISTICOS(PER_REP_ESTADISTICOS);
            per.setPER_REV_FACTURAS(PER_REV_FACTURAS);
            per.setPER_REV_NOT_CREDITO(PER_REV_NOT_CREDITO);
            per.setPER_REV_NOT_DEBITO(PER_REV_NOT_DEBITO);
            per.setPER_USUARIOS(PER_USUARIOS);
            per.setPER_PERMISOS(PER_PERMISOS);
            per.setPER_AJUSTES(PER_AJUSTES);
            per.setPER_EMPRESA(PER_EMPRESA);
            if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                if (daoPer.update(per) == "El permiso fue actualizado con exito!") {
                    panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Los permisos se actualizaron con exito!!");
                    panel.showNotification();
//                    limpiarFormulario();
                    ocultar();
                } else if (daoPer.update(per) == "El permiso no fue actualizado!") {
                    panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar los permisos!!");
                    panel.showNotification();
//                    limpiarFormulario();
                    ocultar();
                }
            } else if (obj.getMessageType() == MessageDialogLight.MessageType.CANCEL) {
                panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
                panel.showNotification();
//                limpiarFormulario();
                ocultar();
            }
        } catch (Exception ex) {
            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Hubo un error al procesar tu peticion, favor corregirlos!!");
            panel.showNotification();
//            limpiarFormulario();
            ocultar();
            System.out.println("Hubo un error al procesar tu peticion, favor corregirlos!!" + ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnActualizar;
    private com.anthony.swing.SwitchButton btnAjustes;
    private com.anthony.swing.Button btnBuscar1;
    private com.anthony.swing.Button btnCancelarSuc1;
    private com.anthony.swing.SwitchButton btnCliente;
    private com.anthony.swing.SwitchButton btnEmpleados;
    private com.anthony.swing.SwitchButton btnEmpresa;
    private com.anthony.swing.SwitchButton btnFacturacion;
    private com.anthony.swing.SwitchButton btnNotaCredito;
    private com.anthony.swing.SwitchButton btnNotaDebito;
    private com.anthony.swing.SwitchButton btnPermisos;
    private com.anthony.swing.SwitchButton btnProductos;
    private com.anthony.swing.SwitchButton btnProveedores;
    private com.anthony.swing.SwitchButton btnRepNotCredit;
    private com.anthony.swing.SwitchButton btnRepNotDebit;
    private com.anthony.swing.SwitchButton btnReporteEstad;
    private com.anthony.swing.SwitchButton btnReporteGeneral;
    private com.anthony.swing.SwitchButton btnRevFacturas;
    private com.anthony.swing.SwitchButton btnUsuarios;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblTotalRegistros1;
    private com.anthony.swing.RoundPanel panelBuscar;
    private com.anthony.swing.RoundPanel panelPermisos;
    private com.anthony.swing.RadioButton rdbtnCargoUsuario;
    private com.anthony.swing.RadioButton rdbtnEstadoUsuario;
    private com.anthony.swing.RadioButton rdbtnUsuario;
    private com.anthony.swing.RoundPanel roundPanel2;
    private javax.swing.JScrollPane sp;
    private javax.swing.JScrollPane spUsuarios;
    private rojeru_san.complementos.TableMetro tDatosUsuarios;
    private textfield.TextField txtBusqueda;
    private javax.swing.JLabel usuCedula;
    private javax.swing.JLabel usuNombres;
    // End of variables declaration//GEN-END:variables
}
