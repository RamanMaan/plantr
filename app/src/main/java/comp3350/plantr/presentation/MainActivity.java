package comp3350.plantr.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseCloseFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.User;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_main);
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);

			DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
			ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
					this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
			drawer.addDrawerListener(toggle);
			toggle.syncState();

			NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
			navigationView.setNavigationItemSelectedListener(this);

			View header = navigationView.getHeaderView(0);
			User loggedInUser = UserManager.getUser();
			TextView userName = (TextView) header.findViewById(R.id.nav_user_name);
			userName.setText(loggedInUser.getName());
			TextView userEmail = (TextView) header.findViewById(R.id.nav_user_email);
			userEmail.setText(loggedInUser.getEmail());

			//set default fragment to garden layout
			displayFragment(R.id.nav_garden_layout);
		} catch (UserLoginException e) {
			Toast.makeText(getApplicationContext(), R.string.login_user_login_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		try {
			DatabaseAccess.close();
		} catch (DatabaseCloseFailureException e) {
			Toast.makeText(getApplicationContext(), R.string.main_database_close_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		displayFragment(item.getItemId());
		return true;
	}

	private void displayFragment(int id) {
		Fragment fragment = null;

		switch (id) {
			case R.id.nav_garden_layout:
				fragment = new GardenFragment();
				break;
			case R.id.nav_plantipedia_layout:
				fragment = new PlantipediaFragment();
				break;
		}

		if (fragment != null) {
			getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, fragment)
					.commit();
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
	}


}
