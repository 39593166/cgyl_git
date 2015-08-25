package com.utoo.chunguanyouli.ui.shopping;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgTypeId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.shopping.goodslist.ClassifyGoodsListActivity;

public class GoodsClassifyActivity extends NetDataBaseActionBarActivity
		implements OnItemClickListener {
	// TopClassifyAdapter tAdapter;// 父类adapter
	CommonAdapter<CgTypeId> cAdapter;// 子类adapter
	// final int TOP_CLASSIFY = 1;
	final int CHILD_CLASSIFY = 2;
	boolean isFirstIn = true;
	int TAG_FROM = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classify_goods);
		findViews();
		initChildAdapter();
		TAG_FROM = getIntent().getIntExtra("where", 0);
		// getTopClassify();
		getClildClassify();
		initToolbar("分类查找");
	}

	private void initChildAdapter() {
//		cAdapter = new CommonAdapter<CgTypeId>(this, R.layout.item_classify_c) {
//			@Override
//			public void convert(ViewHolderHelper helper, CgTypeId item) {
//				helper.setText(R.id.item_classify_c_name, item.getName());
//				helper.setImageByUrl(R.id.item_classify_c_img, item.getPic(),
//						R.drawable.index, R.drawable.index);
//			}
//		};
		cAdapter = new CommonAdapter<CgTypeId>(this,
				R.layout.adapter_type) {
			@Override
			public void convert(ViewHolderHelper helper, CgTypeId item) {
				// TODO Auto-generated method stub
				helper.setImageByUrl(R.id.icon, item.getPic(), R.drawable.index_news_list_big,
						R.drawable.index_news_list_big).setText(R.id.name, item.getName());
			}
		};
		this.goodsClassifyChild.setAdapter(cAdapter);
	}

	// private void getTopClassify() {
	// apply(API_F.getGoodsType(0, TOP_CLASSIFY));
	// }

	private void getClildClassify() {
		apply(API_F.getGoodsType(0, CHILD_CLASSIFY));
	}

	// private void setTopClassify(List<CgTypeId> types) {
	// tAdapter = new TopClassifyAdapter(this, types);
	// goodsClassifyTop.setAdapter(tAdapter);
	// getClildClassify(types.get(0));
	// }

	private void setChildClassify(List<CgTypeId> clsifys) {
		cAdapter.resetData(clsifys);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		List<CgTypeId> typesList = null;
		try {
			JSONObject json = new JSONObject(response);
			// String sid = json.getString("sid");
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
			// }
			// if (TAG == TOP_CLASSIFY) {
			// setTopClassify(typesList);
			// if (typesList.size() > 0)
			// choosedTopType = typesList.get(0);
			// } else

		}
		if (TAG == CHILD_CLASSIFY) {
			if (typesList == null || typesList.size() == 0) {
				// if (!isFirstIn) {
				choosedDown(choosedTopType, true);
				// }
			} else {
				setChildClassify(typesList);
			}
			// isFirstIn = false;
		}

	}

	// private ListView goodsClassifyTop;
	private GridView goodsClassifyChild;

	private void findViews() {
		// goodsClassifyTop = (ListView) findViewById(R.id.goodsClassifyTop);
		goodsClassifyChild = (GridView) findViewById(R.id.goodsClassifyChild);
		// goodsClassifyTop.setOnItemClickListener(this);
		goodsClassifyChild.setOnItemClickListener(this);
	}

	CgTypeId choosedTopType = null;

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// if (parent == goodsClassifyTop) {
		// choosedTopType = tAdapter.clsifies.get(position);
		// tAdapter.selecter.set(tAdapter.oldSelecter, false);
		// tAdapter.selecter.set(position, true);
		// tAdapter.oldSelecter = position;
		// getClildClassify(tAdapter.clsifies.get(position));
		// tAdapter.notifyDataSetChanged();

		// } else if (parent == goodsClassifyChild) {
		choosedDown(cAdapter.getItem(position), false);
		// }
	}

	private void choosedDown(CgTypeId type, boolean isTop) {

		if (TAG_FROM == 0) {
			Intent intent = new Intent();
			intent.putExtra("type", type);
			intent.setClass(this, ClassifyGoodsListActivity.class);
			startActivity(intent);
		} else if (TAG_FROM == 1) {
			Intent intent = new Intent();
			// if (type.equals(choosedTopType)) {
			// intent.putExtra("topType", type);
			// } else {
			// intent.putExtra("topType", choosedTopType);
			intent.putExtra("type", type);
			// }

			// intent.setClass(this, ClassifyGoodsListActivity.class);
			setResult(RESULT_OK, intent);
			this.finish();
		}
	}
}
