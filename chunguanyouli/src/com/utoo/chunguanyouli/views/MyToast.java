package com.utoo.chunguanyouli.views;


import android.content.Context;
import android.widget.Toast;

public class MyToast {

	public static void makeText(Context context, CharSequence text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	// public static void makeText(Activity activity, CharSequence text, int
	// resId) {
	// NiftyNotificationView.build(activity, text, Effects.thumbSlider,
	// resId).show();
	// }
	//
	// public static void makeText(Activity activity, CharSequence text, int
	// viewResId, int drawableId) {
	// NiftyNotificationView.build(activity, text, Effects.thumbSlider,
	// viewResId)
	// .setIcon(drawableId).show();
	// }
	//
	// public static void makeText(Activity activity, CharSequence text, int
	// viewResId,
	// int drawableId, OnClickListener onClickListener) {
	// NiftyNotificationView.build(activity, text, Effects.thumbSlider,
	// viewResId)
	// .setIcon(drawableId).setOnClickListener(onClickListener).show();
	// }
	//
	// public static void makeTextAnimation(Activity activity, CharSequence
	// text, Effects effects,
	// int viewResId, int drawableId, OnClickListener onClickListener) {
	// NiftyNotificationView.build(activity, text, effects,
	// viewResId).setIcon(drawableId)
	// .setOnClickListener(onClickListener).show();
	// }

}
