

package inventory.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ExecuteQuery {
    //  method execute syntax select 
     public static ResultSet ExecuteSyntaxSelect(String SQL){
         ConnectionDB cn = new ConnectionDB();
        Connection conn = null;
         try{
             conn = cn.getConnection();
             PreparedStatement pst = conn.prepareCall(SQL);
             return pst.executeQuery();
         }catch(SQLException ex){
             
         }
         return null;
    }
//     method execute syntax select 
//     public static ResultSet ExecuteSyntaxInsert(String SQL){
//         ConnectionDB cn = new ConnectionDB();
//        Connection conn = null;
//         try{
//             conn = cn.getConnection();
//             PreparedStatement pst = conn.prepareCall(SQL);
//             return pst.executeQuery();
//         }catch(SQLException ex){
//             
//         }
//         return null;
//    }
    
}
