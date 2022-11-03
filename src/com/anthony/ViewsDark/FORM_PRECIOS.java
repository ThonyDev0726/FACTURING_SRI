package com.anthony.ViewsDark;

import com.anthony.modelCard.Model_Data;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author The Thøny
 *
 */
public class FORM_PRECIOS extends javax.swing.JPanel {

    public FORM_PRECIOS() {
        initComponents();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        precios01();
        precios02();
//        precios03();
    }

    private void precios01() {
        panelPricing.setPrice("PROOOO", 99.99);
        panelPricing.addItem(new Model_Data(true, "Lorem ipsum"));
        panelPricing.addItem(new Model_Data(true, "Dolor sit amet"));
        panelPricing.addItem(new Model_Data(true, "Consectetuer"));
        panelPricing.addItem(new Model_Data(true, "Adipiscing elit"));
        panelPricing.addItem(new Model_Data(false, "Sit amet"));
        roundPanel.setVisible(false);
        roundPanel1.setVisible(false);
        roundPanel2.setVisible(false);
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

    private void precios02() {
        panelPricing3.setPrice("PROOOO", 99.99);
        panelPricing3.addItem(new Model_Data(true, "Lorem ipsum"));
        panelPricing3.addItem(new Model_Data(true, "Dolor sit amet"));
        panelPricing3.addItem(new Model_Data(true, "Consectetuer"));
        panelPricing3.addItem(new Model_Data(true, "Adipiscing elit"));
        panelPricing3.addItem(new Model_Data(false, "Sit amet"));
        roundPanel1.setVisible(false);
        roundPanel1.setVisible(false);
        roundPanel2.setVisible(false);
        panelPricing.addEventBuy(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                panelPricing2.setVisible(false);
                roundPanel.setVisible(true);
            }
        });
        panelPricing4.setPrice("PROOOO", 99.99);
        panelPricing4.addItem(new Model_Data(true, "Lorem ipsum"));
        panelPricing4.addItem(new Model_Data(true, "Dolor sit amet"));
        panelPricing4.addItem(new Model_Data(true, "Consectetuer"));
        panelPricing4.addItem(new Model_Data(true, "Adipiscing elit"));
        panelPricing4.addItem(new Model_Data(false, "Sit amet"));
        panelPricing4.addEventBuy(new ActionListener() {

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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelPricing2 = new com.anthony.componentPrecios.PanelPricing();
        panelPricing = new com.anthony.componentPrecios.PanelPricing();
        roundPanel = new com.anthony.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        button1 = new com.anthony.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        button2 = new com.anthony.swing.Button();
        panelPricing1 = new com.anthony.componentPrecios.PanelPricing();
        panelPricing3 = new com.anthony.componentPrecios.PanelPricing();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        button3 = new com.anthony.swing.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        button4 = new com.anthony.swing.Button();
        panelPricing4 = new com.anthony.componentPrecios.PanelPricing();
        panelPricing5 = new com.anthony.componentPrecios.PanelPricing();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        button5 = new com.anthony.swing.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        button6 = new com.anthony.swing.Button();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PLANES DE FACTURING");

        jScrollPane1.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane1.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));

        panelPricing2.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing2.setColor1(new java.awt.Color(29, 49, 59));
        panelPricing2.setColor2(new java.awt.Color(35, 85, 58));

        panelPricing.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing.setColor1(new java.awt.Color(29, 39, 59));
        panelPricing.setColor2(new java.awt.Color(35, 51, 85));

        roundPanel.setBackground(new java.awt.Color(32, 32, 32));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("TITULO");

        button1.setBackground(new java.awt.Color(0, 102, 153));
        button1.setText("COMPRAR");
        button1.setFocusPainted(false);
        button1.setFocusable(false);

        jScrollPane2.setBorder(null);

        jTextArea1.setBackground(new java.awt.Color(32, 32, 32));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addGroup(roundPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelPricing1.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing1.setColor1(new java.awt.Color(29, 39, 59));
        panelPricing1.setColor2(new java.awt.Color(35, 51, 85));

        panelPricing3.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing3.setColor1(new java.awt.Color(29, 49, 59));
        panelPricing3.setColor2(new java.awt.Color(35, 85, 58));

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("TITULO");

        button3.setBackground(new java.awt.Color(0, 102, 153));
        button3.setText("COMPRAR");
        button3.setFocusPainted(false);
        button3.setFocusable(false);

        jScrollPane3.setBorder(null);

        jTextArea2.setBackground(new java.awt.Color(32, 32, 32));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        button4.setBackground(new java.awt.Color(51, 0, 0));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelPricing4.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing4.setColor1(new java.awt.Color(29, 39, 59));
        panelPricing4.setColor2(new java.awt.Color(35, 51, 85));

        panelPricing5.setBackground(new java.awt.Color(102, 204, 0));
        panelPricing5.setColor1(new java.awt.Color(29, 49, 59));
        panelPricing5.setColor2(new java.awt.Color(35, 85, 58));

        roundPanel2.setBackground(new java.awt.Color(32, 32, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("TITULO");

        button5.setBackground(new java.awt.Color(0, 102, 153));
        button5.setText("COMPRAR");
        button5.setFocusPainted(false);
        button5.setFocusable(false);

        jScrollPane4.setBorder(null);

        jTextArea3.setBackground(new java.awt.Color(32, 32, 32));
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        button6.setBackground(new java.awt.Color(51, 0, 0));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(panelPricing, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPricing2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(panelPricing1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPricing3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(panelPricing4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPricing5, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelPricing2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(panelPricing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelPricing3, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(panelPricing1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelPricing5, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(panelPricing4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(100, 100, 100))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        panelPricing2.setVisible(true);
        panelPricing.setVisible(true);
        roundPanel.setVisible(false);
    }//GEN-LAST:event_button2ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button4ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button button1;
    private com.anthony.swing.Button button2;
    private com.anthony.swing.Button button3;
    private com.anthony.swing.Button button4;
    private com.anthony.swing.Button button5;
    private com.anthony.swing.Button button6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private com.anthony.componentPrecios.PanelPricing panelPricing;
    private com.anthony.componentPrecios.PanelPricing panelPricing1;
    private com.anthony.componentPrecios.PanelPricing panelPricing2;
    private com.anthony.componentPrecios.PanelPricing panelPricing3;
    private com.anthony.componentPrecios.PanelPricing panelPricing4;
    private com.anthony.componentPrecios.PanelPricing panelPricing5;
    private com.anthony.swing.RoundPanel roundPanel;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
