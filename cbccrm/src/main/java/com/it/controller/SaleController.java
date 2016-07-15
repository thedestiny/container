package com.it.controller;

/**
 * Created by xieyue on 2016/7/15.
 * SaleController
 */


import com.it.pojo.SaleRecord;
import com.it.service.CustomService;
import com.it.service.SaleRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;


@Controller
@RequestMapping("/sale")
public class SaleController {
    Logger logger = LoggerFactory.getLogger(SaleController.class);


    @Inject
    private SaleRecordService saleRecordService;

    @Inject
    private CustomService customService;


    /**
     * 获取销售列表界面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showSalePage(){

        return "sale/salelist";
    }

    /**
     * ajax 提交销售记录
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addNewSaleRecord(SaleRecord saleRecord){
        saleRecordService.addNewSaleRecord(saleRecord);
        return "success";
    }






}
