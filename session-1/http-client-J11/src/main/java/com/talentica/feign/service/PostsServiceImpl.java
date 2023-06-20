package com.talentica.feign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentica.feign.client.PostsClient;
import com.talentica.feign.model.Post;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsClient postsClient;

    @Override
    public List<Post> getPosts() {
        return postsClient.getPosts();
    }

    @Override
    public Post getPostById(Long id) {
        return postsClient.getPostById(id);
    }
}
