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
		for(int i = 0; i < _plants.size(); i++) {
			if(_plants.get(i).getID() == ID) {
				_plants.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean removePersonalPlant(PersonalPlant entry){
		return _plants.remove(entry);
	}

	public PersonalPlant getPersonalPlantById(int ID){
		for(int i = 0; i < _plants.size(); i++) {
			if(_plants.get(i).getID() == ID) {
				return _plants.get(i);
			}
		}
		return null;
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
