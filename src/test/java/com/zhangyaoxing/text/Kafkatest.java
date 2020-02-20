package com.zhangyaoxing.text;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.ListenableFuture;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:producer.xml")
public class Kafkatest {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Test
	public void add() {
		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("1708D", "发送");
		
	}
	
}
