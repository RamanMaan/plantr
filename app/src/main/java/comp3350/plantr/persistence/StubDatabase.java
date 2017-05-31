package comp3350.plantr.persistence;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public class StubDatabase implements StubDatabaseInterface{

    //STATIC CONSTANTS USED FOR NAMING
    final static private String NAME = "name";
    final static private String DESCRIPTION = "description";
    final static private int PLANTS_RETURNED = 5;

    //Return single JSONObject representing a plant
    public JSONObject getPlant() throws JSONException {
        JSONObject plant = createPlant();
        return plant;
    }//getPlant

    //Return a JSONArray of multiple JSONObjects of plants
    public JSONArray getMultiplePlants() throws JSONException {
        JSONArray plants = new JSONArray();
        JSONObject plant = createPlant();
        for (int a = 0; a < PLANTS_RETURNED; a++)
        {
            plants.put(plant);
        }//for
        return plants;
    }//getPlants

    //Create a single JSONObject representing a plant
    public JSONObject createPlant() throws JSONException {
        JSONObject plant = new JSONObject();
        plant.put(NAME,NAME);
        plant.put(DESCRIPTION,DESCRIPTION);
        return plant;
    }//createPlant

}//StubDatabase