package inventory.swing.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustomA extends JScrollBar {

    public ScrollBarCustomA () {
        setUI(new ModernScrollBarUIA());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(new Color(30, 30, 30));
    }
}