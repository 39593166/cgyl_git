package com.utoo.chunguanyouli.ui.main.mainpage.required.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.DailyRequiredInfo.Val;
import com.utoo.chunguanyouli.server.ClientConfigs;

public class DailyRequiredAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Val> list;
	private Picasso picasso;

	public DailyRequiredAdapter(Context context, List<Val> list) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		this.list = list;
		picasso = Picasso.with(context);
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
			convertView = inflater.inflate(R.layout.adapter_dailyrequired,
					parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.time = (TextView) convertView.findViewById(R.id.time);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		Val info = list.get(position);

		picasso.load(ClientConfigs.PICHOST + info.getPic()).resize(150, 500)
				.into(holder.icon);
		holder.title.setText(info.getTitle());
		holder.time.setText(info.getDatesend());

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView title;
		private TextView time;
	}

}
