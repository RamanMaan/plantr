package comp3350.plantr.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.plantr.R;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
}
