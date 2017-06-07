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

public class PlantView extends AppCompatActivity {

    private static final String TAG = "PlantView"; // for logging purposes
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseInterface db;
        Plant plant;
        ImageView plantImage;
        TextView plantTitle, plantDesc;

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

        plantImage.setImageResource(getResources().getIdentifier("@drawable/"+plant.getPlantImg(), null, this.getPackageName()));
        plantTitle.setText(plant.getPlantName());
        plantDesc.setText(plant.getPlantDesc());
    }

}