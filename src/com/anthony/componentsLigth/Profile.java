package com.anthony.componentsLigth;

public class Profile extends javax.swing.JPanel {

    public Profile() {
        initComponents();
        setOpaque(false);
    }

    public void init(String usuario, String cargo) {
        imgUsu.setToolTipText(usuario+" - "+cargo);
        lblUsuario.setText(usuario);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgUsu = new com.anthony.swing.ImageAvatar();
        separator = new javax.swing.JSeparator();
        lblUsuario = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();

        imgUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/logo_empresa.png"))); // NOI18N

        separator.setBackground(new java.awt.Color(153, 255, 153));
        separator.setForeground(new java.awt.Color(153, 255, 153));
        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setForeground(new java.awt.Color(0, 102, 204));
        lblUsuario.setText("Richard Pérez Palacios");

        lblCargo.setForeground(new java.awt.Color(0, 204, 163));
        lblCargo.setText("Administrador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(imgUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(lblCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(imgUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(separator))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.ImageAvatar imgUsu;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JSeparator separator;
    // End of variables declaration//GEN-END:variables
}
