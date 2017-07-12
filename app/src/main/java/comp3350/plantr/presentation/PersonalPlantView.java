package comp3350.plantr.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import comp3350.plantr.business.AccessGarden;
import comp3350.plantr.business.PersonalPlantManager;
import comp3350.plantr.business.exceptions.DatabaseOutOfBoundsException;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.persistence.DatabaseInterface;

public class PersonalPlantView extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_plant_view);

		final PersonalPlant personalPlant;			//the plant we're viewing
		int plantID;							//the ID of the plant we want to view

		//get the ID of the plant to view
		plantID = getIntent().getIntExtra(getString(R.string.plant_id), -1);

		try {
			//get that plant
			personalPlant = AccessGarden.getPersonalPlantByID(plantID);

			//if the plant returns null AND reaches this point, index was out of bounds
			if (personalPlant == null) {
				throw new DatabaseOutOfBoundsException();
			}

			//plant - title
			TextView plantTitle = (TextView) findViewById(R.id.personalPlantViewTitle);
			plantTitle.setText(personalPlant.getName());

			//plant - image
			ImageView plantImage = (ImageView) findViewById(R.id.personalPlantViewImage);
			plantImage.setImageResource(getResources().getIdentifier("@drawable/" + personalPlant.getType().getPlantImg(), null, this.getPackageName()));

			//plant - last time watered
			TextView lastTimeWatered = (TextView) findViewById(R.id.personalPlantViewLastTimeWatered);
			lastTimeWatered.setText(String.format(getString(R.string.lastTimeWatered), dateToString(personalPlant.getLastWatered())));

			//plant - next time water
			TextView nextWateringPeriod = (TextView) findViewById(R.id.personalPlantViewNextWateringPeriod);
			nextWateringPeriod.setText(String.format(getString(R.string.nextWateringPeriod), dateToString(personalPlant.getNextWatering())));

			//The watering can button and its associated Listener
			ImageButton waterPlant = (ImageButton) findViewById(R.id.waterPersonalPlant);
			waterPlant.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(PersonalPlantView.this);
					builder.setTitle(String.format(getString(R.string.waterYourPlant), personalPlant.getName()));
					builder.setMessage(String.format(getString(R.string.theNextWateringPeriodWillBeIn), dateToString(personalPlant.getNextWatering())));

					//When the user has selected that they have watered their plant
					builder.setPositiveButton(getString(R.string.water), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							PersonalPlantManager.waterPlant(getApplicationContext(), personalPlant);
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
			Button removeFromGarden = (Button) findViewById(R.id.removeFromGarden);
			removeFromGarden.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(PersonalPlantView.this);
					builder.setTitle(getString(R.string.removeThisPlant));
					builder.setMessage(getString(R.string.thisPlantWillBeRemoved));

					builder.setPositiveButton(getString(R.string.remove), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							PersonalPlantManager.removePlant(getApplicationContext(), personalPlant);
							Intent intent = new Intent(getApplicationContext(), MainActivity.class);
							startActivity(intent);
							finish();
						}
					});

					builder.setNegativeButton(getString(R.string.cancel), null);

					AlertDialog removeFromGardenDialogue = builder.create();
					removeFromGardenDialogue.show();
				}
			});
		} catch (SQLException | DatabaseStartFailureException e) {
			Toast.makeText(getApplicationContext(), R.string.app_database_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (DatabaseOutOfBoundsException e) {
			finish();
			Toast.makeText(getApplicationContext(), R.string.database_failure_bounds, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	private String dateToString(Date date) {
		return DateFormat.getDateInstance().format(date);
	}
}