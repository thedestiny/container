package com.it.service;

/**
 * Created by xieyue on 2016/7/12.
 * DocumentService
 */


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.it.mapper.DocumentMapper;
import com.it.pojo.Document;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Named
public class DocumentService {
    Logger logger = LoggerFactory.getLogger(DocumentService.class);

    @Inject
    private DocumentMapper documentMapper;

    @Value("${file.documentpath}")
    protected String documentpath;


    public List<Document> findAllDocument(){
        return findDocumentByParams(new HashMap<String, Object>());
    }


    public List<Document> findDocumentByParams(Map<String,Object> map){
        logger.debug("faid is {}",map.get("faid"));
        return documentMapper.queryDocumentByParams(map);
    }

    public Integer deleteDocumentById(Integer id){
        return documentMapper.deleteDocument(new Document(id));
    }

    public Integer deleteDocumentByIds(List<Integer> idList){
        return documentMapper.deleteDocuments(idList);
    }

    public Integer createDocument(String docname,Integer faid){
        Document document = new Document(docname);
        document.setFaid(faid);
        document.setRealname(ShiroUtil.getCurrentRealname());
        document.setUserid(ShiroUtil.getCurrentUserId());
        document.setCreatetime(SmallUtils.getTime());
        return documentMapper.insertDocument(document);
    }

    /**
     * 处理文件上传
     * @param file 传入文件对象
     * @param faid 所属父级id
     * @return
     * @throws IOException
     */
    @Transactional
    public Integer uploadFile(MultipartFile file,Integer faid) throws IOException {
        InputStream inputStream = file.getInputStream();
        String docname = file.getOriginalFilename();
        // 获取文件后缀名
        String suffix = docname.substring(docname.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString();
        FileOutputStream outputStream = new FileOutputStream(new File(documentpath, newFileName));
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        // 文件原始名字创建文件对象
        Document document = new Document(docname,true);
        document.setSuffix(suffix);
        document.setUserid(ShiroUtil.getCurrentUserId());
        document.setCreatetime(SmallUtils.getTime());
        document.setSavename(newFileName);
        document.setContexttype(file.getContentType());
        document.setFilesize(file.getSize());
        document.setFormatsize(FileUtils.byteCountToDisplaySize(file.getSize()));
        document.setFaid(faid);
        document.setRealname(ShiroUtil.getCurrentRealname());
        return documentMapper.insertDocument(document);
    }


    private void saveFile(MultipartFile file, Integer faid) throws IOException {


    }

    public Document findDocumentById(Integer id) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("id",id);
        return findDocumentByParams(map).get(0);
    }

    public Document findDocumentByFaid(Integer faid) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("faid",faid);
        return findDocumentByParams(map).get(0);
    }




    public List<Document> breadCrumb(Integer faid){
        List<Document>  documentList = Lists.newArrayList();
        List<Document>  documentList1 = Lists.newArrayList();
        while (faid > 0){
            Document document = findDocumentById(faid);
            faid = document.getFaid();
            documentList.add(document);
            if(faid == 0){
                break;
            }
        }
        int len = documentList.size();
        if(len == 1){
            return documentList;
        }else {
            for(int i = 0 ; i < len ;i++ ){
                documentList1.add(documentList.get(len - i -1));
            }
            return documentList1;
        }

    }


}
