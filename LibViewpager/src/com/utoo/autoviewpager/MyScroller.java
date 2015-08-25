package com.utoo.autoviewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class MyScroller extends Scroller {
	private int animTime = 800;

	public MyScroller(Context context) {
		super(context);
	}

	public MyScroller(Context context, Interpolator interpolator) {
		super(context, interpolator);
	}

	public int getAnimTime() {
		return animTime;
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
		super.startScroll(startX, startY, dx, dy, animTime);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy) {
		super.startScroll(startX, startY, dx, dy, animTime);
	}

	public void setmDuration(int animTime) {
		this.animTime = animTime;
	}
}