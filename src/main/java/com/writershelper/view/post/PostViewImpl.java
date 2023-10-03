package com.writershelper.view.post;

import com.writershelper.controller.PostController;
import com.writershelper.dto.post.PostCreateDto;
import com.writershelper.dto.post.PostUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.model.Post;
import com.writershelper.utils.ConsoleReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PostViewImpl implements PostView {

    private final PostController postController;

    public PostViewImpl(PostController postController) {
        this.postController = postController;
    }

    @Override
    public void create() {
        try {
            System.out.println("Enter the post's context: ");
            String content = ConsoleReader.read();

            System.out.println("Enter the author's ID for the post: ");
            Long writerId = Long.parseLong(ConsoleReader.read());

            System.out.println("Enter the label's ID separated by a comma:");
            List<Long> labels = Arrays.stream(ConsoleReader.read().split(",")).map(Long::parseLong).toList();

            PostCreateDto postCreateDto = new PostCreateDto(content, writerId, labels);
            Post savedPost = postController.create(postCreateDto);

            System.out.println("INFO: post was successfully saved with id: " + savedPost.getId());
            System.out.println(savedPost);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ValidationException | ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void get() {
        try {
            System.out.println("Enter the post's id: ");
            Long postId = Long.parseLong(ConsoleReader.read());

            Post post = postController.get(postId);

            System.out.println(post);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            System.out.println("Enter the post's id: ");
            Long postId = Long.parseLong(ConsoleReader.read());

            System.out.println("Enter the new post's content: ");
            String content = ConsoleReader.read();

            PostUpdateDto postUpdateDto = new PostUpdateDto(postId, content);
            Post updatedPost = postController.update(postUpdateDto);

            System.out.println("INFO: post was successfully updated");
            System.out.println(updatedPost);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ValidationException | ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            System.out.println("Enter the post's id: ");
            Long postId = Long.parseLong(ConsoleReader.read());

            Post deletedPost = postController.delete(postId);

            System.out.println("INFO: post was successfully deleted");
            System.out.println(deletedPost);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
