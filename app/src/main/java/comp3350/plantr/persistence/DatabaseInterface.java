package comp3350.plantr.persistence;

import java.util.ArrayList;
import comp3350.plantr.objects.Plant;

/**
 * Created: 5/28/2017
 * Raman Maan
 *
 * Purpose: Interface for our database
 * 			TODO: better name than database
 */

public interface DatabaseInterface {
	public void open(String dbName);

	public void close();

	public Plant getPlant(int plantID);

	public ArrayList<Plant> getAllPlants();

	public void addPlant(Plant p);

}
