package com.it.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBhelper <T> {

	private String url;
	private Connection con = null;
	private PreparedStatement sta = null;
	private ResultSet res = null;

	// ע�ⷽ���ķ������� query ����һ�� ��score ������ list
	public DBhelper(String url) {
		this.url = url;
	}

	public int doupdate(String sql, Object... obj) {
		getstatement(sql);
		try {
			for (int i = 1; i <= obj.length; i++) {
				sta.setObject(i, obj[i-1]);
			}
			return sta.executeUpdate();
		} catch (SQLException e) {
			System.out.println("��������");
		 //	e.printStackTrace();
			return -1;
		} finally {
			conclose();
		}
	}

	public T doquery(String sql, Entitybuild<T> be, Object... obj) {
		getstatement(sql);
		try {
			for (int i = 0; i < obj.length; i++) {
//				System.out.println(obj[i]);
//				System.out.println(sta==null);
				sta.setObject(i + 1, obj[i]);
			}
            res = sta.executeQuery();
			if(res != null && res.next()){
				return be.build(res);
			} else {
				return null;
			}
//			return be.build(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			conclose();
		}
	}
    
	public List<T> doquery(String sql, Entitybuild<T> be) {
		getstatement(sql);
		try {
			
			res = sta.executeQuery();
			List<T> list = new ArrayList<>();
			while(res!=null && res.next()){
				list.add(be.build(res));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			conclose();
		}
	}
	
	public List<T> doque(String sql, Entitybuild<T> be,Object... obj) {
		getstatement(sql);
		try {
			for (int i = 0; i < obj.length; i++) {
				sta.setObject(i + 1, obj[i]);
			}
			res = sta.executeQuery();
			List<T> list = new ArrayList<>();
			while(res!=null && res.next()){
				list.add(be.build(res));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			conclose();
		}
	}

	private void getstatement(String sql) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url);
			sta = con.prepareStatement(sql);
		} catch (Exception e) {
			System.out.println("����ʧ�ܣ�");
			 e.printStackTrace();
		}

	}

	public void conclose() {

		try {
			if (res != null)
				res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (sta != null)
					sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
