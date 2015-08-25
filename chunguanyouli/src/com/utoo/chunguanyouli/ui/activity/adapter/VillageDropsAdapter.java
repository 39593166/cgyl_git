package com.utoo.chunguanyouli.ui.activity.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.server.ClientConfigs;

public class VillageDropsAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<CgNewsId> list;
	private Picasso picasso;

	public VillageDropsAdapter(Context context, List<CgNewsId> list) {
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
			convertView = inflater.inflate(R.layout.adapter_villagedrops,
					parent, false);
			holder = new ViewHolder();

			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		CgNewsId info = list.get(position);

		holder.title.setText(info.getTitle());
		holder.content.setText(info.getContent());
		picasso.load(info.getPic())
				.resizeDimen(R.dimen.image_size_big, R.dimen.image_size_list)
				.placeholder(R.drawable.index_news_list_small).into(holder.icon);

		return convertView;
	}

	private class ViewHolder {
		private TextView title;
		private TextView content;
		private ImageView icon;
	}

}
