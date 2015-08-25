package com.utoo.chunguanyouli.ui.personal.cg.dianpu;

/**
 * 免费开店
 */
import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.ccy.photoalbum.PhotoAlbumActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.MyApplication;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgStoreInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.views.APPManager;
import com.utoo.chunguanyouli.views.ActionSheet;
import com.utoo.chunguanyouli.views.ActionSheet.ActionSheetListener;

@ContentView(R.layout.activity_openshop)
public class UpdateShopActivity extends NetDataBaseActionBarActivity implements
		OnClickListener, ActionSheetListener {
	private static final int TAG_UPDATESTOREINFO = 1;
	private static final int TAG_UPLOAD = 0;
	private Picasso picasso;
	MyApplication app;
	CgStoreInfoId store;
	String dirPath;
	String fileName;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setNeedLogin(true);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		app = (MyApplication) getApplication();
		store = (CgStoreInfoId) getIntent().getSerializableExtra("store");
		ViewUtils.inject(this);
		findViews();
		picasso = Picasso.with(this);
		dirPath = getExternalCacheDir() + "/cgyl/";
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
		}
		picasso = Picasso.with(this);
		initToolbar("修改店铺信息");
	}

	private void updateStoreInfo() {
		store.setName(openShopName.getText().toString().trim());
		store.setDomain(openShopDes.getText().toString().trim());
		apply(API_F.updateStoreInfo(store, userNeed.getId(), userNeed.getSid(),
				TAG_UPDATESTOREINFO));
	}

	private void updateStorePic() {
		apply(API_F.updateStoreInfo(store, userNeed.getId(), userNeed.getSid(),
				TAG_UPDATESTOREINFO));
	}

	private com.views.CircleImageView openShopImg;
	private EditText openShopName;
	private EditText openShopDes;
	private Button openShopSubmit;

	private void findViews() {
		openShopImg = (com.views.CircleImageView) findViewById(R.id.openShopImg);
		openShopName = (EditText) findViewById(R.id.openShopName);
		openShopDes = (EditText) findViewById(R.id.openShopDes);
		openShopSubmit = (Button) findViewById(R.id.openShopSubmit);
		openShopImg.setOnClickListener(this);
		openShopSubmit.setOnClickListener(this);
		initViewText();

		setTheme(R.style.ActionSheetStyleIOS7);
	}

	private void initViewText() {
		Log.e("", openShopImg.toString());
		Log.e("", store.toString());
		// NetImgLoader.loadImage(store.getPic(), openShopImg,
		// R.drawable.index);
		Picasso.with(this).load(store.getPic()).error(R.drawable.index)
				.into(openShopImg);
		// app.setImageByUrl(openShopImg, store.getPic(), R.drawable.index);
		openShopDes.setText(store.getDomain());
		openShopName.setText(store.getName());
	}

	@Override
	public void onClick(View v) {
		if (v == openShopSubmit) {
			// Handle clicks for openShopSubmit
			updateStoreInfo();
		} else if (v == openShopImg) {
			ActionSheet.createBuilder(this, getSupportFragmentManager())
					.setOtherButtonTitles("拍照", "从相册选择")
					.setCancelButtonTitle("取消")
					.setCancelableOnTouchOutside(true).setListener(this).show();
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
					uploadImage(userNeed.getId(), file.getAbsolutePath(),
							TAG_UPLOAD);
				} else {
					Toast.makeText(this, "拍照失败", 0).show();
				}
				break;

			case 500:// 相册返回
				// String path = APPManager.getGalleryPath(this, data);
				String path = data.getStringExtra("path");
				Log.e("相册", path);
				if (path != null) {
					Log.e("", path);
					uploadImage(userNeed.getId(), path, TAG_UPLOAD);
					// store.setPic();
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOtherButtonClick(ActionSheet actionSheet, int index) {
		switch (index) {
		case 0:// 拍照
			fileName = System.currentTimeMillis() + ".jpg";
			APPManager.openCamera(this, dirPath + fileName);
			break;

		case 1:// 从相册选择
				// APPManager.openGallery(this);
			Intent intent = new Intent(this, PhotoAlbumActivity.class);
			startActivityForResult(intent, 500);
			break;

		default:
			break;
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {

		if (TAG == TAG_UPDATESTOREINFO) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt(response);
				if (state == 1) {
					Toast.makeText(this, "修改成功", 0).show();
					initViewText();
				}
				finish();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (TAG == TAG_UPLOAD) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0)
					return;
				else {
					String path = jo.optString("path");
					store.setPic(path);
					// updateStorePic();
					// addGoods();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
