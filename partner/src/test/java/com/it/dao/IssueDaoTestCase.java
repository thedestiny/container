package com.it.dao;

import com.it.pojo.Issue;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by xieyue on 2016/6/27.
 * IssueDaoTestCase
 */
public class IssueDaoTestCase {

    private Logger logger = LoggerFactory.getLogger(IssueDaoTestCase.class);
    private static IssueDao issueDao = new IssueDao();

    @Test
    public void getAllIssueTest(){
        List<Issue> issueList ;
        issueList = issueDao.getAllIssue();
        System.out.println("=========================");
        List<Issue> issueList1 = issueDao.getAllIssue();
        System.out.println(issueList1.size());
        Assert.assertNotNull(issueList1);
        Assert.assertEquals(issueList.size(),4);
    }





}
