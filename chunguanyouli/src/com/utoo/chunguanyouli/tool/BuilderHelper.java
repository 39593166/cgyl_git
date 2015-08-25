package com.utoo.chunguanyouli.tool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public abstract class BuilderHelper {

	public static void showBuilder(Context context, String title,
			DialogInterface.OnClickListener submitlis) {
		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setPositiveButton("确定", submitlis);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	public static void showBuilder(Context context, View v, String title,
			DialogInterface.OnClickListener submitlis) {
		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(context);
		builder.setView(v);
		builder.setTitle("修改用户名");
		builder.setPositiveButton("确定", submitlis);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.show();
	}
}
