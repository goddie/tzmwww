package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 用户的通讯录
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_usercontact")
public class UserContact extends BaseUUIDEntity {

	/**
	 * 用户
	 */
	@ManyToOne
	private User user;
	
	/**
	 * 姓名
	 */
	@Column
	private String name;
	
	/**
	 * 电话
	 */
	@Column
	private String phone;
	
	@Column
	private String phone2;
	
	@Column
	private String phone3;
	/**
	 * 联系人
	 */
	@ManyToOne
	private User contact;
	
	/**
	 * 小猫推荐
	 */
	@Column
	private int isRecommend;
 
	
	/**
	 * 粉丝
	 */
	@Column
	private int isFan;
	
	
	/**
	 * 关注他
	 */
	@Column
	private int isFollow;
	
 

	public int getIsFan() {
		return isFan;
	}

	public void setIsFan(int isFan) {
		this.isFan = isFan;
	}

	public int getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getContact() {
		return contact;
	}

	public void setContact(User contact) {
		this.contact = contact;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	
	
}
