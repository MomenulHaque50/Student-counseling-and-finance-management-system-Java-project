/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_management_system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.UIManager.getString;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Mominul Haque
 */
public class studentinsert {

 
    
   public void InsertUpdateDeleteStudent( char operation, String id, String fname, String lname, String phone, String email, String password, String dept, String batch, String gender, String address) throws ClassNotFoundException, SQLException
   {
        
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?zeroDateTimeBehavior=convertToNull", "root", "");
            PreparedStatement ps;
       
       
           if(operation =='i')
            {
              try {
                  ps= con.prepareStatement("INSERT INTO `studentdata`(`ID`, `First name`, `Last name`, `Phone`, `email`, `password`, `Dept`, `Batch`, `Gender`, `Address`) VALUES (?,?,?,?,?,?,?,?,?,?)");
                 ps.setString(1,id);
                ps.setString(2,fname);
                ps.setString(3,lname);
                ps.setString(4,phone);
                ps.setString(5,email);
                ps.setString(6,password);
                ps.setString(7,dept);
                ps.setString(8,batch);
                ps.setString(9,gender);
                ps.setString(10,address);
              
                if(ps.executeUpdate()>0)
                {
                   JOptionPane.showMessageDialog(null,"New Student Added"); 
                }
            }
        
       catch (SQLException ex) {
           Logger.getLogger(studentinsert.class.getName()).log(Level.SEVERE, null, ex);
       }
            
        
       
         
   }

   }
   public void fillstudentjtable(JTable table, String valuetosearch) throws ClassNotFoundException, SQLException
   {
        Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?zeroDateTimeBehavior=convertToNull", "root", "");
            PreparedStatement ps;
       
              try {
                  ps= con.prepareStatement("SELECT * FROM `studentdata` WHERE CONCAT('ID','First name','Last name','Phone')Like ?");
                  ps.setString(1,"%"+valuetosearch+"%");
                  ResultSet rs=ps.executeQuery();
                  DefaultTableModel model=(DefaultTableModel)table.getModel();
                 Object[] row;
                 while(rs.next()){
                     row= new Object[6];
                     row[0]= getString(1);
                     row[1]= getString(2);
                     row[2]= getString(3);
                     row[3]= getString(4);
                     row[4]= getString(5);
                     row[5]= getString(6);
                     
                     model.addRow(row);
                     
                 }
              
            }
        
       catch (SQLException ex) {
           Logger.getLogger(studentinsert.class.getName()).log(Level.SEVERE, null, ex);
   }
   }
    
}
