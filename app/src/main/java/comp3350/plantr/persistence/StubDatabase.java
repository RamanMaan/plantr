package comp3350.plantr.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.User;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 * <p>
 * Stub Database, used to mimick the behavior of a real database
 */

public class StubDatabase implements DatabaseInterface {

	private ArrayList<Plant> plants;
	private Garden _userGarden;
	private List<User> _users;

	@Override
	public void open(String dbPath) {
		plants = new ArrayList<>(Arrays.asList(
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

		_users = new ArrayList<>(Arrays.asList(
				new User("du@plantr.io", "Default-o User-o", "plantr"),
				new User("ramanmaan@plantr.io", "Raman Maan", "plantr"),
				new User("kevindam@plantr.io", "Kevin Dam", "plantr")
		));

		_userGarden = new Garden();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -20);
		ArrayList<PersonalPlant> stubPersonalPlants = new ArrayList<>(Arrays.asList(
				new PersonalPlant(getPlant(0), "Vera the Aloe Vera", 0, cal.getTime(), _users.get(0)),
				new PersonalPlant(getPlant(1), "Arthur the Anthurium", 1, cal.getTime(), _users.get(0)),
				new PersonalPlant(getPlant(2), "Sarah the aspara-gus fern", 2, cal.getTime(), _users.get(0)),
				new PersonalPlant(getPlant(3), "Reece the Peace Lily", 3, cal.getTime(), _users.get(1)),
				new PersonalPlant(getPlant(4), "Pupper the Peperomia", 4, cal.getTime(), _users.get(2))
		));

		_userGarden.addPlants(stubPersonalPlants);
	}

	@Override
	public void close() {
		System.out.println("Closed Stub Database");
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

	@Override
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
	public PersonalPlant getPersonalPlantByID(int ID) {
		return _userGarden.getPersonalPlantById(ID);
	}

	@Override
	public List<PersonalPlant> getAllPersonalPlants() {
		List<PersonalPlant> usersPersonalPlants = new ArrayList<>();
		User u = null;
		try {
			u = UserManager.getUser();
		} catch (UserLoginException e) {
			System.out.println("Couldn't log in user!");
			e.printStackTrace();
		}
		for (PersonalPlant p : _userGarden.getAllPlants()) {
			if (p.getOwner() == u) {
				usersPersonalPlants.add(p);
			}
		}
		return usersPersonalPlants;
	}

	@Override
	public void addPersonalPlantToGarden(PersonalPlant plant) {
		_userGarden.addPlant(plant);
	}

	@Override
	public void updatePersonalPlant(PersonalPlant plant) {
		//TODO write tests
		if (plant == null) {
			System.out.println("Trying to update null plant");
			throw new NullPointerException("Updating Personal Plant");
		}

		PersonalPlant dbPlant = _userGarden.getPersonalPlantById(plant.getID());
		dbPlant.setLastWatered(plant.getLastWatered());
	}

	@Override
	public User getUser(String email) {
		for (User u : _users) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}

		return null;
	}

	@Override
	public Garden getGarden()
	{
		return _userGarden;
	}

	@Override
	public void removePersonalPlantByID(int plantID)
	{
		for (int a = 0; a < _userGarden.getAllPlants().size(); a++)
		{
			if (_userGarden.getAllPlants().get(a).getID() == plantID)
				_userGarden.getAllPlants().remove(a);
		}
	}//removePersonalPlant

}//StubDatabase