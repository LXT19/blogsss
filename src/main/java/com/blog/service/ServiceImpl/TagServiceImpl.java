package com.blog.service.ServiceImpl;

import com.blog.bean.Tag;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> list=tagMapper.getAllTag();
        for(Tag tag:list){
            tag.setBlogList(blogMapper.BlogByTag(tag.getId()));
           // System.out.println(blogMapper.BlogByTag(tag.getId()));
        }
        return tagMapper.getAllTag();
    }

    @Override
    public int updateTag(Long id, String name) {
        return tagMapper.updateTag(id,name);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> listTagByBlog(String tags) {
        String []a=tags.split(",");
        List<Tag> tagsList=new ArrayList<>();
        for(String i:a){
            Tag tag=tagMapper.getTagById(Long.parseLong(i));
            tagsList.add(tag);
        }


        return tagsList;
    }
}
