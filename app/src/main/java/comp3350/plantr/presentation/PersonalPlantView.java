package comp3350.plantr.presentation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.persistence.DatabaseInterface;

/**
 * Created by Keaton MacLeod on 6/6/2017.
 */

public class PersonalPlantView extends AppCompatActivity {

	private static final String TAG = "PersonalPlantView"; // for logging purposes

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DatabaseInterface db;
		PersonalPlant plant = null;
		ImageButton waterPlant;
		Button removeFromGarden;
		ImageView plantImage;
		TextView plantTitle, lastTimeWatered, nextWateringPeriod;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_plant_view);
		Log.d(TAG, "onCreate: started.");

		int plantPosition = getIntent().getIntExtra(getString(R.string.plant_id), -1);

		try {
			plant = DatabaseAccess.getDatabaseAccess().getPersonalPlantByID(plantPosition);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseStartFailureException e) {
			e.printStackTrace();
		}

		plantImage = (ImageView) findViewById(R.id.personalPlantViewImage);
		plantTitle = (TextView) findViewById(R.id.personalPlantViewTitle);
		lastTimeWatered = (TextView) findViewById(R.id.personalPlantViewLastTimeWatered);
		nextWateringPeriod = (TextView) findViewById(R.id.personalPlantViewNextWateringPeriod);
		plantImage.setImageResource(getResources().getIdentifier("@drawable/" + plant.getType().getPlantImg(), null, this.getPackageName()));

		plantTitle.setText(plant.getName());
		lastTimeWatered.setText(getString(R.string.lastTimeWatered) + DateFormat.getDateInstance().format(plant.getLastWatered()));

		//calculate the next watering date

		nextWateringPeriod.setText(getString(R.string.nextWateringPeriod) + DateFormat.getDateInstance().format(plant.getNextWatering()));

		//The watering can button and its associated Listener
		waterPlant = (ImageButton) findViewById(R.id.waterPersonalPlant);
		final PersonalPlant finalPlant = plant;
		waterPlant.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(PersonalPlantView.this);
				builder.setTitle(getString(R.string.waterYourPlant) + finalPlant.getName() + getString(R.string.questionMark));
				builder.setMessage(getString(R.string.theNextWateringPeriodWillBeIn) + DateFormat.getDateInstance().format(finalPlant.getNextWatering()));

				//When the user has selected that they have watered their plant
				builder.setPositiveButton(getString(R.string.water), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//TODO this is business logic, should be in business class, not presentation layer
						finalPlant.setLastWatered(new Date());
						try {
							DatabaseAccess.getDatabaseAccess().updatePersonalPlant(finalPlant);
						} catch (SQLException e) {
							Toast.makeText(getApplicationContext(), R.string.app_database_failure, Toast.LENGTH_LONG).show();
							e.printStackTrace();
						} catch (DatabaseStartFailureException e) {
							Toast.makeText(getApplicationContext(), R.string.app_database_start_failure, Toast.LENGTH_LONG).show();
							e.printStackTrace();
						}

						//refresh the activity
						finish();
						startActivity(getIntent());
					}
				});

				builder.setNegativeButton(getString(R.string.cancel), null);

				//Set up the dialogue for the water plant button
				AlertDialog waterPlantDialogue = builder.create();
				waterPlantDialogue.show();
			}
		});

		//The remove from garden button and its associated dialouge
		removeFromGarden = (Button) findViewById(R.id.removeFromGarden);
		removeFromGarden.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(PersonalPlantView.this);
				builder.setTitle(getString(R.string.removeThisPlant));
				builder.setMessage(getString(R.string.thisPlantWillBeRemoved));

				builder.setPositiveButton(getString(R.string.remove), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//TODO Remove the plant from the Garden
					}
				});

				builder.setNegativeButton(getString(R.string.cancel), null);

				AlertDialog removeFromGardenDialogue = builder.create();
				removeFromGardenDialogue.show();
			}
		});
	}
}