<%@taglib uri="http://www.ymate.net/ymweb_core" prefix="ymp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>后台登录</title>
    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="/admin/css/materialdesignicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/admin/plugins/sliderVerification/css/sliderVerification.min.css"/>
    <link href="/admin/css/style.min.css" rel="stylesheet">
    <style>
        body {
            display: -webkit-box;
            display: flex;
            -webkit-box-pack: center;
            justify-content: center;
            -webkit-box-align: center;
            align-items: center;
            height: 100%;
        }

        .login-box {
            display: table;
            table-layout: fixed;
            overflow: hidden;
            max-width: 700px;
        }

        .login-left {
            display: table-cell;
            position: relative;
            margin-bottom: 0;
            border-width: 0;
            padding: 45px;
        }

        .login-left .form-group:last-child {
            margin-bottom: 0px;
        }

        .login-right {
            display: table-cell;
            position: relative;
            margin-bottom: 0;
            border-width: 0;
            padding: 45px;
            width: 50%;
            max-width: 50%;
            background: #67b26f !important;
            background: -moz-linear-gradient(45deg, #67b26f 0, #4ca2cd 100%) !important;
            background: -webkit-linear-gradient(45deg, #67b26f 0, #4ca2cd 100%) !important;
            background: linear-gradient(45deg, #67b26f 0, #4ca2cd 100%) !important;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#67b26f', endColorstr='#4ca2cd', GradientType=1) !important;
        }

        .login-box .has-feedback.feedback-left .form-control {
            padding-left: 38px;
            padding-right: 12px;
        }

        .login-box .has-feedback.feedback-left .form-control-feedback {
            left: 0;
            right: auto;
            width: 38px;
            height: 38px;
            line-height: 38px;
            z-index: 4;
            color: #dcdcdc;
        }

        .login-box .has-feedback.feedback-left.row .form-control-feedback {
            left: 15px;
        }

        @media (max-width: 576px) {
            .login-right {
                display: none;
            }
        }
    </style>
</head>

<body style="background-image: url(/admin/images/login-bg-2.jpg); background-size: cover;">
<div class="bg-translucent p-10">

    <div class="login-box bg-white clearfix">
        <div class="login-left">
            <div class="login-header text-center" style="padding-bottom: 10px;font-size: 20px;">
                海淘拍后台管理系统
<%--                <img src="/admin/images/logo-sidebar.png" title="LightYear" alt="LightYear"/>--%>
            </div>

            <form id="loginForm" action="/dpstudio/security/admin/login" method="post">
                <div class="form-group has-feedback feedback-left">
                    <input type="text" placeholder="请输入您的用户名" class="form-control" name="user_name" id="userName"/>
                    <span class="mdi mdi-account form-control-feedback" aria-hidden="true"></span>
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="password" placeholder="请输入密码" class="form-control" id="password" name="password"/>
                    <span class="mdi mdi-lock form-control-feedback" aria-hidden="true"></span>
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="hidden" class="form-control" id="code" name="code"/>
                    <div id="slider-verification" class="slider"></div>
                </div>
                <div class="form-group has-feedback feedback-left">
                    <label class="lyear-checkbox checkbox-primary m-t-10">
                        <input type="checkbox" id="jizhu"><span>记住用户名,密码</span>
                    </label>
                </div>
                <div class="form-group">
                    <button class="btn btn-block btn-primary" id="loginButton" type="submit">立即登录
                    </button>
                </div>
            </form>
        </div>
        <div class="login-right">
            <div class="login-header text-center" style="padding-bottom: 10px;font-size: 20px;">
                海淘拍后台管理系统
            </div>
            <p class="text-white">Copyright © 2020 <a href="http://jc2014.com">隽铖网络</a>. All right reserved
            </p>
        </div>
    </div>
</div>
<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/admin/js/jquery.cokie.min.js"></script>
<script type="text/javascript" src="/admin/plugins/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<!--消息提示-->
<script type="text/javascript" src="/admin/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/admin/js/lightyear.js"></script>
<script type="text/javascript" src="/admin/js/md5.js"></script>
<script type="text/javascript" src="/admin/js/form.js"></script>
<script type="text/javascript" src="/admin/plugins/sliderVerification/js/jquery.sliderVerification.min.js"></script>
<script type="text/javascript">
    function changeCaptcha() {
        $("#captcha").attr("src", $("#captcha").attr("src") + '?d=' + Math.random());
    }

    $(document).ready(function () {

        //滑块验证
        $("#slider-verification").slider({
            width: 340,                     // width
            height: 38,                     // height
            sliderBg: "#f9fafb",            // 滑块背景颜色
            color: "#8b95a5",               // 文字颜色
            fontSize: 14,                   // 文字大小
            bgColor: "#15c377",             // 背景颜色
            textMsg: "按住滑块，拖拽验证",     // 提示文字
            successMsg: "验证通过",       // 验证成功提示文字
            successColor: "#fff",           // 滑块验证成功提示文字颜色
            time: 400,                      // 返回时间
            callback: function (result) {    // 回调函数，true(成功),false(失败)
                $("#code").val(result);
                $("#loginForm").bootstrapValidator('disableSubmitButtons', false);
            }
        });

        getCookie();
        function setCookie() { //设置cookie
            var userName = $("#userName").val(); //获取用户名信息
            var password = $("#password").val(); //获取用户名信息
            var checked = $("#jizhu:checked")//获取“是否记住密码”复选框
            if (checked && checked.length > 0) { //判断是否选中了“记住密码”复选框
                $.cookie("is_remmber", 1);//调用jquery.cookie.js中的方法设置cookie中的记住密码
                $.cookie("userName", userName);//调用jquery.cookie.js中的方法设置cookie中的用户名
                $.cookie("password", password);//调用jquery.cookie.js中的方法设置cookie中的密码
            } else {
                $.cookie("is_remmber", 0);
                $.cookie("userName", "");
                $.cookie("password", "");
            }
        }

        function getCookie() { //获取cookie
            var userName = $.cookie("userName"); //获取cookie中的用户名
            var userName = $("#userName").val( $.cookie("userName"));
            var password = $("#password").val( $.cookie("password"));
            if($.cookie("is_remmber")==1){
                $("#jizhu").attr("checked",true);
            }
        }

        $('#loginForm')
            .bootstrapValidator({
                message: '请输入正确内容',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    user_name: {
                        message: '请输入正确的用户名',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    password: {
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                }
                            }
                    }
                }
            })
            .on('success.form.bv', function (e) {
                lightyear.loading('show');
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                var $form = $(e.target);
                // Get the BootstrapValidator instance
                var bv = $form.data('bootstrapValidator');
                // Use Ajax to submit form data
                var data = FORM.getValues($('#loginForm'));
                if(!$.trim(data.code)){
                    layer.tips('请先验证', "#loginButton", {
                        tips: [1, '#f96868'],
                        time: 3000
                    });
                    lightyear.loading('hide');
                    $('#loginForm').bootstrapValidator('disableSubmitButtons', false);
                    return false;
                }
                data.password = hex_md5(data.password);
                $.post($form.attr('action'), data, function (result) {
                    lightyear.loading('hide');
                    if (result.ret == 0) {
                        lightyear.notify('登录成功，页面即将自动跳转~', 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center');
                        setTimeout(function () {
                            setCookie();
                            localStorage.setItem("menu_list",JSON.stringify(result.menu_list))
                            window.location.href = "/admin/home";
                        }, 1000)
                    } else if (result.ret == -1) {
                        changeCaptcha();
                        $('#loginForm').bootstrapValidator('disableSubmitButtons', false);
                        $.each(result.data, function (item) {
                            lightyear.notify(result.data[item] != null ? result.data[item] : "登录失败", 'danger');
                            return false;
                        });
                    } else {
                        changeCaptcha();
                        $('#loginForm').bootstrapValidator('disableSubmitButtons', false);
                        lightyear.notify(result.msg != null ? result.msg : "登录失败", 'danger');
                    }

                }, 'json');
            });
    });
</script>
</body>
</html>