package com.writershelper.view.post;

import com.writershelper.controller.PostController;
import com.writershelper.dto.post.PostCreateDto;
import com.writershelper.dto.post.PostUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.model.Post;
import com.writershelper.utils.ConsoleReader;

import java.io.IOException;

public class PostViewImpl implements PostView {

    private final PostController postController = new PostController();

    @Override
    public void create() {
        try {
            System.out.println("Enter the post's context: ");
            String content = ConsoleReader.read();

            System.out.println("Enter the author's ID for the post: ");
            Long writerId = Long.parseLong(ConsoleReader.read());

            PostCreateDto postCreateDto = new PostCreateDto(content, writerId);
            Post savedPost = postController.create(postCreateDto);

            System.out.println("INFO: post was successfully saved with id: " + savedPost.getId());
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

            System.out.println("Post's content is " + post.getContent());
            System.out.println("Post's was created at " + post.getCreated());
            System.out.println("Post's was last updated at " + post.getUpdated());
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
            postController.update(postUpdateDto);
            System.out.println("INFO: post was successfully updated");
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

            postController.delete(postId);
            System.out.println("INFO: post was successfully deleted");
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
