package com.zhangyaoxing.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.mapper.ArticleMapper;
import com.zhangyaoxing.cms.service.ArticleService;

public class RedisArticleService implements ArticleService {
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	KafkaTemplate<String, String> kafka;
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public PageInfo<Article> selectsArticle(Article article, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSelective(ArticleWithBLOBs record) {
		System.out.println("11111111111");
		redisTemplate.opsForValue().set(record.getId()+"", record);
		String jsonString = JSON.toJSONString(record);
		kafka.send("1708D", jsonString);
		return 0;
	}

	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Article article) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int upd(Article article) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
