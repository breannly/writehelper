package com.writershelper.service.label;

import com.writershelper.model.Label;
import com.writershelper.model.Status;
import com.writershelper.repository.label.LabelRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class LabelServiceImpl implements LabelService {

    private static final Predicate<Label> IS_ACTIVE = label -> label.getStatus().equals(Status.ACTIVE);

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label get(Long id) {
        Optional<Label> label = labelRepository.get(id);
        return label.filter(IS_ACTIVE).orElse(null);
    }

    @Override
    public List<Label> get(List<Long> ids) {
        List<Label> labels = labelRepository.get(ids);
        return labels.stream().filter(IS_ACTIVE).toList();
    }

    @Override
    public Label save(Label label) {
        if (label.getId() == null) {
            return labelRepository.save(label);
        }
        return labelRepository.update(label);
    }
}
