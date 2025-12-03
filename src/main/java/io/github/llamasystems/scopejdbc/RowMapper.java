package io.github.llamasystems.scopejdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/// # RowMapper
/// Functional interface to map a [ResultSet] row into a Java object.
///
/// Typically used by [JdbcClient#query(String, RowMapper, Object...)] to convert query results
/// into domain objects.
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
@FunctionalInterface
public interface RowMapper<T> {

    /// Maps the current row of the result set.
    ///
    /// @param rs the result set, positioned on the current row
    /// @return the mapped object, may be `null`
    /// @throws SQLException if a database access error occurs
    T map(ResultSet rs) throws SQLException;
}
