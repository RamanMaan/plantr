package comp3350.plantr.business;

import java.sql.SQLException;
import java.util.List;

import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.Plant;

public class AccessPlants {
	public static Plant getPlant(int id) throws DatabaseStartFailureException, SQLException {
		return DatabaseAccess.getDatabaseAccess().getPlant(id);
	}

	public static List<Plant> getAllPlants() throws DatabaseStartFailureException, SQLException {
		return DatabaseAccess.getDatabaseAccess().getAllPlants();
	}
}
