package com.blog.service.ServiceImpl;

import com.blog.bean.Type;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.TypeMapper;
import com.blog.service.TypeService;
import com.blog.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisUtils redisService;

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
        List<Type> list= new LinkedList<>();
        //先从Redis查
//        Set<Object> set=redisService.hmgetitem("type");
//        for(Object o:set){
//            Type type=(Type) redisService.hget("type",o.toString());
//            //type.setBlogs(blogMapper.getBlogByType(type.getId()));
//            list.add(type);
//        }
        //Redis没有，再去数据库查
        if(list.size()==0){
            list=typeMapper.getAdminType();
            for(Type type:list){
                type.setBlogs(blogMapper.getBlogByType(type.getId()));
                //同时将数据保存到Redis中
                //redisService.hset("type",type.getId().toString(),type);
            }
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
