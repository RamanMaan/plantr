package comp3350.plantr.persistence;
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
    final static private String NAME = "Name";
    final static private String DESCRIPTION = "Description";
    final static private String PLANT_DESCRIPTOR = "It's a(n)";
    final static private String ALOE = "Aloe";
    final static private String ANTHURIUM = "Anthurium";
    final static private String ASPARAGUS_FERN = "Asparagus fern";
    final static private String PEACE_LILY = "Peace lily";
    final static private String PEPEROMIA = "Peperomia";
    final static private String SNAKE_PLANT = "Snake Plant";
    final static private String DRACAENA = "Dracaena";
    final static private String PHILODENDRON = "Philodendron";
    final static private String[] PLANT_ARRAY = {ALOE,ANTHURIUM,ASPARAGUS_FERN,PEACE_LILY, PEPEROMIA,SNAKE_PLANT,DRACAENA,PHILODENDRON};

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
            plant.put(NAME,PLANT_ARRAY[a]);
            plant.put(DESCRIPTION,PLANT_DESCRIPTOR + " " + PLANT_ARRAY[a]);
            plants.put(plant);
        }//for
        return plants;
    }//getPlants

    //Create a random JSONObject representing a plant
    public JSONObject getRandomTestPlant() throws JSONException {
        JSONObject plant = new JSONObject();
        int plantNumber = RANDOM.nextInt(PLANT_ARRAY.length);
        plant.put(NAME,PLANT_ARRAY[plantNumber]);
        plant.put(DESCRIPTION,PLANT_DESCRIPTOR + " " + PLANT_ARRAY[plantNumber]);
        return plant;
    }//createPlant

}//StubDatabase