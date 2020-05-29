package com.jbit.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jbit.mapper.AppInfoMapper;
import com.jbit.mapper.DataDictionaryMapper;
import com.jbit.pojo.AppInfo;
import com.jbit.pojo.AppVersion;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppInfoService {
    @Resource
    private AppInfoMapper appInfoMapper;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Resource
    private AppCategoryService appCategoryService;

    @Resource
    private AppVersionService appVersionService;

    /**
     * 查询用户属于自己的appinfo
     * @param devId
     * @return
     */
    public PageInfo QueryAppInfoList(Integer pagenum ,Long devId){
        //实现分页
        PageHelper.startPage(pagenum,5);
        AppInfo appInfo=new AppInfo();
        appInfo.setDevid(devId);
        List<AppInfo> appInfos = appInfoMapper.select(appInfo);
        bindData(appInfos);//绑定数ju
        return new PageInfo(appInfos);
    }

    /**
     * 绑定数据
     * @param appInfos
     */
    private void bindData(List<AppInfo> appInfos) {
        appInfos.forEach((app)->{
            //所属平台
            app.setFlatformname(dataDictionaryService.queryData("APP_FLATFORM",app.getFlatformid()).getValuename());
            //加载分类
            app.setCategorylevel1name(appCategoryService.queryById(app.getCategorylevel1()).getCategoryname());
            app.setCategorylevel2name(appCategoryService.queryById(app.getCategorylevel2()).getCategoryname());
            app.setCategorylevel3name(appCategoryService.queryById(app.getCategorylevel3()).getCategoryname());
            //状态
            app.setStatusname(dataDictionaryService.queryData("APP_STATUS",app.getStatus()).getValuename());
            //版本号
            AppVersion appVersion = appVersionService.queryById(app.getVersionid());
            if (appVersion!=null){
                app.setVersionno(appVersion.getVersionno());
            }
        });
    }
}
