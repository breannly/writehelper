package com.writershelper.controller;

import com.writershelper.dto.post.PostCreateDto;
import com.writershelper.dto.post.PostUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.mapper.PostMapper;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.service.post.PostService;
import com.writershelper.service.post.PostServiceImpl;

import java.util.Date;

public class PostController {

    private final PostService postService = new PostServiceImpl();

    public Post create(PostCreateDto request) {
        if (request.content() == null || request.content().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        Post post = PostMapper.map(request, new Date(), Status.ACTIVE);

        return postService.save(post);
    }

    public Post get(Long id) {
        Post post = postService.get(id);
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }

        return post;
    }

    public Post update(PostUpdateDto request) {
        if (request.content() == null || request.content().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        Post post = postService.get(request.postId());
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }

        post.setContent(request.content());
        post.setUpdated(new Date());

        return postService.save(post);
    }

    public Post delete(Long id) {
        Post post = postService.get(id);
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }

        post.setStatus(Status.DELETED);
        post = postService.save(post);

        return post;
    }
}
