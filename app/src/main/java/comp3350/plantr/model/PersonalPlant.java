package comp3350.plantr.model;

import java.util.Date;

/**
 * Created by Michael on 27/06/2017.
 *
 * A specific instance of a plant.
 */

public class PersonalPlant {

	private static int idCounter = 0;

	private Plant _plantType;
	private String _plantName;
	private int _personalPlantID;
	private String _personalPlantImg;


	public PersonalPlant(Plant plantType, String plantName){
		_plantType = plantType;
		_plantName = plantName;
		_personalPlantID = idCounter++;
	}

	public PersonalPlant(Plant plantType, String plantName, int id){
		_plantType = plantType;
		_plantName = plantName;
		_personalPlantID = id;
	}

	public boolean equals(Object other){
		boolean isSame = false;

		if(other instanceof PersonalPlant){ //comparing two PersonalPlants
			isSame = ((PersonalPlant) other)._personalPlantID == this._personalPlantID;
		}

		return isSame;
	}

	public boolean equals(int compareID){
		return _personalPlantID == compareID;
	}

	public int getID(){
		return _personalPlantID;
	}

	public String getPersonalPlantName() {return _plantName;}
	public Plant getPersonalPlantType() {return _plantType;}
	public String get_personalPlantImg() {return _personalPlantImg;}

}
