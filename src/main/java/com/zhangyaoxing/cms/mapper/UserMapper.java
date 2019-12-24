package com.zhangyaoxing.cms.mapper;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.apache.ibatis.annotations.Param;

import com.zhangyaoxing.cms.entity.User;

public interface UserMapper {
	
	User selectByName(User user);
	
	List<User> selects(@Param("name")String name);
	
	User selectUser(User user);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}