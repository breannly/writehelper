package com.writershelper.mapper;

import com.google.common.base.Throwables;
import com.writershelper.dto.writer.WriterCreateDto;
import com.writershelper.model.Label;
import com.writershelper.model.Post;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WriterMapper {

    public static Optional<Writer> map(ResultSet rs) {
        return mapItems(rs).stream().findFirst();
    }

    public static List<Writer> mapList(ResultSet rs) {
        return mapItems(rs);
    }

    public static Writer lazyMap(ResultSet rs) {
        return mapItem(rs);
    }

    public static Writer map(WriterCreateDto request, Status status) {
        Writer writer = new Writer();
        writer.setFirstName(request.firstName());
        writer.setLastName(request.lastName());
        writer.setStatus(status);
        return writer;
    }

    private static List<Writer> mapItems(ResultSet rs) {
        Map<Long, Writer> writersStore = new HashMap<>();
        Map<Long, Post> postsStore = new HashMap<>();

        try {
            while (rs.next()) {
                long writerId = rs.getLong("w_id");
                Writer writer = writersStore.computeIfAbsent(writerId, id -> mapItem(rs));

                long postId = rs.getLong("p_id");
                if (postId != 0L) {
                    Post post = postsStore.computeIfAbsent(postId, id -> {
                        Post newPost = PostMapper.lazyMap(rs);
                        writer.getPosts().add(newPost);
                        return newPost;
                    });

                    long labelId = rs.getLong("l_id");
                    if (labelId != 0L) {
                        Label label = LabelMapper.map(rs);
                        post.getLabels().add(label);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            throw new RuntimeException("Failed to map ResultSet to Writer", e);
        }

        return new ArrayList<>(writersStore.values());
    }

    private static Writer mapItem(ResultSet rs) {
        try {
            Writer writer = new Writer();
            writer.setId(rs.getLong("w_id"));
            writer.setFirstName(rs.getString("w_first_name"));
            writer.setLastName(rs.getString("w_second_name"));
            writer.setStatus(Status.valueOf(rs.getString("w_status")));
            writer.setPosts(new ArrayList<>());
            return writer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
