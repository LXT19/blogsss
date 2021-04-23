package com.blog.service;

import com.blog.bean.Blog;
import com.blog.bean.BlogQuery;
import com.blog.bean.BlogWithType;
import com.blog.bean.ShowBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    List<BlogWithType> getAllBlog();

    /**
     * 根据查询条件查询博客
     * @return
     */
    List<BlogWithType> searchBlog(BlogQuery blogQuery);

    List<Blog> showAllBlog();

    ShowBlog showblog(Long id);

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog blog);

    List<BlogWithType> blogTop();

    int deleteBlog(Long id);

    List<Blog> getBlogByName(String title);

    Blog getBlogToIndex(Long id);

    int updateBlogView(Long id);

    List<Blog> getBlogByType(Long id);

    Map<String,List<Blog>> archiveBlog();

    int countBlog();



}
