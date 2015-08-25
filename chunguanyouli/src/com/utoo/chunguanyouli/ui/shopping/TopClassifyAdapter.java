package com.utoo.chunguanyouli.ui.shopping;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgTypeId;
import com.utoo.chunguanyouli.entity.Classify;

public class TopClassifyAdapter extends BaseAdapter {
	public List<Boolean> selecter = null;
	Context ctx;
	List<CgTypeId> clsifies;
	public int oldSelecter = 0;

	public TopClassifyAdapter(Context ctx, List<CgTypeId> clsifies) {
		this.ctx = ctx;
		this.clsifies = clsifies;
		selecter = new ArrayList<Boolean>();
		selecter.add(true);// 第一个默认选中
		int k = clsifies.size() - 1;
		for (int i = 0; i < k; i++) {
			Boolean b = new Boolean(false);
			selecter.add(b);
		}
	}

	@Override
	public int getCount() {
		return clsifies.size();
	}

	@Override
	public Object getItem(int position) {
		return clsifies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		TextView name;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CgTypeId clsify = clsifies.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.item_classify_p, null);
			holder.name = (TextView) convertView
					.findViewById(R.id.item_classify_p_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (selecter.get(position)) {
			convertView.setBackgroundColor(Color.WHITE);
			holder.name.setTextColor(ctx.getResources().getColor(
					R.color.gainsboro));
		} else {
			convertView.setBackgroundResource(R.color.gainsboro);
			holder.name.setTextColor(Color.BLACK);
		}
		holder.name.setText(clsify.getName());
		return convertView;
	}
}
