package com.utoo.chunguanyouli.ui.villageofficial.adapter;

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
import com.utoo.chunguanyouli.info.VillageOfficialInfo.Val;

public class VillageOfficialAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Val> list;
	private Picasso picasso;

	public VillageOfficialAdapter(Context context, List<Val> list) {
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
			convertView = inflater.inflate(R.layout.adapter_villageofficial,
					parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.level = (TextView) convertView.findViewById(R.id.level);
			holder.address = (TextView) convertView.findViewById(R.id.address);
			holder.tedian = (TextView) convertView.findViewById(R.id.tedian);
			holder.num = (TextView) convertView.findViewById(R.id.num);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		Val info = list.get(position);

		if (!TextUtils.isEmpty(info.getPic())) {
			picasso.load(info.getPic())
					.resizeDimen(R.dimen.image_size_list,
							R.dimen.image_size_list).into(holder.icon);
		}
		holder.name.setText(info.getName());
		holder.level.setText(String.format("LV.%s", info.getLv()));
		holder.address.setText(info.getArea());
		holder.tedian.setText(info.getTj());
		holder.num.setText(String.format("存户 %s / 出售 %s / 代购 %s",
				info.getNum1(), info.getNum2(), info.getNum3()));

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView name;
		private TextView level;
		private TextView address;
		private TextView tedian;
		private TextView num;
	}

}
