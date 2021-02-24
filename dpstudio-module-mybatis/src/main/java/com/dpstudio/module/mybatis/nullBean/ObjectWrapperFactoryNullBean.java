package com.dpstudio.module.mybatis.nullBean;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * @Author: mengxiang.
 * @create: 2021-02-23 11:30
 * @Description:
 */
public class ObjectWrapperFactoryNullBean implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object o) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object o) {
        return null;
    }
}
