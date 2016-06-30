package com.it.mapper;

import com.it.pojo.Issue;
import org.apache.ibatis.annotations.Delete;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/6/29.
 * IssueMapper
 */
public interface IssueMapper {
    /**
     * 插入问题
     * @param map 提问者，问题，提问时间的 集合
     * @return 受影响行数
     */
    Integer insertQue(Map<String,Object> map);
    @Delete("delete from issue where question = #{question}")
    Integer delteQue(String question);

    /**
     * 更新回答数量
     * @param question 传入问题的md5值
     * @return 受影响行数
     * updateLike，updateRight，updateSkim
     * 浏览数
     */
    Integer updateAnswer(String question);

    /**
     * 更新点赞数
     * @param question 传入问题的md5值
     * @return  受影响行数
     */
    Integer updateLike(String question);

    /**
     * 更新有用数
     * @param question 传入问题的md5值
     * @return 受影响行数
     */
    Integer updateRight(String question);

    /**
     * 更新浏览数
     * @param question 传入问题的md5值
     * @return 受影响行数
     */
    Integer updateSkim(String question);

    /**
     * 获取所有的问题
     * @return 返回Issue的集合
     */
    List<Issue> getAllIssue();

    /**
     * @param questioner 根据提问者查找Issue
     * @return 返回Issue的集合
     */
    List<Issue> getIssueByQuestioner(String questioner);


}
