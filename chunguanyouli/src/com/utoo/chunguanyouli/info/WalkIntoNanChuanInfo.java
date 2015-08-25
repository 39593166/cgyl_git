package com.utoo.chunguanyouli.info;

import java.util.List;

public class WalkIntoNanChuanInfo {

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
		private String cid;
		private String pid;
		private String content;
		private String effect;
		private String numhu;
		private String numpro;
		private String numorder;
		private String numgoods;
		private String uid;
		private String datesend;
		private String pic;
		private String name;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setCid(String cid) {
			this.cid = cid;
		}

		public String getCid() {
			return this.cid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getPid() {
			return this.pid;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent() {
			return this.content;
		}

		public void setEffect(String effect) {
			this.effect = effect;
		}

		public String getEffect() {
			return this.effect;
		}

		public void setNumhu(String numhu) {
			this.numhu = numhu;
		}

		public String getNumhu() {
			return this.numhu;
		}

		public void setNumpro(String numpro) {
			this.numpro = numpro;
		}

		public String getNumpro() {
			return this.numpro;
		}

		public void setNumorder(String numorder) {
			this.numorder = numorder;
		}

		public String getNumorder() {
			return this.numorder;
		}

		public void setNumgoods(String numgoods) {
			this.numgoods = numgoods;
		}

		public String getNumgoods() {
			return this.numgoods;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getUid() {
			return this.uid;
		}

		public void setDatesend(String datesend) {
			this.datesend = datesend;
		}

		public String getDatesend() {
			return this.datesend;
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
