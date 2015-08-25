package com.ccy.photoalbum.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class APPManager {

	private static long exitTime = 0;

	public static final int CAMERA = 1418312784;// 照相机
	public static final int GALLERY = 1418312785;// 相册
	public static final int CONTACT = 1418312786;// 联系人

	/**
	 * 取得手机屏幕宽度px
	 * 
	 * @param context
	 *            上下文
	 * @return 屏幕宽度px
	 */
	public static int getScreenWidthPx(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		int screenWidtn = dm.widthPixels;
		return screenWidtn;
	}

	/**
	 * 取得手机屏幕高度px
	 * 
	 * @param context
	 *            上下文
	 * @return 屏幕高度px
	 */
	public static int getScreenHeightPx(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		int screenHeight = dm.heightPixels;
		return screenHeight;
	}

	/**
	 * 检查客户端网络是否可用
	 * 
	 * @param context
	 *            上下文
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info != null) {
			return true;
		}
		return false;
	}

	/**
	 * 检查mobile网络是否可用
	 * 
	 * @param context
	 *            上下文
	 * @return
	 */
	public static boolean isMobileAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (info.isConnected()) {
			return true;
		}
		return false;
	}

	/**
	 * 检查WIFI网络是否可用
	 * 
	 * @param context
	 *            上下文
	 * @return
	 */
	public static boolean isWifiAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (info.isConnected()) {
			return true;
		}
		return false;
	}

	// /**
	// * 发送广播，关闭所有继承自BaseActivity的Activity
	// *
	// * @param ac
	// */
	// public static void exitSystem(Activity ac) {
	// Intent intent = new Intent();
	// intent.setAction(BaseActivity.SYSTEM_EXIT);
	// ac.sendBroadcast(intent);
	// }

	// /**
	// * 回退监听
	// *
	// * @param ac
	// */
	// public static void onBackPressed(Activity ac) {
	// if (System.currentTimeMillis() - exitTime > 2000) {
	// MyToast.makeText(ac, "再按一次退出程序");
	// exitTime = System.currentTimeMillis();
	// } else {
	// APPManager.exitSystem(ac);
	// }
	// }

	/**
	 * 获取versionCode(通过versionCode判断是否需要更新客户端)
	 * 
	 * @param context
	 *            上下文
	 * @return versionCode
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取versionName(展示给用户看的版本名称)
	 * 
	 * @param context
	 *            上下文
	 * @return versionName
	 */
	public static String getVersionName(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "unkonwn";
	}

	/**
	 * 启动需要安装的APK
	 * 
	 * @param ac
	 *            传入activity
	 * @param str
	 *            apk名称，格式（***.apk）
	 */
	public static void startInstal(Activity ac, String str) {
		String fileName = Environment.getExternalStorageDirectory() + "/" + str;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(fileName)),
				"application/vnd.android.package-archive");
		ac.startActivity(intent);
	}

	// /**
	// * 回退监听
	// *
	// * @param ac
	// * @param exitTime
	// */
	// public static void onBack(Activity ac) {
	// if (System.currentTimeMillis() - exitTime > 2000) {
	// MyToast.makeText(ac, "再按一次退出程序");
	// exitTime = System.currentTimeMillis();
	// } else {
	// APPManager.exitSystem(ac);
	// }
	// }

	// /**
	// * 创建进度对话框
	// *
	// * @param con
	// * 上下文
	// * @param content
	// * 内容
	// */
	// public static void showProgressDialog(Context con, String content) {
	// Activity ac = (Activity) con;
	// if (!ac.isFinishing()) {
	// dialog = new Dialog(con, R.style.dialog);
	// dialog.setOnKeyListener(new OnKeyListener() {
	// @Override
	// public boolean onKey(DialogInterface arg0, int arg1, KeyEvent arg2) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	// });
	// dialog.setCanceledOnTouchOutside(false);
	// Window win = dialog.getWindow();
	// win.setContentView(R.layout.progress_bar);
	// if (content != null) {
	// ((TextView)
	// win.findViewById(R.id.progress_bar_dlg_content)).setText(content);
	// } else {
	// ((TextView) win.findViewById(R.id.progress_bar_dlg_content))
	// .setVisibility(View.GONE);
	// }
	// dialog.show();
	// }
	// }
	//
	// /**
	// * 关闭进度对话框
	// */
	// public static void closeProgressDialog() {
	// if (dialog != null) {
	// dialog.dismiss();
	// dialog = null;
	// }
	// }

	// /**
	// * 当前系统语言环境是否为简体中文
	// *
	// * @param context
	// * @return
	// */
	// public static boolean isZH(Context context) {
	// Locale locale = context.getResources().getConfiguration().locale;
	// String language = locale.getCountry();
	// if (language.equals("TW") || language.equals("HK")) {
	// return false;
	// }
	// return true;
	// }

	/**
	 * 取得当前时间--yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		return format.format(date);
	}

	// /**
	// * 判断当前是否为Wifi
	// *
	// * @param icontext
	// * @return
	// */
	// public static boolean isWifiActive(Context icontext) {
	// Context context = icontext.getApplicationContext();
	// ConnectivityManager connectivity = (ConnectivityManager) context
	// .getSystemService(Context.CONNECTIVITY_SERVICE);
	// NetworkInfo[] info;
	// if (connectivity != null) {
	// info = connectivity.getAllNetworkInfo();
	// if (info != null) {
	// for (int i = 0; i < info.length; i++) {
	// if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
	// return true;
	// }
	// }
	// }
	// }
	// return false;
	// }

	/**
	 * dp转px
	 * 
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int DpToPx(Context context, float dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		int px = (int) (dp * scale + 0.5f);
		return px;
	}

	/**
	 * px转dp
	 * 
	 * @param context
	 * @param px
	 * @return
	 */
	public static int PxToDp(Context context, float px) {
		float scale = context.getResources().getDisplayMetrics().density;
		int dp = (int) (px / scale + 0.5f);
		return dp;
	}

	/**
	 * px转sp
	 * 
	 * @param context
	 * @param px
	 * @return
	 */
	public static int PxToSp(Context context, float px) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		int sp = (int) (px / fontScale + 0.5f);
		return sp;
	}

	/**
	 * sp转px
	 * 
	 * @param context
	 * @param sp
	 * @return
	 */
	public static int SPToPx(Context context, float sp) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		int px = (int) (sp * fontScale + 0.5f);
		return px;
	}

	/**
	 * md5加密
	 * 
	 * @param string
	 *            加密字符串
	 * @return
	 */
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	//
	// /**
	// * 获取汉字串拼音，英文字符不变
	// *
	// * @param chinese
	// * 汉字串
	// * @return 汉语拼音
	// */
	// public static String getPinyin(String chinese) {
	// StringBuffer pybf = new StringBuffer();
	// char[] arr = chinese.toCharArray();
	// HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
	// defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	// defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	// for (int i = 0; i < arr.length; i++) {
	// if (arr[i] > 128) {
	// try {
	// pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i],
	// defaultFormat)[0]);
	// } catch (BadHanyuPinyinOutputFormatCombination e) {
	// e.printStackTrace();
	// }
	// } else {
	// pybf.append(arr[i]);
	// }
	// }
	// return pybf.toString();
	// }

	/**
	 * 打开照相机拍照
	 * 
	 * @param ac
	 * @param path
	 */
	public static void openCamera(Activity ac, String path) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
		ac.startActivityForResult(intent, CAMERA);
	}

	/**
	 * 打开相册
	 * 
	 * @param ac
	 */
	public static void openGallery(Activity ac) {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		ac.startActivityForResult(intent, GALLERY);
	}

	// /**
	// * 打开相册
	// *
	// * @param ac
	// */
	// public static void openGallery(Fragment fra) {
	// Intent intent = new Intent(Intent.ACTION_PICK, null);
	// intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	// "image/*");
	// fra.startActivityForResult(intent, GALLERY);
	// }

	public static void openContact(Activity ac) {
		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		ac.startActivityForResult(intent, CONTACT);
	}

	/**
	 * 获取相册照片路径
	 * 
	 * @param ac
	 * @param data
	 * @return
	 */
	public static String getGalleryPath(Activity ac, Intent data) {
		Uri selectedImage = data.getData();
		String[] filePathColumns = { MediaStore.Images.Media.DATA };
		Cursor c = ac.getContentResolver().query(selectedImage,
				filePathColumns, null, null, null);
		c.moveToFirst();
		int columnIndex = c.getColumnIndex(filePathColumns[0]);
		String picturePath = c.getString(columnIndex);
		c.close();
		return picturePath;
	}

	/**
	 * 创建拍照存储路径
	 * 
	 * @return
	 */
	public static File createPath(String packageName) {
		File file = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			String path = Environment.getExternalStorageDirectory() + "/"
					+ packageName;
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(file, "head.jpg");
			file.delete();
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/** 图片转换为二进制 */
	public static String imagToBinary(String path) {
		File file = new File(path);
		final StringBuffer sb = new StringBuffer();
		try {
			FileInputStream is = new FileInputStream(file);
			byte[] b = new byte[is.available()];
			is.read(b);
			for (byte c : b) {
				sb.append(Integer.toBinaryString(c));
			}
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
