package com.utoo.chunguanyouli;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.entity.Classify;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.account.LoginActivity;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.main.mainpage.MainActivity;
import com.utoo.chunguanyouli.ui.personal.cg.dianpu.ordermannerger.OrderMannergerActivity;
import com.utoo.chunguanyouli.ui.personal.order.OrderInfoActivity;
import com.utoo.chunguanyouli.ui.shopping.MakeOrderActivity;
import com.utoo.chunguanyouli.ui.shopping.ShoppingActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsCommendActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;
import com.utoo.chunguanyouli.ui.shopping.goodslist.ClassifyGoodsListActivity;
import com.utoo.chunguanyouli.ui.shopping.shoppingcar.ShoppingcarActivity;

public class TempActivity extends NetDataBaseActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewGroup v = (ViewGroup) getLayoutInflater().inflate(
				R.layout.zzzzzzzzzzzz_temp, null);
		setContentView(v);

		// 有右侧点击按钮时用
		// setToolbarView(getTitle(), true, R.drawable.ic_launcher,
		// new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Toast.makeText(TempActivity.this, "testClick", 0)
		// .show();
		// }
		// });
		//

		// get请求实例
		apply(API_F.getLoginRequest("xxx", "xxxx", 1));
	}

	public void goMain(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	public void goShoppingCar(View v) {
		Intent intent = new Intent();
		intent.setClass(this, ShoppingcarActivity.class);
		startActivity(intent);
	}

	public void makeOrder(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MakeOrderActivity.class);
		startActivity(intent);
	}

	public void goLogin(View v) {
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
	}

	public void goShopping(View v) {
		Intent intent = new Intent();
		intent.setClass(this, ShoppingActivity.class);
		startActivity(intent);
	}

	public void goPersonal(View v) {
		Intent intent = new Intent();
//		intent.setClass(this, PersonalActivity.class);
		startActivity(intent);
	}

	public void goGoodsList(View v) {
		Classify classify = new Classify();
		classify.setClassifyId("x");
		classify.setClassifyName("cccc");
		Intent intent = new Intent();
		intent.putExtra("type", classify);
		intent.setClass(this, ClassifyGoodsListActivity.class);
		startActivity(intent);
	}

	public void goGoodsInfo(View v) {
		Intent intent = new Intent();
		intent.setClass(this, GoodsInfoActivity.class);
		startActivity(intent);
	}

	public void goOrderInfo(View v) {
		Intent intent = new Intent();
		intent.putExtra("orderId", 24);
		intent.setClass(this, OrderInfoActivity.class);
		startActivity(intent);
	}

	public void getShopOrders(View v) {
		Intent intent = new Intent();
		intent.setClass(this, OrderMannergerActivity.class);
		startActivity(intent);
	}

	public void goCommends(View v) {
		Intent intent = new Intent();
		intent.putExtra("goodsId", 1);
		intent.setClass(this, GoodsCommendActivity.class);
		startActivity(intent);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub

	}
}
