package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 提现
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_cashout")
public class CashOut extends BaseUUIDEntity {
	
	@ManyToOne
	private User user;
	
	@Column
	private float total;
	
	@Column
	private int status;
	
	@Column
	private String info;
	
	/**
	 * 提现操作管理员
	 */
	@ManyToOne
	private Member operator;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Member getOperator() {
		return operator;
	}

	public void setOperator(Member operator) {
		this.operator = operator;
	}
	
	
}
