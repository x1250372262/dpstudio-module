<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.ymate.net/ymweb_core" prefix="ymp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ymp:ui src="admin/base/base">
    <ymp:property name="title">日志管理</ymp:property>
    <ymp:property name="css">
        <link href="/admin/css/list.min.css" rel="stylesheet">
    </ymp:property>
    <ymp:layout src="admin/base/nav">
        <ymp:property name="pageTitle">
            日志管理
        </ymp:property>
        <ymp:property name="topSelect">security</ymp:property>
        <ymp:property name="twoSelect">security_log</ymp:property>
    </ymp:layout>
    <ymp:layout>
        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

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
                                                <span for="">内容：</span>
                                                <input type="text" name="content" placeholder="请输入内容">
                                            </div>
                                            <div class="select_flex">
                                                <span for="">创建时间：</span>
                                                <input class="js-datetimepicker times" autocomplete="off"
                                                       data-date-format="yyyy-mm-dd hh:ii" type="text" id="startTime"
                                                       name="start_time" placeholder="从">
                                                <span class="input-group-addon" style="text-align: center;width: 50px;"><i
                                                        class="mdi mdi-chevron-right"></i></span>
                                                <input class="js-datetimepicker times" autocomplete="off"
                                                       data-date-format="yyyy-mm-dd hh:ii" type="text" id="endTime"
                                                       name="end_time" placeholder="至">

                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="card-toolbar clearfix">
                                <div class="menufix">
                                    <div class="menufix_title">操作</div>
                                    <div class="toolbar-btn-action">

                                        <a class="btn btn-danger btn_top deletes" href="#!" data-toggle="modal"
                                           data-target=".removeDio" href="#!">
                                            <i class="mdi mdi-window-close"></i> 删除</a>

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table id="tableAjaxId" class="table table-bordered"
                                           listurl="/dpstudio/security/admin/log/list"
                                           delUrl="/dpstudio/security/admin/log/delete">
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
                    field: 'content',
                    title: '内容',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'create_time',
                    title: '创建时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) { // 单元格格式化函数
                        return Utils.changeDateToString(new Date(value));
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
                return '<a class="btn btn-xs btn-default deletes" data-toggle="modal" data-target=".removeDio" dataId="' + row.id + '"  title="删除" >删除</a>';
            }

        </script>
    </ymp:property>
</ymp:ui>