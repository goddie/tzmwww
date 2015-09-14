package com.xiaba2.invest.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.invest.dao.ITradeRecordDao;
import com.xiaba2.invest.domain.Order;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class TradeRecordService extends BaseService<TradeRecord, UUID> {
	@Resource
	private ITradeRecordDao tradeRecordDao;

	@Override
	protected IBaseDao<TradeRecord, UUID> getEntityDao() {
		return tradeRecordDao;
	}
	
	
	/**
	 * 通过订单号获取交易纪录 
	 * @param tradeNo
	 * @return
	 */
	@Transactional
	public TradeRecord getByTradeNo(String tradeNo)
	{
		DetachedCriteria criteria = tradeRecordDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("tradeNo", tradeNo));
		
		List<TradeRecord> list = tradeRecordDao.findByCriteria(criteria);
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
}