package com.blog.controller.admin;


import com.blog.bean.Tag;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public String Tag(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        //引入分页插件
        //在查询之前调用，传入页码和每页的大小
        PageHelper.startPage(pageNum,3);
        //startPage后面紧跟的查询是一个分页查询
        List<Tag> allTag=tagService.listTag();
        PageInfo<Tag> pageInfo=new PageInfo<>(allTag);
        model.addAttribute("pageInfo",pageInfo);
        return "/admin/tag";
    }

    @GetMapping("/tag/input")
    public String input_type(Model model){
        model.addAttribute("tag",new Tag());
        return "/admin/tag-input";
    }


    /**
     * 增加标签
     * @param tag
     * @param attributes
     * @return
     */
    @PostMapping("/input-tag")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){

        if(tagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","该标签已存在");
        }
        if(result.hasErrors()){
            return "admin/tag-input";
        }
        int i= tagService.saveTag(tag);

        if(i==0){
            attributes.addFlashAttribute("tag-message","添加失败");

        }
        else {
            attributes.addFlashAttribute("tag-message","添加成功");

        }
        return "redirect:/admin/tag";
    }

    /**
     *跳转到编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tag/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tag-input";
    }

    /**
     * 更新标签
     * @param id
     * @param tag
     * @return
     */
    @PostMapping("/tag/{id}/update")
    public String updateTag(@PathVariable Long id,Tag tag){
        int i=tagService.updateTag(id,tag);
        if(i==0){

        }
        return null;
    }

    /**
     * 删除标签
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/tag/{id}/delete")
    public String deleteTag(@PathVariable Long id,RedirectAttributes attributes){
        int i=tagService.deleteTag(id);
        if(i==0){
            attributes.addFlashAttribute("tag-message","删除失败");

        }
        else {
            attributes.addFlashAttribute("tag-message","删除成功");

        }
        return "redirect:/admin/tag";
    }

}
