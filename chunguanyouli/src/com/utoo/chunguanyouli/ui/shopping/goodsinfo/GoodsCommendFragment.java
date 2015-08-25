package com.utoo.chunguanyouli.ui.shopping.goodsinfo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.dbentity.CgReplyId;
import com.utoo.chunguanyouli.entity.Commend;
import com.utoo.chunguanyouli.entity.User;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;

public class GoodsCommendFragment extends NetDataBaseFragment {
	Activity context;
	View pView;
	GoodsCommendHelper gch;
public static	final int TAG_MORE = 1;
public static		final int TAG_REFLESH = 2;
	int sortBy;
	int goodsId;

	public static GoodsCommendFragment getInstance(int searchType, int goodsId) {
		GoodsCommendFragment instance = new GoodsCommendFragment();
		instance.goodsId = goodsId;
		instance.sortBy = searchType;
		return instance;
	}

	// public GoodsCommendFragment(int searchType, int goodsId) {
	// super();
	// this.goodsId = goodsId;
	// this.sortBy = searchType;
	// }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		pView = inflater.inflate(R.layout.fragment_commend, container, false);
		gch = new GoodsCommendHelper(context, pView) {

			@Override
			protected void reflesh() {
				apply(API_F.getGoodsReply(goodsId, 1, TAG_REFLESH));
			}

			@Override
			protected void more(int pageIndex) {
				apply(API_F.getGoodsReply(goodsId, pageIndex, TAG_MORE));
			}
		};
		// ////////
		// gch.resetDatas(getTestCommend());
		apply(API_F.getGoodsReply(goodsId, 1, TAG_MORE));
		// /////////
		return pView;
	}

	private List<Commend> getTestCommend() {
		List<Commend> coms = new ArrayList<Commend>();
		Commend com = new Commend();
		com.setCommendId(1);
		com.setCommendTime(System.currentTimeMillis());
		com.setContext("测试评论");
		User uer = new User();
		uer.setHeadUrl(ClientConfigs.PICHOST);
		uer.setNickName("测试评论人");
		uer.setUserId(2);
		com.setCommender(uer);
		coms.add(com);
		coms.add(com);
		coms.add(com);
		coms.add(com);
		coms.add(com);
		coms.add(com);
		return coms;
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		CgResponse cr = new CgResponse(response);
		if (cr.getState() == 0) {
			return;
		}
		Gson gson = new Gson();
		List<CgReplyId> orderList = gson.fromJson(cr.getValStr(),
				new TypeToken<List<CgReplyId>>() {
				}.getType());
		gch.setData(TAG, orderList);
	}

}
