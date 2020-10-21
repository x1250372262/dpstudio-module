<%@ page import="net.ymate.platform.webmvc.context.WebContext" %>
<%@ page import="net.ymate.platform.webmvc.util.WebUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.ymate.net/ymweb_core" prefix="ymp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ymp:ui src="admin/base/base">
    <ymp:property name="title">安全设置</ymp:property>
    <ymp:property name="css">
        <link href="/admin/css/list.min.css" rel="stylesheet">
    </ymp:property>
    <ymp:layout src="admin/base/nav">
        <ymp:property name="pageTitle">
            安全设置
        </ymp:property>
        <ymp:property name="topSelect">security</ymp:property>
        <ymp:property name="twoSelect">security_setting</ymp:property>
    </ymp:layout>
    <ymp:layout>
        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">

                                <form method="post" action="#!" class="site-form">

                                    <div class="form-group ">
                                        <input type="hidden" name="id" value="">
                                        <input type="hidden" name="last_modify_time" value="">
                                    </div>
                                    <div class="form-group ">
                                        <label for="login_log_status">是否记录登录日志</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_log_status" id="login_log_status"
                                                       tvalue="0"><span>否</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_log_status" tvalue="1"><span>是</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="login_error_status">是否开启错误次数</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_error_status" id="login_error_status"
                                                       tvalue="0"><span>否</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_error_status" tvalue="1"><span>是</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="login_error_count">登录错误锁定次数</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" id="login_error_count"
                                                   name="login_error_count" value=""
                                                   placeholder="请输入登录错误锁定次数"/>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="login_error_time">登录错误锁定时间(单位分钟)</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" id="login_error_time"
                                                   name="login_error_time" value=""
                                                   placeholder="请输入登录错误锁定次数"/>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="login_unlock_founder">总管理员是否可以解锁</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_unlock_founder" id="login_unlock_founder"
                                                       tvalue="0"><span>否</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_unlock_founder" tvalue="1"><span>是</span>
                                            </label>
                                        </div>
                                    </div>
<%--                                    <div class="form-group ">--%>
<%--                                        <label for="login_not_ip_status">是否开启异地登录</label>--%>
<%--                                        <div class="clearfix">--%>
<%--                                            <label class="lyear-radio radio-inline radio-primary">--%>
<%--                                                <input type="radio" name="login_not_ip_status" id="login_not_ip_status"--%>
<%--                                                       tvalue="0"><span>否</span>--%>
<%--                                            </label>--%>
<%--                                            <label class="lyear-radio radio-inline radio-primary">--%>
<%--                                                <input type="radio" name="login_not_ip_status" tvalue="1"><span>是</span>--%>
<%--                                            </label>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group ">--%>
<%--                                        <label for="login_not_ip_notice">异地登录是否提醒</label>--%>
<%--                                        <div class="clearfix">--%>
<%--                                            <label class="lyear-radio radio-inline radio-primary">--%>
<%--                                                <input type="radio" name="login_not_ip_notice" id="login_not_ip_notice"--%>
<%--                                                       tvalue="0"><span>否</span>--%>
<%--                                            </label>--%>
<%--                                            <label class="lyear-radio radio-inline radio-primary">--%>
<%--                                                <input type="radio" name="login_not_ip_notice" tvalue="1"><span>是</span>--%>
<%--                                            </label>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
                                    <div class="form-group ">
                                        <label for="login_client_status">是否禁止多端登录</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_client_status" id="login_client_status"
                                                       tvalue="0"><span>否</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_client_status" tvalue="1"><span>是</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="login_client_type">多端登录的验证类型</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_client_type" id="login_client_type"
                                                       tvalue="0"><span>禁止登录</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="login_client_type" tvalue="1"><span>踢出系统</span>
                                            </label>
                                        </div>
                                    </div>

                                    <button type="button" class="btn btn-primary" id="submit">保存</button>
                                </form>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
        </div>
        </div>
    </ymp:layout>
    <!--图表插件-->
    <ymp:property name="script">
        <script type="text/javascript" src="/admin/js/public/request.js"></script>
        <script type="text/javascript">
            $(function () {
                //拉取数据
                jQuery.axget(null, "/dpstudio/security/setting/detail", null, function (e) {
                    FORM.setValues($(".card-body"), e.data);
                })

                $("#submit").click(function () {
                    var data = FORM.getValues($(".card-body"));
                    console.log(data);
                    jQuery.axpost(null, "/dpstudio/security/setting/update", data, function (e) {
                        if (e.ret == 0) {
                            lightyear.notify('修改成功~', 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center');
                            setTimeout(function () {
                                window.location.reload();
                            }, 1000)
                        } else {
                            lightyear.notify(e.msg != null ? e.msg : "修改失败", 'danger');
                        }
                    })

                });
            });

        </script>
    </ymp:property>
</ymp:ui>



