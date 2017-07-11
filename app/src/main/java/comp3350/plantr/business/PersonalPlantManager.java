package comp3350.plantr.business;

import android.content.Context;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Date;

import comp3350.plantr.R;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.PersonalPlant;

public class PersonalPlantManager {
	public static void waterPlant(Context c, PersonalPlant p) {
		//set the plants last watered to be the current time
		p.setLastWatered(new Date());

		//update the database
		try {
			DatabaseAccess.getDatabaseAccess().updatePersonalPlant(p);
		} catch (SQLException e) {
			Toast.makeText(c, R.string.app_database_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (DatabaseStartFailureException e) {
			Toast.makeText(c, R.string.app_database_start_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
}
