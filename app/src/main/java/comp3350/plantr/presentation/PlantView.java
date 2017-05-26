package comp3350.plantr.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import comp3350.plantr.R;
import comp3350.plantr.persistence.TableManager;

public class PlantView extends AppCompatActivity {

    TableManager tableManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
        tableManager = new TableManager(this); //Calls the constructor of TableManager
    }
}