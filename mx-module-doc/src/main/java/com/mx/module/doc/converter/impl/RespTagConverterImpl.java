package com.mx.module.doc.converter.impl;


import com.mx.module.doc.converter.ITagConverter;
import com.mx.module.doc.tag.ParamTag;
import com.mx.module.doc.tag.RespTag;
import com.mx.dev.utils.BeanUtils;

/**
 * @author mengxiang
 * @Date 2020.01.02.
 * @Time: 14:30.
 * @Description: @resp的转换器
 */
public class RespTagConverterImpl implements ITagConverter {

    @Override
    public RespTag converter(String comment) {
        ParamTag paramTag = new ParamTagConverterImpl().converter(comment);
        return BeanUtils.copy(paramTag, RespTag::new);
    }
}
