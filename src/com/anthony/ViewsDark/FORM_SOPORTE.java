/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.anthony.ViewsDark;

import com.anthony.swing.scrollbar.ScrollBarCustom;

/**
 *
 * @author perez
 */
public class FORM_SOPORTE extends javax.swing.JPanel {

    /**
     * Creates new form FORM_SOPORTE
     */
    public FORM_SOPORTE() {
        initComponents();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
//        sp1.setVerticalScrollBar(new ScrollBarCustom());
//        sp.setVerticalScrollBar(new ScrollBarCustom());
//        sp.setVerticalScrollBar(new ScrollBarCustom());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        mainSilderSoporte = new com.anthony.slider.mainSilderSoporte();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        txtAsunto = new rojeru_san.rsfield.RSTextFullRound();
        txtNombre = new rojeru_san.rsfield.RSTextFullRound();
        txtTelefono = new rojeru_san.rsfield.RSTextFullRound();
        jLabel3 = new javax.swing.JLabel();
        roundPanel3 = new com.anthony.swing.RoundPanel();
        sp4 = new javax.swing.JScrollPane();
        txtMensaje1 = new javax.swing.JTextArea();
        btnEnviarMail = new com.anthony.swing.Button();
        imageAvatar4 = new com.anthony.swing.ImageAvatar();
        imageAvatar2 = new com.anthony.swing.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        imageAvatar3 = new com.anthony.swing.ImageAvatar();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        imageAvatar5 = new com.anthony.swing.ImageAvatar();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(22, 23, 23));

        jScrollPane1.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));

        txtAsunto.setBackground(new java.awt.Color(22, 23, 23));
        txtAsunto.setBorderColor(new java.awt.Color(22, 23, 23));
        txtAsunto.setPhColor(new java.awt.Color(150, 160, 175));
        txtAsunto.setPlaceholder("Asunto");

        txtNombre.setBackground(new java.awt.Color(22, 23, 23));
        txtNombre.setBorderColor(new java.awt.Color(22, 23, 23));
        txtNombre.setPhColor(new java.awt.Color(150, 160, 175));
        txtNombre.setPlaceholder("Nombres");

        txtTelefono.setBackground(new java.awt.Color(22, 23, 23));
        txtTelefono.setBorderColor(new java.awt.Color(22, 23, 23));
        txtTelefono.setPhColor(new java.awt.Color(150, 160, 175));
        txtTelefono.setPlaceholder("Telefono");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText(" Mensaje:");

        roundPanel3.setBackground(new java.awt.Color(22, 23, 23));

        sp4.setBorder(null);
        sp4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp4.setOpaque(false);

        txtMensaje1.setBackground(new java.awt.Color(22, 23, 23));
        txtMensaje1.setColumns(20);
        txtMensaje1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMensaje1.setForeground(new java.awt.Color(150, 160, 175));
        txtMensaje1.setLineWrap(true);
        txtMensaje1.setRows(5);
        txtMensaje1.setWrapStyleWord(true);
        txtMensaje1.setBorder(null);
        sp4.setViewportView(txtMensaje1);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp4, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEnviarMail.setBackground(new java.awt.Color(3, 179, 255));
        btnEnviarMail.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarMail.setText("ENVIAR");
        btnEnviarMail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEnviarMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMailActionPerformed(evt);
            }
        });

        imageAvatar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/img/publicidadEmpresaPersonaje.png"))); // NOI18N

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAsunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(btnEnviarMail, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(imageAvatar4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviarMail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        imageAvatar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/img/imgHomeInicio.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(130, 119, 96));
        jLabel1.setText("??QUIENES SOMOS?");

        jScrollPane2.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(22, 23, 23));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(22, 23, 23));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(150, 160, 175));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Posicionarnos en el Mercado como empresa l??der, a nivel nacional e internacional, implementando soluciones en cuanto a capacitaci??n, desarrollo de software, asesor??a, soporte t??cnico y consultor??as para nuestros clientes de forma confiable, adaptados a las nuevas tendencias del Mercado. Continuaremos construyendo nuestro futuro, siendo una empresa competitiva que ofrece servicios de TI de calidad, reconocida su buena reputacion, por las soluciones tecnol??gicas que entregamos, generando relaciones duraderas con nuestros clientes, proveedores y nuestro equipo.");
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(null);
        jTextArea1.setFocusable(false);
        jTextArea1.setRequestFocusEnabled(false);
        jTextArea1.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane3.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(22, 23, 23));

        jTextArea2.setBackground(new java.awt.Color(22, 23, 23));
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Posicionarnos en el Mercado como empresa l??der, a nivel nacional e internacional, implementando soluciones en cuanto a capacitaci??n, desarrollo de software, asesor??a, soporte t??cnico y consultor??as para nuestros clientes de forma confiable, adaptados a las nuevas tendencias del Mercado. Continuaremos construyendo nuestro futuro, siendo una empresa competitiva que ofrece servicios de TI de calidad, reconocida su buena reputacion, por las soluciones tecnol??gicas que entregamos, generando relaciones duraderas con nuestros clientes, proveedores y nuestro equipo.");
        jScrollPane3.setViewportView(jTextArea2);

        imageAvatar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/img/imgHomeInicio.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(130, 119, 96));
        jLabel2.setText("MISION");

        jScrollPane4.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane4.setBorder(null);
        jScrollPane4.setForeground(new java.awt.Color(22, 23, 23));

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(22, 23, 23));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea3.setForeground(new java.awt.Color(150, 160, 175));
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText("Posicionarnos en el Mercado como empresa l??der, a nivel nacional e internacional, implementando soluciones en cuanto a capacitaci??n, desarrollo de software, asesor??a, soporte t??cnico y consultor??as para nuestros clientes de forma confiable, adaptados a las nuevas tendencias del Mercado. Continuaremos construyendo nuestro futuro, siendo una empresa competitiva que ofrece servicios de TI de calidad, reconocida su buena reputacion, por las soluciones tecnol??gicas que entregamos, generando relaciones duraderas con nuestros clientes, proveedores y nuestro equipo.");
        jTextArea3.setAutoscrolls(false);
        jTextArea3.setBorder(null);
        jTextArea3.setFocusable(false);
        jTextArea3.setRequestFocusEnabled(false);
        jTextArea3.setVerifyInputWhenFocusTarget(false);
        jScrollPane4.setViewportView(jTextArea3);

        imageAvatar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/img/imgHomeInicio.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(130, 119, 96));
        jLabel4.setText("MISION");

        jScrollPane5.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane5.setBorder(null);
        jScrollPane5.setForeground(new java.awt.Color(22, 23, 23));

        jTextArea4.setEditable(false);
        jTextArea4.setBackground(new java.awt.Color(22, 23, 23));
        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea4.setForeground(new java.awt.Color(150, 160, 175));
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setText("Posicionarnos en el Mercado como empresa l??der, a nivel nacional e internacional, implementando soluciones en cuanto a capacitaci??n, desarrollo de software, asesor??a, soporte t??cnico y consultor??as para nuestros clientes de forma confiable, adaptados a las nuevas tendencias del Mercado. Continuaremos construyendo nuestro futuro, siendo una empresa competitiva que ofrece servicios de TI de calidad, reconocida su buena reputacion, por las soluciones tecnol??gicas que entregamos, generando relaciones duraderas con nuestros clientes, proveedores y nuestro equipo.");
        jTextArea4.setAutoscrolls(false);
        jTextArea4.setBorder(null);
        jTextArea4.setFocusable(false);
        jTextArea4.setRequestFocusEnabled(false);
        jTextArea4.setVerifyInputWhenFocusTarget(false);
        jScrollPane5.setViewportView(jTextArea4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSilderSoporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageAvatar5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mainSilderSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageAvatar5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5)))
                .addGap(18, 18, 18)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMailActionPerformed
//        // TODO add your handling code here:
//        Properties propiedad = new Properties();
//        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//        propiedad.setProperty("mail.smtp.starttls.enable", "true");
//        propiedad.setProperty("mail.smtp.port", "587");
//        propiedad.setProperty("mail.smtp.auth", "true");
//
//        Session sesion = Session.getDefaultInstance(propiedad);
//        String correoEnvia = "anthonyp.soft@gmail.com";
//        String clave = "nllegrogzouunhel";
//        //        String clave = "0726TheThony";
//        String destinatario = "anthonyp.soft@gmail.com";
//        String asunto = txtAsunto.getText();
//        String nombre = txtNombre.getText();
//        String telefono = txtTelefono.getText();
//        String mensaje = txtMensaje1.getText();
//
//        MimeMessage mail = new MimeMessage(sesion);
//        try {
//            mail.setFrom(new InternetAddress(correoEnvia));
//            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//            mail.setSubject(asunto);
//            mail.setText("Hola, soy " + nombre + "\n" + "\n" + mensaje + "\n" + "\n" + "Este es mi numero de contacto: " + telefono);
//
//            Transport transporte = sesion.getTransport("smtp");
//            transporte.connect(correoEnvia, clave);
//            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
//            transporte.close();
//
//            DesktopNotify.showDesktopMessage("Informacion", "coreoEnviado", DesktopNotify.SUCCESS, 5000L);
//
//        } catch (AddressException ex) {
//            Logger.getLogger(FormSoporteTecnico.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(FormSoporteTecnico.class.getName()).log(Level.SEVERE, null, ex);
//        }catch(Exception ex){
//            System.out.println(""+ex.toString());
//        }
    }//GEN-LAST:event_btnEnviarMailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnEnviarMail;
    private com.anthony.swing.ImageAvatar imageAvatar2;
    private com.anthony.swing.ImageAvatar imageAvatar3;
    private com.anthony.swing.ImageAvatar imageAvatar4;
    private com.anthony.swing.ImageAvatar imageAvatar5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private com.anthony.slider.mainSilderSoporte mainSilderSoporte;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel3;
    private javax.swing.JScrollPane sp4;
    private rojeru_san.rsfield.RSTextFullRound txtAsunto;
    private javax.swing.JTextArea txtMensaje1;
    private rojeru_san.rsfield.RSTextFullRound txtNombre;
    private rojeru_san.rsfield.RSTextFullRound txtTelefono;
    // End of variables declaration//GEN-END:variables
}
