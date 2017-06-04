package comp3350.plantr.business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import comp3350.plantr.persistence.StubDatabase;

/**
 * Created by Keaton MacLeod on 6/1/2017.
 */

//This is the class used for the "Plant Lookup" page of our application.
public class PlantLookup implements PlantLookupInterface {

	public JSONObject getPlantByName(String plantName) throws JSONException {
		StubDatabase database = new StubDatabase();
		JSONArray plants = database.getAllTestPlants();
		JSONObject plant = new JSONObject();
		boolean foundPlant = false;
		for (int a = 0; a < plants.length() && !foundPlant; a++)
		{
			if ((plants.getJSONObject(a).get("Name")).toString().toLowerCase().equals(plantName.toLowerCase()))
			{
				foundPlant = true;
				plant = plants.getJSONObject(a);
			}
		}//for
		return plant;
	}//getPlantByName
}//PlantLookup
