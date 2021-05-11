package com.mx.module.doc.sdk;

import com.mx.module.doc.IDocConfig;
import com.mx.module.doc.annotation.Doc;
import com.mx.module.doc.bean.ApiResult;
import com.mx.module.doc.bean.SdkInfo;
import com.mx.module.doc.exception.DocException;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @Author: mengxiang.
 * @Date: 2020/7/9.
 * @Time: 下午3:40.
 * @Description:
 */
public interface ISdk {

    /**
     * 生成sdk
     *
     * @param apiResult
     * @return
     */
    File create(ApiResult apiResult) throws Exception;


    /**
     * 准备数据
     *
     * @param apiResult
     * @return
     */
    default List<SdkInfo> readyData(ApiResult apiResult) {
        List<SdkInfo> sdkInfoList = new ArrayList<>();
        apiResult.getApiInfoList().forEach(apiInfo -> {
            SdkInfo sdkInfo = new SdkInfo();
            sdkInfo.setComment(apiInfo.getDocName());
            String sdkName = createSdkName(com.mx.module.doc.Doc.get().getConfig(), apiInfo.getApiModuleList().get(0).getType().getPackage());
            sdkInfo.setName(sdkName);
            List<SdkInfo.ApiInfo> apiInfos = new ArrayList<>();
            apiInfo.getApiModuleList().forEach(apiModule -> {
                String methodNamePrefix = "";
                RequestMapping requestMapping = apiModule.getType().getAnnotation(RequestMapping.class);
                if (requestMapping != null) {
                    methodNamePrefix = StringUtils.replace(requestMapping.value(),"/","_");
                }
                String finalMethodNamePrefix = methodNamePrefix;
                apiModule.getApiActions().forEach(apiAction -> {
                    SdkInfo.ApiInfo api = new SdkInfo.ApiInfo();
                    api.setTitle(apiAction.getTitle());
                    api.setUri(apiAction.getUri());
                    if (StringUtils.isNotBlank(finalMethodNamePrefix)) {
                        api.setSdkMethodName(finalMethodNamePrefix + "_" + apiAction.getName());
                    } else {
                        api.setSdkMethodName(apiAction.getName());
                    }
                    api.setMethodName(apiAction.getName());
                    api.setMethod(apiAction.getMethods().get(0).toUpperCase(Locale.ROOT));
                    apiInfos.add(api);
                });
            });
            sdkInfo.setApiInfos(apiInfos);
            sdkInfoList.add(sdkInfo);
        });
        return sdkInfoList;
    }

    default String createSdkName(IDocConfig iDocConfig, Package pack) {
        if (iDocConfig.isSdkEnabled()) {
            Doc doc = pack.getAnnotation(Doc.class);
            if (doc == null || StringUtils.isBlank(doc.sdkName())) {
                throw new DocException("开启sdk情况下，Doc注解，sdkName不能为空", new NullPointerException());
            }
            return doc.sdkName();
        }
        return null;
    }
}
