package comp3350.plantr.persistence;

import java.util.ArrayList;
import comp3350.plantr.objects.Plant;

/**
 * 5/28/2017
 * Raman Maan
 *
 * Purpose: A temporary database for us to work with
 * 			Why Apollo? -> Greek god of prophecy
 */

public class ApolloDBstub implements DatabaseInterface {
	@Override
	public void open(String dbName) {

	}

	@Override
	public void close() {

	}

	@Override
	public Plant getPlant(int plantID) {
		return null;
	}

	@Override
	public ArrayList<Plant> getAllPlants() {
		return null;
	}

	@Override
	public void addPlant(Plant p) {

	}
}
