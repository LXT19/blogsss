package com.blog.mapper;

import com.blog.bean.Blog;
import com.blog.bean.BlogQuery;
import com.blog.bean.BlogWithType;
import com.blog.bean.ShowBlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface BlogMapper {

    //主页查询博客
    List<Blog> showAllBlog();

    List<Blog> showBlog_Publish();

    List<Blog> BlogById_publish(Long id);

    //根据博客id查询博客
    Blog getBlogById(Long id);

    //根据博客id查询博客后端展示
    ShowBlog getBlogById_2(Long id);

    //列出所有包含分类的博客信息
    List<BlogWithType> getAllBlog();

    //保存博客
    int saveBlog(Blog blog);

    //更新博客
    int updateBlog(ShowBlog blog);

    //删除博客
    int deleteBlog(Long id);

    //根据博客标题查询博客
    List<Blog> getBlogByName(String title);

    //根据查询条件组合查询博客
    List<BlogWithType> search_blog(BlogQuery Blog);

    //列出最新的几篇博客
    List<BlogWithType> blogTop();

    /**根据标签查找博客
     *
     * @param tagId
     * @return
     */
    List<Blog> BlogByTag(Long tagId);

    //更新博客浏览数
    int updateBlogView(Long cancelById);

    //根据type查询博客
    List<Blog> getBlogByType(Long typeId);

    //归档年份
    List<String> archiveBlogYear();

    //根据年份查询博客
    List<Blog> showBlogByyear(String years);
    //博客数量
    int contBlog();




}
