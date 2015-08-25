package com.utoo.chunguanyouli.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class ImageSubTool {

	/**
	 * 压缩图片
	 * 
	 * @param filePath
	 * @param height
	 * @param width
	 * @return
	 */
	public static File pressPic(String filePath, int height, int width) {
		String smallFileDirPath = Environment.getExternalStorageDirectory()
				+ "/cgyl/uploadcatch/";// 保存压缩后图片
		String name = System.currentTimeMillis()+".png";
		File newFileDir = new File(smallFileDirPath);
		if (!newFileDir.exists()) {
			newFileDir.mkdirs();
		}
		String tempFileName = smallFileDirPath + name;
		try {
			// 设置压缩参数
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, o);

			int width_tmp = o.outWidth, height_tmp = o.outHeight;// // 源图片参数
			double widthscal = 0.0;
			double heightscal = 0.0;

			// 如果图片本身的宽高大于目的宽高,则找到缩放比例
			if (width_tmp > width) {
				widthscal = width_tmp / width + 1;
			} else {
				widthscal = 1;
			}
			if (height_tmp > height) {
				heightscal = height_tmp / height + 1;
			} else {
				heightscal = 1;
			}
			int destWidth = (int) (width_tmp / widthscal);
			int destHeight = (int) (height_tmp / heightscal);

			// 宽高按照最大的值来缩放
			int scale = 1;
			if (widthscal > heightscal) {
				scale = ((int) widthscal);
			} else {
				scale = ((int) heightscal);
			}
			// 设置option中缩放
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			o2.inJustDecodeBounds = false;
			o2.outHeight = destHeight;
			o2.outWidth = destWidth;
			// 获取目的bitmap
			Bitmap newBitmap = BitmapFactory.decodeFile(filePath, o2);
			// 文件输出流
			FileOutputStream fos = new FileOutputStream(tempFileName);
			newBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);// 保存到文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new File(tempFileName);
	}
}
