package com.travel.layla.platform.common.exception;

import com.travel.layla.platform.common.error.CommonErrorCode;

/**
 * Exception thrown when an action is forbidden for the current user.
 */
public class ForbiddenException extends BaseException {

    public ForbiddenException() {
        super(CommonErrorCode.FORBIDDEN);
    }
}
