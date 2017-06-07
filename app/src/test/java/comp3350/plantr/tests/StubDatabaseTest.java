package comp3350.plantr.tests;
import org.junit.Test;
import java.util.ArrayList;
import comp3350.plantr.objects.Plant;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;
import static org.junit.Assert.*;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public class StubDatabaseTest{

    DatabaseInterface database = new StubDatabase().open(); //Create a stub database to be used in all unit tests

    @Test
    public void getPlantById() {
        Plant plant = database.getPlant(1);
        assertNotNull(plant);
    }//getPlant

    public void getPlantByName()
    {
        Plant plant = database.getPlant("aloe");
        assertNotNull(plant);
    }//getPlant

    @Test
    public void getAllPlants() {
        ArrayList<Plant> plants = database.getAllPlants();
        for (int a = 0; a < plants.size(); a++)
            assertNotNull(plants.get(a));
    }//getAllPlants

}//StubDatabaseTest
