package com.xiaba2.invest.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.invest.dao.IUserContactDao;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.domain.UserContact;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class UserContactService extends BaseService<UserContact, UUID> {
	@Resource
	private IUserContactDao userContactDao;
 

	@Override
	protected IBaseDao<UserContact, UUID> getEntityDao() {
		return userContactDao;
	}
	
	
	/**
	 * 关注之后更新联系人关系
	 * @param from
	 * @param sendTo
	 * @return
	 */
	@Transactional
	public void updateByFollow(User from,User sendTo)
	{

		DetachedCriteria criteria = userContactDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", from));
		criteria.add(Restrictions.eq("contact", sendTo));
		List<UserContact> list = findByCriteria(criteria);

//		UserContact userContact =new UserContact();
		
		if (list.isEmpty()) {
			
			
			
//			UserContact userContact =new UserContact();
//			userContact.setName(sendTo.getNickname());
//			userContact.setUser(from);
//			userContact.setContact(sendTo);
//			userContact.setIsFollow(1);
//			userContact.setCreatedDate(new Date());
//			userContact.setPhone(sendTo.getPhone());
//			userContactDao.save(userContact);
		}else
		{
			UserContact userContact = list.get(0);
			userContact.setIsFollow(1);
			userContactDao.saveOrUpdate(userContact);
		}

		//更新粉丝
		DetachedCriteria criteria2 = userContactDao.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		criteria2.add(Restrictions.eq("user", sendTo));
		criteria2.add(Restrictions.eq("contact", from));
		List<UserContact> list2 = findByCriteria(criteria2);
		
		if(list2.isEmpty())
		{
//			UserContact userContact =new UserContact();
//			userContact.setUser(sendTo);
//			userContact.setContact(from);
//			userContact.setIsFan(1);
//			userContact.setCreatedDate(new Date());
//			userContact.setName(from.getNickname());
//			userContact.setPhone(from.getPhone());
//			
//			userContactDao.save(userContact);	
		}
		else
		{
			UserContact userContact = list2.get(0);
			userContact.setIsFan(1);
			userContactDao.saveOrUpdate(userContact);
		}
	}
	
	
	@Transactional
	public UserContact getByPhone(String phone,UUID uid)
	{
		if(StringUtils.isEmpty(phone))
		{
			return null;
		}
		
		
		DetachedCriteria criteria = userContactDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.eq("user.id", uid));

		List<UserContact> list = findByCriteria(criteria);

		if (list.isEmpty()) {
			return null;
		}
		
		return list.get(0);
	}
	
	/**
	 * 通过联系人和主人返回记录
	 * @param user 主人
	 * @param contact 联系人
	 * @return
	 */
	@Transactional
	public UserContact getUserContact(User user,User contact)
	{	
		DetachedCriteria criteria = userContactDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("contact", contact));
		
		List<UserContact> list = findByCriteria(criteria);
		
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
	
	
	
	@Transactional
	public void deleteContact(UserContact userContact)
	{	
		userContact.setIsDelete(1);
		userContactDao.saveOrUpdate(userContact);
//		DetachedCriteria criteria = userContactDao.createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//		criteria.add(Restrictions.eq("user", user));
//		criteria.add(Restrictions.eq("contact", contact));
//		
//		List<UserContact> list = findByCriteria(criteria);
//		
//		if(!list.isEmpty())
//		{
//			UserContact userContact = list.get(0);
//			userContact.setIsDelete(1);
//			
//			userContactDao.saveOrUpdate(userContact);
//		}
	}
	
	
}