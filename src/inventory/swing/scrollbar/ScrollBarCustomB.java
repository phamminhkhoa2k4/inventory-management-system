

package inventory.swing.scrollbar;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustomB extends JScrollBar {

    public ScrollBarCustomB() {
        setUI(new ModernScrollBarUIB());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);
    }
}

