package comp3350.plantr.persistence;

import comp3350.plantr.application.Constants;
import comp3350.plantr.objects.Plant;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Keaton MacLeod on 5/30/2017.
 */

public class StubDatabase implements DatabaseInterface {

    //No Image, Temperature, Hardiness or Watering Frequency: Default Values Assigned Currently
    private ArrayList<Plant> plants;

    public StubDatabase() {
        plants = new ArrayList<Plant>(Arrays.asList(
                new Plant(1, Constants.ALOE, Constants.PLANT_DESCRIPTOR + " " + Constants.ALOE , "aloe", null, 1, 1),
                new Plant(2, Constants.ANTHURIUM, Constants.PLANT_DESCRIPTOR + " " + Constants.ANTHURIUM , "anthurium", null, 1, 1),
                new Plant(3, Constants.ASPARAGUS_FERN, Constants.PLANT_DESCRIPTOR + " " + Constants.ASPARAGUS_FERN , null, null, 1, 1),
                new Plant(4, Constants.PEACE_LILY, Constants.PLANT_DESCRIPTOR + " " + Constants.PEACE_LILY , null, null, 1, 1),
                new Plant(5, Constants.PEPEROMIA, Constants.PLANT_DESCRIPTOR + " " + Constants.PEPEROMIA , null, null, 1, 1),
                new Plant(6, Constants.SNAKE_PLANT, Constants.PLANT_DESCRIPTOR + " " + Constants.SNAKE_PLANT , null, null, 1, 1),
                new Plant(7, Constants.DRACAENA, Constants.PLANT_DESCRIPTOR + " " + Constants.DRACAENA , null, null, 1, 1),
                new Plant(8, Constants.PHILODENDRON, Constants.PLANT_DESCRIPTOR + " " + Constants.PHILODENDRON , null, null, 1, 1)
        ));
    }//Constructor

    @Override
    public DatabaseInterface open() {
        return this;
    }

    //Return a Plant Object
    @Override
    public Plant getPlant(int id) {
        Plant plant = null;
        for (int a = 0; a < plants.size() && plant == null; a++)
        {
            if (plants.get(a).getPlantID() == id)
                plant = plants.get(a);
        }
        return plant;
    }//getPlant

    public Plant getPlant(String name)
    {
        Plant plant = null;
        for (int a = 0; a < plants.size() && plant == null; a++)
        {
            if (plants.get(a).getPlantName().toLowerCase().equals(name.toLowerCase()))
                plant = plants.get(a);
        }
        return plant;
    }//getPlant

    //Return an ArrayList of all Plant Objects
    @Override
    public ArrayList<Plant> getAllPlants() {
		return plants;
    }//getAllPlants

}//StubDatabase