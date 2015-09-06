package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IUserTokenDao;
import com.xiaba2.invest.domain.UserToken;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class UserTokenDao extends AbstractHibernateDao<UserToken, UUID> implements
IUserTokenDao {
}