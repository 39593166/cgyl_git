package com.utoo.chunguanyouli.ui.shopping.goodslist;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.nineoldandroids.animation.ObjectAnimator;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgTypeId;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;
import com.utoo.chunguanyouli.views.APPManager;

public class ClassifyGoodsListActivity extends BaseActionBarActivity implements
		OnCheckedChangeListener {
	Toolbar toolbar;
	RadioGroup group;
	LinearLayout fgcontent;
	FragmentGoodsList_Classify price;
	FragmentGoodsList_Classify scanCount;
	FragmentGoodsList_Classify sellCount;
	FragmentGoodsList_Classify dateNew;
	FragmentManager fragmentManager;
	// 查询条件
	public CgTypeId classify;
	private View goodsListStripes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_list_comm);
		classify = (CgTypeId) getIntent().getSerializableExtra("type");
		Log.e("查找商品->所属类型:",
				"id:" + classify.getId() + "，名字：" + classify.getName());
		fragmentManager = getSupportFragmentManager();
		initViews();
		initToolbar(classify.getName());
	}
	
	private void initViews() {
		goodsListStripes = findViewById(R.id.goodsListStripes);
		this.group = (RadioGroup) this.findViewById(R.id.commGoodsRadioGroup);
		this.fgcontent = (LinearLayout) this
				.findViewById(R.id.p03_goodsList_context);
		group.setOnCheckedChangeListener(this);
		onCheckedChanged(group, R.id.commGoodsChooseType1);// 默认点击了第一个
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (checkedId) {
		case R.id.commGoodsChooseType1:
			ObjectAnimator.ofFloat(goodsListStripes, "translationX", 0)
					.setDuration(500).start();
			if (scanCount == null) {
				scanCount =  FragmentGoodsList_Classify.getInstance(1, classify.getId());// classify.getId());
				transaction.add(R.id.p03_goodsList_context, scanCount);

			} else {
				transaction.show(scanCount);
			}
			break;

		case R.id.commGoodsChooseType2:
			ObjectAnimator
					.ofFloat(goodsListStripes, "translationX",
							APPManager.getScreenWidthPx(this) / 4)
					.setDuration(500).start();
			if (price == null) {
				price =FragmentGoodsList_Classify.getInstance(2, classify.getId());
				transaction.add(R.id.p03_goodsList_context, price);

			} else {
				transaction.show(price);
			}
			break;

		case R.id.commGoodsChooseType3:
			ObjectAnimator
					.ofFloat(goodsListStripes, "translationX",
							APPManager.getScreenWidthPx(this) / 4 * 2)
					.setDuration(500).start();
			if (sellCount == null) {
				sellCount =FragmentGoodsList_Classify.getInstance(3, classify.getId());
				transaction.add(R.id.p03_goodsList_context, sellCount);

			} else {
				transaction.show(sellCount);
			}
			break;
		case R.id.commGoodsChooseType4:
			ObjectAnimator
					.ofFloat(goodsListStripes, "translationX",
							APPManager.getScreenWidthPx(this) / 4 * 3)
					.setDuration(500).start();
			if (dateNew == null) {
				dateNew =FragmentGoodsList_Classify.getInstance(4, classify.getId());
				transaction.add(R.id.p03_goodsList_context, dateNew);

			} else {
				transaction.show(dateNew);
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
		if (scanCount != null) {
			transaction.hide(scanCount);
		}
		if (sellCount != null) {
			transaction.hide(sellCount);
		}
		if (price != null) {
			transaction.hide(price);
		}
	}
}
