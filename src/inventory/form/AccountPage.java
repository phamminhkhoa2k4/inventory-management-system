
package inventory.form;

import inventory.database.ConnectionDB;
import inventory.database.ExecuteQuery;
import inventory.event.SearchOptinEvent;
import inventory.swing.SearchOption;
import java.awt.Color;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class AccountPage extends javax.swing.JPanel {


    public AccountPage() {
        initComponents();
        setOpaque(false);
//        call method display data table
        DisplayDataTable();
//        execute syntax select 
//        String SQL = "Select * From Account";
//        ResultSet rs = ExecuteQuery.ExecuteSyntaxSelect(SQL); 
//        try{
//            while(rs.next()){
//                System.out.println(rs.getString(1));
//            }
//        }catch(SQLException ex){
//            System.out.println("loi lay data");
//        }

        try {
            ConnectionDB.getInstance().getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
        
                //       search 
        txt.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txt.setHint("Search by " + option.getName() + "...");
            }
        });
        txt.addOption(new SearchOption("ID", new ImageIcon(getClass().getResource("/inventory/icon/search-id.png"))));
        txt.addOption(new SearchOption("Username", new ImageIcon(getClass().getResource("/inventory/icon/id-card.png"))));
        txt.addOption(new SearchOption("Position", new ImageIcon(getClass().getResource("/inventory/icon/jobs.png"))));
        
        
        setBackground(new Color(30, 30, 30));
        table.fixTable(jScrollPane1);
    }
    private void loadData(String where, Object... search) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            PreparedStatement p  = ConnectionDB.getInstance().getConnection().prepareStatement("select * from account "+ where);
            for(int i = 0 ; i < search.length; i++ ){
                p.setObject(i + 1, search[i]);
            }
            ResultSet r = p.executeQuery();
            while(r.next()){
                int id = r.getInt("account_id");
                String username = r.getString("Username");
                String password = r.getString("Password");
                String avatar = r.getString("Avatar");
                String position = r.getString("Position");
                model.addRow(new Object[]{id,username,password,avatar,position});
            }
            r.close();
            p.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
   
    //      display data on table
        public void DisplayDataTable(){
        String SQL = "Select * From account";
        ResultSet rs = ExecuteQuery.ExecuteSyntaxSelect(SQL); 
//      create a DefaultTableModel take from account table 
        DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
//        create a array include 5 element to capacity colunm value in table 
        Object obj[] = new Object[5];
        try{
            while(rs.next()){
                obj[0] = rs.getString("account_id");
                obj[1] = rs.getString("Username");
                obj[2] = rs.getString("Password");
                obj[3] = rs.getString("Avatar");
                obj[4] = rs.getString("Position");
                
                tbModel.addRow(obj);
                        
                
            }
        }catch(SQLException ex){
            System.out.println("loi lay data");
        }
        }
        
//         medthod resetTable 
         public void ResetTable(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);


         }
//         method insert 
        public void InsertTable(){
            ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
            String sql = "Insert into account(account_id,Username,Password,Position) values (?,?,?,?)"; 
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtUsername.getText());
            pst.setString(3, txtPassword.getText());
            pst.setString(4,txtPosition.getSelectedItem().toString());
           
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Create successfull");
            ResetTable();
            //        call method display data table
            DisplayDataTable();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            JOptionPane.showMessageDialog(null, " insert duplicate Account");
        }
            
        }
//        method delete 
         public void DeleteTable(){
            ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
            String sql = "DELETE FROM account WHERE (account_id=?) and (Username=?) and (Password=?) and  (Position=?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtUsername.getText());
            pst.setString(3, txtPassword.getText());
            pst.setString(4, txtPosition.getSelectedItem().toString());
           
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete successfull");
//            ResetTable();
//            //        call method display data table
//            DisplayDataTable();
// Xóa dòng được chọn trong TableModel
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int selectedRow = table.getSelectedRow();
		if(selectedRow != -1) { // Nếu có dòng được chọn
			model.removeRow(selectedRow);
		}
		
		// Hiển thị lại JTable
		table.repaint();
		table.revalidate();
		
		// Hiển thị lại dữ liệu trên các textfield
		txtID.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		txtPosition.setSelectedIndex(-1);
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
            
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollButton1 = new inventory.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new inventory.table.TableDark();
        jPanel1 = new javax.swing.JPanel();
        txt = new inventory.swing.TextFieldSearchOption();
        txtID = new inventory.swing.TextFieldUI();
        txtUsername = new inventory.swing.TextFieldUI();
        txtPosition = new inventory.swing.Combobox();
        RefreshAccountBtn = new inventory.swing.ButtonGradient();
        DeleteAcountBtn = new inventory.swing.ButtonGradient();
        UpdateAccountBtn = new inventory.swing.ButtonGradient();
        CreateAcountBtn = new inventory.swing.ButtonGradient();
        txtPassword = new inventory.swing.TextFieldUI();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Password", "Avatar", "Position"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
        });

        txtID.setLabelText("ID ");

        txtUsername.setLabelText("Username");

        txtPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sales", "Sale Manager", "Inventory Manager", "Administration" }));
        txtPosition.setSelectedIndex(-1);
        txtPosition.setLabeText("Position");
        txtPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPositionActionPerformed(evt);
            }
        });

        RefreshAccountBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/rotation.png"))); // NOI18N
        RefreshAccountBtn.setText("Refresh");
        RefreshAccountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshAccountBtnActionPerformed(evt);
            }
        });

        DeleteAcountBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/bin.png"))); // NOI18N
        DeleteAcountBtn.setText("Delete");
        DeleteAcountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAcountBtnActionPerformed(evt);
            }
        });

        UpdateAccountBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/pencil.png"))); // NOI18N
        UpdateAccountBtn.setText("Update");
        UpdateAccountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateAccountBtnActionPerformed(evt);
            }
        });

        CreateAcountBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/add-button.png"))); // NOI18N
        CreateAcountBtn.setText("Create");
        CreateAcountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAcountBtnActionPerformed(evt);
            }
        });

        txtPassword.setLabelText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(59, 59, 59)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(CreateAcountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(DeleteAcountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RefreshAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RefreshAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteAcountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateAcountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPositionActionPerformed
        
    }//GEN-LAST:event_txtPositionActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
//        click display rows on textfield
        DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
        int SelectRows = table.getSelectedRow();
        
        
        txtID.setText(tbModel.getValueAt(SelectRows,0).toString());
        txtUsername.setText(tbModel.getValueAt(SelectRows,1).toString());
        txtPassword.setText(tbModel.getValueAt(SelectRows,2).toString());
        txtPosition.setSelectedItem(tbModel.getValueAt(SelectRows,4)); // if item not have , it will no display ;

    }//GEN-LAST:event_tableMouseClicked

    private void CreateAcountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateAcountBtnActionPerformed
        InsertTable();
        System.out.println("insert");
    }//GEN-LAST:event_CreateAcountBtnActionPerformed

    private void DeleteAcountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAcountBtnActionPerformed
        DeleteTable();
        System.out.println("delete");
    }//GEN-LAST:event_DeleteAcountBtnActionPerformed

    private void UpdateAccountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateAccountBtnActionPerformed
        ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
            String sql = "UPDATE account SET Username = ?, Password = ?, Position = ? WHERE (account_id = ?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtUsername.getText());
            pst.setString(2, txtPassword.getText());
            pst.setString(3, txtPosition.getSelectedItem().toString());
            pst.setString(4, txtID.getText());
            pst.executeUpdate(); 
            // Hiển thị lại JTable
//		table.repaint();
//		table.revalidate();
            ResetTable();
            //        call method display data table
            DisplayDataTable();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_UpdateAccountBtnActionPerformed

    private void RefreshAccountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshAccountBtnActionPerformed
        txtID.setText("");
        txtUsername.setText("");
	txtPassword.setText("");
	txtPosition.setSelectedIndex(-1);
    }//GEN-LAST:event_RefreshAccountBtnActionPerformed

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
         if(txt.isSelected()){
            int option = txt.getSelectedIndex();
            String text = "%" + txt.getText().trim() + "%";
            if(option == 0){
                //                search by id
                loadData("where account_id like ?", text);
            }else if(option == 1){
                //                search by Username
                loadData("where Username like ?", text);
            }else if(option == 2 ){
                //                search by  Position
                loadData("where Position like ?", text);
            }
        }
    }//GEN-LAST:event_txtKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private inventory.swing.ButtonGradient CreateAcountBtn;
    private inventory.swing.ButtonGradient DeleteAcountBtn;
    private inventory.swing.ButtonGradient RefreshAccountBtn;
    private inventory.swing.ButtonGradient UpdateAccountBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private inventory.table.TableDark table;
    private inventory.table.TableScrollButton tableScrollButton1;
    private inventory.swing.TextFieldSearchOption txt;
    private inventory.swing.TextFieldUI txtID;
    private inventory.swing.TextFieldUI txtPassword;
    private inventory.swing.Combobox txtPosition;
    private inventory.swing.TextFieldUI txtUsername;
    // End of variables declaration//GEN-END:variables
}
