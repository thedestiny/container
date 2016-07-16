package com.it.service;

/**
 * Created by xieyue on 2016/7/16.
 * SaleLogMapperTestCase
 */


import com.it.mapper.SaleRecordMapper;
import com.it.pojo.SaleRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class SaleLogMapperTestCase {
    Logger logger = LoggerFactory.getLogger(SaleLogMapperTestCase.class);

    @Inject
    private SaleRecordMapper saleRecordMapper;

    @Test
    public void insertSaleLogTest(){
        SaleRecord saleRecord = new SaleRecord();
        saleRecord.setSalename("123465");
        saleRecord.setCreatetime("4651356");
        saleRecord.setUserid(8);
        saleRecord.setCustomerid(1);
        saleRecordMapper.addSaleRecord(saleRecord);
        logger.debug("n is {}",saleRecord.getId());

    }


}
