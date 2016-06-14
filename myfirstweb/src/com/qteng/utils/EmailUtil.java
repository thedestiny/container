package com.qteng.utils;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by xieyue on 2016/6/14.
 */
public class EmailUtil {

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    /**
     * @param email   要发送的邮件地址
     * @param subject 邮件的主题
     * @param content 邮件的内容
     */
    public static void sendEmail(String email, String subject, String content) {

        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(Config.get("mail.hostname"));
        htmlEmail.setSmtpPort(Integer.parseInt(Config.get("mail.port", "25")));
        htmlEmail.setAuthentication(Config.get("mail.username"), Config.get("mail.password"));
        htmlEmail.setCharset(Config.get("mail.charset"));
        logger.debug("begin execute the try/catch");
        try {
            htmlEmail.setFrom(Config.get("mail.fromEmail"));
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(content);
            htmlEmail.addTo(email);
            htmlEmail.send();
            logger.debug(content);
            logger.debug("the email to {} is successful", email);

        } catch (Exception e) {
            logger.debug("the email to {} is failure", email);
            throw new RuntimeException("send email failure", e);
        }

    }


}
