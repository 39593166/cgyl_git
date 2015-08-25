package com.utoo.chunguanyouli.ui.comm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;

public class CityTitleAdapter extends BaseAdapter {
	Context context;

	class ViewHolder {
		TextView title;
	}

	List<String> titles;

	public CityTitleAdapter(Context context) {
		this.context = context;
		titles = new ArrayList<String>();
	}

	public String getStr() {
		StringBuilder sb = new StringBuilder();
		if (titles.size() == 0) {
			return "";
		} else if (titles.size() > 0) {
			sb.append(titles.get(0));
		}
		if (titles.size() > 1) {
			for (int i = 1; i < titles.size(); i++) {
				sb.append(":");
				sb.append(titles.get(i));
			}
		}
		return sb.toString();
	}

	public void remove(int position) {
		titles.remove(position);
		this.notifyDataSetChanged();
	}

	public void add(String titles) {
		Log.e("titlesStr", titles);
		if (titles == null) {
			return;
		}
		String[] dataMore = titles.split(":");
		for (String str : dataMore) {
			Log.e("titlesItem", str);
			if (str != null && !str.equals(""))
				this.titles.add(str);
		}
		this.notifyDataSetChanged();
	}

	public void reset(String titles) {
		this.titles = new ArrayList<String>();
		if (titles == null) {
			return;
		}
		add(titles);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.e("getView", "调用getview");
		ViewHolder holder = null;
		if (convertView == null || convertView.getTag() == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_citytitle, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView
					.findViewById(R.id.item_city_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(titles.get(position));
		return convertView;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public Object getItem(int position) {
		return titles.get(position);
	}

	@Override
	public int getCount() {
		return titles.size();
	}

}
