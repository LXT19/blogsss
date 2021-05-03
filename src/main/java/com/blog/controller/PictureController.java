package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PictureController {

    @GetMapping("/picture")
    public String toPicture(){
        return "images";
    }

}
