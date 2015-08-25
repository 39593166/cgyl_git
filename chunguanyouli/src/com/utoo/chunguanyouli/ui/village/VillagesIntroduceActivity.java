package com.utoo.chunguanyouli.ui.village;

/**
 * 本镇介绍
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.MySharePerference;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgCityId;
import com.utoo.chunguanyouli.dbentity.CgCollectId;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.village.villagefragment.AboutVillagersFragment;
import com.utoo.chunguanyouli.ui.village.villagefragment.FeatureProductFragment;

@ContentView(R.layout.activity_villagesintroduce)
public class VillagesIntroduceActivity extends NetDataBaseActionBarActivity {

	private static final int TAG_COLLECT = 156;
	private static final int TAG_GETINFO = 0;
	private static final int TAG_User = 15;
	// 参数
	public static int id;
	private String cityArea;

	private List<Fragment> fragments = new ArrayList<Fragment>();
	private AboutVillagersFragment avf = new AboutVillagersFragment();
	// private VillagersPeopleFragment vpf = new VillagersPeopleFragment();
	private FeatureProductFragment fpf = new FeatureProductFragment();
	private Intent intent = new Intent();

	private Gson gson = new Gson();
	private Picasso picasso;
	private CgCityId city;

	@ViewInject(R.id.helpImageView)
	private ImageView helpImageView;

	// RadioGroup
	@ViewInject(R.id.radioGroup)
	private RadioGroup group;

	@ViewInject(R.id.content)
	private TextView contentTextView;

	// Toolbar
	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@ViewInject(R.id.ll)
	private LinearLayout ll;

	@ViewInject(R.id.area)
	private TextView areaTextView;

	@ViewInject(R.id.sheng)
	private Button sheng;

	@OnClick(R.id.sheng)
	public void onCLick(View v) {
		switch (v.getId()) {
		case R.id.sheng:
			intent.setClass(this, NewsForVillageActivity.class);
			intent.putExtra("cid", id);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		id = getIntent().getIntExtra("id_a", 0);
		cityArea = getIntent().getStringExtra("city");

		picasso = Picasso.with(this);

		initToolbar("本村介绍");
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		fragments.add(avf);
		fragments.add(fpf);

		for (int i = 0; i < fragments.size(); i++) {
			transaction.add(R.id.linearLayout, fragments.get(i));
		}
		transaction.show(avf).hide(fpf);
		transaction.commitAllowingStateLoss();

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioButton1:
					contentTextView.setVisibility(View.GONE);
					switchFragment(0, false);
					break;

				case R.id.radioButton3:
					if (city != null) {
						contentTextView.setText(String.format(
								"本镇成员共有村名%s人，合计%s人，村官%s人", city.getNumpro(),
								city.getNumhu(), city.getNumorder()));
					}
					switchFragment(1, false);
					break;

				default:
					break;
				}
			}
		});

		apply(API_C.getAboutVillagers(TAG_GETINFO, id));

	}

	private void checkFistInVill() {
		final MySharePerference sharedata = new MySharePerference(this);
		Boolean isSoupon = sharedata.IsFirstInVill();
		if (isSoupon) {
			helpImageView.setVisibility(View.VISIBLE);
			helpImageView.setImageResource(R.drawable.learn3);
		} else {
			helpImageView.setVisibility(View.GONE);
		}
		helpImageView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				helpImageView.setVisibility(View.GONE);
				sharedata.setFirstInVillFalse();
			}
		});
	}

	private void switchFragment(int index, boolean isAnimator) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		if (isAnimator) {
			transaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		}
		for (int i = 0; i < fragments.size(); i++) {
			if (index == i) {
				transaction.show(fragments.get(index));
			} else {
				transaction.hide(fragments.get(i));
			}
		}
		transaction.commit();
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {

		LogUtils.e("本镇介绍 tag" + TAG + response);

		if (TAG == TAG_GETINFO) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<CgCityId> goodsList = null;
			Gson gson = new Gson();

			goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgCityId>>() {
					}.getType());
			if (goodsList != null && goodsList.size() > 0) {
				this.city = goodsList.get(0);
				avf.setCityInfo(city);
			}

			checkFistInVill();
		} else if (TAG == TAG_COLLECT) {

			try {
				JSONObject json = new JSONObject(response);
				int state = json.optInt("state");
				if (state == 1) {
					Toast.makeText(this, "收藏成功", 0).show();
				} else {
					Toast.makeText(this, "收藏失败", 0).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} else if (TAG == TAG_User) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<UUserInfoId> leader = null;
			Gson gson = new Gson();

			leader = gson.fromJson(cr.getValStr(),
					new TypeToken<List<UUserInfoId>>() {
					}.getType());
			// if (leader != null && leader.size() > 0) {
			// UUserInfoId lead = leader.get(0);
			// nameTextView.setText(lead.getRealname());
			// Picasso.with(this).load(lead.getPhoto())
			// .error(R.drawable.laucher).into(icon2);
			// }
		}
	}

	/**
	 * 调用这个方法收藏村子 收藏村子
	 */
	private void collect(int cid) {
		if (getUser1() == null) {
			goLogin();
			return;
		}
		CgCollectId coll = new CgCollectId(0, getUser1().getId(),
				DateTool.getTimeStr(System.currentTimeMillis(),
						"yyyy-MM-dd HH:mm:ss"), cid, 1);
		apply(API_F.collect(coll, getUser1().getSid(), TAG_COLLECT), false);
	}

	public void collectClick(View v) {
		collect(id);
	}
}
