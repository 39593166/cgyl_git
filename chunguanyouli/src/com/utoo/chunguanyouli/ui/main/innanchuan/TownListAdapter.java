package com.utoo.chunguanyouli.ui.main.innanchuan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.entity.Town;

public class TownListAdapter extends BaseAdapter {
	List<Town> towns;
	Activity ctx;
	public List<Boolean> selecter;

	public TownListAdapter(Activity ctx, List<Town> towns) {
		this.towns = towns;
		this.ctx = ctx;
		this.selecter = new ArrayList<Boolean>();
		int sum = towns.size() - 1;
		selecter.add(true);
		for (int i = 0; i < sum; i++) {
			selecter.add(false);
		}
	}

	@Override
	public int getCount() {
		return towns.size();
	}

	@Override
	public Object getItem(int arg0) {
		return towns.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		Town t = towns.get(arg0);
		arg1 = LayoutInflater.from(ctx).inflate(R.layout.item_town_classify,
				null);

		TextView name = (TextView) arg1
				.findViewById(R.id.item_town_classify_name);
		if (selecter.get(arg0)) {
			name.setTextColor(Color.LTGRAY);
			arg1.setBackgroundColor(Color.WHITE);
		} else {
			name.setTextColor(Color.BLACK);
			arg1.setBackgroundColor(Color.GRAY);
		}
		name.setText(t.getName());
		return arg1;
	}

}
