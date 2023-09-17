package com.writershelper.controller;

import com.writershelper.dto.label.LabelCreateDto;
import com.writershelper.dto.label.LabelUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.model.Label;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.repository.label.LabelRepository;
import com.writershelper.repository.label.LabelRepositoryImpl;
import com.writershelper.repository.post.PostRepository;
import com.writershelper.repository.post.PostRepositoryImpl;
import com.writershelper.repository.writer.WriterRepository;
import com.writershelper.repository.writer.WriterRepositoryImpl;
import com.writershelper.utils.CommonUtils;

import java.util.Date;

public class LabelController {

    private final LabelRepository labelRepository = new LabelRepositoryImpl();
    private final PostRepository postRepository = new PostRepositoryImpl();
    private final WriterRepository writerRepository = new WriterRepositoryImpl();

    public Label create(LabelCreateDto request) {
        if (request.labelName() == null || request.labelName().isBlank()) {
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

        Label label = createLabel(request.labelName());
        label = labelRepository.save(label);

        post.setLabels(CommonUtils.emptyIfNull(post.getLabels()));
        post.getLabels().add(label);
        postRepository.save(post);

        writer.setPosts(CommonUtils.updateById(writer.getPosts(), post.getId(), post));
        writerRepository.save(writer);

        return label;
    }

    private Label createLabel(String name) {
        Label label = new Label();
        label.setName(name);
        label.setStatus(Status.ACTIVE);
        return label;
    }

    public Label get(Long id) {
        Label label = labelRepository.get(id);
        if (label == null) {
            throw new ItemNotFoundException("ERROR: label not found");
        }

        return label;
    }

    public Label update(LabelUpdateDto request) {
        if (request.labelName() == null || request.labelName().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        Label label = labelRepository.get(request.labelId());
        if (label == null) {
            throw new ItemNotFoundException("ERROR: label not found");
        }
        Post post = postRepository.get(label.getId());
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }
        Writer writer = writerRepository.get(post.getId());
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        label.setName(request.labelName());
        Label savedLabel = labelRepository.save(label);

        post.setLabels(CommonUtils.updateById(post.getLabels(), label.getId(), savedLabel));
        post.setUpdated(new Date());
        postRepository.save(post);

        writer.setPosts(CommonUtils.updateById(writer.getPosts(), post.getId(), post));
        writerRepository.save(writer);

        return labelRepository.save(label);
    }

    public Label delete(Long id) {
        Label label = labelRepository.get(id);
        if (label == null) {
            throw new ItemNotFoundException("ERROR: label not found");
        }
        Post post = postRepository.get(label.getId());
        if (post == null) {
            throw new ItemNotFoundException("ERROR: post not found");
        }
        Writer writer = writerRepository.get(post.getId());
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        label.setStatus(Status.DELETED);
        Label savedLabel = labelRepository.save(label);

        post.setLabels(CommonUtils.updateById(post.getLabels(), label.getId(), savedLabel));
        post.setUpdated(new Date());
        postRepository.save(post);

        writer.setPosts(CommonUtils.updateById(writer.getPosts(), post.getId(), post));
        writerRepository.save(writer);

        return label;
    }
}
