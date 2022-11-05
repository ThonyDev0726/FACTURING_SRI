package com.anthony.MainLight;

import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.ViewsLight.*;
import com.anthony.event.*;
import com.anthony.componentsLigth.*;
import com.anthony.dialog.MessageDialogLight;
import com.anthony.login.Login;
import com.anthony.toast.Toast;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.*;

public class MainAdministrador extends javax.swing.JFrame {

    /*========VARIABLES========*/
    USUARIO usu = new USUARIO();
    PERMISOS_DAO perDao = new PERMISOS_DAO();
    Integer idUsuario;
    String permiso;
    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private MainAdministrador admin = this;
    Toast toast;

    /*========CONSTRUCTOR========*/
    public MainAdministrador() {
        initComponents();
        setTitle("Facturing");
        extender();
        init();
        initHeader();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png")));
    }

    public MainAdministrador(USUARIO usu) {
        initComponents();
        this.usu = usu;
        setTitle("Facturing");
        extender();
        init();
        initHeader();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png")));
    }


    /*========MAXIMIZAR LA VENTANA Y MOSTRAR BARRA DE TAREAS========*/
    public void extender() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }

    /*========INIT========*/
    private void initHeader() {
        header.initJFram(this);
        header.initNormalizar(this);
        header.setCerrarMenu();
    }

    /*========INIT========*/
    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu(usu);
        header = new Header();
        header.addEventoSalir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    MessageDialogLight obj = new MessageDialogLight(admin);
                    obj.showMessage("¿Deseas salir de Facturing?", "");
                    if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
//                        System.out.println("User click ok");
                        System.exit(0);
                    } else {
                        System.out.println("User click cancel");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Tranqui solo es para adminsitrador");
                }
            }
        });
        main = new MainForm();

        /*========INDENTIFICO CUAL PARTE DE MENU VA A SER SELCCIONADO Y MUESTRO LA FORMA*/
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
//                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (menuIndex == 0) {
                    System.out.println("========================================");
                    System.out.println("Estamos en el Inicio");
                    System.out.println("========================================");
                    main.showForm(new FORM_HOME());
                    header.addMenuEvent(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!animator.isRunning()) {
                                animator.start();
                            }
                            menu.setEnableMenu(false);
                            if (menu.isShowMenu()) {
                                menu.hideallMenu();//Ocultar menu----------
                            }
                        }
                    });
                    menu.hideallMenu();
                } else if (menuIndex == 1) {
                    System.out.println("========================================");
                    System.out.println("ESTAMOS EN EL MENU PARAMETROS");
                    if (subMenuIndex == 0) {
                        cliente();
                        System.out.println("ACCEDISMOS A CLIENTES");
                        System.out.println("========================================");
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 1) {
                        empleados();
                        System.out.println("ACCEDIMOS A EMPLEADOS");
                        System.out.println("========================================");
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 2) {
                        System.out.println("ACCEDIMOS A PROVEEDORES");
                        System.out.println("========================================");
                        proveedores();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 3) {
                        System.out.println("ACCEDIMOS A PRODUCTOS");
                        System.out.println("========================================");
                        productos();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    }
                } else if (menuIndex == 2) {
                    System.out.println("========================================");
                    System.out.println("ACCEDIMOS A FACTURACION");
                    if (subMenuIndex == 0) {
                        System.out.println("ACCEDIMOS A GENERAR FACTURA");
                        facturacion();
                        System.out.println("========================================");
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    }
                } else if (menuIndex == 3) {
                    System.out.println("========================================");
                    System.out.println("ESTAMOS EN REPORTES");
                    if (subMenuIndex == 0) {
                        System.out.println("ACCEDIMOS A REPORTES GENERALES");
                        System.out.println("========================================");
                        reportesGenerales();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 1) {
                        System.out.println("ACCEDIMOS A REPORTES ESTADISTICOS");
                        System.out.println("========================================");
                        reportesEstadisticos();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 2) {
                        System.out.println("ACCEDIMOS A REVISION DE FACTURAS");
                        System.out.println("========================================");
                        reportesRevFacturas();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 3) {
                        System.out.println("ACCEDIMOS A REVISION DE NOTAS DE CREDITO");
                        System.out.println("========================================");
                        reportesRevNotCredito();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 4) {
                        System.out.println("ACCEDIMOS A REVISION DE NOTAS DE DEBITO");
                        System.out.println("========================================");
                        reportesRevNotDebito();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    }
                } else if (menuIndex == 4) {
                    System.out.println("====================================");
                    System.out.println("ESTAMOS EN SEGURIDAD");
                    if (subMenuIndex == 0) {
                        System.out.println("ACCEDIMOS A USUARIOS");
                        System.out.println("====================================");
                        usuarios();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 1) {
                        System.out.println("ACCEDIMOS A PERMISOS DE USUARIOS");
                        System.out.println("====================================");
                        permUsuarios();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 2) {
                        System.out.println("ACCEDIMOS A CAMBIAR CLAVE");
                        System.out.println("====================================");
                        main.showForm(new FORM_CLAVE(usu,admin));
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    }
                } else if (menuIndex == 5) {
                    System.out.println("====================================");
                    System.out.println("ESTAMOS EN AYUDA");
                    if (subMenuIndex == 0) {
                        System.out.println("ACCEDIMOS A MANUAL DE USUARIO");
                        System.out.println("====================================");
                        main.showForm(new FORM_MANUAL());
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 1) {
                        System.out.println("ACCEDIMOS A SOPORTE TECNICO");
                        System.out.println("====================================");
                        main.showForm(new FORM_SOPORTE());
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    }
                } else if (menuIndex == 6) {
                    System.out.println("====================================");
                    System.out.println("ESTAMOS EN CONFIGURACION");
                    if (subMenuIndex == 0) {
                        System.out.println("ACCEDIMOS A AJUSTES");
                        System.out.println("====================================");
                        Ajustes();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 1) {
                        System.out.println("ACCEDIMOS A EMPRESA");
                        System.out.println("====================================");
                        Empresa();
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    } else if (subMenuIndex == 2) {
                        System.out.println("ACCEDIMOS A PERFIL");
                        System.out.println("====================================");
                        main.showForm(new FormPrueba());
                        header.addMenuEvent(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!animator.isRunning()) {
                                    animator.start();
                                }
                                menu.setEnableMenu(false);
                                if (menu.isShowMenu()) {
                                    menu.hideallMenu();//Ocultar menu----------
                                }
                            }
                        });
                        menu.hideallMenu();
                    }
                }
            }
        });

        /*=======INICIALIZAMOS EL POPUP=======*/
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItemLigth item = (MenuItemLigth) com;
                PopupMenu popup = new PopupMenu(MainAdministrador.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = MainAdministrador.this.getX() + 52;
                int y = MainAdministrador.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });

        /*=======INICIALIZAMOS MENU ITEM=======*/
        menu.initMenuItem();

        /*=======AÑADO AL bg EL HEADER, EL MENU Y EL MAIN CON LAS DIMENCIONES QUE ME GUSTEN=======*/
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");

        /*=======INICIALIZAMOS TIMING PARA DESARROLLAR LA LOGICCA DEL MENU Y LA INTERACCION CON EL POPUP=======*/
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }
        };

        /*========SE ANIMA Y SE LE DA LA LOGICA A LA INTERACCION DEL MENU Y EL HEADER========*/
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                    header.setCerrarMenu();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    header.setAbrirMenu();
                    menu.hideallMenu();
                }
            }
        });

        /*========INICIALIZAMOS LA INTERFAS DE HOME========*/
        header.minimizarEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(Frame.ICONIFIED);
            }
        });

        /*========BOTON HOME========*/
        header.addHomeEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainAdministradorDark m = new MainAdministradorDark(usu);
                m.setVisible(true);
                dispose();
            }
        });
        /*========METODO CERRAR SESION PARA ABRIR EL LOGIN========*/
        menu.addEventLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MessageDialogLight obj = new MessageDialogLight(admin);
                obj.showMessage("¿ " +usu.getUSU_USUARIO()+ " deseas cerrar sesion ?", "");
                if (obj.getMessageType() == MessageDialogLight.MessageType.OK) {
                    dispose();
                    Toast panel = new Toast(admin, Toast.Type.SUCCESS, Toast.Location.BOTTOM_RIGHT, "Anthony Pérez Palacios" + " cerraste sesion!!!");
                    panel.showNotification();
                    Login l = new Login();
                    l.setVisible(true);
                } else {
                    System.out.println("User click cancel");
                }
            }
        });
        /*========INICIALIZAMOS LA INTERFAS DE HOME========*/
//        main.showForm(new FormHomeAdministrador());
        main.showForm(new FORM_HOME());

    }

    /*Metodo para consultar si tiene permisos para acceder a los atributos del
    sistema a traves de la base de datos*/
    public void cliente() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_CLIENTE(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_CLIENTES(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Clientes!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void empleados() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_EMPLEADO(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_EMPLEADOS(admin, usu));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Empleados!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void proveedores() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_PROVEEDOR(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_PROVEEDORES(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Proveedores!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void productos() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_PRODUCTO(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_PRODUCTOS(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Productos!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void facturacion() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_FACTURACION(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_FACTURAR(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Facturacion!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void notaCredito() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_NOT_CREDITO(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_REV_CREDITO(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Nota credito!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void notaDebito() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_NOT_DEBITO(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_REV_DEBITO(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Nota credito!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void reportesGenerales() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_REP_GENERAL(idUsuario);
        if (permiso.equals("CON PERMISO")) {
//            main.showForm(new FORM_NOT_CREDITO(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en Rept. Generales!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void reportesEstadisticos() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_REP_ESTADISTICO(idUsuario);
        if (permiso.equals("CON PERMISO")) {
//            main.showForm(new FORM_NOT_CREDITO(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en Rept. Estadisticos!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void reportesRevFacturas() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_REV_FACTURAS(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_REV_FACTURA(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en  Rept. Facturas!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void reportesRevNotCredito() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_REV_NOT_CREDITO(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_REV_CREDITO(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en Rept. Nota credito!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void reportesRevNotDebito() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_REV_NOT_DEBITO(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_REV_DEBITO(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en Rept. Nota debito!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void usuarios() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_USUARIOS(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_USUARIOS(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Permisos!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void permUsuarios() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_PERMISOS(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_PERMISOS(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Permisos!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void Ajustes() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_AJUSTES(idUsuario);
        if (permiso.equals("CON PERMISO")) {
//            main.showForm(new FORM_PERMISOS(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Ajustes!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    public void Empresa() {
        idUsuario = usu.getID_USUARIO();
        permiso = perDao.ESTADO_EMPRESA(idUsuario);
        if (permiso.equals("CON PERMISO")) {
            main.showForm(new FORM_EMPRESA(usu, admin));
            toast = new Toast(admin, Toast.Type.INFO, Toast.Location.BOTTOM_RIGHT, "Se encuentra en la seccion Empresa!!");
            toast.showNotification();
        } else if (permiso.equals("SIN PERMISO")) {
            toast = new Toast(admin, Toast.Type.WARNING, Toast.Location.BOTTOM_RIGHT, "No tiene acceso a este modulo!!");
            toast.showNotification();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 700));
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(221, 234, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
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
            java.util.logging.Logger.getLogger(MainAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>       
        //</editor-fold>
        //</editor-fold>       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
