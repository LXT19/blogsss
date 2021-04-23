package com.blog.service.ServiceImpl;

import com.blog.bean.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User check(String username,String password ){

        return userMapper.check(username, MD5Utils.code(password));

    }

    @Override
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }


    @Override
    public User checkUserByName(String username) {

        return userMapper.getUserByName(username);

    }
}
