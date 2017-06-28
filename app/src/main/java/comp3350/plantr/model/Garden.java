package comp3350.plantr.model;

import java.util.ArrayList;
import java.util.Collection;
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

	public boolean removePersonalPlantById(int ID){

		return _plants.remove(new PersonalPlant(null, null, ID));
	}

	public boolean removePersonalPlant(PersonalPlant entry){
		return _plants.remove(entry);
	}

	public boolean addPlant(PersonalPlant plant){
		boolean success = false;

		if(plant != null) {
			success = _plants.add(plant);
		}

		return success;
	}

	public boolean addPlants(Collection<PersonalPlant> plants){
		boolean success = false;

		if(plants != null){
			success = _plants.addAll(plants);
		}

		return success;
	}

	List<PersonalPlant> getAllPlants(){
		return _plants;
	}

}