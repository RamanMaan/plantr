package comp3350.plantr.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import comp3350.plantr.model.Plant;

/**
 * Created by KevinD on 6/27/2017.
 */

public class DataAccessObject implements DatabaseInterface{


	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;
	private String dbType;

	private ArrayList<Plant> students;

	private String cmdString;
	private int updateCount;
	private String result;
	private static String EOF = "  ";

	public void open(String dbPath) {
		String url;
		try {
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();

		} catch (Exception e) {
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public

	public Plant getPlant(int id); //Return a Plant Object by id

	public Plant getPlant(String name); //Return a Plant Object by name

	public List<Plant> getAllPlants(); //Return an ArrayList of all Plant Objects

}
