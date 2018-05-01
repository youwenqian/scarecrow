package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Message;
import com.shoes.scarecrow.persistence.mappers.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author:wangyucheng7
 * @Description：
 * @Date：Created in 15:11 2018/4/30
 * @Modified By:
 */
@Service
public class MessageServcie {

    @Autowired
    private MessageMapper messageMapper;

    public int delById(List<Integer> ids){
        int count = 0;
        if (!CollectionUtils.isEmpty(ids)){
            for(Integer id : ids){
                if(id != null){
                    count += messageMapper.deleteByPrimaryKey(id);
                }
            }
        }
        return  count;
    }

    public int save(Message record){
        return  messageMapper.insert(record);
    }

    public Message queryById(Integer id){
        return  messageMapper.selectByPrimaryKey(id);
    }

    public List<Message> selectByMap(Map<String, Object> params){
        return  messageMapper.selectByMap(params);
    }

    public int selectCountByMap(Map<String, Object> params){
        return  messageMapper.selectCountByMap(params);
    }

    public int updateByPrimaryKeySelective(Message record){
        return  messageMapper.updateByPrimaryKeySelective(record);
    }

    public int updateWithContent(Message record){
        return  messageMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    public int update(Message record){
        return  messageMapper.updateByPrimaryKey(record);
    }



}
