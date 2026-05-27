package com.travel.layla.platform.common.exception;

import com.travel.layla.platform.common.error.ErrorCode;

/**
 * Generic business exception for domain-specific validation and process errors.
 */
public class BusinessException extends BaseException {

    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
