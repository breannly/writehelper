package com.writershelper.service.label;

import com.writershelper.model.Label;
import com.writershelper.model.Status;
import com.writershelper.repository.label.JdbcLabelRepositoryImpl;
import com.writershelper.repository.label.LabelRepository;

import java.util.Optional;

public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository = new JdbcLabelRepositoryImpl();

    @Override
    public Label get(Long id) {
        Optional<Label> post = labelRepository.get(id);
        return post.filter(l -> !l.getStatus().equals(Status.DELETED)).orElse(null);
    }

    @Override
    public Label save(Label label) {
        if (label.getId() == null) {
            return labelRepository.save(label);
        }
        return labelRepository.update(label);
    }
}
