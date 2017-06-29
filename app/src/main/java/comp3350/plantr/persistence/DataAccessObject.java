package comp3350.plantr.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import comp3350.plantr.model.DifficultyType;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.TemperatureRange;

/**
 * Created by KevinD on 6/27/2017.
 */

public class DataAccessObject implements DatabaseInterface{


	private Statement st1, st2;
	private Connection c1;
	private ResultSet rs1, rs2;

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
			st2 = c1.createStatement();

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

		try {
			cmdString = "Select * from Plants";
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

	//Add a plant to the garden
	public void addPersonalPlant(PersonalPlant personalPlant){
		String values;

		try {
			values = personalPlant.getID()
					+", '" +personalPlant.getName()
					+"', '" +personalPlant.getType().getPlantID()
					+"'";
			cmdString = "Insert into Garden " +" Values(" +values +")";
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	//Return a PersonalPlant by Id
	public PersonalPlant getPersonalPlantByID(int ID){
		PersonalPlant plant = null;

		String personalPlantName;
		int personalPlantID, plantType;

		try
		{
			cmdString = "Select * from Garden where PersonalPlantID=" + ID;
			rs1 = st1.executeQuery(cmdString);

			while (rs1.next()){
				personalPlantID = rs1.getInt("PersonalPlantID");
				personalPlantName = rs1.getString("PlantName");
				plantType = rs1.getInt("PlantType");

				plant = new PersonalPlant( getPlant(plantType), personalPlantName, personalPlantID);
			}

			rs1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return plant;
	}

	//Return a list of all PersonalPlants
	public List<PersonalPlant> getAllPersonalPlants(){

		List<PersonalPlant> plantsResult = new ArrayList<>();
		PersonalPlant plant;
		String personalPlantName;
		int personalPlantID, plantType;

		try
		{
			cmdString = "Select * from Garden";
			rs2 = st2.executeQuery(cmdString);

			while (rs2.next()){
				personalPlantID = rs2.getInt("PersonalPlantID");
				personalPlantName = rs2.getString("PersonalPlantName");
				plantType = rs2.getInt("PlantID");

				plant = new PersonalPlant( getPlant(plantType), personalPlantName, personalPlantID);
				plantsResult.add(plant);
			}

			rs2.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return plantsResult;
	}


	public String processSQLError(Exception e) {
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}

}
