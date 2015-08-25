package com.utoo.chunguanyouli.ui;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.ObjectAnimator;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.R.id;
import com.utoo.chunguanyouli.R.layout;
import com.utoo.chunguanyouli.bean.VersionDataInfoBean;
import com.utoo.chunguanyouli.bean.VersionInfo;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.main.mainpage.MainActivity;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends NetDataBaseActionBarActivity {

	private static final int TAG_GETVERSION_INFO = 0;
	@ViewInject(R.id.welcome_icon)
	private ImageView welcome;

	@ViewInject(R.id.downloadProgressBar)
	private ProgressBar downloadProgressbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		init();
		apply(API_C.update(TAG_GETVERSION_INFO));
	}

	private void init() {

		ObjectAnimator animator = ObjectAnimator.ofFloat(welcome, "alpha", 1);
		animator.setDuration(3000).start();

	}

	private void goNext(long time) {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WelcomeActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, time);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "网络连接失败", 0).show();

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		VersionDataInfoBean bean = gson.fromJson(response,
				VersionDataInfoBean.class);
		VersionInfo info = bean.getVersionInfo();
		checkVersion(info);
	}

	private void checkVersion(VersionInfo info) {
		PackageInfo pinfo = null;
		try {
			pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		int versionCode = pinfo.versionCode; //
		String versionName = pinfo.versionName; // 1.0
		if (versionCode < info.getMin()) {
			showDialog(this, "发现新版本", "您的版本已无法使用，是否立即更新",
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							startDownload(null);
						}
					});
		} else if (versionCode < info.getNow()) {
			showDialog(this, "发现新版本", "有可用的新版本，是否下载体验？",
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							startDownload(null);
							goNext(1000);
						}
					}, new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							goNext(1000);
						}
					});
		} else {
			goNext(2000);
		}
	}

	private void startDownload(String url) {
		downloadProgressbar.setVisibility(View.VISIBLE);
		url = "http://121.42.138.126:82/cg/index.aspx?mod=d&t=a";
		HttpUtils httpUtils = new HttpUtils();
		final String fileName =  app.catchDirPath + "/cgyl.apk";
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
		httpUtils.download(url,file.getAbsolutePath(), true, false,
				new RequestCallBack<File>() {
					@Override
					public void onSuccess(ResponseInfo<File> responseInfo) {
						LogUtils.e("下载成功");
						
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setDataAndType(Uri.fromFile(new File(fileName)),
								"application/vnd.android.package-archive");
						startActivity(intent);
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						super.onLoading(total, current, isUploading);
						downloadProgressbar
								.setProgress((int) (current / total));
					}

					@Override
					public void onFailure(HttpException exception, String string) {
						LogUtils.e("下载失败");
					Log.e("",
								"下载失败，请保持网络连接通畅"+exception.getMessage()+"!set!"+string);
						Toast.makeText(
								WelcomeActivity.this.getApplicationContext(),
								"下载失败，请保持网络连接通畅", 0).show();
					}
				});
	}
}
