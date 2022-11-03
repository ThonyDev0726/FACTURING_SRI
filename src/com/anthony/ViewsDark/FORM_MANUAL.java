package com.anthony.ViewsDark;

import VO.ArchivosVO;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.USUARIO;
import com.anthony.VisorPdf.JnaFileChooser;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mivisorpdf.MiVisorPDF;

public class FORM_MANUAL extends javax.swing.JPanel {

    RoundBorder border = new RoundBorder(0);
    //Contador de paginas
    private int numImg;

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

    public FORM_MANUAL() {
        initComponents();
        scroll();
    }

    public FORM_MANUAL(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        scroll();
    }

    private void scroll() {
        spItems.setVerticalScrollBar(new ScrollBarCustom());
        sc.setVerticalScrollBar(new ScrollBarCustom());
        sc.setHorizontalScrollBar(scrollBarCustom1);
        sc.getViewport().setOpaque(false);
        lblNombreArchivo.setText("Sin documento");
    }

    public void bordes() {
        sc.setVerticalScrollBar(new ScrollBarCustom());
        sc.setBorder(border);
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
        spItems = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        roundPanel11 = new com.anthony.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        roundPanel12 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo2 = new javax.swing.JLabel();
        roundPanel13 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo3 = new javax.swing.JLabel();
        roundPanel59 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo34 = new javax.swing.JLabel();
        roundPanel60 = new com.anthony.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        roundPanel61 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo35 = new javax.swing.JLabel();
        roundPanel62 = new com.anthony.swing.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        roundPanel63 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo36 = new javax.swing.JLabel();
        roundPanel64 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo37 = new javax.swing.JLabel();
        roundPanel65 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo38 = new javax.swing.JLabel();
        roundPanel66 = new com.anthony.swing.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        roundPanel67 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo39 = new javax.swing.JLabel();
        roundPanel68 = new com.anthony.swing.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        roundPanel69 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo40 = new javax.swing.JLabel();
        roundPanel70 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo41 = new javax.swing.JLabel();
        roundPanel71 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo42 = new javax.swing.JLabel();
        roundPanel72 = new com.anthony.swing.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        roundPanel73 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo43 = new javax.swing.JLabel();
        roundPanel74 = new com.anthony.swing.RoundPanel();
        jLabel8 = new javax.swing.JLabel();
        roundPanel75 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo44 = new javax.swing.JLabel();
        roundPanel76 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo45 = new javax.swing.JLabel();
        roundPanel77 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo46 = new javax.swing.JLabel();
        roundPanel78 = new com.anthony.swing.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        roundPanel79 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo47 = new javax.swing.JLabel();
        roundPanel80 = new com.anthony.swing.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        roundPanel81 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo48 = new javax.swing.JLabel();
        roundPanel82 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo49 = new javax.swing.JLabel();
        roundPanel83 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo50 = new javax.swing.JLabel();
        roundPanel84 = new com.anthony.swing.RoundPanel();
        jLabel11 = new javax.swing.JLabel();
        roundPanel85 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo51 = new javax.swing.JLabel();
        roundPanel86 = new com.anthony.swing.RoundPanel();
        jLabel12 = new javax.swing.JLabel();
        roundPanel87 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo52 = new javax.swing.JLabel();
        roundPanel88 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo53 = new javax.swing.JLabel();
        roundPanel89 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo54 = new javax.swing.JLabel();
        roundPanel90 = new com.anthony.swing.RoundPanel();
        jLabel13 = new javax.swing.JLabel();
        roundPanel91 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo55 = new javax.swing.JLabel();
        roundPanel92 = new com.anthony.swing.RoundPanel();
        jLabel14 = new javax.swing.JLabel();
        roundPanel93 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo56 = new javax.swing.JLabel();
        roundPanel94 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo57 = new javax.swing.JLabel();
        roundPanel95 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo58 = new javax.swing.JLabel();
        roundPanel96 = new com.anthony.swing.RoundPanel();
        jLabel15 = new javax.swing.JLabel();
        roundPanel97 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo59 = new javax.swing.JLabel();
        roundPanel98 = new com.anthony.swing.RoundPanel();
        jLabel16 = new javax.swing.JLabel();
        roundPanel99 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo60 = new javax.swing.JLabel();
        roundPanel100 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo61 = new javax.swing.JLabel();
        roundPanel101 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo62 = new javax.swing.JLabel();
        roundPanel102 = new com.anthony.swing.RoundPanel();
        jLabel17 = new javax.swing.JLabel();
        roundPanel103 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo63 = new javax.swing.JLabel();
        roundPanel104 = new com.anthony.swing.RoundPanel();
        jLabel18 = new javax.swing.JLabel();
        roundPanel105 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo64 = new javax.swing.JLabel();
        roundPanel106 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo65 = new javax.swing.JLabel();
        roundPanel107 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo66 = new javax.swing.JLabel();
        roundPanel108 = new com.anthony.swing.RoundPanel();
        jLabel19 = new javax.swing.JLabel();
        roundPanel109 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo67 = new javax.swing.JLabel();
        roundPanel110 = new com.anthony.swing.RoundPanel();
        jLabel20 = new javax.swing.JLabel();
        roundPanel111 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo68 = new javax.swing.JLabel();
        roundPanel112 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo69 = new javax.swing.JLabel();
        roundPanel113 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo70 = new javax.swing.JLabel();
        roundPanel114 = new com.anthony.swing.RoundPanel();
        jLabel21 = new javax.swing.JLabel();
        roundPanel115 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo71 = new javax.swing.JLabel();
        roundPanel14 = new com.anthony.swing.RoundPanel();
        jLabel22 = new javax.swing.JLabel();
        roundPanel15 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo4 = new javax.swing.JLabel();
        roundPanel16 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("MANUAL DE USUARIO");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        roundPanel2.setBackground(new java.awt.Color(32, 32, 32));

        sc.setBackground(new java.awt.Color(32, 32, 32));
        sc.setBorder(null);
        sc.setHorizontalScrollBar(scrollBarCustom1);

        img.setBackground(new java.awt.Color(32, 32, 32));
        img.setPreferredSize(new java.awt.Dimension(210, 297));

        javax.swing.GroupLayout imgLayout = new javax.swing.GroupLayout(img);
        img.setLayout(imgLayout);
        imgLayout.setHorizontalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        imgLayout.setVerticalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
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
                    .addComponent(sc, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
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
                .addComponent(lblNombreArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel10Layout.setVerticalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        spItems.setBackground(new java.awt.Color(22, 23, 23));
        spItems.setBorder(null);
        spItems.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));

        roundPanel11.setBackground(new java.awt.Color(32, 32, 32));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("Alertas y avisos del sistema");

        javax.swing.GroupLayout roundPanel11Layout = new javax.swing.GroupLayout(roundPanel11);
        roundPanel11.setLayout(roundPanel11Layout);
        roundPanel11Layout.setHorizontalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel11Layout.setVerticalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel12.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo2.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel12Layout = new javax.swing.GroupLayout(roundPanel12);
        roundPanel12.setLayout(roundPanel12Layout);
        roundPanel12Layout.setHorizontalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel12Layout.setVerticalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel13.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo3.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel13Layout = new javax.swing.GroupLayout(roundPanel13);
        roundPanel13.setLayout(roundPanel13Layout);
        roundPanel13Layout.setHorizontalGroup(
            roundPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel13Layout.setVerticalGroup(
            roundPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel59.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo34.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel59Layout = new javax.swing.GroupLayout(roundPanel59);
        roundPanel59.setLayout(roundPanel59Layout);
        roundPanel59Layout.setHorizontalGroup(
            roundPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo34, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel59Layout.setVerticalGroup(
            roundPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo34, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel60.setBackground(new java.awt.Color(32, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setText("Inicio de sesion");

        javax.swing.GroupLayout roundPanel60Layout = new javax.swing.GroupLayout(roundPanel60);
        roundPanel60.setLayout(roundPanel60Layout);
        roundPanel60Layout.setHorizontalGroup(
            roundPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel60Layout.setVerticalGroup(
            roundPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel61.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo35.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel61Layout = new javax.swing.GroupLayout(roundPanel61);
        roundPanel61.setLayout(roundPanel61Layout);
        roundPanel61Layout.setHorizontalGroup(
            roundPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo35, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel61Layout.setVerticalGroup(
            roundPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel62.setBackground(new java.awt.Color(32, 32, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 153, 255));
        jLabel4.setText("Gestion de clientes");

        javax.swing.GroupLayout roundPanel62Layout = new javax.swing.GroupLayout(roundPanel62);
        roundPanel62.setLayout(roundPanel62Layout);
        roundPanel62Layout.setHorizontalGroup(
            roundPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel62Layout.setVerticalGroup(
            roundPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel63.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo36.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo36MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel63Layout = new javax.swing.GroupLayout(roundPanel63);
        roundPanel63.setLayout(roundPanel63Layout);
        roundPanel63Layout.setHorizontalGroup(
            roundPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo36, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel63Layout.setVerticalGroup(
            roundPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo36, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel64.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo37.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo37MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel64Layout = new javax.swing.GroupLayout(roundPanel64);
        roundPanel64.setLayout(roundPanel64Layout);
        roundPanel64Layout.setHorizontalGroup(
            roundPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo37, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel64Layout.setVerticalGroup(
            roundPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel65.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo38.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo38MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel65Layout = new javax.swing.GroupLayout(roundPanel65);
        roundPanel65.setLayout(roundPanel65Layout);
        roundPanel65Layout.setHorizontalGroup(
            roundPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo38, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel65Layout.setVerticalGroup(
            roundPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo38, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel66.setBackground(new java.awt.Color(32, 32, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 153, 255));
        jLabel5.setText("Gestion de empleados");

        javax.swing.GroupLayout roundPanel66Layout = new javax.swing.GroupLayout(roundPanel66);
        roundPanel66.setLayout(roundPanel66Layout);
        roundPanel66Layout.setHorizontalGroup(
            roundPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel66Layout.setVerticalGroup(
            roundPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel67.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo39.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo39MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel67Layout = new javax.swing.GroupLayout(roundPanel67);
        roundPanel67.setLayout(roundPanel67Layout);
        roundPanel67Layout.setHorizontalGroup(
            roundPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo39, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel67Layout.setVerticalGroup(
            roundPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel68.setBackground(new java.awt.Color(32, 32, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 153, 255));
        jLabel6.setText("Gestion de proveedores");

        javax.swing.GroupLayout roundPanel68Layout = new javax.swing.GroupLayout(roundPanel68);
        roundPanel68.setLayout(roundPanel68Layout);
        roundPanel68Layout.setHorizontalGroup(
            roundPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel68Layout.setVerticalGroup(
            roundPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel69.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo40.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo40MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel69Layout = new javax.swing.GroupLayout(roundPanel69);
        roundPanel69.setLayout(roundPanel69Layout);
        roundPanel69Layout.setHorizontalGroup(
            roundPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo40, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel69Layout.setVerticalGroup(
            roundPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo40, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel70.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo41.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo41MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel70Layout = new javax.swing.GroupLayout(roundPanel70);
        roundPanel70.setLayout(roundPanel70Layout);
        roundPanel70Layout.setHorizontalGroup(
            roundPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo41, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel70Layout.setVerticalGroup(
            roundPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel71.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo42.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo42MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel71Layout = new javax.swing.GroupLayout(roundPanel71);
        roundPanel71.setLayout(roundPanel71Layout);
        roundPanel71Layout.setHorizontalGroup(
            roundPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo42, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel71Layout.setVerticalGroup(
            roundPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo42, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel72.setBackground(new java.awt.Color(32, 32, 32));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("Gestion de productos");

        javax.swing.GroupLayout roundPanel72Layout = new javax.swing.GroupLayout(roundPanel72);
        roundPanel72.setLayout(roundPanel72Layout);
        roundPanel72Layout.setHorizontalGroup(
            roundPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel72Layout.setVerticalGroup(
            roundPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel73.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo43.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo43MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel73Layout = new javax.swing.GroupLayout(roundPanel73);
        roundPanel73.setLayout(roundPanel73Layout);
        roundPanel73Layout.setHorizontalGroup(
            roundPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo43, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel73Layout.setVerticalGroup(
            roundPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel74.setBackground(new java.awt.Color(32, 32, 32));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 153, 255));
        jLabel8.setText("Generar factura");

        javax.swing.GroupLayout roundPanel74Layout = new javax.swing.GroupLayout(roundPanel74);
        roundPanel74.setLayout(roundPanel74Layout);
        roundPanel74Layout.setHorizontalGroup(
            roundPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel74Layout.setVerticalGroup(
            roundPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel75.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo44.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo44MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel75Layout = new javax.swing.GroupLayout(roundPanel75);
        roundPanel75.setLayout(roundPanel75Layout);
        roundPanel75Layout.setHorizontalGroup(
            roundPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo44, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel75Layout.setVerticalGroup(
            roundPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo44, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel76.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo45.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo45MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel76Layout = new javax.swing.GroupLayout(roundPanel76);
        roundPanel76.setLayout(roundPanel76Layout);
        roundPanel76Layout.setHorizontalGroup(
            roundPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo45, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel76Layout.setVerticalGroup(
            roundPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel77.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo46.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo46MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel77Layout = new javax.swing.GroupLayout(roundPanel77);
        roundPanel77.setLayout(roundPanel77Layout);
        roundPanel77Layout.setHorizontalGroup(
            roundPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo46, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel77Layout.setVerticalGroup(
            roundPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo46, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel78.setBackground(new java.awt.Color(32, 32, 32));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 153, 255));
        jLabel9.setText("Generar nota de credito");

        javax.swing.GroupLayout roundPanel78Layout = new javax.swing.GroupLayout(roundPanel78);
        roundPanel78.setLayout(roundPanel78Layout);
        roundPanel78Layout.setHorizontalGroup(
            roundPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel78Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel78Layout.setVerticalGroup(
            roundPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel79.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo47.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo47MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel79Layout = new javax.swing.GroupLayout(roundPanel79);
        roundPanel79.setLayout(roundPanel79Layout);
        roundPanel79Layout.setHorizontalGroup(
            roundPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo47, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel79Layout.setVerticalGroup(
            roundPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel80.setBackground(new java.awt.Color(32, 32, 32));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Reportes generales");

        javax.swing.GroupLayout roundPanel80Layout = new javax.swing.GroupLayout(roundPanel80);
        roundPanel80.setLayout(roundPanel80Layout);
        roundPanel80Layout.setHorizontalGroup(
            roundPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel80Layout.setVerticalGroup(
            roundPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel81.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo48.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo48MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel81Layout = new javax.swing.GroupLayout(roundPanel81);
        roundPanel81.setLayout(roundPanel81Layout);
        roundPanel81Layout.setHorizontalGroup(
            roundPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo48, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel81Layout.setVerticalGroup(
            roundPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo48, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel82.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo49.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo49MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel82Layout = new javax.swing.GroupLayout(roundPanel82);
        roundPanel82.setLayout(roundPanel82Layout);
        roundPanel82Layout.setHorizontalGroup(
            roundPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo49, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel82Layout.setVerticalGroup(
            roundPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel83.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo50.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo50MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel83Layout = new javax.swing.GroupLayout(roundPanel83);
        roundPanel83.setLayout(roundPanel83Layout);
        roundPanel83Layout.setHorizontalGroup(
            roundPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo50, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel83Layout.setVerticalGroup(
            roundPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo50, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel84.setBackground(new java.awt.Color(32, 32, 32));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 153, 255));
        jLabel11.setText("Reportes estadisticos");

        javax.swing.GroupLayout roundPanel84Layout = new javax.swing.GroupLayout(roundPanel84);
        roundPanel84.setLayout(roundPanel84Layout);
        roundPanel84Layout.setHorizontalGroup(
            roundPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel84Layout.setVerticalGroup(
            roundPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel85.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo51.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo51MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel85Layout = new javax.swing.GroupLayout(roundPanel85);
        roundPanel85.setLayout(roundPanel85Layout);
        roundPanel85Layout.setHorizontalGroup(
            roundPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo51, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel85Layout.setVerticalGroup(
            roundPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel86.setBackground(new java.awt.Color(32, 32, 32));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 153, 255));
        jLabel12.setText("Revision de facturas");

        javax.swing.GroupLayout roundPanel86Layout = new javax.swing.GroupLayout(roundPanel86);
        roundPanel86.setLayout(roundPanel86Layout);
        roundPanel86Layout.setHorizontalGroup(
            roundPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel86Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel86Layout.setVerticalGroup(
            roundPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel87.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo52.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo52MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel87Layout = new javax.swing.GroupLayout(roundPanel87);
        roundPanel87.setLayout(roundPanel87Layout);
        roundPanel87Layout.setHorizontalGroup(
            roundPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo52, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel87Layout.setVerticalGroup(
            roundPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo52, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel88.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo53.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo53MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel88Layout = new javax.swing.GroupLayout(roundPanel88);
        roundPanel88.setLayout(roundPanel88Layout);
        roundPanel88Layout.setHorizontalGroup(
            roundPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo53, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel88Layout.setVerticalGroup(
            roundPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel89.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo54.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo54MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel89Layout = new javax.swing.GroupLayout(roundPanel89);
        roundPanel89.setLayout(roundPanel89Layout);
        roundPanel89Layout.setHorizontalGroup(
            roundPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo54, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel89Layout.setVerticalGroup(
            roundPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo54, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel90.setBackground(new java.awt.Color(32, 32, 32));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 153, 255));
        jLabel13.setText("Revision de notas de credito");

        javax.swing.GroupLayout roundPanel90Layout = new javax.swing.GroupLayout(roundPanel90);
        roundPanel90.setLayout(roundPanel90Layout);
        roundPanel90Layout.setHorizontalGroup(
            roundPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel90Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel90Layout.setVerticalGroup(
            roundPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel91.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo55.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo55MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel91Layout = new javax.swing.GroupLayout(roundPanel91);
        roundPanel91.setLayout(roundPanel91Layout);
        roundPanel91Layout.setHorizontalGroup(
            roundPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo55, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel91Layout.setVerticalGroup(
            roundPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel92.setBackground(new java.awt.Color(32, 32, 32));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 153, 255));
        jLabel14.setText("Revision de notas de debito");

        javax.swing.GroupLayout roundPanel92Layout = new javax.swing.GroupLayout(roundPanel92);
        roundPanel92.setLayout(roundPanel92Layout);
        roundPanel92Layout.setHorizontalGroup(
            roundPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel92Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel92Layout.setVerticalGroup(
            roundPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel93.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo56.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo56MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel93Layout = new javax.swing.GroupLayout(roundPanel93);
        roundPanel93.setLayout(roundPanel93Layout);
        roundPanel93Layout.setHorizontalGroup(
            roundPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo56, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel93Layout.setVerticalGroup(
            roundPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo56, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel94.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo57.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo57MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel94Layout = new javax.swing.GroupLayout(roundPanel94);
        roundPanel94.setLayout(roundPanel94Layout);
        roundPanel94Layout.setHorizontalGroup(
            roundPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo57, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel94Layout.setVerticalGroup(
            roundPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel95.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo58.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo58MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel95Layout = new javax.swing.GroupLayout(roundPanel95);
        roundPanel95.setLayout(roundPanel95Layout);
        roundPanel95Layout.setHorizontalGroup(
            roundPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo58, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel95Layout.setVerticalGroup(
            roundPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo58, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel96.setBackground(new java.awt.Color(32, 32, 32));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 153, 255));
        jLabel15.setText("Gestion de usuarios");

        javax.swing.GroupLayout roundPanel96Layout = new javax.swing.GroupLayout(roundPanel96);
        roundPanel96.setLayout(roundPanel96Layout);
        roundPanel96Layout.setHorizontalGroup(
            roundPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel96Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel96Layout.setVerticalGroup(
            roundPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel97.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo59.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo59MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel97Layout = new javax.swing.GroupLayout(roundPanel97);
        roundPanel97.setLayout(roundPanel97Layout);
        roundPanel97Layout.setHorizontalGroup(
            roundPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo59, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel97Layout.setVerticalGroup(
            roundPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel98.setBackground(new java.awt.Color(32, 32, 32));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 153, 255));
        jLabel16.setText("Permisos de usuarios");

        javax.swing.GroupLayout roundPanel98Layout = new javax.swing.GroupLayout(roundPanel98);
        roundPanel98.setLayout(roundPanel98Layout);
        roundPanel98Layout.setHorizontalGroup(
            roundPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel98Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel98Layout.setVerticalGroup(
            roundPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel99.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo60.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo60MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel99Layout = new javax.swing.GroupLayout(roundPanel99);
        roundPanel99.setLayout(roundPanel99Layout);
        roundPanel99Layout.setHorizontalGroup(
            roundPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo60, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel99Layout.setVerticalGroup(
            roundPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo60, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel100.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo61.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo61MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel100Layout = new javax.swing.GroupLayout(roundPanel100);
        roundPanel100.setLayout(roundPanel100Layout);
        roundPanel100Layout.setHorizontalGroup(
            roundPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo61, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel100Layout.setVerticalGroup(
            roundPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel101.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo62.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo62MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel101Layout = new javax.swing.GroupLayout(roundPanel101);
        roundPanel101.setLayout(roundPanel101Layout);
        roundPanel101Layout.setHorizontalGroup(
            roundPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo62, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel101Layout.setVerticalGroup(
            roundPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo62, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel102.setBackground(new java.awt.Color(32, 32, 32));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 153, 255));
        jLabel17.setText("Cambiar clave");

        javax.swing.GroupLayout roundPanel102Layout = new javax.swing.GroupLayout(roundPanel102);
        roundPanel102.setLayout(roundPanel102Layout);
        roundPanel102Layout.setHorizontalGroup(
            roundPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel102Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel102Layout.setVerticalGroup(
            roundPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel103.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo63.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo63MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel103Layout = new javax.swing.GroupLayout(roundPanel103);
        roundPanel103.setLayout(roundPanel103Layout);
        roundPanel103Layout.setHorizontalGroup(
            roundPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo63, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel103Layout.setVerticalGroup(
            roundPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel104.setBackground(new java.awt.Color(32, 32, 32));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 153, 255));
        jLabel18.setText("Manual de usuario");

        javax.swing.GroupLayout roundPanel104Layout = new javax.swing.GroupLayout(roundPanel104);
        roundPanel104.setLayout(roundPanel104Layout);
        roundPanel104Layout.setHorizontalGroup(
            roundPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel104Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel104Layout.setVerticalGroup(
            roundPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel105.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo64.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo64MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel105Layout = new javax.swing.GroupLayout(roundPanel105);
        roundPanel105.setLayout(roundPanel105Layout);
        roundPanel105Layout.setHorizontalGroup(
            roundPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo64, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel105Layout.setVerticalGroup(
            roundPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo64, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel106.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo65.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo65MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel106Layout = new javax.swing.GroupLayout(roundPanel106);
        roundPanel106.setLayout(roundPanel106Layout);
        roundPanel106Layout.setHorizontalGroup(
            roundPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo65, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel106Layout.setVerticalGroup(
            roundPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel107.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo66.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo66MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel107Layout = new javax.swing.GroupLayout(roundPanel107);
        roundPanel107.setLayout(roundPanel107Layout);
        roundPanel107Layout.setHorizontalGroup(
            roundPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo66, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel107Layout.setVerticalGroup(
            roundPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo66, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel108.setBackground(new java.awt.Color(32, 32, 32));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 153, 255));
        jLabel19.setText("Soporte tecnico");

        javax.swing.GroupLayout roundPanel108Layout = new javax.swing.GroupLayout(roundPanel108);
        roundPanel108.setLayout(roundPanel108Layout);
        roundPanel108Layout.setHorizontalGroup(
            roundPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel108Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel108Layout.setVerticalGroup(
            roundPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel109.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo67.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo67MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel109Layout = new javax.swing.GroupLayout(roundPanel109);
        roundPanel109.setLayout(roundPanel109Layout);
        roundPanel109Layout.setHorizontalGroup(
            roundPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo67, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel109Layout.setVerticalGroup(
            roundPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel110.setBackground(new java.awt.Color(32, 32, 32));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 153, 255));
        jLabel20.setText("Ajustes");

        javax.swing.GroupLayout roundPanel110Layout = new javax.swing.GroupLayout(roundPanel110);
        roundPanel110.setLayout(roundPanel110Layout);
        roundPanel110Layout.setHorizontalGroup(
            roundPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel110Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel110Layout.setVerticalGroup(
            roundPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel111.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo68.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo68MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel111Layout = new javax.swing.GroupLayout(roundPanel111);
        roundPanel111.setLayout(roundPanel111Layout);
        roundPanel111Layout.setHorizontalGroup(
            roundPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo68, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel111Layout.setVerticalGroup(
            roundPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo68, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel112.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo69.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo69MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel112Layout = new javax.swing.GroupLayout(roundPanel112);
        roundPanel112.setLayout(roundPanel112Layout);
        roundPanel112Layout.setHorizontalGroup(
            roundPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo69, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel112Layout.setVerticalGroup(
            roundPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel113.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo70.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo70MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel113Layout = new javax.swing.GroupLayout(roundPanel113);
        roundPanel113.setLayout(roundPanel113Layout);
        roundPanel113Layout.setHorizontalGroup(
            roundPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo70, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel113Layout.setVerticalGroup(
            roundPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo70, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel114.setBackground(new java.awt.Color(32, 32, 32));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 153, 255));
        jLabel21.setText("Ajustes de la empresa");

        javax.swing.GroupLayout roundPanel114Layout = new javax.swing.GroupLayout(roundPanel114);
        roundPanel114.setLayout(roundPanel114Layout);
        roundPanel114Layout.setHorizontalGroup(
            roundPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel114Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel114Layout.setVerticalGroup(
            roundPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel115.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo71.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo71MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel115Layout = new javax.swing.GroupLayout(roundPanel115);
        roundPanel115.setLayout(roundPanel115Layout);
        roundPanel115Layout.setHorizontalGroup(
            roundPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo71, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel115Layout.setVerticalGroup(
            roundPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel14.setBackground(new java.awt.Color(32, 32, 32));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 153, 255));
        jLabel22.setText("Splash Screen");

        javax.swing.GroupLayout roundPanel14Layout = new javax.swing.GroupLayout(roundPanel14);
        roundPanel14.setLayout(roundPanel14Layout);
        roundPanel14Layout.setHorizontalGroup(
            roundPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel14Layout.setVerticalGroup(
            roundPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel15.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo4.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoOjo.png"))); // NOI18N
        lblNombreArchivo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel15Layout = new javax.swing.GroupLayout(roundPanel15);
        roundPanel15.setLayout(roundPanel15Layout);
        roundPanel15Layout.setHorizontalGroup(
            roundPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel15Layout.setVerticalGroup(
            roundPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel16.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo5.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoImpresora.png"))); // NOI18N
        lblNombreArchivo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivo5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel16Layout = new javax.swing.GroupLayout(roundPanel16);
        roundPanel16.setLayout(roundPanel16Layout);
        roundPanel16Layout.setHorizontalGroup(
            roundPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo5, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel16Layout.setVerticalGroup(
            roundPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {roundPanel11, roundPanel12, roundPanel13});

        spItems.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(spItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roundPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(spItems, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE))
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

    private void lblNombreArchivo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo2MouseClicked

    private void lblNombreArchivo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo3MouseClicked

    private void lblNombreArchivo34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo34MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo34MouseClicked

    private void lblNombreArchivo35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo35MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo35MouseClicked

    private void lblNombreArchivo36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo36MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo36MouseClicked

    private void lblNombreArchivo37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo37MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo37MouseClicked

    private void lblNombreArchivo38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo38MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo38MouseClicked

    private void lblNombreArchivo39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo39MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo39MouseClicked

    private void lblNombreArchivo40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo40MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo40MouseClicked

    private void lblNombreArchivo41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo41MouseClicked

    private void lblNombreArchivo42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo42MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo42MouseClicked

    private void lblNombreArchivo43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo43MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo43MouseClicked

    private void lblNombreArchivo44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo44MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo44MouseClicked

    private void lblNombreArchivo45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo45MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo45MouseClicked

    private void lblNombreArchivo46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo46MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo46MouseClicked

    private void lblNombreArchivo47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo47MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo47MouseClicked

    private void lblNombreArchivo48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo48MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo48MouseClicked

    private void lblNombreArchivo49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo49MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo49MouseClicked

    private void lblNombreArchivo50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo50MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo50MouseClicked

    private void lblNombreArchivo51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo51MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo51MouseClicked

    private void lblNombreArchivo52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo52MouseClicked

    private void lblNombreArchivo53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo53MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo53MouseClicked

    private void lblNombreArchivo54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo54MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo54MouseClicked

    private void lblNombreArchivo55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo55MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo55MouseClicked

    private void lblNombreArchivo56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo56MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo56MouseClicked

    private void lblNombreArchivo57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo57MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo57MouseClicked

    private void lblNombreArchivo58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo58MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo58MouseClicked

    private void lblNombreArchivo59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo59MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo59MouseClicked

    private void lblNombreArchivo60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo60MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo60MouseClicked

    private void lblNombreArchivo61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo61MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo61MouseClicked

    private void lblNombreArchivo62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo62MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo62MouseClicked

    private void lblNombreArchivo63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo63MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo63MouseClicked

    private void lblNombreArchivo64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo64MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo64MouseClicked

    private void lblNombreArchivo65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo65MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo65MouseClicked

    private void lblNombreArchivo66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo66MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo66MouseClicked

    private void lblNombreArchivo67MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo67MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo67MouseClicked

    private void lblNombreArchivo68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo68MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo68MouseClicked

    private void lblNombreArchivo69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo69MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo69MouseClicked

    private void lblNombreArchivo70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo70MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo70MouseClicked

    private void lblNombreArchivo71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo71MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo71MouseClicked

    private void lblNombreArchivo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo4MouseClicked

    private void lblNombreArchivo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivo5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivo5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mivisorpdf.CuadroImagen img;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblArchivo;
    private javax.swing.JLabel lblNombreArchivo;
    private javax.swing.JLabel lblNombreArchivo2;
    private javax.swing.JLabel lblNombreArchivo3;
    private javax.swing.JLabel lblNombreArchivo34;
    private javax.swing.JLabel lblNombreArchivo35;
    private javax.swing.JLabel lblNombreArchivo36;
    private javax.swing.JLabel lblNombreArchivo37;
    private javax.swing.JLabel lblNombreArchivo38;
    private javax.swing.JLabel lblNombreArchivo39;
    private javax.swing.JLabel lblNombreArchivo4;
    private javax.swing.JLabel lblNombreArchivo40;
    private javax.swing.JLabel lblNombreArchivo41;
    private javax.swing.JLabel lblNombreArchivo42;
    private javax.swing.JLabel lblNombreArchivo43;
    private javax.swing.JLabel lblNombreArchivo44;
    private javax.swing.JLabel lblNombreArchivo45;
    private javax.swing.JLabel lblNombreArchivo46;
    private javax.swing.JLabel lblNombreArchivo47;
    private javax.swing.JLabel lblNombreArchivo48;
    private javax.swing.JLabel lblNombreArchivo49;
    private javax.swing.JLabel lblNombreArchivo5;
    private javax.swing.JLabel lblNombreArchivo50;
    private javax.swing.JLabel lblNombreArchivo51;
    private javax.swing.JLabel lblNombreArchivo52;
    private javax.swing.JLabel lblNombreArchivo53;
    private javax.swing.JLabel lblNombreArchivo54;
    private javax.swing.JLabel lblNombreArchivo55;
    private javax.swing.JLabel lblNombreArchivo56;
    private javax.swing.JLabel lblNombreArchivo57;
    private javax.swing.JLabel lblNombreArchivo58;
    private javax.swing.JLabel lblNombreArchivo59;
    private javax.swing.JLabel lblNombreArchivo60;
    private javax.swing.JLabel lblNombreArchivo61;
    private javax.swing.JLabel lblNombreArchivo62;
    private javax.swing.JLabel lblNombreArchivo63;
    private javax.swing.JLabel lblNombreArchivo64;
    private javax.swing.JLabel lblNombreArchivo65;
    private javax.swing.JLabel lblNombreArchivo66;
    private javax.swing.JLabel lblNombreArchivo67;
    private javax.swing.JLabel lblNombreArchivo68;
    private javax.swing.JLabel lblNombreArchivo69;
    private javax.swing.JLabel lblNombreArchivo70;
    private javax.swing.JLabel lblNombreArchivo71;
    private javax.swing.JLabel p;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private com.anthony.swing.RoundPanel roundPanel10;
    private com.anthony.swing.RoundPanel roundPanel100;
    private com.anthony.swing.RoundPanel roundPanel101;
    private com.anthony.swing.RoundPanel roundPanel102;
    private com.anthony.swing.RoundPanel roundPanel103;
    private com.anthony.swing.RoundPanel roundPanel104;
    private com.anthony.swing.RoundPanel roundPanel105;
    private com.anthony.swing.RoundPanel roundPanel106;
    private com.anthony.swing.RoundPanel roundPanel107;
    private com.anthony.swing.RoundPanel roundPanel108;
    private com.anthony.swing.RoundPanel roundPanel109;
    private com.anthony.swing.RoundPanel roundPanel11;
    private com.anthony.swing.RoundPanel roundPanel110;
    private com.anthony.swing.RoundPanel roundPanel111;
    private com.anthony.swing.RoundPanel roundPanel112;
    private com.anthony.swing.RoundPanel roundPanel113;
    private com.anthony.swing.RoundPanel roundPanel114;
    private com.anthony.swing.RoundPanel roundPanel115;
    private com.anthony.swing.RoundPanel roundPanel12;
    private com.anthony.swing.RoundPanel roundPanel13;
    private com.anthony.swing.RoundPanel roundPanel14;
    private com.anthony.swing.RoundPanel roundPanel15;
    private com.anthony.swing.RoundPanel roundPanel16;
    private com.anthony.swing.RoundPanel roundPanel2;
    private com.anthony.swing.RoundPanel roundPanel3;
    private com.anthony.swing.RoundPanel roundPanel5;
    private com.anthony.swing.RoundPanel roundPanel59;
    private com.anthony.swing.RoundPanel roundPanel6;
    private com.anthony.swing.RoundPanel roundPanel60;
    private com.anthony.swing.RoundPanel roundPanel61;
    private com.anthony.swing.RoundPanel roundPanel62;
    private com.anthony.swing.RoundPanel roundPanel63;
    private com.anthony.swing.RoundPanel roundPanel64;
    private com.anthony.swing.RoundPanel roundPanel65;
    private com.anthony.swing.RoundPanel roundPanel66;
    private com.anthony.swing.RoundPanel roundPanel67;
    private com.anthony.swing.RoundPanel roundPanel68;
    private com.anthony.swing.RoundPanel roundPanel69;
    private com.anthony.swing.RoundPanel roundPanel7;
    private com.anthony.swing.RoundPanel roundPanel70;
    private com.anthony.swing.RoundPanel roundPanel71;
    private com.anthony.swing.RoundPanel roundPanel72;
    private com.anthony.swing.RoundPanel roundPanel73;
    private com.anthony.swing.RoundPanel roundPanel74;
    private com.anthony.swing.RoundPanel roundPanel75;
    private com.anthony.swing.RoundPanel roundPanel76;
    private com.anthony.swing.RoundPanel roundPanel77;
    private com.anthony.swing.RoundPanel roundPanel78;
    private com.anthony.swing.RoundPanel roundPanel79;
    private com.anthony.swing.RoundPanel roundPanel8;
    private com.anthony.swing.RoundPanel roundPanel80;
    private com.anthony.swing.RoundPanel roundPanel81;
    private com.anthony.swing.RoundPanel roundPanel82;
    private com.anthony.swing.RoundPanel roundPanel83;
    private com.anthony.swing.RoundPanel roundPanel84;
    private com.anthony.swing.RoundPanel roundPanel85;
    private com.anthony.swing.RoundPanel roundPanel86;
    private com.anthony.swing.RoundPanel roundPanel87;
    private com.anthony.swing.RoundPanel roundPanel88;
    private com.anthony.swing.RoundPanel roundPanel89;
    private com.anthony.swing.RoundPanel roundPanel9;
    private com.anthony.swing.RoundPanel roundPanel90;
    private com.anthony.swing.RoundPanel roundPanel91;
    private com.anthony.swing.RoundPanel roundPanel92;
    private com.anthony.swing.RoundPanel roundPanel93;
    private com.anthony.swing.RoundPanel roundPanel94;
    private com.anthony.swing.RoundPanel roundPanel95;
    private com.anthony.swing.RoundPanel roundPanel96;
    private com.anthony.swing.RoundPanel roundPanel97;
    private com.anthony.swing.RoundPanel roundPanel98;
    private com.anthony.swing.RoundPanel roundPanel99;
    private javax.swing.JScrollPane sc;
    private com.anthony.swing.scrollbar.ScrollBarCustom scrollBarCustom1;
    private javax.swing.JScrollPane spItems;
    // End of variables declaration//GEN-END:variables
}
