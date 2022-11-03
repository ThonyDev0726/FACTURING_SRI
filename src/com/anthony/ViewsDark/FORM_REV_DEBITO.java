package com.anthony.ViewsDark;

import VO.ArchivosVO;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.USUARIO;
import com.anthony.VisorPdf.JnaFileChooser;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mivisorpdf.MiVisorPDF;

public class FORM_REV_DEBITO extends javax.swing.JPanel {

    RoundBorder border = new RoundBorder(0);
    //Contador de paginas
    private int numImg;
    Color activo = new Color(39, 43, 50);
    Color inactivo = new Color(32, 32, 32);

    //Contiene el archivo PDF en bytes de imagenes
    private ArrayList<ArchivosVO> ListaComponente;
    MiVisorPDF pn = new MiVisorPDF();
    ArchivosVO pl = new ArchivosVO();

    //Para mostrar el total de paginas
    private int paginas = -1;
    private int totalp = -1;

    //Guardamos la ruta del PDF--------------------------------------
    private String ruta_archivo = "";
    //---------------------------------------------------------------

    //Mantiene el zoom en la pagina que asignamos
    int zoom = 0;

    public FORM_REV_DEBITO() {
        initComponents();
        scroll();
        tabClientes.setBackground(activo);
        btnClientes.setVisible(true);
    }

    public FORM_REV_DEBITO(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        scroll();
        tabClientes.setBackground(activo);
        btnClientes.setVisible(true);
    }

    private void scroll() {
        sc.setVerticalScrollBar(new ScrollBarCustom());
        sc.setHorizontalScrollBar(scrollBarCustom1);
        sc.getViewport().setOpaque(false);
        lblNombreArchivo.setText("Sin documento");
        spClientes.setVerticalScrollBar(new ScrollBarCustom());
        spEmpleados.setVerticalScrollBar(new ScrollBarCustom());
        spSucursal.setVerticalScrollBar(new ScrollBarCustom());
        spGeneral.setVerticalScrollBar(new ScrollBarCustom());
        sc.getViewport().setOpaque(false);
        spClientes.getViewport().setOpaque(false);
        spEmpleados.getViewport().setOpaque(false);
        spSucursal.getViewport().setOpaque(false);
        spGeneral.getViewport().setOpaque(false);
        bordes();
    }

    

    public void bordes() {
        sc.setVerticalScrollBar(new ScrollBarCustom());
        spClientes.setBorder(border);
        spEmpleados.setBorder(border);
        spSucursal.setBorder(border);
        spGeneral.setBorder(border);
    }
    

    //Metodo abrir documento PDF
    public void abrir_pdf(String url) {
        //Es considerado pagina 1
        this.numImg = 0;
        //Lee la pagina 1
        this.ListaComponente = pn.leerPDF(url);
        //Guardamos todas las paginas en el ArrayList
        for (int i = 0; i < ListaComponente.size(); i++) {
            pl = ListaComponente.get(i);;
            this.img.setImagen(pl.getArchivos());
        }
        //Pagina 1 lo muestra en la pantalla
        paginas = 1;
        totalp = ListaComponente.size();
        p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));

        //tp.setText(String.valueOf(totalp));
        //Mostramos la primera pagina
        ArchivosVO pi = new ArchivosVO();
        pi = ListaComponente.get(0);
        this.img.setImagen(pi.getArchivos());
    }

    //Metodo para adaptar el zoom a la pantalla
    public void adaptar_vista() {
        //System.out.print(zoom);
        if (zoom > 0) {
            for (int i = 0; i <= zoom; i++) {
                this.img.aumentar();
            }
        }
        if (zoom < 0) {
            String z = String.valueOf(zoom);
            z = z.replace("-", "");
            int zz = Integer.parseInt(z);
            for (int j = 0; j <= zz; j++) {
                this.img.disminuir();
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        jPanel1 = new javax.swing.JPanel();
        panelClientes = new javax.swing.JPanel();
        txtNombres = new textfield.TextField();
        rdtbApellidos = new com.anthony.swing.RadioButton();
        rdtbCedula = new com.anthony.swing.RadioButton();
        rdtbRuc = new com.anthony.swing.RadioButton();
        spClientes = new javax.swing.JScrollPane();
        tDatosClientes = new rojeru_san.complementos.TableMetro();
        panelEmpleados = new javax.swing.JPanel();
        txtNombres1 = new textfield.TextField();
        rdtbApellidos1 = new com.anthony.swing.RadioButton();
        rdtbCedula1 = new com.anthony.swing.RadioButton();
        spEmpleados = new javax.swing.JScrollPane();
        tDatosEmpleados = new rojeru_san.complementos.TableMetro();
        panelSucursales = new javax.swing.JPanel();
        txtNombres2 = new textfield.TextField();
        rdtbApellidos2 = new com.anthony.swing.RadioButton();
        spSucursal = new javax.swing.JScrollPane();
        tDatosSucusal = new rojeru_san.complementos.TableMetro();
        panelGeneral = new javax.swing.JPanel();
        txtRuc = new textfield.TextField();
        spGeneral = new javax.swing.JScrollPane();
        tDatosGeneral = new rojeru_san.complementos.TableMetro();
        rdtbApellidos3 = new com.anthony.swing.RadioButton();
        rdtbApellidos4 = new com.anthony.swing.RadioButton();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        sc = new javax.swing.JScrollPane();
        img = new mivisorpdf.CuadroImagen();
        scrollBarCustom1 = new com.anthony.swing.scrollbar.ScrollBarCustom();
        roundPanel3 = new com.anthony.swing.RoundPanel();
        p = new javax.swing.JLabel();
        roundPanel5 = new com.anthony.swing.RoundPanel();
        p1 = new javax.swing.JLabel();
        roundPanel6 = new com.anthony.swing.RoundPanel();
        p2 = new javax.swing.JLabel();
        roundPanel8 = new com.anthony.swing.RoundPanel();
        p4 = new javax.swing.JLabel();
        roundPanel9 = new com.anthony.swing.RoundPanel();
        lblArchivo = new javax.swing.JLabel();
        roundPanel7 = new com.anthony.swing.RoundPanel();
        p3 = new javax.swing.JLabel();
        roundPanel10 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo = new javax.swing.JLabel();
        tabClientes = new com.anthony.swing.RoundPanel();
        btnClientes = new javax.swing.JLabel();
        tabEmpleados = new com.anthony.swing.RoundPanel();
        btnEmpleados = new javax.swing.JLabel();
        tabSucursales = new com.anthony.swing.RoundPanel();
        btnSucursal = new javax.swing.JLabel();
        tabGenerales = new com.anthony.swing.RoundPanel();
        btnGeneral = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("REVISION NOTA DE DEBITO");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));

        jPanel1.setBackground(new java.awt.Color(32, 32, 32));
        jPanel1.setLayout(new java.awt.CardLayout());

        panelClientes.setBackground(new java.awt.Color(32, 32, 32));

        txtNombres.setBackground(new java.awt.Color(32, 32, 32));
        txtNombres.setForeground(new java.awt.Color(0, 153, 204));
        txtNombres.setLabelText("Nombres");

        rdtbApellidos.setBackground(new java.awt.Color(32, 32, 32));
        rdtbApellidos.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos.setText("Apellidos");
        rdtbApellidos.setFocusPainted(false);
        rdtbApellidos.setOpaque(true);
        rdtbApellidos.setRequestFocusEnabled(false);

        rdtbCedula.setBackground(new java.awt.Color(32, 32, 32));
        rdtbCedula.setForeground(new java.awt.Color(63, 81, 102));
        rdtbCedula.setText("Cedula");
        rdtbCedula.setFocusPainted(false);
        rdtbCedula.setOpaque(true);
        rdtbCedula.setRequestFocusEnabled(false);

        rdtbRuc.setBackground(new java.awt.Color(32, 32, 32));
        rdtbRuc.setForeground(new java.awt.Color(63, 81, 102));
        rdtbRuc.setText("RUC");
        rdtbRuc.setFocusPainted(false);
        rdtbRuc.setOpaque(true);
        rdtbRuc.setRequestFocusEnabled(false);

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

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addComponent(rdtbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdtbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdtbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdtbRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelClientes, "card2");

        panelEmpleados.setBackground(new java.awt.Color(32, 32, 32));

        txtNombres1.setBackground(new java.awt.Color(32, 32, 32));
        txtNombres1.setForeground(new java.awt.Color(0, 153, 204));
        txtNombres1.setLabelText("Nombres");

        rdtbApellidos1.setBackground(new java.awt.Color(32, 32, 32));
        rdtbApellidos1.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos1.setText("Apellidos");
        rdtbApellidos1.setFocusPainted(false);
        rdtbApellidos1.setOpaque(true);
        rdtbApellidos1.setRequestFocusEnabled(false);

        rdtbCedula1.setBackground(new java.awt.Color(32, 32, 32));
        rdtbCedula1.setForeground(new java.awt.Color(63, 81, 102));
        rdtbCedula1.setText("Cedula");
        rdtbCedula1.setFocusPainted(false);
        rdtbCedula1.setOpaque(true);
        rdtbCedula1.setRequestFocusEnabled(false);

        spEmpleados.setBackground(new java.awt.Color(32, 32, 32));
        spEmpleados.setBorder(null);
        spEmpleados.setForeground(new java.awt.Color(32, 32, 32));
        spEmpleados.setFocusable(false);
        spEmpleados.setOpaque(false);

        tDatosClientes = new rojeru_san.complementos.TableMetro(){
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
        tDatosEmpleados.setAutoscrolls(false);
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
        spEmpleados.setViewportView(tDatosEmpleados);

        javax.swing.GroupLayout panelEmpleadosLayout = new javax.swing.GroupLayout(panelEmpleados);
        panelEmpleados.setLayout(panelEmpleadosLayout);
        panelEmpleadosLayout.setHorizontalGroup(
            panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpleadosLayout.createSequentialGroup()
                        .addComponent(rdtbApellidos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbCedula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(266, 266, 266))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmpleadosLayout.createSequentialGroup()
                        .addGroup(panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spEmpleados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtNombres1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelEmpleadosLayout.setVerticalGroup(
            panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtNombres1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdtbApellidos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdtbCedula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelEmpleados, "card2");

        panelSucursales.setBackground(new java.awt.Color(32, 32, 32));

        txtNombres2.setBackground(new java.awt.Color(32, 32, 32));
        txtNombres2.setForeground(new java.awt.Color(0, 153, 204));
        txtNombres2.setLabelText("Nombres");

        rdtbApellidos2.setBackground(new java.awt.Color(32, 32, 32));
        rdtbApellidos2.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos2.setText("Sucursal");
        rdtbApellidos2.setFocusPainted(false);
        rdtbApellidos2.setOpaque(true);
        rdtbApellidos2.setRequestFocusEnabled(false);

        spSucursal.setBackground(new java.awt.Color(32, 32, 32));
        spSucursal.setBorder(null);
        spSucursal.setForeground(new java.awt.Color(32, 32, 32));
        spSucursal.setFocusable(false);
        spSucursal.setOpaque(false);

        tDatosClientes = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosSucusal.setBackground(new java.awt.Color(32, 32, 32));
        tDatosSucusal.setForeground(new java.awt.Color(32, 32, 32));
        tDatosSucusal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosSucusal.setAltoHead(30);
        tDatosSucusal.setAutoscrolls(false);
        tDatosSucusal.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosSucusal.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosSucusal.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosSucusal.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosSucusal.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosSucusal.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosSucusal.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosSucusal.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosSucusal.setFocusable(false);
        tDatosSucusal.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosSucusal.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosSucusal.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosSucusal.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosSucusal.setGrosorBordeFilas(0);
        tDatosSucusal.setGrosorBordeHead(0);
        tDatosSucusal.setOpaque(false);
        tDatosSucusal.setRowHeight(30);
        tDatosSucusal.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosSucusal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosSucusalMouseClicked(evt);
            }
        });
        spSucursal.setViewportView(tDatosSucusal);

        javax.swing.GroupLayout panelSucursalesLayout = new javax.swing.GroupLayout(panelSucursales);
        panelSucursales.setLayout(panelSucursalesLayout);
        panelSucursalesLayout.setHorizontalGroup(
            panelSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSucursalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSucursalesLayout.createSequentialGroup()
                        .addComponent(rdtbApellidos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 343, Short.MAX_VALUE))
                    .addComponent(txtNombres2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelSucursalesLayout.setVerticalGroup(
            panelSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSucursalesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtNombres2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdtbApellidos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelSucursales, "card2");

        panelGeneral.setBackground(new java.awt.Color(32, 32, 32));

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

        spGeneral.setBackground(new java.awt.Color(32, 32, 32));
        spGeneral.setBorder(null);
        spGeneral.setForeground(new java.awt.Color(32, 32, 32));
        spGeneral.setFocusable(false);
        spGeneral.setOpaque(false);

        tDatosClientes = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosGeneral.setBackground(new java.awt.Color(32, 32, 32));
        tDatosGeneral.setForeground(new java.awt.Color(32, 32, 32));
        tDatosGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDatosGeneral.setAltoHead(30);
        tDatosGeneral.setAutoscrolls(false);
        tDatosGeneral.setColorBackgoundHead(new java.awt.Color(23, 25, 27));
        tDatosGeneral.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosGeneral.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosGeneral.setColorFilasBackgound2(new java.awt.Color(22, 23, 23));
        tDatosGeneral.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosGeneral.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosGeneral.setColorForegroundHead(new java.awt.Color(130, 119, 96));
        tDatosGeneral.setColorSelBackgound(new java.awt.Color(61, 61, 61));
        tDatosGeneral.setFocusable(false);
        tDatosGeneral.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosGeneral.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tDatosGeneral.setFuenteHead(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tDatosGeneral.setGridColor(new java.awt.Color(32, 32, 32));
        tDatosGeneral.setGrosorBordeFilas(0);
        tDatosGeneral.setGrosorBordeHead(0);
        tDatosGeneral.setOpaque(false);
        tDatosGeneral.setRowHeight(30);
        tDatosGeneral.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosGeneralMouseClicked(evt);
            }
        });
        spGeneral.setViewportView(tDatosGeneral);

        rdtbApellidos3.setBackground(new java.awt.Color(32, 32, 32));
        rdtbApellidos3.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos3.setText("Sucursal");
        rdtbApellidos3.setFocusPainted(false);
        rdtbApellidos3.setOpaque(true);
        rdtbApellidos3.setRequestFocusEnabled(false);

        rdtbApellidos4.setBackground(new java.awt.Color(32, 32, 32));
        rdtbApellidos4.setForeground(new java.awt.Color(63, 81, 102));
        rdtbApellidos4.setText("Sucursal");
        rdtbApellidos4.setFocusPainted(false);
        rdtbApellidos4.setOpaque(true);
        rdtbApellidos4.setRequestFocusEnabled(false);

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRuc, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addComponent(rdtbApellidos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdtbApellidos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdtbApellidos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdtbApellidos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelGeneral, "card2");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel2.setBackground(new java.awt.Color(32, 32, 32));

        sc.setBackground(new java.awt.Color(32, 32, 32));
        sc.setBorder(null);
        sc.setHorizontalScrollBar(scrollBarCustom1);

        img.setBackground(new java.awt.Color(32, 32, 32));
        img.setOpaque(false);
        img.setPreferredSize(new java.awt.Dimension(210, 297));

        javax.swing.GroupLayout imgLayout = new javax.swing.GroupLayout(img);
        img.setLayout(imgLayout);
        imgLayout.setHorizontalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );
        imgLayout.setVerticalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        sc.setViewportView(img);

        scrollBarCustom1.setOrientation(javax.swing.JScrollBar.HORIZONTAL);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollBarCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sc, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        roundPanel3.setBackground(new java.awt.Color(32, 32, 32));

        p.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        p.setForeground(new java.awt.Color(130, 119, 96));
        p.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p.setText("0 paginas");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel5.setBackground(new java.awt.Color(32, 32, 32));

        p1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p1.setForeground(new java.awt.Color(63, 81, 102));
        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoIzquierda.png"))); // NOI18N
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel6.setBackground(new java.awt.Color(32, 32, 32));

        p2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p2.setForeground(new java.awt.Color(63, 81, 102));
        p2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoDerecha.png"))); // NOI18N
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel8.setBackground(new java.awt.Color(32, 32, 32));

        p4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p4.setForeground(new java.awt.Color(63, 81, 102));
        p4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoMenos.png"))); // NOI18N
        p4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel9.setBackground(new java.awt.Color(32, 32, 32));

        lblArchivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblArchivo.setForeground(new java.awt.Color(130, 119, 96));
        lblArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoArchivo.png"))); // NOI18N
        lblArchivo.setText("Abrir archivo");
        lblArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArchivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel9Layout = new javax.swing.GroupLayout(roundPanel9);
        roundPanel9.setLayout(roundPanel9Layout);
        roundPanel9Layout.setHorizontalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblArchivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel9Layout.setVerticalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel7.setBackground(new java.awt.Color(32, 32, 32));

        p3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p3.setForeground(new java.awt.Color(63, 81, 102));
        p3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoSuma.png"))); // NOI18N
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel10.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel10Layout = new javax.swing.GroupLayout(roundPanel10);
        roundPanel10.setLayout(roundPanel10Layout);
        roundPanel10Layout.setHorizontalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel10Layout.setVerticalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        tabClientes.setBackground(new java.awt.Color(32, 32, 32));

        btnClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(130, 119, 96));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoArchivo.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClientesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tabClientesLayout = new javax.swing.GroupLayout(tabClientes);
        tabClientes.setLayout(tabClientesLayout);
        tabClientesLayout.setHorizontalGroup(
            tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClientes)
                .addContainerGap())
        );
        tabClientesLayout.setVerticalGroup(
            tabClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        tabEmpleados.setBackground(new java.awt.Color(32, 32, 32));

        btnEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEmpleados.setForeground(new java.awt.Color(130, 119, 96));
        btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoArchivo.png"))); // NOI18N
        btnEmpleados.setText("Empleado");
        btnEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmpleadosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tabEmpleadosLayout = new javax.swing.GroupLayout(tabEmpleados);
        tabEmpleados.setLayout(tabEmpleadosLayout);
        tabEmpleadosLayout.setHorizontalGroup(
            tabEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEmpleados)
                .addContainerGap())
        );
        tabEmpleadosLayout.setVerticalGroup(
            tabEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEmpleados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        tabSucursales.setBackground(new java.awt.Color(32, 32, 32));

        btnSucursal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSucursal.setForeground(new java.awt.Color(130, 119, 96));
        btnSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoArchivo.png"))); // NOI18N
        btnSucursal.setText("Sucursal");
        btnSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSucursalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tabSucursalesLayout = new javax.swing.GroupLayout(tabSucursales);
        tabSucursales.setLayout(tabSucursalesLayout);
        tabSucursalesLayout.setHorizontalGroup(
            tabSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSucursalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSucursal)
                .addContainerGap())
        );
        tabSucursalesLayout.setVerticalGroup(
            tabSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSucursal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        tabGenerales.setBackground(new java.awt.Color(32, 32, 32));

        btnGeneral.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGeneral.setForeground(new java.awt.Color(130, 119, 96));
        btnGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoArchivo.png"))); // NOI18N
        btnGeneral.setText("Generales");
        btnGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGeneralMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tabGeneralesLayout = new javax.swing.GroupLayout(tabGenerales);
        tabGenerales.setLayout(tabGeneralesLayout);
        tabGeneralesLayout.setHorizontalGroup(
            tabGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGeneral)
                .addContainerGap())
        );
        tabGeneralesLayout.setVerticalGroup(
            tabGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {roundPanel5, roundPanel6});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tabClientes, tabEmpleados, tabGenerales, tabSucursales});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
//Metodo usado para retroceder pagina
        //El if para comprobar que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //if para que comprueba si la pagina es 1 entonces se ira a la ultima pagina
            this.numImg -= 1;
            if (paginas == 1) {
                paginas = totalp;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            } else {
                paginas = paginas - 1;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            }
            //Aplicamos la pagina respectiva para mostrarlo
            if (this.numImg < 0) {
                this.numImg = (this.ListaComponente.size() - 1);
            }
            ArchivosVO pi = new ArchivosVO();
            for (int i = 0; i < ListaComponente.size(); i++) {
                pi = ListaComponente.get(numImg);
                break;
            }
            //Con este metodo mostramos la imagen
            this.img.setImagen(pi.getArchivos());
            //Este metodo adapta el zoom a la pantalla
            adaptar_vista();

        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p1MouseClicked

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        //Este metodo pasa la pagina hacia adelante
        //Verifica que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //Este if comprueba si la pagina esta en el ultimo
            //O si esta en el primero pasara a la siguiente pagina
            this.numImg += 1;
            if (paginas == totalp) {
                paginas = 1;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            } else {
                paginas = paginas + 1;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            }
            //Aplica los cambios de la pagina que corresponde
            if (this.numImg >= this.ListaComponente.size()) {
                this.numImg = 0;
            }
            ArchivosVO pi = new ArchivosVO();
            for (int i = 0; i < ListaComponente.size(); i++) {
                pi = ListaComponente.get(numImg);
                break;
            }
            //Muestra la pagina que corresponde
            this.img.setImagen(pi.getArchivos());
            //adapta la pagina a la pantalla
            adaptar_vista();
        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p2MouseClicked

    private void lblArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArchivoMouseClicked
        JnaFileChooser jnaCh = new JnaFileChooser();
        boolean save = jnaCh.showOpenDialog(null);
        if (save) {
            System.out.println(jnaCh.getSelectedFile());
            //Usamos el metodo abrir pdf para mostrarlo
            ruta_archivo = jnaCh.getSelectedFile().toString();
            lblNombreArchivo.setText("Archivo: " + jnaCh.getSelectedFile().getName());
            abrir_pdf(ruta_archivo);
            this.img.disminuir();
        }
    }//GEN-LAST:event_lblArchivoMouseClicked

    private void lblNombreArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivoMouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        //Este metodo comprueba que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //Aplica el zoom
            this.img.aumentar();
            zoom = zoom + 1;
        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p3MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        //Este metodo comrpueba que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //Disminuir el zoom
            this.img.disminuir();
            zoom = zoom - 1;

        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p4MouseClicked

    private void btnClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseClicked
        panelClientes.setVisible(true);
        panelEmpleados.setVisible(false);
        panelSucursales.setVisible(false);
        panelGeneral.setVisible(false);
        tabClientes.setBackground(activo);
        tabEmpleados.setBackground(inactivo);
        tabSucursales.setBackground(inactivo);
        tabGenerales.setBackground(inactivo);

    }//GEN-LAST:event_btnClientesMouseClicked

    private void btnEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpleadosMouseClicked
        panelClientes.setVisible(false);
        panelEmpleados.setVisible(true);
        panelSucursales.setVisible(false);
        panelGeneral.setVisible(false);
        tabClientes.setBackground(inactivo);
        tabEmpleados.setBackground(activo);
        tabSucursales.setBackground(inactivo);
        tabGenerales.setBackground(inactivo);
    }//GEN-LAST:event_btnEmpleadosMouseClicked

    private void btnSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalMouseClicked
        panelClientes.setVisible(false);
        panelEmpleados.setVisible(false);
        panelSucursales.setVisible(true);
        panelGeneral.setVisible(false);
        tabClientes.setBackground(inactivo);
        tabEmpleados.setBackground(inactivo);
        tabSucursales.setBackground(activo);
        tabGenerales.setBackground(inactivo);
    }//GEN-LAST:event_btnSucursalMouseClicked

    private void btnGeneralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGeneralMouseClicked
        panelClientes.setVisible(false);
        panelEmpleados.setVisible(false);
        panelSucursales.setVisible(false);
        panelGeneral.setVisible(true);
        tabClientes.setBackground(inactivo);
        tabEmpleados.setBackground(inactivo);
        tabSucursales.setBackground(inactivo);
        tabGenerales.setBackground(activo);
    }//GEN-LAST:event_btnGeneralMouseClicked

    private void txtRucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRucMouseClicked
//        limpiarCedulaRuc();
    }//GEN-LAST:event_txtRucMouseClicked

    private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased
//        validarRuc();
    }//GEN-LAST:event_txtRucKeyReleased

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
//        int key = evt.getKeyChar();
//        boolean numeros = key >= 48 && key <= 57;
//        if (!numeros) {
//            evt.consume();
//            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten numeros!!");
//            panel.showNotification();
//        }
//        if (txtRuc.getText().trim().length() == 13) {
//            evt.consume();
//            panel = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Solo se permiten 13 caracteres!!");
//            panel.showNotification();
//        }
    }//GEN-LAST:event_txtRucKeyTyped

    private void tDatosClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosClientesMouseClicked
        
    }//GEN-LAST:event_tDatosClientesMouseClicked

    private void tDatosEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosEmpleadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tDatosEmpleadosMouseClicked

    private void tDatosSucusalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosSucusalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tDatosSucusalMouseClicked

    private void tDatosGeneralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosGeneralMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tDatosGeneralMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClientes;
    private javax.swing.JLabel btnEmpleados;
    private javax.swing.JLabel btnGeneral;
    private javax.swing.JLabel btnSucursal;
    private mivisorpdf.CuadroImagen img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblArchivo;
    private javax.swing.JLabel lblNombreArchivo;
    private javax.swing.JLabel p;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelEmpleados;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelSucursales;
    private com.anthony.swing.RadioButton rdtbApellidos;
    private com.anthony.swing.RadioButton rdtbApellidos1;
    private com.anthony.swing.RadioButton rdtbApellidos2;
    private com.anthony.swing.RadioButton rdtbApellidos3;
    private com.anthony.swing.RadioButton rdtbApellidos4;
    private com.anthony.swing.RadioButton rdtbCedula;
    private com.anthony.swing.RadioButton rdtbCedula1;
    private com.anthony.swing.RadioButton rdtbRuc;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel10;
    private com.anthony.swing.RoundPanel roundPanel2;
    private com.anthony.swing.RoundPanel roundPanel3;
    private com.anthony.swing.RoundPanel roundPanel5;
    private com.anthony.swing.RoundPanel roundPanel6;
    private com.anthony.swing.RoundPanel roundPanel7;
    private com.anthony.swing.RoundPanel roundPanel8;
    private com.anthony.swing.RoundPanel roundPanel9;
    private javax.swing.JScrollPane sc;
    private com.anthony.swing.scrollbar.ScrollBarCustom scrollBarCustom1;
    private javax.swing.JScrollPane spClientes;
    private javax.swing.JScrollPane spEmpleados;
    private javax.swing.JScrollPane spGeneral;
    private javax.swing.JScrollPane spSucursal;
    private rojeru_san.complementos.TableMetro tDatosClientes;
    private rojeru_san.complementos.TableMetro tDatosEmpleados;
    private rojeru_san.complementos.TableMetro tDatosGeneral;
    private rojeru_san.complementos.TableMetro tDatosSucusal;
    private com.anthony.swing.RoundPanel tabClientes;
    private com.anthony.swing.RoundPanel tabEmpleados;
    private com.anthony.swing.RoundPanel tabGenerales;
    private com.anthony.swing.RoundPanel tabSucursales;
    private textfield.TextField txtNombres;
    private textfield.TextField txtNombres1;
    private textfield.TextField txtNombres2;
    private textfield.TextField txtRuc;
    // End of variables declaration//GEN-END:variables
}
