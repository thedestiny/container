package com.qteng.service;

import com.qteng.dao.DocumentDao;
import com.qteng.entity.Document;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by xieyue on 2016/6/16.
 * DocumentService
 */
public class DocumentService {
    private DocumentDao documentDao = new DocumentDao();

    public void upload(String fileName, long fileSize, InputStream inputStream) throws IOException {

        // 将数据流进行转换
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
        // 计算MD5值
        String md5 = DigestUtils.md5Hex(byteArrayInputStream);

        Document document = documentDao.findDocumentByMD5(md5);

        if (document == null) {
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String diplaySize = diplaySize(fileSize);
            String uuid = UUID.randomUUID().toString();
            String saveName = uuid + fileType;
            saveFile(saveName, byteArrayInputStream);
            document = new Document();
            document.setFilename(fileName);
            document.setSavename(saveName);
            document.setFiletype(fileType);
            document.setDisplaysize(diplaySize);
            document.setSize(fileSize);
            document.setMd5(md5);
            documentDao.insertDocument(document);
        }
    }

    public Document findFile(String md5) {
        return documentDao.findDocumentByMD5(md5);
    }


    public List<Document> getAllDocument() {
        return documentDao.findAllDocument();
    }

    private void saveFile(String saveName, InputStream inputStream) throws IOException {
        // 重置数据流
        inputStream.reset();
        File dir = new File("E:/upload");
        if (!dir.exists()) {
            // 返回创建结果
            dir.mkdir();
        }
        FileOutputStream outputStream = new FileOutputStream(new File(dir, saveName));
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }


    private String diplaySize(long fileSize) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.applyPattern(".##");
       //  String formatSize = "";
        if (fileSize < 103) {
            // 输出范围 0-103B
            return fileSize + "B";
        } else if ((fileSize > 103) && (fileSize / 1024 < 103)) {
            // 输出范围0.10KB-103KB
            return df.format((double) fileSize / 1024) + "KB";
        } else if ((fileSize / 1024 > 102) && (fileSize / 1024 / 1024 < 1000)) {
            // 输出范围 0.10MB-1000MB
            return df.format((double) fileSize / 1024 / 1024) + "MB";
        } else if ((fileSize / 1024 / 1024 > 999) && (fileSize / 1024 / 1024 / 1024 < 103)) {
            //输出范围0.10GB-103GB
            return df.format((double) fileSize / 1024 / 1024 / 1024) + "GB";
        } else{
            return null;
        }

    }

    public String format(long num) {
        String str = num * 100 / 1024 + "";
        str = str.substring(0, str.length() - 2) + "." + str.substring(str.length() - 2);
        return str;
    }

    @Test
    public void test() {
        long n = 3441462000L;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();
        decimalFormat.applyPattern(".##");
        String num = decimalFormat.format((double) 1000 / 12);
        System.out.println(num);
        System.out.println(diplaySize(n));

        String save = FileUtils.byteCountToDisplaySize(123400000);
        System.out.println(save);


    }


}
