package com.mx.module.doc.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.mx.module.doc.Constants;
import com.mx.module.doc.bean.FieldInfo;
import com.mx.module.doc.impl.SourceFileManager;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import net.ymate.platform.webmvc.annotation.PathVariable;
import net.ymate.platform.webmvc.annotation.RequestParam;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mengxiang
 * @Date 2020.01.02.
 * @Time: 14:30.
 * @Description: 注释文本工具
 */
public class CommentUtils {

    private static final Pattern TAG_NAME_COMPILE = Pattern.compile("^@[\\w]+[\\t ]");

    private static final JavaParser JAVA_PARSER;

    static {
        JAVA_PARSER = new JavaParser();
    }

    /**
     * 获取注释的类型
     *
     * @param comment 注释文本
     */
    public static String getTagType(String comment) {
        Matcher m = TAG_NAME_COMPILE.matcher(comment);
        if (m.find()) {
            return m.group().trim();
        } else {
            return null;
        }
    }

    /**
     * 解析基本的文本注释
     *
     * @param comment 注释文本
     */
    public static String parseCommentText(String comment) {
        List<String> comments = asCommentList(comment);
        for (String s : comments) {
            if (!s.startsWith("@")) {
                return s;
            }
        }
        return "";
    }

    /**
     * 将注释转为多行文本
     *
     * @param comment 注释文本
     */
    public static List<String> asCommentList(String comment) {
        comment = comment.replaceAll("\\*", "").trim();
        String[] commentArr = comment.split("\n");
        List<String> comments = new ArrayList<>(commentArr.length);
        for (String str : commentArr) {
            if (StringUtils.isNotBlank(str)) {
                comments.add(str);
            }
        }
        return comments;
    }


    public static Map<String, String> fileCommentMap(Class<?> clazz) throws Exception {

        Map<String, String> commentMap = new HashMap<>();

        List<Class<?>> classes = new ArrayList<>();

        Class<?> tempClass = clazz;

        //获取所有的类(包括父类)
        while (true) {
            classes.add(0, tempClass);
            if (Object.class.equals(tempClass) || Object.class.equals(tempClass.getSuperclass())) {
                break;
            }
            tempClass = tempClass.getSuperclass();
        }

        for (Class<?> clz : classes) {
            String path = SourceFileManager.me().get(clz.getName());
            if (StringUtils.isBlank(path)) {
                continue;
            }
            FileInputStream in = new FileInputStream(path);
            CompilationUnit cu = JAVA_PARSER.parse(in).getResult().get();

            new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(FieldDeclaration n, Void arg) {
                    String name = n.getVariable(0).getName().asString();
                    String comment = "";
                    if (n.getComment().isPresent()) {
                        comment = n.getComment().get().getContent();
                    }
                    if (name.contains("=")) {
                        name = name.substring(0, name.indexOf("=")).trim();
                    }
                    commentMap.put(name, CommentUtils.parseCommentText(comment));
                }
            }.visit(cu, null);
        }
        return commentMap;
    }

    public static List<FieldInfo> findFieldInfo(Class<?> clazz, Map<String, String> commentMap) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(clazz);

        List<FieldInfo> fieldInfos = new ArrayList<>();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            //排除掉class属性
            if ("class".equals(propertyDescriptor.getName())) {
                continue;
            }
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setType(propertyDescriptor.getPropertyType());
            fieldInfo.setSimpleTypeName(propertyDescriptor.getPropertyType().getSimpleName());
            if (fieldMap.containsKey(propertyDescriptor.getName())) {
                Field field = fieldMap.get(propertyDescriptor.getName());
                if (field != null) {
                    JSONField jsonField = field.getAnnotation(JSONField.class);
                    RequestParam requestParam = field.getAnnotation(RequestParam.class);
                    PathVariable pathVariable = field.getAnnotation(PathVariable.class);
                    if (jsonField != null && StringUtils.isNotBlank(jsonField.name())) {
                        fieldInfo.setName(jsonField.name());
                    }else if (requestParam != null && StringUtils.isNotBlank(requestParam.value())) {
                        fieldInfo.setName(requestParam.value());
                    }else if (pathVariable != null && StringUtils.isNotBlank(pathVariable.value())) {
                        fieldInfo.setName(pathVariable.value());
                    } else {
                        fieldInfo.setName(propertyDescriptor.getName());
                    }
                } else {
                    fieldInfo.setName(propertyDescriptor.getName());
                }
            } else {
                fieldInfo.setName(propertyDescriptor.getName());
            }
            String comment = commentMap.get(propertyDescriptor.getName());
            if (StringUtils.isBlank(comment)) {
                fieldInfo.setComment("");
                fieldInfo.setRequire(false);
                fieldInfos.add(fieldInfo);
            } else {
                boolean require = false;
                String demoValue = "";
                if (comment.contains("|")) {
                    String[] commentArr = comment.split("\\|");
                    comment = commentArr[0];
                    if (commentArr.length == 2) {
                        String requireString = commentArr[1];
                        require = Constants.YES_EN.equalsIgnoreCase(requireString);
                    } else if (commentArr.length == 3) {
                        String requireString = commentArr[1];
                        require = Constants.YES_EN.equalsIgnoreCase(requireString);
                        demoValue = commentArr[2];
                    }
                }
                fieldInfo.setComment(comment);
                fieldInfo.setRequire(require);
                fieldInfo.setDemoValue(demoValue);
                fieldInfos.add(fieldInfo);
            }
        }
        return fieldInfos;
    }
}
