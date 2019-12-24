package com.zhangyaoxing.cms.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: CookieUtil
 * @Description: TODO
 * @author: com.zhangyaoxing.cms
 * @date: 2019年12月22日 上午10:18:05
 */
public class CookieUtil {

	// 写入 cookie
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);//Cookie存活时间
		}
		response.addCookie(cookie);
	}
	
	// 从cookie获取值
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name))
					return cookie;
			}
		}
		return null;
	}
	
}
