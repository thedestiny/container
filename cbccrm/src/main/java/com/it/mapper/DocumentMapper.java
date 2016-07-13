package com.it.mapper;

import com.it.pojo.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/12.
 * DocumentMapper
 */
public interface DocumentMapper {

    List<Document> queryDocumentByParams(Map<String,Object> map);

    Integer insertDocument(Document document);

    Integer deleteDocument(Document document);

    Integer deleteDocuments(List<Integer> idList);
}
