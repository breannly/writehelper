package com.writershelper.service.post;

import com.writershelper.model.Post;

public interface PostService {

    Post get(Long id);

    Post save(Post post);
}
