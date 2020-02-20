package com.zhangyaoxing.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.entity.Complain;

public interface ArticleService {
	
	
	
	PageInfo<Article> selectsArticle(Article article, int pageNum, int pageSize);
	int insertSelective(ArticleWithBLOBs record);
	  ArticleWithBLOBs selectByPrimaryKey(Integer id);
	  boolean  update(Article article);
	  int upd(Article article);
	  int updateByPrimaryKeySelective(ArticleWithBLOBs record);

}
