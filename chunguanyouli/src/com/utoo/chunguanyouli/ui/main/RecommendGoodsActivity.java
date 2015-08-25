package com.utoo.chunguanyouli.ui.main;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgTypeId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;

public class RecommendGoodsActivity extends NetDataBaseActionBarActivity
		implements OnItemClickListener {
	private static final int TAG_GETGOODS = 0;
	PullToRefreshGridView recommendGoodsList;
	CgTypeId typeid;
	CommonAdapter<CgGoodsId> goodsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend_goods);
		typeid = (CgTypeId) getIntent().getSerializableExtra("type");
		init();
		getRecommendGoods();
		initToolbar(typeid.getName() + "推荐");
	}

	private void init() {
		recommendGoodsList = (PullToRefreshGridView) findViewById(R.id.recommendGoodsList);
		recommendGoodsList.setMode(Mode.DISABLED);
		// goodsAdapter = new CommonAdapter<CgGoodsId>(this,
		// R.layout.item_activity_goods_list_comm) {
		//
		// @Override
		// public void convert(ViewHolderHelper helper, CgGoodsId item) {
		// helper.setText(R.id.comm_goods_name, item.getName())
		// .setText(R.id.comm_goods_price, "￥" + item.getPrice())
		// .setText(R.id.comm_goods_des, item.getContent())
		// .setText(R.id.comm_goods_num_sell,
		// item.getNumsell() + "\t人购买")
		// .setImageByUrl(R.id.comm_goods_img, item.getPic(),
		// R.drawable.index_goods_list,
		// R.drawable.index_goods_list);
		// }
		// };
		goodsAdapter = new CommonAdapter<CgGoodsId>(this,
				R.layout.adapter_featureproduct) {

			@Override
			public void convert(ViewHolderHelper helper, CgGoodsId item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.title, item.getName())
						.setText(R.id.price, "￥" + item.getPrice() + "元")
						.setText(R.id.people, "已售" + item.getNumsell() + "件")
						.setImageByUrl(R.id.icon, item.getPic(),
								R.drawable.index_news_grid,
								R.drawable.index_news_grid);
			}
		};
		recommendGoodsList.setAdapter(goodsAdapter);
		recommendGoodsList.setOnItemClickListener(this);
	}

	private void getRecommendGoods() {
		if (typeid.getId() != 0) {
			apply(API_F.getTypeRecommendGoods(typeid.getId(), TAG_GETGOODS));
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {

	}

	@Override
	protected void onReturned(String response, int TAG) {
		CgResponse cr = new CgResponse(response);
		if (cr.getState() == 0) {
			return;
		}
		Log.e("e", cr.getValStr());
		Gson gson = new Gson();
		List<CgGoodsId> goodsList = gson.fromJson(cr.getValStr(),
				new TypeToken<List<CgGoodsId>>() {
				}.getType());

		goodsAdapter.addData(goodsList);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(this, GoodsInfoActivity.class);
		intent.putExtra("goodsId", goodsAdapter.getItem(position).getId());
		this.startActivity(intent);
	}

}
