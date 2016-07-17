package com.it.controller;

/**
 * Created by xieyue on 2016/7/15.
 * SaleController
 */


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.it.dto.DataTablesResult;
import com.it.dto.JSONResult;
import com.it.pojo.Document;
import com.it.pojo.SaleFile;
import com.it.pojo.SaleLog;
import com.it.pojo.SaleRecord;
import com.it.service.SaleRecordService;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sale")
public class SaleRecordController {
    Logger logger = LoggerFactory.getLogger(SaleRecordController.class);


    @Inject
    private SaleRecordService saleRecordService;

    @Value("${file.documentpath}")
    private String filepath;

    /**
     * 获取销售列表界面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showSalePage() {

        return "sale/salelist";
    }

    /**
     * ajax 提交销售记录
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addNewSaleRecord(SaleRecord saleRecord) {
        saleRecordService.addNewSaleRecord(saleRecord);
        return "success";
    }

    /**
     * dataTable 获取数据列
     */
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<SaleRecord> showSaleDataTable(HttpServletRequest request) {

        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");

        String salename = request.getParameter("salename");
        String proce = request.getParameter("proce");
        String starts = request.getParameter("starts");
        String ends = request.getParameter("ends");

        keyword = SmallUtils.transtoUTF8(keyword);
        salename = SmallUtils.transtoUTF8(salename);
        proce = SmallUtils.transtoUTF8(proce);
        starts = SmallUtils.transtoUTF8(starts);
        if(ends != null){
            ends = SmallUtils.transtoUTF8(ends + " 23:59:59");
        } else{
            ends = SmallUtils.transtoUTF8(ends);
        }


        Map<String, Object> param = Maps.newHashMap();
        param.put("start", start);
        param.put("length", length);
        param.put("keyword", keyword);

        param.put("salename", salename);
        param.put("proce", proce);
        param.put("starts", starts);
        param.put("ends", ends);

        logger.debug("salename is {}proce is {}starts is {}ends is {}", salename, proce, starts, ends);
        List<SaleRecord> saleRecordList = saleRecordService.findSaleRecordList(param);
        Long total = saleRecordService.querySaleRecordTotal();
        Long filter = saleRecordService.querySaleRecordByParams(param);

        return new DataTablesResult<>(draw, saleRecordList, total, filter);
    }

    /**
     * 跳转交易详情页
     * 获取文件列表
     */
    @RequestMapping(value = "/detail/{id:\\d+}", method = RequestMethod.GET)
    public String showDetailsPage(@PathVariable Integer id, Model model) {
        SaleRecord saleRecord = saleRecordService.findSaleRecordById(id);
        List<SaleFile> saleFileList = saleRecordService.findSalefileBySaleid(id);
        List<SaleLog> saleLogList = saleRecordService.querySaleLog(id);
        model.addAttribute("saleRecord", saleRecord);
        model.addAttribute("saleFileList",saleFileList);
        List<SaleLog> saleLogList1 = Lists.newArrayList();
        for(SaleLog saleLog : saleLogList){
            String  time = saleLog.getCreatetime();
            saleLog.setCreatetime(SmallUtils.transTime(time));
            saleLogList1.add(saleLog);
        }
        model.addAttribute("saleLogList",saleLogList1);
        return "sale/saledetails";
    }

    /**
     * 提交交易备忘
     */

    @RequestMapping(value = "/log/add",method = RequestMethod.POST)
    @ResponseBody
    public String addSaleLog(SaleLog saleLog){
        saleRecordService.addNewSaleLog(saleLog);
        return "success";
    }

    /**
     * datatable 销售记录备忘列表
     */
    @RequestMapping(value = "/log/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public List<SaleLog> showSaleLog(@PathVariable Integer id){
        List<SaleLog> saleLogList1 = Lists.newArrayList();
        List<SaleLog> saleLogList = saleRecordService.querySaleLog(id);
        for(SaleLog saleLog : saleLogList){
            String  time = saleLog.getCreatetime();
            saleLog.setCreatetime(SmallUtils.transTime(time));
            saleLogList1.add(saleLog);
        }
        return saleLogList1;
    }
    /**
     * 修改该进度
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public String modifyTheProcess(SaleRecord saleRecord){
        logger.debug("id is {}",saleRecord.getId());
        logger.debug("id is {}",saleRecord.getProcess());
        saleRecordService.updateProcess(saleRecord);
        return "success";
    }

    /**
     * 上传文件资料
     */
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile file,Integer saleid)  {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        } else {
            logger.debug("saleid is {}",saleid);
            try {
                saleRecordService.uploadFile(file, saleid);
            } catch (IOException e) {
                throw new RuntimeException("文件上传失败");
            }
        }

        return "success";
    }

    /**
     * 文件下载
     */
    @RequestMapping(value = "/down/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer id) throws FileNotFoundException {

        SaleFile saleFile = saleRecordService.findSalefileById(id);

        if (saleFile == null) {
            throw new RuntimeException("文件请假回家了。。。");
        }
        File file = new File(filepath, saleFile.getSavename());
        if (!file.exists()) {
            throw new RuntimeException("文件回火星了。。");
        }
        FileInputStream inputStream = new FileInputStream(file);
        String filename = saleFile.getFilename();
        filename = SmallUtils.transtoISO(filename);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(saleFile.getContenttype()))
                .contentLength(saleFile.getSize())
                .header("Content-Disposition", "attachment;filename=\"" + filename + "\"")
                .body(new InputStreamResource(inputStream));
    }


    /**
     * 删除销售记录
     */

    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String deleteTheSaleRecord(@PathVariable Integer id){
        saleRecordService.deleteSaleRecordById(id);
        return "success";
    }









}
