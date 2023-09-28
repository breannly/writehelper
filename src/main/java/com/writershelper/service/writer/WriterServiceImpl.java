package com.writershelper.service.writer;

import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.repository.writer.JdbcWriterRepositoryImpl;
import com.writershelper.repository.writer.WriterRepository;

import java.util.Optional;

public class WriterServiceImpl implements WriterService {

    WriterRepository writerRepository = new JdbcWriterRepositoryImpl();

    @Override
    public Writer get(Long id) {
        Optional<Writer> writer = writerRepository.get(id);
        return writer.filter(w -> !w.getStatus().equals(Status.DELETED)).orElse(null);
    }

    @Override
    public Writer save(Writer writer) {
        if (writer.getId() == null) {
            return writerRepository.save(writer);
        }
        return writerRepository.update(writer);
    }
}
