package com.blog.mapper;

import com.blog.bean.Q_type;
import com.blog.bean.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    Question getQuestionById(Long id);

    List<Question> getQuestionByType(Long type);

    List<Q_type> getQuestionType();

    List<Question> getQuestionDisplay(Long type);

    Q_type getTypeById(Long id);




}
