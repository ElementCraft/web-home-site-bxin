package com.bxin.Home.constants.error;

import com.bxin.Home.tools.entity.ErrorCode;

public interface PublicError {

    ErrorCode REQUIRE_IS_NULL = ErrorCode.of(-999, "缺少必要参数");
}
