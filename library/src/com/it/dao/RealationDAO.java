package com.it.dao;

import java.util.List;

import com.it.helper.BeanRowMapper;
import com.it.helper.DBhelper;
import com.it.entity.Realation;

public class RealationDAO {
	DBhelper<Realation> db = new DBhelper<>(Config.URL);
	BeanRowMapper<Realation> be = new BeanRowMapper<>(Realation.class);
	
	public List<Realation> findbcode(String bcode){
		String sql = "SELECT * FROM view_bc WHERE bcode=?"; 
		return db.doque(sql, be, bcode);
	} 
	public List<Realation> findccode(String ccode){
		String sql = "SELECT * FROM view_bc WHERE ccode=?"; 
		return db.doque(sql, be, ccode);
	} 
	public List<Realation> findccode(){
		String sql = "SELECT * FROM view_bc order by ccode"; 
		return db.doquery(sql, be);
	} 
	
	
}
