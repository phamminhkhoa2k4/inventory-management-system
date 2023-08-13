
package inventory.database;

import java.sql.*;

public class ConnectionDB {
    public static ConnectionDB instance = null ;
    public static ConnectionDB getInstance(){
       if(instance == null){
           instance = new ConnectionDB();
       }
       return instance;
    }
 
    public Connection getConnection(){
        Connection conn = null;
        try{
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String url = "jdbc:sqlserver://localhost:1433;databaseName=InventoryManagement;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String pwd = "sa";
            conn = DriverManager.getConnection(url,user,pwd);

            if(conn != null){
                System.out.println("Connection Susseccfull");
            }
        }catch (ClassNotFoundException | SQLException ex){
            System.out.println(ex.toString());
        }
        return conn;
    }

    
}

 
    

   