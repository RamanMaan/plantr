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

	public void close(){
		//
	}

	public StubDatabase() {
		plants = new ArrayList<Plant>(Arrays.asList(
				new Plant.PlantBuilder(0)
						.name("Aloe")
						.desc("An Aloe!! Whew Lad")
						.img("aloe")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(170)
						.make(),
				new Plant.PlantBuilder(1)
						.name("Anthurium")
						.desc("An Anthurium!! Woah man")
						.img("anthurium")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(50)
						.make(),
				new Plant.PlantBuilder(2)
						.name("Asparagus fern")
						.desc("An Asparagus fern!! Waddup!")
						.img("asparagus_fern")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(75)
						.make(),
				new Plant.PlantBuilder(3)
						.name("Peace lily")
						.desc("An Peace lily!! Soul crushing")
						.img("peace_lily")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(35)
						.make(),
				new Plant.PlantBuilder(4)
						.name("Peperomia")
						.desc("An Peperomia!! Not to be confused with pepperoni")
						.img("peperomia")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(80)
						.make(),
				new Plant.PlantBuilder(5)
						.name("Snake Plant")
						.desc("An Snake Plant!! Oh lawd")
						.img("snake_plant")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(50)
						.make(),
				new Plant.PlantBuilder(6)
						.name("Dracaena")
						.desc("An Dracaena!! Oh jeez")
						.img("dracaena")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(50)
						.make(),
				new Plant.PlantBuilder(7)
						.name("Philodendron")
						.desc("An Philodendron!! They've trapped me in this factory typing text send help")
						.img("philodendron")
						.tempRange(new Temperature(21), new Temperature(23))
						.wateringPeriod(50)
						.make()
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
	public List<PersonalPlant> getAllPersonalPlants(){
		return _userGarden.getAllPlants();
	}

	@Override
	public void addPersonalPlantToGarden(PersonalPlant plant) {
		_userGarden.addPlant(plant);
	}

}//StubDatabase