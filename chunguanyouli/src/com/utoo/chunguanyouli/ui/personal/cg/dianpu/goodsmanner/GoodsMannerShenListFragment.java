package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.BuilderHelper;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;

public class GoodsMannerShenListFragment extends NetDataBaseFragment implements
		OnRefreshListener2<ListView> {
	private static final int TAG_MORE = 1;
	private static final int TAG_REFLESH = 2;
	private static final int TAG_DELETE = 0;
	CommonAdapter<CgGoodsId> sallingAdapter;
	Activity context;
	View pView;
	int pageIndex = 1;

	PullToRefreshListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public void onResume() {
		getGoods(TAG_REFLESH, pageIndex);
		super.onResume();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		pView = inflater.inflate(R.layout.fragment_goods_manager, container,
				false);
		initViews();
		return pView;
	}

	private void getGoods(int Tag, int pageIndex) {
		apply(API_F
				.getMyStoreGoods(//act.userNeed.getId(), act.userNeed.getSid(),
						act.userNeed.getCid(), 0, pageIndex, Tag));
	}

	private void initViews() {
		list = (PullToRefreshListView) this.pView
				.findViewById(R.id.goodsManagerList);
		list.setMode(Mode.BOTH);
		list.setOnRefreshListener(this);
		initAdapter();
	}

	private void initAdapter() {

		sallingAdapter = new CommonAdapter<CgGoodsId>(getActivity(),
				R.layout.item_goodsmanner_shenhezhong) {

			@Override
			public void convert(ViewHolderHelper helper, final CgGoodsId item) {

				helper.setText(R.id.goodsMannerName, item.getName());
				helper.setText(R.id.goodsMannerPrice, "" + item.getPrice());
				helper.setText(R.id.goodsMannerSoldSum,
						"已售: " + item.getNumsell());
				helper.setText(R.id.goodsMannerStoreSum,
						"库存:" + item.getNumhave());
				helper.setImageByUrl(R.id.goodsMannerImage, item.getPic(),
						R.drawable.index_goods_list,
						R.drawable.index_goods_list);
				OnClickListener lis = new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int viewId = v.getId();
						if (viewId == R.id.goodsShenMannerDelete) {
							// 删除
							BuilderHelper.showBuilder(context, "确认删除吗",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											delettingGoods = item;
											deleteGoods(delettingGoods);
										}
									});
						}
					}
				};

				helper.setClickListener(R.id.goodsShenMannerDelete, lis);

			}
		};
		list.setAdapter(sallingAdapter);
	}

	private void deleteGoods(CgGoodsId goods) {
		apply(API_F.deleteGoods(goods.getId(), TAG_DELETE));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		list.onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		list.onRefreshComplete();
		if (TAG == TAG_MORE) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<CgGoodsId> goodsList = null;
			Gson gson = new Gson();

			goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgGoodsId>>() {
					}.getType());
			sallingAdapter.addData(goodsList);
			pageIndex++;
		} else if (TAG == TAG_REFLESH) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<CgGoodsId> goodsList = null;
			Gson gson = new Gson();

			goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgGoodsId>>() {
					}.getType());
			sallingAdapter.addData(goodsList);
			pageIndex = 2;
		} else if (TAG == TAG_DELETE) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					// 删除成功，清除缓存
					deleteSoftCatch();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	CgGoodsId delettingGoods;

	private void deleteSoftCatch() {
		sallingAdapter.delete(delettingGoods);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		getGoods(TAG_REFLESH, 1);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		getGoods(TAG_MORE, pageIndex);
	}

}
