package com.utoo.chunguanyouli.ui.village.villagefragment;

/**
 * 特色产品
 */
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;
import com.utoo.chunguanyouli.ui.village.VillagesIntroduceActivity;

public class FeatureProductFragment extends NetDataBaseFragment implements
		OnRefreshListener2<GridView>, OnItemClickListener {

	final int TAG_MORE = 1;
	final int TAG_REFLESH = 2;
	private FragmentActivity context;
	CommonAdapter<CgGoodsId> goodsAdapter;
	int pageIndex = 1;
	// GridView
	@ViewInject(R.id.gridView)
	private PullToRefreshGridView gridView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_featureproduct,
				container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		gridView.setMode(Mode.PULL_FROM_END);
		gridView.setOnRefreshListener(this);
		
		GridView gv = gridView.getRefreshableView();
		gv.setHorizontalSpacing(20);
		gv.setVerticalSpacing(20);

		// 初始化
		goodsAdapter = new CommonAdapter<CgGoodsId>(context,
				R.layout.adapter_featureproduct) {
			@Override
			public void convert(ViewHolderHelper helper, CgGoodsId item) {
				helper.setText(R.id.title, item.getName())
						.setText(R.id.price, "￥" + item.getPrice()+"元")
						.setText(R.id.people, item.getNumsell() + "\t人购买")
						.setImageByUrl(R.id.icon, item.getPic(),
								R.drawable.index_news_grid,
								R.drawable.index_news_grid);
			}
		};
		gridView.setOnItemClickListener(this);
		gridView.setAdapter(goodsAdapter);
		// 请求网络
		apply(API_F.getMyStoreGoods(VillagesIntroduceActivity.id, 1, pageIndex,
				TAG_MORE));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		gridView.onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		gridView.onRefreshComplete();
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

	private void setGoods(int tag, List<CgGoodsId> goodsList) {
		if (tag == TAG_MORE) {
			goodsAdapter.addData(goodsList);
			pageIndex++;
		} else if (tag == TAG_REFLESH) {
			pageIndex = 2;
			goodsAdapter.resetData(goodsList);
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		apply(API_F.getMyStoreGoods(VillagesIntroduceActivity.id, 1, pageIndex,
				TAG_MORE));
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
