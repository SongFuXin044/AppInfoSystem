package com.jbit.Service;

import com.jbit.mapper.AppCategoryMapper;
import com.jbit.pojo.AppCategory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppCategoryService {
    @Resource
    private AppCategoryMapper appCategoryMapper;

    /**
     * id查询
     * @param id
     * @return
     */
    public AppCategory queryById(Long id){
        return appCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询分类ID
     * @param id
     * @return
     */
    public List<AppCategory> queryId(Long id){
        Example example = new Example(AppCategory.class);
        Example.Criteria criteria=example.createCriteria();
        if (id==null){
            //第一级
            criteria.andIsNull("parentid");
        }else {
            //其他
            criteria.andEqualTo("parentid",id);
        }
        return appCategoryMapper.selectByExample(example);
    }
}
