package com.writershelper.mapper;

import com.writershelper.dto.writer.WriterCreateDto;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;

public class WriterMapper {

    public static Writer map(WriterCreateDto request, Status status) {
        Writer writer = new Writer();
        writer.setFirstName(request.firstName());
        writer.setLastName(request.lastName());
        writer.setStatus(status);
        return writer;
    }
}
