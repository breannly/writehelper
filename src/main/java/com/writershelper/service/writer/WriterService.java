package com.writershelper.service.writer;

import com.writershelper.model.Writer;

public interface WriterService {

    Writer get(Long id);

    Writer save(Writer writer);
}
