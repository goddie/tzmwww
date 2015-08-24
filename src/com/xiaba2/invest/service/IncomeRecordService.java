package com.xiaba2.invest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.invest.dao.IIncomeRecordDao;
import com.xiaba2.invest.domain.IncomeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.core.Page;

@Service
public class IncomeRecordService extends BaseService<IncomeRecord, UUID> {
	@Resource
	private IIncomeRecordDao incomeRecordDao;

	@Override
	protected IBaseDao<IncomeRecord, UUID> getEntityDao() {
		return incomeRecordDao;
	}
	
	/**
	 * 收益最好的x个人
	 * @param count
	 * @return
	 */
	@Transactional
	public List<User> getTopUser(int count)
	{
		DetachedCriteria criteria = incomeRecordDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		
		Page<IncomeRecord> page = new Page<IncomeRecord>();
		page.setPageNo(1);
		page.setPageSize(count);
		page.addOrder("income", "desc");
		
		page = incomeRecordDao.findPageByCriteria(criteria, page);
		
		List<User> list = new ArrayList<User>();
		
		for (IncomeRecord ir : page.getResult()) {
			
			list.add(ir.getUser());
		}
		
		return list;
	}
}