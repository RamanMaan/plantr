package comp3350.plantr.presentation;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseCloseFailureException;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.User;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			copyDatabaseToDevice();
			DatabaseAccess.open();

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

			//TODO make exception more specific
		} catch (DatabaseStartFailureException e) {
			//TODO should make a toast to print msg
			System.out.println("Database failed during open");
			e.printStackTrace();
		} catch (UserLoginException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		try {
			DatabaseAccess.close();
		} catch (DatabaseCloseFailureException e) {
			//TODO add better handling
			System.out.println("Failure during database close.");
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

	private void copyDatabaseToDevice() {
		final String DB_PATH = "db";

		String[] assetNames;
		Context context = getApplicationContext();
		File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
		AssetManager assetManager = getAssets();

		try {
			assetNames = assetManager.list(DB_PATH);
			for (int i = 0; i < assetNames.length; i++) {
				assetNames[i] = DB_PATH + "/" + assetNames[i];
			}
			copyAssetsToDirectory(assetNames, dataDirectory);

			DatabaseAccess.setDBPathName(dataDirectory.toString() + "/" + DatabaseAccess.dbName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
		AssetManager assetManager = getAssets();

		for (String asset : assets) {
			String[] components = asset.split("/");
			String copyPath = directory.toString() + "/" + components[components.length - 1];
			char[] buffer = new char[1024];
			int count;

			File outFile = new File(copyPath);

			if (!outFile.exists()) {
				InputStreamReader in = new InputStreamReader(assetManager.open(asset));
				FileWriter out = new FileWriter(outFile);

				count = in.read(buffer);
				while (count != -1) {
					out.write(buffer, 0, count);
					count = in.read(buffer);
				}

				out.close();
				in.close();
			}
		}
	}
}
