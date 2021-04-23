package com.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogWithType {

    private Long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;
    private Type type;
    private Integer published;

}
