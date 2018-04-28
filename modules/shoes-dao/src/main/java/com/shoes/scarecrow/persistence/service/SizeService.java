package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Size;
import com.shoes.scarecrow.persistence.domain.SizeCondition;
import com.shoes.scarecrow.persistence.mappers.SizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:45
 */
@Service
public class SizeService {

    @Autowired
    protected SizeMapper sizeMapper;

    public int saveSize(Size size){
        if(size == null) return 0;
        if(sizeMapper.queryByName(size.getSizeName()) != null)
            return 0;
        return sizeMapper.insert(size);
    }

    public List<Size> queryByCondition(SizeCondition condition){
        return sizeMapper.queryByCondition(condition);
    }

    public int queryCountByCondition(SizeCondition condition){
        return sizeMapper.queryCountByCondition(condition);
    }

    public Size queryById(int id){
        return sizeMapper.queryById(id);
    }

    public Size queryByName(String name){
        return sizeMapper.queryByName(name);
    }

    public int updateSize(Size size){
        Size oriSize = sizeMapper.queryByName(size.getSizeName());
        if(oriSize == null || oriSize.getId() == size.getId());
            return sizeMapper.update(size);
    }

    public int delSize(int id){
        return sizeMapper.delById(id);
    }

}