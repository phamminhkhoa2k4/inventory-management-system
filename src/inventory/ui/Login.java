package inventory.ui;

import inventory.component.Menu;
import inventory.database.ConnectionDB;
import inventory.model.ModelUser;
import java.awt.Color;
import java.sql.*;
import javaproject.ManagerMenu;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Login extends javax.swing.JFrame {

//    public ModelUser getData() {
//        return data;
//    }

//    private ModelUser data;
    private Animator animatorLogin;
    private Animator animatorBody;
    private boolean signIn;

    public Login() {
        initComponents();
        getContentPane().setBackground(new Color(245, 245, 245));
        TimingTarget targetLogin = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (signIn) {
                    background1.setAnimate(fraction);
                } else {
                    background1.setAnimate(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (signIn) {
                    panelLogin.setVisible(false);
                    background1.setShowPaint(true);
                    panelBody.setAlpha(0);
                    panelBody.setVisible(true);
                    animatorBody.start();
                } else {
                    enableLogin(true);
                    txtUser.grabFocus();
                }
            }
        };
        TimingTarget targetBody = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (signIn) {
                    panelBody.setAlpha(fraction);
                } else {
                    panelBody.setAlpha(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (signIn == false) {
                    panelBody.setVisible(false);
                    background1.setShowPaint(false);
                    background1.setAnimate(1);
                    panelLogin.setVisible(true);
                    animatorLogin.start();
                }
            }
        };
        animatorLogin = new Animator(1500, targetLogin);
        animatorBody = new Animator(500, targetBody);
        animatorLogin.setResolution(0);
        animatorBody.setResolution(0);
    }


    
//    setText Frame orther
    public void Menu(ModelUser user){
        Menu menu = new Menu(user);
        menu.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new inventory.swing.Background();
        panelLogin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmdSignIn = new inventory.swing.Button();
        txtUser = new inventory.swing.TextField();
        txtPass = new inventory.swing.PasswordField();
        panelBody = new inventory.swing.PanelTransparent();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        imageAvatar1 = new inventory.swing.ImageAvatar();
        UsernameL = new javax.swing.JLabel();
        PositionL = new javax.swing.JLabel();
        btnCancel = new inventory.swing.Button();
        btnContinue = new inventory.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background1.setLayout(new java.awt.CardLayout());

        panelLogin.setOpaque(false);

        jPanel1.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/Big-logo.png"))); // NOI18N

        cmdSignIn.setText("Sign In ");
        cmdSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSignInActionPerformed(evt);
            }
        });

        txtUser.setBackground(new java.awt.Color(245, 245, 245));
        txtUser.setLabelText("Username");

        txtPass.setBackground(new java.awt.Color(245, 245, 245));
        txtPass.setLabelText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdSignIn, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(cmdSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        background1.add(panelLogin, "card2");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        imageAvatar1.setForeground(new java.awt.Color(51, 51, 51));
        imageAvatar1.setBorderSize(4);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/avatar.jpg"))); // NOI18N

        UsernameL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UsernameL.setForeground(new java.awt.Color(255, 255, 255));
        UsernameL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PositionL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PositionL.setForeground(new java.awt.Color(255, 255, 255));
        PositionL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnContinue.setText("Continue");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PositionL, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UsernameL, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UsernameL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PositionL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(386, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(353, 353, 353))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBodyLayout = new javax.swing.GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBodyLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBodyLayout.setVerticalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        background1.add(panelBody, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSignInActionPerformed
        ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
//            String sql = "Select * From Profile Where username='" + txtUser.getText() + "' and Password='" + txtPass.getText()+ "'";
//            String sql = "Select * From Account Where Username =? and Password =? and Position=?";
            String sql = "Select * From account Where Username =? and Password =? ";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtUser.getText());
            pst.setString(2, txtPass.getText());


            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                    
                    String position = rs.getString("Position");
                    PositionL.setText(position);
                    String username = rs.getString("Username");
                    UsernameL.setText(username);
                    ImageIcon avatar;
                    if(rs.getObject("Avatar") != null){
                    avatar = (ImageIcon) rs.getObject("Avatar");
                    imageAvatar1.setIcon(avatar);
                    }else{
                        avatar = new ImageIcon(getClass().getResource("/inventory/icon/questionMark.png"));
                        imageAvatar1.setIcon(avatar);   
                    }
                    // store on modelUser
                    ModelUser user = new ModelUser(username,avatar, position );
                    Menu(user);

                if (!animatorLogin.isRunning()) {
                    signIn = true;
                    String userna = txtUser.getText().trim();
                    String pass = String.valueOf(txtPass.getPassword());
                    
                    boolean action = true;
                    if (userna.equals("")) {
                        txtUser.setHelperText("Please input user name");
                        txtUser.grabFocus();
                        action = false;
                    }
                    if (pass.equals("")) {
                        txtPass.setHelperText("Please input password");
                        if (action) {
                            txtPass.grabFocus();
                        }
                        action = false;
                    }
                    if (action) {
                        animatorLogin.start();
                        enableLogin(false);
                       
                    }
                }            
            } else {

                String userna = txtUser.getText().trim();
                String pass = String.valueOf(txtPass.getPassword());
                boolean action = true;
                if (userna.equals("")) {
                    txtUser.setHelperText("Please input user name");
                    txtUser.grabFocus();
                    action = false;
                } else {
                    txtUser.setHelperText("");
                }
                if (pass.equals("")) {
                    txtPass.setHelperText("Please input password");
                    if (action) {
                        txtPass.grabFocus();
                    }
                    action = false;
                } else {
                    txtPass.setHelperText("");
                    JOptionPane.showMessageDialog(this, "sai roi");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
            System.out.println(ex.toString());
            System.out.println("loi");
        }


    }//GEN-LAST:event_cmdSignInActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        signIn = false;
        clearLogin();
        animatorBody.start();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        {
                if (PositionL.getText().equals("Administration")) {
                    Main dashboard = new Main();
                    dashboard.show();
                    dispose();
                } else if (PositionL.getText().equals("Inventory Manager")) {
                    ManagerMenu inven = new ManagerMenu();
                    inven.show();
                    dispose();
                }
            }

      

    }//GEN-LAST:event_btnContinueActionPerformed

    private void enableLogin(boolean action) {
        txtUser.setEditable(action);
        txtPass.setEditable(action);
        cmdSignIn.setEnabled(action);
    }

    public void clearLogin() {
        txtUser.setText("");
        txtPass.setText("");
        txtUser.setHelperText("");
        txtPass.setHelperText("");
    }


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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new inventory.ui.Splash(null, true).setVisible(true);
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PositionL;
    private javax.swing.JLabel UsernameL;
    private inventory.swing.Background background1;
    private inventory.swing.Button btnCancel;
    private inventory.swing.Button btnContinue;
    private inventory.swing.Button cmdSignIn;
    private inventory.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private inventory.swing.PanelTransparent panelBody;
    private javax.swing.JPanel panelLogin;
    private inventory.swing.PasswordField txtPass;
    private inventory.swing.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
