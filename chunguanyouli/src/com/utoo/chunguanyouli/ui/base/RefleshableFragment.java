package com.utoo.chunguanyouli.ui.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.utoo.chunguanyouli.R;
import com.views.RLScrollView;
import com.views.RLScrollView.OnScrollChangedListener;

public abstract class RefleshableFragment extends NetDataBaseFragment {

	public void initReflesh(final SwipeRefreshLayout refleshView,
			final RLScrollView scrollView) {
		scrollView.setOnScrollListener(new OnScrollChangedListener() {

			@Override
			public void onScrollChanged(int x, int y, int oldxX, int oldY) {
				if (scrollView.isAtTop()) {
					refleshView.setEnabled(true);
				} else {
					refleshView.setEnabled(false);
				}
			}
		});
		refleshView.setColorSchemeResources(R.color.blue_bright,
				R.color.green_light, R.color.orange_light, R.color.red_light);
		refleshView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				reflesh();
			}
		});
	}

	protected abstract void reflesh();
}
