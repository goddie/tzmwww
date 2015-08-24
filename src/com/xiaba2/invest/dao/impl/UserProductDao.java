package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IUserProductDao;
import com.xiaba2.invest.domain.UserProduct;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class UserProductDao extends AbstractHibernateDao<UserProduct, UUID> implements
IUserProductDao {
}