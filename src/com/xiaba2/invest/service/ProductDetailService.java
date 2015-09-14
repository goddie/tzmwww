package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.IProductDetailDao;
import com.xiaba2.invest.domain.ProductDetail;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class ProductDetailService extends BaseService<ProductDetail, UUID> {
@Resource
private IProductDetailDao productDetailDao;
@Override
protected IBaseDao<ProductDetail, UUID> getEntityDao() {
return productDetailDao;
}
}