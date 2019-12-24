package com.zhangyaoxing.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Complain;
import com.zhangyaoxing.cms.entity.ComplainVO;
import com.zhangyaoxing.cms.mapper.ArticleMapper;
import com.zhangyaoxing.cms.mapper.ComplainMapper;
import com.zhangyaoxing.cms.service.ComplainService;
import com.zhangyaoxing.cms.util.CMSExctption;
import com.zhangyaoxing.util.StringUtil;
@Service
public class ComplainServiceImpl implements ComplainService {

	@Resource
	private ComplainMapper complainMapper;
	@Resource
	private ArticleMapper articleMapper;

	@Override
	public boolean inserts(Complain complain) {
		try {
			//校验举报的地址是否合法
			boolean b = StringUtil.isHttpUrl(complain.getUrl());
			if(!b) {
				throw new CMSExctption("url 不合法");
			}
			
			//举报
			complainMapper.inserts(complain);
			//增加次数
			articleMapper.updateComplainnum(complain.getArticleId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("举报失败");
			
		}
	
	}

	@Override
	public PageInfo<Complain> selects(ComplainVO complainVO, int pageNum) {
		Page<Object> startPage = PageHelper.startPage(pageNum, 3);
		List<Complain> selects = complainMapper.selects(complainVO);
		return new PageInfo<Complain>(selects);
	}

}
