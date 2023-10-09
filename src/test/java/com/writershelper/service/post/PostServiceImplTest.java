package com.writershelper.service.post;

import com.writershelper.TestDataUtils;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.repository.post.JdbcPostRepositoryImpl;
import com.writershelper.repository.post.PostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class PostServiceImplTest {

    private final PostRepository postRepository = Mockito.mock(JdbcPostRepositoryImpl.class);
    private final PostService postService = new PostServiceImpl(postRepository);

    @Test
    public void should_return_active_post() {
        Post post = TestDataUtils.createPost();
        Mockito.when(postRepository.get(Mockito.anyLong())).thenReturn(Optional.of(post));

        Post gotPost = postService.get(post.getId());

        Assert.assertNotNull(gotPost);
    }

    @Test
    public void should_return_null_when_deleted_post() {
        Post post = TestDataUtils.createPost();
        post.setStatus(Status.DELETED);
        Mockito.when(postRepository.get(Mockito.anyLong())).thenReturn(Optional.of(post));

        Post gotPost = postService.get(post.getId());

        Assert.assertNull(gotPost);
    }

    @Test
    public void should_save_post_without_id() {
        Post post = TestDataUtils.createPost();
        post.setId(null);
        Mockito.when(postRepository.save(Mockito.any())).thenReturn(post);
        Mockito.when(postRepository.update(Mockito.any())).thenReturn(post);

        Post savedPost = postService.save(post);

        Assert.assertNotNull(savedPost);
        Mockito.verify(postRepository, Mockito.times(1)).save(post);
        Mockito.verify(postRepository, Mockito.never()).update(post);
    }

    @Test
    public void should_update_post_with_id() {
        Post post = TestDataUtils.createPost();
        Mockito.when(postRepository.save(Mockito.any())).thenReturn(post);
        Mockito.when(postRepository.update(Mockito.any())).thenReturn(post);

        Post updatedPost = postService.save(post);

        Assert.assertNotNull(updatedPost);
        Mockito.verify(postRepository, Mockito.never()).save(post);
        Mockito.verify(postRepository, Mockito.times(1)).update(post);
    }

}