package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DataHandler {

	private static Connection c = null;

	public DataHandler()
	{
		
	}
	
	//Connect to local database
	public static void openLocalDB()
	{
	    try {
	    	Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			createAndPopulateCategoryTable(c);
			createVehicleTable(c);
			createRentalTable(c);
			createCostTable(c);

	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}

	//Table Category: Creates and Populate id needed
	static void createAndPopulateCategoryTable(Connection c)
	{
		//sqlite ODBC Driver on cmd: http://www.ch-werner.de/sqliteodbc/
		//See tables:  SELECT name FROM sqlite_master WHERE type='table';
		//Set Project Encoding with: File -> Settings -> Editor -> File Encodings -> Set Project Encoding to "UTF-8"
		try {
			c.createStatement().execute("CREATE TABLE IF NOT EXISTS Category (id INT PRIMARY KEY, name TEXT NOT NULL );");

			if (c.createStatement().executeQuery("SELECT COUNT(*) FROM Category;").getInt(1) == 0 )
			{
				c.createStatement().execute("INSERT INTO Category (id, name) values (1, 'Μηχανάκι')");
				c.createStatement().execute("INSERT INTO Category (id, name) values (2, 'Αυτοκίνητο')");
				System.out.println("Table Category created and populated");
			}


			//ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Category ;");
			//while(rs.next())
			//{
			//	System.out.println( rs.getInt(1) + " : " + rs.getString(2));
			//}

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	//Table Vehicle: Creates table if not exists
	static void createVehicleTable(Connection c)
	{
		try {
			c.createStatement().execute("CREATE TABLE IF NOT EXISTS Vehicle " +
					"( id INT PRIMARY KEY, " +
					"  category_id INT NOT NULL," +
					"  plate TEXT NOT NULL," +
					"  totalRentalDays INT DEFAULT 0," +
					"  pricePerDay REAL DEFAULT 0," +
					"  buyCost REAL default 0 );");

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	//Table Rental: Creates table if not exists
	static void createRentalTable(Connection c)
	{
		try {
			c.createStatement().execute("CREATE TABLE IF NOT EXISTS Rental " +
					"( id INT PRIMARY KEY, " +
					"  moto_id INT NOT NULL," +
					"  rented_from DATETIME," +
					"  rented_to DATETIME," +
					"  pricePerDay REAL DEFAULT 0 );");

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	//Table Cost: Creates table if not exists
	static void createCostTable(Connection c)
	{
		try {
			c.createStatement().execute("CREATE TABLE IF NOT EXISTS Cost " +
					"( id INT PRIMARY KEY, " +
					"  moto_id INT NOT NULL," +
					"  price REAL DEFAULT 0 ," +
					"  description TEXT NOT NULL);");

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}


	public static ObservableList<Vehicle> getSavedVehicles()
	{
		ObservableList<Vehicle> vehicles= FXCollections.observableArrayList();

		try {
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Vehicle ;");
			while(rs.next())
			{
				vehicles.add(new Moto(rs.getInt("id"), rs.getString("plate"), rs.getInt("totalRentalDays"), rs.getDouble("pricePerDay"), rs.getDouble("buyCost"), null));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return vehicles;
	}
	
	public static void saveVehicles()
	{
		
	}

	/** Generates and executes an INSERT statement to create a Moto in Vehicle table  */
	public static void insertMoto(Moto moto)
	{
		try {
			int nextId = c.createStatement().executeQuery("SELECT MAX(id) FROM Vehicle").getInt(1) + 1;
			c.createStatement().execute(                                                               //insert query
					"INSERT INTO Vehicle (id, category_id, plate, totalRentalDays, buyCost) VALUES ("+nextId+", 1, '"+moto.plate+"', "+moto.totalRentDays+", "+moto.buyCost+") ;");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Generates and executes a DELETE statement to delete a Moto in Vehicle table based on id  */
	public static void deleteMoto(Moto moto)
	{
		try {
			c.createStatement().execute("DELETE FROM Vehicle WHERE id = "+ moto.getId() +";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return c;
	}
}
