package com.travel.layla.platform.common.exception;

import com.travel.layla.platform.common.error.CommonErrorCode;

/**
 * Exception for invalid data or failed validation rules.
 */
public class ValidationException extends BaseException {

    public ValidationException() {
        super(CommonErrorCode.VALIDATION_ERROR);
    }
}
