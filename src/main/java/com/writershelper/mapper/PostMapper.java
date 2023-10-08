package com.writershelper.mapper;

import com.writershelper.dto.post.PostCreateDto;
import com.writershelper.model.Label;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;

import java.util.*;

public class PostMapper {

    public static Post map(PostCreateDto request,
                           List<Label> labels,
                           Date date,
                           Status status) {
        Writer writer = new Writer();
        writer.setId(request.writerId());

        Post post = new Post();
        post.setWriter(writer);
        post.setContent(request.content());
        post.setLabels(labels);
        post.setCreated(date);
        post.setUpdated(date);
        post.setStatus(status);
        return post;
    }
}
