package com.zhangyaoxing.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.mapper.ArticleMapper;
import com.zhangyaoxing.cms.mapper.ArticleRepository;
import com.zhangyaoxing.cms.service.ArticleService;

public class MsgListener implements MessageListener<String, String> {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	public void onMessage(ConsumerRecord<String, String> data) {
		//运行时会 自己启动  因为在配置文件里面配置了
		String value = data.value();
		ArticleWithBLOBs parseObject = JSON.parseObject(value, ArticleWithBLOBs.class);//string类型转换成对象类型
		int insertSelective = articleService.insertSelective(parseObject);
		if(insertSelective==1) {
			System.out.println(parseObject.getTitle()+"成功");
			redisTemplate.delete(parseObject.getId()+"");
		}
//		if(StringUtil.isNumber(value)) {
//			String substring = value.substring(0);
//			System.err.println(substring);
//			articleMapper.addOne(Integer.valueOf(substring));
//		}else {
//			ArticleWithBLOBs parseObject = JSON.parseObject(value, ArticleWithBLOBs.class);
//			System.err.println(value);//输出 结果
//			articleMapper.insert(parseObject);
//		}
		
		
//		Article parseObject = JSON.parseObject(value,Article.class);
//		articleRepository.save(parseObject);
	}

}
