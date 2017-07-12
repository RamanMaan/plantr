package comp3350.plantr.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class for managing the Garden
 */

public class Garden {

	private ArrayList<PersonalPlant> _plants;

	public Garden() {
		_plants = new ArrayList<>();
	}

	public boolean removePersonalPlant(int ID) {
		for (int i = 0; i < _plants.size(); i++) {
			if (_plants.get(i).getID() == ID) {
				_plants.remove(i);
				return true;
			}
		}
		return false;
	}

	public PersonalPlant getPersonalPlantById(int ID) {
		for (int i = 0; i < _plants.size(); i++) {
			if (_plants.get(i).getID() == ID) {
				return _plants.get(i);
			}
		}
		return null;
	}

	public boolean addPlant(PersonalPlant plant) {
		return _plants.add(plant);
	}

	public boolean addPlants(Collection<PersonalPlant> plants) {
		return _plants.addAll(plants);
	}

	public List<PersonalPlant> getAllPlants() {
		return _plants;
	}

}
