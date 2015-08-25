package com.utoo.chunguanyouli.ui.main.mainpage.market;

/**
 * 供求行情
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.main.mainpage.market.adapter.MarketAdapter;
import com.utoo.chunguanyouli.views.MyToast;
import com.views.NoScrollGridView;

public class MarketFragment extends Fragment {

	private FragmentActivity context;

	// NoScrollGridView
	@ViewInject(R.id.gridView)
	private NoScrollGridView gridView;

	// Item点击事件
	@OnItemClick(R.id.gridView)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		MyToast.makeText(context, position + "");
	}

	// 点击事件
	@OnClick({ R.id.gongying, R.id.xuqiu })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gongying:// 发布供应
			MyToast.makeText(context, "发布供应");
			break;

		case R.id.xuqiu:// 发布需求
			MyToast.makeText(context, "发布需求");
			break;

		default:
			break;
		}
	}

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
		View view = inflater.inflate(R.layout.fragment_market, container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		MarketAdapter adapter = new MarketAdapter(context);
		gridView.setAdapter(adapter);
	}
}
