package io.github.llamasystems.scopejdbc;

import io.github.llamasystems.scopejdbc.exception.ConnectionScopeException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/// # JdbcClientImpl
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
@SuppressWarnings("ClassCanBeRecord")
final class JdbcClientImpl implements JdbcClient {

    private final Connection connection;

    JdbcClientImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> Result<T> query(String sql, RowMapper<T> mapper, Object... params) {
        Objects.requireNonNull(sql, "sql");
        Objects.requireNonNull(mapper, "mapper");
        try (PreparedStatement ps = prepareStatement(connection, sql, false, params);
             ResultSet rs = ps.executeQuery()) {

            List<T> rows = new ArrayList<>();
            while (rs.next()) {
                rows.add(mapper.map(rs));
            }
            return new QueryResult<>(rows);
        } catch (SQLException e) {
            throw new ConnectionScopeException("Query failed", e);
        }
    }

    @Override
    public Result<Integer> update(String sql, Object... params) {
        Objects.requireNonNull(sql, "sql");
        try (PreparedStatement ps = prepareStatement(connection, sql, false, params)) {
            int affected = ps.executeUpdate();
            return new UpdateResult(affected);
        } catch (SQLException e) {
            throw new ConnectionScopeException("Update failed", e);
        }
    }

    @Override
    public Result<Integer> updateReturningKey(String sql, Object... params) {
        Objects.requireNonNull(sql, "sql");
        try (PreparedStatement ps = prepareStatement(connection, sql, true, params)) {
            int affected = ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return new UpdateResult(keys.getInt(1));
                }
            }

            return new UpdateResult(affected);
        } catch (SQLException e) {
            throw new ConnectionScopeException("Update (returning key) failed", e);
        }
    }

    private static PreparedStatement prepareStatement(Connection conn, String sql, boolean returnKeys, Object... params)
            throws SQLException {

        final PreparedStatement ps = returnKeys
                ? conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                : conn.prepareStatement(sql);

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps;
    }
}
