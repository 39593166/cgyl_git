package com.utoo.chunguanyouli.ui.personal.order;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;

public class OrderListActivity extends BaseActionBarActivity implements
		OnCheckedChangeListener {
	private RadioGroup orderActivityRadioGroup;
	int choosedShowingType = CgOrderId.TYPE_ALL;
	LinearLayout orderListLayout;

	OrderFragment all;
	OrderFragment nopay;
	OrderFragment payed;
	OrderFragment appending;
	OrderFragment finish;
	FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orders_list);
		initToolbar("我的订单");
		fragmentManager = getSupportFragmentManager();
		findViews();
		choosedShowingType = getIntent()
				.getIntExtra("type", CgOrderId.TYPE_ALL);
		initCheck(choosedShowingType);
	}

	private void initCheck(int choosedShowingType2) {
		RadioButton order_activity_radionButton_1, order_activity_radionButton_2, order_activity_radionButton_3, order_activity_radionButton_4, order_activity_radionButton_5;
		order_activity_radionButton_1 = (RadioButton) findViewById(R.id.order_activity_radionButton_1);
		order_activity_radionButton_2 = (RadioButton) findViewById(R.id.order_activity_radionButton_2);
		order_activity_radionButton_3 = (RadioButton) findViewById(R.id.order_activity_radionButton_3);
		order_activity_radionButton_4 = (RadioButton) findViewById(R.id.order_activity_radionButton_4);
		order_activity_radionButton_5 = (RadioButton) findViewById(R.id.order_activity_radionButton_5);
		if (choosedShowingType2 == CgOrderId.TYPE_ALL) {
			order_activity_radionButton_1.setChecked(true);
		} else if (choosedShowingType2 == CgOrderId.TYPE_NO_PAY) {
			order_activity_radionButton_2.setChecked(true);
		} else if (choosedShowingType2 == CgOrderId.TYPE_WAIT_APPEND) {
			order_activity_radionButton_3.setChecked(true);
		} else if (choosedShowingType2 == CgOrderId.TYPE_WAIT_COLLECT) {
			order_activity_radionButton_4.setChecked(true);
		} else if (choosedShowingType2 == CgOrderId.TYPE_WAIT_COMMEND) {
			order_activity_radionButton_5.setChecked(true);
		}
	}

	private void findViews() {
		orderListLayout = (LinearLayout) this
				.findViewById(R.id.orderListLayout);
		orderActivityRadioGroup = (RadioGroup) findViewById(R.id.order_activity_radioGroup);
		orderActivityRadioGroup.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (checkedId) {
		case R.id.order_activity_radionButton_1:
			if (all == null) {
				all =  OrderFragment.getInstance(-1);
				transaction.add(R.id.orderListLayout, all);

			} else {
				transaction.show(all);
			}
			break;
		case R.id.order_activity_radionButton_2:
			if (nopay == null) {
				nopay = OrderFragment.getInstance(CgOrderId.STATU_USER_SUBMIT);
				transaction.add(R.id.orderListLayout, nopay);

			} else {
				transaction.show(nopay);
			}
			break;
		// OrderFragment all;
		// OrderFragment nopay;
		// OrderFragment payed;
		// OrderFragment appending;
		// OrderFragment finish;
		case R.id.order_activity_radionButton_3:
			if (payed == null) {
				payed = OrderFragment.getInstance(CgOrderId.STATU_PAYED);
				transaction.add(R.id.orderListLayout, payed);

			} else {
				transaction.show(payed);
			}
			break;
		case R.id.order_activity_radionButton_4:
			if (appending == null) {
				appending = OrderFragment.getInstance(CgOrderId.STATU_APPENDDING);
				transaction.add(R.id.orderListLayout, appending);

			} else {
				transaction.show(appending);
			}
			break;
		case R.id.order_activity_radionButton_5:
			if (finish == null) {
				finish = OrderFragment.getInstance(CgOrderId.STATU_OVER);
				transaction.add(R.id.orderListLayout, finish);

			} else {
				transaction.show(finish);
			}
			break;
		}
		transaction.commit();
	}

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
		if (finish != null) {
			transaction.hide(finish);
		}
	}
}
