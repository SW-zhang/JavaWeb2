<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="page" uri="/WEB-INF/pagerTag.tld" %>

<c:set var="ctx" value="${basePath}"/>
<!DOCTYPE html>
<html>
<head>
    <script>
        var webContext = "${basePath}";
    </script>

    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <link rel="stylesheet" href="${ctx }/static/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx }/static/css/page/pagetable.css">
</head>
<body>
<div class="block-white-m">
    <table class="table table-across-border table-page">
        <thead>
            <tr>
                <th>name</th>
                <th>parent_id</th>
                <th>level</th>
                <th>path</th>
                <th>status</th>
                <th>createTime</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.content}" var="demo">
                <tr>
                    <td>${demo.name}</td>
                    <td>${demo.parent_id}</td>
                    <td>${demo.level}</td>
                    <td>${demo.path}</td>
                    <td>${demo.status}</td>
                    <td>${demo.createTime}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <page:outpage pageSize="${page.size}" params="${pageParam}" totalPage="${page.totalPages}" curPage="${page.number}" url="${ctx}/demo/list_page"/>
</div>

</body>
</html>
