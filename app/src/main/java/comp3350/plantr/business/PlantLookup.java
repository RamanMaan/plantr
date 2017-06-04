package comp3350.plantr.business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import comp3350.plantr.objects.Plant;
import comp3350.plantr.persistence.StubDatabase;

/**
 * Created by Keaton MacLeod on 6/1/2017.
 */

////This is the class used for the "Plant Lookup" page of our application.
//public class PlantLookup implements PlantLookupInterface {
//
//	public JSONObject getPlantByName(String plantName) throws JSONException {
//		StubDatabase database = new StubDatabase();
//		ArrayList<Plant> plants = database.getAllPlants();
//		Plant plant = new Plant();
//		boolean foundPlant = false;
//		for (int a = 0; a < plants.size() && !foundPlant; a++)
//		{
//			if ((plants.getJSONObject(a).get("Name")).toString().toLowerCase().equals(plantName.toLowerCase()))
//			{
//				foundPlant = true;
//				plant = plants.getJSONObject(a);
//			}
//		}//for
//		return plant;
//	}//getPlantByName
//}//PlantLookup
