package com.utoo.chunguanyouli.entity;

import java.io.Serializable;
import java.util.Date;

public class Contry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 598643578607104665L;
	private int id;
	private String name;
	private String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
