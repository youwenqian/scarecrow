package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.ShoesType;
import com.shoes.scarecrow.persistence.domain.TypeCondition;
import com.shoes.scarecrow.persistence.mappers.ShoesTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:45
 */
@Service
public class TypeService {

    @Autowired
    protected ShoesTypeMapper shoesTypeMapper;

    public int saveType(ShoesType type){
        if(type == null) return 0;
        if(shoesTypeMapper.queryByName(type.getTypeName()) != null)
            return 0;
        return shoesTypeMapper.insert(type);
    }

    public List<ShoesType> queryByCondition(TypeCondition condition){
        return shoesTypeMapper.queryByCondition(condition);
    }

    public int queryCountByCondition(TypeCondition condition){
        return shoesTypeMapper.queryCountByCondition(condition);
    }

    public ShoesType queryById(int id){
        return shoesTypeMapper.queryById(id);
    }

    public ShoesType queryByName(String name){
        return shoesTypeMapper.queryByName(name);
    }

    public int updateType(ShoesType type){
        ShoesType oriType = shoesTypeMapper.queryByName(type.getTypeName());
        if(oriType == null || oriType.getId() == type.getId());
            return shoesTypeMapper.update(type);
    }

    public int delType(int id){
        return shoesTypeMapper.delById(id);
    }

}