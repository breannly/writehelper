package com.writershelper.repository.post;

import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.utils.FileHelper;
import com.writershelper.utils.IdGenerator;
import com.writershelper.utils.JsonUtil;

import java.util.Objects;

public class PostRepositoryImpl implements PostRepository {

    private final String fileName;

    public PostRepositoryImpl() {
        this.fileName = "src/main/resources/posts.json";
    }

    public PostRepositoryImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Post save(Post post) {
        String content;
        if (Objects.isNull(post.getId())) {
            post.setId(IdGenerator.generate());
            content = JsonUtil.toJson(post) + "\n";
            FileHelper.write(fileName, content);
        } else {
            content = FileHelper.read(fileName, post.getId(), post);
            FileHelper.rewrite(fileName, content);
        }

        return post;
    }

    @Override
    public Post get(Long id) {
        String context = FileHelper.read(fileName, id);
        if (context.isBlank()) {
            return null;
        }
        return JsonUtil.fromJson(context, Post.class);
    }
}
