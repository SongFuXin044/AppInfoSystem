package com.jbit.Service;

import com.jbit.Utils.AppUtils;
import com.jbit.mapper.DevUserMapper;
import com.jbit.pojo.DevUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DevUserService  {
    @Resource
    private DevUserMapper devUserMapper;


    public DevUser queryLogin(String devcode , String devpassword){
    DevUser user =new DevUser();
    user.setDevcode(devcode);
    user.setDevpassword(AppUtils.encoderByMd5(devpassword));//MD5加密
    return devUserMapper.selectOne(user);
    }
}
