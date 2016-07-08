package com.it.mapper;

import com.it.pojo.User;

/**
 * Created by xieyue on 2016/7/8.
 */
public interface UserMapper {

    User queryUser(User user);
    Integer insertUser(User user);

}
