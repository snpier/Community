package com.example.forum.mapper;

import com.example.forum.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}
