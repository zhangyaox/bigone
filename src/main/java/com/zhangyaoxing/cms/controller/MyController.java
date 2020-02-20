package com.zhangyaoxing.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.entity.Category;
import com.zhangyaoxing.cms.entity.Channel;
import com.zhangyaoxing.cms.entity.Collection;
import com.zhangyaoxing.cms.entity.User;
import com.zhangyaoxing.cms.service.ArticleService;
import com.zhangyaoxing.cms.service.ChannelService;
import com.zhangyaoxing.cms.service.CollectionService;
import com.zhangyaoxing.cms.service.UserService;

/**
 * 
 * @ClassName: MyController 
 * @Description: TODO
 * @author: 13362
 * @date: 2019年12月12日 下午3:37:26
 */
@RequestMapping("my")
@Controller
public class MyController {
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	CollectionService collectionService;
	
	
	@RequestMapping(value = {"/","","index"})
	public String myIndex() {
		return "my/index";
	}
	@RequestMapping("article/publish")
	public String publish() {
		return "my/article/publish";
	}
	@RequestMapping("article/collection")//查找收藏
	public String collection(HttpSession session, @RequestParam(defaultValue = "1")int pageNum,Model m) {//跳到收藏页面
		User user = (User) session.getAttribute("user");
		User selectByName = userService.selectByName(user);
		Integer id = selectByName.getId();//用户id
		PageInfo<Collection> selectCollections = collectionService.selectCollections(id, pageNum);
		m.addAttribute("info", selectCollections);
		return "my/article/collection";
	}
	@ResponseBody
	@RequestMapping("article/delcollection")//删除收藏
	public Object delcollection(int id) {
		int delCollection = collectionService.delCollection(id);
		return delCollection;
	}
	
	@RequestMapping("article/selectById")
	public String selectById(int id, Model m) {
		ArticleWithBLOBs selectByPrimaryKey = articleService.selectByPrimaryKey(id);
		m.addAttribute("selectByPrimaryKey", selectByPrimaryKey);
		return "my/article/article";
	}
	@RequestMapping("article/articles")
	public String articles(HttpSession session, Article article, Model m, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "3") int pageSize) {///////
		User user = (User) session.getAttribute("user");
		User selectByName = userService.selectByName(user);
		article.setUserId(selectByName.getId());
		PageInfo<Article> info = articleService.selectsArticle(article, pageNum, pageSize);
		m.addAttribute("info", info);
		return "my/article/articles";
	}
	@ResponseBody
	@RequestMapping("Channel/selectsChannel")
	public Object selectsChannel() {
		List<Channel> selectsChannel = channelService.selectsChannel();
		return selectsChannel;
	}
	@ResponseBody
	@RequestMapping("Category/selectsCategory")
	public Object selectsCategory(int id) {
		List<Category> selectsCategory = channelService.selectsCategory(id);
		return selectsCategory;
	}
	
	@ResponseBody
	@RequestMapping("article/update")
	public Object update(Article article) {
		boolean update = articleService.update(article);
		return update;
	}
	
	@ResponseBody
	@RequestMapping("Article/addpublish")
	public Object addpublish(HttpSession session,ArticleWithBLOBs articleWithBLOBs, MultipartFile myfile) throws IllegalStateException, IOException {//ArticleWithBLOBs  关于富文本的属性
		
		String path="d:/pic/";//把文件放到d盘
		/*
		 * File file = new File(path); file.mkdirs();
		 */
		if(!myfile.isEmpty()) {//myfile  要和我 网页的 name中的一样
			
			//获取原始的文件名称
			String filename=myfile.getOriginalFilename();
			
			//防止文件重名
			String newfileName=UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			myfile.transferTo(new File(path,newfileName));//拷贝
			//上面是把我传过来的 图片存入到了  d盘
			articleWithBLOBs.setPicture(newfileName);//把新的文件名存入表
			
		}
		User user = (User) session.getAttribute("user");
		User selectByName = userService.selectByName(user);
		articleWithBLOBs.setUserId(selectByName.getId());
		articleWithBLOBs.setCreated(new Date());//发布时间
		articleWithBLOBs.setStatus("0");//文章状态 0带审核
		articleWithBLOBs.setHits(0);//点击量
		articleWithBLOBs.setDeleted(0);//是否删除
		articleWithBLOBs.setUpdated(new Date());//修改时间
		
		articleWithBLOBs.setHot(0);// 是否 热门文章
		
		String jsonString = JSON.toJSONString(articleWithBLOBs);
		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("1708D", jsonString);
		return articleService.insertSelective(articleWithBLOBs);
	}
}
