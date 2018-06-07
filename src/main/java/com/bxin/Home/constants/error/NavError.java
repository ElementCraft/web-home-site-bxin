package com.bxin.Home.constants.error;

import com.bxin.Home.tools.entity.ErrorCode;

/**
 * Nav接口相关错误枚举
 *
 * @author noobug.com
 */
public interface NavError {

    ErrorCode NON_EXIST_ID = ErrorCode.of(-1, "不存在该导航栏");

    /**
     * 新增相关错误
     */
    interface Add {
        ErrorCode TITLE_IS_NULL = ErrorCode.of(1, "标题不能为空");
        ErrorCode PARENT_NO_LEVEL_1 = ErrorCode.of(2, "父级导航只能是一级导航");
        ErrorCode PARENT_NO_EXIST = ErrorCode.of(3, "指定的父级导航不存在");
        ErrorCode NEED_JUMP_NO_URL = ErrorCode.of(4, "导航需要特殊跳转必须指定跳转URL");
    }
}
