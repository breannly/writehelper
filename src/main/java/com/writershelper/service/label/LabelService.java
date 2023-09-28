package com.writershelper.service.label;

import com.writershelper.model.Label;

public interface LabelService {

    Label get(Long id);

    Label save(Label post);
}
