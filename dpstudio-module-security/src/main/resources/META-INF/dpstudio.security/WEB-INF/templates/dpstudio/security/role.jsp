<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.ymate.net/ymweb_core" prefix="ymp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ymp:ui src="admin/base/base">
    <ymp:property name="title">角色列表</ymp:property>
    <ymp:property name="css">
        <link href="/admin/css/list.min.css" rel="stylesheet">
    </ymp:property>
    <ymp:layout src="admin/base/nav">
        <ymp:property name="pageTitle">
            角色列表
        </ymp:property>
        <ymp:property name="topSelect">security</ymp:property>
        <ymp:property name="twoSelect">security_role</ymp:property>
    </ymp:layout>
    <ymp:layout>
        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="modal fade bs-example-modal-lg" id="commonDiv" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" data-backdrop="static">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="exampleModalLabel ">添加</h4>
                            </div>
                            <div class="modal-body" style="height: 250px;">
                                <form action="" id="commonForm">
                                    <div class="form-group col-md-12">
                                        <input type="hidden" name="id" value="">
                                        <input type="hidden" name="last_modify_time" value="">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label>角色名称</label><span class="required">
										* </span>
                                        <input type="text" class="form-control" id="name" name="name" value=""
                                               placeholder="请输入角色名称"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <div>
                                            <label>备注</label>
                                            <textarea class="form-control" id="remark" name="remark"
                                                      style="height: 100px;"></textarea></div>
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
                                <div class="menufix">
                                    <div class="menufix_title">操作</div>
                                    <div class="toolbar-btn-action">
                                        <a class="btn btn-primary m-r-5 creates" href="#!" data-toggle="modal"
                                           data-target="#commonDiv" data-whatever="@mdo"><i class="mdi mdi-plus"></i> 新增</a>

                                        <a class="btn btn-danger btn_top deletes" href="#!" data-toggle="modal"
                                           data-target=".removeDio" href="#!">
                                            <i class="mdi mdi-window-close"></i> 删除</a>

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table id="tableAjaxId" class="table table-bordered"
                                           listurl="/dpstudio/security/role/list"
                                           addUrl="/dpstudio/security/role/create"
                                           editUrl="/dpstudio/security/role/update"
                                           detailUrl="/dpstudio/security/role/detail"
                                           delUrl="/dpstudio/security/role/delete">
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

            });

            columns = [
                {
                    checkbox: true, // 显示一个勾选框
                    align: 'center' // 居中显示
                }, {
                    field: 'name',
                    title: '名称',
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
                return ' <a class="btn btn-xs btn-default edits" data-toggle="modal" data-target="#commonDiv" dataId="' + row.id + '" title="编辑" >编辑</a>' +
                    '<a class="btn btn-xs btn-default deletes" data-toggle="modal" data-target=".removeDio" dataId="' + row.id + '"  title="删除" >删除</a>';
            }

        </script>
    </ymp:property>
</ymp:ui>