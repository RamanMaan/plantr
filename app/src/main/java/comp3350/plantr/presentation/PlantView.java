package comp3350.plantr.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.plantr.R;
import comp3350.plantr.objects.Plant;
import comp3350.plantr.persistence.StubDatabase;

public class PlantView extends AppCompatActivity {

    private static final String TAG = "PlantView"; // for logging purposes
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StubDatabase testStub;
        Plant testPlant;
        ImageView plantImage;
        TextView plantTitle, plantDesc;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
        Log.d(TAG, "onCreate: started.");

        // initialize the stub database
        testStub = new StubDatabase();
        testStub.open();

        testPlant = testStub.getPlant("aloe");

        plantImage = (ImageView) findViewById(R.id.plantImageView);
        plantTitle = (TextView) findViewById(R.id.plantViewTitle);
        plantDesc = (TextView) findViewById(R.id.plantViewDescription);

        plantImage.setImageResource(getResources().getIdentifier("@drawable/"+testPlant.getPlantImg(), null, this.getPackageName()));
        plantTitle.setText(testPlant.getPlantName());
        plantDesc.setText(testPlant.getPlantDesc());
    }

}