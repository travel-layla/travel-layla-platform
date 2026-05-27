package com.travel.layla.platform.common.error;

/**
 * Base interface for error code enumeration.
 * All error codes must implement this interface.
 */
public interface ErrorCode {
    String code();
    String message();
}
