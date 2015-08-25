
package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.entity.Classify;
import com.utoo.chunguanyouli.entity.TopClassify;
import com.utoo.chunguanyouli.tool.CommonExpandableAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.RefleshableActivity;
import com.views.NoScrollExpandableListView;
import com.views.RLScrollView;

public class ChooseGoodsTypeActivity extends RefleshableActivity {
	final int TAG_REFLESH = 1;
	final int TAG_FIRST = 2;
	SwipeRefreshLayout classifyReflesh;
	RLScrollView scorllview;
	NoScrollExpandableListView classifyList;

	CommonExpandableAdapter<TopClassify, Classify> classifyAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_goods_type);
		classifyReflesh = (SwipeRefreshLayout) this
				.findViewById(R.id.classifyReflesh);
		scorllview = (RLScrollView) this.findViewById(R.id.chooseClsfyScroll);
		classifyList = (NoScrollExpandableListView) this
				.findViewById(R.id.chooseClassifyList);
		initReflesh(classifyReflesh, scorllview);
		initAdapter();
	}

	private void initAdapter() {
		classifyAdapter = new CommonExpandableAdapter<TopClassify, Classify>(
				this, R.layout.item_choosgoodsclassify_p,
				R.layout.item_choosgoodsclassify_c) {

			@Override
			public List<Classify> getChildDatas(TopClassify per) {
				return per.getChildList();
			}

			@Override
			public void convertchiView(ViewHolderHelper helper, Classify item) {
				helper.setText(R.id.item_choosegoodsClassify_c_Name,
						item.getClassifyName());
			}

			@Override
			public void convertPerView(ViewHolderHelper helper, TopClassify item) {
				helper.setText(R.id.item_choosegoodsClassify_p_Name,
						item.getClassifyName());
			}
		};
		classifyList.setAdapter(classifyAdapter);
	}

	private void getClassifies() {

	}

	private void setClassifies(List<TopClassify> cls) {
		classifyAdapter.resetData(cls);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		classifyReflesh.setRefreshing(false);
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

	@Override
	protected void reflesh() {
		// TODO Auto-generated method stub

	}
}
