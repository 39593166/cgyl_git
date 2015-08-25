package com.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class NoScrollWebView extends WebView {

	public NoScrollWebView(Context context) {
		super(context);
	}

	public NoScrollWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
