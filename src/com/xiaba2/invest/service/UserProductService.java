package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.IUserProductDao;
import com.xiaba2.invest.domain.UserProduct;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class UserProductService extends BaseService<UserProduct, UUID> {
@Resource
private IUserProductDao userProductDao;
@Override
protected IBaseDao<UserProduct, UUID> getEntityDao() {
return userProductDao;
}
}