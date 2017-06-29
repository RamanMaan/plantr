package comp3350.plantr.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.plantr.R;
import comp3350.plantr.model.Plant;

/**
 * Created by KevinD on 6/6/2017.
 */

public class PlantListAdapter extends ArrayAdapter<Plant> implements Filterable {

	private List<Plant> _plants;
	private List<Plant> _filteredPlants;

	public PlantListAdapter(Context context, int resource, List<Plant> plants) {
		super(context, resource, plants);

		//create a backup of all the plants originally
		this._plants = new ArrayList<>();
		this._plants.addAll(plants);

		//point to listview's plants
		this._filteredPlants = plants;
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
		TextView plantName = (TextView) convertView.findViewById(R.id.plantlist_text);
		ImageView plantImg = (ImageView) convertView.findViewById(R.id.plantlist_icon);

		// Populate the data into the view using the data object
		plantName.setText(plant.getPlantName());
		plantImg.setImageResource(convertView.getResources().getIdentifier("@drawable/" + plant.getPlantImg(), null, convertView.getContext().getPackageName()));

		// Return the completed view to render on screen
		return convertView;
	}

	@Override
	public Filter getFilter() {

		Filter filter = new Filter() {

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				//return filteredPlants to original plants state
				_filteredPlants.clear();
				_filteredPlants.addAll(_plants);

				//remove all that don't fit filter
				_filteredPlants.retainAll((List<Plant>) results.values);
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				ArrayList<Plant> newList = new ArrayList<>();

				constraint = constraint.toString().toLowerCase();
				for (int i = 0; i < _plants.size(); i++) {
					String dataNames = _plants.get(i).getPlantName();
					if (dataNames.toLowerCase().startsWith(constraint.toString())) {
						newList.add(_plants.get(i));
					}
				}

				results.count = newList.size();
				results.values = newList;

				return results;
			}
		};

		return filter;
	}
}
