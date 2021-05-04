package com.blog.service;

import com.blog.bean.Tag;

import java.util.List;

public interface TagService {

    /**
     * 保存标签
     * @param tag
     * @return
     */
    int saveTag(Tag tag);

    /**
     * 获得标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 分页查询
     * @return
     */
    List<Tag> listTag();

    /**
     * 更新标签
     * @param id
     * @param tag
     * @return
     */
    int updateTag(Long id,String name);


    /**
     * 删除标签
     * @param id
     */
    int deleteTag(Long id);

    /**
     * 通过名称获取Tag
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    List<Tag> listTagByBlog(String tags);
}
