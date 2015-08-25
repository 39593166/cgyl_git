package com.utoo.chunguanyouli.ui.villageofficial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;

public class VillageOfficialGridViewAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private int[] imageRes;
	private String[] counts;

	public VillageOfficialGridViewAdapter(Context context, int[] imageRes, String[] counts) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		this.imageRes = imageRes;
		this.counts = counts;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageRes.length;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = inflater.inflate(R.layout.adapter_villageofficialgridview, parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.villicon);
			holder.count = (TextView) convertView.findViewById(R.id.villcount);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		holder.icon.setImageResource(imageRes[position]);
		if (counts != null) {
			if (position != 0) {
				holder.count.setText(counts[position - 1]);
			}
		}

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView count;
	}

}
