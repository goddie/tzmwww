package com.xiaba2.invest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 验证token
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_usertoken")
public class UserToken extends BaseUUIDEntity {

	@ManyToOne
	private User user;
	
	@Column
	private String token;
	
	@Column
	private String key;
	
	/**
	 * 到期时间
	 */
	@Column
	private Date expired;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}
	
	
	
	
}
