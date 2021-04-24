package com.blog;

import com.blog.bean.*;
import com.blog.mapper.TagMapper;
import com.blog.mapper.TypeMapper;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import com.blog.service.UserService;
import com.blog.util.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.bytebuddy.asm.Advice;
import org.jboss.logging.BasicLogger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private RedisUtils redisService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TypeService typeService;

    @Test
    void contextLoads() {

        List<BlogWithType> blogWithTypes=blogService.getAllBlog();
        for(BlogWithType blog:blogWithTypes){
            System.out.println(blog);
        }

    }

    @Test
    public void search_Blog() {
        User temp=new User();
        temp.setUsername("admin");
        temp.setPassword("123456");
        User user=userService.checkUserByName(temp.getUsername());
        System.out.println(user);


    }
    @Test
    public void test(){
        LinkedList<String> list=new LinkedList<String>();
        list.add("a");
        list.add(2,"b");
        System.out.println(list.get(1));

    }

    @Test
    public void isPalindrome() {
        String str="12";
        StringBuilder s=new StringBuilder(str);
        String o=s.reverse().toString();
        System.out.println(str);
        System.out.println(o);
        System.out.println(o.equals(str));
    }

    @Test
    public void redisTest(){

        List<Blog> blog=blogService.showAllBlog();

        for(Blog blog1:blog){

            String str=blog1.getId().toString();

            redisService.hset("blogs",str,blog1);

        }
        /*

        List<Blog> blogs=new LinkedList<>();
        for(Blog blog1:blog){
            String str=blog1.getId().toString();
            blogs.add((Blog) redisService.hget("blogs",str));
        }

        for(Blog blog1:blogs){
            System.out.println(blog1);
        }*/

        System.out.println(redisService.hlen("blogs"));

        Set<Object> blogs = redisService.hmgetitem("blogs");

        Blog blog1;

        for (Object object:blogs){

            blog1= (Blog) redisService.hget("blogs", (String) object);

            //System.out.println(blog.getType());

            System.out.println(blog1.getTitle());
        }
        // redisService.lSet("blog",blog);




    }

    @Test
    public void redisAddType(){
        //主页分类以及最新文章
        List<Type> types=typeService.listType();
        System.out.println(types.size());
        for(Type type:types){
            System.out.println(type.getId() + type.getName());
        }
        /*for(Type type:types){
            redisService.hset("type",type.getId().toString(),type);
        }*/
        /*
        List<BlogWithType> blogs=blogService.blogTop();

        for(BlogWithType blog:blogs){
            redisService.hset("topBlog",blog.getId().toString(),blog);
        }*/
        /*System.out.println(redisService.hlen("type"));
        Set<Object> set=redisService.hmgetitem("type");*/
       // List<Type> types=new LinkedList<>();
        /*for (Object o:set){
            //System.out.println(redisService.hget("type", o.toString()));
            System.out.println(o);
        }*/
       // System.out.println(redisService.hlen("topBlog"));
    }

    @Test
    void redisDel(){
        System.out.println(redisService.hasKey("blog"));
    }

    @Test
    void redisAdd(){

        /*List<Blog> blogs=blogService.showAllBlog();

        for(Blog blog:blogs){
            redisService.hset("blog",String.valueOf(blog.getId()),blog);
        }*/

       /* List<Blog> blogs=blogService.showAllBlog();

        for(Blog blog:blogs){
            String str=blog.getId().toString();
            redisService.hset("blogss",str,blog);
        }*/

        Set<Object> objects=redisService.hmgetitem("blogss");
        System.out.println(objects.size());
        /*List<Blog> blogs1=new LinkedList<>();
        for(Object o:objects){
            blogs1.add((Blog) redisService.hget("blogss", (String) o));
        }
        for(Blog blog:blogs1){
            System.out.println(blog.getTitle());
        }*/
    }
}
