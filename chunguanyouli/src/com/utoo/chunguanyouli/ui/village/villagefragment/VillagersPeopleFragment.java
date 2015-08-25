package com.utoo.chunguanyouli.ui.village.villagefragment;

/**
 * 本镇成员
 */
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.VillagersPeopleInfo;
import com.utoo.chunguanyouli.info.VillagersPeopleInfo.Val;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.activity.adapter.VillagersPeopleAdapter;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.village.VillagesIntroduceActivity;

public class VillagersPeopleFragment extends NetDataBaseFragment {

	private FragmentActivity context;
	private Gson gson = new Gson();
	private List<Val> list = new ArrayList<VillagersPeopleInfo.Val>();
	private VillagersPeopleAdapter adapter;

	// GridView
	@ViewInject(R.id.gridView)
	private GridView gridView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_villagerspeople, container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		// 初始化
		adapter = new VillagersPeopleAdapter(context, list);
		gridView.setAdapter(adapter);

		// 请求网络
		apply(API_C.getVillagersPeople(0, VillagesIntroduceActivity.id));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		LogUtils.e("本镇成员" + response);
		VillagersPeopleInfo info = gson.fromJson(response, VillagersPeopleInfo.class);
		if (info.getState().equals("1")) {
			List<Val> list = info.getVal();

			this.list.clear();
			this.list.addAll(list);
			adapter.notifyDataSetChanged();
		}
	}

}
