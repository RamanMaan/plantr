package comp3350.plantr.model;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;

/**
 * Created by Michael on 27/06/2017.
 * <p>
 * A specific instance of a plant.
 */

public class PersonalPlant {

	private Plant _plantType;
	private String _plantName;
	private int _personalPlantID;
	private Date _lastWatered;

	public PersonalPlant(Plant plantType, String plantName, int ID, Date lastWatered) {
		_plantType = plantType;
		_plantName = plantName;
		_personalPlantID = ID;
		_lastWatered = lastWatered;
	}

	public boolean equals(Object other) {
		boolean isSame = false;

		if (other instanceof PersonalPlant) { //comparing two PersonalPlants
			isSame = ((PersonalPlant) other)._personalPlantID == this._personalPlantID;
		}

		return isSame;
	}

	public boolean equals(int compareID) {
		return _personalPlantID == compareID;
	}

	public int getID() {
		return _personalPlantID;
	}

	public String getName() { return _plantName; }

	public Plant getType() { return _plantType; }

	public Date getLastWatered() {
		return _lastWatered;
	}

	public void setLastWatered(Date d) {
		//TODO add tests with getNextWatering
		_lastWatered = d;
	}

	public Date getNextWatering() {
		Date nextWatering = _lastWatered;
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(nextWatering);
		cal.add(Calendar.HOUR_OF_DAY, _plantType.getWateringFreq());
		//the latest the next watering period can be is the current time
		return cal.getTime().before(currentDate) ? currentDate : cal.getTime();
	}

}
