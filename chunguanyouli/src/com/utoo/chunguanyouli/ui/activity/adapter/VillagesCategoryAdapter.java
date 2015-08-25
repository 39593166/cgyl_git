package com.utoo.chunguanyouli.ui.activity.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.server.ClientConfigs;

public class VillagesCategoryAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val> list;
	private Picasso picasso;

	public VillagesCategoryAdapter(Context context,
			List<com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val> list) {
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
			convertView = inflater.inflate(R.layout.adapter_villagescategory,
					parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.name = (TextView) convertView.findViewById(R.id.name);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val info = list
				.get(position);

		if (!TextUtils.isEmpty(info.getPic())) {
			picasso.load(ClientConfigs.PICHOST + info.getPic())
					.resizeDimen(R.dimen.image_size_big,
							R.dimen.image_size_list).into(holder.icon);
		}
		holder.name.setText(info.getName());

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView name;
	}
}
