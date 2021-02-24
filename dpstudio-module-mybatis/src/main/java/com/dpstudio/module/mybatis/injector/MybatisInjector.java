package com.dpstudio.module.mybatis.injector;

import com.dpstudio.module.mybatis.Mybatis;
import net.ymate.platform.core.beans.IBeanFactory;
import net.ymate.platform.core.beans.IBeanInjector;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.beans.annotation.Injector;
import org.apache.ibatis.session.SqlSessionManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Injector(Inject.class)
public class MybatisInjector implements IBeanInjector {

    @Override
    public Object inject(IBeanFactory beanFactory, Annotation annotation, Class<?> targetClass, Field field, Object originInject) {
        if (originInject == null) {
            SqlSessionManager sqlSessionManager = Mybatis.get().sqlSessionManager();
            if (sqlSessionManager == null) {
                return originInject;
            }
            return sqlSessionManager.getMapper(field.getType());
        }
        return originInject;
    }
}
