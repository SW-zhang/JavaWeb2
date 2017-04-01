<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <script src="${ctx}/static/js/jquery-1.11.3.min.js"></script>
</head>
<body>
测试add页面
<form:form action="${ctx}/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form:form>
<input type="button" value="addListDemos" class="addListDemos"/>
<input type="button" value="addDemo" class="addDemo"/>
</body>
<script>
    $("input.addListDemos").on('click', function () {
        var saveDataAry = [];
        var data1 = {"name": "测试", "path": "url测试"};
        var data2 = {"name": "测试2", "path": "url测试2"};
        saveDataAry.push(data1);
        saveDataAry.push(data2);
        $.ajax({
            type: "POST",
            url: "/demo/addListDemos",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(saveDataAry),
            success: function (data) {

            }
        });
    })
    $("input.addDemo").on('click', function () {
        $.post(webContext + "/demo/add", {"name": '测试', "path": 'url测试'}, function (data) {
            if (data.success) {
                alert("success");
            } else {
                alert(data.error);
            }
        }, 'json')
    })
</script>
</html>
