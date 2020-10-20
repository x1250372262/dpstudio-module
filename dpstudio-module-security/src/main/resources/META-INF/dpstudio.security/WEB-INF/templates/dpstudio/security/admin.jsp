<%@ page import="com.dpstudio.module.security.core.CommonMethod" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.ymate.net/ymweb_core" prefix="ymp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ymp:ui src="admin/base/base">
    <ymp:property name="title">管理员列表</ymp:property>
    <ymp:property name="css">
        <link href="/admin/css/list.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="/admin/plugins/layer/theme/default/layer.css">
    </ymp:property>
    <ymp:layout src="admin/base/nav">
        <ymp:property name="pageTitle">
            管理员列表
        </ymp:property>
        <ymp:property name="security">
            active open
        </ymp:property>
        <ymp:property name="security_admin">
            class="active active1"
        </ymp:property>
    </ymp:layout>
    <ymp:layout>
        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">
                <!--重置密码-->
                <div class="modal fade bs-example-modal-lg reset" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel" data-backdrop="static">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">×</span></button>
                                <h4 class="modal-title" id="myLargeModalLabel">重置密码？</h4>
                            </div>
                            <div class="modal-body">
                                是否确定重置密码?默认为用户名
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="resetPass">确认</button>
                                <input type="hidden" id="dataId">
                            </div>
                        </div>
                    </div>
                </div>
                <!--解除冻结-->
                <div class="modal fade bs-example-modal-lg lockDio" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel" data-backdrop="static">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">×</span></button>
                                <h4 class="modal-title" id="myLargeModalLabels">确认解除冻结</h4>
                            </div>
                            <div class="modal-body">
                                是否确认解除冻结?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="lock">确认</button>
                                <input type="hidden" id="lockDataId">
                            </div>
                        </div>
                    </div>
                </div>
                <!--添加-->
                <div class="modal fade bs-example-modal-lg" id="commonDiv" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" data-backdrop="static">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="exampleModalLabel ">添加</h4>
                            </div>
                            <div class="modal-body" style="height: 650px;">
                                <form action="" id="commonForm">
                                    <div class="form-group col-md-12">
                                        <input type="hidden" name="id" value="">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label>用户名</label><span class="required">
										* </span>
                                        <input type="text" class="form-control" id="user_name" name="user_name" value=""
                                               placeholder="请输入角色名"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label>真实名称</label>
                                        <input type="text" class="form-control" id="real_name" name="real_name" value=""
                                               placeholder="请输入真实名称"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <div>
                                            <label>头像</label>
                                        </div>
                                        <div class="fileinput fileinput-new" data-provides="fileinput" id="fileinput">
                                            <div class="fileinput-new thumbnail" name="photo_uri">
                                                <img src="/admin/images/no_image.gif" alt=""/>
                                            </div>
                                            <div name="photo_uri" class="fileinput-preview fileinput-exists thumbnail">
                                            </div>
                                            <div>
                                                <span class="btn default btn-file">
                                                    <span class="fileinput-new"> 选择图片 </span>
                                                    <span class="fileinput-exists"> 重选 </span>
                                                    <input type="file" name="file"/>
                                                    <input type="hidden" name="photo_uri" class="fileresult"
                                                           id="fileresult">
                                                </span>
                                                <a href="#" class="btn red 1-exists" data-dismiss="fileinput">
                                                    移除 </a>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label>密码</label><span class="required">
										* </span>
                                        <input type="password" class="form-control" id="password" name="password"
                                               value=""
                                               placeholder="密码"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <div>
                                            <label>性别</label>
                                        </div>
                                        <div>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="gender"
                                                       tvalue="0"><span>男</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="gender" tvalue="1"
                                                       checked><span>女</span>
                                            </label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer" style="height: 65px;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" isClick="0" class="btn btn-primary" id="save">保存</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-toolbar clearfix">
                                <div class="border">
                                    <div class="menufix borderNone">
                                        <div class="menufix_title">筛选</div>
                                        <div class="toolbar-btn-action">
                                            <a class="btn btn-danger" id="resetButton">重置</a>
                                            <a class="btn btn-primary m-r-5" id="searchButton">查询</a>
                                        </div>
                                    </div>
                                    <!-- 输入框 -->
                                    <form action="" id="searchForm">
                                        <div class="section_flex">
                                            <div>
                                                <span for="">用户名：</span>
                                                <input type="text" name="user_name" placeholder="请输入用户名">
                                            </div>
                                            <div>
                                                <span for="">真实姓名：</span>
                                                <input type="text" name="real_name" placeholder="请输入真实姓名">
                                            </div>
                                            <div class="select_flex">
                                                <span style=" width: 113px;" for="">请选择状态：</span>
                                                <select class="form-control" name="disable_status">
                                                    <option value="" selected="selected">请选择</option>
                                                    <option value="0">启用</option>
                                                    <option value="1">禁用</option>
                                                </select>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="card-toolbar clearfix">
                                <div class="menufix">
                                    <div class="menufix_title">操作</div>
                                    <div class="toolbar-btn-action">
                                        <a class="btn btn-primary m-r-5 creates" href="#!" data-toggle="modal"
                                           data-target="#commonDiv" data-whatever="@mdo"><i class="mdi mdi-plus"></i> 新增</a>

                                            <%-- <a class="btn btn-danger btn_top deletes" href="#!" data-toggle="modal"
                                                data-target=".removeDio" href="#!">
                                                 <i class="mdi mdi-window-close"></i> 删除</a>--%>

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table id="tableAjaxId" class="table table-bordered"
                                           listurl="/dpstudio/security/admin/list"
                                           addUrl="/dpstudio/security/admin/create"
                                           editUrl="/dpstudio/security/admin/update"
                                           resetPasswordUrl="/dpstudio/security/admin/resetPassword"
                                           detailUrl="/dpstudio/security/admin/detail"
                                           statusUrl="/dpstudio/security/admin/disabled"
                                           delUrl="/dpstudio/security/admin/delete">
                                    </table>
                                </div>
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
        <script type="text/javascript" src="/admin/js/table.js"></script>
        <script type="text/javascript" src="/admin/js/public/request.js"></script>
        <script src="/admin/js/select.js"></script>
        <script type="text/javascript">

            $(function () {
                Table.init();

                $("#save").click(function () {
                    $(this).attr("isClick", 1);
                    var data = FORM.getValues($('#commonForm'));
                    data.password = hex_md5(data.password);
                    jQuery.axpost($("#save"), $("#commonForm").attr("action"), data, function (e) {
                        if (e.ret == 0) {
                            lightyear.notify(e.msg ? e.msg : '操作成功', 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center');
                            setTimeout(function () {
                                $("#tableAjaxId").bootstrapTable('refresh');
                                $('#commonDiv').find(".close").trigger("click");
                            }, 1000)
                        } else {
                            lightyear.notify(e.msg != null ? e.msg : "操作失败", 'danger');
                        }
                    })
                });
                //重置密码
                $("#tableAjaxId").on("click", ".reset1", function () {
                    $("#dataId").val($(this).attr("dataId"))
                })
                $("#resetPass").on("click", function () {
                    var id = $("#dataId").val();

                    jQuery.axpost(null, "/dpstudio/security/admin/resetPassword", {"id": id,}, function (e) {
                        if (e.ret === 0) {
                            lightyear.notify('操作成功~', 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center');
                            setTimeout(function () {
                                $("#tableAjaxId").bootstrapTable('refresh');
                                $(".reset").find(".close").trigger("click");
                            }, 1000)
                        } else {
                            lightyear.notify(e.msg != null ? e.msg : "操作失败", 'danger');
                        }
                    })

                });
                //解除冻结
                $("#tableAjaxId").on("click", ".lock", function () {
                    $("#lockDataId").val($(this).attr("dataId"))
                })
                $("#lock").on("click", function () {
                    var id = $("#lockDataId").val();

                    jQuery.axpost(null, "/dpstudio/security/admin/unlock", {"id": id,}, function (e) {
                        if (e.ret === 0) {
                            lightyear.notify('操作成功~', 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center');
                            setTimeout(function () {
                                $("#tableAjaxId").bootstrapTable('refresh');
                                $(".lockDio").find(".close").trigger("click");
                            }, 1000)
                        } else {
                            lightyear.notify(e.msg != null ? e.msg : "操作失败", 'danger');
                        }
                    })

                });
            });

            columns = [
                {
                    checkbox: true, // 显示一个勾选框
                    align: 'center' // 居中显示
                }, {
                    field: 'photo_uri', // 返回json数据中的name
                    title: '头像', // 表格表头显示文字
                    align: 'center', // 左右居中
                    valign: 'middle', // 上下居中
                    formatter: function (value, row, index) { // 单元格格式化函数
                        if (value != undefined && value != null && value != '') {
                            return "<img data-toggle='modal' data-target='.picbig' class='smallpic' src='" + value + "' style='height:50px;width: 100px;'>";
                        }
                        return "<img data-toggle='modal' data-target='.picbig' class='smallpic' src='/admin/images/no_image.gif' style='height:50px;width: 100px;'>";
                    }
                }, {
                    field: 'user_name',
                    title: '用户名',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'real_name',
                    title: '姓名',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'disable_status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) { // 单元格格式化函数
                        if (value == 0) {
                            return "启用";
                        } else if (value == 1) {
                            return "禁用";
                        } else {
                            return "未知";
                        }
                    }
                }, {
                    field: 'login_lock_status',
                    title: '冻结状态',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) { // 单元格格式化函数
                        if (value == 0) {
                            return "正常";
                        } else if (value == 1) {
                            return "冻结";
                        } else {
                            return "未知";
                        }
                    }
                }, {
                    field: 'role_name',
                    title: '角色名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'create_time',
                    title: '创建时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) { // 单元格格式化函数
                        return Utils.changeTimeToString(new Date(value));
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: 160, // 定义列的宽度，单位为像素px
                    formatter: option
                }
            ]

            function option(value, row, index) {
                var html = ' <a class="btn btn-xs btn-default " href="/dpstudio/security/admin_role?id=' + row.id + '" title="角色列表">角色列表</a>' +
                    '<a class="btn btn-xs btn-default reset1" data-toggle="modal" data-target=".reset" dataId="' + row.id + '" title="重置密码" >重置密码</a>' +
                    ' <a class="btn btn-xs btn-default statusa" data-toggle="modal" data-target=".statusDio" status="' + row.disable_status + '" text0="禁用" text1="启用" dataId="' + row.id + '" title="禁用" >禁用or启用</a>' +
                    ' <a class="btn btn-xs btn-default " href="/dpstudio/security/admin_log?id=' + row.id + '" title="日志">登录日志</a>';
                var adminLock = <%=CommonMethod.settingDetail().getLoginUnlockFounder()%>
                if (row.login_lock_status === 1 && adminLock===1 ) {
                    html += '<a class="btn btn-xs btn-default lock" data-toggle="modal" data-target=".lockDio"  dataId="' + row.id + '" title="解冻" >解除冻结</a>';
                }
                return html;

            }

        </script>
    </ymp:property>
</ymp:ui>
