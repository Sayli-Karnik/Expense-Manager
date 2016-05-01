package com.codeitsuisse.team71.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper{
public static final String Dname="information6.db";
public static final String table_name="exp";
public static final String col1="CATEGORY";
public static final String col2="STATUS";
public static final String col3="AMT";
public static final String col4="DES";
public static final String col5="SrNo";
public static final String table2_name="bg";
public static final String col12="bgt";
public static final String col22="goal";

public static float initial=0;
public static float tot_sum=initial;

public static int initial_srno=0;
public static int i=initial_srno;
public static final String CREATE_FIRST_TABLE =
"CREATE TABLE " + table_name + " (" +
        col1 + " TEXT, " +
        col2 + " TEXT, " +
        col3 + " REAL, " +
        col4 + " TEXT, " +
        col5 + 	" INTEGER);";

public static final String CREATE_SECOND_TABLE =
"CREATE TABLE " + table2_name + " (" + 
        col12 + " FLOAT, " +
        col22+	" FLOAT);";

	public Databasehelper(Context context) {
		super(context, Dname, null, 1);
		
	}
	public void func(int ini)
	{
		initial_srno=ini;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	/*db.execSQL("CREATE TABLE "+table_name+" ("+col1+" varchar(25), " +
			col2+" varchar(10), " +col3+" float(5,3), " +
					col4 +" varchar(255));");	
	
    db.execSQL("CREATE TABLE "+table2_name+" ("+col12+" float(10,2) NOT NULL DEFAULT '0.0');");
	*/
	    db.execSQL(CREATE_FIRST_TABLE);
	    db.execSQL(CREATE_SECOND_TABLE);
	}
		
		
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	db.execSQL("DROP TABLE IF EXISTS "+table_name);
	//onCreate(db);
	
	db.execSQL("DROP TABLE IF EXISTS "+table2_name);
	onCreate(db);
	}
	public boolean insertData(String category,String status,double amt,
			String des)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(col1, category);
		cv.put(col2, status);
		cv.put(col3, amt);
		cv.put(col4, des);
		cv.put(col5,++i );
		
		tot_sum+=amt;
		System.out.println(tot_sum);
		long result=db.insert(table_name, null, cv);
		//insertData2(tot_sum);
		Cursor c=getData2();
		while(c.moveToNext())
		{
			initial=c.getFloat(0);
		}
		if(result==-1)return false;
		else return true;
	}
	
	public void insertData2(float bgt, float goal)
	{
		/*System.out.println("id up"+tot_sum);

		
		ContentValues cv=new ContentValues();
		cv.put(col12,tot_sum);
		System.out.println("id "+tot_sum);
		long result=db.insert(table2_name, null, cv);
		if(result==-1)return false;
		else return true;*/
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("INSERT INTO "+table2_name+" values ( "+bgt+" , "+ goal+" );");
		
		
	}
	public void update(int pos)
	{String where="srno"+"=?";String args[]={String.valueOf(pos)};
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(col3,0);
		db.update(table_name, cv,where , args);
		
	}
	
	public Cursor getData()
	{SQLiteDatabase db=this.getWritableDatabase();
	Cursor c=db.rawQuery("select * from "+table_name, null);
	return c;
		
	}
	public Cursor getData2()
	{
		SQLiteDatabase db=this.getWritableDatabase();
	    Cursor c=db.rawQuery("select * from "+table2_name, null);
	    return c;
		
	}
	
	public void delete_row()
	{//	String where="srno"+"=?";
	//System.out.println("pos"+pos);
	//String args[]=new String[]{String.valueOf(1)};
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("delete from "+table_name);
		
	}
}