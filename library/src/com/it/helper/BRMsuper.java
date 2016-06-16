package com.it.helper;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class BRMsuper<T> {

	// private static final long serialVersionUID = 1L;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private Class<T> type;
	public BRMsuper(Class<T> type ,HttpServletRequest request,HttpServletResponse response) {
		this.type = type;
		this.request = request;
		this.response = response;
	}
	public T build() throws Exception {

		T obj = null;
		try {
			obj = type.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// method �洢 T �ķ���
		Method[] method = type.getMethods(); 
		// ���洢 ����  ֵ�洢 ������get�����ķ�������  ������String �� Integer
		HashMap<String,String> map = new HashMap<>();
		for (Method me : method) {
			if(me.getName().contains("get")){
				String name = me.getName().substring(3);
				name = name.substring(0, 1).toLowerCase() + name.substring(1);
				Class<?> type = me.getReturnType();
				map.put(name, type.getSimpleName());
			}
		} 
		for(Entry<String, String> entry : map.entrySet()) {
			String name = entry.getKey().toString();
			String typ = entry.getValue().toString();
			String nameu = name.substring(0,1).toUpperCase() + name.substring(1);
			nameu ="set" + nameu;
			String str = request.getParameter(name);
//			System.out.println("str  :" + str); 
//			System.out.println("name: " + name);
//			System.out.println("nameu: " + nameu);
//			System.out.println(name + "  :  " + nameu + "  :  " + typ);
			for (Method me : method) {
				if(me.getName().equals(nameu)){ 
				//	System.out.println("��������" + me.getName());
					if(str == null){
						break;
					}
					if(typ.equals("String")){
						me.invoke(obj, str);
					} else if (typ.equals("Integer")) {
						me.invoke(obj, Integer.parseInt(str));
					} else {
						break;
					}
					// System.out.println("obj   :" + obj );
					break;
				}
			} 
		}
		return obj;
	}
	public void fixon(String file) {
		if(!file.contains("/")){
			file = "/" + file;
		}
		try {
			response.sendRedirect(file);
		} catch (IOException e) {
			System.out.println("�ض������");
			e.printStackTrace();
		}
	}
	public void fixon(String file,String infname ,String infvalue) {
		if(!file.contains("/")){
			file = "/" + file;
		}
		String str = file + "?" + infname + "=" + infvalue;
		try {
			response.sendRedirect(str);
		} catch (IOException e) {
			System.out.println("�ض������");
			e.printStackTrace();
		}
	}
	public void forhelp(String roots ,String file, String infname ,String infvalue ){
		String str = roots + file + "?" + infname + "=" + infvalue;
		try {
			request.getRequestDispatcher(str).forward(request, response);
		} catch (Exception e) {
			System.out.println("����ת������1��");
			e.printStackTrace();
		} 
	}
	public void forhelp(String file, String infname ,String infvalue ){
		if(!file.contains("/")){
			file = "/" + file;
		}
		String str = file + "?" + infname + "=" + infvalue;
		try {
			request.getRequestDispatcher(str).forward(request, response);
		} catch (Exception e) {
			System.out.println("����ת������2��");
			e.printStackTrace();
		} 
	}
	public void forhelp(String file ){
		if(!file.contains("/")){
			file = "/" + file;
		}
		try {
			request.getRequestDispatcher(file).forward(request, response);
		} catch (Exception e) {
			System.out.println("����ת������3��");
			e.printStackTrace();
		} 
	}
	
}
