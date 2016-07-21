package com.it.controller;

/**
 * Created by xieyue on 2016/7/19.
 * ChartController
 */


import com.it.dto.JSONResult;
import com.it.service.ChartService;
import com.it.service.SaleRecordService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chart")
public class ChartController {
    Logger logger = LoggerFactory.getLogger(ChartController.class);

    @Inject
    private ChartService chartService;

    @RequestMapping(method = RequestMethod.GET)
    public String getChartPage(Model model,
                               @RequestParam(required = false, defaultValue = "") String start,
                               @RequestParam(required = false, defaultValue = "") String end) {

        Long customNum = chartService.countCustomNum(start, end);
        Long dealSaleNum = chartService.countDealSaleNum(start, end);
        Float saleTotal = chartService.countSaleTotal(start, end);

        model.addAttribute("customNum", customNum);
        model.addAttribute("dealSaleNum", dealSaleNum);
        model.addAttribute("saleTotal", saleTotal);
        logger.debug("customNum is {}",customNum);
        logger.debug("dealSaleNum is {}",dealSaleNum);
        logger.debug("saleTotal is {}",saleTotal);

        return "/chart/home";
    }


    /**
     * 获取员工销售图
     * @return
     */

    @RequestMapping(value = "/employee/data",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getEmployeeSaleChart(
            @RequestParam(required = false, defaultValue = "") String start,
            @RequestParam(required = false, defaultValue = "") String end) {
        Map<String,Object> map = chartService.countEmployeeSales(start,end);
        return new JSONResult(map);
    }

    @RequestMapping(value = "/process/data",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getProcessSaleRecordChart(
            @RequestParam(required = false, defaultValue = "") String start,
            @RequestParam(required = false, defaultValue = "") String end) {
        List<Map<String,Object>> list = chartService.countProcessNum(start,end);
        return new JSONResult(list);
    }

    @RequestMapping(value = "/custom/data",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getCustomSaleChart(
            @RequestParam(required = false, defaultValue = "") String start,
            @RequestParam(required = false, defaultValue = "") String end) {
        Map<String,Object> map = chartService.countCustomTotal(start,end);
        return new JSONResult(map);
    }




}
