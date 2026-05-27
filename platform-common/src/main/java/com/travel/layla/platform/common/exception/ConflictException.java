package com.travel.layla.platform.common.exception;

import com.travel.layla.platform.common.error.CommonErrorCode;

/**
 * Exception for conflict conditions such as duplicate resources.
 */
public class ConflictException extends BaseException {

    public ConflictException() {
        super(CommonErrorCode.CONFLICT);
    }
}
