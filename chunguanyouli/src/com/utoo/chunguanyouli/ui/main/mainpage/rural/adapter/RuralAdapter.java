package com.utoo.chunguanyouli.ui.main.mainpage.rural.adapter;

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
import com.utoo.chunguanyouli.info.RuralInfo.Val;
import com.utoo.chunguanyouli.server.ClientConfigs;

public class RuralAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Val> list;
	private Picasso picasso;

	public RuralAdapter(Context context, List<Val> list) {
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
			convertView = inflater.inflate(R.layout.adapter_rural, parent,
					false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.jianjie = (TextView) convertView.findViewById(R.id.jianjie);
			holder.cyx = (TextView) convertView.findViewById(R.id.cyx);
			holder.num = (TextView) convertView.findViewById(R.id.num);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		Val info = list.get(position);

		picasso.load(ClientConfigs.PICHOST + info.getPic())
				.resizeDimen(R.dimen.image_size_list, R.dimen.image_size_list)
				.into(holder.icon);
		holder.name.setText(info.getName());
		holder.jianjie.setText(info.getContent());
		holder.cyx.setText(info.getEffect().replace(":", " "));
		holder.num
				.setText(String.format("村户 %s / 村民 %s / 村官 %s / 农产 %s",
						info.getNum1(), info.getNum2(), info.getNum3(),
						info.getNum4()));

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView name;
		private TextView jianjie;
		private TextView cyx;
		private TextView num;
	}

}
