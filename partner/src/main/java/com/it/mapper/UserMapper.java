package com.it.mapper;

import com.it.pojo.User;

/**
 * Created by xieyue on 2016/6/29.
 * UserMapper
 */
public interface UserMapper {

    Integer insertUser(User user);
    Integer updateInfo(User user);
    Integer updatePwd(User user);
    Integer updateQue(String username);
    Integer updateAns(String username);
    Integer updateAcc(String username);
    User queryUsername(String username);

}
