package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * UUserBaseId entity. @author MyEclipse Persistence Tools
 */

public class UUserBaseId implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String tel;
	private String email;
	private Timestamp birth;
	private String sex;
	private String cardnum;
	private String area;
	private String QQuestioin;
	private String QAnswer;

	// Constructors

	/** default constructor */
	public UUserBaseId() {
	}

	/** full constructor */
	public UUserBaseId(Integer userid, String tel, String email,
			Timestamp birth, String sex, String cardnum, String area,
			String QQuestioin, String QAnswer) {
		this.userid = userid;
		this.tel = tel;
		this.email = email;
		this.birth = birth;
		this.sex = sex;
		this.cardnum = cardnum;
		this.area = area;
		this.QQuestioin = QQuestioin;
		this.QAnswer = QAnswer;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getBirth() {
		return this.birth;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardnum() {
		return this.cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getQQuestioin() {
		return this.QQuestioin;
	}

	public void setQQuestioin(String QQuestioin) {
		this.QQuestioin = QQuestioin;
	}

	public String getQAnswer() {
		return this.QAnswer;
	}

	public void setQAnswer(String QAnswer) {
		this.QAnswer = QAnswer;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UUserBaseId))
			return false;
		UUserBaseId castOther = (UUserBaseId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null && castOther.getEmail() != null && this
						.getEmail().equals(castOther.getEmail())))
				&& ((this.getBirth() == castOther.getBirth()) || (this
						.getBirth() != null && castOther.getBirth() != null && this
						.getBirth().equals(castOther.getBirth())))
				&& ((this.getSex() == castOther.getSex()) || (this.getSex() != null
						&& castOther.getSex() != null && this.getSex().equals(
						castOther.getSex())))
				&& ((this.getCardnum() == castOther.getCardnum()) || (this
						.getCardnum() != null && castOther.getCardnum() != null && this
						.getCardnum().equals(castOther.getCardnum())))
				&& ((this.getArea() == castOther.getArea()) || (this.getArea() != null
						&& castOther.getArea() != null && this.getArea()
						.equals(castOther.getArea())))
				&& ((this.getQQuestioin() == castOther.getQQuestioin()) || (this
						.getQQuestioin() != null
						&& castOther.getQQuestioin() != null && this
						.getQQuestioin().equals(castOther.getQQuestioin())))
				&& ((this.getQAnswer() == castOther.getQAnswer()) || (this
						.getQAnswer() != null && castOther.getQAnswer() != null && this
						.getQAnswer().equals(castOther.getQAnswer())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result
				+ (getBirth() == null ? 0 : this.getBirth().hashCode());
		result = 37 * result
				+ (getSex() == null ? 0 : this.getSex().hashCode());
		result = 37 * result
				+ (getCardnum() == null ? 0 : this.getCardnum().hashCode());
		result = 37 * result
				+ (getArea() == null ? 0 : this.getArea().hashCode());
		result = 37
				* result
				+ (getQQuestioin() == null ? 0 : this.getQQuestioin()
						.hashCode());
		result = 37 * result
				+ (getQAnswer() == null ? 0 : this.getQAnswer().hashCode());
		return result;
	}

}