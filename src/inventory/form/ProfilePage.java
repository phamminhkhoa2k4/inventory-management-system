package inventory.form;

import inventory.database.ConnectionDB;
import inventory.database.ExecuteQuery;
import inventory.event.SearchOptinEvent;
import inventory.swing.SearchOption;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class ProfilePage extends javax.swing.JPanel {

    public ProfilePage() {
        initComponents();
        setOpaque(false);
//        call method display data table
        DisplayDataTable();
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
        txt.addOption(new SearchOption("Name", new ImageIcon(getClass().getResource("/inventory/icon/identification.png"))));
        txt.addOption(new SearchOption("Age", new ImageIcon(getClass().getResource("/inventory/icon/age.png"))));
        txt.addOption(new SearchOption("Sex", new ImageIcon(getClass().getResource("/inventory/icon/sex.png"))));
        txt.addOption(new SearchOption("Tel", new ImageIcon(getClass().getResource("/inventory/icon/call.png"))));
        txt.addOption(new SearchOption("Email", new ImageIcon(getClass().getResource("/inventory/icon/mail.png"))));
        txt.addOption(new SearchOption("Address", new ImageIcon(getClass().getResource("/inventory/icon/location.png"))));
        txt.addOption(new SearchOption("Postion", new ImageIcon(getClass().getResource("/inventory/icon/jobs.png"))));

        setBackground(new Color(30, 30, 30));
        table.fixTable(jScrollPane1);
//        table.setColumnAlignment(0, JLabel.CENTER);
//        table.setCellAlignment(0, JLabel.CENTER);
//        table.setColumnAlignment(2, JLabel.CENTER);
//        table.setCellAlignment(2, JLabel.CENTER);
//        table.setColumnAlignment(4, JLabel.RIGHT);
//        table.setCellAlignment(4, JLabel.RIGHT);
//        table.setColumnWidth(0, 50);
//        table.setColumnWidth(2, 100);
//        DefaultTableModel mode = (DefaultTableModel) table.getModel();
//        for (int i = 1; i <= 20; i++) {
//            mode.addRow(new Object[]{i, "Ra Ven", 10, "001 001 001", "PP"});
//        }
    }

    private void loadData(String where, Object... search) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            PreparedStatement p  = ConnectionDB.getInstance().getConnection().prepareStatement("SELECT * FROM Administration\n" + where +
"UNION\n" +
"SELECT * FROM SalePerson\n" + where +
"UNION\n" +
"SELECT * FROM SaleManager\n" + where + 
"UNION\n" +
"SELECT * FROM Inventory_Manager\n" + where );
            for(int i = 0 ; i < search.length; i++ ){
                p.setObject(i + 1, search[i]);
            }
            ResultSet r = p.executeQuery();
            while(r.next()){
                String id = r.getString("ID");
                String name = r.getString("Name");
                int age = r.getInt("Age");
                String sex = r.getString("Sex");
                String tel = r.getString("Phone");
                String email = r.getString("Email");
                String address = r.getString("Address");
                String position = r.getString("Position");
                model.addRow(new Object[]{id, name,age,sex,tel,email,address,position});
            }
            r.close();
            p.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     //      display data on table
        public void DisplayDataTable(){
        String SQL = "SELECT \n" +
"  Admin_ID AS ID,\n" +
"  Name,\n" +
"  Age,\n" +
"  Sex,\n" +
"  Phone,\n" +
"  Email,\n" +
"  Address,\n" +
"  Position\n" +
"FROM Administration\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"SELECT \n" +
"  SalePerson_ID AS ID,\n" +
"  Name,\n" +
"  Age,\n" +
"  Sex,\n" +
"  Phone,\n" +
"  Email,\n" +
"  Address,\n" +
"  Position\n" +
"FROM SalePerson\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"SELECT \n" +
"  SaleManager_ID AS ID,\n" +
"  Name,\n" +
"  Age,\n" +
"  Sex,\n" +
"  Phone,\n" +
"  Email,\n" +
"  Address,\n" +
"  Position\n" +
"FROM SaleManager\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"SELECT \n" +
"  InventoryManager_ID AS ID,\n" +
"  Name,\n" +
"  Age,\n" +
"  Sex,\n" +
"  Phone,\n" +
"  Email,\n" +
"  Address,\n" +
"  Position\n" +
"FROM Inventory_Manager\n" +
";";
        ResultSet rs = ExecuteQuery.ExecuteSyntaxSelect(SQL); 
//      create a DefaultTableModel take from account table 
        DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
//        create a array include 5 element to capacity colunm value in table 
        Object obj[] = new Object[8];
        try{
            while(rs.next()){
                obj[0] = rs.getString("ID");
                obj[1] = rs.getString("Name");
                obj[2] = rs.getString("Age");
                obj[3] = rs.getString("Sex");
                obj[4] = rs.getString("Phone");
                obj[5] = rs.getString("Email");
                obj[6] = rs.getString("Address");
                obj[7] = rs.getString("Position");
                
                
                
                tbModel.addRow(obj);
                        
                
            }
        }catch(SQLException ex){
//            System.out.println("loi lay data");
            System.out.println(ex.toString());
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
            if (txtID.getText().startsWith("a")) {
            String sql = "INSERT INTO Administration(Admin_id,Name, Age, Sex, Phone, Email, Address, Position) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }else if(txtID.getText().startsWith("b")){
            String sql = "INSERT INTO SaleManager(SaleManager_ID,Name, Age, Sex, Phone, Email, Address, Position) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }else if(txtID.getText().startsWith("c")){
            String sql = "INSERT INTO Inventory_Manager(InventoryManager_ID,Name, Age, Sex, Phone, Email, Address, Position) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }else if(txtID.getText().startsWith("d")){
            String sql = "INSERT INTO SalePerson(SalePerson_ID,Name, Age, Sex, Phone, Email, Address, Position) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }
           
            
            JOptionPane.showMessageDialog(null, "Create successfull");
            ResetTable();
            //        call method display data table
            DisplayDataTable();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
            
        }
         
         
         
//        method delete 
         public void DeleteTable(){
            ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
            if (txtID.getText().startsWith("a")) {
            String sql = "DELETE FROM Administration WHERE (Admin_ID=?) and (Name=?) and (Age=?) and (Sex=?) and (Phone=?) and (Email=?) and (Address=?) and (Position=?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            } else if (txtID.getText().startsWith("b")) {
            String sql = "DELETE FROM SaleManager WHERE (SaleManager_ID=?) and (Name=?) and (Age=?) and (Sex=?) and (Phone=?) and (Email=?) and (Address=?) and (Position=?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }  else if (txtID.getText().startsWith("c")) {
            String sql = "DELETE FROM Inventory_Manager  WHERE (InventoryManager_ID=?) and (Name=?) and (Age=?) and (Sex=?) and (Phone=?) and (Email=?) and (Address=?) and (Position=?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            } else if (txtID.getText().startsWith("d")) {
            String sql = "DELETE FROM SalePerson WHERE (SalePerson_ID=?) and (Name=?) and (Age=?) and (Sex=?) and (Phone=?) and (Email=?) and (Address=?) and (Position=?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }
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
                txtName.setText("");
                txtAge.setValue(0);
                txtSex.setSelectedIndex(-1);
                txtPhone.setText("");
                txtEmail.setText("");
                txtAddress.setText("");
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
        txtName = new inventory.swing.TextFieldUI();
        txtAge = new inventory.swing.Spinner();
        txtSex = new inventory.swing.Combobox();
        txtPhone = new inventory.swing.TextFieldUI();
        txtEmail = new inventory.swing.TextFieldUI();
        txtAddress = new inventory.swing.TextFieldUI();
        txtPosition = new inventory.swing.Combobox();
        Refresh = new inventory.swing.ButtonGradient();
        DeleteProfileBtn = new inventory.swing.ButtonGradient();
        UpdateProfileBtn = new inventory.swing.ButtonGradient();
        CreateProfileBtn = new inventory.swing.ButtonGradient();
        txtID = new inventory.swing.TextFieldUI();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Age", "Gender", "Tel", "Email", "Adress", "Position"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(60);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);
            table.getColumnModel().getColumn(2).setPreferredWidth(40);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setPreferredWidth(120);
            table.getColumnModel().getColumn(5).setPreferredWidth(120);
            table.getColumnModel().getColumn(6).setPreferredWidth(200);
            table.getColumnModel().getColumn(7).setPreferredWidth(150);
        }

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActionPerformed(evt);
            }
        });
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
        });

        txtName.setLabelText("Name");
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtAge.setLabelText("Age");

        txtSex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female", "Orther" }));
        txtSex.setSelectedIndex(-1);
        txtSex.setLabeText("Sex");
        txtSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSexActionPerformed(evt);
            }
        });

        txtPhone.setLabelText("Telephone");

        txtEmail.setLabelText("Email ");

        txtAddress.setLabelText("Address");
        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });

        txtPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sales", "Sale Manager", "Inventory Manager", "Administration" }));
        txtPosition.setLabeText("Position");

        Refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/rotation.png"))); // NOI18N
        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        DeleteProfileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/bin.png"))); // NOI18N
        DeleteProfileBtn.setText("Delete");
        DeleteProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProfileBtnActionPerformed(evt);
            }
        });

        UpdateProfileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/pencil.png"))); // NOI18N
        UpdateProfileBtn.setText("Update");
        UpdateProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateProfileBtnActionPerformed(evt);
            }
        });

        CreateProfileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/add-button.png"))); // NOI18N
        CreateProfileBtn.setText("Create");
        CreateProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateProfileBtnActionPerformed(evt);
            }
        });

        txtID.setLabelText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CreateProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(UpdateProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSex, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 1166, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        if(txt.isSelected()){
            int option = txt.getSelectedIndex();
            String text = "%" + txt.getText().trim() + "%";
            if(option == 0){
                //                search by id
                loadData("where ID like ?", text);
            }else if(option == 1){
                //                search by name
                loadData("where Name like ?", text);
            }else if(option == 2 ){
                //                search by  age
                loadData("where Age like ?", text);
            }else if(option == 3 ){
                //                search by  sex
                loadData("where Sex like ?", text);
            }else if(option == 4 ){
                //                search by  tel
                loadData("where Phone like ?", text);
            }else if(option == 5){
                //                search by email
                loadData("where Email like ?", text);
            }else if(option == 6){
                //                search by address
                loadData("where Address like ? ",text);
            }else if(option == 7){
                //                search by position
                loadData("where Position like ?", text);
            }
        }
    }//GEN-LAST:event_txtKeyReleased

    private void txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActionPerformed

    private void DeleteProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProfileBtnActionPerformed
        DeleteTable();
        System.out.println("delete");
    }//GEN-LAST:event_DeleteProfileBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        //        click display rows on textfield
        DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
        int SelectRows = table.getSelectedRow();
        
        
        txtID.setText(tbModel.getValueAt(SelectRows,0).toString());
        txtName.setText(tbModel.getValueAt(SelectRows,1).toString());
        int ageValue = Integer.parseInt(tbModel.getValueAt(SelectRows, 2).toString());
        SpinnerNumberModel ageModel = (SpinnerNumberModel) txtAge.getModel();
        ageModel.setValue(ageValue);
        txtSex.setSelectedItem(tbModel.getValueAt(SelectRows,3).toString()); // if not have in item , it will no display ;
        txtPhone.setText(tbModel.getValueAt(SelectRows,4).toString());
        txtEmail.setText(tbModel.getValueAt(SelectRows,5).toString());
        txtAddress.setText(tbModel.getValueAt(SelectRows,6).toString());
        txtPosition.setSelectedItem(tbModel.getValueAt(SelectRows,7)); // if not have in item , it will no display ;
    }//GEN-LAST:event_tableMouseClicked

    private void CreateProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateProfileBtnActionPerformed
        InsertTable();
        System.out.println("insert");
    }//GEN-LAST:event_CreateProfileBtnActionPerformed

    private void UpdateProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateProfileBtnActionPerformed
        ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
            if (txtID.getText().startsWith("a")) {
            String sql = "UPDATE Administration SET  Position = ?, Name = ?, Age = ?, Sex = ? , Phone = ? , Email = ? ,Address = ?  WHERE (Admin_ID = ?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(8, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(1, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            } else if (txtID.getText().startsWith("b")) {
            String sql = "UPDATE SaleManager SET  Position = ?, Name = ?, Age = ?, Sex = ? , Phone = ? , Email = ? ,Address = ?  WHERE (SaleManager_ID = ?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(8, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(1, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }  else if (txtID.getText().startsWith("c")) {
            String sql = "UPDATE  Inventory_Manager SET  Position = ?, Name = ?, Age = ?, Sex = ? , Phone = ? , Email = ? ,Address = ?  WHERE (InventoryManager_ID = ?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(8, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(1, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            } else if (txtID.getText().startsWith("d")) {
            String sql = "UPDATE SalePerson SET  Position = ?, Name = ?, Age = ?, Sex = ? , Phone = ? , Email = ? ,Address = ?  WHERE (SalePerson_ID = ?)";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(8, txtID.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, (int) txtAge.getValue());
            pst.setString(4, txtSex.getSelectedItem().toString());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtAddress.getText());
            pst.setString(1, txtPosition.getSelectedItem().toString());
            pst.executeUpdate();
            }
            // Hiển thị lại JTable
//		table.repaint();
//		table.revalidate();
            ResetTable();
            //        call method display data table
            DisplayDataTable();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_UpdateProfileBtnActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        txtID.setText("");
        txtName.setText("");
        txtAge.setValue(0);
        txtSex.setSelectedIndex(-1);
        txtPhone.setText("");
        txtEmail.setText("");
	txtAddress.setText("");
	txtPosition.setSelectedIndex(-1);
    }//GEN-LAST:event_RefreshActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void txtSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private inventory.swing.ButtonGradient CreateProfileBtn;
    private inventory.swing.ButtonGradient DeleteProfileBtn;
    private inventory.swing.ButtonGradient Refresh;
    private inventory.swing.ButtonGradient UpdateProfileBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private inventory.table.TableDark table;
    private inventory.table.TableScrollButton tableScrollButton1;
    private inventory.swing.TextFieldSearchOption txt;
    private inventory.swing.TextFieldUI txtAddress;
    private inventory.swing.Spinner txtAge;
    private inventory.swing.TextFieldUI txtEmail;
    private inventory.swing.TextFieldUI txtID;
    private inventory.swing.TextFieldUI txtName;
    private inventory.swing.TextFieldUI txtPhone;
    private inventory.swing.Combobox txtPosition;
    private inventory.swing.Combobox txtSex;
    // End of variables declaration//GEN-END:variables
}
