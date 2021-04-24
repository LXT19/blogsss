package com.blog.bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowBlog implements Serializable {

    private Long id;
    private boolean published;
    private String title;
    private String flag;
    private String content;
    private Long typeId;
    private String tagIds;
    private String firstPicture;
    private String description;
    private boolean recommend;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;
    private Type type;
    private Tag tag;

}
