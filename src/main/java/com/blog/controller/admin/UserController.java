package com.blog.controller.admin;
import com.blog.bean.User;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BlogsController blogsController;

    /**
     * 访问localhost/admin，跳转到登录页
     * @return
     */
    @GetMapping
    public String loginPage(){

        return "admin/login";

    }

    /**
     * 进行登陆验证
     * @param username
     * @param password
     * @param session
     * @param model
     * @return
     */
    @PostMapping( "/login")
    public String login(String username,
                        String password,
                        HttpSession session,
                        Model model){

       // System.out.println("执行认证操作");
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登陆数据
        UsernamePasswordToken token= new UsernamePasswordToken(username, MD5Utils.code(password));
       // System.out.println("封装用户数据");
        try{
            subject.login(token);
            Subject user=SecurityUtils.getSubject();
            Session session1= user.getSession();
            session1.setAttribute("loginUser",user);
            User user1= (User) SecurityUtils.getSubject().getPrincipal();
            System.out.println(user1);
            session.setAttribute("user",user1);
            //System.out.println("存在用户，放行");
            return "/admin/index";
        }catch (UnknownAccountException e){
            model.addAttribute("messages","用户名错误");
         //   System.out.println("用户名错误");
            return "/admin/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("messages","密码错误");
          //  System.out.println("密码错误");
            return "/admin/login";
        }
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
