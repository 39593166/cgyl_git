package com.utoo.chunguanyouli.ui.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val;

public class WalkIntoNanChuanAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Val> list;

	public WalkIntoNanChuanAdapter(Context context, List<Val> list) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
			convertView = inflater.inflate(R.layout.adapter_walkintonanchuan, parent, false);
			holder = new ViewHolder();

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		return convertView;
	}

	private class ViewHolder {
	}

}
