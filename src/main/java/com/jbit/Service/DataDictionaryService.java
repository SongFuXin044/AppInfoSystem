package com.jbit.Service;

import com.jbit.mapper.DataDictionaryMapper;
import com.jbit.pojo.DataDictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataDictionaryService {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;

    /**
     * 查询数据
     * @param typecode  类型
     * @param valueid   值
     * @return
     */
    public DataDictionary queryData(String typecode,Long valueid){
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setTypecode(typecode);
        dataDictionary.setValueid(valueid);
        return dataDictionaryMapper.selectOne(dataDictionary);
    }
}
