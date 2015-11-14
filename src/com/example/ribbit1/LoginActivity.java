package com.example.ribbit1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
	
	
	protected EditText mUsername;
	protected EditText mPassword;
	protected Button mLoginButton;
	
	protected TextView mSignUpTextView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);//must before setContentView
		setContentView(R.layout.activity_login);
		
		mSignUpTextView = (TextView) findViewById(R.id.signUpText);
		mUsername = (EditText) findViewById(R.id.usernameField);
		mPassword = (EditText) findViewById(R.id.passwordField);
		mLoginButton = (Button) findViewById(R.id.loginButton);
		mSignUpTextView = (TextView) findViewById(R.id.signUpText);
		
		mLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				
				username = username.trim();
				password = password.trim();
				
				if(username.isEmpty()||password.isEmpty()){
					AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); 
				//	builder.setMessage(R.string.login_error_message);
					builder.setMessage("one of them is empty");
					builder.setTitle(R.string.login_error_title);
					builder.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
					
				}
				else{
					//login
					setProgressBarIndeterminateVisibility(true);
					ParseUser.logInInBackground(username, password, new LogInCallback() {
						@Override  
						public void done(ParseUser user, ParseException e) {
							setProgressBarIndeterminateVisibility(false);
							if(e==null){
								//Success
								Intent intent = new Intent(LoginActivity.this, MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
							}
							else{
								AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); 
								builder.setMessage(e.getMessage());
								builder.setTitle(R.string.login_error_title);
								builder.setPositiveButton(android.R.string.ok, null);
								AlertDialog dialog = builder.create();
								dialog.show();		
							}
						  }
						});	
				}
			}
		});
		
		mSignUpTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
				startActivity(intent);
			}
		});
	}
}
