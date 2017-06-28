package comp3350.plantr.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DatabaseInterface;

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

		// initialize the database
		db = DatabaseAccess.open();

		int plantPosition = getIntent().getIntExtra(getString(R.string.plantID), -1);
		plant = db.getPlant(plantPosition);

		plantImage = (ImageView) findViewById(R.id.plantImageView);
		plantTitle = (TextView) findViewById(R.id.plantViewTitle);
		plantDesc = (TextView) findViewById(R.id.plantViewDescription);
		plantDifficulty = (TextView) findViewById(R.id.plantViewDifficulty);
		plantOptimalTempRange = (TextView) findViewById(R.id.plantview_optimalTemperatures);
		wateringFrequency = (TextView) findViewById(R.id.plantview_wateringFrequency);

		plantImage.setImageResource(getResources().getIdentifier("@drawable/" + plant.getPlantImg(), null, this.getPackageName()));
		plantTitle.setText(plant.getPlantName());
		plantDesc.setText(plant.getPlantDesc());
		plantDifficulty.setText(String.format(getString(R.string.plantview_difficulty), plant.getDifficulty()));
		plantOptimalTempRange.setText(String.format(getString(R.string.plantview_optimal_temps), plant.getOptimalTemp().getLowerTemp(), plant.getOptimalTemp().getUpperTemp()));
		wateringFrequency.setText(String.format(getString(R.string.plantview_watering_freq), plant.getWateringFreq(), "day"));
	}

}