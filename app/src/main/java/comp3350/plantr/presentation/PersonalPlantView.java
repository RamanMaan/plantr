package comp3350.plantr.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.plantr.R;
import comp3350.plantr.application.DatabaseAccess;
import comp3350.plantr.objects.Plant;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;

public class PersonalPlantView extends AppCompatActivity {

	private static final String TAG = "PersonalPlantView"; // for logging purposes

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		DatabaseInterface db;
		Plant plant;
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

		plantImage.setImageResource(getResources().getIdentifier("@drawable/"+plant.getPlantImg(), null, this.getPackageName()));
		plantTitle.setText("Benny");
		lastTimeWatered.setText(getString(R.string.lastTimeWatered) + " mm/dd/yy");
		nextWateringPeriod.setText(getString(R.string.nextWateringPeriod) + " mm/dd/yy");
		plantNotes.setText("This is Benny! He's my first plant that I'm going to grow with the plantr application!");
	}

}