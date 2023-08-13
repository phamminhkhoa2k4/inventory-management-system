package inventory.component;

import inventory.event.EventMenu;
import inventory.model.ModelUser;
import inventory.swing.ButtonMenu;
import inventory.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    private EventMenu event;
    private ModelUser user;    
    public static String globalUsername;
    public static String globalPosition; 
    public static ImageIcon globalAvatar; 


    public Menu() {
       
        
        
        
//        
        initComponents();
        setOpaque(false);
        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setForeground(new Color(130, 130, 130, 100));
        jScrollPane1.setVerticalScrollBar(sb);
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        UsernameM.setText(globalUsername);
        PositionM.setText(globalPosition);
        imageAvatar1.setIcon(globalAvatar);


        
    }

    
    
    
    public Menu(ModelUser user) {
         this.user = user;
        String username =  user.getUserName();
        String position = user.getPosition();
        ImageIcon avatar =   user.getAvatar();
        System.out.println(username); 
        System.out.println("pham minh khoa");
        System.out.println(position);
        globalUsername = username;
        globalPosition = position;
        globalAvatar = avatar;
//        UsernameM.setText(username);
//        PositionM.setText(position);
//        imageAvatar1.setIcon(avatar);
        System.out.println("Tmy and Nyen");

    }
    public void initMenu(EventMenu event) {
        this.event = event;
        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/3.png")), "Account", 1);
        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/2.png")), "Profile", 2);
        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/5.png")), "Products", 3);
        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/6.png")), "Product List", 4);
        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/4.png")), "Synthesis Report", 5);
//        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/7.png")), "", 6);
        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/8.png")), "Setting", 6);
        addEmpty();
        addMenu(new ImageIcon(getClass().getResource("/inventory/icon/logout.png")), "Logout", 7);
    }

    private void addEmpty() {
        panelMenu.add(new JLabel(), "push");
    }

    private void addMenu(Icon icon, String text, int index) {
        ButtonMenu menu = new ButtonMenu();
        menu.setIcon(icon);
        menu.setText("  " + text);
        panelMenu.add(menu);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(index);
                setSelected(menu);
            }
        });
    }

    private void setSelected(ButtonMenu menu) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof ButtonMenu) {
                ButtonMenu b = (ButtonMenu) com;
                b.setSelected(false);
            }
        }
        menu.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel3 = new inventory.swing.RoundPanel();
        imageAvatar1 = new inventory.swing.ImageAvatar();
        UsernameM = new javax.swing.JLabel();
        PositionM = new javax.swing.JLabel();
        roundPanel4 = new inventory.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel();

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));

        imageAvatar1.setForeground(new java.awt.Color(255, 255, 255));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/avatar.jpg"))); // NOI18N

        UsernameM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UsernameM.setForeground(new java.awt.Color(255, 255, 255));
        UsernameM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UsernameM.setText("Pham Minh Khoa");

        PositionM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PositionM.setForeground(new java.awt.Color(255, 255, 255));
        PositionM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PositionM.setText("Aministration");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UsernameM, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(PositionM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(UsernameM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PositionM, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelMenu.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelMenu);

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PositionM;
    private javax.swing.JLabel UsernameM;
    private inventory.swing.ImageAvatar imageAvatar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMenu;
    private inventory.swing.RoundPanel roundPanel3;
    private inventory.swing.RoundPanel roundPanel4;
    // End of variables declaration//GEN-END:variables
}
