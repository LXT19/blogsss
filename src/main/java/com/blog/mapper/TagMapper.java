package com.blog.mapper;

import com.blog.bean.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TagMapper {

    int saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

   // List<Tag> getAdminTag();

    int deleteTag(Long id);

    int updateTag(@Param("id") Long id, @Param("name") String name);


}
