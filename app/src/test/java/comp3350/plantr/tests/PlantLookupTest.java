package comp3350.plantr.tests;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import comp3350.plantr.business.PlantLookup;
import comp3350.plantr.persistence.StubDatabase;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jeeves on 6/1/2017.
 */

public class PlantLookupTest {

	PlantLookup plantLookup = new PlantLookup();

	@Test
	public void getPlantByName() throws JSONException {
		String plantName = "Snake Plant";
		JSONObject plant = plantLookup.getPlantByName(plantName);
		assertNotNull(plant);
	}//lookupPlantByName
}
