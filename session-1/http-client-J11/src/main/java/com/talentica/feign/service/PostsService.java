package com.talentica.feign.service;

import java.util.List;

import com.talentica.feign.model.Post;

public interface PostsService {

    List<Post> getPosts();

    Post getPostById(Long id);
}
