package com._4.javasocketchat;

import java.util.Collections;
import java.util.List;
// import java.util.Properties;

// import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
// import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

@SuppressWarnings("unused")
@SpringBootTest
class JavasocketchatApplicationTests {
	// @Autowired
	// private String privateKey;
	// private RabbitAdmin rabbitAdmin;
	@Autowired
	private RedisTemplate<String, String> template;

	@Test
	void contextLoads() throws Exception {
		System.out.println("\nStart Test");

		// redis script 测试
		// String script = "return redis.call('set', 'name', 'test')";
        // RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
        
        // List<String> keys = Collections.singletonList(null);
        // String result = template.execute(redisScript, keys);
        
        // System.out.println("Result: " + result);

		// redis 测试
		template.opsForValue().set("test_key", "Hello, Redis!");
		String value = (String) template.opsForValue().get("test_key");
		System.out.println("Value from Redis: " + value);

		// RabbitMQ连接测试
		// try {
        //     Properties properties = rabbitAdmin.getQueueProperties("some.queue.name");
        //     // 或者执行其他管理操作
        //     System.out.println("RabbitMQ连接成功");
        // } catch (Exception e) {
        //     Assertions.fail("RabbitMQ连接失败: " + e.getMessage());
        // }

		System.out.println("End Test\n");
	}

}
