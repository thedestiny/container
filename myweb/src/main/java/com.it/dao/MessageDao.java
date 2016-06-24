package com.it.dao;

import com.it.entity.Message;
import com.it.utils.Dbhelp;
import com.it.utils.SmallUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.util.List;

/**
 * Created by xieyue on 2016/6/24.
 * MessageDao
 */
public class MessageDao {

    public List<Message> fingAllMessage() {
        String sql = "select * from message order by id desc";
        return Dbhelp.query(sql, new BeanListHandler<>(Message.class));
    }

    public List<Message> fingMessageById(Integer id) {
        String sql = "select * from message where id > ? order by id desc";
        return Dbhelp.query(sql, new BeanListHandler<>(Message.class), id);
    }

    public Integer insertMessage(Message message) {
        String sql = "insert into message ( msg , author) values (?,?)";
        System.out.println(message.getMsg() + "  :  " + message.getAuthor());
        SmallUtils.helpGet(message, sql);
        return Dbhelp.update(sql, SmallUtils.helpGet(message, sql));
        // return Dbhelp.update(sql, message.getMsg(),message.getAuthor());
    }

    @Test
    public void auto() throws InterruptedException {
        for(int i =0 ; i< 20; i++){
            Message message = new Message("this is running", "jim");
            Integer num = insertMessage(message);
            Thread.sleep(20000);
        }

    }

}
