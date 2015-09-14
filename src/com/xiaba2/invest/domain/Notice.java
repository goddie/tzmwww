package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

 
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 系统通知
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_notice")
public class Notice extends BaseUUIDEntity {
 
	
	/**
	 * 内容
	 */
	@Column(length=1000)
	private String content;
	
	/**
	 * 标题
	 */
	@Column
	private String title;
	
	/**
	 * 发送方
	 */
	@ManyToOne
	private User from;
	
	/**
	 * 接受方
	 */
	@ManyToOne
	private User sendTo;
	
	/**
	 * 0 未读
	 * 1 已读
	 */
	@Column
	private int status;
	
	/**
	 * 消息类型 
	 */
	@Column
	private int type;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getSendTo() {
		return sendTo;
	}

	public void setSendTo(User sendTo) {
		this.sendTo = sendTo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
 
}
