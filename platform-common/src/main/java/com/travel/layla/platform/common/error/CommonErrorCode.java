package com.travel.layla.platform.common.error;

import com.travel.layla.platform.common.constant.CommonErrorCodes;
import com.travel.layla.platform.common.constant.CommonErrorMessages;

/**
 * Common platform error codes and messages for shared exception handling.
 */
public enum CommonErrorCode implements ErrorCode {
    INTERNAL_SERVER_ERROR(
            CommonErrorCodes.INTERNAL_SERVER_ERROR,
            CommonErrorMessages.INTERNAL_SERVER_ERROR
    ),

    VALIDATION_ERROR(
            CommonErrorCodes.VALIDATION_ERROR,
            CommonErrorMessages.VALIDATION_ERROR
    ),

    RESOURCE_NOT_FOUND(
            CommonErrorCodes.RESOURCE_NOT_FOUND,
            CommonErrorMessages.RESOURCE_NOT_FOUND
    ),

    UNAUTHORIZED(
            CommonErrorCodes.UNAUTHORIZED,
            CommonErrorMessages.UNAUTHORIZED
            ),

    FORBIDDEN(
            CommonErrorCodes.FORBIDDEN,
            CommonErrorMessages.FORBIDDEN
            ),

    CONFLICT(
            CommonErrorCodes.CONFLICT,
            CommonErrorMessages.CONFLICT
            );

    private final String code;
    private final String message;

    CommonErrorCode(
            String code,
            String message
    ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
