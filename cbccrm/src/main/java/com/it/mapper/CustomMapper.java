package com.it.mapper;

import com.it.pojo.Custom;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/13.
 * CustomMapper
 */
public interface CustomMapper {

    Integer insertCustom(Custom custom);

    Integer deleteCustom(Integer id);

    Integer updateCustom(Custom custom);

    List<Custom> queryCustomByParams(Map<String,Object> map);

    Long queryCustomNumByParams(Map<String,Object> map);
    //
    Integer updateCustomInf(Custom custom);

    void updateUserid(Custom custom);

    Custom queryCustomByParam(Map<String, Object> map);
}
