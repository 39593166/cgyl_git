package com.utoo.chunguanyouli.ui.personal.cg.dianpu;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

/**
 * 我的店铺
 * 
 * @author fsm
 * 
 */
public class MyShopActivity1 extends NetDataBaseActionBarActivity implements
		OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_shop);
		findViews();
	}

	private void setMyShopInfo(MyShopInfo msi) {
		
	}

	private ImageView myShopImage;
	private TextView myShopName;
	private TextView myShopIsRegist;
	private TextView myShopTdIn;
	private TextView myShopTdAsk;
	private TextView myShopTdSold;
	private TextView myShopTdOrder;
	private Button myShopOrderMannerger;
	private Button myShopOrderMyGoods;
	private Button myShopOrderPostGoods;
	private Button myShopSetting;

	private void findViews() {
		myShopImage = (ImageView) findViewById(R.id.myShopImage);
		myShopName = (TextView) findViewById(R.id.myShopName);
		myShopIsRegist = (TextView) findViewById(R.id.myShopIsRegist);
		myShopTdIn = (TextView) findViewById(R.id.myShopTdIn);
		myShopTdAsk = (TextView) findViewById(R.id.myShopTdAsk);
		myShopTdSold = (TextView) findViewById(R.id.myShopTdSold);
		myShopTdOrder = (TextView) findViewById(R.id.myShopTdOrder);
		myShopOrderMannerger = (Button) findViewById(R.id.myShopOrderMannerger);
		myShopOrderMyGoods = (Button) findViewById(R.id.myShopOrderMyGoods);
		myShopOrderPostGoods = (Button) findViewById(R.id.myShopOrderPostGoods);
		myShopSetting = (Button) findViewById(R.id.myShopSetting);

		myShopOrderMannerger.setOnClickListener(this);
		myShopOrderMyGoods.setOnClickListener(this);
		myShopOrderPostGoods.setOnClickListener(this);
		myShopSetting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == myShopOrderMannerger) {
			// Handle clicks for myShopOrderMannerger
		} else if (v == myShopOrderMyGoods) {
			// Handle clicks for myShopOrderMyGoods
		} else if (v == myShopOrderPostGoods) {
			// Handle clicks for myShopOrderPostGoods
		} else if (v == myShopSetting) {
			// Handle clicks for myShopSetting
		}
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
		// TODO Auto-generated method stub

	}
}
