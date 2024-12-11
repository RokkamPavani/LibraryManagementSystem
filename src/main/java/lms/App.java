package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App
{
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args)
	{
		while(true)
		{
			System.out.println("\n"+"select option");
			System.out.println("1) Add Book"+"\t \t \t"+"2) Display All Books"+"\t"+"3) Display Books by Author Name"+"\t \t"+"4) Display Books by Title");
			System.out.println("5)Update Book price by ID"+"\t"+"6) Delete Book by ID"+"\t"+"7)Update Quantity by ID"+"\t \t \t"+"8)Exit");
			System.out.println("9)Search Book by keywords"+"\t"+"10) Search Book by price");
			int key=s.nextInt();
			switch(key)
			{
			case 1:
				addBook();
				break;
			case 2:
				displayAllBooks();
				break;
			case 3:
				displayBookByAuthor();
				break;
			case 4:
				displayBookByTitle();
				break;
			case 5:
				updateBookByPrice();
				break;
			case 6:
				deleteBookByID();
				break;
			case 7:
				updateQuantityByID();
				break;
			case 8:
				System.exit(0);
				break;
			case 9:
				searchBookByKeyWords();
				break;
			case 10:
				searchBookByPrice();
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}
	private static void searchBookByPrice() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			Statement st=con.createStatement();
//			System.out.println("Enter >=price or <=price");
			ResultSet rs=st.executeQuery("select * from book where price '"+s.next()+"'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void searchBookByKeyWords() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			Statement st=con.createStatement();
			System.out.println("Enter KeyWord");
			ResultSet rs=st.executeQuery("select * from book where title like'"+s.next()+"%'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void updateQuantityByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			PreparedStatement st=con.prepareStatement("update book set quantity =? where  id= ?");
			System.out.println("Enter Book quantity");
			st.setDouble(1, s.nextInt());
			System.out.println("Enter id");
			st.setInt(2, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated...");	
			displayAllBooks();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}		
	}
	private static void deleteBookByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			PreparedStatement st=con.prepareStatement("delete from book where id= ?");
			System.out.println("Enter id");
			st.setInt(1, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated...");	
			displayAllBooks();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void updateBookByPrice() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			PreparedStatement st=con.prepareStatement("update book set price =? where  id= ?");
			System.out.println("Enter Book price");
			st.setDouble(1, s.nextDouble());
			System.out.println("Enter id");
			st.setInt(2, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated...");	
			displayAllBooks();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}
	private static void displayBookByTitle() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			Statement st=con.createStatement();
			System.out.println("Enter Title");
			ResultSet rs=st.executeQuery("select * from book where title='"+s.next()+"'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	private static void displayAllBooks() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from book");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		
	}
	private static void displayBookByAuthor() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			Statement st=con.createStatement();
			System.out.println("Enter Author Name");
			ResultSet rs=st.executeQuery("select * from book where author='"+s.next()+"'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void addBook() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","username","pw");
			PreparedStatement st=con.prepareStatement("insert into book values(?,?,?,?,?)");
			System.out.print("Enter id \t");
			st.setInt(1, s.nextInt());
			System.out.print("Enter title \t");
			st.setString(2, s.next());
			System.out.print("Enter price \t");
			st.setDouble(3, s.nextDouble());
			System.out.print("Enter Author \t");
			st.setString(4, s.next());
			System.out.print("Enter quantity \t");
			st.setInt(5, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");	
			displayAllBooks();
			st.close();
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
}
