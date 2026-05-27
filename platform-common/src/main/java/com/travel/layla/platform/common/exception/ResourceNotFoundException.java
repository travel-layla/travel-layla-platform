package com.travel.layla.platform.common.exception;

import com.travel.layla.platform.common.error.CommonErrorCode;

/**
 * Exception when a requested resource cannot be found.
 */
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
        super(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
}
