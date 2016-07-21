package com.it.service;

/**
 * Created by xieyue on 2016/7/19.
 */


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.it.mapper.SaleRecordMapper;
import com.it.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class ChartService {
    Logger logger = LoggerFactory.getLogger(ChartService.class);

    @Inject
    private SaleRecordMapper saleRecordMapper;

    /**
     * 查询员工的销售额
     */
    public Map<String, Object> countEmployeeSales(String start, String end) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("start", start);
        map.put("end", end);
        if (ShiroUtil.isEmployee()) {
            map.put("userid", ShiroUtil.getCurrentUserId());
        }

        List<Map<String, Object>> result = saleRecordMapper.countEmployeeSale(map);
        List<String> realnameList = Lists.newArrayList();
        List<Object> amountList = Lists.newArrayList();
        for (Map<String, Object> map1 : result) {
            for (Map.Entry<String, Object> entry : map1.entrySet()) {
                if (entry.getKey().equals("realname")) {
                    realnameList.add(entry.getValue().toString());
                }
                if (entry.getKey().equals("amount")) {
                    amountList.add(entry.getValue());
                }
            }
        }

        Map<String, Object> trans = Maps.newHashMap();

        trans.put("names", realnameList);
        trans.put("value", amountList);
        return trans;
    }

    /**
     * 查询进展状况
     */

    public List<Map<String, Object>> countProcessNum(String start, String end) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("start", start);
        map.put("end", end);
        if (ShiroUtil.isEmployee()) {
            map.put("userid", ShiroUtil.getCurrentUserId());
        }
        return saleRecordMapper.countProcessNum(map);
    }

    /**
     * 客户总数
     * @param start
     * @param end
     * @return
     */
    public Long countCustomNum(String start, String end) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("start", start);
        map.put("end", end);
        if (ShiroUtil.isEmployee()) {
            map.put("userid", ShiroUtil.getCurrentUserId());
        }
        return saleRecordMapper.countSaleRecordNum(map);
    }

    /**
     * 成交数量
     * @param start
     * @param end
     * @return
     */

    public Long countDealSaleNum(String start, String end) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("start", start);
        map.put("end", end);
        map.put("process", "完成交易");
        if (ShiroUtil.isEmployee()) {
            map.put("userid", ShiroUtil.getCurrentUserId());
        }
        return saleRecordMapper.countSaleRecordNum(map);
    }

    /**
     * 成交额
     * @param start
     * @param end
     * @return
     */
    public Float countSaleTotal(String start, String end) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("start", start);
        map.put("end", end);
        if (ShiroUtil.isEmployee()) {
            map.put("userid", ShiroUtil.getCurrentUserId());
        }
        return saleRecordMapper.countSaleTotal(map);

    }

    /**
     * 查询客户成交额
     */
    public Map<String, Object> countCustomTotal(String start, String end){
        Map<String, Object> map = Maps.newHashMap();
        map.put("start", start);
        map.put("end", end);
        if (ShiroUtil.isEmployee()) {
            map.put("userid", ShiroUtil.getCurrentUserId());
        }

        List<Map<String, Object>> result = saleRecordMapper.countCustomSale(map);
        List<String> customerList = Lists.newArrayList();
        List<Object> totalList = Lists.newArrayList();
        for (Map<String, Object> map1 : result) {
            for (Map.Entry<String, Object> entry : map1.entrySet()) {
                if (entry.getKey().equals("customer")) {
                    customerList.add(entry.getValue().toString());
                }
                if (entry.getKey().equals("total")) {
                    totalList.add(entry.getValue());
                }
            }
        }

        Map<String, Object> trans = Maps.newHashMap();
        trans.put("names", customerList);
        trans.put("value", totalList);
        return trans;
    }



}
