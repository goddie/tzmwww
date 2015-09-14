package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 用户购买的产品
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_userproduct")
public class UserProduct extends BaseUUIDEntity {


	/**
	 * 持有
	 */
	@Column
	private float amount;
 
	
	/**
	 * 用户
	 */
	@ManyToOne
	private User user;
	
	/**
	 * 产品
	 */
	@ManyToOne
	private Product product;
	
	/**
	 * 同意购买
	 */
	@Column
	private int agreeBuy;
	
	/**
	 * 公开收益
	 */
	@Column
	private int isShow;
	
	/**
	 * 同步买入
	 */
	@Column
	private int synBuy;
	
	/**
	 * 同步卖出
	 */
	@Column
	private int synSell;
	
	/**
	 * 跟买
	 */
	@ManyToOne
	private User follow;
	
	/**
	 * 是否有人跟买
	 */
	@Column
	private int hasFans;
	
 
	/**
	 * 订单号
	 */
	@Column
	private String tradeNo;

	
	/**
	 * 昨日收益
	 */
	@Column
	private float lastIncome;
	
	/**
	 * 总收益
	 */
	@Column
	private float totalIncome;
	
	/**
	 * 持有产品状态
	 * 0无效 1有效收益 
	 */
	@Column
	private int status;
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAgreeBuy() {
		return agreeBuy;
	}

	public void setAgreeBuy(int agreeBuy) {
		this.agreeBuy = agreeBuy;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getSynBuy() {
		return synBuy;
	}

	public void setSynBuy(int synBuy) {
		this.synBuy = synBuy;
	}

	public int getSynSell() {
		return synSell;
	}

	public void setSynSell(int synSell) {
		this.synSell = synSell;
	}

	public User getFollow() {
		return follow;
	}

	public void setFollow(User follow) {
		this.follow = follow;
	}

	public int getHasFans() {
		return hasFans;
	}

	public void setHasFans(int hasFans) {
		this.hasFans = hasFans;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public float getLastIncome() {
		return lastIncome;
	}

	public void setLastIncome(float lastIncome) {
		this.lastIncome = lastIncome;
	}

	public float getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(float totalIncome) {
		this.totalIncome = totalIncome;
	}
	
	
	
}
