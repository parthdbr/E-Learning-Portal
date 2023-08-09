 package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn {
	
	public static Connection connect() {
		
		Connection con = null;
		
		try {
		
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningportal","root","root");
            System.out.println("Executed");
        } catch (SQLException e) {
            return null;
        }
		
        return con;
		
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");  
			
			stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from demo1");  
		
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  ");  
			
			con.close();
			
		}catch(Exception e){ 
			System.out.println(e);
		} 
	}*/

}
