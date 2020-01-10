package com.zhangyaoxing.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.service.ArticleService;

public class MsgListener implements MessageListener<String, String> {

	@Autowired
	private ArticleService articleService;
	public void onMessage(ConsumerRecord<String, String> data) {
		//运行时会 自己启动  因为在配置文件里面配置了
		String value = data.value();
		ArticleWithBLOBs parseObject = JSON.parseObject(value, ArticleWithBLOBs.class);//string类型转换成对象类型
		int insertSelective = articleService.insertSelective(parseObject);
		System.out.println(insertSelective);//输出 结果
	}

}
