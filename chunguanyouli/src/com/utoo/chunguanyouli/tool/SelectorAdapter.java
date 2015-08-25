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
public abstract class SelectorAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	public List<Boolean> selects;
	protected final int mItemLayoutId;

	/**
	 * 带数据初始化
	 * 
	 * @param context
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public SelectorAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
		selects = new ArrayList<Boolean>();
		int sum = mDatas.size();
		for (int i = 0; i < sum; i++) {
			selects.add(false);
		}
	}

	/**
	 * 无数据初始化
	 * 
	 * @param context
	 * @param itemLayoutId
	 */
	public SelectorAdapter(Context context, int itemLayoutId) {
		this.mItemLayoutId = itemLayoutId;
		this.mContext = context;
		mDatas = new ArrayList<T>();
		selects = new ArrayList<Boolean>();
	}

	/**
	 * 删除
	 */
	public void deleteSelecters() {
		List<T> deletes = new ArrayList<T>();
		for (int i = 0; i < selects.size(); i++) {
			if (selects.get(i)) {
				deletes.add(mDatas.get(i));
			}
		}
		for (T t : deletes) {
			mDatas.remove(t);
		}

		this.selects.clear();
		int sum = mDatas.size();
		for (int i = 0; i < sum; i++) {
			selects.add(false);
		}
		this.notifyDataSetChanged();
	}

	/**
	 * 重置数据
	 * 
	 * @param mDatas
	 */
	public void resetData(List<T> mDatas) {
		this.mDatas.clear();
		this.mDatas.addAll(mDatas);
		this.notifyDataSetChanged();
		selects.clear();
		int sum = mDatas.size();
		for (int i = 0; i < sum; i++) {
			selects.add(false);
		}
	}

	/**
	 * 增加数据
	 * 
	 * @param mDatas
	 */
	public void addData(List<T> mDatas) {
		this.mDatas.addAll(mDatas);
		this.notifyDataSetChanged();
		int sum = mDatas.size();
		for (int i = 0; i < sum; i++) {
			selects.add(false);
		}
	}

	/**
	 * 获取选中数据
	 * 
	 * @return
	 */
	public List<T> getSelectedDatas() {
		List<T> mDatasSelect = new ArrayList<T>();
		for (int i = 0; i < selects.size(); i++) {
			boolean b = selects.get(i);
			if (b) {
				mDatasSelect.add(mDatas.get(i));
			}
		}
		return mDatasSelect;
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
		convert(viewHolder, getItem(position), position);
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolderHelper helper, T item, int position);

	private ViewHolderHelper getViewHolder(int position, View convertView,
			ViewGroup parent) {
		return ViewHolderHelper.get(mContext, convertView, parent,
				mItemLayoutId, position);
	}

	public boolean isSelect(int position) {
		return selects.get(position);
	}

	public void change(int position, boolean isSelect) {
		if (position > selects.size() - 1) {
			return;
		}
		selects.set(position, isSelect);
		this.notifyDataSetChanged();
	}
}