package com.dpstudio.module.doc.sdk;

import com.dpstudio.module.doc.Doc;
import com.dpstudio.module.doc.IDoc;
import com.dpstudio.module.doc.bean.ApiResult;
import com.dpstudio.module.doc.bean.SdkInfo;
import com.dpstudio.module.doc.exception.DocException;
import com.dpstudio.module.doc.format.VelocityTemplater;
import net.ymate.platform.commons.util.RuntimeUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xujianpeng.
 * @Date: 2020/7/9.
 * @Time: 下午3:41.
 * @Description:
 */
public class AllSdkImpl implements ISdk {
    private static final VelocityTemplater SDK_VM = new VelocityTemplater("com/dpstudio/module/doc/sdk/wxmini/sdk.vm");

    @Override
    public File create(ApiResult apiResult) throws Exception {

        List<SdkInfo> sdkInfoList = readyData(apiResult);
        String sdkStr;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("sdkInfos", sdkInfoList);
            map.put("host", Doc.get().getConfig().host());
            sdkStr = SDK_VM.parse(map);
        } catch (Exception e) {
            throw new DocException("sdk生成失败", e);
        }

        if (StringUtils.isBlank(sdkStr)) {
            return null;
        }
        File tempFileDir = new File(RuntimeUtils.replaceEnvVariable("${root}/doc/sdk/wxmini"));
        tempFileDir.deleteOnExit();
        tempFileDir.mkdirs();
        File tempFile = new File(tempFileDir, "sdk.js");
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.write(sdkStr, out, IDoc.CHARSET);
        tempFile = new File(tempFileDir, "sdk.js");
        return tempFile;
    }
}
