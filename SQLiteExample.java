package com.example.calendarapp;														//My package name

import android.app.Activity;															//Required imports
import android.app.Dialog;																//Required imports
import android.content.Intent;															//Required imports
import android.os.Bundle;																//Required imports
import android.view.View;																//Required imports
import android.view.View.OnClickListener;												//Required imports
import android.widget.Button;															//Required imports
import android.widget.EditText;															//Required imports
import android.widget.TextView;															//Required imports

public class SQLiteExample extends Activity implements OnClickListener{					//Public class extends the activity it implements the on click listeners

	Button sqlUpdate, sqlView, sqlGetinfo, sqlEdit, sqlDelete;							//Button variables
	
	EditText SQLTitle, SQLTime, SQLDetails, SQLRowid;									//Edit text variables
	
	 @Override																			//Override method declared
	 protected void onCreate(Bundle savedInstanceState) {								//Public oncreate method
	        
		 super.onCreate(savedInstanceState);											//This is used for start of activity
	     setContentView(R.layout.sqliteexample);										//Gets the sqlliteexample view
	     
	     sqlUpdate = (Button) findViewById(R.id.SQLUpdate);								//Variable sqlupdate gets SQL update button
	     sqlView = (Button) findViewById(R.id.SQLview);									//Variable sqlView gets SQL view button
	     sqlGetinfo = (Button) findViewById(R.id.SQLGetinfo);							//Variable sqlGetinfo gets  SQL get info button
	     sqlEdit = (Button) findViewById(R.id.SQLEdit);									//Variable sql edit gets SQL edit button
	     sqlDelete = (Button) findViewById(R.id.SQLDelete);								//Variable sql delete gets SQL delete button
	     
	     SQLTitle = (EditText) findViewById(R.id.edit_title);							// SQLtitle edit text finds the text field edit title
	     SQLTime = (EditText) findViewById(R.id.edit_time);								// SQLtime edit text finds the edit time text field
	     SQLDetails = (EditText) findViewById(R.id.edit_details);						// SQLDetails edit text finds the edit details text field 
	     SQLRowid = (EditText) findViewById(R.id.editrowid);							// SQLRowid edit text finds the edit row id text field
	     
	     sqlUpdate.setOnClickListener(this);											// sql update sets on click listener for this button
	     sqlView.setOnClickListener(this);												// sql view sets on click listener for this button
	     sqlGetinfo.setOnClickListener(this);											// sql Getinfo sets on click listener for this button
	     sqlEdit.setOnClickListener(this);												// sql Edit sets on click listener for this button
	     sqlDelete.setOnClickListener(this);											// sql Delete sets on click listener for this button
	     
	 }
	
	@Override																			//Override method delcared
	public void onClick(View v) {														//Public method for onclick method
		switch (v.getId()) {															// get the id of button clicked then launch its activity
		
		case R.id.SQLUpdate:															//Does the SQL update because of the switch case
			
			boolean diditwork = true;													//Boolean statement if true 
			try{																		//Try statement
			String title = SQLTitle.getText().toString();								// 	String title declared
			String time = SQLTime.getText().toString();									// 	String time declared
			String details = SQLDetails.getText().toString();							// 	String details declared
			
			AddAppointment entry = new AddAppointment(SQLiteExample.this);				//Addappointment class equals to addappointment and SQL lite example class
			entry.open();																//Entry open
			entry.createEntry(title, time, details);									//Entry method gets in strings title time details
			entry.close();																//Entry closed
			} catch (Exception e){														// Catch exception declared
				diditwork = false;														// boolean which is false
				String error = e.toString();											// String declared
				Dialog d = new Dialog(this);											// This is showing a progress indicator
				d.setTitle("Information");												//Sets title
				TextView tv = new TextView(this);										// Textview tv set
				tv.setText(error);														//Tv is set to error
				d.setContentView(tv);													//D content view set
				d.show();																// d show opend so soemthing is displayed on screen
			} finally{																	// Finally statement
				if (diditwork){															// if statement declared for diditwork
					Dialog d = new Dialog(this);										//Dialog d set
					d.setTitle("Information");											//Sets title information									
					TextView tv = new TextView(this);									//Textview tv set
					tv.setText("Success");												//Tv sets texts to success
					d.setContentView(tv);												//Sets content view
					d.show();															//show open
				}
			}
			
			break;																		//break set
		case R.id.SQLview:																//Case for sql view button
			Intent i = new Intent(this, SQLView.class);									// Inetnt i calls SQL view class
			startActivity(i);															//starts activity I
			break;																		//Break set
			
		case R.id.SQLGetinfo:															//SQL get info case set

			try{																		//Try catch declared
			String A = SQLRowid.getText().toString();									//String A set to SQL row id
			long l = Long.parseLong(A);													// long declared passing in a string
			AddAppointment addapp = new AddAppointment(this);							//Add appoint declared 
			
			addapp.open();																//Addapp set to open to pass in values
			String returnedTitle = addapp.getTitle(l);									// String return title gets title passes in a value for title
			String returnedDeatils = addapp.getDetails(l);								// String returns details gets details passes in a value for details							
			String returnedTime = addapp.getTime(l);									// String returns time gets time and passes in a value for time
			addapp.close();																// Add app closes
			
			SQLTitle.setText(returnedTitle);											//SQL title field text returns title in this text field
			SQLDetails.setText(returnedDeatils);										//	SQL details field text returns deatils in this text field					
			SQLTime.setText(returnedTime);												//	SQL time field text rerurns time in this text field			
			
		} catch (Exception e){															//Catch exception set
			
			String error = e.toString();												// String error set to get values in
			Dialog d = new Dialog(this);												// Dialog d set to this
			d.setTitle("Information");													// dset title
			TextView tv = new TextView(this);											// Textview set to this
			tv.setText(error);															// tv set to error
			d.setContentView(tv);														// content view for d set
			d.show();																	// d show set with open brackets
		}
			
			break;																		//Break set
			
		case R.id.SQLEdit:																//SQL edit button case
			
			try{																		//Try catch set
			String mtitle = SQLTitle.getText().toString();								// String mtitle set to pass in title
			String mtime = SQLTime.getText().toString();								// String mtime set to pass time
			String mdetails = SQLDetails.getText().toString();							// String mdetails set to pass in details
			String sRow = SQLRowid.getText().toString();								// String mRow ID set to pass in row id
			long lrow = Long.parseLong(sRow);											// Long parselong set to pass in value
			
			AddAppointment ex = new AddAppointment(this); 								//Add appoint declared 
			ex.open();																	// ex open set
			ex.updateEntry(lrow, mtitle, mtime, mdetails);								// update method set it will update the row title time details
			ex.close();																	// closing ex
			
		} catch (Exception e){															// Catching exception
			String error = e.toString();												// String error set to pass in value
			Dialog d = new Dialog(this);												// Dialog d set to this
			d.setTitle("Information");													// d set title passing in information
			TextView tv = new TextView(this);											// Textview tv set
			tv.setText(error);															// Tv set text
			d.setContentView(tv);														// d content view set
			d.show();																	// d show set with open brackets													
		}
			
			break;																		//Break declared
			
		case R.id.SQLDelete:															// SQL DELETE button case set
			try{																		// Try catch exception declared
			String sRow1 = SQLRowid.getText().toString();								// String row equal to the row ID in the database and string set
			long lrow1 = Long.parseLong(sRow1);											// Row1 getting passed in
			
			AddAppointment ex1 = new AddAppointment(this);								// Add appoint declared
			ex1.open();																	// Ex open declared
			ex1.deleteEntry(lrow1);														// delete method set to delete entries and database values
			ex1.close();																// close declared
		} catch (Exception e){															//
		
			String error = e.toString();												// String set
			Dialog d = new Dialog(this);												// Dialog D set
			d.setTitle("Information");													//  Set title set
			TextView tv = new TextView(this);											// Textview tv set
			tv.setText(error);															// Tv set text set
			d.setContentView(tv);														// Content view set
			d.show();																	// d show set with passing brackets
		}
			break;																		//Break declared
		}
	}
}
