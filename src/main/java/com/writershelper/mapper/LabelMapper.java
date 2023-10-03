package com.writershelper.mapper;

import com.google.common.base.Throwables;
import com.writershelper.dto.label.LabelCreateDto;
import com.writershelper.model.Label;
import com.writershelper.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabelMapper {

    public static Label map(ResultSet rs) {
        return mapItem(rs);
    }

    public static List<Label> mapList(ResultSet rs) {
        return mapItems(rs);
    }

    public static Label map(LabelCreateDto request, Status status) {
        Label label = new Label();
        label.setName(request.labelName());
        label.setStatus(status);
        return label;
    }

    private static List<Label> mapItems(ResultSet rs) {
        try {
            List<Label> labels = new ArrayList<>();
            while (rs.next()) {
                Label label = mapItem(rs);
                labels.add(label);
            }
            return labels;
        } catch (SQLException e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            throw new RuntimeException("Failed to map ResultSet to Writer", e);
        }
    }

    private static Label mapItem(ResultSet rs) {
        try {
            Label label = new Label();
            label.setId(rs.getLong("l_id"));
            label.setName(rs.getString("l_name"));
            label.setStatus(Status.valueOf(rs.getString("l_status")));
            return label;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
