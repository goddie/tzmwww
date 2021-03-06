package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 聊天纪录
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_message")
public class Message extends BaseUUIDEntity {
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
	 * 内容
	 */
	@Column(length=1000)
	private String content;

	/**
	 * 状态
	 * 0 未读取
	 * 1 已读取
	 */
	@Column
	private int status;
	
	
	

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getContent() {
		return content;
	}


	public User getFrom() {
		return from;
	}


	public User getSendTo() {
		return sendTo;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setFrom(User from) {
		this.from = from;
	}
	
	
	public void setSendTo(User sendTo) {
		this.sendTo = sendTo;
	}
}
