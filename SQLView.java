package com.example.calendarapp;																	//My package name											

import android.app.Activity;																		//Required imports
import android.os.Bundle;																			//Required imports
import android.widget.TextView;																		//Required imports

public class SQLView extends Activity{																// Public class extends the activity

	 @Override																						//Override method declared
	 protected void onCreate(Bundle savedInstanceState) {											//Protected method for onCreate set
	        
		 super.onCreate(savedInstanceState);														// This is called for when activity is starting
		 setContentView(R.layout.sqlview);															// Goes to the sql view xml
		 TextView sqlInfo = (TextView) findViewById(R.id.tvSQLinfo);								// Finds the sql id tvSQLinfo
		 AddAppointment info = new AddAppointment(this);											// Addappointment set		 											// context of this class
		 info.open();																				// Info open set
		 String data = info.getData();																// String set to pass in values
		 info.close();																				// Info close
		 sqlInfo.setText(data);																		// Set text get the data
	 }
}
