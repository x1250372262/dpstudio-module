<%@ page import="net.ymate.platform.webmvc.context.WebContext" %>
<%@ page import="net.ymate.platform.webmvc.util.WebUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.ymate.net/ymweb_core" prefix="ymp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ymp:ui src="admin/base/base">
    <ymp:property name="title">修改密码</ymp:property>
    <ymp:property name="css">
    </ymp:property>
    <ymp:layout src="admin/base/nav">
        <ymp:property name="pageTitle">
            修改密码
        </ymp:property>
    </ymp:layout>
    <ymp:layout>
        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">

                                <form method="post" action="/dpstudio/security/admin/pwd/update" id="passwordForm" class="site-form">
                                    <div class="form-group">
                                        <label for="old-password">旧密码</label>
                                        <input type="password" class="form-control" name="old_pwd" id="old-password"
                                               placeholder="输入账号的原登录密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="new-password">新密码</label>
                                        <input type="password" class="form-control" name="new_pwd" id="new-password"
                                               placeholder="输入新的密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="confirm-password">确认新密码</label>
                                        <input type="password" class="form-control" name="confirm_pwd"
                                               id="confirm-password" placeholder="请输入正确的邮箱地址">
                                    </div>
                                    <button type="submit" class="btn btn-primary">修改密码</button>
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

                $('#passwordForm')
                    .bootstrapValidator({
                        message: '请输入正确内容',
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            old_pwd: {
                                validators: {
                                    notEmpty: {
                                        message: '原密码不能为空'
                                    },
                                }
                            },
                            new_pwd: {
                                validators: {
                                    notEmpty: {
                                        message: '新密码不能为空'
                                    },
                                    identical: {
                                        field: 'confirmpwd',
                                        message: '两次输入的密码不相符'
                                    }
                                }
                            },
                            confirm_pwd: {
                                validators: {
                                    notEmpty: {
                                        message: '确认密码不能为空'
                                    },
                                    identical: {
                                        field: 'newpwd',
                                        message: '两次输入的密码不相符'
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
                        var data = FORM.getValues($('#passwordForm'));
                        data.confirm_pwd = hex_md5(data.confirm_pwd);
                        data.new_pwd = hex_md5(data.new_pwd);
                        data.old_pwd = hex_md5(data.old_pwd);
                        jQuery.axpost(null,$form.attr('action'),data,function(e){
                            if(e.ret == 0){
                                lightyear.notify('修改成功,请重新登录~', 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center');
                                setTimeout(function () {
                                    window.location.href = "/admin/login_view";
                                }, 1000)
                            }else{
                                lightyear.notify(e.msg != null ? e.msg : "修改失败", 'danger');
                            }
                        })
                    });
            });

        </script>
    </ymp:property>
</ymp:ui>



