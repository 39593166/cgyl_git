package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;

/**
 * 商品管理
 * 
 * @author fsm
 * 
 */
public class GoodsMannergerActivity extends BaseActionBarActivity implements
		OnClickListener, OnCheckedChangeListener {
	FragmentManager fragmentManager;
	GoodsMannerSallingListFragment saling;
	// GoodsMannerShenListFragment shen;
	GoodsMannerStoreListFragment storing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_mannerger);
		findViews();
		initToolbar("宝贝管理");
		fragmentManager = getSupportFragmentManager();
		onCheckedChanged(goodsmannerRadioGroup, R.id.goodsmanner_radionButton_1);// 默认点击了第一个
	}

	private RadioGroup goodsmannerRadioGroup;
	private Button goodsmannerAddgoods;

	private void findViews() {
		goodsmannerRadioGroup = (RadioGroup) findViewById(R.id.goodsmanner_radioGroup);
		goodsmannerAddgoods = (Button) findViewById(R.id.goodsmannerAddgoods);

		goodsmannerAddgoods.setOnClickListener(this);
		goodsmannerRadioGroup.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == goodsmannerAddgoods) {
			Intent intent = new Intent();
			intent.setClass(this, AddGoodsActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (checkedId) {
		case R.id.goodsmanner_radionButton_1:
			if (saling == null) {
				saling = new GoodsMannerSallingListFragment();
				transaction.add(R.id.goodsmannerLayout, saling);

			} else {
				transaction.show(saling);
			}
			break;

		// case R.id.goodsmanner_radionButton_2:
		// if (shen == null) {
		// shen = new GoodsMannerShenListFragment();
		// transaction.add(R.id.goodsmannerLayout, shen);
		//
		// } else {
		// transaction.show(shen);
		// }
		// break;

		case R.id.goodsmanner_radionButton_3:
			if (storing == null) {
				storing = new GoodsMannerStoreListFragment();
				transaction.add(R.id.goodsmannerLayout, storing);

			} else {
				transaction.show(storing);
			}
			break;
		default:
			break;
		}
		transaction.commit();
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (saling != null) {
			transaction.hide(saling);
		}
		// if (shen != null) {
		// transaction.hide(shen);
		// }
		if (storing != null) {
			transaction.hide(storing);
		}
	}
}
