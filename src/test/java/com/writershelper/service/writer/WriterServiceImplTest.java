package com.writershelper.service.writer;

import com.writershelper.TestDataUtils;
import com.writershelper.model.Status;
import com.writershelper.model.Writer;
import com.writershelper.repository.writer.JdbcWriterRepositoryImpl;
import com.writershelper.repository.writer.WriterRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Optional;

public class WriterServiceImplTest {

    private final WriterRepository writerRepository = Mockito.mock(JdbcWriterRepositoryImpl.class);
    private final WriterService writerService = new WriterServiceImpl(writerRepository);

    @Test
    public void should_return_active_writer() {
        Writer writer = TestDataUtils.createWriter();
        Mockito.when(writerRepository.get(Mockito.anyLong())).thenReturn(Optional.of(writer));

        Writer gotWriter = writerService.get(writer.getId());

        Assertions.assertNotNull(gotWriter);
    }

    @Test
    public void should_return_null_when_deleted_writer() {
        Writer writer = TestDataUtils.createWriter();
        writer.setStatus(Status.DELETED);
        Mockito.when(writerRepository.get(Mockito.anyLong())).thenReturn(Optional.of(writer));

        Writer gotWriter = writerService.get(writer.getId());

        Assertions.assertNull(gotWriter);
    }

    @Test
    public void should_save_writer_without_id() {
        Writer writer = TestDataUtils.createWriter();
        writer.setId(null);
        Mockito.when(writerRepository.save(Mockito.any())).thenReturn(writer);
        Mockito.when(writerRepository.update(Mockito.any())).thenReturn(writer);

        Writer savedWriter = writerService.save(writer);

        Assertions.assertNotNull(savedWriter);
        Mockito.verify(writerRepository, Mockito.times(1)).save(writer);
        Mockito.verify(writerRepository, Mockito.never()).update(writer);
    }

    @Test
    public void should_update_writer_with_id() {
        Writer writer = TestDataUtils.createWriter();
        Mockito.when(writerRepository.save(Mockito.any())).thenReturn(writer);
        Mockito.when(writerRepository.update(Mockito.any())).thenReturn(writer);

        Writer updatedWriter = writerService.save(writer);

        Assertions.assertNotNull(updatedWriter);
        Mockito.verify(writerRepository, Mockito.never()).save(writer);
        Mockito.verify(writerRepository, Mockito.times(1)).update(writer);
    }


}