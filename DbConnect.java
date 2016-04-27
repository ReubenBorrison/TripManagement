
 
import java.sql.*;
public class DbConnect 
{
	Connection myConn;
	Statement stmt=null;
	PreparedStatement preStmt=null;
	ResultSet rs=null;
	public DbConnect()
	{
		String Url="jdbc:mysql://localhost/tripmanagement",User="root", Pass="team123";
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		try
		{
			System.out.println("Connecting to Database....");
			Class.forName(JDBC_DRIVER); // Register the drivers
			myConn= DriverManager.getConnection(Url, User, Pass); //Get the connection to the database
			System.out.println("Database Connected!!");
			
			
		}
		catch(Exception e)
		{
			System.out.println("oops not able to connect to the database");
		}
		
	}

}
