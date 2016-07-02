package com.it.dao;

/**
 * Created by xieyue on 2016/7/2.
 */


import com.it.mapper.CardMapper;
import com.it.pojo.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardDao implements CardMapper {
    Logger logger = LoggerFactory.getLogger(CardDao.class);


    public void saveCard(Card card) {

        logger.debug("this card is {}",card.getUser());
    }
}
