package com.it.mapper;

import com.it.pojo.Topic;

import java.util.List;

/**
 * Created by xieyue on 2016/6/29.
 * TopicMapper
 */
public interface TopicMapper {

    Integer insertTopic(Topic topic);

    List<Topic> queryTopicByTheme(String theme);

    List<Topic> queryTopicByIssueid(Integer issueid);

    Integer deleteTopic(Topic topic);

}
