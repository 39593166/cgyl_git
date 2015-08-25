package com.utoo.chunguanyouli.ui.personal.cg.dianpu;

/**
 * 我的店铺
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.MyApplication;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgStoreInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner.AddGoodsActivity;
import com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner.GoodsMannergerActivity;
import com.utoo.chunguanyouli.ui.personal.cg.dianpu.ordermannerger.OrderMannergerActivity;
import com.utoo.gson.GsonHelper;
import com.views.CircleImageView;
import com.views.NoScrollGridView;

public class MyShopActivity extends NetDataBaseActionBarActivity implements
		OnItemClickListener, OnClickListener {
	private static final int TAG_GET_STOREINFO = 0;
	private static final int TAG_NUM = 1;
	private static final int TAG_HEAD = 0;
	CommonAdapter<ControllerItem> conAdapter;
	MyApplication app;
	CgStoreInfoId myStore = null;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setNeedLogin(true);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myshop);
		findViews();
		app = (MyApplication) getApplication();

		initToolbar("我的店铺");
	}

	@Override
	protected void onResume() {
		getStoreInfo();
		super.onResume();
	}

	private void getNum(int cid) {
		apply(API_F.getMyStoreData(userNeed.getId(), userNeed.getSid(), cid,
				TAG_NUM));
	}

	private void getStoreInfo() {
		Log.e("MyShopAct", "发送请求：" + userNeed.getId());
		apply(API_F.getMyStoreInfo(userNeed.getId(), userNeed.getSid(),
				TAG_GET_STOREINFO));
	}

	private CircleImageView myshopImage;
	private TextView myshopName;
	private TextView myshopDes;
	private TextView myshopisRegist;
	private TextView myshopTodayIn;
	private TextView myshopTodayAskSum;
	private TextView myshopTodayBuyerSum;
	private TextView myshopTodayOrderSum;
	private NoScrollGridView myshopControl;

	private void findViews() {
		myshopImage = (CircleImageView) findViewById(R.id.myshopImage);
		myshopName = (TextView) findViewById(R.id.myshopName);
		myshopDes = (TextView) findViewById(R.id.myshopDes);
		myshopisRegist = (TextView) findViewById(R.id.myshopisRegist);
		myshopTodayIn = (TextView) findViewById(R.id.myshopTodayIn);
		myshopTodayAskSum = (TextView) findViewById(R.id.myshopTodayAskSum);
		myshopTodayBuyerSum = (TextView) findViewById(R.id.myshopTodayBuyerSum);
		myshopTodayOrderSum = (TextView) findViewById(R.id.myshopTodayOrderSum);
		myshopControl = (NoScrollGridView) findViewById(R.id.myshopControl);
		// 功能栏
		List<ControllerItem> conts = new ArrayList<ControllerItem>();
		conts.add(new ControllerItem(1, R.drawable.my_store_order_manager,
				"订单管理"));
		conts.add(new ControllerItem(2, R.drawable.mystore_goods_manager,
				"宝贝管理"));
		conts.add(new ControllerItem(3, R.drawable.my_store_add_goods, "发布宝贝"));
		conts.add(new ControllerItem(4, R.drawable.my_store_setting, "店铺设置"));

		conAdapter = new CommonAdapter<MyShopActivity.ControllerItem>(this,
				conts, R.layout.item_myshop_controll) {
			@Override
			public void convert(ViewHolderHelper helper, ControllerItem item) {
				helper.setImageResource(R.id.item_myshop_controll_img,
						item.getResId());

			}
		};
		myshopControl.setAdapter(conAdapter);
		myshopControl.setOnItemClickListener(this);
		myshopisRegist.setOnClickListener(this);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {

		if (TAG == TAG_GET_STOREINFO) {
			try {
				CgResponse cr = new CgResponse(response);

				if (cr.getState() == 0) {
					return;
				}
				JSONArray data = new JSONArray(cr.getValStr());
				Log.e("", data.toString());
				JSONObject userjson = data.optJSONObject(0);
				myStore = GsonHelper.getBean(userjson.toString(),
						CgStoreInfoId.class);
				app.putBusinessData(ClientConfigs.BUSI_MYSTORE, myStore);
				setStoreInfo(myStore);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_NUM) {
			// {"state":1,"sid":"","num1":"0","num2":"0","num3":"0","num4":"0"}
			JSONObject jo;
			try {
				jo = new JSONObject(response);
				double num1 = jo.optDouble("num1");
				int num2 = jo.optInt("num2");
				int num3 = jo.optInt("num3");
				int num4 = jo.optInt("num4");
				setNums(num1, num2, num3, num4);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (TAG == TAG_HEAD) {

		}
	}

	private void setNums(double n1, int n2, int n3, int n4) {
		myshopTodayIn.setText("" + n1);
		myshopTodayAskSum.setText("" + n2);
		myshopTodayBuyerSum.setText("" + n3);
		myshopTodayOrderSum.setText("" + n4);
	}

	private void setStoreInfo(CgStoreInfoId myStore2) {
		this.myshopDes.setText(myStore2.getDomain());
		this.myshopName.setText(myStore2.getName());
		// app.bu.display(this.myshopImage, myStore2.getPic());
		// NetImgLoader
		// .loadImage(myStore2.getPic(), myshopImage, R.drawable.index);
		Picasso.with(this).load(myStore2.getPic()).error(R.drawable.index)
				.into(myshopImage);
		getNum(myStore2.getId());
	}

	class ControllerItem {
		public ControllerItem(int action, int resId, String name) {
			this.action = action;
			this.resId = resId;
		}

		private String name;
		private int action;
		private int resId;

		public int getAction() {
			return action;
		}

		public int getResId() {
			return resId;
		}

		public String getName() {
			return name;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();

		if (conAdapter.getItem(position).getAction() == 1) {
			intent.setClass(this, OrderMannergerActivity.class);
		} else if (conAdapter.getItem(position).getAction() == 2) {
			intent.setClass(this, GoodsMannergerActivity.class);
		} else if (conAdapter.getItem(position).getAction() == 3) {
			intent.setClass(this, AddGoodsActivity.class);
		} else if (conAdapter.getItem(position).getAction() == 4) {
			intent.setClass(this, MyShopSettingActivity.class);
		}
		this.startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		if (v == myshopisRegist) {
			if (myStore == null) {
				Toast.makeText(this, "请等待", 0).show();
				return;
			}
			Intent intent = new Intent();
			intent.setClass(this, UpdateShopActivity.class);
			intent.putExtra("store", myStore);
			startActivity(intent);
		}
	}
}
