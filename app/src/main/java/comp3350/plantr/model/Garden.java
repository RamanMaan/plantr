package comp3350.plantr.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

		return _plants.remove(new PersonalPlant(null, null, null, ID));
	}

	public boolean removePersonalPlant(PersonalPlant entry){
		return _plants.remove(entry);
	}

	public PersonalPlant getPersonalPlantById(int ID){
		PersonalPlant returnPlant = null;

		int index = _plants.indexOf(new PersonalPlant(null, null, ID));

		//found in Garden
		if(index != -1){
			returnPlant = _plants.get(index);
		}

		return returnPlant;
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
			plants.removeAll(Collections.singleton(null));
			success = _plants.addAll(plants);
		}

		return success;
	}

	public List<PersonalPlant> getAllPlants(){
		return _plants;
	}

}
