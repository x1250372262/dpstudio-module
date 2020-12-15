package com.dpstudio.module.doc.converter.impl;


import com.dpstudio.module.doc.converter.ITagConverter;
import com.dpstudio.module.doc.tag.ObjectTag;
import com.dpstudio.module.doc.tag.RespObjTag;
import net.ymate.platform.log.Logs;

/**
 * @author mengxiang
 * @Date 2020.01.02.
 * @Time: 14:30.
 * @Description:
 */

public class RespObjTagConverterImpl implements ITagConverter {

    @Override
    public RespObjTag converter(String comment) {

        ObjectTag objectTag = null;
        try {
            objectTag = (ObjectTag) objectConverter(comment);
        } catch (Exception e) {
            Logs.get().getLogger().error("解析失败:" + e.getMessage());
        }
        if (objectTag != null) {
            return new RespObjTag(objectTag.getTagName(), objectTag.getValues());
        }
        return null;
    }
}
