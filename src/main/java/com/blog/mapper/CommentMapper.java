package com.blog.mapper;

import com.blog.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    /**
     * 根据博客ID列出评论
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlog(Long blogId);

    int saveComment(Comment comment);



}
