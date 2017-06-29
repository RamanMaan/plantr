package comp3350.plantr.presentation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DatabaseInterface;

public class PlantView extends AppCompatActivity {

	private static final String TAG = "PlantView"; // for logging purposes

	private Button addToGarden = null;
	private String text = "";
	private AlertDialog.Builder ADBuilder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final DatabaseInterface db;
		Plant plant;
		ImageView plantImage;
		TextView plantTitle, plantDesc, plantDifficulty, plantOptimalTempRange, wateringFrequency;


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_view);
		Log.d(TAG, "onCreate: started.");

		// initialize the database
		db = DatabaseAccess.open();
		addToGarden = (Button) findViewById(R.id.addPersonalPlantButton);//initialize button
		final android.content.Context context = this;

		int plantPosition = getIntent().getIntExtra(getString(R.string.plant_id), -1);
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


		//on button click, add to Garden
		addToGarden.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//initialize alert dialog box:
				ADBuilder = new AlertDialog.Builder(context);
				buildAlertDialog(context);

				ADBuilder.show();

				PersonalPlant plant = new PersonalPlant(new Plant(0), text, 0);
				db.addPersonalPlantToGarden(plant);

			}
		});
	}


	private void buildAlertDialog(android.content.Context context) {
		ADBuilder.setTitle("Enter a name for your plant:");
		final EditText userInput = new EditText(context);
		userInput.setInputType(InputType.TYPE_CLASS_TEXT);
		ADBuilder.setView(userInput);

		//set buttons
		ADBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				text = userInput.getText().toString();
			}
		});

		ADBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

	}

}