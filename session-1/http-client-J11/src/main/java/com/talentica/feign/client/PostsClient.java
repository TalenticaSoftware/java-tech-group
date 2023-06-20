package com.talentica.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentica.feign.config.ClientConfiguration;
import com.talentica.feign.model.Post;

@FeignClient(value = "posts", url = "https://posts.typicode.com/", configuration = ClientConfiguration.class)
public interface PostsClient {

	@RequestMapping(method = RequestMethod.GET, value = "/posts")
	List<Post> getPosts();

	@RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}", produces = "application/json")
	Post getPostById(@PathVariable("postId") Long postId);
}
