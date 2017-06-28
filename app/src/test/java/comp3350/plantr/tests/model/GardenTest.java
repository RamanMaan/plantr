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

	@Test
	public void garden_testCreate() {
		myGarden = new Garden();

		assertNotNull(myGarden);
	}

	@Test
	public void garden_testAddPlant(){

		myGarden = new Garden();

		PersonalPlant plantOne = new PersonalPlant(null,null);
		PersonalPlant plantTwo = new PersonalPlant(null,null);

		assertTrue(myGarden.addPlant(plantOne));
		assertTrue(myGarden.addPlant(plantTwo));

		assertTrue(!myGarden.addPlant(null));
	}

	@Test
	public void garden_testAddPlants(){

		myGarden = new Garden();

		ArrayList<PersonalPlant> plantList = new ArrayList<>();

		for(int i = 0; i < 10; i++){
			plantList.add(new PersonalPlant(null, null));
		}

		assertTrue(myGarden.addPlants(plantList));

		assertTrue(!myGarden.addPlants(null));

	}

	@Test
	public void garden_testRemovePersonalPlant(){
		myGarden = new Garden();

		PersonalPlant plantOne = new PersonalPlant(null, null);
		PersonalPlant plantTwo = new PersonalPlant(null, null);

		ArrayList<PersonalPlant> plants = new ArrayList<>();

		for(int i = 0; i< 10; i++){
			plants.add(new PersonalPlant(null, null));
		}

		myGarden.addPlants(plants);
		myGarden.addPlant(plantOne);
		myGarden.addPlant(plantTwo);

		//removing
		assertTrue(myGarden.removePersonalPlant(plantOne));

		//ensuring it was deleted
		assertTrue(!myGarden.removePersonalPlant(plantOne));

		assertTrue(myGarden.removePersonalPlant(plantTwo));
		assertTrue(!myGarden.removePersonalPlant(plantTwo));

		for(PersonalPlant plant: plants){
			assertTrue(myGarden.removePersonalPlant(plant));
			assertTrue(!myGarden.removePersonalPlant(plant));
		}
	}

	@Test
	public void garden_testRemovePersonalPlantByID(){
		myGarden = new Garden();


		PersonalPlant plantOne = new PersonalPlant(null, null);
		PersonalPlant plantTwo = new PersonalPlant(null, null);

		int plantIdOne = plantOne.getID();
		int plantIdTwo = plantTwo.getID();

		ArrayList<PersonalPlant> plants = new ArrayList<>();

		for(int i = 0; i< 10; i++){
			plants.add(new PersonalPlant(null, null));
		}

		myGarden.addPlants(plants);
		myGarden.addPlant(plantOne);
		myGarden.addPlant(plantTwo);


		assertTrue(myGarden.removePersonalPlantById(plantIdOne));
		assertTrue(!myGarden.removePersonalPlantById(plantIdOne));

		assertTrue(myGarden.removePersonalPlantById(plantIdTwo));
		assertTrue(!myGarden.removePersonalPlantById(plantIdTwo));

		for(PersonalPlant plant : plants){
			assertTrue(myGarden.removePersonalPlantById(plant.getID()));
			assertTrue(!myGarden.removePersonalPlantById(plant.getID()));
		}
	}
}
