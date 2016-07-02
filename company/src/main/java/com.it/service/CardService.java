package com.it.service;

/**
 * Created by xieyue on 2016/7/2.
 */


import com.it.mapper.CardMapper;
import com.it.pojo.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardService {
    Logger logger = LoggerFactory.getLogger(CardService.class);

    private CardMapper cardMapper;
    public CardService(CardMapper cardMapper){
        this.cardMapper = cardMapper;
    }
    public void insertCard(){
        Card card = new Card();
        card.setUser("jim");
        cardMapper.saveCard(card);
    }


}
