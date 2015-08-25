package com.utoo.chunguanyouli.ui.main.mainpage;

/**
 * 主页
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.ccy.photoalbum.PhotoAlbumActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.utoo.chunguanyouli.MySharePerference;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.account.LoginActivity;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.main.PersonalFg;
import com.utoo.chunguanyouli.ui.main.mainpage.recommended.RecommendedFragment;
import com.utoo.chunguanyouli.ui.main.mainpage.required.DailyRequiredFragment;
import com.utoo.chunguanyouli.ui.main.mainpage.rural.RuralFragment;
import com.utoo.chunguanyouli.views.APPManager;
import com.utoo.chunguanyouli.views.ActionSheet;
import com.utoo.chunguanyouli.views.MyToast;
import com.utoo.chunguanyouli.views.ActionSheet.ActionSheetListener;
import com.utoo.gson.GsonHelper;

@ContentView(R.layout.activity_main)
public class MainActivity extends NetDataBaseActionBarActivity implements
		ActionSheetListener, OnRefreshListener2<ScrollView> {
	private static final int TAG_HEAD = 2;
	private static final int TAG_LOGIN = 0;
	String dirPath;
	String fileName;
	private PersonalFg pf;
	int radioPos = 1;
	FragmentManager fragmentManager;
	private List<Fragment> fragments;
	// private int previousPos = 0;// fragment上一次的位置
	QuanMallFragment qf;
	RuralFragment rlf;
	RecommendedFragment rf;
	DailyRequiredFragment drf;

	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@ViewInject(R.id.mainScrollView)
	public PullToRefreshScrollView scrollView;
	// RadioGroup
	// @ViewInject(R.id.group)
	// private RadioGroup group;

	// @ViewInject(R.id.main_adv)
	// private ImageView adv;

	@ViewInject(R.id.helpImageView)
	private ImageView helpImageView;
	// ViewPager
	// @ViewInject(R.id.viewPager)
	// private ViewPager pager;

	// 抽屉布局
	@ViewInject(R.id.drawerLayout)
	private DrawerLayout drawerLayout;

	@ViewInject(R.id.mainRadioGroup)
	private RadioGroup group;

	// 抽屉
	@ViewInject(R.id.ll)
	private LinearLayout ll;

	@OnRadioGroupCheckedChange(R.id.mainRadioGroup)
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		hiddingAll();
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		switch (checkedId) {
		case R.id.mainRadioButton1:// 圈商城
			if (fragments.get(0) == null) {
				qf = new QuanMallFragment();
			}
			transaction.show(fragments.get(0));
			radioPos = 1;
			break;

		case R.id.mainRadioButton2:// 美丽家乡
			if (fragments.get(1) == null) {
				rlf = new RuralFragment();
			}
			transaction.show(fragments.get(1));
			checkFistInCatg();
			radioPos = 2;
			break;

		case R.id.mainRadioButton3:
			if (fragments.get(2) == null) {
				rf = new RecommendedFragment();
			}
			transaction.show(fragments.get(2));
			radioPos = 3;
			break;

		case R.id.mainRadioButton4:
			if (fragments.get(3) == null) {
				drf = new DailyRequiredFragment();
			}
			transaction.show(fragments.get(3));
			radioPos = 4;
			break;

		default:
			break;
		}
		transaction.commit();
	}

	private void hiddingAll() {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		for (Fragment fg : fragments) {
			transaction.hide(fg);
		}
		transaction.commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initLogin();
		fragmentManager = this.getSupportFragmentManager();
		initViews();
		checkFistInMain();
		initFragment();
		// onCheckedChanged(group, R.id.raduiButton1);

		setTheme(R.style.ActionSheetStyleIOS7);

		drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// if (drawerLayout.isDrawerOpen(ll)) {
		// drawerLayout.closeDrawers();
		// }
		if (pf != null) {
			pf.setUserInfo();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_page, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.servicephone:
			Intent intentPhone = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:" + app.servicePhone));

			startActivity(intentPhone);

			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initLogin() {
		MySharePerference msp = new MySharePerference(this);
		String userName = msp.getUserName();
		String password = msp.getPassword();
		if (!userName.equals("") || !password.equals("")) {
			apply(API_F.getLoginRequest(userName, password, TAG_LOGIN));
		}

	}

	private void checkFistInMain() {
		final MySharePerference sharedata = new MySharePerference(this);
		Boolean isSoupon = sharedata.IsFirstInMain();
		if (isSoupon) {
			helpImageView.setVisibility(View.VISIBLE);
			helpImageView.setImageResource(R.drawable.learn1);
		} else {
			helpImageView.setVisibility(View.GONE);
		}
		helpImageView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				helpImageView.setVisibility(View.GONE);
				sharedata.setFirstInMainFalse();
			}
		});
	}

	private void checkFistInCatg() {
		final MySharePerference sharedata = new MySharePerference(this);
		Boolean isSoupon = sharedata.IsFirstInCatg();
		if (isSoupon) {
			helpImageView.setImageResource(R.drawable.learn2);
			helpImageView.setVisibility(View.VISIBLE);
		} else {
			helpImageView.setVisibility(View.GONE);
		}
		helpImageView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				helpImageView.setVisibility(View.GONE);
				sharedata.setFirstInCatgFalse();
			}
		});
	}

	private void initViews() {

		toolbar.setTitle("村官有礼");
		toolbar.setTitleTextColor(getResources()
				.getColor(android.R.color.white));
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.index);
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (drawerLayout.isDrawerOpen(ll)) {
					drawerLayout.closeDrawers();
				} else {
					if (getUser1() != null) {
						if (pf == null) {
							pf = new PersonalFg(MainActivity.this) {

								@Override
								public void headClick() {
									fileName = System.currentTimeMillis()
											+ ".jpg";
									ActionSheet
											.createBuilder(
													MainActivity.this,
													MainActivity.this
															.getSupportFragmentManager())
											.setOtherButtonTitles("拍照", "从相册选择")
											.setCancelButtonTitle("取消")
											.setCancelableOnTouchOutside(true)
											.setListener(MainActivity.this)
											.show();
								}
							};
						}
						drawerLayout.openDrawer(ll);
					} else {
						Intent intent = new Intent();
						intent.setClass(MainActivity.this, LoginActivity.class);
						MainActivity.this.startActivity(intent);
					}

				}
			}
		});
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
				drawerLayout, R.string.open, R.string.close);
		mDrawerToggle.syncState();
		drawerLayout.setDrawerListener(mDrawerToggle);
		scrollView.setMode(Mode.PULL_FROM_START);
		scrollView.setOnRefreshListener(this);
	}

	/**
	 * 初始化fragment
	 */
	private void initFragment() {
		qf = new QuanMallFragment();
		rlf = new RuralFragment();
		rf = new RecommendedFragment();
		drf = new DailyRequiredFragment();

		fragments = new ArrayList<Fragment>();

		fragments.add(qf);
		fragments.add(rlf);
		fragments.add(rf);
		fragments.add(drf);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		for (int i = 0; i < fragments.size(); i++) {
			Fragment fragment = fragments.get(i);
			transaction.add(R.id.mainLayout1, fragment);
			if (i == 0) {
				transaction.show(fragment);
			} else {
				transaction.hide(fragment);
			}
		}
		transaction.commit();
	}

	// /**
	// * 切换fragment
	// *
	// * @param index
	// * 当前位置
	// */
	// private void switchFragment(int index) {
	// if (previousPos != index) {
	// FragmentTransaction transaction = getSupportFragmentManager()
	// .beginTransaction();
	// transaction.hide(fragments.get(previousPos));
	// Fragment fragment = fragments.get(index);
	// if (!fragment.isAdded()) {
	// transaction.add(R.id.mainLayout1, fragment);
	// }
	// transaction.show(fragment);
	// transaction.commit();
	// previousPos = index;
	// }
	// }

	// /////////////////////按两次退出程序////////////////////
	private long mExitTime = 0;

	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - mExitTime) > 2000) {
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();

		} else {
			finish();

		}
	}

	@Override
	public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

	}

	@Override
	public void onOtherButtonClick(ActionSheet actionSheet, int index) {
		switch (index) {
		case 0:// 拍照
			APPManager.openCamera(this, dirPath + fileName);
			break;

		case 1:// 重相册选择
			// APPManager.openGallery(this);
			Intent intent = new Intent(this, PhotoAlbumActivity.class);
			startActivityForResult(intent, 500);
			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case APPManager.CAMERA:// 拍照返回
				Log.e("相机", "");
				File file = new File(dirPath + fileName);
				if (file.exists()) {
					upload(dirPath + fileName, TAG_HEAD);
				} else {
					Toast.makeText(this, "拍照失败", Toast.LENGTH_SHORT).show();
				}
				break;

			case 500:// 相册返回
				// String path = APPManager.getGalleryPath(this, data);
				String path = data.getStringExtra("path");
//				MyToast.makeText(getApplicationContext(), path);
				Log.e("相册", path);
				if (path != null) {
					upload(path, TAG_HEAD);

				}
				break;
			default:
				break;
			}
		}
	}

	private void upload(String filePath, int tag) {
		if (getUser1() == null) {
			return;
		}
		uploadImage(getUser1().getId(), filePath, tag);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
	}

	String path;

	@Override
	protected void onReturned(String response, int TAG) {
		if (TAG == 1) {
			// 修改用户名
			JSONObject jo = null;
			try {
				jo = new JSONObject(response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int state = jo.optInt("state");
			if (state == 1) {
				Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
				pf.setNewUserName();
				if (path != null) {
					pf.setHead(path);
				}
			}
		} else if (TAG == TAG_HEAD) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0)
					return;
				else {
					path = jo.optString("path");
					if (getUser1() != null) {
						getUser1().setPhoto(path);
						apply(API_F.updatePersonal(getUser1(), 1));
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_LOGIN) {
			try {
				JSONObject json = new JSONObject(response);
				String sid = json.getString("sid");
				int sum = json.getInt("num");
				if (sum == 0) {
					Toast.makeText(this, "登陆验证失败", 0).show();
					return;
				}
				JSONArray data = json.optJSONArray("val");

				JSONObject userjson = data.optJSONObject(0);
				UUserInfoId user = GsonHelper.getBean(userjson.toString(),
						UUserInfoId.class);
				user.setSid(sid);
				app.user = user;
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
		if (radioPos == 1) {
			qf.refleshQuan();
		} else if (radioPos == 2) {
			rlf.refleshRural();
		} else if (radioPos == 3) {
			rf.refleshRecommend();
		} else if (radioPos == 4) {
			drf.refleshDailyRequired();
		}
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// TODO Auto-generated method stub

	}

}
