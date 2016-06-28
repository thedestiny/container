package com.qteng.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Book implements Serializable{
	private static final long serialVersionUID = -3981248998221109612L;
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
		return  code +"  " + title  + "\t\t" + author  +"\t" + press + "\t"+ station+ "\t" +total +"\t" + remain;
				
	}

}
