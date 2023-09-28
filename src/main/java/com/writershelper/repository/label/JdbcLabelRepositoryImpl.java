package com.writershelper.repository.label;

import com.writershelper.JdbcConnectionPool;
import com.writershelper.mapper.LabelMapper;
import com.writershelper.model.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcLabelRepositoryImpl implements LabelRepository {
    private static final String SAVE_SQL_QUERY = "INSERT INTO labels (l_post_id, l_name, l_status) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE labels SET l_name = ? WHERE l_id = ?";
    private static final String GET_SQL_QUERY = "SELECT * FROM labels WHERE l_id = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM posts";

    @Override
    public Label save(Label label) {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, label.getPostId());
            statement.setString(2, label.getName());
            statement.setString(3, label.getStatus().name());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    label.setId(generatedId);
                }
            }
            return label;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Label update(Label label) {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL_QUERY)) {

            statement.setString(1, label.getName());
            statement.setObject(2, label.getId());
            statement.executeUpdate();

            return label;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    labels.add(LabelMapper.map(resultSet));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all posts", e);
        }
        return labels;
    }

    @Override
    public Optional<Label> get(Long id) {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_SQL_QUERY)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(LabelMapper.map(resultSet));
                }
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get posts", e);
        }
    }
}
