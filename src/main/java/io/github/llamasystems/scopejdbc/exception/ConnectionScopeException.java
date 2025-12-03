package io.github.llamasystems.scopejdbc.exception;

import io.github.llamasystems.scopejdbc.ConnectionScope;

/// # ConnectionScopeException
/// Unchecked exception thrown when a [ConnectionScope] operation fails.
///
/// This includes failures during connection acquisition, commit, rollback,
/// or resource cleanup. It wraps [java.sql.SQLException]s where appropriate
/// and may contain suppressed exceptions from secondary failure paths.
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
public class ConnectionScopeException extends RuntimeException {

    public ConnectionScopeException(String message) {
        super(message);
    }

    public ConnectionScopeException(Throwable cause) {
        super(cause);
    }

    public ConnectionScopeException(String message, Throwable cause) {
        super(message, cause);
    }
}
