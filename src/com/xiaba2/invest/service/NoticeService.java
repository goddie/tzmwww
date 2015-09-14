package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.INoticeDao;
import com.xiaba2.invest.domain.Notice;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class NoticeService extends BaseService<Notice, UUID> {
@Resource
private INoticeDao noticeDao;
@Override
protected IBaseDao<Notice, UUID> getEntityDao() {
return noticeDao;
}
}