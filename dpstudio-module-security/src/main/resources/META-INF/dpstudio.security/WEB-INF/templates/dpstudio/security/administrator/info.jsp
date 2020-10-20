<%@ page import="net.ymate.platform.webmvc.context.WebContext" %>
<%@ page import="net.ymate.platform.webmvc.util.WebUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.ymate.net/ymweb_core" prefix="ymp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ymp:ui src="admin/base/base">
    <ymp:property name="title">个人信息</ymp:property>
    <ymp:property name="css">
    </ymp:property>
    <ymp:layout src="admin/base/nav">
        <ymp:property name="pageTitle">
            个人信息
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

                                <div class="edit-avatar ">

                                    <div class="fileinput fileinput-new" data-provides="fileinput" id="fileinput">
                                        <div class="fileinput-new thumbnail" name="thumb">
                                            <img name="thumb" src="/admin/images/no_image.gif" alt=""/>
                                        </div>
                                        <div class="fileinput-preview fileinput-exists thumbnail">
                                        </div>
                                        <div>
                                            <span class="btn default btn-file">
                                                <span class="fileinput-new"> 选择图片 </span>
                                                <span class="fileinput-exists"> 重选 </span>
                                                <input type="file" name="file"/>
                                                <input type="hidden" name="thumb" class="fileresult" id="fileresult">
                                            </span>
                                            <a href="#" class="btn red fileinput-exists" data-dismiss="fileinput">
                                                移除 </a>
                                                <%--                                                <span class="btn green fileinput-exists upload">上传</span>--%>
                                            <span></span>
                                        </div>
                                    </div>


                                </div>
                                <hr>
                                <form method="post" action="#!" class="site-form">
                                    <div class="form-group">
                                        <label>用户名</label>
                                        <input type="text" class="form-control" name="user_name" disabled="disabled"/>
                                    </div>
                                    <div class="form-group">
                                        <label>真实姓名</label>
                                        <input type="text" class="form-control" name="real_name" id="realName"
                                               placeholder="输入您的昵称">
                                    </div>
                                    <div class="form-group">
                                        <label>手机号</label>
                                        <input type="number" class="form-control" name="mobile" id="mobile"
                                               aria-describedby="emailHelp" placeholder="请输入正确的手机号">
                                    </div>
                                    <div class="form-group ">
                                        <label for="gender">性别</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="gender" id="gender" tvalue="1"><span>男</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="gender" tvalue="2"><span>女</span>
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
                jQuery.axget(null, "/dpstudio/security/admin/info", null, function (e) {
                    FORM.setValues($(".card-body"), e.data);
                })

                $("#submit").click(function () {
                    var data = FORM.getValues($(".card-body"));
                    console.log(data);
                    jQuery.axpost(null, "/dpstudio/security/admin/update/info", data, function (e) {
                        if (e.ret == 0) {
                            lightyear.notify('修改成功~', 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center');
                            window.location.reload();
                        } else {
                            lightyear.notify(e.msg != null ? e.msg : "修改失败", 'danger');
                        }
                    })

                });
            });

        </script>
    </ymp:property>
</ymp:ui>



