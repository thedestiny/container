package com.it.mapper;

import com.it.pojo.Notice;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/11.
 * NoticeMapper
 */
public interface NoticeMapper {

    Integer addNotice(Notice notice);

    List<Notice> queryNotice(Map<String,Object> map);

    Long queryNoticeNum(Map<String,Object> map);
}
