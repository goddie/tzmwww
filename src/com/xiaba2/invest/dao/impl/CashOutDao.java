package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.ICashOutDao;
import com.xiaba2.invest.domain.CashOut;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class CashOutDao extends AbstractHibernateDao<CashOut, UUID> implements
ICashOutDao {
}