package comp3350.plantr.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.plantr.R;
import comp3350.plantr.business.objects.Plant;

/**
 * Created by KevinD on 6/6/2017.
 */

public class PlantListAdapter extends ArrayAdapter<Plant> {

	public PlantListAdapter(Context context, ArrayList<Plant> plants) {
		super(context, 0, plants);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Plant plant = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_plant_list_item, parent, false);
		}
		// Lookup view for data population
		TextView plantName = (TextView) convertView.findViewById(R.id.plantListName);
		// Populate the data into the template view using the data object
		plantName.setText(plant.getPlantName());
		// Return the completed view to render on screen

		return convertView;
	}
}
