package com.anthony.componentsLigth;

import com.anthony.componentsDark.*;
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

public class MessageDialogLight extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass_notification glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;

    public MessageDialogLight(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
       
        bordes();
    }

    public void bordes() {
        ScrollBarCustom sbh = new ScrollBarCustom();
        
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
        lbTitle.setText(title);
        txt.setText(message);
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
        lbTitle = new javax.swing.JLabel();
        cmdOK = new com.anthony.swing.Button();
        cmdCancel = new com.anthony.swing.Button();
        txt = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(0, 0, 0));
        lbTitle.setText("TITULO DEL MENSAJE");

        cmdOK.setBackground(new java.awt.Color(3, 179, 255));
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setText("ACEPTAR");
        cmdOK.setFocusable(false);
        cmdOK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });

        cmdCancel.setBackground(new java.awt.Color(204, 204, 204));
        cmdCancel.setForeground(new java.awt.Color(65, 51, 51));
        cmdCancel.setText("CANCELAR");
        cmdCancel.setFocusable(false);
        cmdCancel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        txt.setEditable(false);
        txt.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt.setForeground(new java.awt.Color(112, 112, 112));
        txt.setText("Cuerpo del mensaje\nmensaje");
        txt.setFocusable(false);
        txt.setOpaque(false);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt)
                    .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTitle)
                .addGap(12, 12, 12)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.OK;
        closeMessage();
    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_cmdCancelActionPerformed

    public static enum MessageType {
        CANCEL, OK
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button cmdCancel;
    private com.anthony.swing.Button cmdOK;
    private javax.swing.JLabel lbTitle;
    private com.anthony.swing.RoundPanel roundPanel1;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}
