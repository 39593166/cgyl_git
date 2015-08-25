package com.utoo.pageradapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MyViewPagerAdapter extends PagerAdapter {

	List<ImageView> mList = null;
	ViewPager vPage = null;

	public MyViewPagerAdapter(List<ImageView> list, ViewPager page) {
		mList = list;
		vPage = page;
	}

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

		// ï¿½ï¿½ï¿½ï¿½Â¼ï¿?
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		container.addView(imageView, 0);
		return imageView;
	}

}
