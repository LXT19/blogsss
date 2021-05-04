package com.blog.service;
import com.blog.bean.Type;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
/**
 * 分类
 */

public interface TypeService {

    /**
     * 保存类型
     * @param type
     * @return
     */
    int saveType(Type type);

    /**
     * 获得类型
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分页查询类型
     * @return
     */
    List<Type> listType();

    /**
     * 更新分类
     * @param id
     * @param name
     * @return
     */
    int updateType( Long id,  String  name);


    /**
     * 删除分类
     * @param id
     */
    int deleteType(Long id);

    /**
     * 通过名称获取Type
     * @param name
     * @return
     */
    Type getTypeByName(String name);

}
