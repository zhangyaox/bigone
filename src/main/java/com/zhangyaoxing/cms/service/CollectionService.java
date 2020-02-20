package com.zhangyaoxing.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Collection;

public interface CollectionService {
	PageInfo<Collection> selectCollections(int id,int pageNum);
	
	int addCollection(Collection collection);
	
	int delCollection(int id);
}
