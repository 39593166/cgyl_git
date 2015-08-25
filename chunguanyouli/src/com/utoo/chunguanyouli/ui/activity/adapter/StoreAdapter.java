package com.utoo.chunguanyouli.ui.activity.adapter;

import com.utoo.chunguanyouli.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class StoreAdapter extends BaseAdapter {

	private LayoutInflater inflaterl;
	private int[] imageRes = { R.drawable.paihangbang, R.drawable.chanpinfenlei,
			R.drawable.dianpujieshao, R.drawable.lianxiguanjia };

	public StoreAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflaterl = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageRes.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageRes[position];
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
			convertView = inflaterl.inflate(R.layout.adapter_store, parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		holder.icon.setImageResource(imageRes[position]);

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
	}

}
