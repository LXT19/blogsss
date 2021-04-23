package com.blog.controller.admin;


import com.blog.bean.Type;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 标签分类页面，并查询所有标签
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/type")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){

        //引入分页插件
        //在查询之前调用，传入页码和每页的大小
        PageHelper.startPage(pageNum,5);
        //startPage后面紧跟的查询是一个分页查询
        List<Type> allType=typeService.listType();
        PageInfo<Type> pageInfo=new PageInfo<>(allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/type";

    }


    /**
     *
     * @return
     */
    @GetMapping("/type/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";

    }

    /**
     * 增加分类
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/input-type")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes,Model model){

        if(typeService.getTypeByName(type.getName())!=null){
            result.rejectValue("name","nameError","该分类已存在");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
        int i= typeService.saveType(type);

        if(i==0){
            attributes.addFlashAttribute("type-message","添加失败");

        }
        else {
            attributes.addFlashAttribute("type-message","添加成功");

        }
        return "redirect:/admin/type";
    }

    /**
     *跳转到编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/type/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";

    }

    /**
     * 更新分类
     * @param id
     * @param type
     * @return
     */
    @PostMapping("/type/{id}/update")
    public String updateType(@PathVariable Long id,Type type){

        int i=typeService.updateType(id,type);
        if(i==0){

        }
        return null;
    }

    /**
     * 删除分类
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/type/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        int i=typeService.deleteType(id);
        if(i==0){
            attributes.addFlashAttribute("type-message","删除失败");

        }
        else {
            attributes.addFlashAttribute("type-message","删除成功");

        }
        return "redirect:/admin/type";

    }

}
