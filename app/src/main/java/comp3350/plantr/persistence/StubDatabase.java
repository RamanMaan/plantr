package comp3350.plantr.persistence;
import comp3350.plantr.application.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Random;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public class StubDatabase implements StubDatabaseInterface{

    //Sample plant names / constants used for testing during iteration 0
    Random RANDOM = new Random(); //Used to generated random test plants
    final static private String[] PLANT_ARRAY = {Constants.ALOE,Constants.ANTHURIUM,Constants.ASPARAGUS_FERN,Constants.PEACE_LILY,
                                                 Constants.PEPEROMIA,Constants.SNAKE_PLANT,Constants.DRACAENA,Constants.PHILODENDRON};

    //Return a JSONObject representing single plant
    public JSONObject getTestPlant() throws JSONException {
        JSONObject plant = getRandomTestPlant();
        return plant;
    }//getPlant

    //Return a JSONArray of all Test Plants
    public JSONArray getAllTestPlants() throws JSONException {
        JSONArray plants = new JSONArray();
        for (int a = 0; a < PLANT_ARRAY.length; a++)
        {
			JSONObject plant = new JSONObject();
            plant.put(Constants.NAME,PLANT_ARRAY[a]);
            plant.put(Constants.DESCRIPTION,Constants.PLANT_DESCRIPTOR + " " + PLANT_ARRAY[a]);
            plants.put(plant);
        }//for
        return plants;
    }//getPlants

    //Create a random JSONObject representing a plant
    public JSONObject getRandomTestPlant() throws JSONException {
        JSONObject plant = new JSONObject();
        int plantNumber = RANDOM.nextInt(PLANT_ARRAY.length);
        plant.put(Constants.NAME,PLANT_ARRAY[plantNumber]);
        plant.put(Constants.DESCRIPTION,Constants.PLANT_DESCRIPTOR + " " + PLANT_ARRAY[plantNumber]);
        return plant;
    }//createPlant

}//StubDatabase