package com.utoo.chunguanyouli.ui.activity.adapter;

import com.utoo.chunguanyouli.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StoreListViewAdapter extends BaseAdapter {

	private LayoutInflater inflater;

	public StoreListViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = inflater.inflate(R.layout.adapter_storelistview, parent, false);
			holder = new ViewHolder();
			
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		
		return convertView;
	}

	private class ViewHolder {

	}

}
