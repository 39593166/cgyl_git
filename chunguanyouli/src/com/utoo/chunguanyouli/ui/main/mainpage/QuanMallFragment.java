package com.utoo.chunguanyouli.ui.main.mainpage;

/**
 * 圈商城
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.utoo.autoviewpager.AutoScrollViewPager;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgTypeId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.main.RecommendGoodsActivity;
import com.utoo.chunguanyouli.ui.shopping.GoodsClassifyActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;
import com.utoo.pagertranseffect.DepthPageTransformer;
import com.views.NoScrollGridView;

public class QuanMallFragment extends NetDataBaseFragment implements
		OnItemClickListener {

	private MainActivity act;
	private int pageIndex = 1;
	private static final int TAG_ADV_IMG = 3;
	private CommonAdapter<CgTypeId> adapter;
	private List<CgTypeId> list = new ArrayList<CgTypeId>();
	private Intent intent = new Intent();
	private CommonAdapter<CgGoodsId> goodsAdapter;

	// @ViewInject(R.id.goodsListMore)
	// private Button button;

	@ViewInject(R.id.good_gridView)
	private GridView gridView;

	@ViewInject(R.id.main_adv_pager)
	private AutoScrollViewPager advGallery;

	@ViewInject(R.id.goodsGridView)
	private NoScrollGridView goodsGridView;

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (parent == gridView) {
			CgTypeId info = adapter.getItem(position);
			intent.setClass(act, RecommendGoodsActivity.class);
			intent.putExtra("type", info);
			startActivity(intent);
		} else if (parent == goodsGridView) {
			intent.setClass(act, GoodsInfoActivity.class);
			intent.putExtra("goodsId", goodsAdapter.getItem(position).getId());
			startActivity(intent);
		}
	}

	@OnClick(R.id.goodsListMore)
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.goodsListMore:
			Intent intent = new Intent(act, GoodsClassifyActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		act = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_quan, container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new CommonAdapter<CgTypeId>(act, list, R.layout.adapter_type) {
			@Override
			public void convert(ViewHolderHelper helper, CgTypeId item) {
				helper.setImageByUrl(R.id.icon, item.getPic(),
						R.drawable.index_news_list_big,
						R.drawable.index_news_list_big).setText(R.id.name,
						item.getName());
			}
		};
		gridView.setAdapter(adapter);
		apply(API_C.getMainNews(TAG_ADV_IMG));
		apply(API_C.getType(0));
		apply(API_F.getMainNewGoods(1));
		goodsAdapter = new CommonAdapter<CgGoodsId>(act,
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
		goodsGridView.setAdapter(goodsAdapter);
		goodsGridView.setOnItemClickListener(this);
		gridView.setOnItemClickListener(this);

	}

	/**
	 * 提供外部更新接口
	 */
	public void refleshQuan() {
		apply(API_C.getType(0));
		apply(API_F.getMainNewGoods(1));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		act.scrollView.onRefreshComplete();
		// onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		act.scrollView.onRefreshComplete();

		if (TAG == 0) {
			List<CgTypeId> typesList = null;
			try {
				JSONObject json = new JSONObject(response);
				int state = json.getInt("state");
				if (state == 0) {
					return;
				}

				JSONArray data = json.optJSONArray("val");
				Gson gson = new Gson();
				typesList = gson.fromJson(data.toString(),
						new TypeToken<List<CgTypeId>>() {
						}.getType());

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			adapter.resetData(typesList);
			Log.e("adapterType", adapter.getCount() + "");
		} else if (TAG == 1) {

			List<CgGoodsId> goodsList = null;

			try {
				JSONObject json = new JSONObject(response);
				// String sid = json.getString("sid");
				int state = json.getInt("state");
				if (state == 0) {
					return;
				}

				JSONArray data = json.optJSONArray("val");
				Gson gson = new Gson();
				goodsList = gson.fromJson(data.toString(),
						new TypeToken<List<CgGoodsId>>() {
						}.getType());

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int size = goodsList.size();
			if (size > 0) {
				if (size <= 2 || size == 4) {
					goodsAdapter.resetData(goodsList);
				} else if (size == 3) {
					goodsAdapter.resetData(goodsList.subList(0, 2));
				} else if (size > 4) {
					goodsAdapter.resetData(goodsList.subList(0, 4));
				}
			}
			Log.e("goodsAdapter", goodsAdapter.getCount() + "------q");
		} else if (TAG == TAG_ADV_IMG) {
			try {
				JSONObject ob = new JSONObject(response);
				JSONArray ja = ob.optJSONArray("urls");
				List<String> urls = new ArrayList<String>();
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.optJSONObject(i);
					String url = jo.optString("url");
					urls.add(ClientConfigs.PICHOST + url);
				}
				BannerAdapter adapter = new BannerAdapter(urls, act,
						R.drawable.index_news_list_big);
				advGallery.setAdapter(adapter);
				advGallery.setPageTransformer(true, new DepthPageTransformer());
				advGallery.startAutoScroll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
