package comp3350.plantr.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.TemperatureRange;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 *
 * Stub Database, used to mimick the behavior of a real database
 */

public class StubDatabase implements DatabaseInterface {

	private ArrayList<Plant> plants;
	private Garden _userGarden;

	public StubDatabase() {
		plants = new ArrayList<>(Arrays.asList(
				new Plant(0, "Aloe", "Aloe", "aloe", new TemperatureRange(new Temperature(21), new Temperature(23)), 170),
				new Plant(1, "Anthurium", "Anthurium", "anthurium", new TemperatureRange(new Temperature(21), new Temperature(23)), 50),
				new Plant(2, "Asparagus fern", "Asparagus fern", "asparagus_fern", new TemperatureRange(new Temperature(21), new Temperature(30)), 1),
				new Plant(3, "Peace lily", "Peace lily", "peace_lily", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(4, "Peperomia", "Peperomia", "peperomia", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(5, "Snake Plant", "Snake Plant", "snake_plant", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(6, "Dracaena", "Dracaena", "dracaena", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(7, "Philodendron", "Philodendron", "philodendron", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(8, "Aloe", "Aloe", "aloe", new TemperatureRange(new Temperature(21), new Temperature(23)), 170),
				new Plant(9, "Anthurium", "Anthurium", "anthurium", new TemperatureRange(new Temperature(21), new Temperature(23)), 50),
				new Plant(10, "Asparagus fern", "Asparagus fern", "asparagus_fern", new TemperatureRange(new Temperature(21), new Temperature(30)), 1),
				new Plant(11, "Peace lily", "Peace lily", "peace_lily", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(12, "Peperomia", "Peperomia", "peperomia", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(13, "Snake Plant", "Snake Plant", "snake_plant", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(14, "Dracaena", "Dracaena", "dracaena", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(15, "Philodendron", "Philodendron", "philodendron", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(16, "Philodendron", "Philodendron", "philodendron", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(17, "Aloe", "Aloe", "aloe", new TemperatureRange(new Temperature(21), new Temperature(23)), 170),
				new Plant(18, "Anthurium", "Anthurium", "anthurium", new TemperatureRange(new Temperature(21), new Temperature(23)), 50),
				new Plant(19, "Asparagus fern", "Asparagus fern", "asparagus_fern", new TemperatureRange(new Temperature(21), new Temperature(30)), 1),
				new Plant(20, "Peace lily", "Peace lily", "peace_lily", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(21, "Peperomia", "Peperomia", "peperomia", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(22, "Snake Plant", "Snake Plant", "snake_plant", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(23, "Dracaena", "Dracaena", "dracaena", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(24, "Philodendron", "Philodendron", "philodendron", new TemperatureRange(new Temperature(21), new Temperature(23)), 1)
		));

		_userGarden = new Garden();
		ArrayList<PersonalPlant> stubPersonalPlants = new ArrayList<>(Arrays.asList(
				new PersonalPlant(getPlant(0), "Vera the Aloe Vera", 0),
				new PersonalPlant(getPlant(1), "Arthur the Anthurium", 1),
				new PersonalPlant(getPlant(2), "Sarah the aspara-gus fern", 2),
				new PersonalPlant(getPlant(3), "Reece the Peace Lily", 3),
				new PersonalPlant(getPlant(4), "Pupper the Peperomia", 4)
		));

		_userGarden.addPlants(stubPersonalPlants);

	}//Constructor

	@Override
	public DatabaseInterface open() {
		return this;
	}

	//Return a Plant Object
	@Override
	public Plant getPlant(int id) {
		Plant plant = null;
		for (int a = 0; a < plants.size() && plant == null; a++) {
			if (plants.get(a).getPlantID() == id) {
				plant = plants.get(a);
			}
		}
		return plant;
	}//getPlant

	public Plant getPlant(String name) {
		if (name == null) {
			return null;
		}

		Plant p;
		for (int a = 0; a < plants.size(); a++) {
			p = plants.get(a);
			if (p.getPlantName().compareToIgnoreCase(name) == 0) {
				return p;
			}
		}

		return null;
	}//getPlant

	//Return an ArrayList of all Plant Objects
	@Override
	public List<Plant> getAllPlants() {
		return plants;
	}//getAllPlants

	@Override
	public PersonalPlant getPersonalPlantByID(int ID){
		return _userGarden.getPersonalPlantById(ID);
	}

	@Override
	public Collection<PersonalPlant> getAllPersonalPlants(){
		return _userGarden.getAllPlants();
	}

}//StubDatabase