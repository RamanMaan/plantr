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

import java.util.List;

import comp3350.plantr.R;
import comp3350.plantr.business.AccessPlants;
import comp3350.plantr.model.Plant;

/**
 * Plantipedia view
 */

public class PlantipediaFragment extends Fragment {

	private AccessPlants accessPlants;

	public PlantipediaFragment() {
		//required empty public constructor
	}

	View myView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.plantipedia_layout, container, false);

		accessPlants = new AccessPlants();

		List<Plant> plantList = accessPlants.getPlants();
		ListView listView = (ListView) myView.findViewById(R.id.plantipedia_listview);
		PlantListAdapter listViewAdapter = new PlantListAdapter(getActivity(), R.layout.activity_plant_list_item, plantList);

		listView.setAdapter(listViewAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), PlantView.class);
				//store the plant ID with the intent to display
				intent.putExtra(getString(R.string.plant_id), position);
				startActivity(intent);
			}
		});

		return myView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getActivity().setTitle(getString(R.string.plantipediaview_title));
	}
}
