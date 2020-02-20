package com.zhangyaoxing.text;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.zhangyaoxing.cms.entity.Article;
import com.zhangyaoxing.cms.entity.ArticleWithBLOBs;
import com.zhangyaoxing.cms.service.ArticleService;
import com.zhangyaoxing.cms.service.impl.RedisArticleService;
import com.zhangyaoxing.cms.util.FileUtilIO;
import com.zhangyaoxing.util.RandomUtil;
import com.zhangyaoxing.util.StringUtil;

public class Artitxt {
	
	@Autowired
	private RedisArticleService articleService;
	
	@Test
	public void sendKafka() throws IOException, ParseException {
		File file = new File("D:/1708D22");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
//			System.out.println(file2);//现在循环出了 所有的文件
			String readFile = FileUtilIO.readFile(file2, "utf8");//读取文件
			String name = file2.getName();//获取文件名
			String replace = name.replace(".txt", "");//把 文件名的 .txt去掉  获得标题
			ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
			articleWithBLOBs.setContent(readFile);//内容  String readFile = FileUtilIO.readFile(file2, "utf8");//读取文件
			articleWithBLOBs.setTitle(replace);// 存入 标题
			//(2)	在文本内容中截取前140个字作为摘要
			if(readFile.length()!=0) {
				if(readFile.length()>=140) {
					articleWithBLOBs.setSummary(readFile.substring(0, 140));
				}else {
					articleWithBLOBs.setSummary(readFile.substring(0, readFile.length()));
				}
				
			}
			//点击量”和“是否热门”、“频道”字段要使用随机值。（2分）
			int Hits = RandomUtil.random(0, 1);
			articleWithBLOBs.setHits(Hits);
			int ChannelId = RandomUtil.random(1, 9);
			articleWithBLOBs.setChannelId(ChannelId);
			//(4)	文章发布日期从2019年1月1日模拟到今天。（2分）
			String birthday = StringUtil.getBirthday(2019,2020);
			String year = birthday.substring(0, 4);
			String montm = birthday.substring(5, 6);
			if(year.equals("2020")&&Integer.valueOf(montm)>1) {
				birthday = StringUtil.getBirthday(2019,2020-1);
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parse = simpleDateFormat.parse(birthday);
			articleWithBLOBs.setCreated(parse);
			
//			System.out.println(articleWithBLOBs);
			/* simpleDateFormat.parse("2019") */
			Article article = new Article();
			System.out.println(articleWithBLOBs.getContent()+"|");
			System.out.println(articleWithBLOBs.getSummary());
			//把对象转换成 string   import com.alibaba.fastjson.JSON;
			String jsonString = JSON.toJSONString(articleWithBLOBs);
//			articleService.insertSelective(articleWithBLOBs);//(1)	编写RedisArticleService，把文章保存到Redis：

		}
		
//		utilStr
//		kafkaTemplate.send("1708D", "");
	}
}
