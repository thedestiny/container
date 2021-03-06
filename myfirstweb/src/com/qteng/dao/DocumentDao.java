package com.qteng.dao;

import com.qteng.entity.Document;
import com.qteng.utils.Dbhelper;
import com.qteng.utils.Utilssmall;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xieyue on 2016/6/16.
 * Document
 */
public class DocumentDao {

    public Document findDocumentByMD5(String md5) {
        String sql = "select * from document where md5=?";
        return Dbhelper.query(sql, new BeanHandler<>(Document.class), md5);
    }

    public Document findDocumentById(int id) {
        String sql = "select * from document where id=?";
        return Dbhelper.query(sql, new BeanHandler<>(Document.class), id);
    }

    public void insertDocument(Document document) {
        String sql = "INSERT INTO document (filename,savename,size,displaysize,md5,filetype)VALUE (?,?,?,?,?,?)";
        // 之前的写法
//        Dbhelper.update(sql, document.getFilename(), document.getSavename(), document.getSize(),
//                document.getDisplaysize(), document.getMd5(), document.getFiletype());
        // 现在的写法
        Dbhelper.update(sql, Utilssmall.helpGet(document, sql));
    }

    public List<Document> findAllDocument() {
        String sql = "select * from document";
        return Dbhelper.query(sql, new BeanListHandler<>(Document.class));
    }

    @Test
    public void test() {
        //  Object[] object = Utils(findDocumentById(2), "filename", "savename", "size", "displaysize", "md5", "filetype");
        String sql = "INSERT INTO document (filename,savename,size,displaysize,md5,filetype)VALUE (?,?,?,?,?,?)";
        Object[] object = Utilssmall.helpGet(findDocumentById(2), sql);
        for (Object obj : object) {
            System.out.println(obj);
        }
    }

    // CharSequence 字段


    public Object[] Utils(Object document, String... prarms) {
        Object[] objects = new Object[prarms.length];
        int i = 0;
        Class<?> type = document.getClass();
        List<String> listname = new ArrayList<>();
        for (String value : prarms) {
            listname.add("get" + value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        // 获取对象方法
        Method[] methods = type.getMethods();
        for (String value : listname) {
            System.out.println(value);
            for (Method method : methods) {
                if (method.getName().equals(value)) {
                    try {
                        Object object = method.invoke(document);
                        objects[i++] = object;
                    } catch (Exception e) {
                        throw new RuntimeException("method.invoke(?) 抛出异常", e);
                    }
                }
            }
        }
        return objects;
    }
}
