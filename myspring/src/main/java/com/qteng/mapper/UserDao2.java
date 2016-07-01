package com.qteng.mapper;

/**
 * Created by xieyue on 2016/7/1.
 * UserDao2
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// spring 注解 @Component @Service  @Repository @Controller 注入 @Autowired
// @Scope("") singleton prototype  @Lazy(true)
// JSR 注解  @Named   注入@Inject JSR330    @Resource JSR250
@Repository("userMapper")
public class UserDao2 implements UserMapper {

    Logger logger = LoggerFactory.getLogger(UserDao2.class);

    @Override
    public String sayHello() {
        logger.debug(" this is come from UserDao2");
        return "456";
    }



}
