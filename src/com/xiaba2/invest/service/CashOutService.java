package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.ICashOutDao;
import com.xiaba2.invest.domain.CashOut;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class CashOutService extends BaseService<CashOut, UUID> {
@Resource
private ICashOutDao cashOutDao;
@Override
protected IBaseDao<CashOut, UUID> getEntityDao() {
return cashOutDao;
}
}