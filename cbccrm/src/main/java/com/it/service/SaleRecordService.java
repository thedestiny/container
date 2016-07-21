package com.it.service;

/**
 * Created by xieyue on 2016/7/15.
 * SaleRecordService
 */


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.it.mapper.SaleFileMapper;
import com.it.mapper.SaleLogMapper;
import com.it.mapper.SaleRecordMapper;
import com.it.pojo.Custom;
import com.it.pojo.SaleFile;
import com.it.pojo.SaleLog;
import com.it.pojo.SaleRecord;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Named
public class SaleRecordService {
    Logger logger = LoggerFactory.getLogger(SaleRecordService.class);

    @Inject
    private SaleRecordMapper saleRecordMapper;

    @Inject
    private CustomService customService;

    @Inject
    private SaleLogMapper saleLogMapper;

    @Inject
    private SaleFileMapper saleFileMapper;

    @Value("${file.documentpath}")
    protected String filepath;

    /**
     * 添加创建时间、客户名、创建人id、创建人
     * 添加新的销售记录 最近联系时间为创建时间
     */
    @Transactional
    public void addNewSaleRecord(SaleRecord saleRecord) {

        Integer customerid = saleRecord.getCustomerid();
        Custom custom = customService.findCustomById(customerid);
        saleRecord.setCustomer(custom.getCustomer());
        saleRecord.setUserid(ShiroUtil.getCurrentUserId());
        saleRecord.setRealname(ShiroUtil.getCurrentRealname());
        saleRecord.setCreatetime(SmallUtils.getTime());
        saleRecord.setLasttime(SmallUtils.getTime());
        logger.debug("saleRecord is {}", saleRecord);
        saleRecordMapper.addSaleRecord(saleRecord);

        // 创建事项
        SaleLog saleLog = new SaleLog();
        saleLog.setCreatetime(SmallUtils.getTime());
        saleLog.setContext("创建了该销售事项");
        saleLog.setType("初次接触");
        saleLog.setSaleid(saleRecord.getId());
        saleLogMapper.insertSaleLog(saleLog);

    }


    /**
     * 根据参数查找 List<SaleRecord>
     *
     * @param param start length keyword
     * @return List<SaleRecord>
     */
    public List<SaleRecord> findSaleRecordList(Map<String, Object> param) {
        if (ShiroUtil.isAdmin()) {
            return null;
        }
        if (ShiroUtil.isEmployee()) {
            param.put("userid", ShiroUtil.getCurrentUserId());
        }
        return saleRecordMapper.querySaleRecord(param);
    }


    /**
     * 员工角色添加员工id
     *
     * @param param 根据参数查找数据条数 keyowrd
     * @return 过滤后的数据条数
     */
    public Long querySaleRecordByParams(Map<String, Object> param) {

        if (ShiroUtil.isAdmin()) {
            return null;
        }
        if (ShiroUtil.isEmployee()) {
            param.put("userid", ShiroUtil.getCurrentUserId());
        }

        return saleRecordMapper.querySaleRecordNumByParams(param);
    }

    /**
     * 查找数据总条数
     *
     * @return 数据总条数
     */
    public Long querySaleRecordTotal() {
        return querySaleRecordByParams(new HashMap<String, Object>());
    }

    /**
     * 根据id 查找 salerecord
     *
     * @param id salerecord的id
     * @return 一条SaleRecord
     */
    public SaleRecord findSaleRecordById(Integer id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        return saleRecordMapper.querySaleRecord(map).get(0);
    }

    /**
     * 插入一条销售记录备忘 并更新阶段/最后接触时间
     * @param saleLog 销售记录备忘
     */
    @Transactional
    public void addNewSaleLog(SaleLog saleLog) {
        // 先根据 saleid 获取当前进度 进行判断，不同时先更新进度，在写入数据
        SaleRecord saledata = findSaleRecordById(saleLog.getSaleid());
        if (!saleLog.getType().equals(saledata.getProcess())) {
            String proce = saleLog.getType();
            SaleLog salelog = new SaleLog();
            salelog.setContext("将当前进度修改为:" + proce);
            salelog.setCreatetime(SmallUtils.getTime());
            salelog.setType(proce);
            salelog.setSaleid(saleLog.getSaleid());
            saleLogMapper.insertSaleLog(salelog);
        }
        saleLog.setCreatetime(SmallUtils.getTime());
        saleLogMapper.insertSaleLog(saleLog);

        // 更新saleRecord 数据 主要是最后接触时间
        SaleRecord saleRecord = new SaleRecord();
        saleRecord.setId(saleLog.getSaleid());
        saleRecord.setLasttime(SmallUtils.getTime());
        saleRecord.setProcess(saleLog.getType());
        if(saleRecord.getProcess().equals("完成交易")){
            saleRecord.setSuccesstime(SmallUtils.getTime());
        }
        saleRecordMapper.updateSaleRecord(saleRecord);


    }

    /**
     * 根据saleid 查询销售记录
     */
    public List<SaleLog> querySaleLog(Integer saleid) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("saleid", saleid);
        List<SaleLog> list = saleLogMapper.querySaleLog(map);
        return list;
    }

    /**
     * 修改进度
     * @param saleRecord id process
     */
    @Transactional
    public void updateProcess(SaleRecord saleRecord) {

        if(saleRecord.getProcess().equals("完成交易")){
            saleRecord.setSuccesstime(SmallUtils.getTime());
        }

        saleRecordMapper.updateSaleRecord(saleRecord);

        SaleLog saleLog = new SaleLog();
        saleLog.setContext("将当前进度修改为:" + saleRecord.getProcess());
        saleLog.setCreatetime(SmallUtils.getTime());
        saleLog.setType(saleRecord.getProcess());
        saleLog.setSaleid(saleRecord.getId());

        saleLogMapper.insertSaleLog(saleLog);
    }

    /**
     * 上传文件
     */
    @Transactional
    public void uploadFile(MultipartFile file, Integer saleid) throws IOException {

        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        String savename = UUID.randomUUID().toString();
        FileOutputStream outputStream = new FileOutputStream(new File(filepath, savename));
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        SaleFile saleFile = new SaleFile();
        saleFile.setSaleid(saleid);
        saleFile.setFilename(filename);
        saleFile.setSavename(savename);
        saleFile.setSize(file.getSize());
        saleFile.setCreatetime(SmallUtils.getTime());
        saleFile.setContenttype(file.getContentType());
        saleFileMapper.addNewFile(saleFile);
    }


    /**
     * @param saleid 根据saleid查找
     * @return List<SaleFile>
     */

    public List<SaleFile> findSalefileBySaleid(Integer saleid) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("saleid", saleid);
        return findSaleFileByParams(map);
    }


    /**
     * @param id 根据文件id 查找文件
     * @return SaleFile
     */
    public SaleFile findSalefileById(Integer id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        return findSaleFileByParams(map).get(0);
    }

    /**
     * @param map 根据参数查找  List<SaleFile>
     * @return List<SaleFile>
     */

    public List<SaleFile> findSaleFileByParams(Map<String, Object> map) {
        return saleFileMapper.findSaleFileByParams(map);
    }


    public List<SaleRecord> findCustomSaleList(Integer id) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("customerid", id);
        return findSaleRecordList(map);

    }

    /**
     * 删除交易详情 交易记录备忘 以及相关文件
     * @param id 详情id
     */
    @Transactional
    public void deleteSaleRecordById(Integer id) {

        List<Integer> list = Lists.newArrayList();
        // 先获得备忘记录列表 再删除备忘
        List<SaleLog> saleLogList = querySaleLog(id);
        if(saleLogList.size() > 0){
            for(SaleLog saleLog : saleLogList ){
                list.add(saleLog.getId());
            }
            saleLogMapper.deleteSaleLogs(list);
            list.clear();
        }

        // 获得相关文件列表 先删除文件 在删除数据库中的记录
        List<SaleFile> saleFileList = findSalefileBySaleid(id);
        if(saleFileList.size() > 0){
            for(SaleFile saleFile : saleFileList){
                list.add(saleFile.getId());
                // 删除文件
                File file = new File(filepath, saleFile.getSavename());
                if (!file.exists()) {
                    throw new RuntimeException("文件回火星了。。");
                } else {
                    if(file.delete() ){
                        list.add(saleFile.getId());
                    }
                }
            }
            saleFileMapper.deleteSaleFiles(list);
        }
        saleRecordMapper.deleteSaleRecord(id);
    }






}
