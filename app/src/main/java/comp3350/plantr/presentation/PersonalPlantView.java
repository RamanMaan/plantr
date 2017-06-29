package comp3350.plantr.presentation;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;

/**
 * Created by Keaton MacLeod on 6/6/2017.
 */

public class PersonalPlantView extends AppCompatActivity {

	private static final String TAG = "PersonalPlantView"; // for logging purposes

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		DatabaseInterface db;
		final PersonalPlant plant;
		ImageButton waterPlant;
		Button removeFromGarden;
		ImageView plantImage;
		TextView plantTitle, plantNotes;
		TextView lastTimeWatered, nextWateringPeriod;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_plant_view);
		Log.d(TAG, "onCreate: started.");

		// initialize the stub database
		db = DatabaseAccess.open();

		int plantPosition = getIntent().getIntExtra(getString(R.string.plant_id), -1);
		plant = db.getPersonalPlantByID(plantPosition);

		plantImage = (ImageView) findViewById(R.id.personalPlantViewImage);
		plantTitle = (TextView) findViewById(R.id.personalPlantViewTitle);
		lastTimeWatered = (TextView) findViewById(R.id.personalPlantViewLastTimeWatered);
		nextWateringPeriod = (TextView) findViewById(R.id.personalPlantViewNextWateringPeriod);

		//If the user has their own picture, use it
		if (plant.get_personalPlantImg() != null)
			plantImage.setImageResource(getResources().getIdentifier("@drawable/" + plant.get_personalPlantImg(), null, this.getPackageName()));

		//Otherwise use the default pictured stored in the database
		else
			plantImage.setImageResource(getResources().getIdentifier("@drawable/" + plant.getPersonalPlantType().getPlantImg(), null, this.getPackageName()));

		plantTitle.setText(plant.getPersonalPlantName());
		lastTimeWatered.setText(getString(R.string.lastTimeWatered) + " mm/dd/yy");
		nextWateringPeriod.setText(getString(R.string.nextWateringPeriod) + "[x]days [y]hours");

		//The watering can button and its associated Listener
		waterPlant = (ImageButton) findViewById(R.id.waterPersonalPlant);
		waterPlant.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(PersonalPlantView.this);
				builder.setTitle(getString(R.string.waterYourPlant) + plant.getPersonalPlantName() + getString(R.string.questionMark));
				builder.setMessage(getString(R.string.theNextWateringPeriodWillBeIn) + "[x] days, [y] hours.");

				//When the user has selected that they have watered their plant
				builder.setPositiveButton(getString(R.string.water), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//TODO Computation for the next time the user waters their plant
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
					public void onClick(DialogInterface dialog, int which)
					{

					}
				});

				builder.setNegativeButton(getString(R.string.cancel), null);

				AlertDialog removeFromGardenDialogue = builder.create();
				removeFromGardenDialogue.show();
			}
		});
	}
}