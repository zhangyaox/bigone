package com.zhangyaoxing.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.entity.Complain;
import com.zhangyaoxing.cms.mapper.ArticleMapper;
import com.zhangyaoxing.cms.mapper.ComplainMapper;
import com.zhangyaoxing.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ComplainMapper complainMapper;

	@Override
	public PageInfo<Article> selectsArticle(Article article, int pageNum, int pageSize) {
		
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<Article> selectsArticle = articleMapper.selectsArticle(article);
		return new PageInfo<Article>(selectsArticle);
	}

	@Override
	public int insertSelective(ArticleWithBLOBs record) {
		// TODO Auto-generated method stub
		return articleMapper.insertSelective(record);
	}

	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article)>0;
	}

	@Override
	public int upd(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.upd(article);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleWithBLOBs record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeySelective(record);
	}

}
