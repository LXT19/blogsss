package com.blog.controller;

import com.blog.bean.Q_type;
import com.blog.bean.Question;
import com.blog.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Face_questionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/face")
    public String face_question(Model model,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){

        PageHelper.startPage(pageNum,5);

        List<Q_type> q_types_list=questionService.getQuestionType();

        PageInfo<Q_type> pageInfo=new PageInfo<>(q_types_list);
        model.addAttribute("pageInfo",pageInfo);

        return "question_type";
    }

    @GetMapping("/question/{id}")
    public String type_Question(Model model,@PathVariable Long id){

        List<Question> list=questionService.getQuestionByType(id);

        Q_type q_type=questionService.getTypeById(id);

        model.addAttribute("q_type",q_type);

        model.addAttribute("question_list",list);

        return "face_question";

    }


}
