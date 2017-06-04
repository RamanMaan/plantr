package comp3350.plantr.tests;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.Test;
import comp3350.plantr.persistence.StubDatabase;

import static org.junit.Assert.*;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public class StubDatabaseTest{

    StubDatabase database = new StubDatabase(); //Create a stub database to be used in all unit tests

    @Test
    public void getTestPlant() throws JSONException {
        JSONObject plant = database.getTestPlant();
        assertNotNull(plant);
    }//getTestPlant

    @Test
    public void getAllTestPlants() throws JSONException {
        JSONArray plants = database.getAllTestPlants();
        for (int a = 0; a < plants.length(); a++)
        	assertNotNull(plants.getJSONObject(a));
    }//getAllTestPlants

}//StubDatabaseTest
