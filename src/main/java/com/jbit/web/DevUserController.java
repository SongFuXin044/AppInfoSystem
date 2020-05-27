package com.jbit.web;

import com.jbit.Service.DevUserService;
import com.jbit.pojo.DevUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("dev")//区分前缀
public class DevUserController {
    @Resource
    private DevUserService devUserService;
    /**
     * 开发者登录
     * @return
     */
    @PostMapping("dologin")
    public String Devlogin(Model model, HttpSession session, String devcode , String devpassword){
        DevUser devUser = devUserService.queryLogin(devcode, devpassword);
        if (devUser!=null){
            session.setAttribute("devuser",devUser);
            return "/developer/main";//如果用户存在,就转发到后台主页面
        }
        model.addAttribute("error","用户名密码错误.");
        return "/devlogin";
    }

    /**
     * 用退出功能
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();//初始化session里面的数据
        return "redirect:jsp/devlogin";
    }
 }
