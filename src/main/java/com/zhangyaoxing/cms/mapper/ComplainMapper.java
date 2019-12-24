package com.zhangyaoxing.cms.mapper;

import java.util.List;

import com.zhangyaoxing.cms.entity.Complain;
import com.zhangyaoxing.cms.entity.ComplainVO;

public interface ComplainMapper {
	int inserts(Complain complain);
	
	//查询举报
	List<Complain> selects(ComplainVO complainVO);
}
