package com.xiaba2.invest.service;

import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.IUserTokenDao;
import com.xiaba2.invest.domain.UserToken;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class UserTokenService extends BaseService<UserToken, UUID> {
	@Resource
	private IUserTokenDao userTokenDao;

	@Override
	protected IBaseDao<UserToken, UUID> getEntityDao() {
		return userTokenDao;
	}
}