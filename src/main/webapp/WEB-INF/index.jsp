<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/" method="post">
    <label>Login:
        <input name="login" type="text" required="required">
    </label>
    <label>Password:
        <input name="password" type="text" required="required">
    </label>
    <button type="submit">Login</button>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Login or password is not correct</span>
        </div>
    </c:if>
    <c:if test="${param.userCreated != null}">
        <div style="color: darkgreen">
            <span>User ${param.userCreated} successfully created!</span>
        </div>
    </c:if>
    <c:if test="${param.report != null}">
        <div style="color: darkgreen">
            <span>Report sent</span>
        </div>
    </c:if>
</form>


</body>
</html>
