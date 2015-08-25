package com.utoo.chunguanyouli.tool;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public abstract class CommonExpandableAdapter<T, V> extends
		BaseExpandableListAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> perDatas;
	protected final int mItemLayoutIdChild;
	protected final int mItemLayoutIdGroup;

	/**
	 * 带数据初始化
	 * 
	 * @param context
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public CommonExpandableAdapter(Context context, List<T> perDatas,
			int mItemLayoutIdGroup, int mItemLayoutIdChild) {
		this.perDatas = new ArrayList<T>();
		this.perDatas.addAll(perDatas);
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mItemLayoutIdChild = mItemLayoutIdChild;
		this.mItemLayoutIdGroup = mItemLayoutIdGroup;
	}

	/**
	 * 无数据初始化
	 * 
	 * @param context
	 * @param itemLayoutId
	 */
	public CommonExpandableAdapter(Context context, int mItemLayoutIdGroup,
			int mItemLayoutIdChild) {
		this.mContext = context;
		this.perDatas = new ArrayList<T>();
		this.mInflater = LayoutInflater.from(mContext);
		this.mItemLayoutIdChild = mItemLayoutIdChild;
		this.mItemLayoutIdGroup = mItemLayoutIdGroup;
	}

	/**
	 * 重置数据
	 * 
	 * @param mDatas
	 */
	public void resetData(List<T> perDatas) {
		this.perDatas = perDatas;
		this.notifyDataSetChanged();
	}

	/**
	 * 增加数据
	 * 
	 * @param mDatas
	 */
	public void addData(List<T> mDatas) {
		this.perDatas.addAll(mDatas);
		this.notifyDataSetChanged();
	}

	@Override
	public int getGroupCount() {
		return perDatas.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		List<V> cDatas = getChildDatas(getGroup(groupPosition));
		if (cDatas != null)
			return cDatas.size();
		else
			return 0;
	}

	@Override
	public T getGroup(int groupPosition) {
		return perDatas.get(groupPosition);
	}

	@Override
	public V getChild(int groupPosition, int childPosition) {
		List<V> cDatas = getChildDatas(getGroup(groupPosition));
		if (cDatas != null)
			return cDatas.get(childPosition);
		else
			return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		final ViewHolderHelper viewHolder = getViewHolder(groupPosition,
				convertView, parent, mItemLayoutIdGroup);
		convertPerView(viewHolder, getGroup(groupPosition));
		return viewHolder.getConvertView();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final ViewHolderHelper viewHolder = getViewHolder(childPosition,
				convertView, parent, mItemLayoutIdChild);
		convertchiView(viewHolder, getChild(groupPosition, childPosition));
		return viewHolder.getConvertView();
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public abstract void convertPerView(ViewHolderHelper helper, T item);

	public abstract void convertchiView(ViewHolderHelper helper, V item);

	private ViewHolderHelper getViewHolder(int position, View convertView,
			ViewGroup parent, int layoutId) {
		return ViewHolderHelper.get(mContext, convertView, parent, layoutId,
				position);
	}

	public abstract List<V> getChildDatas(T per);
}
