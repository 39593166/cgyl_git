package com.utoo.chunguanyouli.ui.main.mainpage;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.squareup.picasso.Picasso;

public  class BannerAdapter extends PagerAdapter {
	List<String> mDatas = null;
	List<ImageView> mList = null;
	Context ctx = null;

	int indexImageRsc;

	public BannerAdapter(List<String> mDatas, Context ctx,
			final int indexImageRrc) {
		this.indexImageRsc = indexImageRrc;
		mList = new ArrayList<ImageView>();
		this.mDatas = mDatas;
		this.ctx = ctx;
		for (int i = 0; i < mDatas.size(); i++) {
			final ImageView image = new ImageView(ctx);
			image.setFocusable(true);
			image.setScaleType(ScaleType.FIT_XY);
			Picasso.with(ctx).load(mDatas.get(i)).error(indexImageRrc)
					.resize(360, 180).into(image);
			mList.add(image);
		}
	}

	@Override
	public int getCount() {
		return mDatas.size();
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
		// int location = position % mList.size();

		ImageView imageView = mList.get(position);

		if (imageView.getParent() != null) {
			((ViewPager) container).removeView(imageView);
		}

		container.addView(imageView, 0);
		return imageView;
	}
}
