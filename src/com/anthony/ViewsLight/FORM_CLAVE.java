package com.anthony.ViewsLight;

import com.anthony.MainLight.MainAdministrador;
import com.anthony.Models.USUARIO;
import com.anthony.ModelsDAO.USUARIO_DAO;
import com.anthony.toast.Toast;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FORM_CLAVE extends javax.swing.JPanel {

    MainAdministrador admin;
    USUARIO usu;
    USUARIO_DAO dao = new USUARIO_DAO();
    Toast panel;

    public FORM_CLAVE() {
        this.admin = admin;
        this.usu = usu;
        initComponents();
        btnCambiar.setVisible(false);
        txtCambiar.setVisible(false);
    }

    public FORM_CLAVE(USUARIO usu, MainAdministrador admin) {
        this.admin = admin;
        this.usu = usu;
        initComponents();
        btnCambiar.setVisible(false);
        txtCambiar.setVisible(false);
    }

    public Boolean confirmarClave() throws SQLException {
        Boolean ban = false;
        if (!txtConfirmar.getText().equals(usu.getUSU_CLAVE())) {
            panel = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Usuario no autorizado");
            panel.showNotification();
            txtConfirmar.setText("");
        } else {
            txtCambiar.setVisible(true);
            btnCambiar.setVisible(true);
            btnConfirmar.setVisible(false);
            txtConfirmar.setVisible(false);
            ban = true;
        }
        return ban;
    }

    public void cambiarClave() throws SQLException {
        if (confirmarClave()) {
            usu.setUSU_CLAVE(txtConfirmar.getText());
            dao.update_password(usu.getID_USUARIO(), txtCambiar.getText());
            panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Clave actualizada!!");
            panel.showNotification();
            txtCambiar.setText("");
        }
    }

    public void reseteo() {
        txtCambiar.setVisible(false);
        txtConfirmar.setText("");
        txtCambiar.setText("");
        btnCambiar.setVisible(false);
        btnConfirmar.setVisible(true);
        txtConfirmar.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.anthony.swing.RoundPanel();
        txtConfirmar = new textfield.PasswordField();
        txtCambiar = new textfield.PasswordField();
        btnConfirmar = new com.anthony.swing.Button();
        btnCambiar = new com.anthony.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(246, 247, 251));
        setForeground(new java.awt.Color(246, 242, 251));

        roundPanel1.setBackground(new java.awt.Color(234, 241, 251));

        txtConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConfirmar.setLabelText("Contraseña actual");
        txtConfirmar.setShowAndHide(true);
        txtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmarActionPerformed(evt);
            }
        });

        txtCambiar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCambiar.setLabelText("Contraseña nueva");
        txtCambiar.setShowAndHide(true);
        txtCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCambiarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(8, 170, 250));
        btnConfirmar.setForeground(new java.awt.Color(7, 6, 17));
        btnConfirmar.setText("VERIFICAR CLAVE");
        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCambiar.setBackground(new java.awt.Color(8, 170, 250));
        btnCambiar.setForeground(new java.awt.Color(7, 6, 17));
        btnCambiar.setText("CAMBIAR CLAVE");
        btnCambiar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/img/imagenSeguridad.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(7, 6, 17));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CAMBIAR CLAVE");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(btnCambiar, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(txtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(txtCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmarActionPerformed

    private void txtCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCambiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCambiarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            // TODO add your handling code here:
            confirmarClave();
        } catch (SQLException ex) {
            Logger.getLogger(FORM_CLAVE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        try {
            cambiarClave();
            reseteo();
        } catch (SQLException ex) {
            Logger.getLogger(FORM_CLAVE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCambiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnCambiar;
    private com.anthony.swing.Button btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private com.anthony.swing.RoundPanel roundPanel1;
    private textfield.PasswordField txtCambiar;
    private textfield.PasswordField txtConfirmar;
    // End of variables declaration//GEN-END:variables
}
