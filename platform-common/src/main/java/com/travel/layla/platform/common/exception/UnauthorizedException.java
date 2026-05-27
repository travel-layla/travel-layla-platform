package com.travel.layla.platform.common.exception;

import com.travel.layla.platform.common.error.CommonErrorCode;

/**
 * Exception thrown when authentication is required or invalid.
 */
public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        super(CommonErrorCode.UNAUTHORIZED);
    }
}
