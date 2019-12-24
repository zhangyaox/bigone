package com.zhangyaoxing.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.entity.Complain;
import com.zhangyaoxing.cms.entity.ComplainVO;
import com.zhangyaoxing.cms.entity.User;
import com.zhangyaoxing.cms.service.ArticleService;
import com.zhangyaoxing.cms.service.ComplainService;
import com.zhangyaoxing.cms.service.UserService;

/**
 */
@RequestMapping("admin")
@Controller//这个是为了进入 admin中的  index.jsp页面 而建立
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ComplainService complainService;
	
	@RequestMapping(value = {"/","","index"})
	public String index() {
		return "admin/index";
	}
	@RequestMapping("article/selects")//   /selects
	public String articleSelects(Article article, Model m, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "3") int pageSize ) {
		PageInfo<Article> info = articleService.selectsArticle(article, pageNum, pageSize);
		m.addAttribute("info", info);
		return "/admin/article/articles";
	}
	@GetMapping("user/selects")
	public String selects(Model m, String name,@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5")int pageSize) {
		System.out.println(name);
		PageInfo<User> info = userService.selects(name,pageNum,pageSize);
		m.addAttribute("info", info);
		m.addAttribute("name", name);
		
		return "admin/user/users";
	}
	@ResponseBody
	@PostMapping("user/update")
	public boolean updateByPrimaryKeySelective(User user) {
		System.out.println(user);
		return userService.updateByPrimaryKeySelective(user)>0;
	}
	@RequestMapping("article")
	public String select(Model m,int id) {
		ArticleWithBLOBs selectByPrimaryKey = articleService.selectByPrimaryKey(id);
		m.addAttribute("selectByPrimaryKey", selectByPrimaryKey);
		return "/index/article";
	}
	
	@RequestMapping("upda")
		public String upda(Model m,Article article) {
			int upd = articleService.upd(article);
			return "/admin/article/articles";
	}
	
	@RequestMapping("article/selectComplain")
	public String selectComplain(Model m, ComplainVO complainVO,@RequestParam(defaultValue = "1")int pageNum) {
		PageInfo<Complain> info = complainService.selects(complainVO, pageNum);
		m.addAttribute("info", info);
		return "admin/article/selectComplain";
	}
	
	@ResponseBody
	@PostMapping("article/update")
	public boolean update(ArticleWithBLOBs article) {
		return articleService.updateByPrimaryKeySelective(article)> 0;
	}
}
