package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.INoticeDao;
import com.xiaba2.invest.domain.Notice;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class NoticeDao extends AbstractHibernateDao<Notice, UUID> implements
INoticeDao {
}