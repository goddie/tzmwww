package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IProductDetailDao;
import com.xiaba2.invest.domain.ProductDetail;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ProductDetailDao extends AbstractHibernateDao<ProductDetail, UUID> implements
IProductDetailDao {
}