package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


/**
 * 关注
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_follow")
public class Follow extends BaseUUIDEntity {

	/**
	 * 发起人
	 */
	@ManyToOne
	private User from;


	/**
	 * 接受人
	 */
	@ManyToOne
	private User sendTo;


	/**
	 * 0 未接受 1 已好友 2 黑名单
	 */
	@Column
	private int status;
	
	/**
	 * 关注我
	 */
	@Column
	private int isFan;
	
	/**
	 * 我已关注
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


	public User getFrom() {
		return from;
	}


	public User getSendTo() {
		return sendTo;
	}


	public int getStatus() {
		return status;
	}


	public void setFrom(User from) {
		this.from = from;
	}
	
	
	public void setSendTo(User sendTo) {
		this.sendTo = sendTo;
	}
	
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
