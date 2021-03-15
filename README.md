# dpstudio-module

#### 介绍
基于YMP框架的开发模块

#### 软件架构
软件架构说明


#### DOC使用模板


```
/**
     * 首页
     *
     * @param  name 姓名|String|Y|张三
     * @return 首页
     * @throws Exception
     * @resp ret 错误码|int|Y|0
     * @resp msg 错误描述|String|N|
     * @resp name 姓名|String|Y|张三
     * @respbody {"ret":0,"msg":"","data":"张三"}
    */
```


```
 /**
     * dto
     *
     * @paramObj DemoDTO
     * @return dto
     * @throws Exception
     * @resp ret 错误码|int|Y|0
     * @resp msg 错误描述|String|N|
     * @respObj DemoDTO|data|object
     * @respbody {"ret":1,"data":{"age":11,"area":"","city":"大连","gender":1,"name":"张三"}}
    */
```


```
/**
     * 姓名|Y|张三
     */
    @VRequired(msg = "姓名不能为空")
    @RequestParam
    private String name;

    /**
     * 年龄|N|11
     */
    @RequestParam
    private Integer age;

    /**
     * 性别|N|1
     */
    @RequestParam
    private Integer gender;

    /**
     * 城市|N|大连
     */
    @RequestParam
    private String city;

    /**
     * 区域|N|
     */
    @RequestParam
    private String area;
```


