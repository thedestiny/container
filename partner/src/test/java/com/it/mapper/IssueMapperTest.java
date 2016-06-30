package com.it.mapper;

import com.google.common.collect.Maps;
import com.it.pojo.Issue;
import com.it.utils.BatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/6/29.
 * IssueMapperTest
 */
public class IssueMapperTest {

    Logger logger = LoggerFactory.getLogger(IssueMapperTest.class);

    @Test
    public void insertQueTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        IssueMapper issueMapper = sqlSession.getMapper(IssueMapper.class);
        Map<String,Object> map = Maps.newHashMap();
        map.put("questioner","jim");
        map.put("question","123456");
        map.put("time","2016-06-29");
        issueMapper.insertQue(map);
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void getAllIssueTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        IssueMapper issueMapper = sqlSession.getMapper(IssueMapper.class);
        List<Issue> issueList = issueMapper.getAllIssue();
        for(Issue issue : issueList){
            logger.debug("issue is {} ",issue);
        }
        sqlSession.close();
    }

    @Test
    public void updateTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        IssueMapper issueMapper = sqlSession.getMapper(IssueMapper.class);
        issueMapper.updateAnswer("123456");
        issueMapper.updateLike("123456");
        issueMapper.updateRight("123456");
        issueMapper.updateSkim("123456");
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void getIssueByQuestionerTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        IssueMapper issueMapper = sqlSession.getMapper(IssueMapper.class);
        List<Issue> issueList = issueMapper.getIssueByQuestioner("jim");
        for(Issue issue : issueList){
            logger.debug("issue is {} ",issue);
        }
        sqlSession.close();
    }

    @Test
    public void deleteQueTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        IssueMapper issueMapper = sqlSession.getMapper(IssueMapper.class);
        issueMapper.delteQue("123456");
        sqlSession.commit();
        sqlSession.close();
    }





}
