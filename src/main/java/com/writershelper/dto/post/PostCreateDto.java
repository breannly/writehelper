package com.writershelper.dto.post;

import java.util.List;

public record PostCreateDto(String content, Long writerId, List<Long> labels) {
}
