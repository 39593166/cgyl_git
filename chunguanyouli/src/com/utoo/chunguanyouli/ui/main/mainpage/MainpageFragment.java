package com.utoo.chunguanyouli.ui.main.mainpage;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.ui.base.RefleshableFragment;
import com.utoo.chunguanyouli.ui.main.mainpage.helper.GongGaoAdFt;

public class MainpageFragment extends RefleshableFragment {
	MainActivity context;
	View pView;
	GongGaoAdFt ggaf;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		pView = inflater.inflate(R.layout.fragment_mainpage, container, false);
		ggaf = new GongGaoAdFt(context, pView);
		return pView;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		if (!hidden) {
			// context.setActionBarView(context, "村官有礼", false);
		}
		super.onHiddenChanged(hidden);
	}

	@Override
	protected void reflesh() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub

	}
}
