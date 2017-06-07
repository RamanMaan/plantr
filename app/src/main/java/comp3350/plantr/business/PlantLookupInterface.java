package comp3350.plantr.business;

import comp3350.plantr.objects.Plant;
import java.util.ArrayList;

/**
 * Created by Keaton MacLeod on 6/1/2017.
 */

//This interface will be used in future iterations to perform data
//retrieval on the PlantLookup page when the production Database / Tables
//are implemented

public interface PlantLookupInterface {
	public Plant getPlant(int id); //Return a Plant Object by id
	public Plant getPlant(String name); //Return a Plant Object by name
	public ArrayList<Plant> getAllPlants(); //Return an ArrayList of all Plant Objects
}//PlantLookupInterface
