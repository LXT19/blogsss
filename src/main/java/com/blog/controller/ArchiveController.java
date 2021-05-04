package com.blog.controller;


import com.blog.bean.Blog;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archive")
    public String archiveShow(Model model){

        Map<String, List<Blog>> blog=blogService.archiveBlog();

        model.addAttribute("blog",blog);

        model.addAttribute("countBlog",blogService.countBlog());

        return "archives";

    }

}
