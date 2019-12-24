package com.zhangyaoxing.cms.mapper;

import java.util.List;

import com.zhangyaoxing.cms.entity.Slide;

public interface SlideMapper {
	
	//查找轮播图
	List<Slide> selects();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Slide record);

    int insertSelective(Slide record);

    Slide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slide record);

    int updateByPrimaryKey(Slide record);
}