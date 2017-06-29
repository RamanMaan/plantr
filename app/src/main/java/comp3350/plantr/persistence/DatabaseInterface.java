package comp3350.plantr.persistence;

import java.util.Collection;
import java.util.List;

import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;

/**
 * 5/30/2017
 * Keaton MacLeod
 * Class Purpose: An interface for databases in our system
 */

public interface DatabaseInterface {
	void open(String string); //Start a connection with the database

	void close(); //Closes connection with the database

	Plant getPlant(int ID); //Return a Plant Object by ID

	Plant getPlant(String name); //Return a Plant Object by name

	List<Plant> getAllPlants(); //Return an ArrayList of all Plant Objects

	void addPersonalPlant(PersonalPlant personalPlant); //Add a plant to the garden

	PersonalPlant getPersonalPlantByID(int ID); //Return a PersonalPlant by ID,

	List<PersonalPlant> getAllPersonalPlants();
}//StudDatabaseInterface
