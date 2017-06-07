package comp3350.plantr.persistence;

import comp3350.plantr.objects.Plant;
import java.util.ArrayList;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public interface DatabaseInterface {
    public DatabaseInterface open(); //Return an instance of the database
    public Plant getPlant(int id); //Return a Plant Object by id
    public Plant getPlant(String name); //Return a Plant Object by name
    public ArrayList<Plant> getAllPlants(); //Return an ArrayList of all Plant Objects
}//StudDatabaseInterface
