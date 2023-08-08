package com.writershelper.repository.writer;

import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.utils.FileHelper;
import com.writershelper.utils.IdGenerator;
import com.writershelper.utils.JsonUtil;

import java.util.Objects;

public class WriterRepositoryImpl implements WriterRepository {

    private final String fileName;

    public WriterRepositoryImpl() {
        this.fileName = "src/main/resources/writers.json";
    }

    public WriterRepositoryImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Writer save(Writer writer) {
        String content;
        if (Objects.isNull(writer.getId())) {
            writer.setId(IdGenerator.generate());
            content = JsonUtil.toJson(writer) + "\n";
            FileHelper.write(fileName, content);
        } else {
            content = FileHelper.read(fileName, writer.getId(), writer);
            FileHelper.rewrite(fileName, content);
        }

        return writer;
    }

    @Override
    public Writer get(Long id) {
        String context = FileHelper.read(fileName, id);
        if (context.isBlank()) {
            return null;
        }
        return JsonUtil.fromJson(context, Writer.class);
    }
}
