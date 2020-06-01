package com.jbit.web;

import com.jbit.Service.AppCategoryService;
import com.jbit.pojo.AppCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("dev")
public class AppCategoryController {

    @Resource
    private AppCategoryService appCategoryService;

    @GetMapping("categoryleve1list")
    @ResponseBody
    public List<AppCategory> queryByPid(Long id){
        return appCategoryService.queryId(id);
    }
}
