package com.blog;

import com.blog.bean.BlogQuery;
import com.blog.bean.BlogWithType;
import com.blog.bean.User;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

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
        redisTemplate.opsForValue().set("mykey","linxutao");
        System.out.println(redisTemplate.opsForValue().get("mykey"));

    }
}
