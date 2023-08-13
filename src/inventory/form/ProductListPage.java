
package inventory.form;

import inventory.database.ExecuteQuery;
import inventory.event.SearchOptinEvent;
import inventory.swing.SearchOption;
import inventory.table.TableCustom;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ProductListPage extends javax.swing.JPanel {


    public ProductListPage() {
        initComponents();
        setOpaque(false);
//        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
//        call method display data table
        DisplayDataTable();
        
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
    }
    

//      display data on table
        public void DisplayDataTable(){
        String SQL = "SELECT p.Product_ID, gc.Category_name, p.Product_name, p.Product_price\n" + "FROM products p\n" + "JOIN GoodsCategories gc ON p.Category_ID = gc.Category_ID;";
        ResultSet rs = ExecuteQuery.ExecuteSyntaxSelect(SQL); 
//      create a DefaultTableModel take from account table 
        DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
//        create a array include 5 element to capacity colunm value in table 
        Object obj[] = new Object[4];
        try{
            while(rs.next()){
                obj[0] = rs.getInt("Product_ID");
                obj[1] = rs.getString("Product_name");
                obj[2] = rs.getString("Category_name");
                obj[3] = rs.getString("Product_price");

                tbModel.addRow(obj);  
            }
        }catch(SQLException ex){
            System.out.println("loi lay data");
        }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollButton2 = new inventory.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new inventory.table.TableDark();
        txt = new inventory.swing.TextFieldSearchOption();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name ", "Categorry", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        tableScrollButton2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
            .addComponent(txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private inventory.table.TableDark table;
    private inventory.table.TableScrollButton tableScrollButton2;
    private inventory.swing.TextFieldSearchOption txt;
    // End of variables declaration//GEN-END:variables
}
