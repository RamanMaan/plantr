package comp3350.plantr.model;

import java.util.Calendar;
import java.util.Date;

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

	private static int idCtr = 0;

	public PersonalPlant(Plant plantType, String plantName) {
		_plantType = plantType;
		_plantName = plantName;
		_personalPlantID = idCtr;
		idCtr++;
		_lastWatered = new Date();
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
		_lastWatered = d;
	}

	public Date getNextWatering() {
		Date nextWatering = _lastWatered;
		Calendar cal = Calendar.getInstance();
		cal.setTime(nextWatering);
		cal.add(Calendar.HOUR_OF_DAY, _plantType.getWateringFreq());
		return cal.getTime();
	}

}
