package com.codeitsuisse.team71.expensetracker;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codeitsuisse.team71.expensetracker.Expenses.ListViewItem;


public class MyAdapter extends BaseAdapter {

	LayoutInflater inflator;
	List<ListViewItem> items;
	
	public MyAdapter (Activity context, List<ListViewItem> items)
	{
		super();
		this.items= items;
		this.inflator= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	
		ListViewItem item= items.get(position);
		View vi= convertView;
		if(convertView==null)
		
			vi= inflator.inflate(R.layout.custom, null);
		TextView c= (TextView) vi.findViewById(R.id.textView1);
		TextView s= (TextView) vi.findViewById(R.id.textView2);
		TextView a= (TextView) vi.findViewById(R.id.textView3);
		TextView d= (TextView) vi.findViewById(R.id.textView4);
		
		c.setText(item.category);
		s.setText(item.status);
		a.setText(item.amount+"");
		d.setText("description:"+item.desc);
		return vi;
	}
	
	

}
