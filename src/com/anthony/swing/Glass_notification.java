package com.anthony.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Glass_notification extends JComponent {

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }

    public Glass_notification() {
        setOpaque(false);
    }

    private float alpha = 0f;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(new Color(0, 0, 0, 170));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }
}
