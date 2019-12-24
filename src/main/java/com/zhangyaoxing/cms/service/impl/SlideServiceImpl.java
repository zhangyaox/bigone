package com.zhangyaoxing.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangyaoxing.cms.entity.Slide;
import com.zhangyaoxing.cms.mapper.SlideMapper;
import com.zhangyaoxing.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {
	@Autowired
	private SlideMapper slideMapper;

	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return slideMapper.selects();
	}
}
