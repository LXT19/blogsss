package com.blog.controller;
import com.blog.bean.Blog;
import com.blog.bean.Comment;
import com.blog.bean.User;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;


    /**
     * 更新评论后重新跳转到blog
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/comment/{id}")
    public String listComment(@PathVariable Long id, Model model){

        List<Comment> comment=commentService.listCommentByBlog(id);
        model.addAttribute("comments",comment);
        Blog blog=blogService.getBlogToIndex(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @PostMapping("/commentSave")
    public String saveComment(Comment comment,HttpSession session){
        Long blogId=comment.getBlogId();
        User user= (User) session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(userService.getUser(user.getId()).getAvatar());
            comment.setAdmin(true);
        }
        else {
            comment.setAvatar("https://picsum.photos/id/101/100/100");
            comment.setAdmin(false);
        }
        commentService.saveComment(comment);
        return  "redirect:/comment/" + blogId;
    }

}
