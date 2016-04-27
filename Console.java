 
import java.util.Scanner;
import java.util.InputMismatchException;
public class Console {
		Application app=new Application();
		
		int i,nights,persons,sightSeeing;
		String emailId, pass, destination,location,name,DoB,pass2,transport,hotelid,desc,gender;
		Scanner reader= new Scanner(System.in); //System.in because it is console based
		public void mainScreen()
		{
			try
			{
				System.out.println("--------Welcome to Trip Management System----------");
				System.out.println("Enter your Choice");
				System.out.println("1 Search for a package\n" +"2 customise Packages (login required) \n"+ "3 Login/sign up \n"
						+ "4 Exit");
				System.out.println("Enter your choice: ");
				i=Integer.parseInt(reader.next());
				switch(i)
				{
					case 1: //search for a package
					{
						this.viewAllPackages();
						break;
						
					}
					case 2: //customise packages
					{
						this.searchPackage(false);
						
						break;
					}
					case 3:// Login/Sign up
					{
						this.loginSignUp();
						break;
					}
					case 4:// exit the program
					{
						System.exit(-1); //exits the program
					}
					default:
					{
						System.out.println("Invalid input");
						this.mainScreen();
					}
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("invalid input");
				this.mainScreen();
			}
			
			
			
		}
		public void searchPackage(boolean login)//customise package 
		{
			if(login==true)
			{
				try
				{
					System.out.println("----------Customise a package----------");
					System.out.println("Enter location from the following: ");
					app.searchAvailableLocation();
					location=reader.next();
					System.out.println("Enter the destination from the following ");
					app.searchAvailableDestination(location);
					System.out.print("Destination: ");
					destination=reader.next();
					System.out.println("number of nights");
					app.searchNights(location, destination);
					nights=Integer.parseInt(reader.next());
					System.out.println("no. of persons");
					persons=Integer.parseInt(reader.next());
					System.out.println("type of hotel");
					app.searchhotel(location, destination);
					hotelid=reader.next();
					System.out.println("Mode of transport");
					app.searchTransport(location, destination);
					transport=reader.next();
					System.out.println("-----------------------------------------");
					System.out.println("package details\n");
					System.out.println("sightseeing Available");
					app.searchSightSeeing(location,destination);
					System.out.println("\n");
				
					app.totalPackageCost(location, destination, hotelid, transport,nights,persons);
					this.custBookOption();	
				}
			
				catch(InputMismatchException e)
				{
					System.out.println("Invalid Input");
					this.searchPackage(false);
				}
			}
			else
			{
				System.out.println("you need to login before accessing this function ");
				this.mainScreen();
			}
				
				//show the packages
				
			
		}
		public void sightSeeingData()
		{
			
		}
		public void viewAllPackages()//searches the packages
		{
			try
			{
				System.out.println("Enter your location: ");
				app.searchAvailableLocation();
				location=reader.next();
				app.showAllPackages(location);
				System.out.println("1. Customise and book a package\n2. back to main menu");
				i=Integer.parseInt(reader.next());
				switch(i)
				{
				case 1:
				{
					System.out.println("you need to login before customising");
					this.login();
				}
				case 2:
				{
					this.mainScreen();
				}
				default:
				{
					System.out.println("invalid option");
				}
				}
				
				//call the display all packages method 
			}
			catch(InputMismatchException e)
			{
				System.out.println("Invalid Input");
				this.viewAllPackages();
	
			}
		}
		public void loginSignUp()
		{

			try 
			{
				System.out.println("1 Login \n"+ "2 Sign up \n "+"Enter your choice: ");
				
				i=Integer.parseInt(reader.next());
				switch(i)
				{
					case 1:
					{
						this.login();
						break;
					}
					case 2:
					{
						this.signUp();
						break;
					}
					default:
					{
						this.loginSignUp();
					}
				}
				
				
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Invalid Input");
				this.loginSignUp();
			}
			
		}
		public void login()
		{
			try
			{
				System.out.println("Enter registered Email id:");
				emailId=reader.next();
				System.out.println("Enter your password:");
				pass=reader.next();
				if(app.login(emailId, pass))
				{
					this.overview(emailId);
				}
				else
				{
					System.out.println("invalid credentials");
					this.login();
				}
				//call the login method
			}
			catch(InputMismatchException e)
			{
				System.out.println("invalid input");
				
			}
		}
		public void signUp()
// start from here
		{
			try
			{
				System.out.println("----Signup----");
				System.out.println("Enter your name: ");
				name=reader.next();
				System.out.println("Enter your email: ");
				emailId=reader.next();
				while(!app.emailValidation(emailId))
				{
					System.out.println("Enter your email: ");
					emailId=reader.next();
				}
				System.out.println("Enter a password: ");
				System.out.println("should contain an upper case letter, special character and must be of 8 or more characters");
				
				pass=reader.next();
				while(!app.passValidation(pass))
				{
					System.out.println("invalid pass \n"+ "Enter your pass: ");
					pass=reader.next();
				}
				System.out.println("Re enter the password: ");
				pass2=reader.next();
				while(!app.passwordMatch(pass,pass2))
				{
					System.out.println("Passwords doesnt Match \n"+ "Re-Enter your pass: ");
					pass2=reader.next();
				}
				//call password check method
				System.out.println("Enter your date of birth (in yyyy-mm-dd format): ");
				DoB=reader.next();
				System.out.println("Current location: ");
				app.searchAvailableLocation();
				location=reader.next();
				System.out.println("Gender(m/f)");
				gender=reader.next();
				app.signUp(emailId, pass, name, gender, DoB, location);
				//call sign up method
			}
			catch(InputMismatchException e)
			{
				System.out.println("Invalid Input");
				this.signUp();
			}
		}
		public void custBookOption()
		{
			System.out.println("1. Save this package\n2. Book this Package\n3. go to main menu");
			i=Integer.parseInt(reader.next());
			switch(i)
			{
				case 1:
				{
					this.savePackage(true);
					break;
				}
				case 2:
				{
					this.bookPackage();
					break;
				}
				case 3:
				{
					//this.mainScreen();
					break;
				}
				default:
				{
					System.out.println("invalid option");
					this.custBookOption();
				}
			}
		}
		public void bookPackage()
		{
			System.out.println("Package booked successfully");
		}
		public void overview(String emailId)
		{
			System.out.print("welcome ");
			app.showUserInfo(emailId);
			overviewData(emailId);
		}
		public void savePackage(boolean login)
	{
		if(login==true)
		{
			System.out.println("package saved");
		}
		else
		{
			System.out.println("you need to login");
			this.login();
		}
	}
		public void overviewData(String email)
		{
			System.out.println("\n1. edit profile\n2. customize package\n3. search Package\n4.log out");
			System.out.println("Enter a option from above: ");
			i=Integer.parseInt(reader.next());
			switch(i)
			{
				case 1:
				{
					this.editUserData(true, email);
					break;
				}
				case 2:
				{
					this.searchPackage(true);
					break;
				}
				case 3:
				{
					this.viewAllPackages();
					break;
				}
				case 4:
				{
					
					System.out.println("Logged out successfully");
					this.mainScreen();
					break;
				}
				default:
				{
					System.out.println("Invalid Input");
					this.overviewData(email);
					break;
				}
			}
		}
		public void editUserData(boolean login, String email)
		{
			if(login==true)
			{
				System.out.println("what do you want to edit?\n1 name\n2 password\n3 location");
				i=Integer.parseInt(reader.next());
				switch(i)
				{
					case 1:
					{
						System.out.println("Enter your new name");
						name=reader.next();
						app.updateName(name, email);
						//update data in dbquery
						break;
					}
					case 2:
					{
						System.out.println("Enter new password");
						System.out.println("should contain an upper case letter, special character and must be of 8 or more characters");
					pass=reader.next();
						while(!app.passValidation(pass) )
						{
							System.out.println("invalid pass \n"+ "Enter your pass: ");
							pass=reader.next();
											
						}
						System.out.println("Enter the password again");
						pass2=reader.next();
					
						while(!app.passwordMatch(pass, pass2))
						{
							System.out.println("Password doesnot match");
							pass2=reader.next();
							
						}
						app.updatePass(pass, email);
						//update in the database
						break;
					}
					case 3:
					{
						System.out.println("Enter your new location: ");
						app.searchAvailableLocation();
						location=reader.next();
						app.updateLocation(location, email);
						break;
						//update location in the database
					}
					default:
					{
						System.out.println("Invalid option");
						this.editUserData(true,email);
						break;
					}
				}
			}
			
		}
}


	


