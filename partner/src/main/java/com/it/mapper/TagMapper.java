package com.it.mapper;

import com.it.pojo.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xieyue on 2016/6/29.
 * TagMapper
 */
public interface TagMapper {

    @Insert("insert t_tag ( userid ,tag ) values ( #{userid} , #{tag})")
    Integer insertTag(Tag tag);

    @Select(" select * from t_tag where userid = #{usereid}")
    List<Tag> queryTagByUserid();

    @Select(" select * from t_tag where tag = #{tag}")
    List<Tag> queryTagByTag();

    Integer insertTags(List<Tag> tagList);

}
