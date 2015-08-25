package com.utoo.chunguanyouli.ui.village;

/**
 * 走进南川
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo;
import com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.activity.adapter.WalkIntoNanChuanAdapter;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

@ContentView(R.layout.activity_walkintonanchuan)
public class WalkIntoNanChuanActivity extends NetDataBaseActionBarActivity {

	private Intent intent = new Intent();
	private Gson gson = new Gson();
	private List<Val> list = new ArrayList<Val>();
	private WalkIntoNanChuanAdapter adapter;

	// ListView
	@ViewInject(R.id.listView)
	private ListView listView;

	// Toolbar
	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@OnClick({ R.id.category })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.category:// 村镇类目
			intent.setClass(this, VillagesCategoryActivity.class);
			startActivity(intent);
			break;
		case R.id.dengji:// 登记我的家乡

			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 初始化
		ViewUtils.inject(this);

		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		toolbar.setTitleTextColor(getResources()
				.getColor(android.R.color.white));

		adapter = new WalkIntoNanChuanAdapter(this, list);
		listView.setAdapter(adapter);

		// 请求网路
		apply(API_C.getWalkIntoNanChuan(0, 2592));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		WalkIntoNanChuanInfo info = gson.fromJson(response,
				WalkIntoNanChuanInfo.class);
		if (info.getState().equals("1")) {
			List<Val> list = info.getVal();

			this.list.clear();
			this.list.addAll(list);
			adapter.notifyDataSetChanged();
		}
	}

}
