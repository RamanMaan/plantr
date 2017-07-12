package comp3350.plantr.presentation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;

/**
 * The Garden View
 * <p>
 * Display the users Garden here
 */

public class GardenFragment extends Fragment {

	public GardenFragment() {
		//required Empty constructor
	}

	View myView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.garden_layout, container, false);

		Garden myGarden = new Garden();
		try {
			myGarden.addPlants(DatabaseAccess.getDatabaseAccess().getAllPersonalPlants());
		} catch (DatabaseStartFailureException | SQLException e) {
			Toast.makeText(getActivity().getApplicationContext(), R.string.app_database_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (UserLoginException e) {
			Toast.makeText(getActivity().getApplicationContext(), R.string.login_user_login_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		ListView listView = (ListView) myView.findViewById(R.id.garden_view);
		PersonalPlantListAdapter listViewAdapter = new PersonalPlantListAdapter(getActivity(), R.layout.activity_plant_list_item, myGarden.getAllPlants());

		listView.setAdapter(listViewAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int plantID = ((PersonalPlant) parent.getAdapter().getItem(position)).getID();
				Intent intent = new Intent(getActivity(), PersonalPlantView.class);
				//store the plant ID with the intent to display
				intent.putExtra(getString(R.string.plant_id), plantID);
				startActivity(intent);
			}
		});

		return myView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getActivity().setTitle(getString(R.string.gardenview_title));
	}
}
