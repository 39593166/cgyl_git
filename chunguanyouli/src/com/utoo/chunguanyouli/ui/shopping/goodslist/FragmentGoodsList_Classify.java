package com.utoo.chunguanyouli.ui.shopping.goodslist;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;

public class FragmentGoodsList_Classify extends NetDataBaseFragment implements
		OnItemClickListener, OnRefreshListener2<GridView> {
	Activity context;
	View pView;

	final int TAG_MORE = 1;
	final int TAG_REFLESH = 2;

	ImageView img1;
	PullToRefreshGridView goodsListView;

	int sortBy;

	public int classifyId;
	private int pageIndex = 1;
	CommonAdapter<CgGoodsId> goodsAdapter;
	public static FragmentGoodsList_Classify getInstance(int searchType, int classifyId) {
		FragmentGoodsList_Classify instance = new FragmentGoodsList_Classify();

		instance.sortBy = searchType;
		instance.classifyId=classifyId;
		return instance;
	}

//	public FragmentGoodsList_Classify(int searchType, int classifyId) {
//		super();
//		this.sortBy = searchType;
//		this.classifyId = classifyId;
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		pView = inflater.inflate(R.layout.activity_goods_list_comm_fragment,
				container, false);

		initViews();
		getGoods(TAG_MORE, pageIndex, sortBy, 0);
		return pView;
	}

	private void getGoods(int TAG, int pageIndex, int sortBy, int des) {
		// 加载
		Log.e("发送请求：", "classifyId:" + classifyId);
		apply(API_F.getGoodsByType(classifyId, sortBy, pageIndex, des, TAG));
	}

	private void initViews() {
		this.goodsListView = (PullToRefreshGridView) pView
				.findViewById(R.id.goodsList);
		goodsListView.setOnItemClickListener(this);
		goodsListView.setMode(Mode.BOTH);
		goodsListView.setOnRefreshListener(this);
		// goodsAdapter = new CommonAdapter<CgGoodsId>(context,
		// R.layout.item_activity_goods_list_comm) {
		//
		// @Override
		// public void convert(ViewHolderHelper helper, CgGoodsId item) {
		// helper.setText(R.id.comm_goods_name, item.getName())
		// .setText(R.id.comm_goods_price, "￥" + item.getPrice())
		// .setText(R.id.comm_goods_des, item.getContent())
		// .setText(R.id.comm_goods_num_sell,
		// "已售" + item.getNumsell() + "件")
		// .setImageByUrl(R.id.comm_goods_img, item.getPic(),
		// R.drawable.index_news_grid,
		// R.drawable.index_news_grid);
		// }
		// };
		goodsAdapter = new CommonAdapter<CgGoodsId>(context,
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
		goodsListView.setAdapter(goodsAdapter);
	}

	private void setGoods(int tag, List<CgGoodsId> goodsList) {
		if (tag == TAG_MORE) {
			goodsAdapter.addData(goodsList);
			pageIndex++;
		} else if (tag == TAG_REFLESH) {
			pageIndex = 2;
			goodsAdapter.resetData(goodsList);
		}
	}

	/**
	 * 刷新完成
	 */
	public void onRefreshComplete() {
		goodsListView.onRefreshComplete();
	}

	/**
	 * 设置正在
	 */
	public void setRefreshing() {
		goodsListView.setRefreshing();
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		onRefreshComplete();
		CgResponse cr = new CgResponse(response);
		if (cr.getState() == 0) {
			return;
		}
		Gson gson = new Gson();
		List<CgGoodsId> goodsList = gson.fromJson(cr.getValStr(),
				new TypeToken<List<CgGoodsId>>() {
				}.getType());
		setGoods(TAG, goodsList);

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		getGoods(TAG_REFLESH, 1, sortBy, 0);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		getGoods(TAG_MORE, pageIndex, sortBy, 0);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), GoodsInfoActivity.class);
		intent.putExtra("goodsId", goodsAdapter.getItem(position).getId());
		getActivity().startActivity(intent);
	}
}
