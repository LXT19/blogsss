package com.blog.controller;
import com.blog.bean.*;
import com.blog.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum)  {

        //分页查询
        PageHelper.startPage(pageNum,5);

        List<Blog> allBlog = blogService.showAllBlog();

        //查询博客
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        //查询分类
        List<Type> allType=typeService.listType();
        //查询标签
       // List<Tag> allTag=tagService.listTag();

        List<BlogWithType> topBlog=blogService.blogTop();

        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("types",allType);

       // model.addAttribute("tags",allTag);

        model.addAttribute("topBlog",topBlog);

        return "index";

    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";

    }
    @GetMapping("/input")
    public String input(){
        return "./admin/blogs-input";
    }

    @PostMapping("/search")
    public String search(String title,Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){

        PageHelper.startPage(pageNum,5);

        List<Blog> allBlog=blogService.getBlogByName(title);

        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);

        model.addAttribute("pageInfo",pageInfo);

        return "search";

    }

    /**
     * 博客详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){

        Blog blog=blogService.getBlogToIndex(id);
        System.out.println(blog.getUser().getUsername());
        blogService.updateBlogView(blog.getId());
        blog.setTag(tagService.listTagByBlog(blog.getTagIds()));
        //List<Tag> tag=tagService.listTagByBlog(blog.getTagIds());
        List<Comment> comment=commentService.listCommentByBlog(id);
        for(Comment comment1:comment){
            System.out.println(comment1.isAdmin());
        }
        model.addAttribute("comments",comment);
        model.addAttribute("blog",blog);
        return "blog";
    }

}
