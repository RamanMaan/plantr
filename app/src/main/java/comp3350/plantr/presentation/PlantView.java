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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Date;

import comp3350.plantr.R;
import comp3350.plantr.business.AccessGarden;
import comp3350.plantr.business.AccessPlants;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DatabaseInterface;

public class PlantView extends AppCompatActivity {

	private static final String TAG = "PlantView"; // for logging purposes

	private Button addToGarden = null;
	private String text = "";
	private AlertDialog.Builder ADBuilder = null;
	private PersonalPlant p = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ImageView plantImage;
		TextView plantTitle, plantDesc, plantDifficulty, plantOptimalTempRange, wateringFrequency;


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_view);
		Log.d(TAG, "onCreate: started.");

		addToGarden = (Button) findViewById(R.id.addPersonalPlantButton);//initialize button

		int plantPosition = getIntent().getIntExtra(getString(R.string.plant_id), -1);

		Plant plant = null;
		try {
			plant = AccessPlants.getPlant(plantPosition);

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
			wateringFrequency.setText(String.format(getString(R.string.plantview_watering_freq), plant.getWateringFreq(), "hours"));
		} catch (SQLException e) {
			Toast.makeText(getApplicationContext(), R.string.app_database_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (DatabaseStartFailureException e) {
			Toast.makeText(getApplicationContext(), R.string.app_database_start_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		//on button click, add to Garden
		final Plant finalPlant = plant;
		addToGarden.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//initialize alert dialog box:
				ADBuilder = new AlertDialog.Builder(view.getContext());
				ADBuilder.setTitle("Enter a name for your plant:");
				final EditText userInput = new EditText(view.getContext());
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.MATCH_PARENT);
				userInput.setLayoutParams(lp);

				userInput.setInputType(InputType.TYPE_CLASS_TEXT);
				ADBuilder.setView(userInput);

				//set buttons
				ADBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						text = userInput.getText().toString();
						try {
							p = new PersonalPlant(finalPlant, text, -1, null, UserManager.getUser());
							AccessGarden.addPersonalPlantToGarden(p);
						} catch (SQLException e) {
							Toast.makeText(getApplicationContext(), R.string.app_database_failure, Toast.LENGTH_LONG).show();
							e.printStackTrace();
						} catch (DatabaseStartFailureException e) {
							Toast.makeText(getApplicationContext(), R.string.app_database_start_failure, Toast.LENGTH_LONG).show();
							e.printStackTrace();
						} catch (UserLoginException e) {
							Toast.makeText(getApplicationContext(), R.string.login_user_login_failure, Toast.LENGTH_LONG).show();
							e.printStackTrace();
						}
					}
				});

				ADBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

				ADBuilder.show();

			}
		});
	}


}