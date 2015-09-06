package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IOrderDao;
import com.xiaba2.invest.domain.Order;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class OrderDao extends AbstractHibernateDao<Order, UUID> implements
IOrderDao {
}