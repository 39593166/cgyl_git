package com.utoo.chunguanyouli.info;

import java.util.List;

public class TypeInfo {

	private String state;
	private List<Val> val;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Val> getVal() {
		return val;
	}

	public void setVal(List<Val> val) {
		this.val = val;
	}

	public class Val {
		private int id;
		private String pic;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
