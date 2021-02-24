package com.dpstudio.module.mybatis.nullBean;

import org.apache.ibatis.io.VFS;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-02-23 11:28
 * @Description:
 */
public class VFSNullBean extends VFS {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    protected List<String> list(URL url, String s) throws IOException {
        return null;
    }
}
