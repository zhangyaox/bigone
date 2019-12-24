package com.zhangyaoxing.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Complain;
import com.zhangyaoxing.cms.entity.ComplainVO;

public interface ComplainService {
	boolean inserts(Complain complain);
	
	PageInfo<Complain> selects(ComplainVO complainVO, int pageNum);
}
