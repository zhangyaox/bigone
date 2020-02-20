package com.zhangyaoxing.cms.mapper;

import java.util.List;

import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;

public interface ArticleMapper {
	
	int addOne(int id);//添加阅读次数
	
	int upd(Article article);
	
	List<Article> selectsArticle(Article article);
	
	List<ArticleWithBLOBs> selectArticleWithBLOBs(Article article);
	
	int  update(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
    
    int updateComplainnum(Integer articleId);//更新举报数量
}