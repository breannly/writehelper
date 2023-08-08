package com.writershelper.repository.label;

import com.writershelper.model.Label;
import com.writershelper.model.Status;
import com.writershelper.utils.FileHelper;
import com.writershelper.utils.IdGenerator;
import com.writershelper.utils.JsonUtil;

import java.util.Objects;

public class LabelRepositoryImpl implements LabelRepository {

    private final String fileName;

    public LabelRepositoryImpl() {
        this.fileName = "src/main/resources/labels.json";
    }

    public LabelRepositoryImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Label save(Label label) {
        String content;
        if (Objects.isNull(label.getId())) {
            label.setId(IdGenerator.generate());
            content = JsonUtil.toJson(label) + "\n";
            FileHelper.write(fileName, content);
        } else {
            content = FileHelper.read(fileName, label.getId(), label);
            FileHelper.rewrite(fileName, content);
        }

        return label;
    }

    @Override
    public Label get(Long id) {
        String content = FileHelper.read(fileName, id);
        if (content.isBlank()) {
            return null;
        }
        return JsonUtil.fromJson(content, Label.class);
    }
}
