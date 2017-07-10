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
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseCloseFailureException;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.User;

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

	@Override
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

	@Override
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


	@Override
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

	@Override
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

	@Override
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

	@Override
	public PersonalPlant getPersonalPlantByID(int ID) throws SQLException {
		PersonalPlant plant = null;

		String personalPlantName;
		int personalPlantID, plantType;
		Timestamp lastWatered;
		String owner;

		cmdString = "Select * from Garden where PersonalPlantID=" + ID;
		rs1 = st1.executeQuery(cmdString);

		while (rs1.next()) {
			personalPlantID = rs1.getInt("PersonalPlantID");
			personalPlantName = rs1.getString("PERSONALPLANTNAME");
			plantType = rs1.getInt("PLANTID");
			lastWatered = rs1.getTimestamp("LASTWATERED");
			owner = rs1.getString("OWNER");

			User u = getUser(owner);
			plant = new PersonalPlant(getPlant(plantType), personalPlantName, personalPlantID, new Date(lastWatered.getTime()), u);
		}

		rs1.close();

		return plant;
	}

	@Override
	public void addPersonalPlantToGarden(PersonalPlant personalPlant) throws SQLException {
		PreparedStatement cmd;

		cmd = c1.prepareStatement("INSERT into Garden VALUES(NULL, ?, ?, ?, ?)");
		cmd.setString(1, personalPlant.getName());
		Timestamp sqlDate = new Timestamp(new Date().getTime());
		cmd.setTimestamp(2, sqlDate);
		cmd.setInt(3, personalPlant.getType().getPlantID());
		cmd.setString(4, personalPlant.getOwner().getEmail());

		cmd.executeUpdate();
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

	@Override
	public List<PersonalPlant> getAllPersonalPlants() throws SQLException, UserLoginException {
		List<PersonalPlant> plantsResult = new ArrayList<>();
		PersonalPlant plant;
		PreparedStatement cmd;

		cmd = c1.prepareStatement("SELECT * FROM Garden WHERE OWNER = ?");
		cmd.setString(1, UserManager.getUser().getEmail());
		rs2 = cmd.executeQuery();

		while (rs2.next()) {
			Plant plantid = getPlant(rs2.getInt("PLANTID"));
			String personalPlantName = rs2.getString("PERSONALPLANTNAME");
			int personalPlantID = rs2.getInt("PERSONALPLANTID");
			Date lastwatered = new Date(rs2.getTimestamp("LASTWATERED").getTime());
			User owner = getUser(rs2.getString("OWNER"));

			plant = new PersonalPlant(plantid, personalPlantName, personalPlantID, lastwatered, owner);
			plantsResult.add(plant);
		}

		rs2.close();
		cmd.close();

		return plantsResult;
	}

	@Override
	public User getUser(String email) throws SQLException {
		User user = null;
		PreparedStatement cmd;

		cmd = c1.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
		cmd.setString(1, email);
		rs1 = cmd.executeQuery();

		if (rs1.next()) {
			user = new User(rs1.getString("EMAIL"), rs1.getString("NAME"), rs1.getString("PASSWORD"));
		}

		rs1.close();
		cmd.close();

		return user;
	}

}
