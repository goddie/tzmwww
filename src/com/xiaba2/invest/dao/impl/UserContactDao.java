package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IUserContactDao;
import com.xiaba2.invest.domain.UserContact;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class UserContactDao extends AbstractHibernateDao<UserContact, UUID> implements
IUserContactDao {
}