package com.tal.demo.standalone;

import com.tal.demo.standalone.cache.Cache;
import com.tal.demo.standalone.model.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class StandAloneCacheApplication {

	@Value("${SPRING_REDIS_CLUSTER_NODES}")
    private String redisClusterNodes;

	public static void main(String[] args) {
		SpringApplication.run(StandAloneCacheApplication.class, args);
	}

	@Bean
	public RedisConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory(
				new RedisClusterConfiguration(List.of(redisClusterNodes.split(","))));
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	@Bean
	public Cache<String, Product> cache() {
		return new Cache<>();
	}
}
