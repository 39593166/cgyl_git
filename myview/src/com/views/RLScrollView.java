package com.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class RLScrollView extends ScrollView {

	public RLScrollView(Context context) {
		super(context);
	}

	public RLScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RLScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public interface OnScrollChangedListener {
		public void onScrollChanged(int x, int y, int oldxX, int oldY);
	}

	private OnScrollChangedListener onScrollChangedListener;

	/**
	 * 
	 * @param onScrollChangedListener
	 */
	public void setOnScrollListener(
			OnScrollChangedListener onScrollChangedListener) {
		this.onScrollChangedListener = onScrollChangedListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldX, int oldY) {
		super.onScrollChanged(x, y, oldX, oldY);
		if (onScrollChangedListener != null) {
			onScrollChangedListener.onScrollChanged(x, y, oldX, oldY);
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAtTop() {
		return getScrollY() <= 0;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAtBottom() {
		return getScrollY() == getChildAt(getChildCount() - 1).getBottom()
				+ getPaddingBottom() - getHeight();
	}

	/**
	 * 
	 * @param child
	 * @return
	 */
	public boolean isChildVisible(View child) {
		if (child == null) {
			return false;
		}
		Rect scrollBounds = new Rect();
		getHitRect(scrollBounds);
		return child.getLocalVisibleRect(scrollBounds);
	}
}
