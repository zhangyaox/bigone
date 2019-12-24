package com.zhangyaoxing.cms.mapper;

import java.util.List;

import com.zhangyaoxing.cms.entity.Channel;

public interface ChannelMapper {
	
	List<Channel> selectsChannel();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}