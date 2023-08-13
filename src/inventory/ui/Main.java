package inventory.ui;

import inventory.event.EventMenu;
import inventory.form.AccountPage;
import inventory.form.ProductListPage;
import inventory.form.ProductsPage;
import inventory.form.ProfilePage;
import inventory.form.SettingPage;
import inventory.form.SynthesisReportPage;
import inventory.message.MessageDiaLog;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import inventory.message.MessageDiaLog;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        EventMenu event = new EventMenu() {
            @Override
            public void selected(int index) {
                if (index == 1) {
                    showForm(new AccountPage());
                    System.out.println("Account");
                } else if (index == 2) {
                    showForm(new ProfilePage());
                    System.out.println("Profile");
                } else if (index == 3) {
                    showForm(new ProductsPage());
                    System.out.println("Products");
                } else if (index == 4) {
                    showForm(new ProductListPage());
                    System.out.println("Product List");
                } else if (index == 5) {
                    showForm(new SynthesisReportPage());
                    System.out.println("Synthesis Report");
                } else if (index == 6) {
                    showForm(new SettingPage());
                    System.out.println("setting");
                } else if (index == 7) {
//                    MessageDiaLog obj = new MessageDiaLog(this);
//                    obj.showMessage("Delete your account ?", "All data will lose if you press ok button\nYou can restore any time within 30 days start from now.");
//                    if (obj.getMessageType() == MessageDialog.MessageType.OK) {
//                        System.out.println("User click ok");
//                    } else {
//                        System.out.println("User click cancel");
//                    }

                    Login SI = new Login();
                    SI.show();
                    dispose();
                    System.out.println("logout");
                }
            }
        };
        menu1.initMenu(event);
        showForm(new AccountPage());
        

    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel2 = new inventory.swing.RoundPanel();
        header1 = new inventory.component.Header();
        menu1 = new inventory.component.Menu();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel2.setBackground(new java.awt.Color(21, 21, 21));

        body.setBackground(new java.awt.Color(21, 21, 21));
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 1164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new inventory.ui.Splash(null, true).setVisible(true);
//                new Login().setVisible(true);
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private inventory.component.Header header1;
    private inventory.component.Menu menu1;
    private inventory.swing.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
