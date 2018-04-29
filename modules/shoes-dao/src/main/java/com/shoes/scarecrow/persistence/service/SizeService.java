package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Size;
import com.shoes.scarecrow.persistence.mappers.SizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/14 22:40
 */
@Service
public class SizeService {
    @Autowired
    protected SizeMapper sizeMapper;

    public int save(Size size){
        if(size == null) return 0;
        if(queryByName(size.getName()) != null)
            return 0;
        return sizeMapper.insert(size);
    }

    public List<Size> queryByCondition(Map<String, Object> params){
        return sizeMapper.queryByCondition(params);
    }

    public int queryCountByCondition(Map<String, Object> params){
        return sizeMapper.queryCountByCondition(params);
    }

    public Size queryById(int id){
        return sizeMapper.queryById(id);
    }

    public Size queryByName(String name){
        return sizeMapper.queryByName(name);
    }

    public int update(Size size){
        return sizeMapper.update(size);
    }

    public int delById(List<Integer> ids){
        return sizeMapper.delById(ids);
    }
}