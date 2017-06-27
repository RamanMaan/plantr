package comp3350.plantr.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Michael on 27/06/2017.
 *
 * Class for storing a person's PersonalPlants in
 */

public class Garden {

	private ArrayList<PersonalPlant> _plants;

	public Garden(){
		_plants = new ArrayList<>();
	}

	public void addPlant(PersonalPlant plant){
		_plants.add(plant);
	}

	List<PersonalPlant> getAllPlants(){
		return _plants;
	}

}
