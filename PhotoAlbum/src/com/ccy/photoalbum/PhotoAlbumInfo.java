package com.ccy.photoalbum;

import java.util.ArrayList;

public class PhotoAlbumInfo implements Comparable<PhotoAlbumInfo> {

	private String path;// 首页图片路径
	private String name; // 父类名
	private int count;// 数量
	private ArrayList<String> arrayList;// 子图片集

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<String> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<String> arrayList) {
		this.arrayList = arrayList;
	}

	@Override
	public int compareTo(PhotoAlbumInfo another) {
		// TODO Auto-generated method stub
		if (getName().substring(0, 1).equals("最"))
			return -1;
		else
			// return getName().compareTo(another.getName());
			return 0;
	}

}
