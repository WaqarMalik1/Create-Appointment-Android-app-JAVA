package com.example.calendarapp;											//My package name

import android.os.Bundle;													//Required imports
import android.app.Activity;												//Required imports
import android.view.Menu;													//Required imports
import android.view.View;													//Required imports
import android.content.Intent;												//Required imports
import android.view.View.OnClickListener;									//Required imports
import android.widget.TextView;												//Required imports

public class CalendarActivity extends Activity implements OnClickListener{	//A public method which extends and implements the on click listener method

	private TextView date; 													// Textview date
	
    @Override																//Override method declared
    protected void onCreate(Bundle savedInstanceState) {					//Method of oncreate
        super.onCreate(savedInstanceState);									//This is used for start of activity
        setContentView(R.layout.activity_calendar);							//This goes to the layout activity calender
        
        View createButton = findViewById(R.id.create_button);				//View createbutton find the id create button
        createButton.setOnClickListener(this);								// Set up click listeners for all the buttons
        View viewButton = findViewById(R.id.view_button);					// View for the view button finds view button id
        viewButton.setOnClickListener(this);								// Set up click listeners for all the buttons
        View deleteButton = findViewById(R.id.delete_button);				//View deletebutton find the id delete button
        deleteButton.setOnClickListener(this);								// Set up click listeners for all the buttons
        View moveButton = findViewById(R.id.move_button);					//View movebutton find the id move button
        moveButton.setOnClickListener(this);								// Set up click listeners for all the buttons
        View searchButton = findViewById(R.id.search_button);				//View search find the id search button
        searchButton.setOnClickListener(this);								// Set up click listeners for all the buttons
        View translateButton = findViewById(R.id.translate_button);			//View translate find the id translate button
        translateButton.setOnClickListener(this);							// Set up click listeners for all the buttons        
    }


    @Override																//Override method delclared
	public void onClick(View v) {											//Public method for onclick method
    	switch (v.getId()) {												// get the id of button clicked then launch its activity
    	case R.id.create_button:											//case for the create button
    		Intent i = new Intent(this, SQLiteExample.class);				//This is a intent declared which will start the activity and it gets it from the SQLiteExample class for the create button
    		startActivity(i);												//Starts activity I
    		break;															//Break for next button declared
    	
    	case R.id.view_button:												//Case for view button
    		Intent ii = new Intent(this, SQLView.class);					//This is a intent declared which will start the activity and it gets it from the SQLview class for the view button
    		startActivity(ii);												//Starts activity II
    		break;															//Break for the next button declared
    		
    	case R.id.delete_button:											//Case for the delete button
    		Intent iii = new Intent(this, SQLiteExample.class);				//This is a intent declared which will start the activity and it gets it from the SQLiteExample class for the delete button
    		startActivity(iii);												//Starts activity III
    		break;															//Break declared    	
    	}
    }
    
    @Override																//Override method declared
    public boolean onCreateOptionsMenu(Menu menu) {							//Menu options created
        getMenuInflater().inflate(R.menu.calendar, menu);					// Inflate the menu; this adds items to the action bar if it is present.
        return true;														//Retruns value
    }
}
