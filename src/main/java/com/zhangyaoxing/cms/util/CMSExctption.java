package com.zhangyaoxing.cms.util;
//自定义异常类
public class CMSExctption extends RuntimeException {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;//格式化
	//继承  RuntimeException  
	//  两个构造方法 一个有参  一个无参
	public CMSExctption() {
		
	}
	public CMSExctption(String message) {
		super(message);
	}
}
