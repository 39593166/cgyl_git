package com.utoo.chunguanyouli.ui.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.VillagersPeopleInfo.Val;
import com.views.BadgeView;

public class VillagersPeopleAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private List<Val> list;

	public VillagersPeopleAdapter(Context context, List<Val> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
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
			convertView = inflater.inflate(R.layout.adapter_villagerspeople, parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.name = (TextView) convertView.findViewById(R.id.name);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		Val info = list.get(position);
		
		holder.name.setText(info.getRealname());
		if (info.isIslock()) {
			BadgeView badgeView = new BadgeView(context, holder.icon);
			badgeView.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
			badgeView.setText("官");
			badgeView.show();
		}

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView name;
	}

}
