package com.zhangyaoxing.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.mapper.ArticleMapper;
import com.zhangyaoxing.util.StringUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class Newadd {
	
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Test
	public void text() throws IOException {
		ArrayList<String> readtow = StringUtil.readtow(this.getClass().getResourceAsStream("/data3.txt"));
		ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
		for (String string : readtow) {
			String[] split = string.split("\\|");
			if(split.length>1) {
				articleWithBLOBs.setHits(0);
				articleWithBLOBs.setCreated(new Date());
				articleWithBLOBs.setChannelId(1);
				articleWithBLOBs.setContent(split[1]);
				articleWithBLOBs.setSummary(split[0]);
			}
			String jsonString = JSON.toJSONString(articleWithBLOBs);
			kafkaTemplate.send("1708D", jsonString);
		}
		
		
		
	}
}
