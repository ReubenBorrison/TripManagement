 
import java.sql.*;
public class DbQuery extends DbConnect {
	
	String sql,errorMsg="error executing statement";
	String setFilters,val;
	
	public boolean loginAuthentication(String email,String pass)
	{ 	boolean returnValue=false;
		
		try
		{
				
				sql="USE tripmanagement;";
				myConn.createStatement().execute(sql);
				sql="SELECT EmailId,Pass FROM userdata WHERE EmailId=? AND Pass=?";
				preStmt=myConn.prepareStatement(sql);
				preStmt.setString(1,email);
				preStmt.setString(2, pass);
				ResultSet rObject=preStmt.executeQuery();
				
				while(rObject.next())
				{
					
					if(email.equals(rObject.getString("EmailId")) && pass.equals(rObject.getString("Pass")))
					{
						returnValue=true;
					}
				}
		}
		catch(SQLException e)
		{
			
			System.out.println("Database error: LoginAuthMethod");
			
		}
		return returnValue;
	}
	public boolean signUp(String name,String emailId,String pass,String gender,String DoB, String location)
	{
		int result;
		boolean returnValue=false;
		try
		{
			sql="INSERT INTO userdata (EmailId,Pass,Name,Gender,BirthDate,location) VALUES(?,?,?,?,?,?);";
			preStmt=myConn.prepareStatement(sql);
			preStmt.setString(1, emailId);
			preStmt.setString(2, pass);
			preStmt.setString(3, name);
			preStmt.setString(4, gender);
			preStmt.setString(5, DoB);
			preStmt.setString(6, location);
			result=preStmt.executeUpdate();
			if(result==1)
			{
				returnValue=true;
			}
		}
		catch(SQLException e)
		{
			System.out.println("error:Database: Signupmethod" );
			return false;
		}
		return returnValue;
	}
	
	public void searchAvailableLocation()
{
	try
	{
		sql="SELECT DISTINCT Source from Destination ";
		stmt=myConn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			
			System.out.println(rs.getString("Source"));	
		
		}
	}
	catch(SQLException e)
	{
		
		System.out.println("Error:Databse:availablelocation");
	}
}
	public void searchAvailableDestination(String location)
		{	try
			{
				sql="Select distinct DestinationName from Destination where source=?";
				preStmt=myConn.prepareStatement(sql);
				preStmt.setString(1, location);
				ResultSet rs=preStmt.executeQuery();
				while(rs.next())
				{
						System.out.println(rs.getString("DestinationName"));
								
					
				}
				
			}
			catch(SQLException e)
			{
				System.out.println("Error:Databse:availableDestination");
				
			}
			
		}
	public void searchNights(String location, String destination)
	{
		sql="SELECT Packages.Stay_Duration "  
				+ "from Packages "
				+ "Inner join destination "
				+ "ON Packages.Dest=destination.idDestination "
				+ "where destination.source='"+location
				+ "' AND destination.DestinationName='"+destination+"';";
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getInt("Stay_Duration"));
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Error:Database:searchNights");
		}
		
	}
	public void searchTransport(String location, String destination)
	{
		sql="SELECT Packages.Rail, packages.Flight, packages.bus FROM Packages " 
				+"Inner join destination "
				+ "on Packages.Dest=destination.idDestination "
				+"where destination.source='"+ location
				+ "' and destination.DestinationName='"+ destination +"'; ";
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{	
				
				if(rs.getInt("Flight")==0 && rs.getInt("Rail")==0  && rs.getInt("Bus")==0)
				{
					System.out.println("no transport found");
				}
				else if(rs.getInt("Flight")!=0 && rs.getInt("Rail")==0  && rs.getInt("Bus")==0)
				{
					System.out.println("Flight");
				}
				else if(rs.getInt("Flight")!=0 && rs.getInt("Rail")!=0  && rs.getInt("Bus")==0)
				{
					System.out.println("Flight\n"+"Rail");
				}
				else if(rs.getInt("Flight")!=0 && rs.getInt("Rail")==0  && rs.getInt("Bus")!=0)
				{
					System.out.println("Flight\n"+"Bus");
					
				}
				else if(rs.getInt("Flight")==0 && rs.getInt("Rail")!=0  && rs.getInt("Bus")==0)
				{
					System.out.println("Rail");
					
				}
				else if(rs.getInt("Flight")==0 && rs.getInt("Rail")!=0  && rs.getInt("Bus")!=0)
				{
					System.out.println("Rail\n"+"Bus");
					
				}
				else if(rs.getInt("Flight")==0 && rs.getInt("Rail")==0  && rs.getInt("Bus")!=0)
				{
					System.out.println("Bus");
					
				}
				else if(rs.getInt("Flight")!=0 && rs.getInt("Rail")!=0  && rs.getInt("Bus")!=0)
				{
					System.out.println("Flight\n"+"Rail\n"+"Bus");
				}
				
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("error:database:searchTransport");
		}
		
	}
	public void searchHotel(String location, String destination)
	{
		sql="SELECT hoteldata.idHotelData,hoteldata.HotelName,hoteldata.Category "
				+ "from hoteldata "
				+ "inner join destination "
				+ "on hoteldata.DestHotel=destination.idDestination "
				+ "where destination.source='"+location
				+ "' and destination.DestinationName='"+destination+"';";
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("idHotelData")+" "+rs.getString("HotelName")+" "+rs.getString("Category")+" Star");
			}
		}
		catch(SQLException e)
		{
			System.out.println("error:database:searchhotel");
		}
	}
	public void searchSightSeeing(String location,String destination)
	{	
		sql="select sightseeing.sightSeeing, sightseeing.idSightSeeing "
				+ "from sightseeing "
				+ "inner join destination "
				+ "on sightseeing.destSight=destination.idDestination "
				+ "where destination.source='"+location
				+"' and destination.DestinationName='"+destination+"';";
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("idSightSeeing")+ " "+rs.getString("sightSeeing"));
				
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("error:database:searchsightseeing");
		}
	}
	public void totalPackageCost(String location, String destination, String hotelid,String Transport,int nights,int persons)
	{
		int totalCost,sightSeeingCost;
		sql="select hoteldata.hotelName,hoteldata.HotelCost, "+Transport+"data."+Transport+"Cost " 
				+"from hoteldata "
				+"inner join "+Transport+"data "
				+ "on "+Transport+"data.destid=hoteldata.DestHotel "
				+ "inner join destination "
				+ "on destination.idDestination="+Transport+"data.destid "
				+ "where destination.source='"+location
				+ "' and destination.DestinationName='"+destination
				+ "' and hoteldata.idHotelData="+hotelid+";";
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				sightSeeingCost=this.sightSeeingCost(location, destination);
				totalCost=(nights*Integer.parseInt(rs.getString("hotelCost")))+(2*persons*Integer.parseInt(rs.getString(Transport+"Cost"))) 
						+sightSeeingCost;
				System.out.println("Hotel Name: "+rs.getString("hotelName")+"\nHotelcost per night: "
						+rs.getString("hotelCost")+
						"\nTransportation cost per person: "
						+rs.getString(Transport+"Cost")+
						"\nSightseeing total cost: "
						+sightSeeingCost
						+"\nNights "
						+nights
						+"\nNo. of persons "
						+persons);
				System.out.println("\nTotal Cost of the Package comes to be: "+totalCost+" Euros");
				System.out.println("-------------------------------------------------------------");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("error:Database:totalpackagecost");
		}
		
	}
	public void showAllPackages(String location)
	{
		sql="select * from destination where source='"+location+"';";
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				
				System.out.println(rs.getString("DestinationName")+" | "+rs.getString("source")+" | "+rs.getString("Description"));
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("error:database:showallpackages");
		}
	}
	public void showUserInfo(String emailId)
	{
		sql="select * from userdata where EmailId='"+emailId+"';";
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("Name"));
				//System.out.println(rs.getString("location"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("error:database:showuserinfo");
		}
	}
	public void updateName(String name,String email)
	{
		sql="UPDATE userdata SET name=? WHERE EmailId=?;";
		try
		{
			preStmt=myConn.prepareStatement(sql);
			preStmt.setString(1, name);
			preStmt.setString(2, email);
			int result=preStmt.executeUpdate();
			
			if(result==1)
			{
				System.out.println("name updated");
			}
			else
			{
				System.out.println("oops some error occured");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("error:database:updatename");
		}
		
	}
	public void updatePass(String pass,String email)
	{
		sql="UPDATE userdata SET Pass=? WHERE EmailId=?;";
		try
		{
			preStmt=myConn.prepareStatement(sql);
			preStmt.setString(1, pass);
			preStmt.setString(2, email);
			int result=preStmt.executeUpdate();
			
			if(result==1)
			{
				System.out.println("password changed");
			}
			else
			{
				System.out.println("oops some error occured");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("error:database:updatename");
		}
	}
	public void updateLocation(String location,String email)
	{
		sql="UPDATE userdata SET location=? WHERE EmailId=?;";
		try
		{
			preStmt=myConn.prepareStatement(sql);
			preStmt.setString(1, location);
			preStmt.setString(2, email);
			int result=preStmt.executeUpdate();
			
			if(result==1)
			{
				System.out.println("location updated");
			}
			else
			{
				System.out.println("oops some error occured");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("error:database:updatename");
		}
	}
	public int sightSeeingCost(String location, String destination)
	{
		sql="select sightseeing.SightSeeingCost " 
				+"from sightseeing "
				+ "inner join destination "
				+ "on destination.idDestination=sightseeing.DestSight "
				+ "where destination.source='"+location
				+"'and destination.DestinationName='"+destination+"';";
		int totalCost=0;
		try
		{
			stmt=myConn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				totalCost=totalCost+rs.getInt("SightSeeingCost");
			}
		}
		catch(SQLException e)
		{
			System.out.println("error:database:sightseeingcost");
		}
		return totalCost;
	}
}
