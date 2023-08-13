

package inventory.swing.scrollbar;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustomC extends JScrollBar {

    public ScrollBarCustomC() {
        setUI(new ModernScrollBarUIC());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(180, 180, 180));
        setBackground(Color.WHITE);
        setUnitIncrement(20);
    }
}

