package com.writershelper.controller;

import com.writershelper.dto.label.LabelCreateDto;
import com.writershelper.dto.label.LabelUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.mapper.LabelMapper;
import com.writershelper.model.Label;
import com.writershelper.model.Status;
import com.writershelper.service.label.LabelService;
import com.writershelper.service.label.LabelServiceImpl;

public class LabelController {

    private final LabelService labelService = new LabelServiceImpl();

    public Label create(LabelCreateDto request) {
        if (request.labelName() == null || request.labelName().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        Label label = LabelMapper.map(request, Status.ACTIVE);
        label = labelService.save(label);

        return label;
    }

    public Label get(Long id) {
        Label label = labelService.get(id);
        if (label == null) {
            throw new ItemNotFoundException("ERROR: label not found");
        }

        return label;
    }

    public Label update(LabelUpdateDto request) {
        if (request.labelName() == null || request.labelName().isBlank()) {
            throw new ValidationException("ERROR: Content cannot be empty");
        }

        Label label = labelService.get(request.labelId());
        if (label == null) {
            throw new ItemNotFoundException("ERROR: label not found");
        }

        label.setName(request.labelName());

        return labelService.save(label);
    }

    public Label delete(Long id) {
        Label label = labelService.get(id);
        if (label == null) {
            throw new ItemNotFoundException("ERROR: label not found");
        }

        label.setStatus(Status.DELETED);

        return labelService.save(label);
    }
}
