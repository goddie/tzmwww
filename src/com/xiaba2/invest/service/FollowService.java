package com.xiaba2.invest.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.invest.dao.IFollowDao;
import com.xiaba2.invest.domain.Follow;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.domain.UserContact;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class FollowService extends BaseService<Follow, UUID> {
	@Resource
	private IFollowDao followDao;

	@Override
	protected IBaseDao<Follow, UUID> getEntityDao() {
		return followDao;
	}
	
	
	
	/**
	 * 删除关注
	 * @param user
	 * @param contact
	 */
	@Transactional
	public void deleteFollow(User user,User contact)
	{	
		DetachedCriteria criteria = followDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("from", user));
		criteria.add(Restrictions.eq("sendTo", contact));
		
		List<Follow> list = findByCriteria(criteria);
		
		if(!list.isEmpty())
		{
			Follow follow = list.get(0);
			follow.setIsDelete(1);
			
			followDao.saveOrUpdate(follow);
		}
	}
	
	
	
	/**
	 * 通过联系人和主人返回记录
	 * @param user 主人
	 * @param contact 联系人
	 * @return
	 */
	@Transactional
	public Follow getUserContact(User user,User contact)
	{	
		DetachedCriteria criteria = followDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("from", user));
		criteria.add(Restrictions.eq("sendTo", contact));
		
		List<Follow> list = findByCriteria(criteria);
		
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
}