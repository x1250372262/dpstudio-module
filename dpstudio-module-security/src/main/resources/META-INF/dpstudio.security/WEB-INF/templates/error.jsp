<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.ymate.net/ymweb_core" prefix="ymweb" %>
<%@ taglib uri="http://www.ymate.net/ymweb_fn" prefix="func" %>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>错误页面</title>
    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="/admin/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/admin/css/style.min.css" rel="stylesheet">
    <style>
        body{
            background-color: #fff;
        }
        .error-page {
            height: 100%;
            position: fixed;
            width: 100%;
        }
        .error-body {
            padding-top: 5%;
        }
        .error-body h1 {
            font-size: 210px;
            font-weight: 700;
            text-shadow: 4px 4px 0 #f5f6fa, 6px 6px 0 #33cabb;
            line-height: 210px;
            color: #33cabb;
        }
        .error-body h4 {
            margin: 30px 0px;
        }
    </style>
</head>

<body>
<section class="error-page">
    <div class="error-box">
        <div class="error-body text-center">
            <h1>
                <c:choose>
                    <c:when test="${not empty param.status}">
                        ${param.status}
                    </c:when>
                    <c:otherwise>
                        -50
                    </c:otherwise>
                </c:choose>
            </h1>
            <h4>${requestScope.msg}</h4>
            <c:choose>
                <c:when test="${requestScope.ret == 65102}">
                    <a href="/admin/login_view" class="btn btn-primary ">去登录</a>
                </c:when>
                <c:when test="${requestScope.ret == 65009}">
                    <a href="/admin/login_view" class="btn btn-primary ">重新登录</a>
                </c:when>
                <c:otherwise>
                    <a href="/admin/home" class="btn btn-primary ">返回首页</a>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</section>
<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<script type="text/javascript">;</script>
</body>
</html>