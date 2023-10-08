package com.writershelper.controller;

import com.writershelper.dto.post.PostCreateDto;
import com.writershelper.dto.post.PostUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.mapper.PostMapper;
import com.writershelper.model.Label;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.service.label.LabelService;
import com.writershelper.service.post.PostService;

import java.util.Date;
import java.util.List;

public class PostController {

    private final PostService postService;
    private final LabelService labelService;

    public PostController(PostService postService,
                          LabelService labelService) {
        this.postService = postService;
        this.labelService = labelService;
    }

    public Post create(PostCreateDto request) {
        if (request.content() == null || request.content().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        List<Long> ids = request.labels();
        List<Label> labels = labelService.get(ids);
        if (labels.size() != ids.size()) {
            throw new ValidationException("ERROR: unknown labels");
        }

        Post post = PostMapper.map(request, labels, new Date(), Status.ACTIVE);

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
