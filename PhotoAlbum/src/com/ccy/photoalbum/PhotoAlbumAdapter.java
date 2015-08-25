package com.ccy.photoalbum;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccy.photoalbum.utils.APPManager;
import com.squareup.picasso.Picasso;

public class PhotoAlbumAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<PhotoAlbumInfo> list;
	private Picasso picasso;

	public PhotoAlbumAdapter(Context context, List<PhotoAlbumInfo> list) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;
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
			convertView = inflater.inflate(R.layout.adapter_photo_album,
					parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.nama = (TextView) convertView.findViewById(R.id.name);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		PhotoAlbumInfo info = list.get(position);

		LayoutParams params = holder.icon.getLayoutParams();
		params.width = APPManager.getScreenWidthPx(context) / 3;
		params.height = params.width;

		picasso.load(new File(info.getPath()))
				.placeholder(R.drawable.placeholder)
				.resize(params.width, params.height).centerCrop()
				.into(holder.icon);
		holder.nama.setText(String.format("%s（%d）", info.getName(),
				info.getCount()));

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private TextView nama;
	}

}
