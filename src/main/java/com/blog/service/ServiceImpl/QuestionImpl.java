package com.blog.service.ServiceImpl;

import com.blog.bean.Q_type;
import com.blog.bean.Question;
import com.blog.mapper.QuestionMapper;

import com.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public Question getQuestionById(Long id) {
        return questionMapper.getQuestionById(id);
    }

    @Override
    public List<Question> getQuestionByType(Long typeId) {
        return questionMapper.getQuestionByType(typeId);
    }

    @Override
    public List<Q_type> getQuestionType() {

        List<Q_type> list=questionMapper.getQuestionType();
        for(Q_type q_type:list){
            q_type.setQuestions(getQuestionDisplay(q_type.getId()));
        }
        return list;
    }

    @Override
    public List<Question> getQuestionDisplay(Long id) {
        return questionMapper.getQuestionDisplay(id);
    }

    @Override
    public Q_type getTypeById(Long id) {
        return questionMapper.getTypeById(id);
    }
}
