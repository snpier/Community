package com.example.forum.mapper;

import com.example.forum.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,accountId,token,gmtCreate,gmtMofifed,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtMofifed},#{avatarUrl})")
    void inster(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where accountId = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token},gmt_mofifed = #{gmtMofifed},avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
