package com.writershelper.controller;

import com.writershelper.dto.writer.WriterCreateDto;
import com.writershelper.dto.writer.WriterUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.repository.writer.WriterRepository;
import com.writershelper.repository.writer.WriterRepositoryImpl;

public class WriterController {

    private final WriterRepository writerRepository = new WriterRepositoryImpl();

    public Writer create(WriterCreateDto request) {
        if (request.firstName() == null || request.firstName().isBlank()) {
            throw new ValidationException("ERROR: first name cannot be empty");
        }
        if (request.lastName() == null || request.lastName().isBlank()) {
            throw new ValidationException("ERROR: last name cannot be empty");
        }

        Writer writer = createWriter(request.firstName(), request.lastName());
        writer = writerRepository.save(writer);

        return writer;
    }

    private Writer createWriter(String firstName, String lastName) {
        Writer writer = new Writer();
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setStatus(Status.ACTIVE);
        return writer;
    }

    public Writer get(Long id) {
        Writer writer = writerRepository.get(id);
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

        Writer writer = writerRepository.get(request.writerId());
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        writer.setFirstName(request.firstName());
        writer.setLastName(request.lastName());
        writer = writerRepository.save(writer);

        return writer;
    }

    public Writer delete(Long id) {
        Writer writer = writerRepository.get(id);
        if (writer == null) {
            throw new ItemNotFoundException("ERROR: writer not found");
        }

        writer.setStatus(Status.DELETED);
        writer = writerRepository.save(writer);

        return writer;
    }
}
