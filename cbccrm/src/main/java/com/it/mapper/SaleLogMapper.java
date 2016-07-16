package com.it.mapper;

import com.it.pojo.SaleLog;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/15.
 * SaleLogMapper
 */
public interface SaleLogMapper {
    void insertSaleLog(SaleLog saleLog);
    List<SaleLog> querySaleLog (Map<String,Object> map);
}
