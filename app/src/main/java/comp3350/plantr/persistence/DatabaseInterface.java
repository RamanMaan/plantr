package comp3350.plantr.persistence;

import java.util.ArrayList;

import comp3350.plantr.business.objects.Plant;

/**
 * 5/30/2017
 * Keaton MacLeod
 * Class Purpose: An interface for databases in our system
 */

public interface DatabaseInterface {
	public DatabaseInterface open(); //Return an instance of the database

	public Plant getPlant(int id); //Return a Plant Object by id

	public Plant getPlant(String name); //Return a Plant Object by name

	public ArrayList<Plant> getAllPlants(); //Return an ArrayList of all Plant Objects
}//StudDatabaseInterface
