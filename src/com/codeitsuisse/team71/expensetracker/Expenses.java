package com.codeitsuisse.team71.expensetracker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Expenses extends Activity {
	ListView l;Databasehelper mydb;List<ListViewItem> items;
	//float sum=0;
	GestureDetector gd;
	MyAdapter adapter;int flag=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);
		l= (ListView)findViewById(R.id.lv1);
		mydb=new Databasehelper(this);
		 items= new ArrayList<Expenses.ListViewItem>();
		 l.setOnItemLongClickListener(new OnItemLongClickListener() {
			 int pos;
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				pos= position;
				
				//mydb.update(pos);
				mydb.delete_row();
				items.remove(pos);
				adapter= new MyAdapter(Expenses.this, items);
				l.setAdapter(adapter);
				flag=1;
			
				Toast.makeText(Expenses.this, "Item deleted!", Toast.LENGTH_LONG).show();
				return true;
				
			}
		 
		 });
		// ListView lv = getListView();
		 //setContentView(lv);
		 }
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		l.setOnItemClickListener(new ItemList());
		if(flag==0)
		items.clear();
				Cursor cur=mydb.getData();
				if(cur.getCount()==0)
					Toast.makeText(this, "No expenses to display", Toast.LENGTH_LONG).show();
				
				while(cur.moveToNext())
				{
					ListViewItem a = new ListViewItem();
					a.category=cur.getString(0);
					a.status=cur.getString(1);
					a.amount=Float.parseFloat(cur.getString(2));
					a.desc=cur.getString(3);
					items.add(a);
				//	sum+=cur.getDouble(2);
				}
				//mydb.insertData2();
				adapter= new MyAdapter(this, items);
				l.setAdapter(adapter);		//ListViewItem a= new ListViewItem();
		
		/*for(int i=0; i<items.size(); i++)
		{
			System.out.print(items.get(i).category+" "+items.get(i).status+" "+items.get(i).amount);
			System.out.println();
		}*/
				/*l.setOnItemClickListener(new OnItemClickListener()
				 {
				     @Override 
				     public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
				     { System.out.println("in");
				     String descr="";
			    	 Cursor cur2=mydb.getData();
			    	 while(cur2.moveToNext())
			    	 {
			    		 if(position==cur2.getPosition())
			    		 { descr= cur2.getString(3);
				         Toast.makeText(Expenses.this, descr, Toast.LENGTH_LONG).show();
			    			 break;}
			    			 
			    	 }
				    	
				     }
				 });*/
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expenses, menu);
		return true;
	}
	
	public class ListViewItem{
		public String category, status,desc;
		public double amount;
	}
	
	public void onAdd(View v){
		Intent in= new Intent(this, Input.class);
		startActivity(in);
		
	}
@Override
public void onBackPressed() {
	//Intent i=new Intent();
	mydb.delete_row();
	for(int i=0;i<items.size();i++)
	{
		String cat=items.get(i).category;
		String sta=items.get(i).status;
		double amt=items.get(i).amount;
		String des=items.get(i).desc;
		mydb.insertData(cat, sta, amt, des);
		
	}
	finish();
		
	
}

}
 class ItemList  extends Expenses implements OnItemClickListener{
 	
 	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  			//ViewGroup viewg=(ViewGroup)view;
  			//TextView textv=(TextView)viewg.findViewById(R.id.country);
  			//Toast.makeText(context, textv.getText().toString(), Toast.LENGTH_LONG).show();
  		//
 		 String descr="";
    	 Cursor cur2=mydb.getData();
    	 while(cur2.moveToNext())
    	 {
    		 if(position==cur2.getPosition())
    		 { descr= cur2.getString(3);
	         Toast.makeText(this, descr, Toast.LENGTH_LONG).show();
    			 break;}
    			 
    	 }
  		}
 }