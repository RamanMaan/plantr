package comp3350.plantr.model;

import java.util.Date;

/**
 * Created by Michael on 27/06/2017.
 *
 * A specific instance of a plant.
 */

public class PersonalPlant {

	private static long idCounter = 0;

	private Plant _plantType;
	private String _plantName;
	private long personalPlantID;


	public PersonalPlant(Plant plantType, String plantName){
		_plantType = plantType;
		_plantName = plantName;
		personalPlantID = idCounter++;
	}


}
