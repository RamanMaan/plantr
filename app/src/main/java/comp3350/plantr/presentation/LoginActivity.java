package comp3350.plantr.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		copyDatabaseToDevice();
		try {
			//This is where the database is switched between stub and regular
			DatabaseAccess.open();
			//DatabaseAccess.openStub();
		} catch (DatabaseStartFailureException e) {
			Toast.makeText(getApplicationContext(), R.string.login_database_init_failure, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		setContentView(R.layout.activity_login);
	}

	public void login(View v) {
		//get email/password input
		EditText emailView = (EditText) findViewById(R.id.login_email);
		String email = emailView.getText().toString();
		if (UserManager.invalidEmail(email)) {
			emailView.setError(getString(R.string.login_email_invalid));
		}

		EditText passView = (EditText) findViewById(R.id.login_password);
		String password = passView.getText().toString();
		if (UserManager.invalidPassword(password)) {
			passView.setError(getString(R.string.login_password_invalid));
		}

		try {
			UserManager.loginUser(email, password);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		} catch (UserLoginException e) {
			Toast.makeText(getApplicationContext(), R.string.login_failedlogin, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
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
