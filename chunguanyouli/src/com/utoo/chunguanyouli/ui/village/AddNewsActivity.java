package com.utoo.chunguanyouli.ui.village;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.views.APPManager;
import com.utoo.chunguanyouli.views.MyToast;

@ContentView(R.layout.activity_addnews)
public class AddNewsActivity extends NetDataBaseActionBarActivity {

	private int tid;

	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@ViewInject(R.id.title)
	private EditText titleEditText;

	@ViewInject(R.id.content)
	private EditText contentEditText;

	@ViewInject(R.id.icon)
	private ImageView icon;

	@ViewInject(R.id.group)
	private RadioGroup group;

	@ViewInject(R.id.addNewsType)
	private Spinner type;
	private String pic = "";
	private String title;
	String content;
	String path;

	@OnClick({ R.id.commit, R.id.icon })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commit:

			title = titleEditText.getText().toString().trim();
			content = contentEditText.getText().toString().trim();

			if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
				MyToast.makeText(this, "您还有未输入的信息");
				return;
			}

			// if (TextUtils.isEmpty(path)) {
			// MyToast.makeText(this, "您还未上传图片");
			// return;
			// }
			if (userNeed == null)
				return;
			if (path != null) {
				uploadImage(userNeed.getId(), path, 0);
			} else {
				commit();
			}
			break;

		case R.id.icon:
			APPManager.openGallery(this);
			break;

		default:
			break;
		}
	}

	private void commit() {
		if (userNeed == null)
			return;
		// uploadImage(userNeed.getId(), path, 0);

		// for (int i = 0; i < group.getChildCount(); i++) {
		// if (((RadioButton) group.getChildAt(i)).isChecked()) {
		// tid = i + 1;
		// }
		// }
		tid = type.getSelectedItemPosition() + 1;

		CgNewsId article = new CgNewsId();
		article.setId(0);
		article.setTitle(title);
		article.setContent(content);
		article.setDatesend(DateTool.getTimeStr(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));
		article.setPic(pic);
		article.setUid(userNeed.getId());
		article.setCid(userNeed.getCid());
		article.setTid(tid);

		apply(API_C.postNews(article, userNeed.getId(), userNeed.getSid(), 1));
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if (arg1 == RESULT_OK) {
			if (arg0 == APPManager.GALLERY) {
				path = APPManager.getGalleryPath(this, arg2);

				// uploadImage(userNeed.getId(), path, 0);
				Picasso.with(this).load(new File(path)).resize(400, 360)
						.into(icon);

			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar("发表");
//		String[] array = getResources().getStringArray(R.array.newstype);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,array);
//		type.setAdapter(adapter)
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		if (TAG == 0) {
			Toast.makeText(this, "图片上传失败", 0).show();
		}
	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		if (TAG == 0) {// 0上传图片
			Toast.makeText(this, "图片已上传", 0).show();
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0)
					return;
				else {
					pic = jo.optString("path");

					commit();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (TAG == 1) {
			finish();
		}
	}

}
