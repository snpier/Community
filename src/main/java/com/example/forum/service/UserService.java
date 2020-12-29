package com.example.forum.service;

import com.example.forum.mapper.UserMapper;
import com.example.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser = userMapper.findByAccountId(user.getAccountId());
        if (dbuser == null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtMofifed(user.getGmtCreate());
            userMapper.inster(user);
        }else {
            dbuser.setGmtMofifed(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setAvatarUrl(user.getName());
            dbuser.setAvatarUrl(user.getToken());
            userMapper.update(dbuser);
        }
    }
}
