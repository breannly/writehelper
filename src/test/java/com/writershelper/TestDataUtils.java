package com.writershelper;

import com.writershelper.model.Label;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;

import java.util.Date;

public class TestDataUtils {

    public static Writer createWriter() {
        Writer writer = new Writer();
        writer.setFirstName("first_name_test");
        writer.setLastName("last_name_test");
        writer.setStatus(Status.ACTIVE);
        return writer;
    }

    public static Post createPost() {
        Post post = new Post();
        post.setCreated(new Date());
        post.setUpdated(new Date());
        post.setContent("post_content_test");
        post.setStatus(Status.ACTIVE);
        return post;
    }

    public static Label createLabel() {
        Label label = new Label();
        label.setName("label_name_test");
        label.setStatus(Status.ACTIVE);
        return label;
    }
}
