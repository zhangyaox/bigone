package com.zhangyaoxing.cms.service;

import java.util.List;

import com.zhangyaoxing.cms.entity.Category;
import com.zhangyaoxing.cms.entity.Channel;

public interface ChannelService {

	List<Channel> selectsChannel();
	
	List<Category> selectsCategory(Integer id);
}
