package com.blog.controller;


import com.blog.bean.Blog;
import com.blog.bean.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;




    @GetMapping("/type/{id}")
    public String ShowType(@PathVariable long id, Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){

        List<Type> types=typeService.listType();
        if(id==-1){
            id=types.get(0).getId();
        }
        //分页查询
        PageHelper.startPage(pageNum,5);

        List<Blog> blogs=blogService.getBlogByType(id);


        PageInfo<Blog> pageInfo=new PageInfo<>(blogs);

        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("types",types);

        return "type";

    }


}
