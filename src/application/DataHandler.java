package application;

import java.sql.*;
import java.util.*;

public class DataHandler {
	
	public DataHandler()
	{
		
	}
	
	//Connect to local database
	public static void openLocalDB()
	{
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
	
	public static ArrayList<Vehicle> getSavedVehicles()
	{
		ArrayList<Vehicle> vehicles=new ArrayList<Vehicle>();
		
		return vehicles;
	}
	
	public static void saveVehicles()
	{
		
	}

}
