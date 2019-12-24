package com.zhangyaoxing.cms.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhangyaoxing.cms.entity.User;
import com.zhangyaoxing.cms.mapper.UserMapper;

public class UserInterceptor extends HandlerInterceptorAdapter {//继承 HandlerInterceptorAdapter节省资源
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//用户拦截
		HttpSession session = request.getSession(false);//有session 返回session  没有返回null
		if(null==session) {//如果没有session
			return false;//没有session拦截
		}else {//有session
			User attribute = (User) session.getAttribute("user");//依靠user键取我存入的对象
			if(null!=attribute) {//如果有user的对象
				return true;//不拦截
			}
		}
		
		// 如果用户存cookie了,并和数据库的账户密码匹配则不拦截
				if(rememberAutoLogin(request,request.getSession()))
					return true;
		
		request.setAttribute("error", "不符合标准，请先登录");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);//失败跳转到  绝对路径
		return false;
	}
	
	@Resource
	private UserMapper userMapper;

	
	private boolean rememberAutoLogin(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
		//从cookie获取账户密码
		Cookie cokUsername = CookieUtil.getCookieByName(request, "username");
		Cookie cokPassword = CookieUtil.getCookieByName(request, "password");
		// 从cookie获取用户名和密码
		if (null != cokUsername && null != cokPassword && null != cokUsername.getValue()
				&& null != cokPassword.getValue()) {
			
			String username =URLDecoder.decode(cokUsername.getValue(),"UTF-8");
			String password = URLDecoder.decode(cokPassword.getValue(),"UTF-8");
			// 从数据库获取用户名和密码并和cookie的比较。若一直则返回true
			User user2 = new User();
			user2.setUsername(username);
			User user = userMapper.selectByName(user2);
			if (null != user && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				///系统里面用到了session ,需要重新存储session
				session.setAttribute("user", user);
				
				return true;
			}
		}
		return false;

	}
}
