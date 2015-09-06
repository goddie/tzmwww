package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.IUserContactDao;
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
}