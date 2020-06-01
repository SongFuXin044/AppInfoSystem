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
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
     * @param querySoftwareName
     * @param queryStatus
     * @param queryFlatformId
     * @param queryCategoryLevel1
     * @param queryCategoryLevel2
     * @param devId
     * @return
     */
    public PageInfo QueryAppInfoList(Integer pagenum, Long devId, String querySoftwareName, Long queryStatus, Long queryFlatformId, Long queryCategoryLevel1, Long queryCategoryLevel2, Long queryCategoryLevel3){
        //实现分页
        PageHelper.startPage(pagenum,5);
        Example example = new Example(AppInfo.class);
        Example.Criteria criteria = example.createCriteria();
        /**
         * 条件查询
         */
        if (!StringUtils.isEmpty(querySoftwareName)){
            criteria.andLike("softwarename","%"+querySoftwareName+"%");
        }
        if (queryStatus!=null && queryStatus!=0){
            criteria.andEqualTo("status",queryStatus);
        }
        if (queryFlatformId!=null && queryFlatformId!=0){
            criteria.andEqualTo("flatformid",queryFlatformId);
        }
        if (queryCategoryLevel1!=null && queryCategoryLevel1!=null){
            criteria.andEqualTo("categorylevel1",queryCategoryLevel1);
        }
        if (queryCategoryLevel2!=null && queryCategoryLevel2!=null){
            criteria.andEqualTo("categorylevel2",queryCategoryLevel2);
        }
        if (queryCategoryLevel3!=null && queryCategoryLevel3!=null){
            criteria.andEqualTo("categorylevel3",queryCategoryLevel3);
        }
        criteria.andEqualTo("devid",devId);
        List<AppInfo> appInfos = appInfoMapper.selectByExample(example);
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
