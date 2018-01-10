package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import org.apache.commons.dbcp2.BasicDataSource;



import enumerator.ShowType;
import tools.DateTools;

/**
 * This class drives the base functions of using the SQL server.
 * This is a singleton.
 * @author Sadi Wali
 *
 */
public class DatabaseDriverA {
	
	private static DatabaseDriverA instance = null; // the instance class
	
	// credentials
	private final String user = "sadiw";
	private final String passw = "frgtwhy11";

	
	private BasicDataSource dataSource = new BasicDataSource();
	
	private Connection connect= null; // the connection to use
    private Statement statement = null; // the statement to send
    private PreparedStatement preparedStatement = null; // the prepared statement to send
    private ResultSet resultSet = null; // the results from server
    
    /**
     * Constructor for DatabaseDriverA
     */
    public DatabaseDriverA() {
    	// set up the connection pool
		this.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		this.dataSource.setUrl("jdbc:mysql://localhost/shows");
		this.dataSource.setUsername(user);
		this.dataSource.setPassword(passw);
    }

    /**
     * Insert a single new show as a row into the table watchedshows.
     * Do not use this for inserting multiple shows in a loop.
     * Insertion of multiple shows should only be used once for database initializing
     * and you should write your own function for that.
     * 
     * @param title is the title of the show.
     * @param comment is the comment given to the show.
     * @param type is the type of show, must be one of types in enum.
     * @param rating is a rating given out of 10
     * @return true if successful, false otherwise.
     */
    public boolean insertNewShow(String title, String comment, ShowType type, int rating) {
    	    	
		try {
			connect = this.dataSource.getConnection();
		} catch (SQLException e) {
			// connection could not be made, so return false
			e.printStackTrace();
			return false;
		}
		
    	String query = "insert into shows.watchedshows (title, comment, type, rating, date) values (?, ?, ?, ?, ?)";
    	
    	// connection established, continue
    	try {

	    	this.preparedStatement = this.connect.prepareStatement(query);
	    	this.preparedStatement.setString(1,  title);
	    	this.preparedStatement.setString(2,  comment);
	    	this.preparedStatement.setString(3,  type.toString().toLowerCase());
	    	// ensure rating is between 0 and 10
	    	if (rating < 0) {
	    		rating = 0;
	    	} else if (rating > 10) {
	    		rating = 10;
	    	}
	    	this.preparedStatement.setString(4, Integer.toString(rating));
	    	this.preparedStatement.setString(5,  DateTools.getFormattedDate());
	    
	    	this.preparedStatement.executeUpdate();
	    	
    	} catch (SQLException e) {
    		// could not do prepared statement
    		e.printStackTrace();
    		return false;
    	} finally {
    		// close the sql connections
    		close();
    	}
    	
    	// prepared statement completed
    	return true;
    }
    
    /**
     * Close the connection to the SQL database.
     */
    private void close() {
    	
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }

        } catch (Exception e) {
        	System.out.println(e.getStackTrace());
        }
    }
    
    /**
     * Given a nested ArrayList of all lines in file data, read each line and insert into table.
     * @param textDb the databaes in text representation.
     */
    public void importFileToDatabase(ArrayList<ArrayList<String>> textDb) {
    	try {
			this.connect = this.dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String query = "insert into shows.watchedshows (title, comment, type, rating, date) values (?, ?, ?, ?, ?)";
    	
    	try {
    		this.preparedStatement = this.connect.prepareStatement(query);
    		
	    	for (ArrayList<String> line : textDb) {
	    		this.preparedStatement.setString(1, line.get(0)); // title
	    		this.preparedStatement.setString(2, line.get(1)); // comment
	    		this.preparedStatement.setString(3, line.get(2)); // type
	    		this.preparedStatement.setString(4, line.get(3)); // rating
	    		
	    		String date = line.get(4);
	    		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); // your template here
	    		java.util.Date dateStr = formatter.parse(date);
	    		java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
	    		
	    		this.preparedStatement.setDate(5, dateDB); // date
	    		this.preparedStatement.executeUpdate();
	    		
	    	}
	    	System.out.println("done. Closing.");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
    		// done
    		close();
    	}
    
    }
    
    /**
     * Get the instance of the Database driver. 
     * @return the DatabaseDriverA instance.
     */
    public static DatabaseDriverA getInstance() {
    	if (instance == null) {
    		instance = new DatabaseDriverA();
    	}
		return instance;
    }

}
