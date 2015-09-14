package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_invest_productdetail")
public class ProductDetail extends BaseUUIDEntity {

	/**
	 * 类型 1  购买须知  2 产品详情
	 * 
	 */
	@Column
	private int type;
	
	/**
	 * 内容
	 */
	@Column(length=1000)
	private String content;
	
	/**
	 * 产品
	 */
	@ManyToOne
	private Product product;
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
