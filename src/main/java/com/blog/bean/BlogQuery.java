package com.blog.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {


    private Long id;
    private String title;
    private String typeId;
    private boolean recommend;
    private Integer isRecomend;


    public void setIsRecomend() {
        if(this.recommend)
            this.isRecomend = 1;
        else
            this.isRecomend=0;
    }

}
