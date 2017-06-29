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
	DatabaseInterface open(); //Return an instance of the database

	void close();//Closes connection with the database

	Plant getPlant(int id); //Return a Plant Object by id

	Plant getPlant(String name); //Return a Plant Object by name

	List<Plant> getAllPlants(); //Return an ArrayList of all Plant Objects

	PersonalPlant getPersonalPlantByID(int ID); //Return a PersonalPlant by Id,

	Collection<PersonalPlant> getAllPersonalPlants();

	public boolean addPersonalPlantToGarden(PersonalPlant plant);
}//StudDatabaseInterface
