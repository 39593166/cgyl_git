package com.utoo.chunguanyouli.ui.shopping.goodsinfo;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgReplyId;
import com.utoo.chunguanyouli.entity.Commend;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;

public abstract class GoodsCommendHelper implements
		OnRefreshListener2<ListView> {
	Activity ctx;
	View pView;
	int pageIndex;

	public GoodsCommendHelper(Activity ctx, View pView) {
		this.ctx = ctx;
		this.pView = pView;
		findViews();

	}

	PullToRefreshListView ptl;
	CommonAdapter<CgReplyId> comAdapter;

	private void findViews() {
		if (pView == null) {
			ptl = (PullToRefreshListView) ctx
					.findViewById(R.id.goodsCommendList);
		} else {
			ptl = (PullToRefreshListView) pView
					.findViewById(R.id.goodsCommendList);
		}
		if (ptl != null) {
			comAdapter = new CommonAdapter<CgReplyId>(ctx, R.layout.item_comment) {

				@Override
				public void convert(ViewHolderHelper helper, CgReplyId item) {
					helper.setText(R.id.item_commend_context, item.getContent())
							.setText(R.id.item_commend_name,
									item.getUname())
//							.setImageByUrl(R.id.item_commend_head,
//									item.getCommender().getHeadUrl(),
//									R.drawable.index,
//									R.drawable.index)
							.setText(R.id.item_commend_time,
									item.getDatesend() + "");
				}
			};
			ptl.setMode(Mode.BOTH);
			ptl.setAdapter(comAdapter);
			ptl.setOnRefreshListener(this);
		}
	}

//	public void resetDatas(List<CgReplyId> mData) {
//		ptl.onRefreshComplete();
//		if (mData != null && mData.size() != 0) {
//			pageIndex = 2;
//			comAdapter.resetData(mData);
//		}
//	}

	public void setData(int tag,List<CgReplyId> mData) {
		Log.e("设置数据条数", mData.size() + "");
		ptl.onRefreshComplete();
		if(tag == GoodsCommendFragment.TAG_MORE){
			
			if (mData != null && mData.size() != 0) {
				pageIndex++;
				comAdapter.addData(mData);
			}
		}else {
			if (mData != null && mData.size() != 0) {
				pageIndex = 2;
				comAdapter.resetData(mData);
			}
		}
		Log.e("list子元素数量：", ptl.getChildCount() + "");
		Log.e("adapter数据量：", comAdapter.getCount() + "");
	}

	protected abstract void reflesh();

	protected abstract void more(int pageIndex);

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		reflesh();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		more(pageIndex);
	}
}
