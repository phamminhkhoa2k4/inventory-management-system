
package inventory.form;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import inventory.component.Menu;
import inventory.database.ConnectionDB;
import inventory.model.ModelUser;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.filechooser.FileFilter;


public class SettingPage extends javax.swing.JPanel {
    
    public ModelUser getData() {
        return data;
    }
    private ModelUser data;
    private Animator animatorLogin;
    private Animator animatorBody;
    private boolean signIn;
 
    public SettingPage() {
        initComponents();
        setOpaque(false);
        TimingTarget targetLogin = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (signIn) {
                    background1.setAnimate(fraction);
                } else {
                    background1.setAnimate(1f - fraction);
//background1.setAnimate(fraction);
                }
            }

            @Override
            public void end() {

                   panelLogin.setVisible(false);
                    background1.setShowPaint(true);
//                    panelBody.setAlpha(0);
                    panelBody.setVisible(true);
                    animatorBody.start();
         
                    
         
            }
        };
        TimingTarget targetBody = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
if (signIn) {
                    background1.setAnimate(fraction);
                } else {
                    background1.setAnimate(1f - fraction);
//background1.setAnimate(fraction);
                }
            }

            @Override
            public void end() {
                if (signIn == false) {
//                    animatorLogin.start();
                    panelBody.setVisible(false);
                    background1.setShowPaint(false);
//                    background1.setAnimate(1);
                    panelLogin.setVisible(true);

                }
            }
        };
        animatorLogin = new Animator(1500, targetLogin);
        animatorBody = new Animator(500, targetBody);
        animatorLogin.setResolution(0);
        animatorBody.setResolution(0);
    
     
    }

//     method change password 
    private void changePassword() {
    String username = txtUser.getText().trim();
    String oldPassword = String.valueOf(txtOldPass.getPassword()).trim();
    String newPassword = String.valueOf(txtNewPass.getPassword()).trim();
    String confirmPassword = String.valueOf(txtConfirm.getPassword()).trim();
    
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter username");
        txtUser.requestFocus();
        return;
    }
    if (oldPassword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter old password");
        txtOldPass.requestFocus();
        return;
    }
    if (newPassword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter new password");
        txtNewPass.requestFocus();
        return;
    }
    if (confirmPassword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter confirm password");
        txtConfirm.requestFocus();
        return;
    }
    
    if (!newPassword.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(null, "Confirm password not matched");
        txtConfirm.requestFocus();
        return;
    }
    
    // Kiểm tra xem mật khẩu cũ có đúng hay không
        ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
        String sql = "SELECT * FROM account WHERE Username=? AND Password=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, oldPassword);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
            txtUser.requestFocus();
            txtUser.selectAll();
            return;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error accessing database");
        ex.printStackTrace();
        return;
    }
    
    // Cập nhật mật khẩu mới vào database
        try {
            conn = cn.getConnection();
        String sql = "UPDATE account SET Password=? WHERE Username=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, newPassword);
        pst.setString(2, username);
        int result = pst.executeUpdate();
        if (result == 1) {
            JOptionPane.showMessageDialog(null, "Password changed successfully");
            signIn = false;
           animatorBody.start();
        } else {
            JOptionPane.showMessageDialog(null, "Error changing password");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error accessing database");
        ex.printStackTrace();
    }
}

    
    
//    //Tạo hàm upload avatar
//public void uploadAvatar() {
//    JFileChooser chooser = new JFileChooser();
//    //Chọn chỉnh sửa file ảnh
//    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//    int returnVal = chooser.showOpenDialog(this);
//    if(returnVal == JFileChooser.APPROVE_OPTION) {
//        File file = chooser.getSelectedFile();
//        ConnectionDB cn = new ConnectionDB();
//        Connection conn = null;
//        try {
//            conn = cn.getConnection();
//            //Đọc file ảnh vào một mảng byte
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//            for(int readNum; (readNum=fis.read(buf)) != -1;) {
//                bos.write(buf, 0, readNum);               
//            }
//            byte[] imageBytes = bos.toByteArray();
//            // Sử dụng SQL query để insert dữ liệu vào cột của database có kiểu dữ liệu varbinary
//            String sql = "INSERT INTO Profile (avatar) VALUES (?)";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setBytes(1, imageBytes);
//            statement.executeUpdate();
//            statement.close();
//            JOptionPane.showMessageDialog(null, "Avatar đã được tải lên thành công");
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi khi đọc file");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi khi insert vào database");
//        }
//    }
//}

        public void done(ModelUser data) {
        this.data = data;
        imageAvatar.setIcon(data.getAvatar());
        DaPosition.setText("");

    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new inventory.swing.Background();
        panelLogin = new javax.swing.JPanel();
        imageAvatar = new inventory.swing.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();
        UploadAvatar = new inventory.swing.Button();
        DaUsername = new javax.swing.JLabel();
        DaPosition = new javax.swing.JLabel();
        button1 = new inventory.swing.Button();
        panelBody = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtConfirm = new inventory.swing.PasswordFieldUI();
        txtUser = new inventory.swing.TextFieldUI();
        ChangePassBtn = new inventory.swing.Button();
        button3 = new inventory.swing.Button();
        txtOldPass = new inventory.swing.PasswordFieldUI();
        txtNewPass = new inventory.swing.PasswordFieldUI();

        background1.setLayout(new java.awt.CardLayout());

        imageAvatar.setForeground(new java.awt.Color(51, 255, 204));
        imageAvatar.setBorderSize(4);
        imageAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/avatar.jpg"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Account");

        UploadAvatar.setBackground(new java.awt.Color(204, 204, 204));
        UploadAvatar.setForeground(new java.awt.Color(255, 255, 255));
        UploadAvatar.setText("Upload");
        UploadAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadAvatarActionPerformed(evt);
            }
        });

        DaUsername.setBackground(new java.awt.Color(0, 0, 0));
        DaUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DaUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaUsername.setText("Phạm Minh Khoa");

        DaPosition.setBackground(new java.awt.Color(0, 0, 0));
        DaPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DaPosition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaPosition.setText("Admin");

        button1.setBackground(new java.awt.Color(255, 102, 102));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Change Password ");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(DaPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addComponent(UploadAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DaUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(233, 233, 233))))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UploadAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(DaUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DaPosition)
                .addGap(122, 122, 122)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        background1.add(panelLogin, "card2");

        panelBody.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Change Passeword");

        txtConfirm.setLabelText("Comfirm Password");

        txtUser.setLabelText("Username");

        ChangePassBtn.setBackground(new java.awt.Color(102, 255, 102));
        ChangePassBtn.setForeground(new java.awt.Color(255, 255, 255));
        ChangePassBtn.setText("Change");
        ChangePassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePassBtnActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(255, 102, 102));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Cancel");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        txtOldPass.setLabelText("Old Password ");

        txtNewPass.setLabelText("New Password");

        javax.swing.GroupLayout panelBodyLayout = new javax.swing.GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBodyLayout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addGroup(panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(ChangePassBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(234, 234, 234))
        );
        panelBodyLayout.setVerticalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBodyLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(ChangePassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        background1.add(panelBody, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(345, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void UploadAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadAvatarActionPerformed
        JFileChooser UploadAvatarFile = new JFileChooser();

        //      fillter image extentions
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES","png","jpg","jpeg");
        UploadAvatarFile.addChoosableFileFilter(fnef);
        int showOpenDialogue = UploadAvatarFile.showOpenDialog(null);

        if(showOpenDialogue == JFileChooser.APPROVE_OPTION){
            File selectedImageFile = UploadAvatarFile.getSelectedFile();
            String selectedImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null,selectedImagePath);
            //           display image on avatar
            ImageIcon ka = new ImageIcon(selectedImagePath);
            //            Resize image to fit avatar
            Image image = ka.getImage().getScaledInstance(imageAvatar.getWidth(),imageAvatar.getHeight(),Image.SCALE_SMOOTH);

            imageAvatar.setIcon(new ImageIcon(image));
        }
        JFileChooser ch = new JFileChooser();
        ch.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName().toLowerCase();
                return file.isDirectory() || name.endsWith(".png") || name.endsWith(".jpg");
            }

            @Override
            public String getDescription() {
                return "Image File";
            }
        });
        int option = ch.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = ch.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            imageAvatar.setIcon(icon);
            repaint();
            try {
                String sql = "update account set Avatar=? where Username=? ";
                PreparedStatement p = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
                p.setBinaryStream(1, Files.newInputStream(file.toPath()));
                p.setString(2, data.getUserName());
                p.execute();
                data.setAvatar(icon);
            } catch (IOException | SQLException e) {
                System.err.println(e);
            }
        }

    }//GEN-LAST:event_UploadAvatarActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        signIn = true;
        animatorLogin.start();
       
    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        signIn = false;
        animatorBody.start();
    }//GEN-LAST:event_button3ActionPerformed

    private void ChangePassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePassBtnActionPerformed
        changePassword();
    }//GEN-LAST:event_ChangePassBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private inventory.swing.Button ChangePassBtn;
    private javax.swing.JLabel DaPosition;
    private javax.swing.JLabel DaUsername;
    private inventory.swing.Button UploadAvatar;
    private inventory.swing.Background background1;
    private inventory.swing.Button button1;
    private inventory.swing.Button button3;
    private inventory.swing.ImageAvatar imageAvatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelLogin;
    private inventory.swing.PasswordFieldUI txtConfirm;
    private inventory.swing.PasswordFieldUI txtNewPass;
    private inventory.swing.PasswordFieldUI txtOldPass;
    private inventory.swing.TextFieldUI txtUser;
    // End of variables declaration//GEN-END:variables
}
