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
		Plant plant;
		ImageButton waterPlant;
		ImageView plantImage;
		TextView plantTitle, plantNotes;
		TextView lastTimeWatered, nextWateringPeriod;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_plant_view);
		Log.d(TAG, "onCreate: started.");

		// initialize the stub database
		db = DatabaseAccess.open();

		int plantPosition = getIntent().getIntExtra(getString(R.string.plantID), -1);
		plant = db.getPlant(plantPosition);

		plantImage = (ImageView) findViewById(R.id.personalPlantViewImage);
		plantTitle = (TextView) findViewById(R.id.personalPlantViewTitle);
		plantNotes = (TextView) findViewById(R.id.personalPlantViewNotes);
		lastTimeWatered = (TextView) findViewById(R.id.personalPlantViewLastTimeWatered);
		nextWateringPeriod = (TextView) findViewById(R.id.personalPlantViewNextWateringPeriod);

		plantImage.setImageResource(getResources().getIdentifier("@drawable/" + plant.getPlantImg(), null, this.getPackageName()));
		plantTitle.setText("Benny");
		lastTimeWatered.setText(getString(R.string.lastTimeWatered) + " mm/dd/yy");
		nextWateringPeriod.setText(getString(R.string.nextWateringPeriod) + " mm/dd/yy");
		plantNotes.setText("This is Benny! He's my first plant that I'm going to grow with the plantr application!");

		waterPlant = (ImageButton) findViewById(R.id.waterPersonalPlant);
		waterPlant.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(PersonalPlantView.this);
				builder.setTitle("Water your plant?");
				builder.setMessage("The next time you will have to water <plant_name> is in <x> hours.");

				//When the user has selected that they have watered their plant
				builder.setPositiveButton("Water", new DialogInterface.OnClickListener() {
				@Override
					public void onClick(DialogInterface dialog, int which) {
					//TODO Computation for the next time the user waters their plant
				}
				});

				builder.setNegativeButton("Cancel", null);

				//Set up the dialogue for the water plant button
				AlertDialog waterPlantDialogue = builder.create();
				waterPlantDialogue.show();
			}
		});
	}
}