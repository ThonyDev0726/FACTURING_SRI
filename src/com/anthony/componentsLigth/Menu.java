package com.anthony.componentsLigth;

import com.anthony.Models.USUARIO;
import com.anthony.event.EventMenu;
import com.anthony.event.EventMenuSelected;
import com.anthony.event.EventShowPopupMenu;
import com.anthony.modelComponets.ModelMenu;
import com.anthony.swing.MenuAnimation;
import com.anthony.swing.scrollbar.ScrollBarCustomClaro;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    USUARIO usu = new USUARIO();

    public Menu(USUARIO usu) {
        initComponents();
        this.usu = usu;
        setOpaque(false);
        setRequestFocusEnabled(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustomClaro());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
        profile1.init(usu.getUSU_USUARIO(), usu.getUSU_PARAMETRO());
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    public void addEventLogout(ActionListener event) {
        btnUsuario.addActionListener(event);
    }
    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        setRequestFocusEnabled(false);
        sp.getViewport().setOpaque(false);        
        sp.setVerticalScrollBar(new ScrollBarCustomClaro());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoHome-light.png")), "Inicio"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoParametros-light.png")), "Parametros", "Clientes", "Empleados", "Proveedores", "Productos"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoFacturacion-light.png")), "Facturación", "Generar factura", "Generar Nt. de credito", "Generar Nt. de debito"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoReporte-light.png")), "Reportes", "Reportes generales","Reportes estadísticos", "Revs. facturas","Revs. Nt. Credito","Revs. Debito"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoSeguridad-light.png")), "Seguridad", "Usuarios", "Permisos de usuarios", "Cambiar contraseña"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoInformacion-light.png")), "Ayuda", "Manual de usuario", "Soporte técnico"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/anthony/icons/iconoAjustes-light.png")), "Configuracion", "Ajustes","Empresa", "Perfil"));
    }

    public void grad() {
        Graphics grphcs = null;
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        GradientPaint gra = new GradientPaint(0, 0, new Color(221,234,255), getWidth(), 0, new Color(221,234,255));

        g2.setPaint(gra);
    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItemLigth(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
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
            MenuItemLigth item = (MenuItemLigth) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        btnUsuario = new com.anthony.swing.ButtonGradient();
        profile1 = new com.anthony.componentsLigth.Profile();

        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profile1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
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
        GradientPaint gra = new GradientPaint(0, 0, new Color(255, 255, 255), getWidth(), 0, new Color(255, 255, 255));

        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.ButtonGradient btnUsuario;
    private javax.swing.JPanel panel;
    private com.anthony.componentsLigth.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
