package com.jbit.web;

import com.jbit.Service.AppInfoService;
import com.jbit.mapper.AppInfoMapper;
import com.jbit.pojo.DevUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("dev/app")
public class AppInfoController {
    @Resource
    private AppInfoService appInfoService;

    @RequestMapping("/list")
    public  String list(HttpSession httpSession, Model model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pagenum){
        DevUser user = (DevUser)httpSession.getAttribute("devuser");
        //防止Session丢失
        model.addAttribute("pageInfo",appInfoService.QueryAppInfoList(pagenum,user.getId()));
        return "/developer/appinfolist";
    }
}
