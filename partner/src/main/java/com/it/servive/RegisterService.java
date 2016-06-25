package com.it.servive;

import com.it.dao.RegisterDao;
import com.it.entity.Register;
import com.it.utils.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/25.
 * RegisterService
 */
public class RegisterService {
    Logger logger = LoggerFactory.getLogger(RegisterService.class);

    private RegisterDao dao = new RegisterDao();

    /**
     * @param register 传入一个user对象
     * @return 返回受影响行数
     */
    public Integer register(final Register register) {

        String identify = DigestUtils.md5Hex(register.getEmail());
        register.setIdentify(identify);
        // 获取当前时间
        String time = new DateTime().plusHours(12).toString("MM-dd HH:mm:ss");
        register.setTime(time);
        // 用户验证链接
        final String url = "loaclhost/identify?username=" + register.getUsername() +
                "&email=" + register.getEmail() +
                "&password=" + register.getPassword() +
                "&identify=" + identify +
                "&time=" + time;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String subject = "欢迎注册segmentfault.com";
                String content = "<a href=\"" + url + "\"><h1>点击这里继续完成注册</h1></a>";
                String address = register.getEmail();
                EmailUtil.sendEmail(address, subject, content);
            }
        });
        return dao.Insert(register);
    }

    /**
     * @param name 传入用户名
     * @return 返回是否存在
     */
    public boolean usernameExist(String name) {
        return dao.queryUsername(name) == null;
    }
}
