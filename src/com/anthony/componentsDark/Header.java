package com.anthony.componentsDark;

import com.anthony.swing.clases.ComponentResizer;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Header extends javax.swing.JPanel {
    
    private ComponentResizer resizer;
    private JFrame fram;
    private boolean register = true;
    private int x;
    private int y;
    
    public Header() {
        initComponents();
    }
    
    public void initJFram(JFrame fram) {
        this.fram = fram;
        cmdTema.setVisible(false);
        resizer = new ComponentResizer();
        resizer.setSnapSize(new Dimension(10, 10));
        resizer.setMinimumSize(new Dimension(970, 700));
        resizer.registerComponent(fram);
        fram.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == JFrame.MAXIMIZED_BOTH) {
                    resizer.deregisterComponent(fram);
                    register = false;
                } else if (e.getNewState() == JFrame.NORMAL) {
                    if (register == false) {
                        resizer.registerComponent(fram);
                        register = true;
                    }
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (fram.getExtendedState() == JFrame.NORMAL && SwingUtilities.isLeftMouseButton(e)) {
                    x = e.getX() + 3;
                    y = e.getY() + 3;
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (fram.getExtendedState() == JFrame.NORMAL) {
                        fram.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
                    }
                }
            }
        });
    }
    
    public void initNormalizar(JFrame fram) {
        btnNormaizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (fram.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    fram.setExtendedState(JFrame.NORMAL);
                    btnNormaizar.setToolTipText("Max. Ventana");
                } else {
                    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    fram.setMaximizedBounds(env.getMaximumWindowBounds());
                    fram.setExtendedState(fram.getExtendedState() | fram.MAXIMIZED_BOTH);
                    btnNormaizar.setToolTipText("Normalizar ventana");
                }
            }
        });
    }
    
    public void minimizar(JFrame fram) {
        btnMinimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fram.setExtendedState(JFrame.ICONIFIED);
                btnMinimizar.setToolTipText("Minimizar");                
            }
        });
    }
    
    public void addMenuEvent(ActionListener event) {
        cmdMenu.addActionListener(event);
    }
    
    public void addTemaEvent(ActionListener event) {
        cmdTema.addActionListener(event);
    }
    
    public void addEventoSalir(ActionListener event) {
        btnCerrar.addActionListener(event);
    }
    
    public void setAbrirMenu() {
        cmdMenu.setToolTipText("Abrir Menu");
    }
    
    public void setCerrarMenu() {
        cmdMenu.setToolTipText("Cerrar Menu");
    }
    
    public void minimizarEvent(ActionListener event) {
        btnMinimizar.addActionListener(event);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMenu = new com.anthony.swing.Button();
        btnCerrar = new com.anthony.swing.Button();
        btnMinimizar = new com.anthony.swing.Button();
        btnNormaizar = new com.anthony.swing.Button();
        cmdTema = new com.anthony.swing.Button();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(32, 32, 32));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(42, 53, 66)));

        cmdMenu.setBackground(new java.awt.Color(32, 32, 32));
        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/menu.png"))); // NOI18N
        cmdMenu.setFocusable(false);

        btnCerrar.setBackground(new java.awt.Color(153, 13, 13));
        btnCerrar.setToolTipText("Cerrar");
        btnCerrar.setEffectColor(new java.awt.Color(255, 255, 255));
        btnCerrar.setFocusable(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnMinimizar.setBackground(new java.awt.Color(178, 94, 11));
        btnMinimizar.setToolTipText("minimizar");
        btnMinimizar.setEffectColor(new java.awt.Color(255, 255, 255));
        btnMinimizar.setFocusable(false);

        btnNormaizar.setBackground(new java.awt.Color(65, 123, 32));
        btnNormaizar.setEffectColor(new java.awt.Color(255, 255, 255));
        btnNormaizar.setFocusable(false);

        cmdTema.setBackground(new java.awt.Color(55, 49, 30));
        cmdTema.setForeground(new java.awt.Color(204, 204, 204));
        cmdTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoSol.png"))); // NOI18N
        cmdTema.setText("Modo claro");
        cmdTema.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(150, 150, 150));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FACTURING");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdTema, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNormaizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNormaizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cmdMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdTema, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
//        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button btnCerrar;
    private com.anthony.swing.Button btnMinimizar;
    private com.anthony.swing.Button btnNormaizar;
    private com.anthony.swing.Button cmdMenu;
    private com.anthony.swing.Button cmdTema;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
