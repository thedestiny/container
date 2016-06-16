package com.it.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.it.entity.Record;
import com.it.helper.BeanRowMapper;
import com.it.helper.DBhelper;

public class RecordDAO {
	// DBhelper constructor ����д�ɿɼ�  public ���ܲ�д
	DBhelper<Record> db = new DBhelper<Record>(Config.URL);
	BeanRowMapper<Record> be = new BeanRowMapper<>(Record.class);
	
	public boolean insert(String bcode, String ccode) {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		BookDAO bdao = new BookDAO();
		CardDAO cdao = new CardDAO();
		// ����  ���ж��Ƿ�� �����鼮�� code �� station �б�
		if(bdao.access(bcode)){
			return false;
		}
		String sql = "INSERT INTO `record` (`bcode`, `ccode`, `bcstate`, `brtime`) VALUES (?, ?, ?, ?)";
		Record record = new Record();
		record.setCcode(ccode);
		record.setBcode(bcode);
		record.setBcstate("���");
		record.setBrtime(df.format(now));
		//record.getBcstate()
		//cdao.update(ccode,"+");
		//bdao.update(bcode,"+");
		if(cdao.update(ccode,"+") && bdao.update(bcode,"+")){
			// System.out.println("û���ҵ�");
			return db.doupdate(sql, record.getBcode(),record.getCcode(),"���",record.getBrtime()) != 0;
			
		} else{
			return false;
		}
		
	}
	public boolean update(String bcode, String ccode) {
		//����
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Record record = new Record();
		CardDAO cdao = new CardDAO();
		BookDAO bdao = new BookDAO();
		String sql = "UPDATE `record` SET `bcstate`=?, `retime`=?,`bday`=? WHERE (bcode=? and ccode=?) and bcstate ='���'";
		record.setBcstate("�ѻ�");
		record.setRetime(df.format(now)); 
		String sq = "select brtime FROM record WHERE bcode = ? AND ccode = ?";
		Record rec = new Record();
		rec = db.doquery(sq, be, bcode,ccode);
		String btime = rec.getBrtime();
		// cdao.update(ccode,"-");
		// bdao.update(bcode,"-");
		if(cdao.update(ccode,"-") && bdao.update(bcode,"-")){

			return db.doupdate(sql,record.getBcstate(),record.getRetime(),1,bcode,ccode) == 1;
		} else{
			return false;
		}
	}
	public List<Record> show(){
		String sql = "SELECT * from record order by bcode";
		return db.doquery(sql, be);
	} 
	public List<Record> findccode(String ccode){
		String sql = "SELECT * from record where ccode =? order by bcode";
		return db.doque(sql, be, ccode);
	} 
	public List<Record> findbcode(String bcode){
		String sql = "SELECT * from record where bcode =? order by bcode";
		return db.doque(sql, be, bcode);
	} 
	

}
