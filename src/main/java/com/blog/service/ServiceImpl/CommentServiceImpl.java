package com.blog.service.ServiceImpl;

import com.blog.bean.Comment;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> listCommentByBlog(Long blogId) {

        return commentMapper.listCommentByBlog(blogId);

    }

    @Override
    public int saveComment(Comment comment) {

        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }
}
