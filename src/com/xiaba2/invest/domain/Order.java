package com.xiaba2.invest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_invest_order")
public class Order extends BaseUUIDEntity {
	
	/**
	 * 用户
	 */
	@ManyToOne
	private User user;
	
	/**
	 * 订单号
	 */
	@Column
	private String tradeNo;
	
	
	/**
	 * 产品
	 */
	@ManyToOne
	private Product product;
	
	
	/**
	 * 支付状态
	 */
	@Column
	private int status;
	
	
	/**
	 * 产品名
	 */
	@Column
	private String name;
	
	
	/**
	 * 数量
	 */
	@Column
	private float count;
	
	/**
	 * 总金额
	 */
	@Column
	private float total;
	
	/**
	 * 单价
	 */
	@Column
	private float price;
	
	/**
	 * 支付方式
	 */
	@Column
	private int payType;
	
	/**
	 * 支付时间
	 */
	@Column
	private Date payDate;
	
	
	/**
	 * 支付宝支付通知
	 */
	@Column
	private String alipayServer;
	
	/**
	 * 说明备注
	 */
	@Column
	private String info;
	
	
	

	public String getAlipayServer() {
		return alipayServer;
	}

	public void setAlipayServer(String alipayServer) {
		this.alipayServer = alipayServer;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

 

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

 

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
}
