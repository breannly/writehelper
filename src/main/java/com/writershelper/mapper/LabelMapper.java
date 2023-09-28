package com.writershelper.mapper;

import com.writershelper.dto.label.LabelCreateDto;
import com.writershelper.model.Label;
import com.writershelper.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelMapper {

    public static Label map(ResultSet rs) {
        return mapItem(rs);
    }

    public static Label map(LabelCreateDto request, Status status) {
        Label label = new Label();
        label.setName(request.labelName());
        label.setPostId(request.postId());
        label.setStatus(status);
        return label;
    }

    private static Label mapItem(ResultSet rs) {
        try {
            Label label = new Label();
            label.setId(rs.getLong("l_id"));
            label.setPostId(rs.getLong("l_post_id"));
            label.setName(rs.getString("l_name"));
            label.setStatus(Status.valueOf(rs.getString("l_status")));
            return label;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
