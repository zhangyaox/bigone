package com.zhangyaoxing.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.User;
import com.zhangyaoxing.cms.mapper.UserMapper;
import com.zhangyaoxing.cms.service.UserService;
import com.zhangyaoxing.cms.util.CMSExctption;
import com.zhangyaoxing.cms.util.Md5Util;
import com.zhangyaoxing.util.StringUtil;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageInfo<User> selects(String name,int pageNum,int pageSize) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<User> selects = userMapper.selects(name);
		PageInfo<User> pageInfo = new PageInfo<User>(selects);
		return pageInfo;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User user) {
		// 这个功能里进行验证会安全一些
		//用户名非空
				if(!StringUtil.hasText(user.getUsername())) {
					//用户名为空报自定义异常
					throw new CMSExctption("用户名非空");
				}
				//用户名唯一
				User selectUser = userMapper.selectByName(user);
				if(selectUser!=null) {
					//用户名为空报自定义异常
					throw new CMSExctption("已有用户注册此名字");
				}
				
				//密码非空
				if(!StringUtil.hasText(user.getPassword())) {
					//用户名为空报自定义异常
					throw new CMSExctption("密码非空");
				}
				if(user.getPassword().length()<6||user.getPassword().length()>12) {
					//用户名为空报自定义异常
					throw new CMSExctption("密码格式6-12");
				}
		
		//用户名是否符合要求
		
		//密码是否一致
				if(!user.getPassword().equals(user.getRepassword())) {
					//用户名为空报自定义异常
					throw new CMSExctption("密码格式不一致");
				}
				user.setNickname(user.getUsername());
				user.setLocked(0);
				user.setRole("0");
				user.setCreated(new Date());
				user.setPassword(Md5Util.md5Encoding(user.getPassword()));//给密码加密
		return userMapper.insertSelective(user);
	}

	@Override
	public User selectUser(User user) {
		//用户名非空
		if(!StringUtil.hasText(user.getUsername())) {
			//用户名为空报自定义异常
			throw new CMSExctption("用户名非空");
		}
		//密码非空
		if(!StringUtil.hasText(user.getPassword())) {
			//用户名为空报自定义异常
			throw new CMSExctption("密码非空");
		}
		
		user.setPassword(Md5Util.md5Encoding(user.getPassword()));
		System.out.println("---------------"+user.getPassword());
		User selectUser = userMapper.selectByName(user);//下面验证用户名和密码是否一致
		if(!user.getUsername().equals(selectUser.getUsername())) {
			throw new CMSExctption("用户名错误");
		}
		if (!user.getPassword().equals(selectUser.getPassword())) {//因为加密所以比较加密后的密码
			//用户名没有问题  验证密码
			System.out.println(user.getPassword());
			System.out.println(selectUser.getPassword());
			throw new CMSExctption("密码错误");
		} 
		
		return selectUser;
	}

	@Override
	public User selectByName(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectByName(user);
	}

}
