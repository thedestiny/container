package com.it.dao;

import java.util.List;

import com.it.entity.Card;
import com.it.helper.BeanRowMapper;
import com.it.helper.DBhelper;

public class CardDAO {
	DBhelper<Card> db = new DBhelper<Card>(Config.URL);
	BeanRowMapper<Card> be = new BeanRowMapper<>(Card.class);

	public boolean update(String ccode, String fu) {
		String sql = "SELECT ctime,count FROM card where `code` = ?";
		Card card = new Card();
		card = db.doquery(sql, be, ccode);
		Integer time = card.getCtime();
		Integer count = card.getCount();
		time++;
		if (fu.equals("+")) {
			count++;
		} else if (fu.equals("-")) {
			count--;
		}
		String sql1 = "update card set ctime=?,count=? where `code` = ?";
		return db.doupdate(sql1, time, count, ccode) == 1;
	}

	public List<Card> show() {
		String sql = "SELECT * FROM card ORDER BY code";
		return db.doquery(sql, be);
	}

	public boolean delete(String code) {
		if (find(code).getCount() > 0) {
			System.out.println("������δ��������");
			return false;
		} else {
			String sql = "delete from card where code=?";
			return db.doupdate(sql, code) > 0;
		}
	}
	public Card find(String code) {
		String sql = "select * from card where code=?";
		Card card = db.doquery(sql, be, code);
		return card;
	}

	public boolean update(Card card) {
		// set ֮��Ҫ��,
		String sql = "update card set user=?,tel=?, ctime=?,count=? where `code` = ?";
		System.out.println(card.toString());
		return db.doupdate(sql, card.getUser(), card.getTel(), card.getCtime(), card.getCount(), card.getCode()) > 0;
	}
	public boolean insert(Card card) {
		// set ֮��Ҫ��,
		if(find(card.getCode()) != null){
			System.out.println("�����Ѿ����ڣ�");
			return false;
		}
		String sql = "INSERT INTO `card` (`code`, `user`, `tel`) VALUES (?,?,?)";
		System.out.println(card.toString());
		return db.doupdate(sql, card.getCode(),card.getUser(), card.getTel()) > 0;
	}

}
