package com.writershelper.model;


public class Label implements Identifiable<Long> {

    private Long id;
    private Long postId;
    private String name;
    private Status status;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
