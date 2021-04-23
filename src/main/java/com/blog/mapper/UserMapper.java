package com.blog.mapper;

import com.blog.bean.User;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * 用户mapper
 */
@Mapper
@Component
public interface UserMapper {

    User check(@Param("username") String username, @Param("password")String password);

    User getUser(Long id);

    User getUserByName(String username);

}