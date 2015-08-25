package com.utoo.autoviewpager;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

public class AutoScrollViewPager extends ViewPager {
	Handler handler;
	public static final int SCROLL_WHAT = 0;
	private int scrollDelayTime = 3000;
	int direction = RIGHT;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	boolean isCycle = true;
	boolean isBorderAnimation = false;
	static MyScroller myScroller = null;
	private boolean isAutoScroll = false;
	private boolean stopScrollWhenTouch = true;
	private boolean isStopByTouch = false;
	private float touchX = 0f, downX = 0f;

	/** scroll factor for auto scroll animation, default is 1.0 **/
	private double autoScrollFactor = 1.0;
	/** scroll factor for swipe scroll animation, default is 1.0 **/
	private double swipeScrollFactor = 1.0;

	/** do nothing when sliding at the last or first item **/
	public static final int SLIDE_BORDER_MODE_NONE = 0;
	/** cycle when sliding at the last or first item **/
	public static final int SLIDE_BORDER_MODE_CYCLE = 1;
	/** deliver event to parent when sliding at the last or first item **/
	public static final int SLIDE_BORDER_MODE_TO_PARENT = 2;

	/**
	 * how to process when sliding at the last or first item, default is
	 * {@link #SLIDE_BORDER_MODE_NONE}
	 **/
	private int slideBorderMode = SLIDE_BORDER_MODE_NONE;

	public AutoScrollViewPager(Context paramContext) {
		super(paramContext);
		init();
	}

	public AutoScrollViewPager(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		init();
	}

	private void init() {
		handler = new MyHandler(this);
		setViewPagerScroller();
	}

	/**
	 * 方向
	 * 
	 * @param direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * 触碰是否停止自动滑动，默认true
	 * 
	 * @param isStopByTouch
	 */
	public void setStopByTouch(boolean isStopByTouch) {
		this.isStopByTouch = isStopByTouch;
	}

	/**
	 * 间隔时间
	 * 
	 * @param scrollDelayTime
	 */
	public void setScrollDelayTime(int scrollDelayTime) {
		this.scrollDelayTime = scrollDelayTime;
	}

	/**
	 * 开启自动跳转
	 * 
	 * @param delayTimeInMills
	 *            first scroll delay time
	 */
	public void startAutoScroll(int delayTimeInMills) {
		isAutoScroll = true;
		sendScrollMessage(delayTimeInMills);
	}

	public void startAutoScroll() {
		isAutoScroll = true;
		sendScrollMessage((long) (scrollDelayTime + myScroller.getAnimTime()
				/ autoScrollFactor * swipeScrollFactor));
	}

	/**
	 * s停止自动
	 */
	public void stopAutoScroll() {
		isAutoScroll = false;
		handler.removeMessages(SCROLL_WHAT);
	}

	/**
	 * 通知进行滑动
	 * 
	 * @param delayTimeInMills
	 */
	private void sendScrollMessage(long delayTimeInMills) {
		/** remove messages before, keeps one message is running at most **/
		handler.removeMessages(SCROLL_WHAT);
		handler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
	}

	/**
	 * scroll only once 滚动一次
	 */
	private void scrollOnce() {
		PagerAdapter adapter = getAdapter();
		int currentItem = getCurrentItem();
		int totalCount;
		if (adapter == null || (totalCount = adapter.getCount()) <= 1) {
			return;
		}

		int nextItem = (direction == LEFT) ? --currentItem : ++currentItem;
		if (nextItem < 0) {
			if (isCycle) {
				setCurrentItem(totalCount - 1, isBorderAnimation);
			}
		} else if (nextItem == totalCount) {
			if (isCycle) {
				setCurrentItem(0, isBorderAnimation);
			}
		} else {
			setCurrentItem(nextItem, true);
		}
	}

	/**
	 * 滚动过程中的动画延时 set ViewPager scroller to change animation duration when
	 * sliding
	 */
	private void setViewPagerScroller() {
		try {
			Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
			scrollerField.setAccessible(true);
			Field interpolatorField = ViewPager.class
					.getDeclaredField("sInterpolator");
			interpolatorField.setAccessible(true);

			myScroller = new MyScroller(getContext(),
					(Interpolator) interpolatorField.get(null));
			scrollerField.set(this, myScroller);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点击后是否继续滚动
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = MotionEventCompat.getActionMasked(ev);

		if (stopScrollWhenTouch) {
			if ((action == MotionEvent.ACTION_DOWN) && isAutoScroll) {
				isStopByTouch = true;
				stopAutoScroll();
			} else if (ev.getAction() == MotionEvent.ACTION_UP && isStopByTouch) {
				startAutoScroll();
			}
		}

		if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT
				|| slideBorderMode == SLIDE_BORDER_MODE_CYCLE) {
			touchX = ev.getX();
			if (ev.getAction() == MotionEvent.ACTION_DOWN) {
				downX = touchX;
			}
			int currentItem = getCurrentItem();
			PagerAdapter adapter = getAdapter();
			int pageCount = adapter == null ? 0 : adapter.getCount();
			/**
			 * current index is first one and slide to right or current index is
			 * last one and slide to left.<br/>
			 * if slide border mode is to parent, then
			 * requestDisallowInterceptTouchEvent false.<br/>
			 * else scroll to last one when current item is first one, scroll to
			 * first one when current item is last one.
			 */
			if ((currentItem == 0 && downX <= touchX)
					|| (currentItem == pageCount - 1 && downX >= touchX)) {
				if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					if (pageCount > 1) {
						setCurrentItem(pageCount - currentItem - 1,
								isBorderAnimation);
					}
					getParent().requestDisallowInterceptTouchEvent(true);
				}
				return super.dispatchTouchEvent(ev);
			}
		}
		getParent().requestDisallowInterceptTouchEvent(true);

		return super.dispatchTouchEvent(ev);
	}

	private static class MyHandler extends Handler {

		private final WeakReference<AutoScrollViewPager> autoScrollViewPager;

		public MyHandler(AutoScrollViewPager autoScrollViewPager) {
			this.autoScrollViewPager = new WeakReference<AutoScrollViewPager>(
					autoScrollViewPager);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case SCROLL_WHAT:
				AutoScrollViewPager pager = this.autoScrollViewPager.get();
				if (pager != null) {
					pager.scrollOnce();
					pager.sendScrollMessage(pager.scrollDelayTime
							+ myScroller.getAnimTime());
				}
			default:
				break;
			}
		}
	}
}
