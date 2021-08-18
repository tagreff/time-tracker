<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <button type="submit">Login</button>
</form>

</body>
</html>
