package comp3350.plantr.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseCloseFailureException;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;

/**
 * Created by KevinD on 6/27/2017.
 */

public class DataAccessObject implements DatabaseInterface {

	private Statement st1, st2;
	private Connection c1;
	private ResultSet rs1, rs2;

	private String dbName;
	private String dbType;

	private String cmdString;

	public DataAccessObject(String dbName) {
		this.dbName = dbName;
		this.dbType = "HSQL";
	}

	public void open(String dbPath) throws DatabaseStartFailureException {
		try {
			String url;

			// Setup for HSQL
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + DatabaseAccess.getDBPathName(); // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();

			System.out.println("Opened " + dbType + " database " + DatabaseAccess.getDBPathName());
		} catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
			throw new DatabaseStartFailureException(e);
		}
	}

	public void close() throws DatabaseCloseFailureException {
		try {    // commit all changes to the database
			cmdString = "shutdown compact";
			rs1 = st1.executeQuery(cmdString);
			c1.close();

			System.out.println("Closed " + dbType + " database " + dbName);
		} catch (SQLException e) {
			throw new DatabaseCloseFailureException(e);
		}
	}


	//Return a Plant Object by id
	public Plant getPlant(int id) throws SQLException {
		Plant plant = null;

		String plantName, plantDesc, plantIMG;
		Float minTempRange, maxTempRange;
		int plantID, wateringPeriod;

		cmdString = "Select * from Plants where PlantID=" + id;
		rs1 = st1.executeQuery(cmdString);

		while (rs1.next()) {
			plantID = rs1.getInt("PlantID");
			plantName = rs1.getString("PlantName");
			plantDesc = rs1.getString("PlantDesc");
			plantIMG = rs1.getString("PlantIMG");
			minTempRange = rs1.getFloat("MinTempRange");
			maxTempRange = rs1.getFloat("MaxTempRange");
			wateringPeriod = rs1.getInt("MaxTempRange");

			plant = new Plant.PlantBuilder(plantID)
					.name(plantName)
					.desc(plantDesc)
					.img(plantIMG)
					.tempRange(new Temperature(minTempRange), new Temperature(maxTempRange))
					.wateringPeriod(wateringPeriod)
					.make();
		}

		rs1.close();

		return plant;
	}

	//Return a Plant Object by name
	public Plant getPlant(String name) throws SQLException {
		Plant plant = null;

		String plantName, plantDesc, plantIMG;
		Float minTempRange, maxTempRange;
		int plantID, wateringPeriod;

		cmdString = "Select * from Plants where PlantName=" + name;
		rs1 = st1.executeQuery(cmdString);

		while (rs1.next()) {
			plantID = rs1.getInt("PlantID");
			plantName = rs1.getString("PlantName");
			plantDesc = rs1.getString("PlantDesc");
			plantIMG = rs1.getString("PlantIMG");
			minTempRange = rs1.getFloat("MinTempRange");
			maxTempRange = rs1.getFloat("MaxTempRange");
			wateringPeriod = rs1.getInt("MaxTempRange");

			//				plant = new Plant(plantID, plantName, plantDesc, plantIMG, new TemperatureRange(new Temperature(minTempRange), new Temperature(maxTempRange)), wateringPeriod);
			new Plant.PlantBuilder(plantID)
					.name(plantName)
					.desc(plantDesc)
					.img(plantIMG)
					.tempRange(new Temperature(minTempRange), new Temperature(maxTempRange))
					.wateringPeriod(wateringPeriod)
					.make();
		}

		rs1.close();

		return plant;
	}

	//Return an ArrayList of all Plant Objects
	public List<Plant> getAllPlants() throws SQLException {
		Plant plant = null;

		String plantName, plantDesc, plantIMG;
		Float minTempRange, maxTempRange;
		int plantID, wateringPeriod;

		List<Plant> plantsResult = new ArrayList<>();

		cmdString = "Select * from Plants";
		rs1 = st1.executeQuery(cmdString);

		while (rs1.next()) {
			plantID = rs1.getInt("PlantID");
			plantName = rs1.getString("PlantName");
			plantDesc = rs1.getString("PlantDesc");
			plantIMG = rs1.getString("PlantIMG");
			minTempRange = rs1.getFloat("MinTempRange");
			maxTempRange = rs1.getFloat("MaxTempRange");
			wateringPeriod = rs1.getInt("MaxTempRange");

			plant = new Plant.PlantBuilder(plantID)
					.name(plantName)
					.desc(plantDesc)
					.img(plantIMG)
					.tempRange(new Temperature(minTempRange), new Temperature(maxTempRange))
					.wateringPeriod(wateringPeriod)
					.make();
			plantsResult.add(plant);
		}

		rs1.close();

		return plantsResult;
	}

	//Return a PersonalPlant by Id
	public PersonalPlant getPersonalPlantByID(int ID) throws SQLException {
		PersonalPlant plant = null;

		String personalPlantName;
		int personalPlantID, plantType;
		Timestamp lastWatered;

		cmdString = "Select * from Garden where PersonalPlantID=" + ID;
		rs1 = st1.executeQuery(cmdString);

		while (rs1.next()) {
			personalPlantID = rs1.getInt("PersonalPlantID");
			personalPlantName = rs1.getString("PERSONALPLANTNAME");
			plantType = rs1.getInt("PLANTID");
			lastWatered = rs1.getTimestamp("LASTWATERED");

			plant = new PersonalPlant(getPlant(plantType), personalPlantName, personalPlantID, new Date(lastWatered.getTime()));
		}

		rs1.close();

		return plant;
	}

	@Override
	public void addPersonalPlantToGarden(PersonalPlant personalPlant) throws SQLException {
		String values;

		//will always prefer autogenerated IDs
		values = "NULL"
				+ ", '" + personalPlant.getName()
				+ "', '" + personalPlant.getType().getPlantID()
				+ "'";
		cmdString = "Insert into Garden " + " Values(" + values + ")";
		st1.executeUpdate(cmdString);
	}

	@Override
	public void updatePersonalPlant(PersonalPlant plant) throws SQLException {
		PreparedStatement cmd;

		cmd = c1.prepareStatement("UPDATE Garden SET LASTWATERED = ? WHERE PERSONALPLANTID = ?");
		Timestamp sqlDate = new Timestamp(plant.getLastWatered().getTime());
		cmd.setTimestamp(1, sqlDate);
		cmd.setInt(2, plant.getID());

		cmd.executeUpdate();
	}


	//Return a list of all PersonalPlants
	public List<PersonalPlant> getAllPersonalPlants() throws SQLException {
		List<PersonalPlant> plantsResult = new ArrayList<>();
		PersonalPlant plant;
		String personalPlantName;
		int personalPlantID, plantType;
		Timestamp lastWatered;

		cmdString = "Select * from Garden";
		rs2 = st2.executeQuery(cmdString);

		while (rs2.next()) {
			personalPlantID = rs2.getInt("PersonalPlantID");
			personalPlantName = rs2.getString("PersonalPlantName");
			plantType = rs2.getInt("PlantID");
			lastWatered = rs2.getTimestamp("LASTWATERED");

			plant = new PersonalPlant(getPlant(plantType), personalPlantName, personalPlantID, new Date(lastWatered.getTime()));
			plantsResult.add(plant);
		}

		rs2.close();

		return plantsResult;
	}
}
