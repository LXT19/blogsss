package com.blog.service.ServiceImpl;

import com.blog.bean.Type;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.TypeMapper;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int saveType(Type type) {

          return typeMapper.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getTypeById(id);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Type> listType() {
        List<Type> list= typeMapper.getAdminType();
        for(Type type:list){
            type.setBlogs(blogMapper.getBlogByType(type.getId()));
        }
        return list;
    }

    @Override
    public int updateType(Long id, Type type) {
        return typeMapper.updateType(id,type);
    }

    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);

    }



    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }
}
