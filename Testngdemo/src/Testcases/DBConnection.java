package Testcases;

import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static void main(String[] args) throws FileAlreadyExistsException {
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation","root", "");
		System.out.println("Database is connected !");
		
		java.sql.Statement stmt = conn.createStatement();
		String sql= "SELECT * FROM Testsuiterunmode";
        java.sql.ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
        String Testsuitename = rs.getString("Testsuite");
        System.out.println(Testsuitename);
	      
        } 
	      
	      
		} catch(Exception e) 
        { 
          System.out.println("Really poor exception handling " +e.toString()); 
        }
      }
	

}
