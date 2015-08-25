package com.utoo.chunguanyouli.ui;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.android.volley.VolleyError;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.R.id;
import com.utoo.chunguanyouli.R.layout;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends NetDataBaseActionBarActivity {

	public static final int GET_VERSION_INFO = 1;
	public static final int GET_DOWNLOAD_URL = 2;

//	private File download;

	@OnClick(R.id.update)
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.update:// 更新
			apply(API_C.update(GET_VERSION_INFO));
			apply(API_C.getDownloadUrl(GET_DOWNLOAD_URL));
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
//			download = Environment.getExternalStorageDirectory();
//			download = new File(download, "/cgyl");
//			if (!download.exists()) {
//				download.mkdirs();
//			}
//		}

	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		switch (TAG) {
		case GET_VERSION_INFO:
			LogUtils.e("检查更新：" + response);
			break;

		case GET_DOWNLOAD_URL:
			LogUtils.e("下载：" + response);
			break;

		default:
			break;
		}

	}

	private void startDownload(String url) {
		url="http://121.42.138.126:82/cg/index.aspx?mod=d&t=a";
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.download(url, app.catchDirPath + "/cgyl.apk", true, false,
				new RequestCallBack<File>() {
					@Override
					public void onSuccess(ResponseInfo<File> responseInfo) {
						// TODO Auto-generated method stub
						LogUtils.e("下载成功");
						String fileName = Environment.getExternalStorageDirectory() + "/"
								+ "cgyl/cgyl.apk";
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setDataAndType(Uri.fromFile(new File(fileName)),
								"application/vnd.android.package-archive");
						startActivity(intent);
					}
					
					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						// TODO Auto-generated method stub
						super.onLoading(total, current, isUploading);
					}

					@Override
					public void onFailure(HttpException exception, String string) {
						// TODO Auto-generated method stub
						LogUtils.e("下载失败");
					}
				});
	}
}
