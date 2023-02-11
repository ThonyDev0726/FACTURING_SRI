package com.anthony.ViewsDark;

import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.AJUSTES;
import com.anthony.Models.EMPRESA;
import com.anthony.Models.USUARIO;
import com.anthony.ModelsDAO.AJUSTES_DAO;
import com.anthony.ModelsDAO.EMPRESA_DAO;
import com.anthony.VisorPdf.JnaFileChooser;
import com.anthony.componentsDark.MessageDialogDark;
import com.anthony.login.Login;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.toast.Toast;
import java.awt.Cursor;
import java.io.File;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author The Thøny
 *
 */
public class FORM_CONFIGURACION extends javax.swing.JPanel {

    MainAdministradorDark admin;
    Toast toast;
    USUARIO usu;
    AJUSTES ajustes = new AJUSTES();
    AJUSTES_DAO ajDao = new AJUSTES_DAO();

    //DATOS EMPRESA
    public Integer ID_AJUSTE;
    public String AJ_EMAIL;
    public String AJ_CLAVE;
    public String AJ_RUTA_FIRMA;
    public String AJ_CLAVE_ACCESO;
    public String AJ_LOGO_EMPRESA;

    public FORM_CONFIGURACION() {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        lblImg.start();
        init();
    }

    public FORM_CONFIGURACION(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        this.admin = admin;
        this.usu = usu;
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        lblImg.start();
        init();
    }

    private void init() {
        EMPRESA empDao = new EMPRESA_DAO().list();
        ajustes = ajDao.listAjustes();
        if (ajustes.getAJ_LOGO_EMPRESA().equals("/com/anthony/img/imgHomeInicio.png")) {
            lblImg.setImage(new javax.swing.ImageIcon(getClass().getResource(usu.getUSU_FOTO())));
        } else {
            lblImg.setImage(new javax.swing.ImageIcon(ajustes.getAJ_LOGO_EMPRESA()));
        }
        lblImg.start();
        txtFirma.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtEmail.setText(ajustes.getAJ_EMAIL());
        lblEmail.setText(ajustes.getAJ_EMAIL());
        txtClave.setText(ajustes.getAJ_CLAVE());
        txtFirma.setText(ajustes.getAJ_RUTA_FIRMA());
        txtClaveAcceso.setText(ajustes.getAJ_CLAVE_ACCESO());
        
        lblDireccion.setText(empDao.getEMP_MATRIZ());
        lblRuc.setText(empDao.getEMP_RUC());
        lblPropietario.setText(empDao.getEMP_RAZON_SOCIAL());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        lblNombreEmpresa = new javax.swing.JLabel();
        lblPropietario = new javax.swing.JLabel();
        lblRuc = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JTextPane();
        lblImg = new raven.fbr.Panel();
        button9 = new com.anthony.swing.Button();
        roundPanel6 = new com.anthony.swing.RoundPanel();
        button8 = new com.anthony.swing.Button();
        pictureBox1 = new com.anthony.swing.PictureBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        txtClave = new textfield.PasswordField();
        jLabel12 = new javax.swing.JLabel();
        txtEmail = new textfield.TextField();
        jLabel13 = new javax.swing.JLabel();
        txtFirma = new textfield.TextField();
        txtClaveAcceso = new textfield.PasswordField();
        button11 = new com.anthony.swing.Button();
        scrollBarHorizontal = new com.anthony.swing.scrollbar.ScrollBarCustom();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel2.setBackground(new java.awt.Color(63, 81, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(63, 81, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("AJUSTES");

        jScrollPane1.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBar(scrollBarHorizontal);

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(67, 152, 128));
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(67, 152, 128));
        jLabel4.setText("Propietario:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(67, 152, 128));
        jLabel5.setText("Ruc:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(67, 152, 128));
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(67, 152, 128));
        jLabel7.setText("Telefono:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(67, 152, 128));
        jLabel8.setText("Direccion:");

        jSeparator1.setBackground(new java.awt.Color(32, 32, 32));
        jSeparator1.setForeground(new java.awt.Color(42, 53, 66));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setOpaque(true);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(188, 154, 55));
        jLabel9.setText("DATOS DE LA EMPRESA");

        lblNombreEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNombreEmpresa.setForeground(new java.awt.Color(204, 204, 204));
        lblNombreEmpresa.setText("Richard Anthony");

        lblPropietario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPropietario.setForeground(new java.awt.Color(204, 204, 204));
        lblPropietario.setText("Pérez Palacios");

        lblRuc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblRuc.setForeground(new java.awt.Color(204, 204, 204));
        lblRuc.setText("1723382594");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(204, 204, 204));
        lblEmail.setText("richardanto0726@gmail.com");

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(204, 204, 204));
        lblTelefono.setText("+593 983 698 513");

        lblDireccion.setEditable(false);
        lblDireccion.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblDireccion.setForeground(new java.awt.Color(204, 204, 204));
        lblDireccion.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ");
        lblDireccion.setFocusable(false);
        lblDireccion.setOpaque(false);

        lblImg.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/img/camaraPerfil.png"))); // NOI18N
        lblImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgMouseClicked(evt);
            }
        });

        button9.setBackground(new java.awt.Color(0, 102, 153));
        button9.setForeground(new java.awt.Color(255, 255, 255));
        button9.setText("CAMBIAR LOGO DE EMPRESA");
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPropietario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(lblImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblNombreEmpresa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblPropietario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblRuc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblTelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        roundPanel6.setBackground(new java.awt.Color(32, 32, 32));

        button8.setBackground(new java.awt.Color(0, 102, 153));
        button8.setForeground(new java.awt.Color(255, 255, 255));
        button8.setText("SABER MAS");

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/img/imgHomeInicio.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(188, 154, 55));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIONA TU NEGOCIO");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(188, 154, 55));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CON COMODIDAD Y");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(188, 154, 55));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("SEGURIDAD");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel6Layout.createSequentialGroup()
                        .addComponent(button8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        roundPanel2.setBackground(new java.awt.Color(32, 32, 32));

        txtClave.setBackground(new java.awt.Color(32, 32, 32));
        txtClave.setForeground(new java.awt.Color(0, 153, 204));
        txtClave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtClave.setLabelText("Contraseña actual");
        txtClave.setShowAndHide(true);
        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(188, 154, 55));
        jLabel12.setText("DATOS DE ENVIO DE LA FACTURA");

        txtEmail.setBackground(new java.awt.Color(32, 32, 32));
        txtEmail.setForeground(new java.awt.Color(0, 153, 204));
        txtEmail.setLabelText("Email (Outlook)");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(188, 154, 55));
        jLabel13.setText("DATOS DE LA FIRMA ELECTRONICA");

        txtFirma.setEditable(false);
        txtFirma.setBackground(new java.awt.Color(32, 32, 32));
        txtFirma.setForeground(new java.awt.Color(0, 153, 204));
        txtFirma.setLabelText("Ruta de la firma electronica");
        txtFirma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFirmaMouseClicked(evt);
            }
        });

        txtClaveAcceso.setBackground(new java.awt.Color(32, 32, 32));
        txtClaveAcceso.setForeground(new java.awt.Color(0, 153, 204));
        txtClaveAcceso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtClaveAcceso.setLabelText("Clave de acceso");
        txtClaveAcceso.setShowAndHide(true);
        txtClaveAcceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveAccesoActionPerformed(evt);
            }
        });

        button11.setBackground(new java.awt.Color(0, 102, 153));
        button11.setForeground(new java.awt.Color(255, 255, 255));
        button11.setText("ACTUALIZAR");
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtClave, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addComponent(txtFirma, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtClaveAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtClaveAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        scrollBarHorizontal.setOrientation(javax.swing.JScrollBar.HORIZONTAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrollBarHorizontal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBarHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblImgMouseClicked

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void txtClaveAccesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveAccesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveAccesoActionPerformed

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        JnaFileChooser jnaCh = new JnaFileChooser();
        jnaCh.setTitle("Facturing - Elegir imagen de perfil");
        boolean save = jnaCh.showOpenDialog(null);
        //Guardamos la ruta del PDF--------------------------------------
        String ruta_archivo = "";
        if (save) {
            System.out.println(jnaCh.getSelectedFile());
            ruta_archivo = jnaCh.getSelectedFile().toString();
            lblImg.setImage(new javax.swing.ImageIcon(ruta_archivo));
            String rutaOrigen = ruta_archivo;
            String rutaDestino = "C:\\FACTURING_V1\\src\\img\\empresa";
            File archivoCreado = new File(rutaOrigen);
            File archivoDestino = new File(rutaDestino + "/" + archivoCreado.getName());
            System.out.println("Archivo: " + archivoDestino.getAbsolutePath().toString());
            try {
                Files.copy(archivoCreado.toPath(), archivoDestino.toPath(), REPLACE_EXISTING);
                if (ajDao.updateLogo(rutaDestino + "/" + archivoCreado.getName()).equalsIgnoreCase("El logo se actualizo con exito")) {
                    toast = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Logo de empresa actualizado!!");
                    toast.showNotification();
                    init();
                } else if (ajDao.updateLogo(rutaDestino + "/" + archivoCreado.getName()).equalsIgnoreCase("El logo no se actualizo")) {
                    toast = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "El logo no se actualizo!!");
                    toast.showNotification();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {

        }
    }//GEN-LAST:event_button9ActionPerformed

    private void txtFirmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFirmaMouseClicked
        JnaFileChooser jnaCh = new JnaFileChooser();
        jnaCh.setTitle("Facturing - Elegir firma electronica");
        boolean save = jnaCh.showOpenDialog(null);
        //Guardamos la ruta del PDF--------------------------------------
        String ruta_archivo = "";
        if (save) {
            System.out.println(jnaCh.getSelectedFile());
            ruta_archivo = jnaCh.getSelectedFile().toString();
            String rutaOrigen = ruta_archivo;
            String rutaDestino = "C:\\FACTURING_V1\\src\\img\\empresa";
            File archivoCreado = new File(rutaOrigen);
            File archivoDestino = new File(rutaDestino + "/" + archivoCreado.getName());
            try {
                Files.copy(archivoCreado.toPath(), archivoDestino.toPath(), REPLACE_EXISTING);
                ID_AJUSTE = 1;
                AJ_CLAVE = txtClave.getText();
                AJ_CLAVE_ACCESO = txtClaveAcceso.getText();
                AJ_EMAIL = txtEmail.getText();
                AJ_RUTA_FIRMA = archivoDestino.toPath().toString();
                AJ_LOGO_EMPRESA = ajustes.getAJ_LOGO_EMPRESA();
                ajustes.setID_AJUSTE(ID_AJUSTE);
                ajustes.setAJ_EMAIL(AJ_EMAIL);
                ajustes.setAJ_CLAVE(AJ_CLAVE);
                ajustes.setAJ_CLAVE_ACCESO(AJ_CLAVE_ACCESO);
                ajustes.setAJ_LOGO_EMPRESA(AJ_LOGO_EMPRESA);
                ajustes.setAJ_RUTA_FIRMA(AJ_RUTA_FIRMA);
                txtFirma.setText(archivoDestino.toPath().toString());
                if (ajDao.updateAjuste(ajustes).equalsIgnoreCase("Los datos de ajustes se acualizados!!")) {
                    toast = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Datos acualizados!!");
                    toast.showNotification();
                    init();
                } else if (ajDao.updateAjuste(ajustes).equalsIgnoreCase("El ajuste no fue actualizado!")) {
                    toast = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar!!");
                    toast.showNotification();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_txtFirmaMouseClicked

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        MessageDialogDark obj = new MessageDialogDark(admin);
        obj.showMessage("¿ Deseas actualizar los ajustes ?", "Recuerda que los datos que se estan actualizando se mantendran como los deja actualmente, no se podran regresar a un estado anterior.");
        if (obj.getMessageType() == MessageDialogDark.MessageType.OK) {
            ID_AJUSTE = 1;
            AJ_CLAVE = txtClave.getText();
            AJ_CLAVE_ACCESO = txtClaveAcceso.getText();
            AJ_EMAIL = txtEmail.getText();
            AJ_RUTA_FIRMA = txtFirma.getText();
            AJ_LOGO_EMPRESA = ajustes.getAJ_LOGO_EMPRESA();
            ajustes.setID_AJUSTE(ID_AJUSTE);
            ajustes.setAJ_EMAIL(AJ_EMAIL);
            ajustes.setAJ_CLAVE(AJ_CLAVE);
            ajustes.setAJ_CLAVE_ACCESO(AJ_CLAVE_ACCESO);
            ajustes.setAJ_LOGO_EMPRESA(AJ_LOGO_EMPRESA);
            ajustes.setAJ_RUTA_FIRMA(AJ_RUTA_FIRMA);
            if (ajDao.updateAjuste(ajustes).equalsIgnoreCase("El ajuste fue actualizado con exito!")) {
                toast = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Los datos de ajustes se acualizados!!");
                toast.showNotification();
                admin.dispose();
                Login l = new Login();
                l.setVisible(true);

            } else if (ajDao.updateAjuste(ajustes).equalsIgnoreCase("El ajuste no fue actualizado!")) {
                toast = new Toast(admin, Toast.Type.ERROR, Toast.Location.BOTTOM_RIGHT, "No se pudo actualizar!!");
                toast.showNotification();
            }
        } else if (obj.getMessageType() == MessageDialogDark.MessageType.CANCEL) {
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se cancelo el proceso!!");
            toast.showNotification();
        }

    }//GEN-LAST:event_button11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button button11;
    private com.anthony.swing.Button button8;
    private com.anthony.swing.Button button9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane lblDireccion;
    private javax.swing.JLabel lblEmail;
    private raven.fbr.Panel lblImg;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JLabel lblPropietario;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblTelefono;
    private com.anthony.swing.PictureBox pictureBox1;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel2;
    private com.anthony.swing.RoundPanel roundPanel6;
    private com.anthony.swing.scrollbar.ScrollBarCustom scrollBarHorizontal;
    private textfield.PasswordField txtClave;
    private textfield.PasswordField txtClaveAcceso;
    private textfield.TextField txtEmail;
    private textfield.TextField txtFirma;
    // End of variables declaration//GEN-END:variables

}
