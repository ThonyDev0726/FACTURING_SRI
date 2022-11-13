package com.anthony.componentsDark;

import com.anthony.Models.*;
import com.anthony.swing.MenuAnimationDark;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import com.anthony.eventDark.EventShowPopupMenuDark;
import com.anthony.eventDark.EventMenuSelectedDark;
import com.anthony.eventDark.EventMenuDark;
import com.anthony.modelComponets.ModelMenuDark;

public class MenuDark extends javax.swing.JPanel {

    USUARIO usu = new USUARIO();
//    private JButton cmdLogOut;
//

    public MenuDark(USUARIO usu) {
        initComponents();
        this.usu = usu;
        setOpaque(false);
        setRequestFocusEnabled(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
        profile1.init(usu.getUSU_USUARIO(), usu.getUSU_PARAMETRO());
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelectedDark event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenuDark eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    public void addEventLogout(ActionListener event) {
        btnUsuario.addActionListener(event);
    }
    private final MigLayout layout;
    private EventMenuSelectedDark event;
    private EventShowPopupMenuDark eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public MenuDark() {
        initComponents();
        setOpaque(false);
        setRequestFocusEnabled(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        addMenu(new ModelMenuDark(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoHome.png")), "Inicio"));
        addMenu(new ModelMenuDark(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoParametros.png")), "Parametros", "Clientes", "Empleados", "Proveedores", "Productos"));
        addMenu(new ModelMenuDark(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoFacturacion.png")), "Facturación", "Generar factura", "Generar Nt. de credito", "Generar Nt. de debito"));
        addMenu(new ModelMenuDark(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoResporte.png")), "Reportes", "Reportes generales", "Reportes estadísticos", "Revs. facturas", "Revs. Nt. Credito", "Revs. Debito"));
        addMenu(new ModelMenuDark(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoSeguridad.png")), "Seguridad", "Usuarios", "Permisos de usuarios", "Cambiar contraseña"));
        addMenu(new ModelMenuDark(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoInformacion.png")), "Ayuda", "Manual de usuario", "Soporte técnico"));
        addMenu(new ModelMenuDark(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoAjustes.png")), "Configuracion", "Ajustes", "Empresa", "Perfil"));
    }

    public void grad() {
        Graphics grphcs = null;
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        GradientPaint gra = new GradientPaint(0, 0, new Color(32, 32, 32), getWidth(), 0, new Color(32, 32, 32));

        g2.setPaint(gra);
    }

    private void addMenu(ModelMenuDark menu) {
        panel.add(new MenuItemDark(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenuDark getEventMenu() {
        return new EventMenuDark() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimationDark(layout, com).openMenu();
                        } else {
                            new MenuAnimationDark(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItemDark item = (MenuItemDark) com;
            if (item.isOpen()) {
                new MenuAnimationDark(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

//    private void createButtonLogout() {
//        cmdLogOut = new Button();
//        cmdLogOut.setIcon(new ImageIcon(getClass().getResource("/com/anthony/icon/home.png")));
//        cmdLogOut.setBorder(new EmptyBorder(0, 12, 0, 12));
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        btnUsuario = new com.anthony.swing.ButtonGradient();
        profile1 = new com.anthony.componentsDark.Profile();

        setBackground(new java.awt.Color(32, 32, 32));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(42, 53, 66)));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setOpaque(false);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        btnUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/exit.png"))); // NOI18N
        btnUsuario.setText("CERRAR SESION");
        btnUsuario.setToolTipText("Cerrar sesion");
        btnUsuario.setColor1(new java.awt.Color(255, 120, 120));
        btnUsuario.setColor2(new java.awt.Color(255, 120, 120));
        btnUsuario.setFocusable(false);
        btnUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnUsuario.setIconTextGap(35);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        GradientPaint gra = new GradientPaint(0, 0, new Color(2105376), getWidth(), 0, new Color(2105376));

        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.ButtonGradient btnUsuario;
    private javax.swing.JPanel panel;
    private com.anthony.componentsDark.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
