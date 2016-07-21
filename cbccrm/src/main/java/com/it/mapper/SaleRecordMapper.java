package com.it.mapper;

import com.it.pojo.SaleRecord;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/15.
 * SaleRecordMapper
 */
public interface SaleRecordMapper {
    void addSaleRecord(SaleRecord saleRecord);

    void deleteSaleRecord(Integer id);

    void updateSaleRecord(SaleRecord saleRecord);

    List<SaleRecord> querySaleRecord(Map<String,Object> map);

    Long querySaleRecordNumByParams(Map<String,Object> map);

    List<Map<String,Object>> countEmployeeSale(Map<String,Object> map);


    Long countSaleRecordNum(Map<String, Object> map);

    Float countSaleTotal(Map<String, Object> map);

    List<Map<String,Object>> countCustomSale(Map<String,Object> map);

    List<Map<String,Object>> countProcessNum(Map<String,Object> map);


}
