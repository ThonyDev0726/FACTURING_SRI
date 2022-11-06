package com.anthony.Licencias;

import com.anthony.login.*;
import com.anthony.Controller.*;
import com.anthony.MainLight.loading;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.dialog.MessageDialogLight;
import com.anthony.swing.clases.ComponentResizer;
import com.anthony.toast.Toast;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MainActivacionProducto extends javax.swing.JFrame {
    
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelActivacionSoporte activacionSoporte;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private ComponentResizer resizer;
    private JFrame fram;
    private boolean register = true;
    private int x;
    private int y;
    LICENCIA_DAO licencia_dao = new LICENCIA_DAO();
    
    public MainActivacionProducto() {
        initComponents();
        extender();
        init();
        cover.initMoving(this);
        setTitle("Facturing - Iniciar sesión");
        //para poder poder insertar nuestro Icono de Aplicacion
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png")));
        //initJFram(this);
    }
    
    public MainActivacionProducto(String cesionCerrada) {
        initComponents();
        extender();
        init();
        cover.initMoving(this);
        setTitle("Inicio de Sesión");
        //para poder poder insertar nuestro Icono de Aplicacion
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png")));
        Toast panel = new Toast(this, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Sesion cerrada, limite de tiempo finalizado");
        panel.showNotification();
        
    }

    // para poder mostrar la barra de tareas
    public void extender() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }
    
    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        activacionSoporte = new PanelActivacionSoporte();
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    activacionSoporte.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(activacionSoporte, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }
            
            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);  //  for smooth animation
        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(activacionSoporte, "width " + loginSize + "%, pos 1al 0 n 100%"); //  1al as 100%
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        cover.addEventLogout(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MessageDialogLight obj = new MessageDialogLight(MainActivacionProducto.this);
                obj.showMessage("¿ Deseas salir de Facturing ?", "");
                if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                    System.exit(0);
                }
            }
            
            public void mousePressed(MouseEvent e) {
            }
            
            public void mouseReleased(MouseEvent e) {
            }
            
            public void mouseEntered(MouseEvent e) {
            }
            
            public void mouseExited(MouseEvent e) {
            }
        });
        activacionSoporte.btnMetodo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                validarUsuarioBase();
                validarCodigo();
            }
        });
    }
    
    private void validarCodigo() {
        String codigo = activacionSoporte.addObtenerClave();
        if (codigo.length() == 0) {
            Toast toast = new Toast(this, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "No se puede dejar campos vacios!!");
            toast.showNotification();
        } else {
            Integer numClaves = licencia_dao.consultarLicencia(codigo);
            if (numClaves == 0) {
                Toast toast = new Toast(this, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "Codigo rechazado!!");
                toast.showNotification();
            } else if (numClaves == 1) {
                if (licencia_dao.updateEstado().equals("Licencia actualizada con exito!")) {                                       
                    Toast panel = new Toast(this, Toast.Type.SUCCESS, Toast.Location.TOP_CENTER, "Producto activado, disfrutalo!!");
                    panel.showNotification();                    
                    dispose();
                    Login l = new Login();
                    l.setVisible(true);
                } else if (licencia_dao.updateEstado().equals("No se actualizo la licencia!")) {
                    Toast toast = new Toast(this, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No se actualizo la licencia!!");
                    toast.showNotification();                                        
                }
            } else {
                Toast toast = new Toast(this, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Error al procesar tu peticion!!");
                toast.showNotification();
                activacionSoporte.limpiarClave();
            }
        }
    }
    
    public void initJFram(JFrame fram) {
        this.fram = fram;
        resizer = new ComponentResizer();
        resizer.setSnapSize(new Dimension(10, 10));
        resizer.setMinimumSize(new Dimension(900, 710));
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
    
    public static String letraNormal(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            //La primera letra en mayuscula y las demas en minuscula.
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainActivacionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainActivacionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainActivacionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainActivacionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainActivacionProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
