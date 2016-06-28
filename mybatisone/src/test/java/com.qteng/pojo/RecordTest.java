package com.qteng.pojo;

import com.qteng.mapper.RecordMapper;
import com.qteng.utils.BatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by xieyue on 2016/6/28.
 * RecordTest
 */
public class RecordTest {
    private Logger logger = LoggerFactory.getLogger(RecordTest.class);

    @Test
    public void queryByBookCodeTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        RecordMapper recordMapper = sqlSession.getMapper(RecordMapper.class);
        List<Record> recordList = recordMapper.queryByBookCode("100101");
        logger.debug("recordList size is {}", recordList.size());
        Record record = recordList.get(0);
        logger.debug("user is {}, author is {} ,bday is{}", record.getCard().getUser(), record.getBook().getAuthor(), record.getBday());
        Assert.assertNotNull(recordList);
        sqlSession.close();

    }

    @Test
    public void queryByCardCodeTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        RecordMapper recordMapper = sqlSession.getMapper(RecordMapper.class);
        List<Record> recordList = recordMapper.queryByCardCode("3001");
        logger.debug("recordList size is {}", recordList.size());
        Record record = recordList.get(0);
        logger.debug("user is {}, author is {} ,bday is{}", record.getCard().getUser(), record.getBook().getAuthor(), record.getBday());
        Assert.assertNotNull(recordList);
        sqlSession.close();

    }

    @Test
    public void queryCardRecordTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        RecordMapper recordMapper = sqlSession.getMapper(RecordMapper.class);
        List<Record> recordList = recordMapper.queryCardRecord("3001");
        logger.debug("recordList size is {}", recordList.size());
        Record record = recordList.get(0);
        List<Book> bookList = record.getBookList();
        for (Book book : bookList){
            System.out.println(book.getTitle());
        }
        Assert.assertNotNull(recordList);
        sqlSession.close();

    }
}
