package com.blog.service;

import com.blog.bean.Q_type;
import com.blog.bean.Question;

import java.util.List;


public interface QuestionService {


    Question getQuestionById(Long id);

    List<Question> getQuestionByType(Long typeId);


    List<Q_type> getQuestionType();

    List<Question> getQuestionDisplay(Long id);

    Q_type getTypeById(Long id);

}
