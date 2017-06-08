package comp3350.plantr.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import comp3350.plantr.R;
import comp3350.plantr.application.DatabaseAccess;
import comp3350.plantr.application.Constants;
import comp3350.plantr.objects.Plant;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;

public class PlantView extends AppCompatActivity {

    private static final String TAG = "PlantView"; // for logging purposes
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseInterface db;
        Plant plant;
        ImageView plantImage;
        TextView plantTitle, plantDesc, plantDifficulty, plantOptimalTempRange, wateringFrequency;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
        Log.d(TAG, "onCreate: started.");

        // initialize the stub database
        db = DatabaseAccess.open();

        int plantPosition = getIntent().getIntExtra(getString(R.string.plantID), -1);
        plant = db.getPlant(plantPosition);

        plantImage = (ImageView) findViewById(R.id.plantImageView);
        plantTitle = (TextView) findViewById(R.id.plantViewTitle);
        plantDesc = (TextView) findViewById(R.id.plantViewDescription);
        plantDifficulty = (TextView) findViewById(R.id.plantViewDifficulty);
        plantOptimalTempRange = (TextView) findViewById(R.id.plantview_optimalTemperatures);
        wateringFrequency = (TextView) findViewById(R.id.plantview_wateringFrequency);

        plantImage.setImageResource(getResources().getIdentifier("@drawable/"+plant.getPlantImg(), null, this.getPackageName()));
        plantTitle.setText(plant.getPlantName());
        plantDesc.setText(plant.getPlantDesc());
        plantDifficulty.setText(String.format(Constants.PLANT_DIFFICULTY_TEXT, plant.getDifficulty()));
        plantOptimalTempRange.setText(String.format(Constants.PLANTVIEW_OPTIMALTEMPS, plant.getOptimalTemp().getLowerTemp(), plant.getOptimalTemp().getUpperTemp()));
        wateringFrequency.setText(String.format(Constants.PLANTVIEW_WATERINGFREQ, plant.getWateringFreq(), "hours"));
    }

}