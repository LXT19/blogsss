package com.blog.bean;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Question implements Serializable {

    private Long id;
    private Long f_typeId;
    private String f_question;
    private String f_answer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getF_typeId() {
        return f_typeId;
    }

    public void setF_typeId(Long f_typeId) {
        this.f_typeId = f_typeId;
    }

    public String getF_question() {
        return f_question;
    }

    public void setF_question(String f_question) {
        this.f_question = f_question;
    }

    public String getF_answer() {
        return f_answer;
    }

    public void setF_answer(String f_answer) {
        this.f_answer = f_answer;
    }
}
