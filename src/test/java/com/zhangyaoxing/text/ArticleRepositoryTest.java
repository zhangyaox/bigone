package com.zhangyaoxing.text;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.mapper.ArticleMapper;
import com.zhangyaoxing.cms.mapper.ArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleRepositoryTest {
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ArticleMapper articleMapper;
	
	@Test
	public void text() {
		Article article = new Article();
		article.setStatus("1");
		
//		 List<ArticleWithBLOBs> selectArticleWithBLOBs =
//		 articleMapper.selectArticleWithBLOBs(article);
//		 System.out.println(selectArticleWithBLOBs);
//		  articleRepository.saveAll(selectArticleWithBLOBs);
		List<Article> selectsArticle = articleMapper.selectsArticle(article);
		articleRepository.saveAll(selectsArticle);
	}
	@Test
	public void findByTitle() {
		List<Article> findByTitle = articleRepository.findByTitle("庆余年");
		System.out.println(findByTitle);
	}
}
