package com.writershelper.controller;

import com.writershelper.dto.writer.WriterCreateDto;
import com.writershelper.dto.writer.WriterUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.mapper.WriterMapper;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.service.writer.WriterService;
import com.writershelper.service.writer.WriterServiceImpl;

public class WriterController {

    private final WriterService writerService = new WriterServiceImpl();

    public Writer create(WriterCreateDto request) {
        if (request.firstName() == null || request.firstName().isBlank()) {
            throw new ValidationException("ERROR: first name cannot be empty");
        }
        if (request.lastName() == null || request.lastName().isBlank()) {
            throw new ValidationException("ERROR: last name cannot be empty");
        }

        Writer writer = WriterMapper.map(request, Status.ACTIVE);

        return writerService.save(writer);
    }

    public Writer get(Long id) {
        Writer writer = writerService.get(id);
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        return writer;
    }

    public Writer update(WriterUpdateDto request) {
        if (request.firstName() == null || request.firstName().isBlank()) {
            throw new ValidationException("ERROR: first name cannot be empty");
        }
        if (request.lastName() == null || request.lastName().isBlank()) {
            throw new ValidationException("ERROR: last name cannot be empty");
        }

        Writer writer = writerService.get(request.writerId());
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        writer.setFirstName(request.firstName());
        writer.setLastName(request.lastName());

        return writerService.save(writer);
    }

    public Writer delete(Long id) {
        Writer writer = writerService.get(id);
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        writer.setStatus(Status.DELETED);

        return writerService.save(writer);
    }
}
