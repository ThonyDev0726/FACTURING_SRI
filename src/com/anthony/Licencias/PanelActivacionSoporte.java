package com.anthony.Licencias;

import java.awt.event.ActionListener;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

public class PanelActivacionSoporte extends javax.swing.JLayeredPane {


    public PanelActivacionSoporte() {
        initComponents();
        Contacto.setVisible(false);
        Activar.setVisible(true);
    }

    public void showRegister(boolean show) {
        if (show) {
            Activar.setVisible(true);
            Contacto.setVisible(false);
        } else {
            Activar.setVisible(false);
            Contacto.setVisible(true);
        }
    }

    public void btnMetodo(ActionListener event) {
        btnIngresar.addActionListener(event);
    }

    public String addObtenerClave() {
        String usu = txtClaveProducto.getText();
        return usu;
    }

    public void limpiarClave() {
        txtClaveProducto.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Activar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnIngresar = new com.anthony.swing.Button();
        txtClaveProducto = new textfield.TextField();
        Contacto = new javax.swing.JPanel();
        txtNombre = new rojeru_san.rsfield.RSTextFullRound();
        txtAsunto = new rojeru_san.rsfield.RSTextFullRound();
        jLabel2 = new javax.swing.JLabel();
        txtTelefono = new rojeru_san.rsfield.RSTextFullRound();
        jLabel3 = new javax.swing.JLabel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        btnEnviarMail = new com.anthony.swing.Button();

        setLayout(new java.awt.CardLayout());

        Activar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(3, 179, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ACTIVAR PRODUCTO");

        btnIngresar.setBackground(new java.awt.Color(3, 179, 255));
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("ACTIVAR");
        btnIngresar.setEffectColor(new java.awt.Color(255, 255, 255));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        txtClaveProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtClaveProducto.setLabelText("Ingresa la clave de producto");

        javax.swing.GroupLayout ActivarLayout = new javax.swing.GroupLayout(Activar);
        Activar.setLayout(ActivarLayout);
        ActivarLayout.setHorizontalGroup(
            ActivarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ActivarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ActivarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(ActivarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(ActivarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                            .addComponent(txtClaveProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ActivarLayout.setVerticalGroup(
            ActivarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ActivarLayout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(txtClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        add(Activar, "card2");

        Contacto.setBackground(new java.awt.Color(255, 255, 255));
        Contacto.setPreferredSize(new java.awt.Dimension(550, 0));

        txtNombre.setBackground(new java.awt.Color(210, 228, 245));
        txtNombre.setBorderColor(new java.awt.Color(210, 228, 245));
        txtNombre.setPlaceholder("Nombres");

        txtAsunto.setBackground(new java.awt.Color(210, 228, 245));
        txtAsunto.setBorderColor(new java.awt.Color(210, 228, 245));
        txtAsunto.setPlaceholder("Asunto");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(3, 179, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CONTACTANOS");

        txtTelefono.setBackground(new java.awt.Color(210, 228, 245));
        txtTelefono.setBorderColor(new java.awt.Color(210, 228, 245));
        txtTelefono.setPlaceholder("Telefono");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(130, 183, 234));
        jLabel3.setText(" Mensaje:");

        roundPanel1.setBackground(new java.awt.Color(210, 228, 245));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        txtMensaje.setBackground(new java.awt.Color(210, 228, 245));
        txtMensaje.setColumns(20);
        txtMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(0, 112, 192));
        txtMensaje.setLineWrap(true);
        txtMensaje.setRows(5);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBorder(null);
        jScrollPane1.setViewportView(txtMensaje);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
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

        javax.swing.GroupLayout ContactoLayout = new javax.swing.GroupLayout(Contacto);
        Contacto.setLayout(ContactoLayout);
        ContactoLayout.setHorizontalGroup(
            ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ContactoLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(txtAsunto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContactoLayout.createSequentialGroup()
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnEnviarMail, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        ContactoLayout.setVerticalGroup(
            ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContactoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addGap(60, 60, 60)
                .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnviarMail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(Contacto, "card3");
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
//        Properties propiedad = new Properties();
//        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//        propiedad.setProperty("mail.smtp.starttls.enable", "true");
//        propiedad.setProperty("mail.smtp.port", "587");
//        propiedad.setProperty("mail.smtp.auth", "true");
//
//        Session sesion = Session.getDefaultInstance(propiedad);
//        String correoEnvia = "anthonyp.soft@gmail.com";
//        String clave = "0726TheThony";
//        String destinatario = "anthonyp.soft@gmail.com";
//        String asunto = txtAsunto.getText();
//        String nombre = txtNombre.getText();
//        String telefono = txtTelefono.getText();
//        String mensaje = txtMensaje.getText();
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
//            Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_button1ActionPerformed

    private void btnEnviarMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMailActionPerformed
//        // TODO add your handling code here:
//        Properties propiedad = new Properties();
//        propiedad.setProperty("mail.smtp.host", "smtp.office365.com");
//        propiedad.setProperty("mail.smtp.starttls.enable", "true");
//        propiedad.setProperty("mail.smtp.port", "587");
//        propiedad.setProperty("mail.smtp.auth", "true");
//        propiedad.put("mail.smtp.ssl.trust", "smtp.office365.com");
//        propiedad.setProperty("mail.pop3s.ssl.protocols", "TLSv1.2");
//
//        Session sesion = Session.getDefaultInstance(propiedad);
//        String correoEnvia = "thonydev@outlook.com";
//        String clave = "07262001AappAnthony";
////        String clave = "0726TheThony";
//        String destinatario = "anthonyp.soft@gmail.com";
//        String asunto = txtAsunto.getText();
//        String nombre = txtNombre.getText();
//        String telefono = txtTelefono.getText();
//        String mensaje = txtMensaje.getText();
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
////            Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("error al enviar mensaje address: " + ex);
//        } catch (MessagingException ex) {
////            Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("error al enviar mensaje MessagingException: " + ex);
//        }
    }//GEN-LAST:event_btnEnviarMailActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Activar;
    private javax.swing.JPanel Contacto;
    private com.anthony.swing.Button btnEnviarMail;
    private com.anthony.swing.Button btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.anthony.swing.RoundPanel roundPanel1;
    private rojeru_san.rsfield.RSTextFullRound txtAsunto;
    private textfield.TextField txtClaveProducto;
    private javax.swing.JTextArea txtMensaje;
    private rojeru_san.rsfield.RSTextFullRound txtNombre;
    private rojeru_san.rsfield.RSTextFullRound txtTelefono;
    // End of variables declaration//GEN-END:variables
}
