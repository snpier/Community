package com.example.forum.mapper;

import com.example.forum.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,content,gmtCreate,gmtModifed,creator,comment_count,view_count,like_count,tags) values (#{title},#{content},#{gmtCreate},#{gmtModifed},#{creator},#{comment_count},#{view_count},#{like_count},#{tags})")
    public void create(Question question);
}
