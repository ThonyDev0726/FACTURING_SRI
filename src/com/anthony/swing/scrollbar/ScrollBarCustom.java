package com.anthony.swing.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
//        setForeground(new Color(65, 165, 225));
        setForeground(new Color(63,81,102));
        setUnitIncrement(20);
        setOpaque(false);
    }
}
