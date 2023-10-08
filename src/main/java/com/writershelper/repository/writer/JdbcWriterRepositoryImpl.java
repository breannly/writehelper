package com.writershelper.repository.writer;

import com.writershelper.JdbcConnectionPool;
import com.writershelper.mapper.WriterMapper;
import com.writershelper.model.Writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    private static final String SAVE_SQL_QUERY = "INSERT INTO writers (w_first_name, w_second_name, w_status)" +
            " VALUES (?, ?, ?)";
    private static final String GET_SQL_QUERY =
            "SELECT * FROM writers AS w " +
            "LEFT JOIN posts AS p ON w.w_id = p.p_writer_id " +
            "LEFT JOIN labels AS l ON p.p_id = l.l_post_id " +
            "WHERE w_id = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM writers";
    private static final String UPDATE_SQL_QUERY = "UPDATE writers" +
            " SET w_first_name = ?, w_second_name = ?, w_status = ? WHERE w_id = ?";

    @Override
    public Writer save(Writer writer) {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setString(3, writer.getStatus().name());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    writer.setId(generatedId);
                }
            }
            return writer;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save writer", e);
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL_QUERY)) {

            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setString(3, writer.getStatus().name());
            statement.setLong(4, writer.getId());
            statement.executeUpdate();

            return writer;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update writer", e);
        }
    }

    @Override
    public List<Writer> getAll() {
        try (Connection connection = JdbcConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                return WriterMapper.mapList(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all writers", e);
        }
    }

    @Override
    public Optional<Writer> get(Long id) {
        try (Connection connection = JdbcConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_SQL_QUERY)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return WriterMapper.map(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get writer", e);
        }
    }
}
