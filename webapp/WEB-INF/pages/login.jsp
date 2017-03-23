<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<c:set var="ctx" value="${basePath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login</title>

    <link rel="stylesheet" href="${ctx}/static/css/login.css">
    <script src="${ctx}/static/js/jquery-1.11.3.min.js"></script>
</head>
    <body onload="document.login_form.username.focus()">
        <div id="content" class="login">
            <div class="logo">
                <h2>Login</h2>
                <div class="clear"></div>
            </div>
            <form name="login_form" th:action="@{/login}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="error-msg">
                    <c:if test="${param.error != null}">
                        <p>用户名或密码不正确.</p>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <p>您已经成功登出.</p>
                    </c:if>
                </div>
                <div class="label-control">
                    <label>用户名</label>
                    <input type="text" name="username"/>
                    <div class="clear"></div>
                </div>
                <div class="label-control">
                    <label>密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                    <input type="password" name="password"/>
                    <div class="clear"></div>
                </div>
                <div class="label-control">
                    <label>&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input id="remember_me" name="remember_me" type="checkbox"/>
                    <span onclick="$('#remember_me').click()">下次自动登陆</span>
                </div>
                <div class="button-control">
                    <input type="submit" value="登  录"/>
                </div>
            </form>
        </div>
    </body>
</html>
