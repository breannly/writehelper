package com.writershelper.controller;

import com.writershelper.dto.post.PostCreateDto;
import com.writershelper.dto.post.PostUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.repository.post.PostRepository;
import com.writershelper.repository.post.PostRepositoryImpl;
import com.writershelper.repository.writer.WriterRepository;
import com.writershelper.repository.writer.WriterRepositoryImpl;
import com.writershelper.utils.CommonUtils;

import java.util.Date;

public class PostController {

    private final PostRepository postRepository = new PostRepositoryImpl();
    private final WriterRepository writerRepository = new WriterRepositoryImpl();

    public Post create(PostCreateDto request) {
        if (request.content() == null || request.content().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        Writer writer = writerRepository.get(request.writerId());
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        Post post = createPost(request.content());
        post = postRepository.save(post);

        writer.setPosts(CommonUtils.emptyIfNull(writer.getPosts()));
        writer.getPosts().add(post);
        writerRepository.save(writer);

        return post;
    }

    private Post createPost(String content) {
        Date now = new Date();

        Post post = new Post();
        post.setContent(content);
        post.setCreated(now);
        post.setUpdated(now);
        return post;
    }

    public Post get(Long id) {
        Post post = postRepository.get(id);
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }

        return post;
    }

    public Post update(PostUpdateDto request) {
        if (request.content() == null || request.content().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        Post post = postRepository.get(request.postId());
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }
        Writer writer = writerRepository.get(post.getId());
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        post.setContent(request.content());
        post.setUpdated(new Date());
        post = postRepository.save(post);

        writer.setPosts(CommonUtils.updateById(writer.getPosts(), post.getId(), post));
        writerRepository.save(writer);

        return post;
    }

    public Post delete(Long id) {
        Post post = postRepository.get(id);
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }
        Writer writer = writerRepository.get(post.getId());
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        post.setStatus(Status.DELETED);
        post = postRepository.save(post);

        writer.setPosts(CommonUtils.updateById(writer.getPosts(), post.getId(), post));
        writerRepository.save(writer);

        return post;
    }
}
