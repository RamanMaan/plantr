package comp3350.plantr.presentation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.Plant;

/**
 * Plantipedia view
 */

public class PlantipediaFragment extends Fragment {

	public PlantipediaFragment() {
		//required empty public constructor
	}

	View myView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.plantipedia_layout, container, false);

		List<Plant> plantList = null;
		try {
			plantList = DatabaseAccess.getDatabaseAccess().getAllPlants();
		} catch (DatabaseStartFailureException | SQLException e) {
			Toast.makeText(getActivity().getApplicationContext(), R.string.app_database_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		EditText search = (EditText) myView.findViewById(R.id.plantipedia_searchbar);
		final ListView listView = (ListView) myView.findViewById(R.id.plantipedia_listview);
		final PlantListAdapter listViewAdapter = new PlantListAdapter(getActivity(), R.layout.activity_plant_list_item, plantList);

		listView.setTextFilterEnabled(true);
		listView.setAdapter(listViewAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int plantID = ((Plant) parent.getAdapter().getItem(position)).getPlantID();
				Intent intent = new Intent(getActivity(), PlantView.class);
				//store the plant ID with the intent to display
				intent.putExtra(getString(R.string.plant_id), plantID);
				startActivity(intent);
			}
		});

		search.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				listViewAdapter.getFilter().filter(s);
			}

			@Override
			public void afterTextChanged(Editable s) {}
		});

		return myView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getActivity().setTitle(getString(R.string.plantipediaview_title));
	}
}
