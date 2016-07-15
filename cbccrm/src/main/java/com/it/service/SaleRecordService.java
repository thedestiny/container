package com.it.service;

/**
 * Created by xieyue on 2016/7/15.
 * SaleRecordService
 */


import com.it.mapper.SaleRecordMapper;
import com.it.pojo.Custom;
import com.it.pojo.SaleRecord;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SaleRecordService {
    Logger logger = LoggerFactory.getLogger(SaleRecordService.class);

    @Inject
    private SaleRecordMapper saleRecordMapper;

    @Inject
    private CustomService customService;

    /**
     * 添加创建时间、客户名、创建人id、创建人
     * 添加新的销售记录
     */
    public void addNewSaleRecord(SaleRecord saleRecord){

        Integer customerid = saleRecord.getCustomerid();
        Custom custom = customService.findCustomById(customerid);
        saleRecord.setCustomer(custom.getCustomer());
        saleRecord.setUserid(ShiroUtil.getCurrentUserId());
        saleRecord.setRealname(ShiroUtil.getCurrentRealname());
        saleRecord.setCreatetime(SmallUtils.getTime());
        logger.debug("saleRecord is {}",saleRecord);
        saleRecordMapper.addSaleRecord(saleRecord);
    }




}
