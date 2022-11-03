package com.anthony.ViewsDark;

import com.anthony.modelCard.Model_Data;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.sun.tools.javac.Main;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author The Thøny
 */
public class FormPrueba extends javax.swing.JPanel {

    public FormPrueba() {
        initComponents();
        panelPricing.setPrice("PROOOO", 99.99);
        panelPricing.addItem(new Model_Data(true, "Lorem ipsum"));
        panelPricing.addItem(new Model_Data(true, "Dolor sit amet"));
        panelPricing.addItem(new Model_Data(true, "Consectetuer"));
        panelPricing.addItem(new Model_Data(true, "Adipiscing elit"));
        panelPricing.addItem(new Model_Data(false, "Sit amet"));
        roundPanel.setVisible(false);
        panelPricing.addEventBuy(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                panelPricing2.setVisible(false);
                roundPanel.setVisible(true);
            }
        });
        panelPricing2.setPrice("PROOOO", 99.99);
        panelPricing2.addItem(new Model_Data(true, "Lorem ipsum"));
        panelPricing2.addItem(new Model_Data(true, "Dolor sit amet"));
        panelPricing2.addItem(new Model_Data(true, "Consectetuer"));
        panelPricing2.addItem(new Model_Data(true, "Adipiscing elit"));
        panelPricing2.addItem(new Model_Data(false, "Sit amet"));
        panelPricing2.addEventBuy(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                panelPricing.setVisible(false);
                roundPanel.setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPricing = new com.anthony.componentPrecios.PanelPricing();
        panelPricing2 = new com.anthony.componentPrecios.PanelPricing();
        roundPanel = new com.anthony.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        button1 = new com.anthony.swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        button2 = new com.anthony.swing.Button();

        setBackground(new java.awt.Color(22, 23, 23));

        panelPricing.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing.setColor1(new java.awt.Color(29, 39, 59));
        panelPricing.setColor2(new java.awt.Color(35, 51, 85));

        panelPricing2.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing2.setColor1(new java.awt.Color(29, 49, 59));
        panelPricing2.setColor2(new java.awt.Color(35, 85, 58));

        roundPanel.setBackground(new java.awt.Color(32, 32, 32));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("TITULO");

        button1.setBackground(new java.awt.Color(0, 102, 153));
        button1.setText("COMPRAR");
        button1.setFocusPainted(false);
        button1.setFocusable(false);

        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new java.awt.Color(32, 32, 32));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        button2.setBackground(new java.awt.Color(51, 0, 0));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanelLayout = new javax.swing.GroupLayout(roundPanel);
        roundPanel.setLayout(roundPanelLayout);
        roundPanelLayout.setHorizontalGroup(
            roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(roundPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanelLayout.setVerticalGroup(
            roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(panelPricing, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPricing2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {panelPricing, panelPricing2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelPricing2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(panelPricing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        panelPricing2.setVisible(true);
        panelPricing.setVisible(true);
        roundPanel.setVisible(false);
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button button1;
    private com.anthony.swing.Button button2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private com.anthony.componentPrecios.PanelPricing panelPricing;
    private com.anthony.componentPrecios.PanelPricing panelPricing2;
    private com.anthony.swing.RoundPanel roundPanel;
    // End of variables declaration//GEN-END:variables
}
