
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
import javax.swing.table.DefaultTableModel;


public class ProductsPage extends javax.swing.JPanel {


    public ProductsPage() {
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
        txt.addOption(new SearchOption("Name", new ImageIcon(getClass().getResource("/inventory/icon/search-product.png"))));
        txt.addOption(new SearchOption("Tel", new ImageIcon(getClass().getResource("/inventory/icon/classification.png"))));
        txt.addOption(new SearchOption("Age", new ImageIcon(getClass().getResource("/inventory/icon/search-price.png"))));
        
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
    }
 private void loadData(String where, Object... search) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            PreparedStatement p  = ConnectionDB.getInstance().getConnection().prepareStatement("SELECT p.Product_ID, gc.Category_name, p.Product_name, p.Product_price, p.Product_quantity\n" + "FROM products p\n" + "JOIN GoodsCategories gc ON p.Category_ID = gc.Category_ID " + where);
            for(int i = 0 ; i < search.length; i++ ){
                p.setObject(i + 1, search[i]);
            }
            ResultSet r = p.executeQuery();
            while(r.next()){
                int product_id = r.getInt("Product_ID");
                String product_name = r.getString("Product_name");
                String category_name = r.getString("Category_name");
                String product_price = r.getString("Product_price");
                String product_quantity = r.getString("Product_quantity");
                model.addRow(new Object[]{product_id,product_name,category_name,product_price,product_quantity});
            }
            r.close();
            p.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   //      display data on table
        public void DisplayDataTable(){
        String SQL = "SELECT prod.Product_ID, prod.Product_name, gc.Category_name, prod.Product_price, prod.Product_quantity \n" +
                        "FROM Products prod \n" +
                        "JOIN GoodsCategories gc ON prod.Category_ID = gc.Category_ID;";
     

        ResultSet rs = ExecuteQuery.ExecuteSyntaxSelect(SQL); 
//      create a DefaultTableModel take from account table 
        DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
//        create a array include 5 element to capacity colunm value in table 
        Object obj[] = new Object[5];
        try{
            while(rs.next()){
                obj[0] = rs.getInt("Product_ID");
                obj[1] = rs.getString("Product_name");
                obj[2] = rs.getString("Category_name");
                obj[3] = rs.getString("Product_price");
                obj[4] = rs.getString("Product_quantity");
                
                
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
            String sql = "INSERT INTO Products (Product_ID, Product_name,Category_ID, Product_price, Product_quantity) VALUES (?,?,(SELECT Category_ID FROM GoodsCategories WHERE Category_name = ? ),?, ?);";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtProductID.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3,  txtCategory.getSelectedItem().toString());
            pst.setString(4, txtPrice.getText());
            pst.setString(5, txtQuantity.getText());
           
            pst.executeUpdate();
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
            String sql = "DELETE FROM products WHERE Product_ID = ? AND Product_name = ? AND Category_ID = (SELECT Category_ID FROM GoodsCategories WHERE Category_name = ?) AND Product_price = ? AND Product_quantity = ? ;";
            PreparedStatement pst = conn.prepareCall(sql);
             pst.setString(1, txtProductID.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3,  txtCategory.getSelectedItem().toString());
            pst.setString(4, txtPrice.getText());
            pst.setString(5, txtQuantity.getText());
           
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
		txtProductID.setText("");
                txtName.setText("");
                txtCategory.setSelectedIndex(-1);
                txtPrice.setText("");
                txtQuantity.setText("");
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
            
        }
         
//     method update 
       public void UpdateTable(){
             ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
        try {
            conn = cn.getConnection();
            String sql = "UPDATE products SET Product_ID = ? , Product_name = ? , Category_ID = (SELECT Category_ID FROM GoodsCategories WHERE Category_name = ? ), Product_price = ? , Product_quantity = ? WHERE Product_ID = ? ";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, txtProductID.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3,  txtCategory.getSelectedItem().toString());
            pst.setString(4, txtPrice.getText());
            pst.setString(5, txtQuantity.getText());
            pst.setString(6, txtProductID.getText());
            pst.executeUpdate(); 
            // Hiển thị lại JTable
//		table.repaint();
//		table.revalidate();
            ResetTable();
            //        call method display data table
            DisplayDataTable();
            JOptionPane.showMessageDialog(null, "Update successfull");
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
       }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt = new inventory.swing.TextFieldSearchOption();
        RefreshProductsBtn = new inventory.swing.ButtonGradient();
        DeleteProductsBtn = new inventory.swing.ButtonGradient();
        UpdateProductsBtn = new inventory.swing.ButtonGradient();
        CreateProductsBtn = new inventory.swing.ButtonGradient();
        txtProductID = new inventory.swing.TextFieldUI();
        txtName = new inventory.swing.TextFieldUI();
        txtCategory = new inventory.swing.Combobox();
        txtPrice = new inventory.swing.TextFieldUI();
        txtQuantity = new inventory.swing.TextFieldUI();
        tableScrollButton2 = new inventory.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new inventory.table.TableDark();

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

        RefreshProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/rotation.png"))); // NOI18N
        RefreshProductsBtn.setText("Refresh");
        RefreshProductsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshProductsBtnActionPerformed(evt);
            }
        });

        DeleteProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/bin.png"))); // NOI18N
        DeleteProductsBtn.setText("Delete");
        DeleteProductsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProductsBtnActionPerformed(evt);
            }
        });

        UpdateProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/pencil.png"))); // NOI18N
        UpdateProductsBtn.setText("Update");
        UpdateProductsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateProductsBtnActionPerformed(evt);
            }
        });

        CreateProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/add-button.png"))); // NOI18N
        CreateProductsBtn.setText("Create");
        CreateProductsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateProductsBtnActionPerformed(evt);
            }
        });

        txtProductID.setText("A22086");
        txtProductID.setLabelText("Product ID");

        txtName.setText("Carrot");
        txtName.setLabelText("Name");

        txtCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toys", "Clothing" }));
        txtCategory.setLabeText("Category");

        txtPrice.setText("10000k");
        txtPrice.setLabelText("Price");

        txtQuantity.setLabelText("Quantity");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CreateProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(UpdateProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(DeleteProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(RefreshProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RefreshProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Name", "Category", "Price", "Quantity"
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

        tableScrollButton2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateProductsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateProductsBtnActionPerformed
       UpdateTable();
        System.out.println("update");
    }//GEN-LAST:event_UpdateProductsBtnActionPerformed

    private void txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActionPerformed
        
    }//GEN-LAST:event_txtActionPerformed

    private void CreateProductsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateProductsBtnActionPerformed
        InsertTable();
        System.out.println("insert");
    }//GEN-LAST:event_CreateProductsBtnActionPerformed

    private void DeleteProductsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProductsBtnActionPerformed
        DeleteTable();
        System.out.println("delete");
    }//GEN-LAST:event_DeleteProductsBtnActionPerformed

    private void RefreshProductsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshProductsBtnActionPerformed
        txtProductID.setText("");
        txtName.setText("");
        txtCategory.setSelectedIndex(-1);
        txtPrice.setText("");
        txtQuantity.setText("");
    }//GEN-LAST:event_RefreshProductsBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
          //        click display rows on textfield
        DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
        int SelectRows = table.getSelectedRow();
        
        
        txtProductID.setText(tbModel.getValueAt(SelectRows,0).toString());
        txtName.setText(tbModel.getValueAt(SelectRows,1).toString());
        txtCategory.setSelectedItem(tbModel.getValueAt(SelectRows,2).toString()); // if not have in item , it will no display ;
        txtPrice.setText(tbModel.getValueAt(SelectRows,3).toString());
        txtQuantity.setText(tbModel.getValueAt(SelectRows,4).toString());
     
    }//GEN-LAST:event_tableMouseClicked

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        if(txt.isSelected()){
            int option = txt.getSelectedIndex();
            String text = "%" + txt.getText().trim() + "%";
            if(option == 0){
                //                search by id
                loadData("where Product_ID like ?", text);
            }else if(option == 1){
                //                search by product name
                loadData("where Product_name like ?", text);
            }else if(option == 2 ){
                //                search by  category name
                loadData("where Category_name like ?", text);
            }else if(option == 3 ){
                //                search by  price
                loadData("where Product_price like ?", text);
            }else if(option == 4 ){
                //                search by  quantity 
                loadData("where Product_quantity like ?", text);
            }
        }
    }//GEN-LAST:event_txtKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private inventory.swing.ButtonGradient CreateProductsBtn;
    private inventory.swing.ButtonGradient DeleteProductsBtn;
    private inventory.swing.ButtonGradient RefreshProductsBtn;
    private inventory.swing.ButtonGradient UpdateProductsBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private inventory.table.TableDark table;
    private inventory.table.TableScrollButton tableScrollButton2;
    private inventory.swing.TextFieldSearchOption txt;
    private inventory.swing.Combobox txtCategory;
    private inventory.swing.TextFieldUI txtName;
    private inventory.swing.TextFieldUI txtPrice;
    private inventory.swing.TextFieldUI txtProductID;
    private inventory.swing.TextFieldUI txtQuantity;
    // End of variables declaration//GEN-END:variables
}
