package com.blog.service;

import com.blog.bean.User;

public interface UserService {

    User check(String username,String password);

    User getUser(Long id);

    User checkUserByName(String username);
}
