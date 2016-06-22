package com.qteng.test;

import com.qteng.utils.Config;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by xieyue on 2016/6/14.
 * EmailTest
 */
public class EmailTest {
    Logger logger = LoggerFactory.getLogger(EmailTest.class);

    /**
     * 发送普通邮件
     */
    @Test
    public void sendTextEmail(){
        SimpleEmail email = new SimpleEmail();
        email.setHostName(Config.get("mail.hostname"));
        email.setSmtpPort(Integer.parseInt(Config.get("mail.port", "25")));
        email.setAuthentication(Config.get("mail.username"), Config.get("mail.password"));
        email.setCharset(Config.get("mail.charset"));
        try {
            email.setFrom(Config.get("mail.fromEmail"));
            email.setSubject("欧洲杯小组赛战况");
            email.setMsg("今天晚上有世界杯，一起观看");
            email.addTo("3131500685@qq.com");
            email.send();
            logger.debug("the email to {} is successful","3131500685@qq.com");
        } catch (Exception e) {
            logger.debug("the email to {} is failure","3131500685@qq.com");
            throw new RuntimeException("send email failure", e);
        }

    }

    /**
     * 发送html邮件
     */
    @Test
    public void sendHtmlEmail(){
        HtmlEmail email = new HtmlEmail();
        email.setHostName(Config.get("mail.hostname"));
        email.setSmtpPort(Integer.parseInt(Config.get("mail.port", "25")));
        email.setAuthentication(Config.get("mail.username"), Config.get("mail.password"));
        email.setCharset(Config.get("mail.charset"));
        try {
            email.setFrom(Config.get("mail.fromEmail"));
            email.setSubject("欧洲杯小组赛战况");
            email.setHtmlMsg("<h1>今天晚上有世界杯，一起观看</h1>");
            email.addTo("3131500685@qq.com");
            email.send();
            logger.debug("the email to {} is successful","3131500685@qq.com");
        } catch (Exception e) {
            logger.debug("the email to {} is failure","3131500685@qq.com");
            throw new RuntimeException("send email failure", e);
        }

    }

    /**
     * 发送含有附件的邮件
     */
    @Test
    public void sendAttachEmail(){
        EmailAttachment ea = new EmailAttachment();
        ea.setPath("E:/attach.txt");
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(Config.get("mail.hostname"));
        email.setSmtpPort(Integer.parseInt(Config.get("mail.port", "25")));
        email.setAuthentication(Config.get("mail.username"), Config.get("mail.password"));
        email.setCharset(Config.get("mail.charset"));
        try {
            email.setFrom(Config.get("mail.fromEmail"));
            email.setSubject("欧洲杯小组战况");
            email.setMsg("今天晚上有世界杯，一起观看");
            email.addTo("1830683298@qq.com");
            email.attach(ea);
            email.send();
            logger.debug("the email to {} is successful","3131500685@qq.com");
        } catch (Exception e) {
            logger.debug("the email to {} is failure","3131500685@qq.com");
            throw new RuntimeException("send email failure", e);
        }

    }


}
