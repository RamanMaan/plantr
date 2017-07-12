package comp3350.plantr.persistence;

import java.sql.SQLException;
import java.util.List;

import comp3350.plantr.business.exceptions.DatabaseCloseFailureException;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.User;

/**
 * An interface for databases in our system
 */

public interface DatabaseInterface {
	void open(String dbPath) throws DatabaseStartFailureException; //Return an instance of the database

	void close() throws DatabaseCloseFailureException;//Closes connection with the database

	Plant getPlant(int id) throws SQLException; //Return a Plant Object by id

	Plant getPlant(String name) throws SQLException; //Return a Plant Object by name

	List<Plant> getAllPlants() throws SQLException; //Return an ArrayList of all Plant Objects

	PersonalPlant getPersonalPlantByID(int ID) throws SQLException; //Return a PersonalPlant by Id,

	List<PersonalPlant> getAllPersonalPlants() throws SQLException, UserLoginException;

	void addPersonalPlantToGarden(PersonalPlant plant) throws SQLException;

	void updatePersonalPlant(PersonalPlant plant) throws SQLException;

	User getUser(String email) throws SQLException;

	void removePersonalPlantByID(int plantID) throws SQLException;

	Garden getGarden() throws SQLException;

}//StudDatabaseInterface
