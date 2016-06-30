package com.it.mapper;

import com.google.common.collect.Lists;
import com.it.pojo.Tag;
import com.it.utils.BatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by xieyue on 2016/6/29.
 * TagMapperTest
 */
public class TagMapperTest {
    Logger logger = LoggerFactory.getLogger(TagMapperTest.class);

    @Test
    public void insertTagTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        TagMapper tagMapper =  sqlSession.getMapper(TagMapper.class);
        Tag tag = new Tag(12,"开朗");
        Tag tag1 = new Tag(13,"活泼");
        Tag tag2 = new Tag(14,"活泼");
        Tag tag3 = new Tag(15,"活泼");
        Tag tag4 = new Tag(16,"活泼");
        List<Tag> tagList = Lists.newArrayList(tag,tag1,tag2,tag3,tag4);
//        tagMapper.insertTags(tagList);
        tagMapper.insertTag(tag);
        sqlSession.commit();
        sqlSession.close();

    }


}
