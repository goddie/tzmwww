package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.I.DS_StoreDao;
import com.xiaba2.cms.domain..DS_Store;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class .DS_StoreService extends BaseService<.DS_Store, UUID> {
@Resource
private I.DS_StoreDao .DS_StoreDao;
@Override
protected IBaseDao<.DS_Store, UUID> getEntityDao() {
return .DS_StoreDao;
}
}