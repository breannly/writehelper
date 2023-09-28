package com.writershelper.mapper;

import com.google.common.base.Throwables;
import com.writershelper.dto.post.PostCreateDto;
import com.writershelper.model.Label;
import com.writershelper.model.Post;
import com.writershelper.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PostMapper {

    public static Optional<Post> map(ResultSet rs) {
        return mapItems(rs).stream().findFirst();
    }

    public static List<Post> mapList(ResultSet rs) {
        return mapItems(rs);
    }

    public static Post lazyMap(ResultSet rs) {
        return mapItem(rs);
    }

    public static Post map(PostCreateDto request, Date date, Status status) {
        Post post = new Post();
        post.setWriterId(request.writerId());
        post.setContent(request.content());
        post.setCreated(date);
        post.setUpdated(date);
        post.setStatus(status);
        return post;
    }

    private static List<Post> mapItems(ResultSet rs) {
        Map<Long, Post> postsStore = new HashMap<>();

        try {
            while (rs.next()) {
                long postId = rs.getLong("p_id");
                Post post = postsStore.computeIfAbsent(postId, id -> mapItem(rs));

                long labelId = rs.getLong("l_id");
                if (labelId != 0L) {
                    Label label = LabelMapper.map(rs);
                    post.getLabels().add(label);
                }

            }
        } catch (SQLException e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            throw new RuntimeException("Failed to map ResultSet to Writer", e);
        }

        return new ArrayList<>(postsStore.values());
    }

    private static Post mapItem(ResultSet rs) {
        try {
            Post post = new Post();
            post.setId(rs.getLong("p_id"));
            post.setWriterId(rs.getLong("p_writer_id"));
            post.setContent(rs.getString("p_content"));
            post.setCreated(rs.getDate("p_created"));
            post.setUpdated(rs.getDate("p_updated"));
            post.setStatus(Status.valueOf(rs.getString("p_status")));
            post.setLabels(new ArrayList<>());
            return post;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
