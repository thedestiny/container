package com.it.service;

/**
 * Created by xieyue on 2016/7/13.
 */


import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.it.mapper.DocumentMapper;
import com.it.pojo.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DocumentServiceTestCase {
    Logger logger = LoggerFactory.getLogger(DocumentServiceTestCase.class);

    @Inject
    private DocumentService documentService;

    @Inject
    private DocumentMapper documentMapper;


    @Test
    public void findDocumentByParamsTest(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("faid",0);
        List<Document> documentList = documentMapper.queryDocumentByParams(map);
        Assert.assertNotNull(documentList);

    }


}
