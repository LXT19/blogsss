package com.blog.service;

import com.blog.bean.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlog(Long BlogId);

    int saveComment(Comment comment);

}
