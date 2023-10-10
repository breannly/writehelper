package com.writershelper.mapper;

import com.writershelper.dto.label.LabelCreateDto;
import com.writershelper.model.Label;
import com.writershelper.model.Status;

public class LabelMapper {

    public static Label map(LabelCreateDto request, Status status) {
        Label label = new Label();
        label.setName(request.labelName());
        label.setStatus(status);
        return label;
    }
}
