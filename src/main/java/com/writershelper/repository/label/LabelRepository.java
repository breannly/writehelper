package com.writershelper.repository.label;

import com.writershelper.model.Label;
import com.writershelper.repository.GenericRepository;

import java.util.List;

public interface LabelRepository extends GenericRepository<Label, Long> {

    List<Label> get(List<Long> ids);
}
