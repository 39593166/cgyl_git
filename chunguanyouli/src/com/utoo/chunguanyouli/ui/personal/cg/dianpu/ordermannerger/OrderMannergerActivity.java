package com.utoo.chunguanyouli.ui.personal.cg.dianpu.ordermannerger;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgOrderGoodsId;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;

/**
 * 订单管理
 * 
 * @author fsm
 */
public class OrderMannergerActivity extends BaseActionBarActivity implements
		OnCheckedChangeListener {
	OrderMannerFragment all;
	OrderMannerFragment nopay;
	OrderMannerFragment payed;
	OrderMannerFragment appending;
	OrderMannerFragment finished;
	OrderMannerFragment returnning;
	private RadioGroup group;
	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_mannerger);
		fragmentManager = getSupportFragmentManager();
		group = (RadioGroup) this
				.findViewById(R.id.orderManner_activity_radioGroup);
		initToolbar("订单管理");
		group.setOnCheckedChangeListener(this);
		onCheckedChanged(group, R.id.orderManner_activity_radionButton_1);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);

		switch (checkedId) {
		case R.id.orderManner_activity_radionButton_1:
			if (all == null) {
				all = OrderMannerFragment.getInstance(-1);
				transaction.add(R.id.orderManner_activity_linearLayout, all);

			} else {
				transaction.show(all);
			}
			break;
		case R.id.orderManner_activity_radionButton_2:
			if (nopay == null) {
				nopay =  OrderMannerFragment.getInstance(CgOrderId.STATU_USER_SUBMIT);
				transaction.add(R.id.orderManner_activity_linearLayout, nopay);
			} else {
				transaction.show(nopay);
			}
			break;

		case R.id.orderManner_activity_radionButton_3:
			if (payed == null) {
				payed = OrderMannerFragment.getInstance(CgOrderId.STATU_PAYED);
				transaction.add(R.id.orderManner_activity_linearLayout, payed);
			} else {
				transaction.show(payed);
			}
			break;
		case R.id.orderManner_activity_radionButton_4:
			if (appending == null) {
				appending = OrderMannerFragment.getInstance(CgOrderId.STATU_APPENDDING);
				transaction.add(R.id.orderManner_activity_linearLayout,
						appending);
			} else {
				transaction.show(appending);
			}
			break;
		case R.id.orderManner_activity_radionButton_5:
			if (finished == null) {
				finished = OrderMannerFragment.getInstance(CgOrderId.STATU_OVER);
				transaction.add(R.id.orderManner_activity_linearLayout,
						finished);
			} else {
				transaction.show(appending);
			}
			break;
		case R.id.orderManner_activity_radionButton_6:
			if (returnning == null) {
				returnning = OrderMannerFragment.getInstance(-2);
				transaction.add(R.id.orderManner_activity_linearLayout,
						returnning);
			} else {
				transaction.show(returnning);
			}
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
	// OrderMannerFragment all;
	// OrderMannerFragment nopay;
	// OrderMannerFragment payed;
	// OrderMannerFragment appending;
	// OrderMannerFragment finished;
	// OrderMannerFragment returnning;
	private void hideFragments(FragmentTransaction transaction) {
		if (all != null) {
			transaction.hide(all);
		}
		if (nopay != null) {
			transaction.hide(nopay);
		}
		if (payed != null) {
			transaction.hide(payed);
		}
		if (appending != null) {
			transaction.hide(appending);
		}
		if (finished != null) {
			transaction.hide(finished);
		}
		if (returnning != null) {
			transaction.hide(returnning);
		}
	}

}
