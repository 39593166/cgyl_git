package com.utoo.chunguanyouli.tool;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @ClassName: CommonAdapter
 * @Description:通用的适配器
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	/**
	 * 带数据初始化
	 * 
	 * @param context
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	/**
	 * 无数据初始化
	 * 
	 * @param context
	 * @param itemLayoutId
	 */
	public CommonAdapter(Context context, int itemLayoutId) {
		this.mItemLayoutId = itemLayoutId;
		this.mContext = context;
		mDatas = new ArrayList<T>();
	}

	/**
	 * 重置数据
	 * 
	 * @param mDatas
	 */
	public void resetData(List<T> mDatas) {
		if (mDatas != null) {
			this.mDatas.clear();
			this.mDatas.addAll(mDatas);
		}
		this.notifyDataSetChanged();
	}

	/**
	 * 增加数据
	 * 
	 * @param mDatas
	 */
	public void addData(List<T> mDatas) {
		this.mDatas.addAll(mDatas);
		this.notifyDataSetChanged();
	}
	/**
	 * 清空数据
	 */
	public void clearData() {
		this.mDatas.clear();
		this.notifyDataSetChanged();
	}
	/**
	 * 删除
	 */
	public void delete(T mData) {
		this.mDatas.remove(mData);
		this.notifyDataSetChanged();
	}
	/**
	 * 删除
	 */
	public void delete(int position) {
		this.mDatas.remove(position);
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolderHelper viewHolder = getViewHolder(position,
				convertView, parent);
		convert(viewHolder, getItem(position));
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolderHelper helper, T item);

	private ViewHolderHelper getViewHolder(int position, View convertView,
			ViewGroup parent) {
		return ViewHolderHelper.get(mContext, convertView, parent,
				mItemLayoutId, position);
	}

}