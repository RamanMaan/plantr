package comp3350.plantr.presentation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DatabaseInterface;

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
	private Garden myGarden;
	private FloatingActionButton addButton;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.garden_layout, container, false);

		//do the stuff here
		myGarden = new Garden();
		myGarden.addPlants(DatabaseAccess.open().getAllPersonalPlants());
		DatabaseInterface db = DatabaseAccess.open();
		addButton = (FloatingActionButton) myView.findViewById(R.id.addPlantButton);
		ListView listView = (ListView) myView.findViewById(R.id.garden_view);
		PersonalPlantListAdapter listViewAdapter = new PersonalPlantListAdapter(getActivity(), R.layout.activity_plant_list_item, (List<PersonalPlant>) myGarden.getAllPlants());

		listView.setAdapter(listViewAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), PersonalPlantView.class);
				intent.putExtra(getString(R.string.plant_id), position);
				startActivity(intent);
			}
		});

		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//on click, take to modified plantipedia view to add plants to their garden
				Fragment fragment = new PlantipediaFragment();
				if (fragment != null) {
					getFragmentManager().beginTransaction()
							.replace(R.id.content_frame, fragment)
							.commit();
				}
				DrawerLayout drawer = (DrawerLayout) myView.findViewById(R.id.drawer_layout);
				drawer.closeDrawer(GravityCompat.START);
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
