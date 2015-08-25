package com.utoo.chunguanyouli.ui.shopping.goodsinfo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

public class GoodsCommendActivity extends NetDataBaseActionBarActivity {
	RadioGroup group;
	private List<Fragment> fragments = new ArrayList<Fragment>();
	GoodsCommendFragment all;
	GoodsCommendFragment good;
	GoodsCommendFragment mid;
	GoodsCommendFragment bad;
	int goodsId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_commend);
		initToolbar("评论列表");
		goodsId = getIntent().getIntExtra("goodsId", -1);
		group = (RadioGroup) findViewById(R.id.commendRadioGroup);
		all = GoodsCommendFragment.getInstance(1, goodsId);
		good = GoodsCommendFragment.getInstance(2, goodsId);
		mid = GoodsCommendFragment.getInstance(3, goodsId);
		bad = GoodsCommendFragment.getInstance(4, goodsId);
		fragments.add(all);
		fragments.add(good);
		fragments.add(mid);
		fragments.add(bad);
		
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		for (int i = 0; i < fragments.size(); i++) {
			transaction.add(R.id.commendLayout, fragments.get(i));
		}

		transaction.show(all).hide(good).hide(mid).hide(bad);
		transaction.commitAllowingStateLoss();
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.commendChooseType1:
					switchFragment(0);
					break;

				case R.id.commendChooseType2:
					switchFragment(1);
					break;

				case R.id.commendChooseType3:
					switchFragment(2);
					break;

				case R.id.commendChooseType4:
					switchFragment(3);
					break;

				default:
					break;
				}
			}
		});
	}

	/**
	 * 切换fragment
	 * 
	 * @param index
	 *            位置
	 */
	private void switchFragment(int index) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		// transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		for (int i = 0; i < fragments.size(); i++) {
			if (index == i) {
				transaction.show(fragments.get(index));
			} else {
				transaction.hide(fragments.get(i));
			}
		}
		transaction.commit();
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}
	@Override
	protected void onReturned(String response, int TAG) {
		JSONObject responseJson = null;
		try {
			responseJson = new JSONObject(response);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
