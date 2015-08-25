package com.utoo.chunguanyouli.ui.personal.adapter;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.MyMessageInfo;

public class MyMessageAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<MyMessageInfo> list;
	private UserContorl uic;

	public List<Integer> integers = new ArrayList<Integer>();
	public List<Boolean> booleans = new ArrayList<Boolean>();

	public MyMessageAdapter(Context context, List<MyMessageInfo> list, UserContorl uic) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		this.list = list;
		this.uic = uic;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.adapter_mymessage, parent, false);

			holder.box = (CheckBox) convertView.findViewById(R.id.box);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.state = (TextView) convertView.findViewById(R.id.state);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		MyMessageInfo info = list.get(position);

		holder.box.setVisibility(integers.get(position));
		holder.title.setText(info.getTitle());
		holder.time.setText(info.getTime());
		holder.state.setText(info.getState());

		holder.box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				booleans.set(position, isChecked);
				uic.onChecked(isChecked);
			}
		});
		holder.box.setChecked(booleans.get(position));

		return convertView;
	}

	private class ViewHolder {
		private CheckBox box;
		private TextView title;
		private TextView time;
		private TextView state;
	}

	public void init() {
		for (int i = 0; i < getCount(); i++) {
			integers.add(View.GONE);
			booleans.add(false);
		}
	}

	public interface UserContorl {
		public void onChecked(boolean isChecked);
	}

}
