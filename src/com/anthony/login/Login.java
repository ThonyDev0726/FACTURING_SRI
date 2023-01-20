package com.anthony.login;

import com.anthony.Controller.*;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.componentsLigth.MessageDialogLight;
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
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Login extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginSoporte loginAndRegister;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private ComponentResizer resizer;
    private JFrame fram;
    private boolean register = true;
    private int x;
    private int y;
    Encriptador enc = new Encriptador();
    USUARIO_DAO usuDAO = new USUARIO_DAO();
    USUARIO usu = new USUARIO();
    String USU_NOMBRES;
    String USU_APELLIDOS;
    String USU_USUARIO;
    String USU_CLAVE;
    Integer ID_USUARIO_BASE = 0;
    int idUsu = 0;
    String usuClaveEncriptada;
    String usuEstado;
    String usuClaveDesencriptada;
    String claveInterfaz;
    Login login;

    public Login() {
        initComponents();
        extender();
        init();
        cover.initMoving(this);
        setTitle("Facturing - Iniciar sesión");
        //para poder poder insertar nuestro Icono de Aplicacion
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png")));
        //initJFram(this);
    }

    public Login(String cesionCerrada) {
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
        loginAndRegister = new PanelLoginSoporte();
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
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
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
        bg.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%"); //  1al as 100%
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
                MessageDialogLight obj = new MessageDialogLight(Login.this);
                obj.showMessage("¿Deseas salir de Facturing?", "Recuerda que los datos que se estan dentro de la aplicacion solo se podran crear, modificar o eliminar manualmete en esta aplicacion.");
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
        loginAndRegister.btnMetodo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                validarUsuarioBase();
                ingresar();
            }
        });
    }

    private void ingresar() {
        USU_USUARIO = loginAndRegister.addObtenerUsu();
        USU_CLAVE = loginAndRegister.addObtenerClave();
        ID_USUARIO_BASE = usuDAO.consultarUsuario(USU_USUARIO);
        idUsu = 0;
        if (loginAndRegister.addObtenerUsu().isEmpty() || loginAndRegister.addObtenerClave().isEmpty()) {
            Toast panel = new Toast(this, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Algun campo se encuentra vacio");
            panel.showNotification();
            loginAndRegister.limpiarFormulario();
        } else {
            if (ID_USUARIO_BASE == null || ID_USUARIO_BASE == 0) {
                Toast panel = new Toast(this, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "El usuario no esta registrado");
                panel.showNotification();
                loginAndRegister.limpiarFormulario();
            } else {
                idUsu = 0;
                idUsu = ID_USUARIO_BASE;
                usuClaveEncriptada = usuDAO.obtenerClave(idUsu);
                usuEstado = usuDAO.consultarEstado(idUsu);
                usuClaveDesencriptada = enc.desencriptar(usuClaveEncriptada);
                claveInterfaz = USU_CLAVE;
                if (usuEstado.equals("EN LINEA")) {
                    if (usuClaveDesencriptada.equals(claveInterfaz)) {
                        String usuCargo = usuDAO.consultarCargo(idUsu);
                        if (usuCargo.equals("Administrador")) {
                            dispose();
                            USUARIO usu1 = (USUARIO) usuDAO.list(idUsu);
                            USU_NOMBRES = usu1.getEMP_NOMBRES();
                            USU_APELLIDOS = usu1.getEMP_APELLIDOS();
                            StringTokenizer tonkenNombre = new StringTokenizer(USU_NOMBRES);
                            StringTokenizer tonkenApellido = new StringTokenizer(USU_APELLIDOS);
                            String PRIMER_NOMBRE = tonkenNombre.nextToken();
                            String PRIMER_APELLIDO = tonkenApellido.nextToken();
                            usu.setID_USUARIO(idUsu);
                            usu.setFK_EMPLEADO(usuDAO.ID_EMPLEADO(idUsu));
                            usu.setFK_SUCURSAL(usuDAO.ID_SUCURSAL(idUsu));
                            usu.setUSU_USUARIO(letraNormal(PRIMER_NOMBRE) + " " + letraNormal(PRIMER_APELLIDO));
                            usu.setUSU_CLAVE(loginAndRegister.addObtenerClave());
                            usu.setUSU_PARAMETRO(usuCargo);
                            
                            //DATOS EMPLEADO
                            USUARIO datEmp = (USUARIO) usuDAO.DATOS_EMPLEADO(usuDAO.ID_EMPLEADO(idUsu));
                            usu.setEMP_NOMBRES(datEmp.getEMP_NOMBRES());
                            usu.setEMP_APELLIDOS(datEmp.getEMP_APELLIDOS());
                            usu.setEMP_CEDULA(datEmp.getEMP_CEDULA());
                            usu.setEMP_EMAIL(datEmp.getEMP_EMAIL());
                            usu.setEMP_TELEFONO(datEmp.getEMP_TELEFONO());
                            usu.setEMP_DIRECCION(datEmp.getEMP_DIRECCION());
                            usu.setEMP_CREACION(datEmp.getEMP_CREACION());
                            //
                            System.out.println("=========== ACCESO CORRECTO ===========");
                            System.out.println("USUARIO: " + usu.getUSU_USUARIO());
                            System.out.println("ID_USUARIO: " + usu.getID_USUARIO());
                            System.out.println("CLAVE: " + usu.getUSU_CLAVE());
                            System.out.println("EMPLEADO: " + usu.getFK_EMPLEADO());
                            System.out.println("EMP_NOMBRES: " + usu1.getEMP_NOMBRES());
                            System.out.println("EMP_APELLIDOS: " + usu1.getEMP_APELLIDOS());
                            System.out.println("CEDULA: " + usu1.getEMP_CEDULA());
                            System.out.println("EMAIL: " + usu1.getEMP_EMAIL());
                            System.out.println("TELEFONO: " + usu1.getEMP_TELEFONO());
                            System.out.println("DIRECCION: " + usu.getEMP_DIRECCION());
                            System.out.println("CREACION: " + usu.getEMP_CREACION());
                            System.out.println("SUCURSAL: " + usu.getFK_SUCURSAL());
                            System.out.println("PARAMETRO: " + usu.getUSU_PARAMETRO());
                            System.out.println("=======================================");
                            MainAdministradorDark miMain = new MainAdministradorDark(usu);
                            miMain.setVisible(true);
                            Toast panel = new Toast(this, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Bienvenido " + letraNormal(PRIMER_NOMBRE) + " " + letraNormal(PRIMER_APELLIDO));
                            panel.showNotification();
                        } else if (usuCargo.equals("Cajero")) {
                            System.out.println("ESTAMOS EN LA PARTE DEL CAJERO");
                        }
                    } else {
                        Toast panel = new Toast(this, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "La clave es incorrecta");
                        panel.showNotification();
                        loginAndRegister.limpiarContra();
                    }
                } else if (usuEstado.equals("DE BAJA")) {

                    Toast panel = new Toast(this, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "El usuario esta bloqueado del sistema");
                    panel.showNotification();
                    loginAndRegister.limpiarFormulario();
                }
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
