package com.qteng.service;

import com.qteng.mapper.UserDao;
import com.qteng.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Created by xieyue on 2016/6/30.
 * UserService
 */

@Named
@Transactional
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    // 0. 事务添加在Service层
    // 1. 碰到RuntimeException事务会回滚
    // 2. @Transactional(rollbackFor = Exception.class) 这种将碰到所有的异常，事务都会回滚
    // 3. @Transactional(noRollbackFor = RuntimeException.class) 碰到RuntimeException将不回滚
    // 4. @Transactional(readOnly = true) 推荐查询方法配置为只读事务，性能较高
    // 5. @Transactional可以添加在类级别或方法级别，如果添加在类级别，则所有方法都将加入事务
    // 6. @Transactional(isolation = Isolation.SERIALIZABLE) 修改事务的隔离级别
    // 7. @Transactional(propagation = Propagation.REQUIRED) 修改事务的传播属性

    @Inject
    private UserMapper userMapper;

//    public UserService(UserMapper userMapper) {
//        this.userMapper=userMapper;
//    }
//
//    public UserService() {
//    }
//
//    public void setUserDaox(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    public void sayHai() {
       logger.debug("UserService sayHai ");
        userMapper.sayHello();
    }


}
