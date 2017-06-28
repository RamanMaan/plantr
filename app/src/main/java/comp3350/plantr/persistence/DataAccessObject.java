package comp3350.plantr.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import comp3350.plantr.model.DifficultyType;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.TemperatureRange;

/**
 * Created by KevinD on 6/27/2017.
 */

public class DataAccessObject implements DatabaseInterface{


	private Statement st1;
	private Connection c1;
	private ResultSet rs1;

	private String dbName;
	private String dbType;

	private String cmdString;

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	public void open(String dbPath) {
		String url;
		try {
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();

		} catch (Exception e) {
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close() {
		try {	// commit all changes to the database
			cmdString = "shutdown compact";
			rs1 = st1.executeQuery(cmdString);
			c1.close();
		} catch (Exception e) {
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}


	//Return a Plant Object by id
	public Plant getPlant(int id){

		Plant plant = null;

		String plantName, plantDesc, plantIMG;
		Float minTempRange, maxTempRange;
		int plantID, wateringPeriod;

		try
		{
			cmdString = "Select * from Plants where PlantID=" + id;
			rs1 = st1.executeQuery(cmdString);

			while (rs1.next()){
				plantID = rs1.getInt("PlantID");
				plantName = rs1.getString("PlantName");
				plantDesc = rs1.getString("PlantDesc");
				plantIMG = rs1.getString("PlantIMG");
				minTempRange = rs1.getFloat("MinTempRange");
				maxTempRange = rs1.getFloat("MaxTempRange");
				wateringPeriod = rs1.getInt("MaxTempRange");

				plant = new Plant(plantID, plantName, plantDesc, plantIMG, new TemperatureRange(new Temperature(minTempRange), new Temperature(maxTempRange)), wateringPeriod);
			}

			rs1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return plant;
	}

	//Return a Plant Object by name
	public Plant getPlant(String name){
		Plant plant = null;

		String plantName, plantDesc, plantIMG;
		Float minTempRange, maxTempRange;
		int plantID, wateringPeriod;

		try
		{
			cmdString = "Select * from Plants where PlantName=" + name;
			rs1 = st1.executeQuery(cmdString);

			while (rs1.next()){
				plantID = rs1.getInt("PlantID");
				plantName = rs1.getString("PlantName");
				plantDesc = rs1.getString("PlantDesc");
				plantIMG = rs1.getString("PlantIMG");
				minTempRange = rs1.getFloat("MinTempRange");
				maxTempRange = rs1.getFloat("MaxTempRange");
				wateringPeriod = rs1.getInt("MaxTempRange");

				plant = new Plant(plantID, plantName, plantDesc, plantIMG, new TemperatureRange(new Temperature(minTempRange), new Temperature(maxTempRange)), wateringPeriod);
			}

			rs1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return plant;
	}

	//Return an ArrayList of all Plant Objects
	public List<Plant> getAllPlants(){
		Plant plant = null;

		String plantName, plantDesc, plantIMG;
		Float minTempRange, maxTempRange;
		int plantID, wateringPeriod;

		List<Plant> plantsResult = new ArrayList<>();

		try
		{
			cmdString = "Select * from PLANTS";
			rs1 = st1.executeQuery(cmdString);

			while (rs1.next()){
				plantID = rs1.getInt("PlantID");
				plantName = rs1.getString("PlantName");
				plantDesc = rs1.getString("PlantDesc");
				plantIMG = rs1.getString("PlantIMG");
				minTempRange = rs1.getFloat("MinTempRange");
				maxTempRange = rs1.getFloat("MaxTempRange");
				wateringPeriod = rs1.getInt("MaxTempRange");

				plant = new Plant(plantID, plantName, plantDesc, plantIMG, new TemperatureRange(new Temperature(minTempRange), new Temperature(maxTempRange)), wateringPeriod);
				plantsResult.add(plant);
			}

			rs1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return plantsResult;
	}


	public String checkWarning(Statement st, int updateCount) {
		String result;

		result = null;
		try {
			SQLWarning warning = st.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		} catch (Exception e) {
			result = processSQLError(e);
		}
		if (updateCount != 1) {
			result = "Tuple not inserted correctly.";
		}
		return result;
	}

	public String processSQLError(Exception e) {
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}

}
