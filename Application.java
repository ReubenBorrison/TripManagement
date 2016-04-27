 
public class Application 
{
	DbQuery db=new DbQuery();
	
	
	
	public boolean containsUpperCase(String stmt)
	{ 	boolean returnValue=false;
		for(int i=0;i<stmt.length();i++)
		{ 
			 for(int j=65;j<=90;j++)
			 {
				 if(stmt.charAt(i)==(char)j)
				 {
					 returnValue=true;
				 }
				 
			 }
		}
		return returnValue;
	}
	public boolean containsSpecialChar(String stmt)
	{
		boolean returnValue=false;
		for(int i=0;i<stmt.length();i++)
		{ 
			 for(int j=33;j<=47;j++)
			 {
				 if(stmt.charAt(i)==(char)j || stmt.charAt(i)==64)
				 {
					 returnValue=true;
				 }
				 
			 }
		}
		return returnValue;
	}
	public boolean containsNumericValue(String stmt)
	{
		boolean returnValue=false;
		for(int i=0;i<stmt.length();i++)
		{ 
			 for(int j=48;j<=57;j++)
			 {
				 if(stmt.charAt(i)==(char)j || stmt.charAt(i)==64)
				 {
					 returnValue=true;
				 }
				 
			 }
		}
		return returnValue;
	}
	public boolean passwordMatch(String pass1, String pass2)
	{
		if(pass1.equals(pass2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean passValidation(String pass)
	{
		if((pass.length()>=8) && !pass.isEmpty() && this.containsNumericValue(pass) && this.containsUpperCase(pass) && this.containsSpecialChar(pass))//consists of uppercase letters
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean emailValidation(String email)
	{
		char ampersand='@';
		String dotCom=".com";
		if(email.indexOf(ampersand)==-1 && email.indexOf(dotCom)==-1)
		{
			return false;
			
		}
		else
		{
			return true;
		}
	}
	public boolean login (String e, String p)
	{	boolean returnValue=false;
		if(db.loginAuthentication(e,p))
		{
			System.out.println("login Successful");
			returnValue=true;//redirect to the dashboard of the user
		}
		else
		{
			System.out.println("login unSuccessful");
			returnValue=false;//give an error message
		}
		return returnValue;
	}
	public void signUp(String email, String password, String name, String gender,String DoB, String location )
	{
		if(db.signUp(name,email,password,gender,DoB,location))
		{
			System.out.println("user registered successfully");
		}
		else
		{
			System.out.println("registration unsuccessfull");
		}
			 // insert command should be written to enter the details into user_data table.
	}
	public void searchPackage(String location, double b, String d, int nop, String sp, String e, String mot )
	{
		
		double budget=b;
		String duration= d;
		int num_of_person= nop;
		String start_point=sp;
		String email=e;
			String mode_of_travel=mot;
			
			//select query to fetch data from package table.
			//After this console will ask for customization.
			
	 }
	public void searchAvailableLocation()
	{
		db.searchAvailableLocation();
	}
	public void searchAvailableDestination(String Location)
	{
		db.searchAvailableDestination(Location);
	}
	public void searchNights(String location, String destination)
	{
		db.searchNights(location, destination);
	}
	public void searchTransport(String location, String destination)
	{
		db.searchTransport(location, destination);
	}
	public void searchhotel(String location, String destination)
	{
		System.out.println("Hotel id | Hotel Name | Category(stars");
		db.searchHotel(location, destination);
	}
	public void searchSightSeeing(String location, String destination)
	{
		db.searchSightSeeing(location, destination);
	}
	public void totalPackageCost(String location, String destination,String hotelid,String Transport,int nights, int persons)
	{
		db.totalPackageCost(location, destination, hotelid, Transport,nights, persons);
	}
	public void showAllPackages(String location)
  {
		System.out.println("Destination"+" | "+"From"+" | "+"Description");
		db.showAllPackages(location);
  }
	public void showUserInfo(String emailId)
	{
		db.showUserInfo(emailId);
	}
	public void cutomizePackage(float c, String t)
   {
	   double cost=c;
	   String type_of_hotel=t;
	   // more parameters can be added 
	   // matching has to be performed with data base w.r.t desired entries of user.
	   
   }
	public void updateName(String name,String email)
	{
		db.updateName(name, email);
	}
	public void updatePass(String pass,String email)
	{
		db.updatePass(pass, email);
	}
	public void updateLocation(String location,String email)
	{
		db.updateLocation(location, email);
	}
  
	
	
}






