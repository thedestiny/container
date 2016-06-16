package com.it.helper;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeanRowMapper<T> implements Entitybuild<T> {

	private Class<T> type;

	public BeanRowMapper(Class<T> type) {
		this.type = type;
	}

	@Override
	public T build(ResultSet res) throws SQLException {
		T obj = null;
		try {
			obj = type.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// res �ǲ�ѯ���  type ������   res.getObject()��ȡֵ  res.getMetaData()��ȡ��̧ͷ
		// ��ȡ���� ̧ͷ 
		ResultSetMetaData rsmd = res.getMetaData();
		// ��ȡtype�ķ������� ���͵ķ���
		Method[] me = type.getMethods(); 
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			// ��ȡÿ�е����� ���� ��װset����
			String colname = rsmd.getColumnLabel(i);
			//System.out.println(rsmd.getColumnName(i)+" " +rsmd.getColumnTypeName(i)+" "+rsmd.getColumnType(i)+" "+rsmd.getColumnClassName(i));
			
			colname = colname.substring(0, 1).toUpperCase() + colname.substring(1);
			// System.out.println("shijian");
			colname = "set" + colname;
			// System.out.println(colname);
			// �ڷ��������� ������Ӧ����
			for (Method method : me) {
				if (method.getName().equals(colname)) {
					try {
						// ���� y=f(x)�� f(y,x)  ��ȡ��������
						String paramtype = "abc";
						Class<?>[] typ = method.getParameterTypes();
						for (Class<?> cla : typ) {
							paramtype = cla.getSimpleName();
						}
						//System.out.println(paramtype);
						if(paramtype.equals("String") && res.getObject(i) == null){
							//System.out.println("BeanRowMapperֵΪ��");
							method.invoke(obj, "--");
							break;
						} else if (paramtype.equals("Integer") && res.getObject(i) == null){
							//System.out.println("����");
							method.invoke(obj, 0);
							break;
						}
						method.invoke(obj, res.getObject(i));
						//System.out.println("i" + res.getObject(i));
					} catch (Exception e) {
						// System.out.println("zheli");
						 e.printStackTrace();
					}
					break;
				}
			}
		} 
		// System.out.println(obj.toString());
		return obj;
	}

}
