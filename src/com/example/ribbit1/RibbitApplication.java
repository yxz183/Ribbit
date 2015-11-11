package com.example.ribbit1;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class RibbitApplication extends Application {
	
	// Enable Local Datastore.
	@Override
	public void onCreate(){
		super.onCreate();
		Parse.enableLocalDatastore(this);
		 
		Parse.initialize(this, "W48z92FMHA3ezKLhUnk9BNEuJTjxiLQ6xLv1ePIe", "Qb1XUATLFBSBnfSYYPlybqFjSD37ptxuc0nqghPa");
		
	}
	

}
