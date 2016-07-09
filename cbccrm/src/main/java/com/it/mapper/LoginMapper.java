package com.it.mapper;

import com.it.pojo.Login;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/8.
 * LoginMapper
 */
public interface LoginMapper {

    Integer insertLoginLog(Login login);

    List<Login> queryLoginLog(Map<String,Object> map);

    long queryCount(Map<String,Object> map);

}
