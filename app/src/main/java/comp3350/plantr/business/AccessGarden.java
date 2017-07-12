package comp3350.plantr.business;

import java.sql.SQLException;
import java.util.List;

import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.PersonalPlant;

public class AccessGarden {
	public static PersonalPlant getPersonalPlantByID(int ID) throws DatabaseStartFailureException, SQLException {
		return DatabaseAccess.getDatabaseAccess().getPersonalPlantByID(ID);
	}

	public static List<PersonalPlant> getAllPersonalPlants() throws DatabaseStartFailureException, SQLException, UserLoginException {
		return DatabaseAccess.getDatabaseAccess().getAllPersonalPlants();
	}

	public static void addPersonalPlantToGarden(PersonalPlant p) throws DatabaseStartFailureException, SQLException {
		DatabaseAccess.getDatabaseAccess().addPersonalPlantToGarden(p);
	}

	public static void updatePersonalPlant(PersonalPlant p) throws DatabaseStartFailureException, SQLException {
		DatabaseAccess.getDatabaseAccess().updatePersonalPlant(p);
	}
}
