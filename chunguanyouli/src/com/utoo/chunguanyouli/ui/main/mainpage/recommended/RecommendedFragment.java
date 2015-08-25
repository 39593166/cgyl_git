package com.utoo.chunguanyouli.ui.main.mainpage.recommended;

/**
 * 优产推荐/折扣农场
 */
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.main.mainpage.MainActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;

public class RecommendedFragment extends NetDataBaseFragment implements
		OnRefreshListener2<ListView> {

	private int index = 1;
	private MainActivity act;
	private Intent intent = new Intent();

	CommonAdapter<CgGoodsId> goodsAdapter;


	@ViewInject(R.id.listView_recom)
	private ListView listView;


	// Item点击事件
	@OnItemClick(R.id.listView_recom)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		CgGoodsId info = (CgGoodsId) goodsAdapter.getItem(position);
		intent.setClass(act, GoodsInfoActivity.class);
		intent.putExtra("goodsId", info.getId());
		startActivity(intent);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		act = (MainActivity) getActivity();
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_recommended, container,
				false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		goodsAdapter = new CommonAdapter<CgGoodsId>(act,
				R.layout.item_activity_goods_list_comm_pricenot) {
			@Override
			public void convert(ViewHolderHelper helper, CgGoodsId item) {
				helper.setText(R.id.comm_goods_name, item.getName())
						.setText(R.id.comm_goods_price,
								"原价：￥" + item.getPrice() + "元")
								.setText(R.id.comm_goods_pricenot, "折扣优惠：￥"+item.getPricenot()+"元")
						.setText(R.id.comm_goods_des, item.getContent())
						.setText(R.id.comm_goods_num_sell,
								item.getNumsell() + "\t人购买")
						.setImageByUrl(R.id.comm_goods_img, item.getPic(),
								R.drawable.index_news_grid,
								R.drawable.index_news_grid);
			}
		};

		listView.setAdapter(goodsAdapter);


		// 请求网络
		apply(API_F.getMainRecommendNewGoods(0));

	}

	/**
	 * call from outsid
	 */
	public void refleshRecommend() {
		apply(API_F.getMainRecommendNewGoods(0));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		act.scrollView.onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		act.scrollView.onRefreshComplete();
		CgResponse cr = new CgResponse(response);
		if (cr.getState() == 0) {
			return;
		}
		Gson gson = new Gson();
		List<CgGoodsId> goodsList = gson.fromJson(cr.getValStr(),
				new TypeToken<List<CgGoodsId>>() {
				}.getType());

		if (TAG == 0) {
			goodsAdapter.resetData(goodsList);
		} else if (TAG == 1) {

		} else if (TAG == 2) {

		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}
}
