package com.writershelper;

import com.writershelper.controller.LabelController;
import com.writershelper.controller.PostController;
import com.writershelper.controller.WriterController;
import com.writershelper.repository.label.JdbcLabelRepositoryImpl;
import com.writershelper.repository.label.LabelRepository;
import com.writershelper.repository.post.JdbcPostRepositoryImpl;
import com.writershelper.repository.post.PostRepository;
import com.writershelper.repository.writer.JdbcWriterRepositoryImpl;
import com.writershelper.repository.writer.WriterRepository;
import com.writershelper.service.label.LabelService;
import com.writershelper.service.label.LabelServiceImpl;
import com.writershelper.service.post.PostService;
import com.writershelper.service.post.PostServiceImpl;
import com.writershelper.service.writer.WriterService;
import com.writershelper.service.writer.WriterServiceImpl;
import com.writershelper.view.label.LabelView;
import com.writershelper.view.label.LabelViewImpl;
import com.writershelper.view.post.PostView;
import com.writershelper.view.post.PostViewImpl;
import com.writershelper.view.writer.WriterView;
import com.writershelper.view.writer.WriterViewImpl;

public class ApplicationManager {

    private static WriterView writerView;
    private static PostView postView;
    private static LabelView labelView;
    private static WriterController writerController;
    private static PostController postController;
    private static LabelController labelController;
    private static WriterService writerService;
    private static PostService postService;
    private static LabelService labelService;
    private static WriterRepository writerRepository;
    private static PostRepository postRepository;
    private static LabelRepository labelRepository;

    public static PostView getPostView() {
        if (postView == null) {
            PostController controller = getPostController();
            postView = new PostViewImpl(controller);
            return postView;
        }
        return postView;
    }

    public static PostController getPostController() {
        if (postController == null) {
            PostService service = getPostService();
            postController = new PostController(service);
            return postController;
        }
        return postController;
    }

    public static PostRepository getPostRepository() {
        if (postRepository == null) {
            postRepository = new JdbcPostRepositoryImpl();
            return  postRepository;
        }
        return postRepository;
    }

    public static PostService getPostService() {
        if (postService == null) {
            PostRepository repository = getPostRepository();
            postService = new PostServiceImpl(repository);
            return postService;
        }
        return postService;
    }

    public static WriterView getWriterView() {
        if (writerView == null) {
            WriterController controller = getWriterController();
            writerView = new WriterViewImpl(controller);
            return writerView;
        }
        return writerView;
    }

    public static WriterController getWriterController() {
        if (writerController == null) {
            WriterService service = getWriterService();
            writerController = new WriterController(service);
            return writerController;
        }
        return writerController;
    }

    public static WriterRepository getWriterRepository() {
        if (writerRepository == null) {
            writerRepository = new JdbcWriterRepositoryImpl();
            return writerRepository;
        }
        return writerRepository;
    }

    public static WriterService getWriterService() {
        if (writerService == null) {
            WriterRepository repository = getWriterRepository();
            writerService = new WriterServiceImpl(repository);
            return writerService;
        }
        return writerService;
    }

    public static LabelView getLabelView() {
        if (labelView == null) {
            LabelController controller = getLabelController();
            labelView = new LabelViewImpl(controller);
            return labelView;
        }
        return labelView;
    }

    public static LabelController getLabelController() {
        if (labelController == null) {
            LabelService service = getLabelService();
            labelController = new LabelController(service);
            return labelController;
        }
        return labelController;
    }

    public static LabelRepository getLabelRepository() {
        if (labelRepository == null) {
            labelRepository = new JdbcLabelRepositoryImpl();
            return labelRepository;
        }
        return labelRepository;
    }

    public static LabelService getLabelService() {
        if (labelService == null) {
            LabelRepository repository = getLabelRepository();
            labelService = new LabelServiceImpl(repository);
            return labelService;
        }
        return labelService;
    }
}
