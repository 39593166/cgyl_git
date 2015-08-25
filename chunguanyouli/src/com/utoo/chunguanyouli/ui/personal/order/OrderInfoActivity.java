package com.utoo.chunguanyouli.ui.personal.order;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.pay.MyPay;
import com.alipay.sdk.pay.PayResult;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgOrderGoodsId;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;
import com.views.NoScrollListView;

public class OrderInfoActivity extends NetDataBaseActionBarActivity implements
		OnClickListener {
	private static final int TAG_GETORDER = 1;
	private static final int TAG_DELETE = 2;
	private static final int TAG_OVER_ORDER = 3;
	private static final int TAG_PAY_INFO = 3;
	private static final int TAG_GETORDERGOODS = 4;
	private static final int TAG_UPDATEORDER4 = 0;
	int orderId;
	CgOrderId order;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_info);
		initToolbar("我的订单");
		orderId = getIntent().getIntExtra("orderId", 0);
		findViews();
		getOrderInfo();
		getOrderGoods();
	}

	private void getOrderInfo() {
		apply(API_F.getOrderInfo(orderId, userNeed.getId(), userNeed.getSid(),
				TAG_GETORDER));
	}

	private void getOrderGoods() {
		apply(API_F.getOrderGoods(orderId, userNeed.getId(), userNeed.getSid(),
				TAG_GETORDERGOODS));
	}

	private void deleteOrder() {
		apply(API_F.deleteOrder(orderId, userNeed.getId(), userNeed.getSid(),
				TAG_DELETE));
	}

	private TextView orderInfoStatu;
	private TextView orderInfoPayPrice1;
	private TextView orderInfoAppend1;
	private TextView orderInfoOrderNum;
	private TextView orderInfoPerson;
	private TextView orderInfoCellphone;
	private TextView orderInfoArea;
	private NoScrollListView orderInfoGoodsInfo;
	private TextView orderInfoPayPrice;
	private Button orderInfoDelete;
	private Button orderInfoGoCommend;
	private Button orderInfoGoPay;
	private Button orderInfoOverOrder;

	private void findViews() {
		orderInfoStatu = (TextView) findViewById(R.id.orderInfoStatu);
		orderInfoPayPrice1 = (TextView) findViewById(R.id.orderInfoPayPrice1);
		orderInfoAppend1 = (TextView) findViewById(R.id.orderInfoAppend1);
		orderInfoPerson = (TextView) findViewById(R.id.orderInfoPerson);
		orderInfoCellphone = (TextView) findViewById(R.id.orderInfoCellphone);
		orderInfoArea = (TextView) findViewById(R.id.orderInfoArea);
		orderInfoGoodsInfo = (NoScrollListView) findViewById(R.id.orderInfoGoodsInfo);
		orderInfoPayPrice = (TextView) findViewById(R.id.orderInfoPayPrice);
		orderInfoOrderNum = (TextView) findViewById(R.id.orderInfoOrderNum);
		orderInfoDelete = (Button) findViewById(R.id.orderInfoDelete);
		orderInfoGoCommend = (Button) findViewById(R.id.orderInfoGoCommend);
		orderInfoGoPay = (Button) findViewById(R.id.orderInfoGoPay);
		orderInfoOverOrder = (Button) findViewById(R.id.orderInfoOverOrder);

		orderInfoDelete.setOnClickListener(this);
		orderInfoGoCommend.setOnClickListener(this);
		orderInfoGoPay.setOnClickListener(this);
		orderInfoOverOrder.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == orderInfoDelete) {
			// List<CgOrderId> deleteOrders = new ArrayList<CgOrderId>();
			// deleteOrders.add(order);
			deleteOrder();
		} else if (v == orderInfoGoCommend) {
			Intent intent = new Intent();
			intent.setClass(this, CommentGoodsListActivity.class);
			intent.putExtra("goods", order.getGoodslist());
			this.startActivity(intent);
		} else if (v == orderInfoGoPay) {
			apply(API_C.getAliPayInfo(TAG_PAY_INFO, order.getNumstr(),
					userNeed.getId(), userNeed.getSid()));
		} else if (v == orderInfoOverOrder) {
			updateOrder();
			// order.setNowState(CgOrderId.STATU_OVER);

			// orderInfoStatu.setText("已收货");
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	private final int MSG_WHAT = 1;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == MSG_WHAT) {
				// Result aliRes = new Result((String) msg.obj);
				// Toast.makeText(OrderInfoActivity.this, aliRes.getResult(),
				// Toast.LENGTH_SHORT).show();
				PayResult payResult = new PayResult((String) msg.obj);

				// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
				String resultInfo = payResult.getResult();

				String resultStatus = payResult.getResultStatus();

				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(OrderInfoActivity.this, "支付成功",
							Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(OrderInfoActivity.this, "支付结果确认中",
								Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(OrderInfoActivity.this, "支付失败",
								Toast.LENGTH_SHORT).show();

					}
				}
			}
		}

	};

	@Override
	protected void onReturned(String response, int TAG) {

		if (TAG == TAG_GETORDER) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			Gson gson = new Gson();
			List<CgOrderId> cityList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgOrderId>>() {
					}.getType());
			// setOrder(cityList.get(0));
			if (cityList.size() == 0) {
				Toast.makeText(this, "网络连接失败", 0).show();
			}
			if (cityList == null || cityList.size() == 0) {
				Toast.makeText(this, "订单未查询到", Toast.LENGTH_SHORT).show();
				return;
			}
			this.order = cityList.get(0);
			setOrderInfo();
		} else if (TAG == TAG_DELETE) {
			this.finish();
		} else if (TAG == TAG_PAY_INFO) {
			try {
				JSONObject jo = new JSONObject(response);
				String str = jo.optString("str");
				String sign = jo.optString("sign");
				Log.e("str", str);
				Log.e("sign", sign);
				MyPay.pay(this, handler, MSG_WHAT, str, sign);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (TAG == TAG_UPDATEORDER4) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					orderInfoStatu.setText("已收货");
					orderInfoDelete.setVisibility(View.VISIBLE);
					orderInfoGoCommend.setVisibility(View.VISIBLE);
					orderInfoGoPay.setVisibility(View.GONE);
					orderInfoOverOrder.setVisibility(View.GONE);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void setOrderType() {
		if (order.getNowState() == CgOrderId.STATU_APPENDDING) {
			// 待收货--
			order.setType(CgOrderId.TYPE_WAIT_COLLECT);
			orderInfoStatu.setText("待收货");
		} else if (order.getNowState() == CgOrderId.STATU_APPLY_RETURN_GOODS) {
			// 退货
		} else if (order.getNowState() == CgOrderId.STATU_APPLY_RETURN_PAY) {
			// 已退货
		} else if (order.getNowState() == CgOrderId.STATU_DELETED) {
			// 已山粗
		} else if (order.getNowState() == CgOrderId.STATU_OVER) {
			// 待评价---
			orderInfoStatu.setText("已完成");
			order.setType(CgOrderId.TYPE_WAIT_COMMEND);
		} else if (order.getNowState() == CgOrderId.STATU_USER_SUBMIT) {
			// 待付款--
			order.setType(CgOrderId.TYPE_NO_PAY);
			orderInfoStatu.setText("待付款");
		} else if (order.getNowState() == CgOrderId.STATU_WAITTING_APPEND
				|| order.getNowState() == CgOrderId.STATU_PAYED) {
			// 带发货--
			orderInfoStatu.setText("待发货");
			order.setType(CgOrderId.TYPE_WAIT_APPEND);
		}
		if (order.getNowState() == CgOrderId.STATU_PAYED) {
			orderInfoStatu.setText("已发货");
		}

		if (order.getType() == CgOrderId.TYPE_NO_PAY) {
			// 用户提交
			orderInfoStatu.setText("待付款");
			orderInfoDelete.setVisibility(View.VISIBLE);
			orderInfoGoPay.setVisibility(View.VISIBLE);
		} else if (order.getType() == CgOrderId.TYPE_WAIT_APPEND) {
			orderInfoStatu.setText("已付款");
			// 支付成功
		} else if (order.getType() == CgOrderId.TYPE_WAIT_COLLECT) {
			orderInfoStatu.setText("待收货");
			// 已发货
			orderInfoOverOrder.setVisibility(View.VISIBLE);
		} else if (order.getType() == CgOrderId.TYPE_WAIT_COMMEND) {
			orderInfoStatu.setText("已收货");
			// 用户确认
			orderInfoDelete.setVisibility(View.VISIBLE);
			orderInfoGoCommend.setVisibility(View.VISIBLE);
		} else if (order.getNowState() == -1) {

			// 用户申请退款
			orderInfoDelete.setVisibility(View.GONE);
			orderInfoGoCommend.setVisibility(View.GONE);
			orderInfoGoPay.setVisibility(View.GONE);
			orderInfoOverOrder.setVisibility(View.GONE);
		} else if (order.getNowState() == -8) {
			// 已退款
			orderInfoDelete.setVisibility(View.GONE);
			orderInfoGoCommend.setVisibility(View.GONE);
			orderInfoGoPay.setVisibility(View.GONE);
			orderInfoOverOrder.setVisibility(View.GONE);
		} else if (order.getNowState() == -3) {
			// 用户申请退货
			orderInfoDelete.setVisibility(View.GONE);
			orderInfoGoCommend.setVisibility(View.GONE);
			orderInfoGoPay.setVisibility(View.GONE);
			orderInfoOverOrder.setVisibility(View.GONE);
		} else if (order.getNowState() == -9) {
			// 已退货
			orderInfoDelete.setVisibility(View.GONE);
			orderInfoGoCommend.setVisibility(View.GONE);
			orderInfoGoPay.setVisibility(View.GONE);
			orderInfoOverOrder.setVisibility(View.GONE);
		} else if (order.getNowState() == -7) {
			orderInfoDelete.setVisibility(View.GONE);
			orderInfoGoCommend.setVisibility(View.GONE);
			orderInfoGoPay.setVisibility(View.GONE);
			orderInfoOverOrder.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置商品信息
	 */
	private void setOrderInfo() {

		if (order == null) {
			return;
		}
		setOrderType();
		// 设定显示按钮
		//
		orderInfoPayPrice1.setText("￥" + order.getGoodsprice());
		orderInfoAppend1.setText("￥" + order.getPostage() + "");
		orderInfoPerson.setText(order.getReceiver() + "");
		orderInfoCellphone.setText(order.getRemobile() + "");
		orderInfoArea.setText(order.getRearea() + ":" + order.getReaddress());
		orderInfoPayPrice.setText("￥"
				+ (order.getGoodsprice() + order.getPostage()));
		orderInfoOrderNum.setText(order.getNumstr() + "");
		List<CgOrderGoodsId> goodsList = order.getGoodslist();
		if (goodsList != null && goodsList.size() != 0) {
			goodsAdapter = new CommonAdapter<CgOrderGoodsId>(this, goodsList,
					R.layout.item_ordergoods) {

				@Override
				public void convert(ViewHolderHelper helper, CgOrderGoodsId item) {
					// TODO Auto-generated method stub
					helper.setText(R.id.order_goods_name, item.getGoodsname())
							.setText(R.id.order_goods_price,
									"￥" + item.getPrice())
							.setText(R.id.order_goods_num, item.getNum() + "")
							.setImageByUrl(R.id.order_goods_img, item.getPic(),
									R.drawable.index_goods_list,
									R.drawable.index_goods_list);
				}
			};
			orderInfoGoodsInfo.setAdapter(goodsAdapter);
			orderInfoGoodsInfo
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, final int position, long id) {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							intent.setClass(OrderInfoActivity.this,
									GoodsInfoActivity.class);
							intent.putExtra("goodsId",
									goodsAdapter.getItem(position).getGoodsid());
							OrderInfoActivity.this.startActivity(intent);
						}
					});
		}
	}

	private void updateOrder() {
		order.setNowState(CgOrderId.STATU_OVER);
		order.setGoodslist(null);
		apply(API_F.updateOrder(order, TAG_UPDATEORDER4, userNeed.getId(),
				userNeed.getSid()));
	}

	CommonAdapter<CgOrderGoodsId> goodsAdapter;
}
