package com.utoo.chunguanyouli.ui.personal.cg.collage;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.ccy.photoalbum.PhotoAlbumActivity;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.views.APPManager;
import com.utoo.chunguanyouli.views.ActionSheet;
import com.utoo.chunguanyouli.views.ActionSheet.ActionSheetListener;

public class ModifyDiandiActivity extends NetDataBaseActionBarActivity
		implements OnClickListener, ActionSheetListener {
	CgNewsId news;
	final int TAG_UPDATEINFO = 1;
	final int TAG_UPLOADIMG = 2;
	String filePath = null;
	String dirPath;
	String fileName;
	UUserInfoId user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_diandi);
		news = (CgNewsId) getIntent().getSerializableExtra("news");
		initToolbar("修改新闻");
		dirPath = getExternalCacheDir() + "/cgyl/";
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
		}
		findViews();
		initDatas();
		user = getUser1();
		if (user == null) {
			goLogin();
		}
	}

	private void upload() {
		if (filePath == null) {
			update(null);
		} else {
			uploadImage(user.getId(), filePath, TAG_UPLOADIMG);
		}
	}

	private void update(String newPath) {
		news.setTitle(udpateNewsTitle.getText().toString());
		news.setContent(udpateNewsContent.getText().toString());
		news.setTid(type.getSelectedItemPosition() + 1);
		if (newPath != null) {
			news.setPic(newPath);
		}
		apply(API_F.updateNews(TAG_UPDATEINFO, news, user.getId(),
				user.getSid()));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {

	}

	@Override
	protected void onReturned(String response, int TAG) {
		if (TAG == TAG_UPLOADIMG) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0) {
					Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
					return;
				} else {
					String pic = jo.optString("path");
					update(pic);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_UPDATEINFO) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0) {
					Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
					return;
				} else {
					Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
					finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	private EditText udpateNewsTitle;
	private EditText udpateNewsContent;
	private ImageView udpateNewsImage;
	private Button updateNewsBack;
	private Button updateNewsSubmit;
	private Spinner type;

	private void findViews() {
		udpateNewsTitle = (EditText) findViewById(R.id.udpateNewsTitle);
		udpateNewsContent = (EditText) findViewById(R.id.udpateNewsContent);
		udpateNewsImage = (ImageView) findViewById(R.id.udpateNewsImage);
		updateNewsBack = (Button) findViewById(R.id.updateNewsBack);
		updateNewsSubmit = (Button) findViewById(R.id.updateNewsSubmit);
		type = (Spinner) findViewById(R.id.udpateNewsType);

		udpateNewsImage.setOnClickListener(this);
		updateNewsBack.setOnClickListener(this);
		updateNewsSubmit.setOnClickListener(this);
	}

	private void initDatas() {
		udpateNewsTitle.setText(news.getTitle());
		udpateNewsContent.setText(news.getContent());
		Picasso.with(this).load(news.getPic())
				.error(R.drawable.index_news_list_small)
				.resizeDimen(R.dimen.image_size_list, R.dimen.image_size_list)
				.into(udpateNewsImage);
		type.setSelection(news.getTid() - 1);
	}

	@Override
	public void onClick(View v) {
		if (v == updateNewsBack) {
			filePath = null;
			Picasso.with(this)
					.load(news.getPic())
					.error(R.drawable.index_news_list_small)
					.resizeDimen(R.dimen.image_size_list,
							R.dimen.image_size_list).into(udpateNewsImage);
		} else if (v == updateNewsSubmit) {
			upload();
		} else if (v == udpateNewsImage) {
			ActionSheet.createBuilder(this, getSupportFragmentManager())
					.setOtherButtonTitles("拍照", "重相册选择")
					.setCancelButtonTitle("取消")
					.setCancelableOnTouchOutside(true).setListener(this).show();
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
					this.filePath = dirPath + fileName;
					Picasso.with(this)
							.load(file)
							.error(R.drawable.index_news_list_small)
							.resizeDimen(R.dimen.image_size_list,
									R.dimen.image_size_list)
							.into(udpateNewsImage);
				} else {
					Toast.makeText(this, "拍照失败", 0).show();
				}
				break;

			case 500:// 相册返回
				// String path = APPManager.getGalleryPath(this, data);
				String path = data.getStringExtra("path");
				Log.e("相册", path);
				if (path != null) {
					this.filePath = path;
					Picasso.with(this)
							.load(new File(path))
							.error(R.drawable.index_news_list_small)
							.resizeDimen(R.dimen.image_size_list,
									R.dimen.image_size_list)
							.into(udpateNewsImage);
				}
				break;
			default:
				break;
			}
		}
	}

}
