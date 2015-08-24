package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IIncomeRecordDao;
import com.xiaba2.invest.domain.IncomeRecord;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class IncomeRecordDao extends AbstractHibernateDao<IncomeRecord, UUID> implements
IIncomeRecordDao {
}