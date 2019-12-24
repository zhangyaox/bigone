package com.zhangyaoxing.cms.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhangyaoxing.cms.entity.User;
import com.zhangyaoxing.cms.service.UserService;
import com.zhangyaoxing.cms.util.CMSExctption;
import com.zhangyaoxing.cms.util.CookieUtil;
import com.zhangyaoxing.util.StringUtil;

@RequestMapping("passport")
@Controller
public class PassPortController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")//去登录页面
	public String login(User user) {
		return "passport/login";
	}
	@RequestMapping("reglogin")//去注册页面
	public String reglogin(User user) {
		return "passport/reg";
	}
	
	@PostMapping("reg")
	public String reg( Model m, User user, RedirectAttributes redirectAttributes) {//RedirectAttributes可以在重定向跳钻时带参
		
		try {//注册成功的
			int insert = userService.insertSelective(user);
			if(insert>0) {
				redirectAttributes.addFlashAttribute("username", user.getUsername());//键值对传递
				return "redirect:/passport/login"; //注册成功到登录页面
			}
		} catch (CMSExctption e) {//失败
			//抛自己定义的异常 主要是为了把异常中的值传递过去
			m.addAttribute("error", e.getMessage());//这个异常是让客户看的
			e.printStackTrace();//这个异常是让程序员看的
		}
		 catch (Exception e) {//失败
			e.printStackTrace();
			m.addAttribute("error", "系统异常，请联系管理员");//这个是为了让不属于我自定义的异常 和普通异常的异常信息
		}
		m.addAttribute("user", user);
		return "passport/reg";//注册失败退回来当前页面
	}
	@PostMapping("login2")//登录页面
	public String login2(Model m, User user, HttpSession session, HttpServletResponse response) {
		try {
			User selectUser = userService.selectUser(user);
			// 如果用户勾选了 【10天免登陆】
						if (StringUtil.hasText(user.getIsRemember())) {
							CookieUtil.addCookie(response, "username", selectUser.getUsername(), 60 * 60 * 24 * 10);// 存10天
							CookieUtil.addCookie(response, "password", selectUser.getPassword(), 60 * 60 * 24 * 10);// 存10天
						}
			
			
			if(selectUser.getRole().equals("0")) {//普通用户
				//可以存储session了
				session.setAttribute("user", user);
				return "redirect:/my"; //成功到我的页面
			}else {//管理员
				//可以存储session了
				session.setAttribute("admin", user);
				return "redirect:/admin";
			}
		} catch (CMSExctption e) {
			m.addAttribute("error", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			m.addAttribute("error", "系统异常，请联系管理员");
			e.printStackTrace();
		}
		m.addAttribute("user", user);
		return "passport/login";//注册失败退回来当前页面  登录页面
	}
	@RequestMapping("loginout")
	public String loginout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		
		//让cookie删除
				Cookie[] cookies = request.getCookies();
				if (null != cookies) {
					for (Cookie cookie : cookies) {
						// System.out.println("cookie.getName():"+cookie.getName());
						if (cookie.getName().equals("username")) {
							cookie.setMaxAge(0);//cookie的存活时间。 0：删除cookie
							cookie.setPath("/");
							response.addCookie(cookie);
						}
					}
				}
		
		session.invalidate();//删除session
		return "redirect:/passport/login";
	}
}
