package com.mx.module.doc.converter.impl;


import com.mx.module.doc.converter.ITagConverter;
import com.mx.module.doc.tag.DocTag;
import com.mx.module.doc.utils.CommentUtils;

import java.util.Objects;

/**
 * @author mengxiang
 * @Date 2020.01.02.
 * @Time: 14:30.
 * @Description: JavaParser包的默认注释解析转换器
 */
public class DefaultTagConverterImpl implements ITagConverter {

    @Override
    public DocTag converter(String comment) {
        String tagType = CommentUtils.getTagType(comment);
        String coment = comment.substring(Objects.requireNonNull(tagType).length()).trim();
        return new DocTag(tagType, coment);
    }
}
