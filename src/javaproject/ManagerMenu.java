/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaproject;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javaproject.ImportProductManager;
import javaproject.Statics;
import javax.swing.UIManager;

/**
 *
 * @author 84338
 */
public class ManagerMenu extends javax.swing.JFrame {

    /**
     * Creates new form InventoryManagerMenu
     */
    public void ManagerMenu() {
        initComponents();
        SetsizeIcon();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImportManagerPanel = new javax.swing.JPanel();
        IconImportManager = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        StaticsPanel = new javax.swing.JPanel();
        StaticsProduct = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        ImportManagerPanel.setBackground(new java.awt.Color(255, 255, 255));
        ImportManagerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ImportManagerPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ImportManagerPanelMousePressed(evt);
            }
        });

        IconImportManager.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaproject/ImportProductPic.jpg")));
        IconImportManager.setMaximumSize(new java.awt.Dimension(100, 100));
        IconImportManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IconImportManagerActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Import Product Manager");

        javax.swing.GroupLayout ImportManagerPanelLayout = new javax.swing.GroupLayout(ImportManagerPanel);
        ImportManagerPanel.setLayout(ImportManagerPanelLayout);
        ImportManagerPanelLayout.setHorizontalGroup(
            ImportManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImportManagerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconImportManager, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ImportManagerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(64, 64, 64))
        );
        ImportManagerPanelLayout.setVerticalGroup(
            ImportManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImportManagerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconImportManager, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        StaticsPanel.setBackground(new java.awt.Color(255, 255, 255));
        StaticsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                StaticsPanelMousePressed(evt);
            }
        });

        StaticsProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaproject/StaticsProductsPic.jpg")));
        StaticsProduct.setMaximumSize(new java.awt.Dimension(100, 100));
        StaticsProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaticsProductActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Statics Product");

        javax.swing.GroupLayout StaticsPanelLayout = new javax.swing.GroupLayout(StaticsPanel);
        StaticsPanel.setLayout(StaticsPanelLayout);
        StaticsPanelLayout.setHorizontalGroup(
            StaticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaticsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StaticsProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaticsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(116, 116, 116))
        );
        StaticsPanelLayout.setVerticalGroup(
            StaticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaticsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StaticsProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Inventory Manger");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(265, 265, 265))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ImportManagerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(StaticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StaticsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ImportManagerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IconImportManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IconImportManagerActionPerformed
        ImportProductManager phanloaiFrame = new ImportProductManager();
        phanloaiFrame.setVisible(true);
        dispose();
    }//GEN-LAST:event_IconImportManagerActionPerformed

    private void StaticsProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaticsProductActionPerformed
        Statics StaticsFrame = new Statics();
        StaticsFrame.setVisible(true);

        dispose();
    }//GEN-LAST:event_StaticsProductActionPerformed

    private void ImportManagerPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportManagerPanelMouseClicked

    }//GEN-LAST:event_ImportManagerPanelMouseClicked

    private void ImportManagerPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportManagerPanelMousePressed
        ImportProductManager phanloaiFrame = new ImportProductManager();
        phanloaiFrame.setVisible(true);
        dispose();
    }//GEN-LAST:event_ImportManagerPanelMousePressed

    private void StaticsPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaticsPanelMousePressed
        Statics StaticsFrame = new Statics();
        StaticsFrame.setVisible(true);

        dispose();
    }//GEN-LAST:event_StaticsPanelMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(ManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManagerMenu importDetailFrame = new ManagerMenu();
        importDetailFrame.ManagerMenu();
        importDetailFrame.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IconImportManager;
    private javax.swing.JPanel ImportManagerPanel;
    private javax.swing.JPanel StaticsPanel;
    private javax.swing.JButton StaticsProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    private void SetsizeIcon() {
        Icon currentIcon = IconImportManager.getIcon();
            // Kiểm tra xem biểu tượng có phải là ImageIcon không
        if (currentIcon instanceof ImageIcon) {
            // Chuyển đổi biểu tượng thành ImageIcon
            ImageIcon currentImageIcon = (ImageIcon) currentIcon;
            // Lấy hình ảnh từ ImageIcon
            Image currentImage = currentImageIcon.getImage();
            // Set size
            int width = 400; // Kích thước mới (chiều rộng)
            int height = 400; // Kích thước mới (chiều cao)
            Image scaledImage = currentImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            // Tạo biểu tượng mới từ hình ảnh đã điều chỉnh kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            // Thiết lập biểu tượng mới cho JButton
            IconImportManager.setIcon(scaledIcon);
            //
            currentIcon = StaticsProduct.getIcon();
            if (currentIcon instanceof ImageIcon) {
                currentImageIcon = (ImageIcon) currentIcon;
                currentImage = currentImageIcon.getImage();
                scaledImage = currentImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                // Thiết lập biểu tượng mới cho JButton
                StaticsProduct.setIcon(scaledIcon);
            }
        }
    }
}
