package com.utoo.pageradapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.squareup.picasso.Picasso;

public abstract class MyObjectPagerAdapter<T> extends PagerAdapter {
	List<T> mDatas = null;
	List<ImageView> mList = null;
	Context ctx = null;

	int indexImageRsc;

	public MyObjectPagerAdapter(List<T> mDatas, Context ctx,
			final int indexImageRrc) {
		this.indexImageRsc = indexImageRrc;
		mList = new ArrayList<ImageView>();
		this.mDatas = mDatas;
		this.ctx = ctx;
		for (int i = 0; i < mDatas.size(); i++) {
			final ImageView image = new ImageView(ctx);
			image.setFocusable(true);
			image.setScaleType(ScaleType.FIT_XY);
			int res = getImageResource(mDatas.get(i));
			String url = getImageUrl(mDatas.get(i));

			if (res != 0) {
				image.setImageResource(res);
			} else {
				if (url.startsWith("http")) {
					Picasso.with(ctx).load(url).error(indexImageRrc)
							.resize(360, 180).into(image);
				} else if (url.startsWith("/")) {
					Picasso.with(ctx).load(new File(url)).into(image);
				}
			}
			mList.add(image);
		}
	}

	// BitmapLoadCallBack<ImageView> callback = new
	// BitmapLoadCallBack<ImageView>() {
	//
	// @Override
	// public void onLoadCompleted(ImageView arg0, String arg1, Bitmap arg2,
	// BitmapDisplayConfig arg3, BitmapLoadFrom arg4) {
	//
	// }
	//
	// @Override
	// public void onLoadFailed(ImageView arg0, String arg1, Drawable arg2) {
	// arg0.setImageResource(indexImageRsc);
	// }
	// };

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		int location = position % mList.size();

		ImageView imageView = mList.get(location);

		if (imageView.getParent() != null) {
			((ViewPager) container).removeView(imageView);
		}

		// ����¼�?
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		container.addView(imageView, 0);
		return imageView;
	}

	/**
	 * ��ȡ����ͼƬurl
	 * 
	 * @param data
	 * @return
	 */
	public abstract String getImageUrl(T data);

	/**
	 * ��ȡ��ԴͼƬ
	 * 
	 * @param data
	 * @return
	 */
	public abstract int getImageResource(T data);
}
