package com.ccy.photoalbum;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

import com.ccy.photoalbum.utils.APPManager;
import com.squareup.picasso.Picasso;

public class RecentPhotosAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<String> list;
	private Picasso picasso;
	// private List<Boolean> booleans;
	public SparseBooleanArray array;
	private UserContorl uic;

	public RecentPhotosAdapter(Context context, List<String> list,
			UserContorl uic) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		picasso = Picasso.with(context);

		this.list = list;
		this.context = context;
		this.uic = uic;

		array = new SparseBooleanArray();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(getCount() - position - 1);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = inflater.inflate(R.layout.adapter_recent_photos,
					parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.box = (CheckBox) convertView.findViewById(R.id.box);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		String path = list.get(getCount() - position - 1);

		LayoutParams params = holder.icon.getLayoutParams();
		params.width = APPManager.getScreenWidthPx(context) / 3;
		params.height = params.width;

		picasso.load(new File(path)).placeholder(R.drawable.placeholder)
				.resize(params.width, params.height).centerCrop()
				.into(holder.icon);
		holder.box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				array.put(position, isChecked);
				uic.onChecked(position, isChecked);
			}
		});
		holder.box.setChecked(array.get(position));

		return convertView;
	}

	private class ViewHolder {
		private ImageView icon;
		private CheckBox box;
	}

	public interface UserContorl {
		public void onChecked(int position, boolean isChecked);
	}

}
