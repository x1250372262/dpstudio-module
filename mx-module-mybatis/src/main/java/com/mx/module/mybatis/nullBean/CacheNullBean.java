package com.mx.module.mybatis.nullBean;

import org.apache.ibatis.cache.Cache;

/**
 * @Author: mengxiang.
 * @create: 2021-02-23 11:29
 * @Description:
 */
public class CacheNullBean implements Cache {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object o, Object o1) {

    }

    @Override
    public Object getObject(Object o) {
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
