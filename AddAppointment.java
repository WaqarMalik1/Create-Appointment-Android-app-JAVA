package com.example.calendarapp;					//package name

import android.app.Activity;						//Required imports
import android.os.Bundle;							//Required imports
import android.content.ContentValues;				//Required imports
import android.content.Context;						//Required imports
import android.database.Cursor;						//Required imports
import android.database.SQLException;				//Required imports
import android.database.sqlite.SQLiteDatabase;		//Required imports
import android.database.sqlite.SQLiteOpenHelper;	//Required imports


public class AddAppointment extends Activity{		//Public class for affappointments which extends

	public static final String DATABASE_NAME = "appointmentsdb"; 	//The database name
	public static final String DATABASE_TABLE = "Appointments";		//The database table
	public static final int DATABASE_VERSION = 1; 					//The version of database
	
	public static final String KEY_ROWID = "_id";					//The row ID
	public static final String KEY_TITLE = "title";					//The title
	public static final String KEY_TIME = "time";					// The time
	public static final String KEY_DETAILS = "details";				//The details
	public static final String KEY_DATE = "date";					//The date field

	private DbHelper myhelper;										//variable for helper	
	private final Context mycontext;								//variable for context
	private SQLiteDatabase mydatabase;								// variable for database
	
	 @Override														//over ride method
	 protected void onCreate(Bundle savedInstanceState) {			//Method of oncreate
	        
		 super.onCreate(savedInstanceState);						//This is used for start of activity	     
	 }
	
	 private static class DbHelper extends SQLiteOpenHelper{		//Private class declared

			public DbHelper(Context context){						//contexts in a method
				super(context, DATABASE_NAME, null, DATABASE_VERSION);	//Manages databases
			}

			@Override												//Override method
			public void onCreate(SQLiteDatabase db) {				//Public method for SQLite database		
				db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +	//Executes creates table plus database table
				          KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 	//Plus keyrow id which is a primary key 
						  KEY_TITLE + " TEXT NOT NULL, " +		//Plus keytite with text not null
						  KEY_TIME + " TEXT NOT NULL, " +		// key time	  with text not null
						  KEY_DETAILS + " TEXT NOT NULL);"		//key details with text not null			
						);
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	//method for upgrading the database
				
				db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE); 	// prevent copies
				onCreate(db); 											// create database	
			}
	 		}
	
	 public AddAppointment(Context c){								//Public method for adding appoint with the context
		 mycontext = c;	 											// give add appointment a context
	 }
	
	 public AddAppointment open() throws SQLException{	// public method open for adding appointment with throwing sql expection to prevent app crashing
		 myhelper = new DbHelper(mycontext);			// give context to database helper
		 mydatabase = myhelper.getWritableDatabase();	// setup database through helper
		 return this;									//returns					
	 }
	 
	 public void close(){								//Public method for closing							
		 myhelper.close(); 								// close database helper
	 }

	public long createEntry(String title, String time, String details) {	// Long method passing string values in here
		ContentValues cv = new ContentValues();								//Content values declared
		cv.put(KEY_TITLE, title);											// put key title and title string
		cv.put(KEY_TIME, time);												// put key time and time string
		cv.put(KEY_DETAILS, details);										// put key details and details string
		return mydatabase.insert(DATABASE_TABLE, null, cv);					//Returns my database and inserts table into it
	}

	public String getData() {												//method declared to get data												
		String[] columns = new String[]{ KEY_ROWID, KEY_TITLE, KEY_TIME,KEY_DETAILS}; 	// set up columns array with all columns
		Cursor c = mydatabase.query(DATABASE_TABLE, columns, null, null, null, null, null); // A cursor implementation that exposes results from a query
		String result = "";																	//String result
		
		int iRow = c.getColumnIndex(KEY_ROWID);			//returns key row id
		int iTitle = c.getColumnIndex(KEY_TITLE);		//returns key row title
		int iTime = c.getColumnIndex(KEY_TIME);			//returns key row time
		int iDetails = c.getColumnIndex(KEY_DETAILS);	//returns key row details
		
		// start at beginning
		// if cursor is at the position after the last entry in database then stop
		// move cursor forward
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			
			// get string version of ID, get title, time and details
			result = result + c.getString(iRow) + " " + c.getString(iTitle) + " " +
		               c.getString(iTime) + " " + c.getString(iDetails) + "\n";
		}
		
		return result;																							//returns result
	}

	public String getTitle(long l)  throws SQLException{														//get title method with throws sql exception
		String[] columns = new String[]{ KEY_ROWID, KEY_TITLE, KEY_TIME,KEY_DETAILS}; 							// set up columns array with all columns
		Cursor c = mydatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l , null, null, null, null); 	// start at beginning
		if (c != null){																							// if cursor is at the position after the last entry in database then stop
			c.moveToFirst();																					// move cursor forward
			String title = c.getString(1);																		//get string position 1 which is title
			return title;																						// Returns title
		}
		
		return null;																							//Returns null
	}

	public String getDetails(long l)  throws SQLException{														//Public string details which thrown an SQL exception
	String[] columns = new String[]{ KEY_ROWID, KEY_TITLE, KEY_TIME,KEY_DETAILS};								// set up columns array with all columns
	Cursor c = mydatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l , null, null, null, null);			// start at beginning
	if (c != null){																								// if cursor is at the position after the last entry in database then stop			
	c.moveToFirst();																							// move cursor forward																								
	String details = c.getString(3);																			//get string position 3 which is details
	return details;																								//Returns details
		}
		
		return null;																							//Returns null
		
	}

	public String getTime(long l) throws SQLException {															//Public string time which thrown an SQL exception
	String[] columns = new String[]{ KEY_ROWID, KEY_TITLE, KEY_TIME,KEY_DETAILS};							    // set up columns array with all columns
	Cursor c = mydatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l , null, null, null, null);		    // start at beginning
	if (c != null){																								// if cursor is at the position after the last entry in database then stop
	c.moveToFirst();																							// move cursor forward	
	String time = c.getString(2);																				//get string position 2 which is time
	return time;																								//Returns time
		}
		
		return null;																							//returns null
	}

	public void updateEntry(long lrow, String mtitle, String mtime, String mdetails) throws SQLException {		//Public void update method with throws exception
		ContentValues cvUpdate = new ContentValues();															//Declaring content values for update method
		cvUpdate.put(KEY_TITLE, mtitle);																		//Updates title	and passes mtitle string		
		cvUpdate.put(KEY_TIME, mtime);																			//Updates time	and passes mtime string
		cvUpdate.put(KEY_DETAILS, mdetails);																	//Updates details and passes mdetails string
		mydatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lrow, null);								//Updates database table and row id
	}

	public void deleteEntry(long lrow1) throws SQLException{													//Public void delete entry method
		mydatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lrow1, null);										//Delets the row ID from the database table
	}
}
