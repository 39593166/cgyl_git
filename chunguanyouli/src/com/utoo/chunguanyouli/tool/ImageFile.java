package com.utoo.chunguanyouli.tool;

import java.io.File;

/**
 * @author fsm
 * @date 2014-6-4 上午10:23:22
 * @Description
 * @version V1.0
 */

public class ImageFile extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6852650155712002546L;
	int width;
	int height;

	public ImageFile(String path) {
		super(path);
		this.width = 400;
		this.height = 500;
	}

	public ImageFile(String path, int width, int height) {
		super(path);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
