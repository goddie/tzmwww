package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 用户购买的产品
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_userproduct")
public class UserProduct extends BaseUUIDEntity {

	@ManyToOne
	private User uesr;
	
	@ManyToOne
	private Product product;
	
	/**
	 * 是否公开
	 */
	@Column
	private int isShow;

	/**
	 * 拥有数量
	 */
	@Column
	private float amount;
	
	@ManyToOne
	private User follow;

	public float getAmount() {
		return amount;
	}

	public User getFollow() {
		return follow;
	}

	public int getIsShow() {
		return isShow;
	}

	public Product getProduct() {
		return product;
	}

	public User getUesr() {
		return uesr;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setFollow(User follow) {
		this.follow = follow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUesr(User uesr) {
		this.uesr = uesr;
	}
	
	
}
