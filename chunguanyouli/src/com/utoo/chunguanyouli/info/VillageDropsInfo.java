package com.utoo.chunguanyouli.info;

import java.util.List;

public class VillageDropsInfo {

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

	public static class Val {
		private String id;
		private String title;
		private String content;
		private String datesend;
		private String pic;
		private String uid;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getDatesend() {
			return datesend;
		}

		public void setDatesend(String datesend) {
			this.datesend = datesend;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

	}

}
