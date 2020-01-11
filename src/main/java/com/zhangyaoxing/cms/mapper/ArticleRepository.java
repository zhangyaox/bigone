package com.zhangyaoxing.cms.mapper;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zhangyaoxing.cms.entity.Article;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {
	List<Article> findByTitle(String title);
}
