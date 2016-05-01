package com.codeitsuisse.team71.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Input extends Activity {
RadioButton rb1,rb2;
	Databasehelper mydb;EditText ed,ed1;Spinner spin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		mydb=new Databasehelper(this);
		ed=(EditText)findViewById(R.id.editText1);
		ed1=(EditText)findViewById(R.id.editText2);
spin=(Spinner)findViewById(R.id.spinner1);
rb2=(RadioButton)findViewById(R.id.radio0);//pl
rb1=(RadioButton)findViewById(R.id.radio1);//paid

	rb1.setChecked(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input, menu);
		return true;
	}
	
	public void paid(View v){
		rb2.setChecked(false);
		
	}
	
	public void liability(View v){
		rb1.setChecked(false);
	}
	
	public void onSubmit(View v)
	{String status,des;
	des=ed1.getText().toString();
		String category=spin.getSelectedItem().toString();
	if(rb1.isChecked())
	{	
	//	rb2.setChecked(false);
		status="paid";
	}
	else
	{
	//	rb1.setChecked(false);
		status="liability";
	}	//update database
	boolean inserted=mydb.insertData(category, status, Double.parseDouble(ed.getText().toString()),des);
			if(inserted)
				Toast.makeText(this, "added successfully.", Toast.LENGTH_LONG).show();
			
		Intent in= new Intent();
		setResult(1);
		finish();
	}

}
