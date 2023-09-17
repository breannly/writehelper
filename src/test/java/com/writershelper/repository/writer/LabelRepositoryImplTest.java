package com.writershelper.repository.writer;

import com.writershelper.TestDataUtils;
import com.writershelper.model.Label;
import com.writershelper.model.Status;
import com.writershelper.repository.label.LabelRepository;
import com.writershelper.repository.label.LabelRepositoryImpl;
import com.writershelper.utils.FileHelper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LabelRepositoryImplTest {
    private static final String TEST_FILE_PATH = "src/test/resources/labels.json";
    private final LabelRepository labelRepository = new LabelRepositoryImpl(TEST_FILE_PATH);

    @Test
    public void should_write_to_file() {
        Label label = TestDataUtils.createLabel();
        labelRepository.save(label);

        Label foundLabel = labelRepository.get(label.getId());

        Assertions.assertNotNull(foundLabel);
        Assertions.assertEquals(label.getId(), foundLabel.getId());
        Assertions.assertEquals(Status.ACTIVE, foundLabel.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

    @Test
    public void should_return_writer_by_id() {
        Label label = TestDataUtils.createLabel();
        labelRepository.save(label);

        Label foundLabel = labelRepository.get(label.getId());

        Assertions.assertNotNull(foundLabel);
        Assertions.assertEquals(label.getId(), foundLabel.getId());
        Assertions.assertEquals(Status.ACTIVE, foundLabel.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }

    @Test
    public void should_update_writer_by_id() {
        Label label = TestDataUtils.createLabel();
        labelRepository.save(label);
        label.setName("another_label_name");
        labelRepository.save(label);

        Label foundLabel = labelRepository.get(label.getId());

        Assertions.assertNotNull(foundLabel);
        Assertions.assertEquals(label.getName(), foundLabel.getName());
        Assertions.assertEquals(Status.ACTIVE, foundLabel.getStatus());

        FileHelper.rewrite(TEST_FILE_PATH, "");
    }
}