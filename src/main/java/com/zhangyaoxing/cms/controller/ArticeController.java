package com.zhangyaoxing.cms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.entity.Collection;
import com.zhangyaoxing.cms.mapper.ArticleRepository;
import com.zhangyaoxing.cms.service.ArticleService;
import com.zhangyaoxing.cms.service.CollectionService;
import com.zhangyaoxing.cms.util.HLUtils;
import com.zhangyaoxing.util.StringUtil;

@Controller
public class ArticeController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	CollectionService collectionService;
	
	@GetMapping("selectEs")
	public String selectEs(Model m,String title,@RequestParam(defaultValue = "1") int page) {
//		List<Article> findByTitle = articleRepository.findByTitle(title);
//		PageInfo<Article> pageInfo = new PageInfo<Article>(findByTitle);
//		m.addAttribute("info", pageInfo);
		long currentTimeMillis = System.currentTimeMillis();
		PageInfo<Article> findByHighLight = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, 1, 2, new String[] {"title"}, "id", title);
		m.addAttribute("info", findByHighLight);
		long currentTimeMillis2 = System.currentTimeMillis();
		m.addAttribute("date", currentTimeMillis2-currentTimeMillis);
		//		System.out.println(pageInfo.getList());
		return "index/index";//index/index
	}
	
	@RequestMapping("addcollection")//添加收藏
	public String collectionAdd(int id) {
		
		ArticleWithBLOBs selectByPrimaryKey = articleService.selectByPrimaryKey(id);//根据文章id取文章
		Collection collection = new Collection();
		Integer userId = selectByPrimaryKey.getUserId();//取到用户id
		collection.setText(selectByPrimaryKey.getTitle());//存入文章标题
		String url="http://localhost:90/my/article/selectById?id="+id;//http://localhost:90/my/article/selectById?id=383
		//boolean httpUrl1 = StringUtil.isHttpUrl1(url);
		//System.out.println();
		//if() {
			collection.setUrl(url);//存入文章路径
		//}
		
		collection.setUser_id(userId);//存入用户id
		collection.setCreated(new Date());//存入时间
		collectionService.addCollection(collection);//保存收藏
		
		return "my/article/collection";
	}
}
