package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 
 * 收益记录
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_incomerecord")
public class IncomeRecord extends BaseUUIDEntity {

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
	 * 利润
	 */
	@Column
	private float profit;
	
	
	/**
	 * 本金
	 */
	@Column
	private float capital;
	
	
	/**
	 * 当前产品年化
	 */
	@Column
	private float yearRate;
	
	
	/**
	 * 7日年化
	 */
	@Column
	private float weekRate;
	
	
	/**
	 * 万份收益
	 */
	@Column
	private float tenThousand;
	
	
	/**
	 * 投资天数
	 */
	@Column
	private int days;
	
	/**
	 * 收益
	 */
	@Column
	private float income;
	
	/**
	 * 收益率
	 */
	@Column
	private float incomePercent;
	
	


	public float getIncome() {
		return income;
	}


	public void setIncome(float income) {
		this.income = income;
	}


	public float getIncomePercent() {
		return incomePercent;
	}


	public void setIncomePercent(float incomePercent) {
		this.incomePercent = incomePercent;
	}


	public float getCapital() {
		return capital;
	}


	public int getDays() {
		return days;
	}


	public Product getProduct() {
		return product;
	}


	public float getProfit() {
		return profit;
	}


	public float getTenThousand() {
		return tenThousand;
	}


	public User getUser() {
		return user;
	}


	public float getWeekRate() {
		return weekRate;
	}


	public float getYearRate() {
		return yearRate;
	}


	public void setCapital(float capital) {
		this.capital = capital;
	}


	public void setDays(int days) {
		this.days = days;
	}
 

	public void setProduct(Product product) {
		this.product = product;
	}


	public void setProfit(float profit) {
		this.profit = profit;
	}


	public void setTenThousand(float tenThousand) {
		this.tenThousand = tenThousand;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setWeekRate(float weekRate) {
		this.weekRate = weekRate;
	}


	public void setYearRate(float yearRate) {
		this.yearRate = yearRate;
	}
	
	
	
	
	
}
