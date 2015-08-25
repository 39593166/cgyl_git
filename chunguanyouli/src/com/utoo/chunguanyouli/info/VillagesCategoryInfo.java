package com.utoo.chunguanyouli.info;

import java.util.List;

public class VillagesCategoryInfo {

	private String state;

	private String sid;

	private String num;

	private List<Val> val;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSid() {
		return this.sid;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNum() {
		return this.num;
	}

	public List<Val> getVal() {
		return val;
	}

	public void setVal(List<Val> val) {
		this.val = val;
	}

	public static class Val {

		private String id;
		private String name;
		private String gid;
		private String code;
		private String pid;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setGid(String gid) {
			this.gid = gid;
		}

		public String getGid() {
			return this.gid;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getPid() {
			return this.pid;
		}

	}

}
