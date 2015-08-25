package com.utoo.chunguanyouli.ui.main.mainpage.rural;

/**
 * 乡村之巅
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.utoo.chunguanyouli.MySharePerference;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.HomeInfo;
import com.utoo.chunguanyouli.info.RuralInfo;
import com.utoo.chunguanyouli.info.RuralInfo.Val;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.main.VillageDropsActivity;
import com.utoo.chunguanyouli.ui.main.mainpage.MainActivity;
import com.utoo.chunguanyouli.ui.main.mainpage.rural.adapter.RuralAdapter;
import com.utoo.chunguanyouli.ui.village.VillagesCategoryActivity;
import com.utoo.chunguanyouli.ui.village.VillagesIntroduceActivity;
import com.utoo.chunguanyouli.ui.villageofficial.adapter.VillageOfficialGridViewAdapter;
import com.views.NoScrollGridView;
import com.views.NoScrollListView;

public class RuralFragment extends NetDataBaseFragment {

	private MainActivity act;
	private Gson gson = new Gson();
	private List<Val> list = new ArrayList<RuralInfo.Val>();
	private RuralAdapter adapter;
	private Intent intent = new Intent();
	// private VillageOfficialAdapter adapter;
	private String[] counts = new String[2];
	private VillageOfficialGridViewAdapter gridViewAdapter;

	// NoScrollGridView
	@ViewInject(R.id.gridView)
	private NoScrollGridView gridView;

	// 信息
	// @ViewInject(R.id.xinxi)
	// private TextView xinxiTextView;

	// // NoScrollGridView
	// @ViewInject(R.id.gridView2)
	// private NoScrollGridView gridView2;

	// NoScrollListView
	@ViewInject(R.id.listView)
	private NoScrollListView listView;

	

	// Item点击事件
	@OnItemClick({ R.id.listView, R.id.gridView })
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (parent.getId()) {
		case R.id.listView:
			Val info = (Val) adapter.getItem(position);
			// MyToast.makeText(context, "ID：" + info.getId());

			intent.setClass(act, VillagesIntroduceActivity.class);
			intent.putExtra("id_a", Integer.parseInt(info.getId()));
			startActivity(intent);
			break;

		case R.id.gridView:
			switch (position) {
			case 0:// 公告
				intent.setClass(act, VillageDropsActivity.class);
				startActivity(intent);
				break;

			case 1:// 走进南川
					// intent.setClass(context, WalkIntoNanChuanActivity.class);
				intent.setClass(getActivity(), VillagesCategoryActivity.class);
				startActivity(intent);
				break;

			case 2:// 村官点滴

				break;

			case 3:// 慈善家

				break;

			default:
				break;
			}
			break;

		// case R.id.gridView2:
		// switch (position) {
		// case 0:// 走进南川
		// intent.setClass(context, WalkIntoNanChuanActivity.class);
		// startActivity(intent);
		// break;
		//
		// case 1:// 村官点滴
		// intent.setClass(context, VillageDropsActivity.class);
		// startActivity(intent);
		// break;
		//
		// case 2:// 我要推荐
		// intent.setClass(context, ProductCategoriesActivity.class);
		// startActivity(intent);
		// break;
		//
		// default:
		// break;
		// }
		// break;

		default:
			break;
		}
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
		View view = inflater.inflate(R.layout.fragment_rural, container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		// 初始化
		int[] imageRes = { R.drawable.gonggao, R.drawable.xiangcun,
				R.drawable.cunguan };
		counts[0] = "0";
		counts[1] = "0";
		// counts[2] = "0";
		gridViewAdapter = new VillageOfficialGridViewAdapter(act, imageRes,
				counts);
		gridView.setAdapter(gridViewAdapter);

		// int[] imageRes2 = { R.drawable.zoujinnanchuan,
		// R.drawable.cunguandiandi };
		// VillageOfficialGridViewAdapter gridViewAdapter2 = new
		// VillageOfficialGridViewAdapter(
		// context, imageRes2, null);
		// gridView2.setAdapter(gridViewAdapter2);

		adapter = new RuralAdapter(act, list);
		listView.setAdapter(adapter);

		// 请求网络
		apply(API_C.getRural(0));
		apply(API_C.getHome(1));
	}

	/**
	 * call from outsid
	 */
	public void refleshRural() {
		apply(API_C.getRural(0));
		apply(API_C.getHome(1));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		act.scrollView.onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		act.scrollView.onRefreshComplete();
		// TODO Auto-generated method stub
		LogUtils.e("乡村之巅：" + response);
		if (TAG == 0) {
			RuralInfo info = gson.fromJson(response, RuralInfo.class);
			if (info.getState().equals("1")) {
				List<Val> list = info.getVals();

				this.list.clear();
				this.list.addAll(list);
				adapter.notifyDataSetChanged();
			}
		} else if (TAG == 1) {
			HomeInfo info2 = gson.fromJson(response, HomeInfo.class);
			if (info2.getState().equals("1")) {
				List<com.utoo.chunguanyouli.info.HomeInfo.Val> list2 = info2
						.getVal();
				if (!list2.isEmpty()) {
					com.utoo.chunguanyouli.info.HomeInfo.Val val = list2.get(0);

					counts[0] = val.getNum1();
					counts[1] = val.getNum2();
					// counts[2] = val.getNum3();
					gridViewAdapter.notifyDataSetChanged();
				}
			}
		}
	}

}
