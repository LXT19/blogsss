package com.blog.bean;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Tag {
    private Long id;

    @NotEmpty(message = "标签名称不能为空")
    private String name;

    private List<Blog> blogList;

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}