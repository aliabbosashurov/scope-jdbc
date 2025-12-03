package io.github.llamasystems.scopejdbc.annotations;

import java.lang.annotation.*;

/// # ConnectionScopeManaged
///
/// Indicates that the annotated resource (usually a [java.sql.Connection])
/// is managed by a [io.github.llamasystems.scopejdbc.ConnectionScope]
/// and **should not be closed manually by the caller**.
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.CLASS)
public @interface ConnectionScopeManaged {
}
