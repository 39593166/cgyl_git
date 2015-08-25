package com.utoo.chunguanyouli;

import java.io.File;
import java.util.HashMap;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.ui.account.LoginActivity;

public class MyApplication extends Application {
	public HashMap<String, Object> commData;
	public String catchDirPath;
	public BitmapUtils bu = null;
	public final String servicePhone = "02367966271";
	public UUserInfoId user;

	@Override
	public void onCreate() {
		super.onCreate();
		commData = new HashMap<String, Object>();
		catchDirPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/cgyl";
		Log.e("缓存", catchDirPath);
		File file = new File(catchDirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		bu = new BitmapUtils(this, catchDirPath);
//		initImageLoader(this);
	}

	public void putBusinessData(String key, Object value) {
		commData.put(key, value);
	}

	// public Object getBusinessData(String key) {
	// if (key.equals(ClientContext.BUSI_USER)) {
	// if (commData.get(key) == null) {
	// Toast.makeText(this, "登陆已失效", Toast.LENGTH_SHORT).show();
	// }
	// }
	// return commData.get(key);
	// }

	public UUserInfoId getLoginUser() throws LoginOutOfDateException {
		if (user == null) {
			throw new LoginOutOfDateException();
		} else {
			return user;
		}
	}

	public void setImageByUrl(final ImageView container, String url,
			final int failedRecId) {
		bu.display(container, url, new BitmapLoadCallBack<View>() {

			@Override
			public void onLoadCompleted(View arg0, String arg1, Bitmap arg2,
					BitmapDisplayConfig arg3, BitmapLoadFrom arg4) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadFailed(View arg0, String arg1, Drawable arg2) {
				// TODO Auto-generated method stub
				container.setImageResource(failedRecId);
			}
		});
	}

//	private void initImageLoader(Context context) {
//		// This configuration tuning is custom. You can tune every option, you
//		// may tune some of them,
//		// or you can create default configuration by
//		// ImageLoaderConfiguration.createDefault(this);
//		// method.
//		String AppFile = Environment.getExternalStorageDirectory()
//				.getAbsolutePath() + "/cgyl/cache";
//		File file = new File(AppFile);
//		// 检测路径是否存在
//		if (!file.exists()) {
//			file.mkdirs();// 创建该路径
//		}
//		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//				.cacheInMemory() // 1.8.6包使用时候，括号里面传入参数true
//				.cacheOnDisc() // 1.8.6包使用时候，括号里面传入参数true
//				.build();
//		File cacheDir = new File(AppFile + "ImgCache");
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				context)
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.enableLogging()
//				// 设置线程池的大小
//				.denyCacheImageMultipleSizesInMemory()
//				// 设置禁止在内存中缓存同一张图片的多个尺寸
//				// .discCacheFileNameGenerator(new Md5FileNameGenerator())
//				.discCache(new UnlimitedDiscCache(cacheDir))
//				// 设置缓存路径
//				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.defaultDisplayImageOptions(defaultOptions) // Remove
//															// for
//															// release
//															// app
//				.build();
//		// Initialize ImageLoader with configuration.
//		ImageLoader.getInstance().init(config);
//	}

	public class LoginOutOfDateException extends Exception {
		private static final long serialVersionUID = -4402476679384003371L;
		public void goLogin(Activity act){
			Intent intent = new Intent();
			intent.setClass(act, LoginActivity.class);
			startActivity(intent);
		}
		@Override
		public String getMessage() {
			return "请登陆";
		}

	}
}
