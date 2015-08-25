package com.utoo.chunguanyouli.ui.shopping;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.RefleshableActivity;
import com.utoo.chunguanyouli.ui.shopping.bean.ShoppingMainData;
import com.views.NoScrollGridView;
import com.views.RLScrollView;

public class ShoppingActivity extends RefleshableActivity implements
		OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping);
		findViews();
		initReflesh(shoppingReflesh, shoppingScrollView);
	}

	@Override
	protected void reflesh() {
		// TODO Auto-generated method stub

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

	private void getShoppingDatas(int TAG) {
		// post(TAG, clientContext.APIHOST_CITY,
		// clientContext.getShoppingMainParams());
	}

	CommonAdapter<CgGoodsId> worthBuyAdapter = null;
	CommonAdapter<CgGoodsId> yourFaverateAdapter = null;

	private void setDatas(ShoppingMainData datas) {
//		bh.setBannerData(datas.getBanners());// 设置banner数据

		shoppingIshot.setVisibility(datas.getHr().isHot() ? View.VISIBLE
				: View.GONE);// 头条是否热门

		shoppingTitle.setText(datas.getHr().getTitle());// 头条内容
		// 发现好产品了
		if (datas.getWorthBuygoodsList() == null
				|| datas.getWorthBuygoodsList().size() == 0) {// 无“发现好产品”隐藏标题
			shoppingTitleWorthbuy.setVisibility(View.GONE);
		} else {
			worthBuyAdapter = new CommonAdapter<CgGoodsId>(this,
					datas.getWorthBuygoodsList(), R.layout.item_goods_grid) {

				@Override
				public void convert(ViewHolderHelper helper, CgGoodsId item) {
					helper.setText(R.id.sp_goods_item_name, item.getName());
					helper.setText(R.id.sp_goods_item_soldsum,
							item.getNumsell() + "人购买");
					helper.setImageByUrl(R.id.sp_goods_item_img, item.getPic(),
							R.drawable.index_goods_list, R.drawable.index_goods_list);
				}
			};
			shoppingWorthbuyList.setAdapter(worthBuyAdapter);
		}
		// 我猜你喜欢这个
		if (datas.getFaverateGoodsList() == null
				|| datas.getFaverateGoodsList().size() == 0) {
			shoppingTitleYourfaverate.setVisibility(View.GONE);
		} else {
			yourFaverateAdapter = new CommonAdapter<CgGoodsId>(this,
					datas.getWorthBuygoodsList(), R.layout.item_goods_grid) {
				@Override
				public void convert(ViewHolderHelper helper, CgGoodsId item) {
					helper.setText(R.id.sp_goods_item_name, item.getName());
					helper.setText(R.id.sp_goods_item_soldsum,
							item.getNumsell() + "人购买");
					helper.setImageByUrl(R.id.sp_goods_item_img, item.getPic(),
							R.drawable.index_goods_list, R.drawable.index_goods_list);
				}
			};
			shoppingYourfaverateList.setAdapter(yourFaverateAdapter);
		}
	}

	private ImageButton shoppingDingzhi;
	private ImageButton shoppingDaigou;
	private ImageButton shoppingClassify;
	private ImageView shoppingIshot;
	private TextView shoppingTitle;
	private ImageView shoppingTitleWorthbuy;
	private NoScrollGridView shoppingWorthbuyList;
	private ImageView shoppingTitleYourfaverate;
	private NoScrollGridView shoppingYourfaverateList;
	private SwipeRefreshLayout shoppingReflesh;
	private RLScrollView shoppingScrollView;

	private void findViews() {
		shoppingReflesh = (SwipeRefreshLayout) this
				.findViewById(R.id.shoppingReflesh);
		shoppingScrollView = (RLScrollView) this
				.findViewById(R.id.shoppingScrollView);
		shoppingDingzhi = (ImageButton) this
				.findViewById(R.id.shopping_dingzhi);
		shoppingDaigou = (ImageButton) this.findViewById(R.id.shopping_daigou);
		shoppingClassify = (ImageButton) this
				.findViewById(R.id.shopping_classify);
		shoppingIshot = (ImageView) this.findViewById(R.id.shopping_ishot);
		shoppingTitle = (TextView) this.findViewById(R.id.shopping_title);
		shoppingTitleWorthbuy = (ImageView) this
				.findViewById(R.id.shopping_title_worthbuy);
		shoppingWorthbuyList = (NoScrollGridView) this
				.findViewById(R.id.shopping_worthbuy_list);
		shoppingTitleYourfaverate = (ImageView) this
				.findViewById(R.id.shopping_title_yourfaverate);
		shoppingYourfaverateList = (NoScrollGridView) this
				.findViewById(R.id.shopping_yourfaverate_list);

		shoppingDingzhi.setOnClickListener(this);
		shoppingDaigou.setOnClickListener(this);
		shoppingClassify.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == shoppingDingzhi) {
			// Handle clicks for shoppingDingzhi
		} else if (v == shoppingDaigou) {
			// Handle clicks for shoppingDaigou
		} else if (v == shoppingClassify) {
			// Handle clicks for shoppingClassify
			Intent intent = new Intent();
			intent.setClass(this, GoodsClassifyActivity.class);
			this.startActivity(intent);
		}
	}

}
