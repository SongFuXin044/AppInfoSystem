package com.jbit.Test;

import com.jbit.Service.DevUserService;
import com.jbit.pojo.DevUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class T {
    @Resource
    private DevUserService devUserService;

    @Test
    public void Login_User() {
        DevUser user = devUserService.queryLogin("SuperManager", "123456");
        System.out.println("用户为 ："+user);
    }
}
