package com.blog.service.ServiceImpl;

import com.blog.NotFoundException;
import com.blog.bean.*;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.TagMapper;
import com.blog.mapper.TypeMapper;
import com.blog.mapper.UserMapper;

import com.blog.service.BlogService;

import com.blog.service.TagService;

import com.blog.util.MarkDownUtils;

import com.blog.util.RedisUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sun.awt.image.ImageWatched;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private RedisUtils redisService;

    /**
     * 根据博客id查询博客
     * @param id
     * @return
     */

    @Override
    public Blog getBlog(Long id) {

        //先去Redis查，没有再去数据库查
        Blog blog= (Blog) redisService.hget("blogs",id.toString());

        if(blog==null) {

            blog=blogMapper.getBlogById(id);

            blog.setType(typeMapper.getTypeById(blog.getTypeId()));

            blog.setUser(userMapper.getUser(blog.getUserId()));

        }

        return blog;

    }

    /**
     * 博客详情
     * @param id
     * @return
     */
    @Override
    public Blog getBlogToIndex(Long id) {

        //先去Redis查，没有再去数据库查
       // Blog blog= (Blog) redisService.hget("blogs",id.toString());

       // if(blog==null){

        Blog blog=blogMapper.getBlogById(id);

        String content=blog.getContent();

        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));

        blog.setType(typeMapper.getTypeById(blog.getTypeId()));

        blog.setUser(userMapper.getUser(blog.getUserId()));

        System.out.println(blog.getUser().getUsername());

       // }
        /*else {

            String content=blog.getContent();

            blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));

            blog.setUser(userMapper.getUser(blog.getUserId()));

        }*/

        return blog;
    }

    /**
     * 查询所有博客
     * @return
     */

    @Override
    public List<BlogWithType> getAllBlog() {

        return blogMapper.getAllBlog();

    }


    /**
     * 保存博客
     * @param blog
     * @return
     */
    @Override
    public int saveBlog(Blog blog) {

        blog.setCreateTime(new Date());

        blog.setUpdateTime(new Date());

        blog.setViews(0);

        return blogMapper.saveBlog(blog);

    }

    /**
     * 更新博客
     * @param blog
     * @return
     */

    @Override
    public int updateBlog( ShowBlog blog) {

        redisService.del("blogs");

        blog.setUpdateTime(new Date());

        blog.setTypeId(blog.getType().getId());

        return blogMapper.updateBlog(blog);
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @Override
    public int deleteBlog(Long id) {
        redisService.del("blogs");

        return blogMapper.deleteBlog(id);

    }

    /**
     * 根据条件查询博客列表
     * @param blogQuery
     * @return
     */
    @Override
    public List<BlogWithType> searchBlog(BlogQuery blogQuery) {

        return blogMapper.search_blog(blogQuery);

    }

    @Override
    public ShowBlog showblog(Long id) {

        ShowBlog blog= blogMapper.getBlogById_2(id);

        blog.setType(typeMapper.getTypeById(blog.getTypeId()));

       // blog.setTag(tagMapper.getTagById(blog.getTagIds()));

        return blog;
    }


    /**
     * 最新文章
     * @return
     */
    @Override
    public List<BlogWithType> blogTop() {

        List<BlogWithType> list=new ArrayList<>();
        Set<Object> set=redisService.hmgetitem("blog_top");
        //先从Redis查
        if(set.size()!=0){
            for(Object o:set){
                BlogWithType blog=(BlogWithType) redisService.hget("blog_top",o.toString());
                list.add(blog);
            }

        }
        //查不到再从数据库查
        if(list.size()==0){
            list=blogMapper.blogTop();
            //并将查到的赋值到Redis上
            for(BlogWithType blogs:list){
                String str=blogs.getId().toString();
                //redisService.hset();
                redisService.hset("blog_top",str,blogs,2, TimeUnit.DAYS);
            }
        }

        return list;

    }


    /**
     * 主页博客显示
     * @return
     */
    @Override
    public List<Blog> showAllBlog() {
        List<Blog> blog=new ArrayList<>();
        Set<Object> set=redisService.hmgetitem("blogs");
        for(Object o:set){
            Blog blogs = (Blog) redisService.hget("blogs", o.toString());

            blog.add(blogs);
        }
        if(blog.size()==0){
            blog=blogMapper.showBlog_Publish();
            for(Blog blogs:blog){

                blogs.setType(typeMapper.getTypeById(blogs.getTypeId()));

                blogs.setUser(userMapper.getUser(blogs.getUserId()));

                String str=blogs.getId().toString();

                redisService.hset("blogs",str,blogs,2,TimeUnit.DAYS);

            }
        }

        return blog;
    }
    /**
     * 根据博客名称模糊查询
     * @param title
     * @return
     */
    @Override
    public List<Blog> getBlogByName(String title) {
        List<Blog> blogs= blogMapper.getBlogByName(title);

        for(Blog blog:blogs){

            blog.setType(typeMapper.getTypeById(blog.getTypeId()));

            blog.setUser(userMapper.getUser(blog.getUserId()));

        }
        return blogs;
    }

    /**
     * 根据Type获取博客列表
     * @param id
     * @return
     */
    @Override
    public List<Blog> getBlogByType(Long id) {

        List<Blog> blogs=blogMapper.getBlogByType(id);

        for(Blog blog:blogs){

            blog.setUser(userMapper.getUser(blog.getUserId()));

            blog.setType(typeMapper.getTypeById(blog.getTypeId()));

            blog.setTag(tagService.listTagByBlog(blog.getTagIds()));

        }
        return blogs;
    }

    /**
     * 博客归档
     * @return
     */
    @Override
    public Map<String, List<Blog>> archiveBlog() {

        List<String> years= blogMapper.archiveBlogYear();
        Map<String,List<Blog>> blogMap=new HashMap<>();
        for(String string:years){
            if(string.equals(""))
                continue;
            List<Blog> blogs=blogMapper.showBlogByyear(string);
            blogMap.put(string,blogs);
        }
        return blogMap;
    }

    /**
     * 更新博客
     * @param id
     * @return
     */
    @Override
    public int updateBlogView(Long id) {

        return blogMapper.updateBlogView(id);

    }

    /**
     * 计算博客条数
     * @return
     */
    @Override
    public int countBlog() {

        return blogMapper.contBlog();

    }
}
