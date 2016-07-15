package com.it.mapper;

import com.it.pojo.User;

import java.util.List;
import java.util.Map;
/**
 * Created by xieyue on 2016/7/8.
 * UserMapper
 */
public interface UserMapper {

    User queryUser(User user);
    Integer insertUser(User user);
    Integer updateUserInfo(User user);
    long queryFilterUserNum(Map<String, Object> param);
    List<User> queryUserInformation(Map<String, Object> param);

    /**
     * 获取用户，非管理员
     * @return List<User>
     */
    List<User> queryUsers(Integer id);
}
