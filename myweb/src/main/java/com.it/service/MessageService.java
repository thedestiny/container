package com.it.service;

import com.it.dao.MessageDao;
import com.it.entity.Message;

import java.util.List;

/**
 * Created by xieyue on 2016/6/24.
 * MessageService
 */
public class MessageService  {

    private MessageDao messageDao = new MessageDao();

    public List<Message> show(){
        return messageDao.fingAllMessage();
    }

    public List<Message> update(Integer id){
        return messageDao.fingMessageById(id);
    }


}
