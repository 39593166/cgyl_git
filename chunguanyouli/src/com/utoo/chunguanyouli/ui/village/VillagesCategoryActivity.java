package com.utoo.chunguanyouli.ui.village;

/**
 * 村镇类目
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.VillagesCategoryInfo;
import com.utoo.chunguanyouli.info.VillagesCategoryInfo.Val;
import com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.activity.adapter.VillagesCategoryAdapter;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.views.APPManager;
import com.utoo.chunguanyouli.views.MyToast;
import com.views.NoScrollGridView;

@ContentView(R.layout.activity_villagescategory)
public class VillagesCategoryActivity extends NetDataBaseActionBarActivity {

	private Intent intent = new Intent();
	private Gson gson = new Gson();
	private List<Val> groupList;
	private VillagesCategoryAdapter adapter;
	private List<com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val> list = new ArrayList<WalkIntoNanChuanInfo.Val>();

	// NoScrollGridView
	@ViewInject(R.id.gridView)
	private NoScrollGridView gridView;

	// Toolbar
	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	// RadioGroup
	@ViewInject(R.id.radioGroup)
	private RadioGroup group;

	// Item点击事件
	@OnItemClick(R.id.gridView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val info = (com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val) adapter
				.getItem(position);
		String city = "";
		for (int i = 0; i < group.getChildCount(); i++) {
			RadioButton button = ((RadioButton) group.getChildAt(i));
			if (button.isChecked()) {
				city = button.getText().toString().trim();
			}
		}
		intent.setClass(this, VillagesIntroduceActivity.class);
		intent.putExtra("id_a", Integer.parseInt(info.getId()));
		intent.putExtra("city", city);
		intent.putExtra("name", info.getName());
		startActivity(intent);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 初始化
		ViewUtils.inject(this);

		toolbar.setTitle("村镇类目");
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

		adapter = new VillagesCategoryAdapter(this, list);
		gridView.setAdapter(adapter);

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				apply(API_C.getWalkIntoNanChuan(1,
						Integer.parseInt(groupList.get(checkedId).getId())));
			}
		});

		// 请求网络
		apply(API_C.getVillagesCategory(0));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		if (TAG == 0) {
			VillagesCategoryInfo info = gson.fromJson(response,
					VillagesCategoryInfo.class);
			if (info.getState().equals("1")) {
				groupList = info.getVal();

				Bitmap bitmap = null;
				Drawable d = new BitmapDrawable(getResources(), bitmap);
				RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
						RadioGroup.LayoutParams.MATCH_PARENT,
						RadioGroup.LayoutParams.WRAP_CONTENT);

				for (int i = 0; i < groupList.size(); i++) {
					Val val = groupList.get(i);
					RadioButton button = new RadioButton(this);
					button.setText(val.getName());
					button.setMaxEms(7);
					button.setPadding(40, 40, 40, 40);
					button.setButtonDrawable(d);
					button.setLayoutParams(layoutParams);
					button.setId(i);
					button.setChecked(false);button.setGravity(Gravity.CENTER);
					button.setBackgroundResource(R.drawable.cunzhen);
					button.setTextSize(16);
					group.addView(button);
				}
				((RadioButton) group.getChildAt(0)).setChecked(true);
			}
		} else if (TAG == 1) {
			WalkIntoNanChuanInfo info = gson.fromJson(response,
					WalkIntoNanChuanInfo.class);
			if (info.getState().equals("1")) {
				List<com.utoo.chunguanyouli.info.WalkIntoNanChuanInfo.Val> list = info
						.getVal();

				this.list.clear();
				this.list.addAll(list);
				adapter.notifyDataSetChanged();
			}
		}

		LogUtils.e("村镇类目：" + response);
	}
}
