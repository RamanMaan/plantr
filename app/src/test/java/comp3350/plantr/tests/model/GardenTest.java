package comp3350.plantr.tests.model;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;

import static org.junit.Assert.*;


/**
 * Created by Michael on 27/06/2017
 *
 * Class that currently only tests plant object creation.
 */

public class GardenTest {

	private Garden myGarden;

	private ArrayList<PersonalPlant> generatePlantList(){
		ArrayList<PersonalPlant> plantList = new ArrayList<>();

		for(int i = 0; i < 10; i++){
			plantList.add(new PersonalPlant(null, null));
		}

		return plantList;
	}

	@Test
	public void garden_testAddPlant(){

		myGarden = new Garden();

		PersonalPlant plantOne = new PersonalPlant(null,null);
		PersonalPlant plantTwo = new PersonalPlant(null,null);

		assertTrue(myGarden.addPlant(plantOne));
		assertTrue(myGarden.addPlant(plantTwo));

		assertFalse(myGarden.addPlant(null));
	}

	@Test
	public void garden_testAddPlants(){

		myGarden = new Garden();

		ArrayList<PersonalPlant> plantList = generatePlantList();

		plantList.add(null);

		assertTrue(myGarden.addPlants(plantList));

		assertFalse(myGarden.addPlants(null));

	}

	@Test
	public void garden_testRemovePersonalPlant(){
		myGarden = new Garden();

		PersonalPlant plantOne = new PersonalPlant(null, null);
		PersonalPlant plantTwo = new PersonalPlant(null, null);

		ArrayList<PersonalPlant> plants = generatePlantList();

		myGarden.addPlants(plants);
		myGarden.addPlant(plantOne);
		myGarden.addPlant(plantTwo);

		//removing
		assertTrue(myGarden.removePersonalPlant(plantOne));

		//ensuring it was deleted
		assertFalse(myGarden.removePersonalPlant(plantOne));

		assertTrue(myGarden.removePersonalPlant(plantTwo));
		assertFalse(myGarden.removePersonalPlant(plantTwo));

		for(PersonalPlant plant: plants){
			assertTrue(myGarden.removePersonalPlant(plant));
			assertFalse(myGarden.removePersonalPlant(plant));
		}

		assertFalse(myGarden.removePersonalPlant(null));
	}

	@Test
	public void garden_testRemovePersonalPlantByID(){
		myGarden = new Garden();

		PersonalPlant plantOne = new PersonalPlant(null, null);
		PersonalPlant plantTwo = new PersonalPlant(null, null);

		int plantIdOne = plantOne.getID();
		int plantIdTwo = plantTwo.getID();

		ArrayList<PersonalPlant> plants = generatePlantList();

		myGarden.addPlants(plants);
		myGarden.addPlant(plantOne);
		myGarden.addPlant(plantTwo);


		assertTrue(myGarden.removePersonalPlantById(plantIdOne));
		assertFalse(myGarden.removePersonalPlantById(plantIdOne));

		assertTrue(myGarden.removePersonalPlantById(plantIdTwo));
		assertFalse(myGarden.removePersonalPlantById(plantIdTwo));

		for(PersonalPlant plant : plants){
			assertTrue(myGarden.removePersonalPlantById(plant.getID()));
			assertFalse(myGarden.removePersonalPlantById(plant.getID()));
		}

		assertFalse(myGarden.removePersonalPlantById(-1));

		assertFalse(myGarden.removePersonalPlantById(Integer.MAX_VALUE));
		assertFalse(myGarden.removePersonalPlantById(Integer.MIN_VALUE));
	}
}
