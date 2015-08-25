package com.utoo.chunguanyouli.ui.village.villagefragment;

/**
 * 关于本镇
 */
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgCityId;
import com.utoo.chunguanyouli.dbentity.CgCollectId;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.comm.CityTitleAdapter;
import com.utoo.chunguanyouli.ui.village.VillagesIntroduceActivity;
import com.utoo.chunguanyouli.views.MyToast;
import com.views.CircleImageView;

public class AboutVillagersFragment extends NetDataBaseFragment {

	private static final int TAG_COLLECT = 1;
	private FragmentActivity context;
	private CgCityId city;
	private String cityArea;
	private static final int TAG_User = 15;

	@ViewInject(R.id.jianjie)
	public TextView jianjieTextView;

	@ViewInject(R.id.yinxiang)
	public GridView yinxiangTextView;
	CityTitleAdapter adapter;

	@ViewInject(R.id.sc)
	private ScrollView sc;

	@ViewInject(R.id.icon)
	private ImageView icon;

	@ViewInject(R.id.icon2)
	private CircleImageView icon2;

	@ViewInject(R.id.name)
	private TextView nameTextView;

	@ViewInject(R.id.time)
	private TextView timeTextView;

	@ViewInject(R.id.cityname)
	private TextView citynameTextView;

	@ViewInject(R.id.area)
	private TextView areaTextView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		cityArea = context.getIntent().getStringExtra("city");
	}

	public void setCityInfo(CgCityId city) {

		this.city = city;
		adapter.add(city.getEffect());
		adapter.notifyDataSetChanged();
		jianjieTextView.setText(city.getContent());

		if (city != null && city.getUid() != 0) {
			apply(API_C.getUser(TAG_User, city.getUid()));
			citynameTextView.setText(city.getName() + "");
			areaTextView.setText(cityArea == null ? "" : cityArea);
			timeTextView.setText(String.format("于%s登记了本村", city.getDatesend()
					.substring(0, 8)));
			Picasso.with(getActivity())
					.load(city.getPic())
					.resizeDimen(R.dimen.image_size_list,
							R.dimen.image_size_list).error(R.drawable.index)
					.into(icon);
		}
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_aboutvillagers,
				container, false);
		ViewUtils.inject(this, view);
		adapter = new CityTitleAdapter(getActivity());
		yinxiangTextView.setEnabled(false);
		yinxiangTextView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sc.smoothScrollTo(0, 0);
	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		if (TAG == TAG_User) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<UUserInfoId> leader = null;
			Gson gson = new Gson();

			leader = gson.fromJson(cr.getValStr(),
					new TypeToken<List<UUserInfoId>>() {
					}.getType());
			if (leader != null && leader.size() > 0) {
				UUserInfoId lead = leader.get(0);
				nameTextView.setText(lead.getRealname());
				Picasso.with(getActivity()).load(lead.getPhoto())
						.error(R.drawable.laucher).into(icon2);
			}
		}
	}

}
