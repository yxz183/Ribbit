package com.example.ribbit1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {
	
	protected EditText mUsername;
	protected EditText mPassword;
	protected EditText mEmail;
	protected Button mSignUpButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);//must before setContentView

		setContentView(R.layout.activity_sign_up);
		
		mUsername = (EditText) findViewById(R.id.usernameField1);
		mPassword = (EditText) findViewById(R.id.passwordField1);
		mEmail = (EditText) findViewById(R.id.emailField);
		mSignUpButton = (Button) findViewById(R.id.signUpButton1);
		mSignUpButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				

				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				String email = mEmail.getText().toString();
				
				username = username.trim();
				password = password.trim();
				email = email.trim();
				
				if(username.isEmpty()||password.isEmpty()||email.isEmpty()){
					AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this); 
					builder.setMessage(R.string.signup_error_message);
					builder.setTitle(R.string.signup_error_title);
					builder.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
					
				}
				else{
					setProgressBarIndeterminateVisibility(true);
					// create the new user
					ParseUser user = new ParseUser();
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);

					user.signUpInBackground(new SignUpCallback() {
					  public void done(ParseException e) {
						  setProgressBarIndeterminateVisibility(false);
						  if(e == null){
							  //Success!
							  Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
							  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							  startActivity(intent);
						  }
						  else{
							  AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this); 
							  builder.setMessage(e.getMessage());
							  builder.setTitle(R.string.signup_error_title);
							  builder.setPositiveButton(android.R.string.ok, null);
							  AlertDialog dialog = builder.create();
							  dialog.show();
							  
						  }
					  }
					});
				}
			}
		});
	}
}
