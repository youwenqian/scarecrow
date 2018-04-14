package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Color;
import com.shoes.scarecrow.persistence.mappers.ColorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/14 22:40
 */
@Service
public class ColorService {
    @Autowired
    protected ColorMapper colorMapper;

    public int save(Color color){
        if(color == null) return 0;
        if(queryByName(color.getName()) != null)
            return 0;
        return colorMapper.insert(color);
    }

    public List<Color> queryByCondition(Map<String, Object> params){
        return colorMapper.queryByCondition(params);
    }

    public int queryCountByCondition(Map<String, Object> params){
        return colorMapper.queryCountByCondition(params);
    }

    public Color queryById(int id){
        return colorMapper.queryById(id);
    }

    public Color queryByName(String name){
        return colorMapper.queryByName(name);
    }

    public int update(Color color){
        return colorMapper.update(color);
    }

    public int delById(List<Integer> ids){
        return colorMapper.delById(ids);
    }
}