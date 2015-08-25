package com.utoo.chunguanyouli.ui.village;

/**
 * 产品分类
 */
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.ProductCategoriesInfo;
import com.utoo.chunguanyouli.info.ProductCategoriesInfo.Val;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.activity.adapter.ProductCategoriesAdapter;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

@ContentView(R.layout.activity_productcategories)
public class ProductCategoriesActivity extends NetDataBaseActionBarActivity {

	private Gson gson = new Gson();
	private List<Val> list = new ArrayList<ProductCategoriesInfo.Val>();
	private ProductCategoriesAdapter adapter;

	
	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@ViewInject(R.id.group)
	private RadioGroup group;

	@ViewInject(R.id.gridView)
	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		toolbar.setTitle("产品分类");
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		adapter = new ProductCategoriesAdapter(this, list);
		gridView.setAdapter(adapter);

		// 请求网络
		apply(API_C.getProductCategories(0));

	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		ProductCategoriesInfo info = gson.fromJson(response, ProductCategoriesInfo.class);
		if (TAG == 0) {
			if (info.getState().equals("1")) {
				List<Val> groupList = info.getVal();

				Bitmap bitmap = null;
				Drawable d = new BitmapDrawable(getResources(), bitmap);
				RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
						RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);

				for (int i = 0; i < groupList.size(); i++) {
					Val val = groupList.get(i);
					RadioButton button = new RadioButton(this);
					button.setText(val.getName());
					button.setMaxEms(5);
					button.setPadding(40, 40, 40, 40);
					button.setButtonDrawable(d);
					button.setLayoutParams(layoutParams);
					button.setId(Integer.parseInt(val.getId()));
					button.setChecked(false);
					button.setBackgroundResource(R.drawable.cunzhen);
					group.addView(button);
				}
				((RadioButton) group.getChildAt(0)).setChecked(true);

				group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						apply(API_C.getProductCategories(1, checkedId));
					}
				});
			}
		} else if (TAG == 1) {
			if (info.getState().equals("1")) {
				List<Val> list = info.getVal();

				this.list.clear();
				this.list.addAll(list);
				adapter.notifyDataSetChanged();
			}
		}

	}
}
