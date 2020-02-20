package com.zhangyaoxing.cms.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zhangyaoxing.util.StringUtil;

public class CollectionServiceImplTest {

	@Test
	public void test() {
		String url="http://localhost:90/my/article/selectById?id="+123;//http://localhost:90/my/article/selectById?id=383
		if(StringUtil.isHttpUrl1(url)) {
			System.out.println("ok");
		}
	}

}
