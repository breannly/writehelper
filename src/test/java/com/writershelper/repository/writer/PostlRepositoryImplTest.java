package com.writershelper.repository.writer;

import com.writershelper.TestDataUtils;
import com.writershelper.model.Label;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.repository.label.LabelRepository;
import com.writershelper.repository.label.LabelRepositoryImpl;
import com.writershelper.repository.post.PostRepository;
import com.writershelper.repository.post.PostRepositoryImpl;
import com.writershelper.utils.FileHelper;
import com.writershelper.utils.IdGenerator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

public class PostlRepositoryImplTest {
    private static final String TEST_FILE_PATH = "src/test/resources/posts.json";
    private final PostRepository postRepository = new PostRepositoryImpl(TEST_FILE_PATH);

    @Test
    public void should_write_to_file() {
        Post post = TestDataUtils.createPost();
        postRepository.save(post);

        Post foundPost = postRepository.get(post.getId());

        Assertions.assertNotNull(foundPost);
        Assertions.assertEquals(post.getId(), foundPost.getId());
        Assertions.assertEquals(Status.ACTIVE, foundPost.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

    @Test
    public void should_return_writer_by_id() {
        Post post = TestDataUtils.createPost();
        postRepository.save(post);

        Post foundPost = postRepository.get(post.getId());

        Assertions.assertNotNull(foundPost);
        Assertions.assertEquals(post.getId(), foundPost.getId());
        Assertions.assertEquals(Status.ACTIVE, foundPost.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

    @Test
    public void should_update_writer_by_id() {
        Post post = TestDataUtils.createPost();
        postRepository.save(post);
        post.setContent("another_post_content");
        postRepository.save(post);

        Post foundPost = postRepository.get(post.getId());

        Assertions.assertNotNull(foundPost);
        Assertions.assertEquals(post.getContent(), foundPost.getContent());
        Assertions.assertEquals(Status.ACTIVE, foundPost.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

}