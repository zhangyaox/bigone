package com.zhangyaoxing.cms.mapper;

import java.util.List;

import com.zhangyaoxing.cms.entity.Collection;

public interface CollectionMapper {
	List<Collection> selectCollections(int id);
	
	int addCollection(Collection collection);
	
	int delCollection(int id);
}
