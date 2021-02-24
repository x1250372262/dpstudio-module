package com.dpstudio.module.mybatis.exception;

import net.ymate.platform.commons.util.RuntimeUtils;

/**
 * @Author: 徐建鹏.
 * @create: 2021-02-23 14:12
 * @Description:
 */
public class MybatisException extends Exception{

    public MybatisException(String msg) {
        super(msg);
    }

    public MybatisException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getMessage() {
        return RuntimeUtils.unwrapThrow(super.getCause()).getMessage();
    }
}
