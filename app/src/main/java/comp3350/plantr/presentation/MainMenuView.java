package comp3350.plantr.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import comp3350.plantr.R;

/**
 * Created by KevinD on 6/5/2017.
 */

public class MainMenuView extends AppCompatActivity{

	private static final String TAG = "MainMenuView";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		ImageView viewPlantsImg;
		Button btn;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage_view);
		Log.d(TAG, "onCreate: started.");

		viewPlantsImg = (ImageView) findViewById(R.id.viewPlantsImg);
		viewPlantsImg.setImageResource(getResources().getIdentifier("@drawable/view_plant_img", null, this.getPackageName()));

		btn = (Button) findViewById(R.id.viewPlantsButton);

		btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(MainMenuView.this, PlantListView.class));
			}
		});


	}
}