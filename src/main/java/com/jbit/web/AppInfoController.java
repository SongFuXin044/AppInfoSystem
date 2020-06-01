package com.jbit.web;

import com.jbit.Service.AppCategoryService;
import com.jbit.Service.AppInfoService;
import com.jbit.Service.DataDictionaryService;
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

    @Resource
    private AppCategoryService appCategoryService;

    @Resource
    private DataDictionaryService dataDictionaryService;


    @RequestMapping("/list")
    public String list(HttpSession httpSession, Model model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pagenum,
                       String querySoftwareName,
                       Long queryStatus,
                       Long queryFlatformId,
                       Long queryCategoryLevel1,
                       Long queryCategoryLevel2,
                       Long queryCategoryLevel3
                       ){
        DevUser user = (DevUser)httpSession.getAttribute("devuser");
        //防止Session丢失
        model.addAttribute("pageInfo",appInfoService.QueryAppInfoList(pagenum,user.getId(),querySoftwareName,queryStatus,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3));
        //处理一级分类
        model.addAttribute("categoryLevel1List",appCategoryService.queryId(null));
        //处理二级分类
        if (queryCategoryLevel1!=null){
            model.addAttribute("categoryLevel2List",appCategoryService.queryId(queryCategoryLevel1));
        }
        //处理三级分类
        if (queryCategoryLevel2!=null){
            model.addAttribute("categoryLevel3List",appCategoryService.queryId(queryCategoryLevel2));
        }
        //平台
        model.addAttribute("flatFormList",dataDictionaryService.queryDataList("APP_FLATFORM"));
        //状态
        model.addAttribute("statusList",dataDictionaryService.queryDataList("APP_STATUS"));
        //参数重新封装传回页面
        if (queryCategoryLevel1!=null){
            System.out.println("信息封装反填页面");
            System.out.println("querySoftwareName = " + querySoftwareName);
            System.out.println("queryStatus = " + queryStatus);
            System.out.println("queryFlatformId = " + queryFlatformId);
            System.out.println("queryCategoryLevel1 = " + queryCategoryLevel1);
            System.out.println("queryCategoryLevel2 = " + queryCategoryLevel2);
            System.out.println("queryCategoryLevel3 = " + queryCategoryLevel3);
        }
        model.addAttribute("querySoftwareName",querySoftwareName);
        model.addAttribute("queryStatus",queryStatus);
        model.addAttribute("queryFlatformId",queryFlatformId);
        model.addAttribute("queryCategoryLevel1",queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2",queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3",queryCategoryLevel3);


        return "/developer/appinfolist";
    }
}
