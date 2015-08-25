package com.utoo.chunguanyouli.ui.personal.collection;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.tool.SelectorAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;

/**
 * 我收藏的商品
 * 
 * @author fsm
 * 
 */
public class MyCollectionGoodsActivity extends NetDataBaseActionBarActivity {
	private static final int TAG_GETCOLLECTION = 0;
	private static final int TAG_DELETE = 1;
	ListView goodsListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_collection_goods);
		init();
		getMyCollection();
		initToolbar("商品收藏");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_collection_goods, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_mycollect_goods_delete:
			deleteCollection();
			break;
		default:
			break;
		}
		return true;
	}

	private void getMyCollection() {
		apply(API_F.getCollectionGoods(userNeed.getId(), userNeed.getSid(), 1,
				TAG_GETCOLLECTION));
	}

	SelectorAdapter<CgGoodsId> goodsAdapter;

	private void init() {
		goodsListView = (ListView) findViewById(R.id.myCollectionGoodsList);
		goodsAdapter = new SelectorAdapter<CgGoodsId>(this,
				R.layout.item_my_collection_goods) {

			@Override
			public void convert(ViewHolderHelper helper, CgGoodsId item,
					final int position) {
				helper.setText(R.id.collect_goods_name, item.getName())
						.setText(R.id.collect_goods_price,
								"￥" + item.getPrice())
						.setText(R.id.collect_goods_des, item.getContent())
						.setText(R.id.collect_goods_num_sell,
								item.getNumsell() + "\t人买过")
						.setImageByUrl(R.id.collect_goods_img, item.getPic(),
								R.drawable.index_goods_list,
								R.drawable.index_goods_list);
				helper.setChecBoxChangeLis(R.id.collect_isSelect,goodsAdapter.isSelect(position),
						new OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								goodsAdapter.change(position, isChecked);
							}
						});
			}

		};
		goodsListView.setAdapter(goodsAdapter);
		// test = (TextView) findViewById(R.id.test);
		goodsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MyCollectionGoodsActivity.this,
						GoodsInfoActivity.class);
				intent.putExtra("goodsId", goodsAdapter.getItem(position)
						.getId());
				startActivity(intent);
			}
		});
	}

	int sum;

	private void deleteCollection() {
		sum = goodsAdapter.getSelectedDatas().size();
		for (CgGoodsId goods : goodsAdapter.getSelectedDatas()) {
			apply(API_F.deleteCollect(goods.getId(), userNeed.getId(),
					userNeed.getSid(), 0, TAG_DELETE));
		}
	}

	// TextView test;

	@Override
	protected void NetFailed(VolleyError error, int TAG) {

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// test.setText(response);
		if (TAG == TAG_GETCOLLECTION) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			Gson gson = new Gson();
			List<CgGoodsId> goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgGoodsId>>() {
					}.getType());
			setGoods(TAG, goodsList);
		} else if (TAG == TAG_DELETE) {
			JSONObject jo = null;
			try {
				jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					Toast.makeText(this, "删除成功", 0).show();
					removeSoft();
				}
			} catch (JSONException e) {
				e.printStackTrace();

			}
		}
	}

	private void removeSoft() {
		List<CgGoodsId> deletes = new ArrayList<CgGoodsId>();
		for (int i = 0; i < goodsAdapter.getCount(); i++) {
			if (goodsAdapter.selects.get(i)) {
				deletes.add(goodsAdapter.getItem(i));
			}
		}

		goodsAdapter.deleteSelecters();
		goodsAdapter.notifyDataSetChanged();
	}

	private void setGoods(int tAG, List<CgGoodsId> goodsList) {
		goodsAdapter.resetData(goodsList);
	}
}
