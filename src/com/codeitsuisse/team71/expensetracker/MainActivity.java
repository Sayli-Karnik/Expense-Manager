package com.codeitsuisse.team71.expensetracker;
//import com.codeitsuisse.*;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//TextView t1= (TextView) findViewById(R.id.textView1);
	int temp=0;
	TextView t1, t3,t2;float exp=0;Databasehelper mydb2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t1= (TextView) findViewById(R.id.textView1);
		t2= (TextView) findViewById(R.id.textView2);
		t3= (TextView) findViewById(R.id.textView3);
		
		mydb2=new Databasehelper(this);
		
	
	}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	
	float f1=0, f2=0;
	Cursor cur1= mydb2.getData2();
	if (cur1.getCount()==0)
		{t1.setText(f1+"");
		t3.setText(f2+"");
		}
		
	else
	{
		while(cur1.moveToNext())
		{
			f1= cur1.getFloat(0);
			f2= cur1.getFloat(1);	
		}
	
	t1.setText(f1+"");
	t3.setText(f2+"");
	}
	System.out.println("hello");
	float f=0;
	
	
	//try{
	/*Cursor cur=mydb2.getData2();
	System.out.println(cur.getPosition());
	if (cur.getCount()==0)
		t2.setText(0.0+"");
	else
	{
		while(cur.moveToNext())
		{
			f=cur.getFloat(0);
		
		}
		t2.setText(f+"");
	//System.out.println(cur.getPosition());
	}*/
	/*catch(Exception e)
	{
		
	}*/
	Cursor cur=mydb2.getData();
	//System.out.println(cur.getPosition());
	if (cur.getCount()==0)
		t2.setText(0.0+"");
	else
	{f=0;
		while(cur.moveToNext())
		{
			f+=cur.getFloat(2);
		temp=cur.getInt(4);
		}
		mydb2.func(temp);
		t2.setText(f+"");
		if(f>f1)
		{
			Toast.makeText(getApplicationContext(), "You have exceeded your budget!", Toast.LENGTH_LONG).show();
		}
	//System.out.println(cur.getPosition());
	}
	
	
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void goto_bgt(View v){
		Intent i= new Intent (this, Budget.class);
		startActivityForResult(i, 1);
		
	}
	public void goto_exp(View v){
		Intent i= new Intent(this, Expenses.class);
		startActivityForResult(i, 10);
	
	}
	@Override
	/*protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Intent in =new Intent();
	
	}*/
	   protected void onActivityResult(int requestCode, int resultCode, Intent data)  
       {  
                 super.onActivityResult(requestCode, resultCode, data);  
                  // check if the request code is same as what is passed  here it is 2  
                   if(requestCode==1)  
                         {  
                            double k=data.getDoubleExtra("budget",0);
                            double d= data.getDoubleExtra("goal",0);
                            t1.setText(k+"");
                            
                            t3.setText(d+"");
                         }  
    /*               if(requestCode==10)
                   {	exp=data.getFloatExtra("sum1", 0);
           		t2.setText(exp+"");}
      */ }
	

}
