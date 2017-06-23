package comp3350.plantr.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.TemperatureRange;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public class StubDatabase implements DatabaseInterface {

	//No Image, Temperature, Hardiness or Watering Frequency: Default Values Assigned Currently
	private ArrayList<Plant> plants;

	public StubDatabase() {
		plants = new ArrayList<Plant>(Arrays.asList(
				new Plant(1, "Aloe", "Aloe", "aloe", new TemperatureRange(new Temperature(21), new Temperature(23)), 170),
				new Plant(2, "Anthurium", "Anthurium", "anthurium", new TemperatureRange(new Temperature(21), new Temperature(23)), 50),
				new Plant(3, "Asparagus fern", "Asparagus fern", "asparagus_fern", new TemperatureRange(new Temperature(21), new Temperature(30)), 1),
				new Plant(4, "Peace lily", "Peace lily", "peace_lily", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(5, "Peperomia", "Peperomia", "peperomia", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(6, "Snake Plant", "Snake Plant", "snake_plant", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(7, "Dracaena", "Dracaena", "dracaena", new TemperatureRange(new Temperature(21), new Temperature(23)), 1),
				new Plant(8, "Philodendron", "Philodendron", "philodendron", new TemperatureRange(new Temperature(21), new Temperature(23)), 1)
		));

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
			if (plants.get(a).getPlantID() == id)
				plant = plants.get(a);
		}
		return plant;
	}//getPlant

	public Plant getPlant(String name) {
		Plant plant = null;
		for (int a = 0; a < plants.size() && plant == null; a++) {
			if (plants.get(a).getPlantName().toLowerCase().equals(name.toLowerCase()))
				plant = plants.get(a);
		}
		return plant;
	}//getPlant

	//Return an ArrayList of all Plant Objects
	@Override
	public List<Plant> getAllPlants() {
		return plants;
	}//getAllPlants

}//StubDatabase