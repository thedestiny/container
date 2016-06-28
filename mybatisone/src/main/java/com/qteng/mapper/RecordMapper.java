package com.qteng.mapper;

import com.qteng.pojo.Record;

import java.util.List;

/**
 * Created by xieyue on 2016/6/28.
 * RecordMapper
 */
public interface RecordMapper {

    List<Record> queryByBookCode(String bcode);
    List<Record> queryByCardCode(String ccode);
    List<Record> queryCardRecord(String ccode);

}
