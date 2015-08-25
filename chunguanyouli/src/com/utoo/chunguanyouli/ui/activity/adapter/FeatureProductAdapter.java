package com.utoo.chunguanyouli.ui.activity.adapter;

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
import com.utoo.chunguanyouli.info.FeatureProductInfo2.Val;
import com.utoo.chunguanyouli.server.ClientConfigs;

public class FeatureProductAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Val> list;
	Picasso picasso;

	public FeatureProductAdapter(Context context, List<Val> list) {
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
			convertView = inflater.inflate(R.layout.adapter_featureproduct,
					parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.people = (TextView) convertView.findViewById(R.id.people);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			holder.name = (TextView) convertView.findViewById(R.id.name);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		Val info = list.get(position);

		picasso.load(ClientConfigs.PICHOST + info.getPic())
				.resizeDimen(R.dimen.image_size_list, R.dimen.image_size_list)
				.placeholder(R.drawable.index_news_grid).into(holder.icon);
		holder.title.setText(info.getName());
		holder.people.setText(String.format("有%s人买过", info.getNumhave()));
		holder.price.setText(String.format("￥%s元", info.getPrice()));
		// holder.name.setText(info.getRealname());

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView title;
		private TextView people;
		private TextView name;
		private TextView price;
	}

}
