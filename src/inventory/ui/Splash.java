
package inventory.ui;

import java.awt.Color;
import javax.swing.JDialog;


public class Splash extends javax.swing.JDialog {


     public Splash(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getContentPane().setBackground(new Color(221, 221, 221));
        //  To disable key Alt+F4 to close dialog
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new inventory.swing.CurvesPanel();
        jLabel1 = new javax.swing.JLabel();
        pro = new inventory.swing.progress.ProgressBarCustom();
        lbPercent = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/Big-logo.png"))); // NOI18N

        lbPercent.setBackground(new java.awt.Color(255, 255, 255));
        lbPercent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPercent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/1.png"))); // NOI18N

        javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);
        curvesPanel1.setLayout(curvesPanel1Layout);
        curvesPanel1Layout.setHorizontalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(lbPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doTask("0", 0);
                    doTask("1", 1);
                    doTask("2", 2);
                    doTask("3", 3);
                    doTask("4", 4);
                    doTask("5", 5);
                    doTask("6", 6);
                    doTask("7", 7);
                    doTask("8", 8);
                    doTask("9", 9);
                    doTask("10", 10);
                    doTask("11", 11);
                    doTask("12", 12);
                    doTask("13", 13);
                    doTask("14", 14);
                    doTask("15", 15);
                    doTask("16", 16);
                    doTask("17", 17);
                    doTask("18", 18);
                    doTask("19", 19);
                    doTask("20", 20);
                    doTask("21", 21);
                    doTask("22", 22);
                    doTask("23", 23);
                    doTask("24", 24);
                    doTask("25", 25);
                    doTask("26", 26);
                    doTask("27", 27);
                    doTask("28", 28);
                    doTask("29", 29);
                    doTask("30", 30);
                    doTask("31", 31);
                    doTask("32", 32);
                    doTask("33", 33);
                    doTask("34", 34);
                    doTask("35", 35);
                    doTask("36", 36);
                    doTask("37", 37);
                    doTask("38", 38);
                    doTask("39", 39);
                    doTask("41", 41);
                    doTask("42", 42);
                    doTask("43", 43);
                    doTask("44", 44);
                    doTask("45", 45);
                    doTask("46", 46);
                    doTask("47", 47);
                    doTask("48", 48);
                    doTask("49", 49);
                    doTask("50", 50);
                    doTask("51", 51);
                    doTask("52", 52);
                    doTask("53", 53);
                    doTask("54", 54);
                    doTask("55", 55);
                    doTask("56", 56);
                    doTask("57", 57);
                    doTask("58", 58);
                    doTask("59", 59);
                    doTask("60", 60);
                    doTask("61", 51);
                    doTask("62", 52);
                    doTask("63", 53);
                    doTask("64", 54);
                    doTask("65", 55);
                    doTask("66", 56);
                    doTask("67", 57);
                    doTask("68", 58);
                    doTask("69", 59);
                    doTask("70", 60);
                    doTask("71", 61);
                    doTask("72", 62);
                    doTask("73", 63);
                    doTask("74", 64);
                    doTask("75", 66);
                    doTask("77", 67);
                    doTask("78", 68);
                    doTask("79", 79);
                    doTask("80", 80);
                    doTask("81", 81);
                    doTask("82", 82);
                    doTask("83", 83);
                    doTask("84", 84);
                    doTask("85", 85);
                    doTask("86", 86);
                    doTask("87", 87);
                    doTask("88", 88);
                    doTask("89", 89);
                    doTask("90", 80);
                    doTask("91", 91);
                    doTask("92", 92);
                    doTask("93", 93);
                    doTask("94", 94);
                    doTask("95", 95);
                    doTask("96", 96);
                    doTask("97", 97);
                    doTask("98", 98);
                    doTask("99", 99);
                    doTask("Done ...", 100);
                    dispose();
                    curvesPanel1.stop();    //  To Stop animation
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened
    
                                

    private void doTask(String taskName, int progress) throws Exception {
        lbPercent.setText(taskName);
        Thread.sleep(70); //  For Test
        pro.setValue(progress);
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
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Splash dialog = new Splash(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private inventory.swing.CurvesPanel curvesPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbPercent;
    private inventory.swing.progress.ProgressBarCustom pro;
    // End of variables declaration//GEN-END:variables
}
