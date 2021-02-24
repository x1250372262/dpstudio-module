package com.dpstudio.module.mybatis.nullBean;

import org.apache.ibatis.reflection.factory.ObjectFactory;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-02-23 11:29
 * @Description:
 */
public class ObjectFactoryNullBean implements ObjectFactory {
    @Override
    public <T> T create(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T create(Class<T> aClass, List<Class<?>> list, List<Object> list1) {
        return null;
    }

    @Override
    public <T> boolean isCollection(Class<T> aClass) {
        return false;
    }
}
