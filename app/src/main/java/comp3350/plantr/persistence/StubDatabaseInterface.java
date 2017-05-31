package comp3350.plantr.persistence;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public interface StubDatabaseInterface {
    public JSONObject getPlant() throws JSONException; //Return a single JSONObject representing a plant
    public JSONArray getMultiplePlants() throws JSONException; //Return a JSONArray of multiple JSONObjects of plants
    public JSONObject createPlant() throws JSONException; //Create a single JSONObject representing a plant
}//StudDatabaseInterface
