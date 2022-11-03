package com.anthony.swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelPopup extends JPanel {

    public PanelPopup() {
        setOpaque(false);
        setRequestFocusEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
//        GradientPaint gra = new GradientPaint(0, 0, new Color(130, 188, 253), getWidth(), 0, new Color(93, 58, 196));
        GradientPaint gra = new GradientPaint(0, 0, new Color(161, 197, 245), getWidth(), 0, new Color(161, 197, 245));
        g2.setPaint(gra);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRect(8, 0, getSize().width - 8, getSize().height);
        int x[] = {0, 10, 10};
        int y[] = {20, 13, 27};
        g2.fillPolygon(x, y, x.length);
        super.paintComponent(grphcs);
    }
}
