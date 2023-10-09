package com.writershelper.service.label;

import com.writershelper.TestDataUtils;
import com.writershelper.model.Label;
import com.writershelper.model.Status;
import com.writershelper.repository.label.JdbcLabelRepositoryImpl;
import com.writershelper.repository.label.LabelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class LabelServiceImplTest {

    private final LabelRepository labelRepository = Mockito.mock(JdbcLabelRepositoryImpl.class);
    private final LabelService labelService = new LabelServiceImpl(labelRepository);

    @Test
    public void should_return_active_label() {
        Label label = TestDataUtils.createLabel();
        Mockito.when(labelRepository.get(Mockito.anyLong())).thenReturn(Optional.of(label));

        Label gotLabel = labelService.get(label.getId());

        Assert.assertNotNull(gotLabel);
    }

    @Test
    public void should_return_null_when_deleted_label() {
        Label label = TestDataUtils.createLabel();
        label.setStatus(Status.DELETED);
        Mockito.when(labelRepository.get(Mockito.anyLong())).thenReturn(Optional.of(label));

        Label gotLabel = labelService.get(label.getId());

        Assert.assertNull(gotLabel);
    }

    @Test
    public void should_save_label_without_id() {
        Label label = TestDataUtils.createLabel();
        label.setId(null);
        Mockito.when(labelRepository.save(Mockito.any())).thenReturn(label);
        Mockito.when(labelRepository.update(Mockito.any())).thenReturn(label);

        Label savedLabel = labelService.save(label);

        Assert.assertNotNull(savedLabel);
        Mockito.verify(labelRepository, Mockito.times(1)).save(label);
        Mockito.verify(labelRepository, Mockito.never()).update(label);
    }

    @Test
    public void should_update_label_with_id() {
        Label label = TestDataUtils.createLabel();
        Mockito.when(labelRepository.save(Mockito.any())).thenReturn(label);
        Mockito.when(labelRepository.update(Mockito.any())).thenReturn(label);

        Label updatedLabel = labelService.save(label);

        Assert.assertNotNull(updatedLabel);
        Mockito.verify(labelRepository, Mockito.never()).save(label);
        Mockito.verify(labelRepository, Mockito.times(1)).update(label);
    }

}