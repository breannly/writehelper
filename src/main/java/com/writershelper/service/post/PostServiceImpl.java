package com.writershelper.service.post;

import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.repository.post.PostRepository;

import java.util.Optional;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post get(Long id) {
        Optional<Post> post = postRepository.get(id);
        return post.filter(w -> !w.getStatus().equals(Status.DELETED)).orElse(null);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            return postRepository.save(post);
        }
        return postRepository.update(post);
    }
}
