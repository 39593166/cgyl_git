package com.utoo.chunguanyouli.ui.shopping;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgOrderGoodsId;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.comm.AddressChooseActivity;
import com.utoo.chunguanyouli.ui.personal.order.OrderInfoActivity;
import com.views.NoScrollListView;

public class MakeOrderActivity extends NetDataBaseActionBarActivity implements
		OnClickListener {
	private static final int TAG_MKORDER = 1;
	private static final int TAG_ADDGOODS = 0;
	List<CgGoodsId> goodsList;
	CgOrderId order;
	CommonAdapter<CgGoodsId> goodsAdapter;

	// int buySum;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_order);
		goodsList = (List<CgGoodsId>) getIntent().getSerializableExtra("goods");
		// buySum = getIntent().getIntExtra("buySum", 1);
		initData();
		findViews();
		initToolbar("提交订单");
	}

	private void initData() {
		order = new CgOrderId();
		goodsSum = goodsList.size();
		double priceSum = 0.0d;
		for (CgGoodsId goods : goodsList) {
			priceSum = priceSum + (goods.getPrice() - goods.getPricenot())
					* goods.getBuyNum();
		}
		order.setGoodsprice(priceSum);
	}

	private void mkOrder() {
		String cellphone = mkOrderCellphone.getText().toString().trim();
		String name = mkOrderName.getText().toString().trim();
		String addressArea = mkOrderAddress.getText().toString().trim();
		String addressEdit = mkorderEditAddress.getText().toString().trim();
		String msg = mkorderMsg.getText().toString().trim();

		if (TextUtils.isEmpty(cellphone) || TextUtils.isEmpty(name)
				|| TextUtils.isEmpty(addressEdit)) {
			Toast.makeText(this, "请完善订单信息", Toast.LENGTH_SHORT).show();
			return;
		}
		order.setPostage(0.00d);
		// order.setNumstr(((0xffffff & ((UUserInfoId) app
		// .getBusinessData(ClientContext.BUSI_USER)).getId()) + System
		// .currentTimeMillis())
		// + "");
		order.setNumstr("" + System.currentTimeMillis()
				+ ((int) (Math.random() * 1000)));
		order.setUid(userNeed.getId());
		order.setRemobile(cellphone);
		order.setReceiver(name);
		order.setRearea(addressArea);
		order.setReaddress(addressEdit);
		order.setOfrom("1");
		order.setCuid(goodsList.get(0).getUid());
		order.setBankstr("xxx");
		order.setRezip("000000");
		order.setRephone(cellphone);
		order.setNowState(0);
		order.setDateset(DateTool.getTimeStr(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));
		apply(API_F.makeOrder(order, userNeed.getId(), userNeed.getSid(),
				TAG_MKORDER));
	}

	int goodsSum;

	private void addGoods() {
		for (CgGoodsId goods : goodsList) {
			CgOrderGoodsId orderGoods = new CgOrderGoodsId(0, goods.getName(),
					goods.getId(), order.getNumstr(), goods.getPic(),
					goods.getBuyNum(), goods.getPrice(), userNeed.getId(), 0);
			apply(API_F.makeOrderGoods(orderGoods, userNeed.getId(),
					userNeed.getSid(), TAG_ADDGOODS));
		}
	}

	private EditText mkOrderCellphone;
	private EditText mkOrderName;
	private TextView mkOrderAddress;
	private TextView mkorderAddressChoose;
	private EditText mkorderEditAddress;
	private NoScrollListView mkorderGoods;
	private TextView mkorderPayType;
	private EditText mkorderMsg;
	private Button mkorderSubmit;
	private TextView mkorderPrice;

	private void findViews() {
		mkOrderCellphone = (EditText) findViewById(R.id.mkOrderCellphone);
		mkOrderName = (EditText) findViewById(R.id.mkOrderName);
		mkOrderAddress = (TextView) findViewById(R.id.mkOrderAddress);
		mkorderAddressChoose = (TextView) findViewById(R.id.mkorderAddressChoose);
		mkorderEditAddress = (EditText) findViewById(R.id.mkorderEditAddress);
		mkorderGoods = (NoScrollListView) findViewById(R.id.mkorderGoods);
		mkorderPayType = (TextView) findViewById(R.id.mkorderPayType);
		mkorderMsg = (EditText) findViewById(R.id.mkorderMsg);
		mkorderSubmit = (Button) findViewById(R.id.mkorderSubmit);
		mkorderPrice = (TextView) findViewById(R.id.mkorderPrice);
		mkorderPrice.setText("订单金额 ：￥ " + order.getGoodsprice());
		mkorderSubmit.setOnClickListener(this);
		mkorderAddressChoose.setOnClickListener(this);

		goodsAdapter = new CommonAdapter<CgGoodsId>(this, goodsList,
				R.layout.item_activity_goods_list_comm) {

			@Override
			public void convert(ViewHolderHelper helper, CgGoodsId item) {
				helper.setText(R.id.comm_goods_name, item.getName())
						.setText(R.id.comm_goods_price,
								"￥" + (item.getPrice() - item.getPricenot()))
						.setText(R.id.comm_goods_des, item.getContent())
						.setText(R.id.comm_goods_num_sell,
								"数量：\t" + item.getBuyNum())
						.setImageByUrl(R.id.comm_goods_img, item.getPic(),
								R.drawable.index_goods_list,
								R.drawable.index_goods_list);
			}
		};
		mkorderGoods.setAdapter(goodsAdapter);
	}

	final int RESULTCODE_APPENDSPACE = 3824;

	@Override
	public void onClick(View v) {
		if (v == mkorderSubmit) {
			mkOrder();
			mkorderSubmit.setEnabled(false);
		} else if (v == mkorderAddressChoose) {
			Intent intent = new Intent();
			intent.setClass(this, AddressChooseActivity.class);
			startActivityForResult(intent, RESULTCODE_APPENDSPACE);
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		mkorderSubmit.setEnabled(true);
	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		// {"id":"15","state":"1","sid":""}

		if (TAG == TAG_MKORDER) {
			try {
				JSONObject mkOrderReturn = new JSONObject(response);
				int id = mkOrderReturn.optInt("id");
				order.setId(id);
				addGoods();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_ADDGOODS) {
			JSONObject mkOrderReturn;
			try {
				mkOrderReturn = new JSONObject(response);
				int state = mkOrderReturn.optInt("state");
				if (state == 1) {
					goodsSum--;
				}
				if (goodsSum == 0) {
					Intent intent = new Intent();
					intent.setClass(this, OrderInfoActivity.class);
					intent.putExtra("orderId", order.getId());
					startActivity(intent);
					this.finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case RESULTCODE_APPENDSPACE:
				Bundle b = data.getExtras(); // data为B中回传的Intent
				String area = b.getString("area");
				this.mkOrderAddress.setText(area);
				break;
			default:
				break;
			}
		}
	}
}
