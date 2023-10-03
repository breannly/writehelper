package com.writershelper.repository.post;

import com.writershelper.JdbcConnectionPool;
import com.writershelper.mapper.PostMapper;
import com.writershelper.model.Label;
import com.writershelper.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class JdbcPostRepositoryImpl implements PostRepository {

    private static final String SAVE_SQL_QUERY = "INSERT INTO posts (p_writer_id, p_content, p_created, p_updated, p_status) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SAVE_PL_SQL_QUERY = "INSERT INTO post_labels (post_id, label_id) VALUES (?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE posts SET p_content = ?, p_updated = ? WHERE p_id = ?";
    private static final String GET_SQL_QUERY =
            "SELECT * FROM posts AS p " +
            "LEFT JOIN labels AS l ON p.p_id = l.l_post_id " +
            "WHERE p.p_id = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM posts";

    @Override
    public Post save(Post post) {
        Connection connection = null;
        try {
            connection = JdbcConnectionPool.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement1 = connection.prepareStatement(SAVE_SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statement2 = connection.prepareStatement(SAVE_PL_SQL_QUERY)) {

                statement1.setLong(1, post.getWriter().getId());
                statement1.setString(2, post.getContent());
                statement1.setObject(3, post.getCreated());
                statement1.setObject(4, post.getUpdated());
                statement1.setString(5, post.getStatus().name());
                statement1.executeUpdate();

                try (ResultSet resultSet = statement1.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        long generatedId = resultSet.getLong(1);
                        post.setId(generatedId);
                    }
                }

                for (Label label : post.getLabels()) {
                    statement2.setLong(1, post.getId());
                    statement2.setLong(2, label.getId());
                    statement2.addBatch();
                }
                statement2.executeBatch();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Failed to save post: " + e.getMessage(), e);
            }
        } catch (SQLException outerException) {
            throw new RuntimeException("Failed to establish or manage database connection: " + outerException.getMessage(), outerException);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL_QUERY)) {

            statement.setString(1, post.getContent());
            statement.setObject(2, post.getUpdated());
            statement.setObject(3, post.getId());
            statement.executeUpdate();

            return post;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Post> getAll() {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                return PostMapper.mapList(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all posts", e);
        }
    }

    @Override
    public Optional<Post> get(Long id) {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_SQL_QUERY)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return PostMapper.map(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get posts", e);
        }
    }
}
