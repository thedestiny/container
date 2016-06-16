package com.qteng.dao;

import com.qteng.entity.Document;
import com.qteng.utils.Dbhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by xieyue on 2016/6/16.
 * Document
 */
public class DocumentDao {

    public Document findDocumentByMD5(String md5) {
        String sql = "select * from document where md5=?";
        return Dbhelper.query(sql, new BeanHandler<>(Document.class), md5);
    }

    public void insertDocument(Document document) {
        String sql = "INSERT INTO document (filename,savename,size,displaysize,md5,filetype)VALUE (?,?,?,?,?,?)";
        Dbhelper.update(sql, document.getFilename(), document.getSavename(), document.getSize(), document.getDisplaysize(), document.getMd5(), document.getFiletype());
    }

    public List<Document> findAllDocument(){
        String sql= "select * from document";
        return Dbhelper.query(sql,new BeanListHandler<>(Document.class));
    }
}
