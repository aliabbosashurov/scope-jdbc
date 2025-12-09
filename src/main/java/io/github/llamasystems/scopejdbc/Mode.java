package io.github.llamasystems.scopejdbc;

/// # Mode
/// Represents the transaction mode for a [ConnectionScope]
///
/// This enum is used to indicate whether a transactional scope should be
/// [#READ_ONLY] (no writes allowed) or [#READ_WRITE] (reads/writes allowed).
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
public enum Mode {
    READ_ONLY,
    READ_WRITE;

    public boolean isReadOnly() {
        return this == READ_ONLY;
    }
}
