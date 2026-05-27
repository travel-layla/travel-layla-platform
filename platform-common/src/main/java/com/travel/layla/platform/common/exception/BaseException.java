package com.travel.layla.platform.common.exception;

import com.travel.layla.platform.common.error.ErrorCode;

/**
 * Base runtime exception carrying a shared error code object.
 */
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    protected BaseException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
