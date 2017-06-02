package comp3350.plantr.business;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Keaton MacLeod on 6/1/2017.
 */

//This is the interface used for the "Plant Lookup" page of our application.
public interface PlantLookupInterface {
	public JSONObject getPlantByName(String plantName) throws JSONException;
}//PlantLookupInterface
