package com.zhangyaoxing.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.User;

public interface UserService {
	PageInfo<User> selects(String name,int pageNum,int pageSize);
	
	int updateByPrimaryKeySelective(User record);
	
	int insert(User record);
	
	int insertSelective(User record);
	
	User selectUser(User user);
	User selectByName(User user);
}
