package com.writershelper.model;

import java.util.Date;
import java.util.List;

public class Post {

    private Long id;
    private String content;
    private Date created;
    private Date updated;
    private List<Label> labels;
}
