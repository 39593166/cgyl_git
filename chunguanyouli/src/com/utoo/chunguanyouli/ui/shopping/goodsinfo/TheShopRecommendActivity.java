package com.utoo.chunguanyouli.ui.shopping.goodsinfo;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

public class TheShopRecommendActivity extends NetDataBaseActionBarActivity
		implements OnItemClickListener {
	private static final int TAG_GETGOODS = 0;
	CommonAdapter<CgGoodsId> goodsAdapter;
	ListView goodsListView;
	int cid;// 店铺id

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_the_shop_recommend);
		this.cid = getIntent().getIntExtra("cid", 0);
		initView();
		initToolbar("店铺推荐");
		getGoods(TAG_GETGOODS);
	}

	private void getGoods(int TAG) {
		// 加载
		// apply(API_F.getGoodsByType(classifyId, sortBy, pageIndex, des, TAG));
		apply(API_F.getStoreRecommendGoods(cid, TAG));
	}

	private void initView() {
		this.goodsListView = (ListView) this
				.findViewById(R.id.shopRecommendGoodsList);
		goodsListView.setOnItemClickListener(this);
		goodsAdapter = new CommonAdapter<CgGoodsId>(this,
				R.layout.item_activity_goods_list_comm) {

			@Override
			public void convert(ViewHolderHelper helper, CgGoodsId item) {
				helper.setText(R.id.comm_goods_name, item.getName())
						.setText(R.id.comm_goods_price,
								"￥" + item.getPrice() + "元")
						.setText(R.id.comm_goods_des, item.getContent())
						.setText(R.id.comm_goods_num_sell,
								item.getNumsell() + "\t人购买")
						.setImageByUrl(R.id.comm_goods_img, item.getPic(),
								R.drawable.index_news_grid,
								R.drawable.index_news_grid);
			}
		};
		goodsListView.setAdapter(goodsAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, GoodsInfoActivity.class);
		intent.putExtra("goodsId", goodsAdapter.getItem(position).getId());
		startActivity(intent);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		CgResponse cr = new CgResponse(response);
		if (cr.getState() == 0) {
			return;
		}
		Gson gson = new Gson();
		List<CgGoodsId> goodsList = gson.fromJson(cr.getValStr(),
				new TypeToken<List<CgGoodsId>>() {
				}.getType());
		goodsAdapter.resetData(goodsList);
	}

}
