package com.anthony.componentsDark;

import com.anthony.swing.Glass;
import com.anthony.swing.Glass_notification;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MessageDialogDark extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass_notification glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;
    RoundBorder1 border = new RoundBorder1(0);

    public MessageDialogDark(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
        pnlPrincipal.setVisible(true);
        pnlCaja.setVisible(false);
        pnlUsuario.setVisible(false);
        bordes();
    }

    public void bordes() {
        ScrollBarCustom sbh = new ScrollBarCustom();
        spPago.setVerticalScrollBar(new ScrollBarCustom());
        spPago.setHorizontalScrollBar(sbh);
        spPago.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spPago.setBorder(border);
        spPago.getViewport().setOpaque(false);
    }
    private void init() {
        bordes();
        setBackground(new Color(0, 0, 0, 0));;
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeMessage();
            }
        });
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if (show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        setOpacity(0f);
        glass = new Glass_notification();
    }

    public void showMessage(String title, String message) {
        fram.setGlassPane(glass);
        glass.setVisible(true);
//        lbTitle.setText(title);
//        txt.setText(message);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }

    private void startAnimator(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }

    public String getClave(String message) {
        fram.setGlassPane(glass);
        glass.setVisible(true);

        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
        return message;
    }

    public void closeMessage() {
        startAnimator(false);
    }

    public MessageType getMessageType() {
        return messageType;
    }

//    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
//        messageType = MessageType.CANCEL;
//        closeMessage();
//    }                                         
//
//    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {                                      
//        messageType = MessageType.OK;
//        closeMessage();
//    }  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.anthony.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlCaja = new javax.swing.JPanel();
        textField2 = new textfield.TextField();
        cmdOK3 = new com.anthony.swing.ButtonOutLine();
        pnlPrincipal = new javax.swing.JPanel();
        btnPnlCaja = new com.anthony.swing.ButtonOutLine();
        btnBuscarUsu = new com.anthony.swing.ButtonOutLine();
        textField3 = new textfield.TextField();
        textField5 = new textfield.TextField();
        combobox1 = new com.anthony.swing.Combobox();
        pnlUsuario = new javax.swing.JPanel();
        textField4 = new textfield.TextField();
        spPago = new javax.swing.JScrollPane();
        tDatosUsuario = new rojeru_san.complementos.TableMetro();
        cmdOK = new com.anthony.swing.Button();
        cmdCancel = new com.anthony.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(8, 170, 250));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("APERTURA DE CAJA");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.CardLayout());

        pnlCaja.setBackground(new java.awt.Color(51, 51, 51));

        textField2.setBackground(new java.awt.Color(51, 51, 51));
        textField2.setLabelText("Nombre de la caja");

        cmdOK3.setBackground(new java.awt.Color(18, 138, 62));
        cmdOK3.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        cmdOK3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOK3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCajaLayout = new javax.swing.GroupLayout(pnlCaja);
        pnlCaja.setLayout(pnlCajaLayout);
        pnlCajaLayout.setHorizontalGroup(
            pnlCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCajaLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdOK3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        pnlCajaLayout.setVerticalGroup(
            pnlCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCajaLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(pnlCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdOK3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel1.add(pnlCaja, "card2");

        pnlPrincipal.setBackground(new java.awt.Color(51, 51, 51));

        btnPnlCaja.setBackground(new java.awt.Color(18, 138, 62));
        btnPnlCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnPnlCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoAgregar.png"))); // NOI18N
        btnPnlCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPnlCajaActionPerformed(evt);
            }
        });

        btnBuscarUsu.setBackground(new java.awt.Color(18, 138, 62));
        btnBuscarUsu.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoBuscar.png"))); // NOI18N
        btnBuscarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuActionPerformed(evt);
            }
        });

        textField3.setBackground(new java.awt.Color(51, 51, 51));
        textField3.setForeground(new java.awt.Color(0, 102, 153));
        textField3.setLabelText("Usuario");
        textField3.setOpaque(true);

        textField5.setBackground(new java.awt.Color(51, 51, 51));
        textField5.setForeground(new java.awt.Color(0, 102, 153));
        textField5.setLabelText("Monto");
        textField5.setOpaque(true);

        combobox1.setBackground(new java.awt.Color(51, 51, 51));
        combobox1.setLabeText("Caja");

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(combobox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPnlCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(btnPnlCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel1.add(pnlPrincipal, "card2");

        pnlUsuario.setBackground(new java.awt.Color(51, 51, 51));

        textField4.setBackground(new java.awt.Color(51, 51, 51));
        textField4.setLabelText("Usuario");

        spPago.setBackground(new java.awt.Color(51, 51, 51));
        spPago.setForeground(new java.awt.Color(51, 51, 51));
        spPago.setFocusable(false);

        tDatosUsuario = new rojeru_san.complementos.TableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tDatosUsuario.setBackground(new java.awt.Color(32, 32, 32));
        tDatosUsuario.setForeground(new java.awt.Color(32, 32, 32));
        tDatosUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "DESCRIPCION"
            }
        ));
        tDatosUsuario.setAltoHead(30);
        tDatosUsuario.setAutoscrolls(false);
        tDatosUsuario.setColorBackgoundHead(new java.awt.Color(63, 63, 63));
        tDatosUsuario.setColorBordeFilas(new java.awt.Color(9, 9, 9));
        tDatosUsuario.setColorBordeHead(new java.awt.Color(102, 102, 102));
        tDatosUsuario.setColorFilasBackgound1(new java.awt.Color(32, 32, 32));
        tDatosUsuario.setColorFilasBackgound2(new java.awt.Color(51, 51, 51));
        tDatosUsuario.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        tDatosUsuario.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        tDatosUsuario.setColorForegroundHead(new java.awt.Color(102, 204, 255));
        tDatosUsuario.setColorSelBackgound(new java.awt.Color(18, 18, 18));
        tDatosUsuario.setColorSelForeground(new java.awt.Color(153, 255, 255));
        tDatosUsuario.setFocusable(false);
        tDatosUsuario.setFuenteHead(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tDatosUsuario.setGridColor(new java.awt.Color(51, 51, 51));
        tDatosUsuario.setGrosorBordeFilas(0);
        tDatosUsuario.setGrosorBordeHead(0);
        tDatosUsuario.setOpaque(false);
        tDatosUsuario.setRowHeight(30);
        tDatosUsuario.setSelectionBackground(new java.awt.Color(32, 32, 32));
        tDatosUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosUsuarioMouseClicked(evt);
            }
        });
        spPago.setViewportView(tDatosUsuario);

        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
        pnlUsuario.setLayout(pnlUsuarioLayout);
        pnlUsuarioLayout.setHorizontalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(64, 64, 64))
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(spPago, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnlUsuarioLayout.setVerticalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPago, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        jPanel1.add(pnlUsuario, "card2");

        cmdOK.setBackground(new java.awt.Color(0, 102, 51));
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setText("ACEPTAR");
        cmdOK.setFocusPainted(false);
        cmdOK.setFocusable(false);
        cmdOK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });

        cmdCancel.setBackground(new java.awt.Color(102, 51, 0));
        cmdCancel.setForeground(new java.awt.Color(255, 255, 255));
        cmdCancel.setText("CANCELAR");
        cmdCancel.setFocusPainted(false);
        cmdCancel.setFocusable(false);
        cmdCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPnlCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPnlCajaActionPerformed
        pnlPrincipal.setVisible(false);
        pnlCaja.setVisible(true);
        pnlUsuario.setVisible(false);
    }//GEN-LAST:event_btnPnlCajaActionPerformed

    private void btnBuscarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuActionPerformed
        pnlPrincipal.setVisible(false);
        pnlCaja.setVisible(false);
        pnlUsuario.setVisible(true);
    }//GEN-LAST:event_btnBuscarUsuActionPerformed

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.OK;
        closeMessage();
    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdOK3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOK3ActionPerformed
        pnlPrincipal.setVisible(true);
        pnlCaja.setVisible(false);
        pnlUsuario.setVisible(false);
    }//GEN-LAST:event_cmdOK3ActionPerformed

    private void tDatosUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosUsuarioMouseClicked
        pnlPrincipal.setVisible(true);
        pnlCaja.setVisible(false);
        pnlUsuario.setVisible(false);
    }//GEN-LAST:event_tDatosUsuarioMouseClicked

    public static enum MessageType {
        CANCEL, OK
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.ButtonOutLine btnBuscarUsu;
    private com.anthony.swing.ButtonOutLine btnPnlCaja;
    private com.anthony.swing.Button cmdCancel;
    private com.anthony.swing.Button cmdOK;
    private com.anthony.swing.ButtonOutLine cmdOK3;
    private com.anthony.swing.Combobox combobox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlCaja;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlUsuario;
    private com.anthony.swing.RoundPanel roundPanel1;
    private javax.swing.JScrollPane spPago;
    private rojeru_san.complementos.TableMetro tDatosUsuario;
    private textfield.TextField textField2;
    private textfield.TextField textField3;
    private textfield.TextField textField4;
    private textfield.TextField textField5;
    // End of variables declaration//GEN-END:variables
}
class RoundBorder1 implements Border {

    /*        
        PARA QUITAR EL BORDE POR DEFECTO DE LA TABLA        
     */
    private int r;

    RoundBorder1(int r) {
        this.r = r;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, r, r);
    }
}