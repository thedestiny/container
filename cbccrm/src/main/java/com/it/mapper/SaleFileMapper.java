package com.it.mapper;

import com.it.pojo.SaleFile;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/15.
 * SaleFileMapper
 */
public interface SaleFileMapper {

    List<SaleFile> findSaleFileByParams(Map<String, Object> map);

    void addNewFile(SaleFile saleFile);
}
