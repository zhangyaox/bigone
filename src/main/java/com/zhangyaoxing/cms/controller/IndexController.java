package com.zhangyaoxing.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.entity.Category;
import com.zhangyaoxing.cms.entity.Channel;
import com.zhangyaoxing.cms.entity.Complain;
import com.zhangyaoxing.cms.entity.Slide;
import com.zhangyaoxing.cms.entity.User;
import com.zhangyaoxing.cms.service.ArticleService;
import com.zhangyaoxing.cms.service.ChannelService;
import com.zhangyaoxing.cms.service.ComplainService;
import com.zhangyaoxing.cms.service.SlideService;
import com.zhangyaoxing.cms.service.UserService;
import com.zhangyaoxing.cms.util.CMSExctption;


@Controller
public class IndexController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private ComplainService complainService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"","/","index"})
	public String Index(Model m, Article article, @RequestParam(defaultValue = "1")int page) {
		
		//编写 推荐 的热门  就是我不点击菜单 时显示的文章  不点击菜单就没有ChannelId
		if(null==article.getChannelId()) {
			//查询广告  //轮播图的 图片 查找
			List<Slide> selects = slideService.selects();
			m.addAttribute("selects", selects);
			//创建一个新的 文章 类对象
			Article article2 = new Article();
			article2.setHot(1);
			PageInfo<Article> info = articleService.selectsArticle(article2, page, 3);
			m.addAttribute("info", info);
		}
		
		//在最开始传递 Article 对象 用来 取当前的对象的值
		m.addAttribute("article", article);
		//查找出所有类型 是用来做菜单
		List<Channel> selectsChannel = channelService.selectsChannel();
		m.addAttribute("selectsChannel", selectsChannel);
		
		//把菜单查找到后 可以顺便查找一下这个菜单对应的类型
		if(null!=article.getChannelId()) {
			
			//非空查询 菜单下的所有分类
			List<Category> selectsCategory = channelService.selectsCategory(article.getChannelId());
			m.addAttribute("selectsCategory", selectsCategory);
			//全部的功能  查找这个菜单的 所有分类的文章
			PageInfo<Article> info = articleService.selectsArticle(article, page, 3);
			m.addAttribute("info", info);
		}
		if(null!=article.getCategoryId()) {
			//非空  查找 分类下的文章
			PageInfo<Article> info = articleService.selectsArticle(article, page, 3);
			m.addAttribute("info", info);
		}
		
//		
//		List<Slide> selects = slideService.selects();
//		m.addAttribute("selects", selects);
		//右面 5个 刚刚发布的文章
		PageInfo<Article> lastinfo = articleService.selectsArticle(article, 1, 5);
		m.addAttribute("lastinfo", lastinfo);
		return "index/index";
	}
	@GetMapping("article")
	public String select(Model m,int id) {
		ArticleWithBLOBs selectByPrimaryKey = articleService.selectByPrimaryKey(id);
		m.addAttribute("selectByPrimaryKey", selectByPrimaryKey);
		return "/index/article";
	}
	
	@GetMapping("complain")
	public String complain(Model m, Article article, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (null!=user) {
			article.setUser(user);
			m.addAttribute("article", article);
			return "index/complain";//到举报的页面
		}
		return "redirect:/passport/login";
	}
	
	@ResponseBody
	@PostMapping("complain")
	public boolean complain(Model model,MultipartFile  file, Complain complain, HttpSession session) {
		if(null!=file &&!file.isEmpty()) {
			String path="d:/pic/";
			String filename = file.getOriginalFilename();
		   String newFileName =UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			File f = new File(path,newFileName);
			try {
				file.transferTo(f);
				complain.setPicurl(newFileName);
				
			} catch ( IOException e) {//IllegalStateException |
				e.printStackTrace();
			}
		}
		try {
			User user = (User) session.getAttribute("user");
			User selectByName = userService.selectByName(user);
			complain.setUser_id(selectByName.getId());
			//执行举报
			 complainService.inserts(complain);
				return true;
		} catch (CMSExctption e) {
			e.printStackTrace();
			
			model.addAttribute("error", e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "系统错误，联系管理员");
		}
		return false;
	
	    
	}
	
}
