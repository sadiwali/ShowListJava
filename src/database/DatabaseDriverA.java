package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;


import org.apache.commons.dbcp2.BasicDataSource;



import enumerator.ShowType;
import objects.Show;
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
     * Get the number of entries or shows in database.
     * @return the number of entries in database.
     */
    public int getNumEntries() {
    	int numEntries = 0;
    	try {
    		this.connect = this.dataSource.getConnection();
    		this.preparedStatement = this.connect.prepareStatement("select count(*) from shows.watchedshows");
    		this.resultSet = this.preparedStatement.executeQuery();
    		this.resultSet.next(); // move the cursor to data
    		numEntries = this.resultSet.getInt(1);
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		close();
    	}
    	return numEntries;
    }

    /**
     * Insert a single new show as a row into the table watched shows.
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
    public boolean insertNewShow(Show show) {	
    	String query = "insert into shows.watchedshows (title, comment, type, rating, date) values (?, ?, ?, ?, ?)";
    	
    	// connection established, continue
    	try {
    		this.connect = this.dataSource.getConnection();
	    	this.preparedStatement = this.connect.prepareStatement(query);
	    	this.preparedStatement.setString(1,  show.getTitle());
	    	this.preparedStatement.setString(2,  show.getComments());
	    	this.preparedStatement.setString(3,  show.getType().toString().toLowerCase());
	    	this.preparedStatement.setInt(4, show.getRating());
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
     * Get an array list of all shows stored in database
     * @return
     */
    public ArrayList<Show> getShows() {    	
    	// connection established
    	ArrayList<Show> showsToRet= new ArrayList<>(); // populate this with show objects
    	String query = "select * from shows.watchedshows";
    	try {
    		this.connect = this.dataSource.getConnection();
	    	this.preparedStatement = this.connect.prepareStatement(query);	
			this.resultSet = this.preparedStatement.executeQuery(query);
			
			while (this.resultSet.next()) {
				String title = this.resultSet.getString("title");
				String comment = this.resultSet.getString("comment");
				ShowType type = ShowType.getShowType(this.resultSet.getString("type"));
				int rating = this.resultSet.getInt("rating");
				LocalDate wDate = this.resultSet.getDate("date").toLocalDate();
				
				// create and add the show objects
				showsToRet.add(new Show(title, comment, type, rating, wDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
    	// return arraylist
    	return showsToRet;    	
    }
    
    /**
     * Given a nested ArrayList of all lines in file data, read each line and insert into table.
     * @param textDb the databaes in text representation.
     */
    public void importFileToDatabase(ArrayList<ArrayList<String>> textDb) {
    	String query = "insert into shows.watchedshows (title, comment, type, rating, date) values (?, ?, ?, ?, ?)";
    	
    	try {
    		this.connect = this.dataSource.getConnection();
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
