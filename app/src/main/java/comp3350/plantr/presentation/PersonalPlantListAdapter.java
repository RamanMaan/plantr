package comp3350.plantr.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import comp3350.plantr.R;
import comp3350.plantr.model.PersonalPlant;

/**
 * Created by Austin on 2017-06-28.
 */

public class PersonalPlantListAdapter extends ArrayAdapter<PersonalPlant> {
	private Context context;

	public PersonalPlantListAdapter(Context context, int resource, List<PersonalPlant> personalPlants) {
		super(context, resource, personalPlants);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		PersonalPlant pPlant = getItem(position);

		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_plant_list_item, parent, false);
		}
		// Lookup view for data population
		TextView plantName = (TextView) convertView.findViewById(R.id.plantlist_text);
		TextView plantType = (TextView) convertView.findViewById(R.id.plantlist_text);
		ImageView plantImg = (ImageView) convertView.findViewById(R.id.plantlist_icon);
		// Populate the data into the template view using the data object
		plantName.setText(pPlant.getName());
		plantType.setText(pPlant.getType().getPlantName());
		plantImg.setImageResource(context.getResources().getIdentifier("@drawable/" + pPlant.getType().getPlantImg(), null, context.getPackageName()));

		// Return the completed view to render on screen
		return convertView;
	}

}

