package com.mx.module.doc.converter.impl;


import com.mx.module.doc.converter.ITagConverter;
import com.mx.module.doc.tag.ObjectTag;
import com.mx.module.doc.tag.ParamObjTag;
import net.ymate.platform.log.Logs;

/**
 * @author mengxiang
 * @Date 2020.01.02.
 * @Time: 14:30.
 * @Description:
 */

public class ParamObjTagConverterImpl implements ITagConverter {

    @Override
    public ParamObjTag converter(String comment) {
        ObjectTag objectTag = null;
        try {
            objectTag = (ObjectTag) objectConverter(comment);
        } catch (Exception e) {
            Logs.get().getLogger().error("解析失败:" + e.getMessage());
        }
        if (objectTag != null) {
            return new ParamObjTag(objectTag.getTagName(), objectTag.getValues());
        }
        return null;
    }
}
