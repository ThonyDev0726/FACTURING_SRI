package com.anthony.MainLight;

import com.anthony.login.Login;
import java.awt.Toolkit;
import java.io.File;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author The Thøny
 */
public class loading extends javax.swing.JFrame implements Runnable {

    private Thread tiempo = null;
    Calendar fecha = Calendar.getInstance();
    int year = fecha.get(Calendar.YEAR);
    int mes = fecha.get(Calendar.MONTH) + 1;
    String MES = "";

    public loading() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png")));
        this.setTitle("Facturing");
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        tiempo = new Thread(this);
        tiempo.start();
        directorios();
    }

    private void directorios() {
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        File facturas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS");
        File facturasFirmadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/FIRMADAS");
        File facturasSinFirmadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/SIN FIRMAR");
        File facturasAprobadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/APROBADAS");
        File facturasRechazadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/FACTURAS/RECHAZADAS");
        File credito = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE CREDITO");
        File creditoFirmadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE CREDITO/FIRMADAS");
        File creditoSinFirmadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE CREDITO/SIN FIRMAR");
        File creditoAprobadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE CREDITO/APROBADAS");
        File creditoRechazadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE CREDITO/RECHAZADAS");
        File debito = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE DEBITO");
        File debitoFirmadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE DEBITO/FIRMADAS");
        File debitoSinFirmadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE DEBITO/SIN FIRMAR");
        File debitoAprobadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE DEBITO/APROBADAS");
        File debitoRechazadas = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/NOTAS DE DEBITO/RECHAZADAS");
        File repGenerales = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES");
        File repCli = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES/CLIENTES");
        File repEmp = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES/EMPLEADOS");
        File repProv = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES/PROVEEDORES");
        File repProd = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES/PRODUCTOS");
        File repUsu = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES/USUARIOS");
        File repPerm = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES/PERMISOS");
        File repEmpresa = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES GENERALES/EMPRESA");
        File repEstadisticos = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES ESTADISTICOS");
        File repEstCli = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES ESTADISTICOS/CLIENTES");
        File repEstEmp = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES ESTADISTICOS/EMPLEADOS");
        File repEstProv = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES ESTADISTICOS/PROVEEDORES");
        File repEstProd = new File("C:\\FACTURING_V1\\/" + year + "/" + MES + "/REPORTES ESTADISTICOS/PRODUCTOS");
        if (!facturas.exists()) {
            if (facturas.mkdirs()) {
                System.out.println("Directorio FACTURAS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!facturasFirmadas.exists()) {
            if (facturasFirmadas.mkdirs()) {
                System.out.println("Directorio FACTURAS FIRMADAS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!facturasSinFirmadas.exists()) {
            if (facturasSinFirmadas.mkdirs()) {
                System.out.println("Directorio FACTURAS SIN FIRMAR creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!facturasAprobadas.exists()) {
            if (facturasAprobadas.mkdirs()) {
                System.out.println("Directorio FACTURAS APROBADAS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!facturasRechazadas.exists()) {
            if (facturasRechazadas.mkdirs()) {
                System.out.println("Directorio FACTURAS RECHAZADA creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!credito.exists()) {
            if (credito.mkdirs()) {
                System.out.println("Directorio CREDITO creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!creditoFirmadas.exists()) {
            if (creditoFirmadas.mkdirs()) {
                System.out.println("Directorio CREDITO FIRMADAS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!creditoSinFirmadas.exists()) {
            if (creditoSinFirmadas.mkdirs()) {
                System.out.println("Directorio CREDITO SIN FIRMAR creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!creditoAprobadas.exists()) {
            if (creditoAprobadas.mkdirs()) {
                System.out.println("Directorio CREDITO APROBADAS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!creditoRechazadas.exists()) {
            if (creditoRechazadas.mkdirs()) {
                System.out.println("Directorio CREDITO RECHAZADA creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!debito.exists()) {
            if (debito.mkdirs()) {
                System.out.println("Directorio DEBITO creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!debitoFirmadas.exists()) {
            if (debitoFirmadas.mkdirs()) {
                System.out.println("Directorio DEBITO FIRMADAS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!debitoSinFirmadas.exists()) {
            if (debitoSinFirmadas.mkdirs()) {
                System.out.println("Directorio DEBITO SIN FIRMAR creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!debitoAprobadas.exists()) {
            if (debitoAprobadas.mkdirs()) {
                System.out.println("Directorio DEBITO APROBADAS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!debitoRechazadas.exists()) {
            if (debitoRechazadas.mkdirs()) {
                System.out.println("Directorio DEBITO RECHAZADA creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repGenerales.exists()) {
            if (repGenerales.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repCli.exists()) {
            if (repCli.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES CLIENTES creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repEmp.exists()) {
            if (repEmp.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES EMPLEADOS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repProv.exists()) {
            if (repProv.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES PROVEEDORES creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repProd.exists()) {
            if (repProd.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES PRODUCTOS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repUsu.exists()) {
            if (repUsu.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES USUARIOS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repPerm.exists()) {
            if (repPerm.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES PERMISOS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repEmpresa.exists()) {
            if (repEmpresa.mkdirs()) {
                System.out.println("Directorio REPORTES GENERALES EMPRESA creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repEstadisticos.exists()) {
            if (repEstadisticos.mkdirs()) {
                System.out.println("Directorio REPORTES ESTADISTICOS  creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repEstCli.exists()) {
            if (repEstCli.mkdirs()) {
                System.out.println("Directorio REPORTES ESTADISTICOS CLIENTES creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repEstEmp.exists()) {
            if (repEstEmp.mkdirs()) {
                System.out.println("Directorio REPORTES ESTADISTICOS EMPLEADOS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repEstProv.exists()) {
            if (repEstProv.mkdirs()) {
                System.out.println("Directorio REPORTES ESTADISTICOS PROVEEDORES creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
        if (!repEstProd.exists()) {
            if (repEstProd.mkdirs()) {
                System.out.println("Directorio REPORTES ESTADISTICOS PRODUCTOS creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        } else {
            System.out.println("El directorio ya esta creado!!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPricing1 = new com.anthony.componentsLigth.PanelPricing();
        lblInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar = new com.anthony.swing.ProgressBarCustom();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusableWindowState(false);
        setMaximumSize(new java.awt.Dimension(550, 300));
        setMinimumSize(new java.awt.Dimension(550, 300));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(550, 300));
        setResizable(false);

        lblInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblInfo.setText("Iniciando aplicación...");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Facturing V1.2  -  Sistema de contabilidad y facturacion");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel4.setText("facturing");

        jProgressBar.setBorder(null);
        jProgressBar.setForeground(new java.awt.Color(255, 255, 255));
        jProgressBar.setFocusable(false);
        jProgressBar.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png"))); // NOI18N

        javax.swing.GroupLayout panelPricing1Layout = new javax.swing.GroupLayout(panelPricing1);
        panelPricing1.setLayout(panelPricing1Layout);
        panelPricing1Layout.setHorizontalGroup(
            panelPricing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPricing1Layout.createSequentialGroup()
                .addGroup(panelPricing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPricing1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelPricing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInfo)
                            .addGroup(panelPricing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelPricing1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addGap(14, 14, 14))
        );
        panelPricing1Layout.setVerticalGroup(
            panelPricing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPricing1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panelPricing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addGap(136, 136, 136)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelPricing1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

    }//GEN-LAST:event_button1ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loading().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.anthony.swing.ProgressBarCustom jProgressBar;
    private javax.swing.JLabel lblInfo;
    private com.anthony.componentsLigth.PanelPricing panelPricing1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (tiempo != null) {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(25);
                    if (i == 5) {
                        lblInfo.setText("Iniciando aplicación.");
                    }
                    if (i == 10) {
                        lblInfo.setText("Iniciando aplicación..");
                    }
                    if (i == 15) {
                        lblInfo.setText("Iniciando aplicación...");
                    }
                    if (i == 20) {
                        lblInfo.setText("Conectando con la nube.");
                    }
                    if (i == 25) {
                        lblInfo.setText("Conectando con la nube..");
                    }
                    if (i == 30) {
                        lblInfo.setText("Conectando con la nube...");
                    }
                    if (i == 35) {
                        lblInfo.setText("Iniciando módulos.");
                    }
                    if (i == 40) {
                        lblInfo.setText("Iniciando módulos..");
                    }
                    if (i == 45) {
                        lblInfo.setText("Iniciando módulos...");
                    }
                    if (i == 50) {
                        lblInfo.setText("Iniciando módulos.");
                    }
                    if (i == 55) {
                        lblInfo.setText("Verificando directorios.");
                    }
                    if (i == 60) {
                        lblInfo.setText("Verificando directorios..");
                    }
                    if (i == 70) {
                        lblInfo.setText("Verificando directorios...");
                    }
                    if (i == 75) {
                        lblInfo.setText("Cargando.");
                    }
                    if (i == 80) {
                        lblInfo.setText("Cargando..");
                    }
                    if (i == 85) {
                        lblInfo.setText("Cargando...");
                    }
                    if (i == 90) {
                        lblInfo.setText("Abriendo Facturing V1.2.");
                    }
                    if (i == 95) {
                        lblInfo.setText("Abriendo Facturing V1.2..");
                    }
                    if (i == 100) {
                        lblInfo.setText("Abriendo Facturing V1.2...");
                        this.dispose();
                        Login login = new Login();
                        login.setVisible(true);
                    }
                    jProgressBar.setValue(i);
                }
                break;
            } catch (InterruptedException ex) {
                Logger.getLogger(loading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
