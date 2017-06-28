package comp3350.plantr.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;

/**
 * Created by KevinD on 6/5/2017.
 */

public class PlantListView extends AppCompatActivity{

	private static final String TAG = "PlantListView";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		DatabaseInterface db;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_list_view);
		Log.d(TAG, "onCreate: started.");

		ListView list = (ListView) findViewById(R.id.plantList);

		//initialize the database
		db = DatabaseAccess.open();
		ArrayList<Plant> plantList = (ArrayList<Plant>) db.getAllPlants();
		PlantListAdapter adapter = new PlantListAdapter(this, plantList);

		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(PlantListView.this, PlantView.class);
				//store the plant ID with the intent to display
				intent.putExtra(getString(R.string.plantID), position+1);
				startActivity(intent);
			}
		});

	}
}
