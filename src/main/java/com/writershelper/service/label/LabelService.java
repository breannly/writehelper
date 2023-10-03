package com.writershelper.service.label;

import com.writershelper.model.Label;

import java.util.List;

public interface LabelService {

    Label get(Long id);

    List<Label> get(List<Long> ids);

    Label save(Label post);
}
