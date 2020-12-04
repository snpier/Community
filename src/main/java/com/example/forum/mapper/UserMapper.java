package com.example.forum.mapper;

import com.example.forum.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("inster into user (name,accountId,token,gmtCreate,gmtMofifed) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtMofifed})")
    void inster(User user);
}
