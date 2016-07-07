package com.it.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Book implements Serializable {
	private static final long serialVersionUID = -9014582649951015462L;
	private Integer id;
	private String code;
	private String title;
	private String author;
	private String press;
	private String station;
	private Long total;
	private BigDecimal remain;
	private Integer btime;
	

	public Integer getBtime() {
		return btime;
	}


	public void setBtime(Integer btime) {
		this.btime = btime;
	}


	public Integer getId() {
		return id;
	}


	public BigDecimal getRemain() {
		return remain;
	}


	public void setRemain(BigDecimal remain) {
		this.remain = remain;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPress() {
		return press;
	}


	public void setPress(String press) {
		this.press = press;
	}


	public String getStation() {
		return station;
	}


	public void setStation(String station) {
		this.station = station;
	}


	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", code='" + code + '\'' +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", press='" + press + '\'' +
				", station='" + station + '\'' +
				", btime=" + btime +
				'}';
	}
}
