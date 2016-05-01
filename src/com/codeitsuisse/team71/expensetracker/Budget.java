package com.codeitsuisse.team71.expensetracker;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Budget extends Activity {
	Databasehelper dbh;
	EditText editText1,editText2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
		editText1= (EditText) findViewById(R.id.editText1);
		editText2= (EditText) findViewById(R.id.editText2);
		dbh=new Databasehelper(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.budget, menu);
		return true;
	}
	
	public void onSubmit( View v)
	{
		String s =editText1.getText().toString();
		String t= editText2.getText().toString();
		int c=0;
		float d=0,e=0;
		
		if(s.equals("") || t.equals(""))
		{
			Toast.makeText(this	, "Please enter a value", Toast.LENGTH_LONG).show();
			c++;
		}
		
		try{
			d=(Float.parseFloat(s));
			e=(Float.parseFloat(t));
			if(d <0 || e<0)
			{
				Toast.makeText(this, "Enter a positive value", Toast.LENGTH_LONG).show();
				c++;
			}
			if(d<e)
			{
				Toast.makeText(this, "Salary must be greater than savings goal!", Toast.LENGTH_LONG).show();
				c++;
			}
			
				
		}
		catch(Exception exc){
			c++;
			Toast.makeText(this, "Enter a valid figure", Toast.LENGTH_LONG).show();
		}
	
		if(c==0){
		float budget= d-e;
	//	boolean inserted=mydb2.insertData(category, status, Double.parseDouble(ed.getText().toString()),des);
		
		Intent intent= new Intent();
	     intent.putExtra("budget",budget);
	     intent.putExtra("goal",e);  
	     dbh.insertData2(budget, e);
	     setResult(1,intent);  
         finish();//finishing activity  
		}
	}
	
	
}
