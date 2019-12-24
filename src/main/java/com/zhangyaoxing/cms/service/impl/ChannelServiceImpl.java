package com.zhangyaoxing.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangyaoxing.cms.entity.Category;
import com.zhangyaoxing.cms.entity.Channel;
import com.zhangyaoxing.cms.mapper.CategoryMapper;
import com.zhangyaoxing.cms.mapper.ChannelMapper;
import com.zhangyaoxing.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Override
	public List<Channel> selectsChannel() {
		// TODO Auto-generated method stub
		return channelMapper.selectsChannel();
	}
	@Override
	public List<Category> selectsCategory(Integer id) {
		// TODO Auto-generated method stub
		return categoryMapper.selectsCategory(id);
	}

}
