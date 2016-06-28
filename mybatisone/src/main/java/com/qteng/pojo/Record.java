package com.qteng.pojo;

import java.io.Serializable;

public class Record implements Serializable {
	private static final long serialVersionUID = -3194272717030008962L;
	private Integer id;
	private String bcode;
	private String ccode;
	private String bcstate;
	private String brtime;
	private String retime;
	private Integer bday;
	private Book book;
	private Card card;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Integer getBday() {
		return bday;
	}

	public void setBday(Integer bday) {
		this.bday = bday;
	}

	public String getBrtime() {
		return brtime;
	}

	public void setBrtime(String brtime) {
		this.brtime = brtime;
	}

	public String getRetime() {
		return retime;
	}

	public void setRetime(String retime) {
		this.retime = retime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBcode() {
		return bcode;
	}

	public void setBcode(String bcode) {
		this.bcode = bcode;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getBcstate() {
		return bcstate;
	}

	public void setBcstate(String bcstate) {
		this.bcstate = bcstate;
	}

	

}
