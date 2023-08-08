package com.writershelper.repository.writer;

import com.writershelper.TestDataUtils;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.utils.FileHelper;
import com.writershelper.utils.IdGenerator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class WriterRepositoryImplTest {
    private static final String TEST_FILE_PATH = "src/test/resources/writers.json";
    private final WriterRepository writerRepository = new WriterRepositoryImpl(TEST_FILE_PATH);

    @Test
    public void should_write_to_file() {
        Writer writer = TestDataUtils.createWriter();
        writerRepository.save(writer);

        Writer foundWriter = writerRepository.get(writer.getId());

        Assertions.assertNotNull(foundWriter);
        Assertions.assertEquals(writer.getId(), foundWriter.getId());
        Assertions.assertEquals(Status.ACTIVE, foundWriter.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

    @Test
    public void should_return_writer_by_id() {
        Writer writer = TestDataUtils.createWriter();
        writerRepository.save(writer);

        Writer foundWriter = writerRepository.get(writer.getId());

        Assertions.assertNotNull(foundWriter);
        Assertions.assertEquals(writer.getId(), foundWriter.getId());
        Assertions.assertEquals(Status.ACTIVE, foundWriter.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

    @Test
    public void should_update_writer_by_id() {
        Writer writer = TestDataUtils.createWriter();
        writerRepository.save(writer);
        writer.setFirstName("another_first_name");
        writer.setLastName("another_last_name");
        writerRepository.save(writer);

        Writer foundWriter = writerRepository.get(writer.getId());
        Assertions.assertNotNull(foundWriter);
        Assertions.assertEquals(writer.getFirstName(), foundWriter.getFirstName());
        Assertions.assertEquals(writer.getLastName(), foundWriter.getLastName());
        Assertions.assertEquals(Status.ACTIVE, foundWriter.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

}