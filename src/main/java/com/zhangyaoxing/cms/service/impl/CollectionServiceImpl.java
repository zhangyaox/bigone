package com.zhangyaoxing.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Collection;
import com.zhangyaoxing.cms.mapper.CollectionMapper;
import com.zhangyaoxing.cms.service.CollectionService;
@Service
public class CollectionServiceImpl implements CollectionService {
	@Autowired
	CollectionMapper collectionMapper;

	@Override
	public PageInfo<Collection> selectCollections(int id,int pageNum) {
		// TODO Auto-generated method stub
		Page<Object> startPage = PageHelper.startPage(pageNum, 3);
		List<Collection> selectCollections = collectionMapper.selectCollections(id);
		return new PageInfo<Collection>(selectCollections);
	}

	@Override
	public int addCollection(Collection collection) {
		// TODO Auto-generated method stub
		return collectionMapper.addCollection(collection);
	}

	@Override
	public int delCollection(int id) {
		// TODO Auto-generated method stub
		return collectionMapper.delCollection(id);
	}
}
